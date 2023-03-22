<template>
  <div class="book-info">
    <h2 class="book-title" >{{ title }}</h2>
    <p class = "book-author">Borrower Name: {{ borrowerFirstName }}</p>
    <p class = "book-author">Borrower Surname: {{ borrowerLastName }}</p>
    <p>Due Date: {{ dueDate }}</p>
    <p>Checked-Out Date: {{ checkedOutDate }}</p>
    <p>Returned Date: {{ returnedDate }}</p>
    <button class="my-button" @click="watchBook(borrowedId)">Watch Book</button>
    <button class="my-button" @click="deleteCheckOut(borrowedId)">Return Book</button>
    <p>NB: After deleting checkout book
      status will be automatically changed to AVAILABLE</p>
  </div>
</template>

<script>

import axios from "axios";

export default {
  data() {
    return {
      checkOut: null,
      title: null,
      borrowerFirstName: null,
      borrowerLastName: null,
      checkedOutDate: null,
      dueDate: null,
      borrowedBook: null,
      borrowedId: null,
      returnedDate: null
    }
  },
  async mounted() {
    const checkOutId = this.$route.params.id
    console.log(checkOutId)
    axios.get("/api/checkout/getCheckout?checkOutId=" + checkOutId).then(response => {
      this.checkOut = response.data
      const borrowedBook = this.checkOut.borrowedBook
      const returnedDate = this.checkOut.returnedDate
      const title = this.checkOut.borrowedBook.title
      const borrowedId = this.checkOut.borrowedBook.id
      const borrowerFirstName = this.checkOut.borrowerFirstName
      const borrowerLastName = this.checkOut.borrowerLastName
      const checkedOutDate = this.checkOut.checkedOutDate
      const dueDate = this.checkOut.dueDate
      this.returnedDate = returnedDate
      this.borrowedId = borrowedId
      this.borrowedBook = borrowedBook
      this.title = title
      this.borrowerFirstName = borrowerFirstName
      this.borrowerLastName = borrowerLastName
      this.checkedOutDate = checkedOutDate
      this.dueDate = dueDate
    })
  },
  methods: {
    deleteCheckOut() {
      if (this.returnedDate == null) {
        this.returnedDate = axios.get('/api/checkout/setDate?checkOutId=' + this.checkOut.id)
        axios.get('/api/book/updateStatus?id=' + this.borrowedId)
        location.reload()
      } else {
        alert("Book is already returned, Returned Date is added !")
      }
    },
    watchBook(){
      const idToRem = this.borrowedBook
      this.$router.push({ name: 'BookView', params: idToRem })
    }
  }
};
</script>

<style>
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
.feedback3 {
  background-color : lightskyblue;
  color: white;
  padding: 8px 8px;
  border-radius: 4px;
  border-color: lightskyblue;
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

.book-title {
  font-size: 32px;
  font-weight: bold;
  margin: 0;
  text-transform: uppercase;
}

.book-author {
  font-size: 24px;
  margin: 0;
}

.book-cover {
  width: 200px;
  height: 300px;
  object-fit: cover;
  margin-right: 20px;
  border-radius: 5px;
}

.book-description {
  font-size: 16px;
  margin: 20px 0;
  line-height: 1.5;
}

.book-link {
  display: inline-block;
  padding: 10px 20px;
  background-color: #4CAF50;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.book-link:hover {
  background-color: #388E3C;
}

</style>