<template>
  <b-tabs type="is-toggle" expanded v-model="activeTab">
    <b-tab-item label="Query catalog">
      <catalog-query @submitCatalogQuery="handleCatalogQuery"></catalog-query>
    </b-tab-item>
    <b-tab-item label="Select plant">
      <query-result :plants="plants" @selectPlant="handlePlantSelection"></query-result>
    </b-tab-item>
    <b-tab-item label="Review order">
      <order-data :order="order" @submitPlantHireRequest="handlePHCreation"></order-data>
    </b-tab-item>
  </b-tabs>
</template>

<script>
import CatalogQuery from "./CatalogQuery.vue";
import QueryResult from "./QueryResult.vue";
import OrderData from "./OrderData.vue";

import { HTTP } from "./../../http-common.js";

export default {
  name: "OrderCreation",
  components: {
    CatalogQuery,
    QueryResult,
    OrderData
  },
  data: function() {
    return {
      activeTab: 0,
      plants: [],
      rawStartDate: null,
      rawEndDate: null,
      order: {
        plant: {},
        rentalPeriod: {}
      }
    };
  },
  methods: {
    handleCatalogQuery: function(query) {
      if (query.name && query.startDate && query.endDate) {
        let params = {
          name: query.name,
          startDate: query.startDate.toLocaleDateString("lt-LT", {
            year: "numeric",
            month: "2-digit",
            day: "2-digit"
          }),
          endDate: query.endDate.toLocaleDateString("lt-LT", {
            year: "numeric",
            month: "2-digit",
            day: "2-digit"
          })
        };

        this.rawStartDate = query.startDate;
        this.rawEndDate = query.endDate;
        HTTP()
          .get("procurements/plants", {
            params: params
          })
          .then(response => {
            console.log(response);
            this.activeTab = 1;
            this.plants = response.data;
            this.order.rentalPeriod.startDate = params.startDate;
            this.order.rentalPeriod.endDate = params.endDate;
            // this.plants.
          })
          .catch(e => {
            console.log(e);
            this.activeTab = 1;
          });
      }
    },
    handlePlantSelection: function(plant) {
      console.log(plant);

      this.order.plant = plant;
      this.activeTab = 2;

      var date1_ms = this.rawStartDate.getDate();
      var date2_ms = this.rawEndDate.getDate();

      this.order.cost = this.order.plant.price * (date2_ms - date1_ms);
    },
    handlePHCreation: function() {
      let body = {
        plant: {
          itemId: this.order.plant._id,
          href: this.order.plant._links.self.href,
          name: this.order.plant.name
          //   description: this.order.plant.description,
          //   price: this.order.plant.price
        },
        rentalPeriod: {
          startDate: this.order.rentalPeriod.startDate,
          endDate: this.order.rentalPeriod.endDate
        },

        site: this.order.site,
        supplier: this.order.plant.supplier,
        siteEngineer: this.order.siteEngineer,
        cost: this.order.cost,
        status: "CREATED"
      };

      console.log(body);

      HTTP()
        .post("procurements/hire", body)
        .then(response => {
          this.$snackbar.open(
            "Hire Request submitted. Waiting for confirmation."
          );
        })
        .catch(error => {
          this.$snackbar.open({
            type: "is-danger",
            message: "Something went wrong with Hire Request submition."
          });
        });
    }
  }
};
</script>