<template>
  <div>
    <h2><strong>Checkouts</strong></h2>
    <h4>Page: {{ this.page + 1 }}</h4>
  </div>
  <div class="col-sm-6 mx-auto" style="padding: 5px">
    <input type="button" v-on:click="previousPage" class="feedback2" style="margin-right: 5px" value="BACK">
    <input type="button" v-on:click="nextPage" class="feedback2" style="margin-left: 5px" value="NEXT">
  </div>
  <div>
    <ul>
      <li v-for="checkout in checkouts" :key="checkout.id">
        <h2>Book: {{ checkout.borrowedBook.title }}</h2>
        <p>Id: {{ checkout.id }}</p>
        <button class="feedback3" @click="selectCheckOut(checkout)">More Info</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      checkouts: [],
      page: 0,
    };
  },
  mounted() {
    this.getBooks();
  },
  methods: {
    async getBooks() {
      this.checkouts =(await axios.get("/api/checkout/getCheckouts?page=0" + "&sortBy=checkedOutDate")).data.content
    },
    async nextPage() {
      if ((await axios.get("/api/checkout/getCheckouts?page=0" + (this.page + 1)+ "&sortBy=checkedOutDate")).data.content.length > 0) {
        this.page++
        this.checkouts = (await axios.get("/api/checkout/getCheckouts?page=0" + this.page + "&sortBy=checkedOutDate")).data.content
        console.log(this.checkouts)
      }
    },

    async previousPage() {
      if (this.page > 0) {
        this.page--
        this.checkouts = (await axios.get("/api/checkout/getCheckouts?page=0" + this.page+ "&sortBy=checkedOutDate")).data.content
        console.log(this.checkouts)
      }
    },
    async selectCheckOut(checkout) {
      this.$router.push({ name: 'CheckOutView', params: { id: checkout.id } })
  }
  },
};
</script>

<style scoped>

table {
}


.feedback2 {
  background-color : lightcoral;
  color: white;
  padding: 8px 8px;
  border-radius: 4px;
  border-color: lightcoral;
}

.feedback3 {
  background-color : lightskyblue;
  color: white;
  padding: 8px 8px;
  border-radius: 4px;
  border-color: lightskyblue;
}


strong {
  font-weight: bold;
}
</style>