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
        <p>First name: {{ checkout.borrowerFirstName }}</p>
        <p>Last name: {{ checkout.borrowerLastName }}</p>
        <p>Checked out date: {{ checkout.checkedOutDate }}</p>
        <p>Due date: {{ checkout.dueDate }}</p>
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


strong {
  font-weight: bold;
}
</style>