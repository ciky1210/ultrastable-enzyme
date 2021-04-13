// const basePath = "http://47.111.11.11:10002/";

//线上
const prod = {
    url1:"http://www.ultrastable-enzyme.online:8083/",	
    // url1:"http://23x69v.natappfree.cc/",

}
//本地
const test = {
    url1:"http://www.ultrastable-enzyme.online:8083/",	
    // url1:"http://enzyme.cn1.utools.club/",
}
 //const basePath = "http://172.16.0.188:9998/app/";
// const basePath = "http://172.16.0.105:9998/app/";
//const basePath = "http://172.16.0.145:9998/app/";
const path = process.env.NODE_ENV == 'production' ? prod : test;


export default {
    //请求
    login:{
        login:path.url1 +'login/userLogin', //登陆
        register:path.url1 + 'enzymeAdminUser/register', //注册
        getCurrLoginUser:path.url1 + 'enzymeAdminUser/getCurrLoginUser', //获取信息
    },
    user:{
        getCount:path.url1 + 'enzyme/countEnzymeData', //获取字典数据
        user_search:path.url1+ 'enzymeDict/findDictList', //获取用户信息
        get_info:path.url1 + 'enzyme/findEnzymeList', //获取字典数据
        saveOrUpdateEnzyme:path.url1 + 'enzymeAdminUser/saveOrUpdateEnzyme', //录入和更新数据
        findEnzymeInfo:path.url1 + 'enzyme/findEnzymeInfo', //查询详情
        deleteEnzyme:path.url1 + 'enzymeAdminUser/deleteEnzyme', //删除酶
        findEnzymeByDict:path.url1 + 'enzyme/findEnzymeByDict', //分类查询
        getUserList:path.url1 + 'enzymeAdminUser/getUserList', //管理列表
        lockUser:path.url1 + 'enzymeAdminUser/lockUser', //账户锁定
        getLogList:path.url1 + 'enzymeAdminUser/getLogList', //获取日志列表
        getEnztmeNum:path.url1 + 'enzyme/getEnztmeNum', //获取调用次数
        saveOrUpdateDict:path.url1 + 'enzymeAdminUser/saveOrUpdateDict', //添加更新字典
        deleteDict:path.url1 + 'enzymeAdminUser/deleteDict', //删除字典
        deleteUser:path.url1 + 'enzymeAdminUser/deleteUser', //删除用户
        changePasswod:path.url1 + 'enzymeAdminUser/changePassword', //修改密码
        findEnzymeByIdList:path.url1 + 'enzyme/findEnzymeByIdList',
        enzymeComplexFindToEnzyme:path.url1 + 'enzyme/complexFindToEnzyme', //复合搜索
    }
}
