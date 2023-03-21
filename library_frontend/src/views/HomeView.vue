<template>
  <div>
    <h2><strong>Books</strong></h2>
    <h4>Page: {{ this.page + 1 }}</h4>
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
        <button class="feedback3" @click="selectBook(book)">More Info</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      books: [],
      page: 0,
      selectedBook: null
    };
  },
  mounted() {
    this.getBooks();
  },
  methods: {
    async getBooks() {
      this.books =(await axios.get("/api/book/getBooks?page=0" + "&sortBy=title")).data.content
    },
    async nextPage() {
      if ((await axios.get("/api/book/getBooks?page=" + (this.page + 1)+ "&sortBy=title")).data.content.length > 0) {
        this.page++
        this.books = (await axios.get("/api/book/getBooks?page=" + this.page + "&sortBy=title")).data.content
        console.log(this.books)
      }
    },

    async previousPage() {
      if (this.page > 0) {
        this.page--
        this.books = (await axios.get("/api/book/getBooks?page=" + this.page+ "&sortBy=title")).data.content
        console.log(this.books)
      }
    },
    async selectBook(book) {
      this.$router.push({ name: 'BookView', params: { id: book.id } })
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