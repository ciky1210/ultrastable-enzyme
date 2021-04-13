// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import { http, Header_http, fileHttp, http_json,http_get,Header_http_1} from '../lib/request'
import api from '@/api/index'
import routes from './routes'

import '@/assets/css/common.css'//公共样式

Vue.use(ElementUI)
Vue.use(VueRouter)

Vue.prototype.$http = http //继承axios
Vue.prototype.$Header_http = Header_http
Vue.prototype.$http_get =http_get
Vue.prototype.$axios = axios //继承axios
Vue.prototype.$fileHttp = fileHttp //继承上传图片
Vue.prototype.$http_json  = http_json
Vue.prototype.$Header_http_1  = Header_http_1
Vue.prototype.$api  = api

Vue.config.productionTip = false


const router = new VueRouter({
  routes
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
