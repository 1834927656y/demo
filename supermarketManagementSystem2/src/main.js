import Vue from 'vue'
import App from './App.vue'
import router from './router'
import TinyVue from '@opentiny/vue'

import SideNavigationBar from './components/SideNavigationBar.vue'
import Hat from './components/Hat.vue'
import DataEntry from './components/DataEntry.vue'
import UserDataEntry from './components/UserDataEntry.vue'
import SEEntry from './components/SEEntry.vue'

Vue.config.productionTip = false
Vue.use(TinyVue)
Vue.component('SideNavigationBar', SideNavigationBar)
Vue.component('Hat', Hat)
Vue.component('DataEntry', DataEntry)
Vue.component('UserDataEntry', UserDataEntry)
Vue.component('SEEntry', SEEntry)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
