<template>
    <div class="x-main">
        <div class="x-view">
            <div class="sec-right" style="max-width:1440px; margin:0 auto">
                <div class="x-BasicInfo">
                    <span class="x-title">用户列表</span>
                    <el-button style="background:rgba(36, 41, 46, .9); color:#fff" v-if="adminFlag == 'true'" @click.native="dialogFormVisible=true">新增管理员</el-button>
                </div>
                <el-table 
                    :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                    stripe
                    :header-cell-style="{'background':'rgba(36, 41, 46, .8)','color':'#fff'}"
                    style="width: 100%; margin-top:30px"
                    :default-sort = "{prop: 'date', order: 'descending'}"
                    >
                    <el-table-column
                        prop="userName"
                        label="账户名"
                        align="center"
                        min-width='25%'>
                    </el-table-column>
                    <el-table-column
                        prop="nickName"
                        label="用户名"
                        align="center"
                        min-width='25%'>
                    </el-table-column>
                    <el-table-column
                        prop="adminFlag"
                        label="是否管理员"
                        align="center"
                        min-width='25%'>
                    </el-table-column>
                    <el-table-column label="操作" align="center"  min-width='30%'>
                        <template slot-scope="scope">
                            <el-button class="look bg_328" @click='see(scope.$index,scope.row.id)'>查看日志</el-button>
                            <el-button class="look bg_ff5" v-if="scope.row.lockFlag == false && adminFlag == 'true'" @click='lock(scope.$index,1,scope.row.id)'>锁定</el-button>
                            <el-button class="look bg_ff5" v-if="scope.row.lockFlag == true && adminFlag == 'true'" @click='lock(scope.$index,2,scope.row.id)'>解锁</el-button>
                            <el-button class="look bg_ff5" @click='cPwd(scope.$index,scope.row.id)'>修改密码</el-button>
                            <el-button class="look bg_c10" v-if="adminFlag == 'true'" @click='del(scope.$index,scope.row.id)'>删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <br>
                <p class="mt-5 ml-10"><span class="c1f2">总条数：</span>{{tableData.length}}</p>
                <el-pagination class="fy"
                    layout="prev, pager, next"
                    @current-change="current_change"
                    :total="total"
                    background
                >
                </el-pagination>
                <!-- <Page :total="100" show-sizer show-elevator/> -->
            </div>

            <el-dialog title="查看日志详情" :visible.sync="seeInfo">
                <el-table :data="journalData.slice((currentPageT-1)*pagesizeT,currentPageT*pagesizeT)">
                    <el-table-column property="userName" label="操作人" align="center" min-width="25"></el-table-column>
                    <el-table-column property="operationType" label="操作类型" align="center" min-width="25"></el-table-column>
                    <el-table-column property="operationTime" label="操作时间" align="center" min-width="25"></el-table-column>
                    <el-table-column property="operationBody" :show-overflow-tooltip="true" label="操作内容" align="center" min-width="25"></el-table-column>
                </el-table>
                <el-pagination class="fy"
                    layout="prev, pager, next"
                    @current-change="current_changeT"
                    :total="totalT"
                    background
                >
                </el-pagination>
            </el-dialog>

            <!-- 新增管理 -->
            <el-dialog class="addUser-dialog" title="新增管理" :visible.sync="dialogFormVisible">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="账户" prop="userName">
                        <el-input v-model="ruleForm.userName" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="用户名" prop="nickName">
                        <el-input v-model.number="ruleForm.nickName" maxlength="8"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>

            <!-- 修改密码 -->
            <el-dialog class="addUser-dialog" title="修改密码" :visible.sync="dialogFormVisibleT">
                <el-form :model="newruleForm" status-icon ref="newruleForm" label-width="100px" class="demo-ruleForm">
                    <!-- <el-form-item label="旧密码">
                        <el-input v-model="newruleForm.oldPassword" autocomplete="off"></el-input>
                    </el-form-item> -->
                    <el-form-item label="新密码">
                        <el-input type="password" v-model="newruleForm.newPassword" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitFormT('newruleForm')">提交</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        var checkAge = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('请输入用户名'));
            } else if(value.length > 8){
                return callback(new Error('用户名不能超过8个字符'));
            }else{
                callback();
            }
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入账号'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
            callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else {
                callback();
            }
        };
        return{
            tableData: [],
            total:0,//默认数据总数
            totalT:0,
            currentPage: 1,
            currentPageT:1,
            pagesize:'10',
            pagesizeT:'10',
            seeInfo:false,
            journalData:[],
            dialogFormVisible:false,
            dialogFormVisibleT:false,
            ruleForm: {
                userName: '',
                password: '',
                nickName: ''
            },
            rules: {
                userName: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                password: [
                    { validator: validatePass2, trigger: 'blur' }
                ],
                nickName: [
                    { validator: checkAge, trigger: 'blur' }
                ]
            },
            newruleForm: {
                id:'',
                oldPassword: '',
                newPassword: '',
            },
            newuserId:'',
            adminFlag:true
        }
    },

    created(){
        // userId:sessionStorage.getItem('userId'),
        var userId = sessionStorage.getItem('token')
        var adminFlag = sessionStorage.getItem('adminFlag')
        this.adminFlag = adminFlag
        // if(userId){
        //     this.isAdmin = true
        // }
        this.getInfo()
        // this.getAllInfo()
    },

    methods:{
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    // alert('submit!');
                    var registerParams = { userName: this.ruleForm.userName, password: this.ruleForm.password, nickName:this.ruleForm.nickName };
                    // console.log(registerParams)
                    this.$Header_http({method:'post',url: this.$api.login.register},registerParams).then(res=>{
                        // console.log(res)
                          this.logining = false;
                          if(res.code == 200){
                            this.$message({
                                message: '注册成功',
                                type: 'success'
                              });
                              this.dialogFormVisible = false,
                              this.getInfo()
                          }else if(res.code == 102){
                                this.$message({
                                    message: 'token已失效,请重新登录',
                                    type: 'error'
                                });
                                this.$router.push({ path:'/Login'});
                          }else{
                              this.$message({
                                message: res.msg,
                                type: 'error'
                              });
                          }
                    });
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },

        submitFormT(formName){
            // console.log('111')
            var registerParams = { userId: this.newuserId,newPassword:this.newruleForm.newPassword };
            this.$Header_http({method:'post',url: this.$api.user.changePasswod},registerParams).then(res=>{
                console.log(res)
                    this.logining = false;
                    if(res.code == 200){
                        this.$message({
                            message: '修改成功',
                            type: 'success'
                        });
                        this.dialogFormVisibleT = false,
                        this.getInfo()
                    }else if(res.code == 102){
                        this.$message({
                            message: 'token已失效,请重新登录',
                            type: 'error'
                        });
                        this.$router.push({ path:'/Login'});
                    }else{
                        this.$message({
                        message: res.msg,
                        type: 'error'
                        });
                    }
            });

            this.newruleForm = {
                id:'',
                oldPassword: '',
                newPassword: '',
                againPassword: ''
            }
        },

        getInfo(){ //获取管理列表
            this.$Header_http({method:'post',url: this.$api.user.getUserList},{}).then(res=>{
              if(res.code == 200){
                if(res.data.length>1){
                    for(var i = 0; i < res.data.length; i++){
                        if(res.data[i].adminFlag == false){
                            res.data[i].adminFlag = '否'
                        }else{
                            res.data[i].adminFlag = '是'
                        }
                    }
                    this.tableData = res.data
                    this.total = res.data.length
                }else{
                    var arr = []
                    if(res.data.adminFlag == false){
                        res.data.adminFlag = '否'
                    }else{
                        res.data.adminFlag = '是'
                    }
                    arr.push(res.data)
                    this.tableData = arr
                    this.total = arr.length
                }
                
                //   console.log(this.characteristicList)
              }else if(res.code == 102){
                    this.$message({
                        message: 'token已失效,请重新登录',
                        type: 'error'
                    });
                    this.$router.push({ path:'/Login'});
              }else{
                this.$message({
                    message: res.msg,
                    type: 'error'
                });
              }
            });
        },

        
        lock(index,id,item){ //账户锁定
            // console.log(index,id)
            if(id == 1){
                this.$confirm('确认锁定该用户？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$Header_http({method:'post',url: this.$api.user.lockUser},{userId:item}).then(res=>{
                        console.log(res)
                        if(res.code == 200){
                            this.getInfo()
                            //   console.log(this.characteristicList)
                        }else if(res.code == 102){
                            this.$message({
                                message: 'token已失效,请重新登录',
                                type: 'error'
                            });
                            this.$router.push({ path:'/Login'});
                        }else{
                            this.$message({
                                message: res.msg,
                                type: 'error'
                            });
                        }
                    });
                })
            }else{
                this.$confirm('确认解锁该用户？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$Header_http({method:'post',url: this.$api.user.lockUser},{userId:item}).then(res=>{
                        console.log(res)
                        if(res.code == 200){
                            this.getInfo()
                            //   console.log(this.characteristicList)
                        }else if(res.code == 102){
                            this.$message({
                                message: 'token已失效,请重新登录',
                                type: 'error'
                            });
                            this.$router.push({ path:'/Login'});
                        }else{
                            this.$message({
                                message: res.msg,
                                type: 'error'
                            });
                        }
                    });
                })
            }
            
        },

        current_change(currentPage){
            // console.log(currentPage)
            this.currentPage = currentPage
        },

        current_changeT(currentPageT){
            // console.log(currentPage)
            this.currentPageT = currentPageT
        },

        see(index,id){
            this.seeInfo = true
            this.$Header_http({method:'post',url: this.$api.user.getLogList},{userId:id}).then(res=>{
                // console.log(res)
                if(res.code == 200){
                    for(var i = 0; i < res.data.length; i++){
                        res.data[i].operationTime = this.formatDate(res.data[i].operationTime)
                    }
                    this.journalData = res.data
                    this.totalT = res.data.length
                    // console.log(totalT)
                }else if(res.code == 102){
                    this.$message({
                        message: 'token已失效,请重新登录',
                        type: 'error'
                    });
                    this.$router.push({ path:'/Login'});
                }else{
                    this.$message({
                        message: res.msg,
                        type: 'error'
                    });
                }
            });
        },

        formatDate: function (value) {
            let date = new Date(value);
            let y = date.getFullYear();
            let MM = date.getMonth() + 1;
            MM = MM < 10 ? ('0' + MM) : MM;
            let d = date.getDate();
            d = d < 10 ? ('0' + d) : d;
            let h = date.getHours();
            h = h < 10 ? ('0' + h) : h;
            let m = date.getMinutes();
            m = m < 10 ? ('0' + m) : m;
            let s = date.getSeconds();
            s = s < 10 ? ('0' + s) : s;
            return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
        },

        cPwd(index,id){
            this.dialogFormVisibleT = true
            this.newuserId = id
        },

        del(index,id){
            this.$confirm('确认删除此条资料？', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
                this.$Header_http({method:'post',url: this.$api.user.deleteUser},{userId:id}).then(res=>{
                    // console.log(res)
                    if(res.code == 200){
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getInfo()
                        //   console.log(this.characteristicList)
                    }else if(res.code == 102){
                        this.$message({
                            message: 'token已失效,请重新登录',
                            type: 'error'
                        });
                        this.$router.push({ path:'/Login'});
                    }else{
                        this.$message({
                            message: res.msg,
                            type: 'error'
                        });
                    }
                });
            })
        },
        
    }

}
</script>

<style scoped>
.x-view{
    padding-bottom: 40px;
}
.sec-right .x-BasicInfo{
    display: flex;
    justify-content:space-between
}
.fy{
	text-align:right;
	margin-top:30px;
}
</style>