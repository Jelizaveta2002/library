<template>
  <div>
    <h2><strong>Favourite books</strong></h2>
  </div>
  <div>
    <ul>
      <li class = "book-info" v-for="book in this.favoriteBooks" :key="book.id">
        <h2>{{ book.title }}</h2>
        <p>Author:
          {{ book.author }}</p>
        <p>Year:
          {{ book.year }}</p>
        <input type="button" v-on:click="selectBook(book)" class="my-button" style="margin-left: 5px" value="More Info">
        <input type="button" v-on:click="deleteFavourite(book)" class="my-button" style="margin-left: 5px" value="Delete from Favourites">
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      favoriteBooks: []
    };
  },
  mounted() {
    const favoriteBooks = localStorage.getItem('favoriteBooks');
    if (favoriteBooks) {
      this.favoriteBooks = JSON.parse(favoriteBooks);
    }
  },
  methods: {
    deleteFavourite(obj) {
      const bookIndexToDelete = this.favoriteBooks.findIndex(book => book.id === obj.id);
      if (bookIndexToDelete !== -1) {
        this.favoriteBooks.splice(bookIndexToDelete, 1);
        localStorage.setItem('favoriteBooks', JSON.stringify(this.favoriteBooks));
    }
  },
    async selectBook(book) {
      this.$router.push({ name: 'BookView', params: { id: book.id } })
    }
}};
</script>

<style scoped>

table {
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