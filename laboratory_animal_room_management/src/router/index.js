import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login"
import SystemUser from "@/views/SystemUser"
import AnimalTransportPersonnel from "@/views/AnimalTransportPersonnel";
import SystemUserAdd from "@/views/SystemUserAdd";
import SystemUserManagement from "@/views/SystemUserManagement";

Vue.use(VueRouter)

const routes = [
  {
    path: '/systemUser',
    name: '移送用户',
    component: SystemUser
  },
  {
    path: '/animalTransportPersonnel',
    name: '移送员工',
    component: AnimalTransportPersonnel,
    redirect: '/systemUserAdd',
    children:[
      {
        path: '/systemUserAdd',
        name: '添加用户',
        component: SystemUserAdd
      },
      {
        path: '/systemUserManagement',
        name: '用户管理',
        component: SystemUserManagement
      }
    ]
  },
  {
    path: '/login',
    name: '登录',
    component: Login
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
