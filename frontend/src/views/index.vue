<template>
    <div class="index-vue">
        <div class="header-img">
            <img src="../assets/img/logo.png" style="width:100%; height:100%">
        </div>
        <!-- 导航栏 -->
        <div class="header">
            <div class="x-main">
                <!-- <el-col :span="10" class="logo">
                    <span>logo</span>
                </el-col> -->
                <el-col :span="14">
                    <el-menu :default-active="$route.path" class="x-navigation" router mode="horizontal" background-color="#67ABD6">
                        <template v-for="(item,index) in $router.options.routes" v-if="!item.hidden && !isAdmin">
                            <el-menu-item v-for="child in item.children" :index="child.path" :key="child.path" v-if="!child.isAdmin">{{child.name}}</el-menu-item>
                        </template>
                        <template v-for="(item,index) in $router.options.routes" v-if="!item.hidden && isAdmin">
                            <el-menu-item v-for="child in item.children" :index="child.path" :key="child.path">{{child.name}}</el-menu-item>
                        </template>
                    </el-menu>
                </el-col>
                <el-col :span="2" class="userinfo">
                        <router-link to="login" class="cfff" v-if="isAdmin == false">登录</router-link>
                        <el-dropdown trigger="hover" v-else style="width:100%">
                            <span class="el-dropdown-link userinfo-inner">已登录</span>
                            <el-dropdown-menu slot="dropdown">
                                <!-- <el-dropdown-item @click.native="dialogFormVisible=true">新增管理员</el-dropdown-item> -->
                                <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                </el-col>
            </div>
        </div>

        <div class="mask"></div>

        <!-- 页面主体 -->
        <div class="main-content">
            <div class="view-c">
                <transition name="fade" mode="out-in">
                    <router-view></router-view>
                </transition>
            </div>
        </div>

        <!-- 页面底部 -->
        <div class="footer">
            <div class="f-index">
                <div class="c0a0" style="display:flex;height:40px;line-height:60px">技术支持：<a target="_blank" class="c191" href="http://www.most.gov.cn/">中华人民共和国科学技术部</a>&nbsp;|&nbsp;<a target="_blank" class="c191" href="http://www2.scut.edu.cn/biology/">华南理工大学生物科学与工程学院</a>&nbsp;|&nbsp;<a target="_blank" class="c191" href="https://www.scut.edu.cn/new/">华南理工大学</a></div>
                <div class="c0a0" style="display:flex;height:20px;line-height:20px">
                    <a target="_blank" class="c191" href="https://www.ncbi.nlm.nih.gov/">NCBI</a>&nbsp;|&nbsp;
                    <a target="_blank" class="c191" href="https://www.rcsb.org/">PDB</a>&nbsp;|&nbsp;
                    <a target="_blank" class="c191" href="https://www.brenda-enzymes.org/index.php">Brenda</a>&nbsp;|&nbsp;
                    <a target="_blank" class="c191" href="https://www.genome.jp/kegg/">KEGG</a>
                </div>
                <div style="display:flex;line-height:20px">
                    联系我们：spzheng@scut.edu.cn | 广州市番禺区广州大学城 | 邮编:510006
                </div>
                <div style="display:flex;height:40px;line-height:20px">
                    备案号：粤ICP备19137776号 | 信息产业部备案管理系统网址<a target="_blank" class="c191" href="http://www.beian.miit.gov.cn/state/outPortal/loginPortal.action">http://www.beian.miit.gov.cn/state/outPortal/loginPortal.action</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            isAdmin:false,
            isShowRouter: true,
            showLoading:false,
            openMenus: [], // 要打开的菜单名字 name属性
            menuCache: [], // 缓存已经打开的菜单
            isShowAsideTitle: true, // 是否展示侧边栏内容
            sysUserName:'',        
        }
    },
    created(){
        var userId = sessionStorage.getItem('token')
        if(userId){
            this.isAdmin = true
            this.sysUserName = sessionStorage.getItem('sysUserName').replace(/["]/g,"")
        }
    },
    methods: {
        //退出登录
        logout: function () {
            var _this = this;
            this.$confirm('确认退出吗?', '提示', {
                //type: 'warning'
            }).then(() => {
                sessionStorage.removeItem('sysUserName');
                sessionStorage.removeItem('token');
                this.isAdmin = false
                this.$router.push({ path:'/'});
            }).catch(() => {

            });
        },
    }
}
</script>

<style scoped>
.index-vue{
    position: absolute;
    top: 0px;
    bottom: 0px;
    width: 100%;
    /* background:rgb(233,233,233); */
    overflow:auto;
}
.index-vue .header-img{
    height: 200px;
    line-height: 200px;
}
.index-vue .header{
    height: 60px;
    line-height: 60px;
    /* background: #b9e1fa; */
    background: #67ABD6;
    color:#fff;
    /* margin-top: 30px; */
}
.index-vue .header .x-main{
    overflow: hidden;
    max-width:1440px;
    margin: 0 auto;
    padding: 0 16px;
}
.index-vue .header .x-main .logo{
    width: 180px;
}

.index-vue .header .x-main ul li{ 
    float: left;
    font-size: 18px;
    width: 100px;
}
.index-vue .header .x-main .userinfo{
    text-align: center;
    float: right;
}

.index-vue .main-content {
    overflow: auto;
    padding: 0 40px;
}

.userinfo-inner {
    cursor: pointer;
    color:#fff;
}

.view-c {
    height: auto;
}

.pointer {
    cursor: pointer;
}
/* loading */
.loading-c {
    top: 0;
    left: 0;
    width: 100%;
    height:auto;
    position: absolute;
    background: rgba(255,255,255,.5);
    display: flex;
    align-items: center;
    justify-content: center;
}

.mask {
    height: 15px;
    width: 100%;
    top: 85px;
    z-index: 10;
}

.footer{
    /* position: fixed; */
    bottom: 0;
    width: 100%;
    height: 120px;
    line-height: 120px;
    background: #92c8ed
}
.footer .f-index{
    overflow: hidden;
    max-width: 1440px;
    margin: 0 auto;
    padding: 0 16px;
    display: flex;
    flex-direction:column;
    justify-content:center
}
</style>