<template>
  <div>
    <h2><strong>Late Checkouts</strong></h2>
    <h4>Page: {{ this.page + 1 }}</h4>
  </div>
  <div class="col-sm-6 mx-auto" style="padding: 5px">
    <input type="button" v-on:click="previousPage" class="my-button" style="margin-right: 5px" value="BACK">
    <input type="button" v-on:click="nextPage" class="my-button" style="margin-left: 5px" value="NEXT">
  </div>
  <div>
    <ul>
      <li v-for="checkout in checkouts" :key="checkout.id">
        <h2>Book: {{ checkout.borrowedBook.title }}</h2>
        <p>Due Date: {{ checkout.dueDate }}</p>
        <button class="my-button" @click="selectCheckOut(checkout)">More Info</button>
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
      this.checkouts =(await axios.get("/api/checkout/getLateCheckouts?page=0")).data
    },
    async nextPage() {
      if ((await axios.get("/api/checkout/getLateCheckouts?page=" + (this.page + 1))).data.length > 0) {
        this.page++
        this.checkouts = (await axios.get("/api/checkout/getLateCheckouts?page=" + this.page)).data
        console.log(this.checkouts)
      }
    },

    async previousPage() {
      if (this.page > 0) {
        this.page--
        this.checkouts = (await axios.get("/api/checkout/getLateCheckouts?page=" + this.page)).data
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

.my-button {
  background-color: #c2fbd7;
  border-radius: 100px;
  box-shadow: rgba(44, 187, 99, .2) 0 -25px 18px -14px inset,rgba(44, 187, 99, .15) 0 1px 2px,rgba(44, 187, 99, .15) 0 2px 4px,rgba(44, 187, 99, .15) 0 4px 8px,rgba(44, 187, 99, .15) 0 8px 16px,rgba(44, 187, 99, .15) 0 16px 32px;
  color: green;
  cursor: pointer;
  display: inline-block;
  font-family: CerebriSans-Regular,-apple-system,system-ui,Roboto,sans-serif;
  padding: 7px 20px;
  text-align: center;
  text-decoration: none;
  transition: all 250ms;
  border: 0;
  font-size: 16px;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  margin-bottom: 10px;
}


.my-button:hover {
  box-shadow: rgba(44,187,99,.35) 0 -25px 18px -14px inset,rgba(44,187,99,.25) 0 1px 2px,rgba(44,187,99,.25) 0 2px 4px,rgba(44,187,99,.25) 0 4px 8px,rgba(44,187,99,.25) 0 8px 16px,rgba(44,187,99,.25) 0 16px 32px;
  transform: scale(1.05) rotate(-1deg);
}



strong {
  font-weight: bold;
}
</style>