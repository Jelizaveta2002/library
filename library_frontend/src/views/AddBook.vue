<template>
  <div class="row">
    <div class="col-sm-3 mx-auto">
      <h1>Add new Book</h1>
      <br>
      <form class="row g-3" @submit="postData">
        <div class="col-md-6">
          <label for="inputFirstName" class="form-label">Title</label>
          <input type="text" class="form-control" id="inputFirstName" v-model="book.title">
        </div>
        <div class="col-md-6">
          <label for="inputLastName" class="form-label">Author</label>
          <input type="text" class="form-control" id="inputLastName" v-model="book.author">
        </div>
        <div class="col-12">
          <label for="inputEmail" class="form-label">Genre</label>
          <input type="text" class="form-control" id="inputEmail" v-model="book.genre">
        </div>
        <div class="col-12">
          <label for="inputPassword" class="form-label">Year</label>
          <input type="text" class="form-control" id="inputPassword" v-model="book.year" style="margin-bottom: 10px">
        </div>
        <div class="col-12">
          <label for="inputPassword" class="form-label">Count</label>
          <input type="text" class="form-control" id="inputPassword" v-model="book.checkOutCount" style="margin-bottom: 10px">
        </div>
        <div class="col-12">
          <input type="button" v-on:click="postData" class="my-button" style="margin-left: 5px" value="Add Book">
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  data(){
    return {
      book: {
        title: null,
        author: null,
        genre: null,
        year: null,
        checkOutCount: null
      },
    }
  },
  mounted() {
  },
  methods: {
    postData() {
      this.book = axios.post("/api/book/saveBook", this.book)
      alert("Book is successfully added !")
      router.push("/")
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