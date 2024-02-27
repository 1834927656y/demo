import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/login.vue'
import Home from '@/views/home.vue'
import AdministratorAccount from '@/views/AdministratorAccount.vue'
Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path: '/', name: 'login', component: Login },
    {
      path: '/home',
      name: 'home',
      component: Home,
      children: [
        { path: '/admin', name: 'admin', component: AdministratorAccount }
      ]
    }

  ]
})

export default router
