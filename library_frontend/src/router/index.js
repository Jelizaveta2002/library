import { createRouter, createWebHistory } from 'vue-router'
import BookView from '../views/BookView.vue';
import HomeView from '../views/HomeView.vue'
import CheckoutView from "@/views/CheckoutView";
import AddBook from "@/views/AddBook";
import LateCheckouts from "@/views/LateCheckouts";

const routes = [
  {
    path: '/late',
    name: 'late',
    component: LateCheckouts
  },
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/add',
    name: 'AddBook',
    component: AddBook
  },
  {
    path: '/book/:id',
    name: 'BookView',
    component: BookView,
    props: true,
  },
  {
    path: '/checkout/:id',
    name: 'CheckOutView',
    component: CheckoutView,
    props: true,
  },
  {
    path: '/search',
    name: 'search',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/SearchBook.vue')
  },
  {
    path: '/favourite',
    name: 'favourite',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/FavouriteBooks.vue')
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/status',
    name: 'status',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/StatusView.vue')
  },
  {
    path: '/checkouts',
    name: 'checkouts',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/CheckoutsView.vue')
  },
  {
    path: '/closedCheckouts',
    name: 'closedCheckouts',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/ClosedCheckouts.vue')
  },
  {
    path: '/options',
    name: 'CheckoutOptions',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/CheckoutOptions.vue')
  },
  {
    path: '/books',
    name: 'books',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/BookView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
