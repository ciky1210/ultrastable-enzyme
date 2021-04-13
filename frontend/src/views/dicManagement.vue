<template>
    <div class="x-main">
        <!-- <div class="search">
            <span></span>
            <el-input placeholder="请输入你要搜索的~" v-model="allSearch" class="conditionalSearch">
                <el-select v-model="select" class="condition" @change="classification" slot="prepend" placeholder="请选择">
                    <el-option v-for="(item,index) in options" :label="item.label" :key="item.id" :value="item.id"></el-option>
                </el-select>
                <el-button slot="append" @click="searchEngines" icon="el-icon-search"></el-button>
            </el-input>
        </div> -->
        <div class="x-view">
            <aside>
                <!-- <el-input v-model="catalogSearch" class="muluSearch" placeholder="搜索目录~"></el-input> -->
                <!-- 菜单栏 -->
                <el-menu
                    default-active="1"
                    class="cSearch"
                    unique-opened>
                    <el-menu-item index="1" @click.native="getEc">
                        <span slot="title">EC编号</span>
                    </el-menu-item>
                    <el-menu-item index="2" @click.native="getLx">
                        <span slot="title">酶类型</span>
                    </el-menu-item>
                    <el-menu-item index="3" @click.native="getWd">
                        <span slot="title">温度耐受性</span>
                    </el-menu-item>
                    <el-menu-item index="4" @click.native="getSj">
                        <span slot="title">酸碱耐受性</span>
                    </el-menu-item>
                    <el-menu-item index="5" @click.native="getNs">
                        <span slot="title">耐受性分类</span>
                    </el-menu-item>
                </el-menu>
            </aside>

            <div class="sec-right">
                <div class="x-BasicInfo">
                    <span class="x-title">字典信息</span>
                    <el-button style="background:rgba(36, 41, 46, .9); color:#fff" @click="addDictionaries()">新增字典</el-button>
                </div>
                <el-table 
                    :data="tableData.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                    stripe
                    :header-cell-style="{'background':'rgba(36, 41, 46, .8)','color':'#fff'}"
                    style="width: 100%; margin-top:30px"
                    :default-sort = "{prop: 'date', order: 'descending'}"
                    >
                     <el-table-column
                        prop="enzymeDictName"
                        label="名称"
                        align="center"
                        min-width='20%'>
                    </el-table-column>
                    <el-table-column
                        prop="enzymeDictCode"
                        label="EC编号"
                        align="center"
                        min-width='20%'>
                    </el-table-column>
                    <el-table-column
                        prop="enzymeDictType"
                        label="分类"
                        align="center"
                        min-width='20%'>
                    </el-table-column>
                    <el-table-column label="操作" align="center"  min-width='30%'>
                        <template slot-scope="scope">
                            <el-button class="look bg_ff5" @click='updata(scope.$index,scope.row.id)'>更新</el-button>
                            <el-button class="look bg_c10" @click='del(scope.$index,scope.row.id)'>删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <br>
                <p class="mt-5 ml-10"><span class="c1f2">总条数：</span>{{tableData.length}}</p>
                <el-pagination class="fy"
                    layout="prev, pager, next"
                    @current-change="current_change"
                    :total="tableData.length"
                    background
                >
                </el-pagination>
                <!-- <Page :total="100" show-sizer show-elevator/> -->
            </div>

            <el-dialog :title="dicTitle" class="addNew" :visible.sync="addNewDictionaries">
                <el-form label-width="70px" class="demo-ruleForm mt-10">
                    <el-form-item label="名称">
                        <el-col :span="20">
                            <el-input v-model="dicName"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="类别" v-if="dicTitle == '新增字典'">
                        <el-col :span="20">
                            <el-select v-model="newDic" @change="getNewDic" style="width:100%;" placeholder="请选择">
                                <el-option
                                    v-for="item in optionsT"
                                    :key="item.id"
                                    :label="item.label"
                                    :value="item.id">
                                </el-option>
                            </el-select>
                        </el-col>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="saveOrUpdateDict(isSave)">保存</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
                    
        </div>

    </div>
</template>

