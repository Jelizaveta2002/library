<template>
  <div>
    <header>
      <h1>Search book</h1>
    </header>
    <div class="options">
      <div v-for="option in options" :key="option.id" class="option">
        <button @click="goToPage(option.path)">
          <h2>{{ option.title }}</h2>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      options: [
        {
          id: 1,
          title: 'Search by Status',
          path: '/status'
        },
        {
          id: 2,
          title: 'Search by Title',
          path: '/about'
        },
        {
          id: 3,
          title: 'Multiple Criteria',
          path: '/multiple'
        },
        {
          id: 4,
          title: 'Add Book',
          path: '/add'
        },
        {
          id: 5,
          title: 'Books',
          path: '/'
        }
      ]
    }
  },
  methods: {
    goToPage(path) {
      this.$router.push(path);
    }
  }
}
</script>

<style>
/* Styles for the page */
body {
  font-family: Arial, sans-serif;
  background-color: #F9F9F9;
  margin: 0;
  padding: 0;
}
header {
  background-color: #c2fbd7;
  color: green;
  text-align: center;
  padding: 10px;
}
h1 {
  font-size: 3rem;
  margin: 0;
  text-transform: uppercase;
}
.options {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  margin: 2rem;
}
.option {
  margin: 1rem;
  width: 300px;
  height: 150px;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}
.option:hover {
  transform: scale(1.05);
  box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
}
button {
  width: 100%;
  height: 100%;
  background-color: #c2fbd7;
  border: none;
  font-size: 2rem;
  transition: all 0.3s ease;
  color: green;
  cursor: pointer;
  display: inline-block;
}
button:hover {
  box-shadow: rgba(44,187,99,.35) 0 -25px 18px -14px inset,rgba(44,187,99,.25) 0 1px 2px,rgba(44,187,99,.25) 0 2px 4px,rgba(44,187,99,.25) 0 4px 8px,rgba(44,187,99,.25) 0 8px 16px,rgba(44,187,99,.25) 0 16px 32px;
  transform: scale(1.05) rotate(-1deg);
}
h2 {
  margin: 0;
  text-align: center;
}
</style>