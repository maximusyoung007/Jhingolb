import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login/Login'
import homePage from '@/views/home/homePage'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/homePage',
      name: 'homePage',
      component: homePage
    }
  ]
})
