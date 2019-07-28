<!-- eslint-disable vue/no-use-v-if-with-v-for,vue/no-confusing-v-for-v-if -->
<template>
  <div>
    <table class="table is-striped is-fullwidth">
      <thead>
        <tr>
          <th class="has-text-center">Engineer</th>
          <th class="has-text-center">Plant</th>
          <th class="has-text-center">Rental period</th>
          <th class="has-text-center">Status</th>
          <th class="has-text-center">Site</th>
          <th class="has-text-center">Price</th>
          <th class="has-text-center">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(order, order_index) in orders" :key="order_index">
          <td>{{order.siteEngineer}}</td>
          <td>
            {{order.plant.name}}
            <br>
            <a
              class="button is-link is-small is-outlined"
              @click="plantModal(order.plant)"
            >See details</a>
          </td>
          <td>{{order.rentalPeriod.startDate}} / {{order.rentalPeriod.endDate}}</td>
          <td>{{order.status}}</td>
          <td>{{order.site}}</td>
          <td>{{order.cost}}</td>
          <td>
            <a
              v-for="(link, rel) in order._links"
              :key="rel"
              v-if="rel !== 'self'"
              v-bind:class="{ 'is-danger': link.type === 'DELETE', 'is-link': link.type !== 'DELETE' }"
              class="button is-small is-outlined"
              @click="followLink(link, rel, order_index, order)"
            >{{rel}}</a>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import Vue from "vue";
import PlantModal from "./PlantModal.vue";
import Generic from "./Generic.vue";
import ModalV1 from "../block/ModalV1.vue";
import { HTTP } from "./../../http-common.js";

export default {
  name: "OpenOrders",
  data: function() {
    return {
      orders: []
    };
  },
  mounted: function() {
    HTTP()
      .get("procurements/hires")
      .then(response => {
        this.orders = response.data;
        //   !response.data._embedded
        //     ? []
        //     : response.data._embedded.purchaseOrderDToes;
      });
  },
  methods: {
    plantModal: function(plant) {
      if (plant && plant.href) {
        let otherToken = null;
        if (plant._ownerToken) {
          otherToken = { Authorization: plant._ownerToken };
        }

        HTTP(otherToken)
          .get(plant.href)
          .then(response => {
            console.log(response.data);
            //     plantDetails = response.data;

            this.$modal.open({
              parent: this,
              component: PlantModal,
              props: { plant: response.data }
            });
          });
      }
    },
    followLink: function(link, rel, order_index, order_item) {
      if (link.type) {
        if (
          (link.type === "PUT" ||
            link.type === "PATCH" ||
            link.type === "POST") &&
          (rel === "updated" || rel === "ExtendPO")
        ) {
          const startDateArray = order_item.rentalPeriod.startDate.split("-");
          const endDateArray = order_item.rentalPeriod.endDate.split("-");
          let startDate = new Date(
            startDateArray[0],
            startDateArray[1],
            startDateArray[2]
          );
          let endDate = new Date(
            endDateArray[0],
            endDateArray[1],
            endDateArray[2]
          );
          let title = "Edit Plant Hire Request";
          let template = [
            { name: "startDate", type: "date" },
            { name: "endDate", type: "date" }
          ];

          let defaultData = {
            startDate: startDate,
            endDate: endDate
          };

          if (rel === "ExtendPO") {
            template[0].readonly = true;
            title = "Extent Purchase Order";
            defaultData.startDate = order_item.rentalPeriod.startDate;
            defaultData.oldEndDate = order_item.rentalPeriod.endDate || endDate;
          }

          this.$modal.open({
            parent: this,
            component: ModalV1,

            props: {
              title: title,
              mode: "form",
              template: template,
              defaultData: defaultData,
              submit: (update, callback) => {
                let data = {};
                data.rentalPeriod = update;
                data._id = order_item._id;

                if (rel === "ExtendPO") {
                  data.entityId = order_item._id;
                  data.oldEndDate = update.oldEndDate;
                  data.client = order_item.po.supplierName;
                  data.endDate = update.endDate.toLocaleDateString("lt-LT", {
                    year: "numeric",
                    month: "2-digit",
                    day: "2-digit"
                  });
                }
                //               Long entityId;
                // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                // LocalDate oldEndDate;
                // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                // LocalDate endDate;
                // String client;
                HTTP()({
                  method: link.type,
                  url: link.href,
                  data: data
                }).then(response => {
                  if (response && response.data) {
                    Vue.set(this.orders, order_index, response.data);
                    callback();
                  }
                });
              }
            }
          });
          return;
        }

        HTTP()({
          method: link.type,
          url: link.href
        })
          .then(response => {
            console.log(response.data);
            if (rel === "details") {
              const template = {};

              this.$modal.open({
                parent: this,
                component: GenericModalForm,
                props: {
                  title: rel,
                  collection: null,
                  template: template,
                  url: response.data._links.self.href
                }
              });

              return;
            }
            Vue.set(this.orders, order_index, response.data);
          })
          .catch(error => console.log(error));
      } else {
        HTTP()
          .get(link.href)
          .then(response => {
            let collection = [];
            if (
              response.data._embedded &&
              response.data._embedded.purchaseOrderDToes
            )
              collection = response.data._embedded.purchaseOrderDToes;
            this.$modal.open({
              parent: this,
              component: Generic,
              props: {
                title: rel,
                collection: collection,
                template: response.data._templates.default,
                url: response.data._links.self.href
              }
            });
          })
          .catch(error => console.log(error));
      }
    }
  }
};
</script>