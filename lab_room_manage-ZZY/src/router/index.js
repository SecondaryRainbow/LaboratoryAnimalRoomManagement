import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from "@/views/Login"
import SystemAdmin from "@/views/SystemAdmin";

import StaffAdd from "@/views/StaffAdd";
import StaffManage from "@/views/StaffManage";
import StaffEdit from "@/views/StaffEdit";

import UserAdd from "@/views/UserAdd";
import UserManage from "@/views/UserManage";
import UserEdit from "@/views/UserEdit";

import GroupAdd from "@/views/GroupAdd";
import GroupManage from "@/views/GroupManage";
import GroupEdit from "@/views/GroupEdit";

import RoomAdd from "@/views/RoomAdd";
import RoomManage from "@/views/RoomManage";
import RoomEdit from "@/views/RoomEdit";

import AnimalAdd from "@/views/AnimalAdd";
import AnimalManage from "@/views/AnimalManage";
import AnimalEdit from "@/views/AnimalEdit";

import RecordAdd from "@/views/RecordAdd";
import RecordManage from "@/views/RecordManage";
import SystemUser from "@/views/SystemUser";
import AnimalAddUser from "@/views/AnimalAddUser";
import AnimalManageUser from "@/views/AnimalManageUser";
import AnimalEditUser from "@/views/AnimalEditUser";
import GroupAddUser from "@/views/GroupAddUser";
import GroupManageUser from "@/views/GroupManageUser";
import GroupEditUser from "@/views/GroupEditUser";
import RecordAddUser from "@/views/RecordAddUser";
import RecordManageUser from "@/views/RecordManageUser";


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: '登录',
        component: Login
    },
    {
        path: '/systemAdmin',
        name: '管理员页面',
        component: SystemAdmin,
        redirect: '/staffManage',
        children: [
            {
                path: '/staffAdd',
                name: '添加员工',
                component: StaffAdd
            },
            {
                path: '/staffManage',
                name: '员工管理',
                component: StaffManage
            },
            {
                path: '/staffEdit',
                name: '编辑员工信息',
                component: StaffEdit
            },
            {
                path: '/userAdd',
                name: '添加用户',
                component: UserAdd
            },
            {
                path: '/userManage',
                name: '用户管理',
                component: UserManage
            },
            {
                path: '/userEdit',
                name: '编辑用户信息',
                component: UserEdit
            },
            {
                path: '/animalAdd',
                name: '添加动物',
                component: AnimalAdd
            },
            {
                path: '/animalManage',
                name: '动物管理',
                component: AnimalManage
            },
            {
                path: '/animalEdit',
                name: '编辑动物信息',
                component: AnimalEdit
            },
            {
                path: '/groupAdd',
                name: '添加动物组',
                component: GroupAdd
            },
            {
                path: '/groupManage',
                name: '动物组管理',
                component: GroupManage
            },
            {
                path: '/groupEdit',
                name: '编辑动物组信息',
                component: GroupEdit
            },
            {
                path: '/roomAdd',
                name: '添加房间',
                component: RoomAdd
            },
            {
                path: '/roomManage',
                name: '房间管理',
                component: RoomManage
            },
            {
                path: '/roomEdit',
                name: '编辑房间信息',
                component: RoomEdit
            },
            {
                path: '/recordAdd',
                name: '添加记录',
                component: RecordAdd
            },
            {
                path: '/recordManage',
                name: '记录查看',
                component: RecordManage
            },
        ]
    },
    {
        path: '/systemUser',
        name: '用户页面',
        component: SystemUser,
        redirect: '/animalManage$User',
        children: [
            {
                path: '/animalAdd$User',
                name: '添加动物',
                component: AnimalAddUser
            },
            {
                path: '/animalManage$User',
                name: '动物管理',
                component: AnimalManageUser
            },
            {
                path: '/animalEdit$User',
                name: '编辑动物信息',
                component: AnimalEditUser
            },
            {
                path: '/groupAdd$User',
                name: '添加动物组',
                component: GroupAddUser
            },
            {
                path: '/groupManage$User',
                name: '动物组管理',
                component: GroupManageUser
            },
            {
                path: '/groupEdit$User',
                name: '编辑动物组信息',
                component: GroupEditUser
            },
            {
                path: '/recordAdd$User',
                name: '添加记录',
                component: RecordAddUser
            },
            {
                path: '/recordManage$User',
                name: '记录查看',
                component: RecordManageUser
            },
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
