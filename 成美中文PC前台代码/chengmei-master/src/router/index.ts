import Vue from 'vue'
import VueRouter, { RouteConfig } from 'vue-router'
import main from '../main.vue'


const originalPush:any = VueRouter.prototype.push
   VueRouter.prototype.push = function push(location:any) {
     return originalPush.call(this, location).catch((err:any)=> err);
}
Vue.use(VueRouter)

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/home/home.vue')
  },
  {
    path: '',
    name: '',
    // component: () => import('../views/newsletter/infoShow.vue')
    component: main,
    children: [
      {
        path: '/newsletter',
        name: 'newsletter',
        component: () => import('../views/newsletter/newsletter.vue')
      },
      {
        path: '/supportus',
        name: 'supportus',
        component: () => import('../views/SupportUs/SupportUs.vue')
      },
      {
        path: '/partner',
        name: 'partner',
        component: () => import('../views/partner/partner.vue')
      },
      {
        path: '/team',
        name: 'Team',
        component: () => import('../views/team/team.vue')
      },
      {
        path: '/info',
        name: 'Info',
        component: () => import('../views/info/info.vue')
      },
      {
        path: '/more',
        name: 'More',
        component: () => import('../views/show/components/more.vue')
      },
      {
        path: '/show',
        name: 'Show',
        component: () => import('../views/show/show.vue')
      },
      {
        path: '/aboutus',
        name: 'aboutus',
        component: () => import('../views/aboutUs/aboutUs.vue')
      }
    ]
  },

]
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
