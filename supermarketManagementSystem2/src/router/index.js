import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/login.vue'
import Home from '@/views/home.vue'
import AdministratorAccount from '@/views/AdministratorAccount.vue'
import UserAccount from '@/views/UserAccount.vue'
import SEEntryVue from '@/components/SEEntry.vue'
Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    { path: '/', name: 'login', component: Login },
    {
      path: '/admin/home',
      name: 'home',
      component: Home,
      children: [
        { path: '/admin', name: 'admin', component: AdministratorAccount },
        { path: '/user', name: 'user', component: UserAccount },
        { path: '/SEEntry', name: 'SEEntry', component: SEEntryVue }
      ]
    }

  ]
})

export default router
