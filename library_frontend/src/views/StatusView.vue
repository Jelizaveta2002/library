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
    <input type="button" v-on:click="searchBookByStatus" class="my-button" style="margin-right: 5px" value="SEARCH">
  </div>
  <div class="col-sm-6 mx-auto" style="padding: 5px">
    <input type="button" v-on:click="previousPage" class="my-button" style="margin-right: 5px" value="BACK">
    <input type="button" v-on:click="nextPage" class="my-button" style="margin-left: 5px" value="NEXT">
  </div>
  <div>
    <ul>
      <li class = "book-info" v-for="book in books" :key="book.id">
        <h2>{{ book.title }}</h2>
        <p>Author:
          {{ book.author }}</p>
        <p>Year:
          {{ book.year }}</p>
        <input type="button" v-on:click="selectBook(book)" class="my-button" style="margin-left: 5px" value="More Info">
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
  },
  methods: {
    async nextPage() {
      if ((await axios.get("/api/book/searchByStatus?status=" + this.status + "&page=" + this.page)).data.length > 0) {
        this.page++
        this.books = (await axios.get("/api/book/searchByStatus?status=" + this.status + "&page=" + this.page)).data
        console.log(this.books)
      }
    },

    async previousPage() {
      if (this.page > 0) {
        this.page--
        this.books = (await axios.get("/api/book/searchByStatus?status=" + this.status + "&page=" + this.page)).data
        console.log(this.books)
      }
    },
    async searchBookByStatus() {
      this.books = (await axios.get("/api/book/searchByStatus?status=" + this.status + "&page=0")).data
    },
    async selectBook(book) {
      this.$router.push({ name: 'BookView', params: { id: book.id } })
    },
  },
};
</script>

<style scoped>

table {
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

.book-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  margin: 20px;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  background-color: #FFFFFF;
}


strong {
  font-weight: bold;
}
</style>