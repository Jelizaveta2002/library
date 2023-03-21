<template>
  <div>
    <h2>{{ title }}</h2>
    <p>Borrower Name: {{ borrowerFirstName }}</p>
    <p>Borrower Surname: {{ borrowerLastName }}</p>
    <p>Due Date: {{ dueDate }}</p>
    <p>Checked-Out Date: {{ checkedOutDate }}</p>
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
      borrowedBook: null
    }
  },
  async mounted() {
    const checkOutId = this.$route.params.id
    console.log(checkOutId)
    axios.get("/api/checkout/getCheckout?checkOutId=" + checkOutId).then(response => {
      this.checkOut = response.data
      const borrowedBook = this.checkOut.borrowedBook
      const title = this.checkOut.borrowedBook.title
      const borrowerFirstName = this.checkOut.borrowerFirstName
      const borrowerLastName = this.checkOut.borrowerLastName
      const checkedOutDate = this.checkOut.checkedOutDate
      const dueDate = this.checkOut.dueDate
      this.borrowedBook = borrowedBook
      this.title = title
      this.borrowerFirstName = borrowerFirstName
      this.borrowerLastName = borrowerLastName
      this.checkedOutDate = checkedOutDate
      this.dueDate = dueDate
    })
  },
  methods: {
  }
};
</script>