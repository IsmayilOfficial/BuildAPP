<template>
  <div>
    <section>
      <b-field label="Site-Engineer">
        <b-input v-model="order.siteEngineer"></b-input>
      </b-field>
      <b-field label="Site">
        <b-input v-model="order.site"></b-input>
      </b-field>
      <button class="button is-primary" v-on:click="submit">Create PlantHire Request</button>
    </section>
    <table class="table is-table-bordered is-table-striped is-fullwidth">
      <thead>
        <tr>
          <th class="has-text-center">Name</th>
          <th class="has-text-center">Start Date</th>
          <th class="has-text-center">End Date</th>
          <th class="has-text-center">Price</th>
          <th class="has-text-center">Subtotal</th>
          <!-- <th class="has-text-center">Actions</th> -->
        </tr>
      </thead>
      <tbody>
        <tr v-if="!!order.plant">
          <td>{{order.plant.name}}</td>
          <td>{{order.rentalPeriod.startDate}}</td>
          <td>{{order.rentalPeriod.endDate}}</td>
          <td>{{order.plant.price}}</td>
          <td>{{order.cost}}</td>
          <!-- <td>
              <a class="button is-danger is-outlined">Reject</a> |
              <a class="button is-warning is-outlined">Update</a> |
          <a class="button is-success is-outlined">Approve</a></td>-->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "OrderData",
  props: ["order"],
  data: function() {
    return {
      siteEngineer: "",
      siteAddress: ""
    };
  },
  methods: {
    submit: function() {
      this.$emit("submitPlantHireRequest");
    }
  }
};
</script>