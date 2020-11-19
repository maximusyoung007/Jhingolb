import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

//重复跳转到相同的路由
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this,location).catch(err => err);
}


export default new Router({
  mode: 'history',
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
    //meta: { requiredAuth:true}表示是否访问这个页面是否需要登录
    {
      path: '/management',
      name: '/management',
      component: () => import("@/views/management/management"),
      meta: {
        requireAuth: true
      },
      redirect: "/articleList",
      children: [
        {
          path: '/manageTags',
          name: 'manageTags',
          component: () => import("@/views/management/manageTags/manageTags"),
          meta: {
            requireAuth: true
          },
        },
        {
          path: '/articleList',
          name: 'articleList',
          component: () => import("@/views/management/manageArticle/articleList"),
          meta: {
            requireAuth: true
          },
        },
        {
          path: '/addArticleResult',
          name: 'addArticleResult',
          component: () => import("@/views/management/manageArticle/addArticleResult"),
          meta: {
            requireAuth: true
          },
        }
      ]
    },
    {
      path: '/addArticle',
      name: 'addArticle',
      component: () => import("@/views/management/manageArticle/addArticle"),
      meta: {
        requireAuth: true
      },
    },
    {
      path: '/previewArticle',
      name: 'previewArticle',
      component: () => import("@/views/management/manageArticle/previewArticle"),
      meta: {
        requireAuth: true
      }
    },
    {
      path: '/editArticle',
      name: 'editArticle',
      component: () => import("@/views/management/manageArticle/editArticle"),
      meta: {
        requireAuth: true
      },
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
        },
        {
          path: '/article/:type',
          name: 'article',
          component: () => import("@/views/index/article"),
        },
        {
          path: '/category',
          name: '/category',
          component: () => import("@/views/index/category"),
        },
        {
          path: '/tags',
          name: '/tags',
          component: () => import("@/views/index/tags"),
        },
        {
          path: '/about',
          name: '/about',
          component: () => import("@/views/index/about"),
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
