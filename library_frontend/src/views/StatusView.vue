<template>
  <div>
    <h2><strong>Books</strong></h2>
    <h4>Page: {{ this.page + 1 }}</h4>
  </div>
  <select v-model="status">
    <option v-for="status in possibleStatus" v-bind:key="status">
      {{ status }}
    </option>
  </select>

  <div class="col-sm-6 mx-auto" style="padding: 9px">
    <input type="button" v-on:click="searchBookByStatus" class="feedback" style="margin-right: 5px" value="SEARCH">
  </div>
  <div class="col-sm-6 mx-auto" style="padding: 5px">
    <input type="button" v-on:click="previousPage" class="feedback2" style="margin-right: 5px" value="BACK">
    <input type="button" v-on:click="nextPage" class="feedback2" style="margin-left: 5px" value="NEXT">
  </div>
  <div>
    <ul>
      <li v-for="book in books" :key="book.id">
        <h2>{{ book.title }}</h2>
        <p>Author: {{ book.author }}</p>
        <p>Year: {{ book.year }}</p>
        <p>Status: {{ book.status }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    let status
    return {
      books: [],
      page: 0,
      possibleStatus:["AVAILABLE", "BORROWED" ],
      status
    };
  },
  mounted() {
    this.getBooks();
  },
  methods: {
    async getBooks() {
      this.books =(await axios.get("/api/book/getBooks?page=0")).data.content
    },
    async nextPage() {
      if ((await axios.get("/api/book/getBooks?page=" + (this.page + 1))).data.content.length > 0) {
        this.page++
        this.books = (await axios.get("/api/book/getBooks?page=" + this.page)).data.content
        console.log(this.books)
      }
    },

    async previousPage() {
      if (this.page > 0) {
        this.page--
        this.books = (await axios.get("/api/book/getBooks?page=" + this.page)).data.content
        console.log(this.books)
      }
    },
    async searchBookByStatus() {
      if ((await axios.get("/api/book/searchByStatus?status=" + this.status)).data.length > 0) {
        this.page = 0
      }
      this.books = (await axios.get("/api/book/searchByStatus?status=" + this.status)).data
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


.feedback {
  background-color : #31B0D5;
  color: white;
  padding: 10px 20px;
  border-radius: 4px;
  border-color: #46b8da;
}


strong {
  font-weight: bold;
}
</style>