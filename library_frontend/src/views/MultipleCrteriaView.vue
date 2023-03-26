<template>
  <div>
    <h2><strong>Search by Multiple Criteria</strong></h2>
    <h4>Page: {{ this.page + 1 }}</h4>
  </div>
  <div class="col-md-6">
    <label for="inputFirstName" class="form-label">Title</label>
    <input v-model="title" placeholder="search ..." type="text" class="form-control">
  </div>
  <div class="col-md-6">
    <label for="inputFirstName" class="form-label">Author</label>
    <input v-model="author" placeholder="search ..." type="text" class="form-control">
  </div>
  <div class="col-md-6">
    <label for="inputFirstName" class="form-label">Status</label>
    <select v-model="status">
      <option v-for="status in possibleStatus" v-bind:key="status">
        {{ status }}
      </option>
    </select>
  </div>
  <div class="col-sm-6 mx-auto" style="padding: 9px">
    <input type="button" v-on:click="searchBook" class="my-button" style="margin-right: 5px" value="Search">
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
  data(){
    let title;
    let author;
    let status;
    return {
      possibleStatus:["AVAILABLE", "BORROWED" ],
      books: [],
      page: 0,
      title,
      author,
      status
    }
  },
  mounted() {
  },
  methods: {
    async selectBook(book) {
      this.$router.push({ name: 'BookView', params: { id: book.id } })
    },
    async nextPage() {
      if ((await axios.get("/api/book/searchByMultiple?title=" + this.title + "&author="
          + this.author +"&status=" + this.status + "&page=" + (this.page + 1))).data.length > 0) {
        this.page++
        this.books = (await axios.get("/api/book/searchByMultiple?title=" + this.title + "&author="
            + this.author +"&status=" + this.status + "&page=" + this.page)).data
        console.log(this.books)
      }
    },

    async previousPage() {
      if (this.page > 0) {
        this.page--
        this.books = (await axios.get("/api/book/searchByMultiple?title=" + this.title + "&author="
            + this.author +"&status=" + this.status + "&page=" + this.page)).data
        console.log(this.books)
      }
    },

    async searchBook() {
      this.books = (await axios.get("/api/book/searchByMultiple?title=" + this.title + "&author="
          + this.author +"&status=" + this.status + "&page=0")).data
    }
  },
};
</script>

<style>

input[type="text"] {
  padding: 8px 16px;
  border-radius: 4px;
  border: 1px solid #ccc;
  margin-bottom: 16px;
}

.book-info {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin: 20px;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  background-color: #FFFFFF;
}

label {
  display: block;
  margin-bottom: 8px;
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


</style>