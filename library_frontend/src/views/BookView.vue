<template>
  <div class="book-info22">
    <h2>{{ title }}</h2>
    <p>Author: {{ author }}</p>
    <p>Genre: {{ genre }}</p>
    <p>Year: {{ year }}</p>
    <p>Status: {{ status }}</p>
  </div>
</template>

<script>

import axios from "axios";

export default {
  data() {
    return {
      bookId: null,
      book: null,
      title: null,
      author: null,
      year: null,
      genre: null,
      status: null
    }
  },
  async mounted() {
    const bookId = this.$route.params.id
    this.bookId = bookId;
    console.log(bookId)
    axios.get("/api/book/getBook?bookId=" + bookId).then(response => {
      this.book = response.data
      const title = this.book.title
      const author = this.book.author
      const year = this.book.year
      const genre = this.book.genre
      const status = this.book.status
      this.title = title
      this.author = author
      this.year = year
      this.genre = genre
      this.status = status
  })
},
  methods: {

  }
};
</script>

<style>
.book-info22 {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  margin: 50px;
  padding: 50px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  background-color: #FFFFFF;
}
</style>