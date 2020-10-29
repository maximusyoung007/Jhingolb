import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'hash',
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
      redirect: "/articleList",
      children: [
        {
          path: '/editArticle',
          name: 'editArticle',
          component: () => import("@/views/management/manageArticle/editArticle")
        },
        {
          path: '/manageTags',
          name: 'manageTags',
          component: () => import("@/views/management/manageTags/manageTags")
        },
        {
          path: '/articleList',
          name: 'articleList',
          component: () => import("@/views/management/manageArticle/articleList")
        },
        {
          path: '/addArticle',
          name: 'addArticle',
          component: () => import("@/views/management/manageArticle/addArticle")
        },
        {
          path: '/addArticleResult',
          name: 'addArticleResult',
          component: () => import("@/views/management/manageArticle/addArticleResult")
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
          path: '/category',
          name: '/category',
          component: () => import("@/views/index/category"),
          meta: {
            requireAuth:true
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
        },
        {
          path: '/articleDetail',
          name: 'articleDetail',
          component: () => import("@/views/index/articleDetail")
        }
      ]
    },
  ]
})
