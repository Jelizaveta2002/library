<template>
  <div>
    <h2>{{ title }}</h2>
    <p>Borrower Name: {{ borrowerFirstName }}</p>
    <p>Borrower Surname: {{ borrowerLastName }}</p>
    <p>Due Date: {{ dueDate }}</p>
    <p>Checked-Out Date: {{ borrowedId }}</p>
    <p>Returned Date: {{ returnedDate }}</p>
    <button class="feedback3" @click="deleteCheckOut(borrowedId)">Return Book</button>
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
        const idToRem = this.borrowedBook
        axios.get('/api/book/updateStatus?id=' + this.borrowedId)
        this.$router.push({ name: 'BookView', params: idToRem })
      } else {
        alert("Book is already returned, Returned Date is added !")
      }
    }
  }
};
</script>

<style>
.feedback3 {
  background-color : lightskyblue;
  color: white;
  padding: 8px 8px;
  border-radius: 4px;
  border-color: lightskyblue;
}
</style>