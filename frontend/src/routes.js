import Login from './views/Login.vue'
import index from './views/index.vue'
import Home from './views/home.vue'
import dataBase from './views/dataBase.vue'
import help from './views/help.vue'
import dicManagement from './views/dicManagement.vue'
import userManagement from './views/userManagement.vue'


let routes = [
    {
        path: '/',
        component: index,
        name: '',
        iconCls: '',
        children: [
            { path: '/', component: Home, name: '首页' },
            { path: '/dataBase', component: dataBase, name: '数据库' },
            { path: '/dicManagement', component: dicManagement, name: '字典管理', isAdmin: true },
            { path: '/userManagement', component: userManagement, name: '用户管理', isAdmin: true },
            { path: '/help', component: help, name: '帮助' },
        ]
    },
    {
        path: '/Login',
        component: Login,
        name: '',
        hidden: true
    },
    // {
    //     path: '/404',
    //     component: NotFound,
    //     name: '',
    //     hidden: true
    // },
    // {
    //     path: '/',
    //     component: Home,
    //     name: '',
    //     iconCls: '',
    //     leaf: true,//只有一个节点
    //     children: [
    //         { path: '/workTable', component: workTable, name: '工作台' },
    //     ]
    // },
    // {
    //     path: '/',
    //     component: Home,
    //     name: '管理中心',
    //     iconCls: '',//图标样式class
    //     meta:{
    //         roles:'isUser'
    //     },
    //     children: [
    //         { path: '/user', component: user, name: '用户列表' },
    //         { path: '/userMoney', component: userMoney, name: '账户管理' },
    //         { path: '/userMoneyManagement', component: userMoneyManagement, name: '用户消费记录', meta:{roles:'isUser'} },
    //         { path: '/interfaceDetails', component: interfaceDetails, name: '接口明细', meta:{roles:'isAdmin'}, leaf:'true' },
    //     ]
    // },
    // {
    //     path: '/',
    //     component: Home,
    //     name: '接口管理',
    //     iconCls: '',
    //     children: [
    //         { path: '/userWind', component: userWind, name: '用户风控模型管理'},
    //         { path: '/userUseApi', component: userUseApi, name: '用户调用接口记录' },
    //         { path: '/riskManagement', component: riskManagement, name: '风控接口管理'},
    //     ]
    // },
    // {
    //     path: '/',
    //     component: Home,
    //     name: 'Charts',
    //     iconCls: '',
    //     children: [
    //         { path: '/echarts', component: echarts, name: 'echarts' }
    //     ]
    // },
    // {
    //     path: '*',
    //     hidden: true,
    //     redirect: { path: '/404' }
    // }
];

export default routes;