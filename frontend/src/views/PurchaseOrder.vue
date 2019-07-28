<template>
  <TableV1 :collection="pos" :template="template"></TableV1>
</template>
<script>
// @ is an alias to /src
import TableV1 from "@/components/block/TableV1.vue";
import { HTTP } from "./../http-common.js";

export default {
  name: "plantlisting",

  components: {
    TableV1
  },
  data: function() {
    return {
      pos: [],
      template: [
        {
          name: item => {
            return item.plant["name"];
          },
          type: "computed",
          label: "Plant Name"
        },
        {
          name: item => {
            return item.plant["description"];
          },
          type: "computed",
          label: "Plant Description"
        },
        { name: "status", type: "text", label: "PO Status" },
        {
          name: item => {
            return item.reservations[0]["location"];
          },
          type: "computed",
          label: "Reservation Status"
        },
        {
          name: item => {
            return (
              item.rentalPeriod["startDate"] +
              "/" +
              item.rentalPeriod["endDate"]
            );
          },
          type: "computed",
          label: "Rental Period"
        }
      ]
    };
  },
  mounted: function() {
    HTTP()
      .get("procurements/pos")
      .then(response => {
        this.pos = response.data;
      });
  }
};
</script>
