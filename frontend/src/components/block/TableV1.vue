<template>
  <table class="table is-striped is-fullwidth">
    <thead>
      <tr>
        <th class="has-text-center" v-for="(p, index) in template">{{p.label}}</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in collection" :key="index">
        <td v-for="(p, index) in template">
          <span v-if="p.type === 'text'">{{item[p.name]}}</span>
          <span v-else-if="p.type === 'computed'">{{p.name(item)}}</span>
        </td>
        <td>
          <tr v-for="(link, rel) in item._links" :key="rel">
            <a
              v-if="rel !== 'self'"
              v-bind:class="{ 'is-danger': link.method === 'DELETE', 'is-link': link.method !== 'DELETE' }"
              class="button is-small is-outlined"
              @click="followLink(link, rel)"
            >{{rel}}</a>
          </tr>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: "TableV1",
  props: ["collection", "title", "template"],
  data() {
    return {
      data: {}
    };
  }
  // methods: {
  //   submit: function() {

  //     console.log("TO DO:", this.template.method, this.url, this.data);
  //   },
  //   followLink: function(link, rel) {
  //     // Similar to the one we used for 'OpenOrders.vue'
  //   }
  // }
};
</script>