<script>
export default {
    data(){
        return{
            allSearch:'',
            select:'全部',
            characteristicList:[],
            options:[
                {
                    id:'1',
                    label:'全部',
                    name:'全部'
                },
                {
                    id:'BCBH',
                    label:'EC编号',
                    name:'EC编号'
                },
                {
                    id:'FL',
                    label:'分类',
                    name:'分类'
                },
                {
                    id:'LX',
                    label:'所属途径',
                    name:'所属途径'
                },               
                {
                    id:'WZ',
                    label:'物种',
                    name:'物种'
                },
            ],
            optionsT:[
                {
                    id:'ECBH',
                    label:'EC编号',
                    name:'EC编号'
                },
                {
                    id:'FL',
                    label:'酶分类',
                    name:'酶分类'
                }, 
                {
                    id:'LX',
                    label:'酶类型',
                    name:'酶类型'
                }, 
                {
                    id:'WD',
                    label:'温度耐受性',
                    name:'温度耐受性'
                },
                {
                    id:'SJ',
                    label:'酸碱耐受性',
                    name:'酸碱耐受性'
                },   
                {
                    id:'NSFL',
                    label:'耐受性分类',
                    name:'耐受性分类'
                },            
            ],
            tableData:[],
            addNewDictionaries:false,
            total:0,//默认数据总数
            currentPage: 1,
            pagesize:'10',
            newDic:'',
            dicName:'',
            newDictCode:'',
            dicLaber:'',
            index:1,
            updataId:'',
            isSave:true,
            dicTitle:'新增字典'
        }
    },

    created(){
        this.getInfo()
    },

    methods:{
        getInfo(){ //获取字典
            this.$http({method:'post',url: this.$api.user.user_search},{}).then(res=>{
            //   console.log(res)
              if(res.code == 200){
                  this.characteristicList = res.data
                  if(this.index == 1){
                      this.tableData = res.data.ECBH
                  }else if(this.index == 2){
                      this.tableData = res.data.WD
                  }else if(this.index == 3){
                      this.tableData = res.data.SJ
                  }else{
                      this.tableData = res.data.NSFL
                  }
                //   console.log(this.characteristicList, '11')
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

        classification(id){ //获取特性值
            // console.log(id)
            this.features = id
        },

        addDictionaries(){ //新增字典
            this.dicName = ''
            this.newDic = ''
            this.isSave = true
            this.dicTitle = '新增字典'
            this.addNewDictionaries = true
        },

        getNewDic(id){
            // console.log(id)
            this.newDictCode = id
            let obj = {};
            obj = this.optionsT.find((item)=>{//这里的userList就是上面遍历的数据源
                return item.id === id;//筛选出匹配数据
            });
            // console.log(obj.label);//我这边的name就是对应label的
            this.dicLaber = obj.label
        },
   
        saveOrUpdateDict(){
            if(this.isSave == true){
                this.$Header_http({method:'post',url: this.$api.user.saveOrUpdateDict},{enzymeDictName:this.dicName,enzymeDictType:this.dicLaber,enzymeDictCode:this.newDictCode}).then(res=>{
                    // console.log(res)
                    if(res.code == 200){
                        this.$message({
                            message: '添加成功',
                            type: 'success'
                        });
                        this.addNewDictionaries = false;
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
            }else{
                this.$Header_http({method:'post',url: this.$api.user.saveOrUpdateDict},{id:this.updataId,enzymeDictName:this.dicName,enzymeDictType:this.newDic,enzymeDictCode:this.newDictCode}).then(res=>{
                    // console.log(res)
                    if(res.code == 200){
                        this.$message({
                            message: '更新成功',
                            type: 'success'
                        });
                        this.addNewDictionaries = false;
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
            }

        },

        getEc(){
            this.index = 1
            this.tableData = this.characteristicList.ECBH
            this.currentPage= 1
        },

        getLx(){
            this.index = 2
            this.tableData = this.characteristicList.LX
            this.currentPage= 1
        },

        getWd(){
            this.index = 3
            this.tableData = this.characteristicList.WD
            this.currentPage= 1
        },

        getSj(){
            this.index = 4
            this.tableData = this.characteristicList.SJ
            this.currentPage= 1
        },

        getNs(){
            this.index = 5
            this.tableData = this.characteristicList.NSFL
            this.currentPage= 1
        },

        current_change(currentPage){
            // console.log(currentPage)
            this.currentPage = currentPage
        },

        updata(index,id){
            // console.log(id)
            this.dicTitle = '更新字典'
            this.isSave = false
            this.updataId = id
            this.addNewDictionaries = true
            this.dicName = this.tableData[index].enzymeDictName
            this.newDictCode = this.tableData[index].enzymeDictCode
            this.newDic = this.optionsT[this.index - 1].label
        },

        del(index,id){
            this.$confirm('确认删除此条资料？', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
                this.$Header_http({method:'post',url: this.$api.user.deleteDict},{dictId:id}).then(res=>{
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
    max-width: 1440px;
    margin: 0 auto;
    display: flex;
    padding-bottom: 40px;
    /* background: #fff */
}

.search{
    max-width: 1440px;
    display: flex;
    justify-content:space-between;
    padding: 0 0px 30px 0px;
}

.asideMenu{
    background: #4E93F4;
    color: #fff;
    height: auto;
    margin: 0;
}

.asideMenu div{
    height: 50px;
    line-height: 50px;
}
/* 侧边栏 */
aside {
    min-width: 240px;
    background: #fff;
    height: 100%;
    transition: all .5s;
    overflow: auto;
    font-size: 16px;
}

.sec-right {
    min-width: 78%;
    display: flex;
    flex-direction: column;
    transition: width .5s;
    padding: 15px 10px;
    margin-left:20px;
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