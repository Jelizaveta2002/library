<template>
  <div>
    <h2><strong>Books</strong></h2>
    <h4>Page: {{ this.page + 1 }}</h4>
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
    return {
      books: [],
      page: 0,
      selectedBook: null,
      favoriteBooks: []
    };
  },
  mounted() {
    this.getBooks();
    const favoriteBooks = localStorage.getItem('favoriteBooks');
    if (favoriteBooks) {
      this.favoriteBooks = JSON.parse(favoriteBooks);
    }
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

.book-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  margin: 20px;
  padding: 5px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  background-color: #FFFFFF;
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