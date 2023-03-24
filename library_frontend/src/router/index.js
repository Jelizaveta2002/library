import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import BookView from '../views/BookView.vue';
import CheckoutView from "@/views/CheckoutView";
import AddBook from "@/views/AddBook";
import LateCheckouts from "@/views/LateCheckouts";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/late',
    name: 'late',
    component: LateCheckouts
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
