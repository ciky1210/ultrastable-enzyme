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
        
        <p class="mt-20 f18 textIndent">超稳酶是指一类在极端条件下依然保持良好活性的酶，除了在自然界中筛选出来的嗜热酶、嗜冷酶、嗜酸酶、嗜碱酶等，还包括在实验室通过突变改造产生的耐热酶、耐低温酶、耐酸酶、耐碱酶、耐蛋白酶的酶等，超稳酶在科学研究和生产应用上都具有巨大的潜力。</p>
        <p class="f18 textIndent">非细胞合成是通过设计人工体外途径实现目标产物的合成，因其开放性和可控性，给合成操作提供了极大的自由度和可控度。非细胞合成体系超稳酶数据库收录了微生物体内途径中酶及其突变体的详细信息，包括序列、反应条件、动力学评价等，给研究人员提供一个较全面的酶数据库来设计合成途径。</p>
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