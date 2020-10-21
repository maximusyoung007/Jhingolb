import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect:'/login'
    },
    {
      path: '/login',
      name: 'login',
      component: () => import("@/views/login/Login")
    },
    {
      path: '/management',
      name: '/management',
      component: () => import("@/views/management/management"),
      redirect: "/editArticle",
      children: [
        {
          path: '/editArticle',
          name: 'editArticle',
          component: () => import("@/views/management/editArticle")
        },
        {
          path: '/manageTags',
          name: 'manageTags',
          component: () => import("@/views/management/manageTags")
        }
      ]
    },
    {
      path: '/homePage',
      name: 'homePage',
      component: () => import("@/views/home/homePage"),
      redirect: '/index',
      children: [
        {
          path: '/index',
          name: 'index',
          component: () => import("@/views/index/index"),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/article',
          name: '/article',
          component: () => import("@/views/index/article"),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/tags',
          name: '/tags',
          component: () => import("@/views/index/tags"),
          meta: {
            requireAuth: true
          }
        },
        {
          path: '/about',
          name: '/about',
          component: () => import("@/views/index/about"),
          meta: {
            requireAuth: true
          }
        }
      ]
    },
  ]
})
