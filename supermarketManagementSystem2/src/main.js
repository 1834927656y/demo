import Vue from 'vue'
import App from './App.vue'
import router from './router'
import TinyVue from '@opentiny/vue'

import SideNavigationBar from './components/SideNavigationBar.vue'
import Hat from './components/Hat.vue'
import DataEntry from './components/DataEntry.vue'

Vue.config.productionTip = false
Vue.use(TinyVue)
Vue.component('SideNavigationBar', SideNavigationBar)
Vue.component('Hat', Hat)
Vue.component('DataEntry', DataEntry)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
