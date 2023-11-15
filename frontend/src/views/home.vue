<template>
    <div class="x-main">
        <div class="flex">
            <div class="item bg_c4d">
                <p class="text-center f20 mt-10">酶数据总条数</p>
                <p class="text-center f36 font_bold">{{totalCount}}</p>
            </div>
            <div class="item bg_92c">
                <p class="text-center f20 mt-10">酶数据总类型</p>
                <p class="text-center f36 font_bold">{{typeCount}}</p>
            </div>
            <div class="item bg_67a">
                <p class="text-center f20 mt-10">酶数据物种条数</p>
                <p class="text-center f36 font_bold">{{speciesCount}}</p>
            </div>
        </div>
        
        <p class="mt-20 f18 textIndent">工业酶是指在工业生产中用于催化、加速反应的酶类。工业酶可以在常温常压下加速化学反应，大大缩短反应时间和降低能耗，升级化工过程的绿色生产水平。工业酶已广泛应用于食品工业、医药、化工、纺织、环保和能源等方向。</p>
        <p class="f18 textIndent">前沿生物技术的发展大大加快了工业酶的研究，海量酶分子数据+AI 技术为酶分子设计与改造提供了新的解决方案，通过改变酶的结构来改善其性质与功能，极大地扩展了工业酶的应用领域。</p>
    </div>
</template>

<script>
export default {
    data(){
        return{
            typeCount:'',
            totalCount:'',
            speciesCount:''
        }
    },

    created(){
        this.getEnztmeNum()
    },

    methods:{
        getEnztmeNum(){
            this.$http({method:'post',url: this.$api.user.getCount},{}).then(res=>{
            //   console.log(res)
              if(res.code == 200){
                this.totalCount = res.data.totalCount
                this.typeCount = res.data.typeCount
                this.speciesCount = res.data.speciesCount
              }else{
                  this.$message({
                    message: res.msg,
                    type: 'error'
                  });
              }
            });
        }
    }
}

</script>

<style scoped>
.x-main{padding-bottom: 40px; max-width: 1440px; margin: 0 auto}
.flex{align-items: center; display: flex; justify-content:center}
.item{margin: 0 30px; width: 300px; height: 100px; border-radius: 10px;}
</style>