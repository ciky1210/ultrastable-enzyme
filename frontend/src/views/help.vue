<template>
    <div class="x-main">
        <p class="text-center f24">非细胞合成体系超稳酶数据库使用说明</p>
        <p class="mt-20 f18 textIndent">非细胞合成体系超稳酶数据库分为五个板块：基本信息、突变信息、反应信息、动力学评价和参考文献。</p>
        <p class="mt-20 f18 textIndent">1、基本信息板块包括EC编号、分类、酶类型、所属物种，以及突变类型。</p>
        <p class="mt-20 f18 textIndent">2、突变信息板块包括NCBI中该序列的gi号、氨基酸数、分子量（二烷基硫酸钠-聚丙烯酰胺凝胶电泳的数据或理论分子量，为单体的数据）、突变前后的位点和序列；</p>
        <p class="mt-20 f18 textIndent">3、反应信息板块包括反应物、产物、所属途径、pH范围、最适pH、酸碱耐受性、温度范围、最适温度、Tm值、半衰期、其他耐受物和耐受性分类，其中pH范围和温度范围取自酶活15%--20%对应的pH值或温度值。</p>
        <p class="mt-20 f18 textIndent">4、动力学评价板块包括Vmax评价、Km评价、Kcat评价、Kcat/Km评价和比活评价，由于酶会有多种底物，如果文献有做多种底物的动力学实验，会在数据后面注明底物；如果在不同条件下进行的动力学实验结果差异较大，也会在数据后面简单注明实验条件。</p>
        <p class="mt-20 f18 textIndent">5、参考文献板块显示该数据来源的文献，附上超链接，点击可查看文献原文。</p>
        <p class="mt-20 f18 textIndent">*如果缺少某个数据，则显示为“Nan”。</p>
        <p class="mt-40 f18 textIndent font_bold">Q1、如何使用查询功能？</p>
        <p class="mt-10 f18 textIndent">在左侧的导航中可以按EC编号、温度耐受性、酸碱耐受性和耐受性分类查询。点击“查看全部资料”，可以查看酶的全部信息。</p>
        <div class="text-center">
            <img src="../assets/img/help1.png" style="width:100%;height:100%"></img>
        </div>
        <!-- <p class="f18 textIndent">文本搜索，在搜索框中输入该酶的信息，如名称、EC编号、物种等。</p> -->
        <p class="mt-20 f18 textIndent font_bold">Q2、如何使用搜索功能？</p>
        <p class="mt-10 f18 textIndent">右侧下拉分类选择搜索条件进行搜索，在搜索框中输入该酶的信息，如酶分类、名称名种等，通过“+”可以进行复合搜索。文本搜索对大小写敏感。</p>
        <div class="text-center">
            <img src="../assets/img/help2.png" style="width:600px;height:100%"></img>
        </div>
        <div class="text-center">
            <img src="../assets/img/help3.png" style="width:600px;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">搜索结果示例</p>
        <div class="text-center">
            <img src="../assets/img/help4.png" style="width:90%;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">点击“查看全部资料”可以查看该酶的全部信息</p>
        <div class="text-center">
            <img src="../assets/img/help5.png" style="width:700px;height:100%"></img>
        </div>
        <p class="mt-20 f18 textIndent font_bold">Q3、什么是管理员权限？如何获得管理员权限。</p>
        <p class="mt-10 f18 textIndent">管理员有添加、更新和删除数据的权限，并且能普通用户账号进行操作。点击首页登录，输入用户名和密码登录成功后，即可修改数据库，或进行更改用户操作。</p>
        <p class="mt-10 f18 textIndent">目前管理员权限暂不对外开放，普通用户账号可联系管理员取得。普通用户也可添加、更新和删除数据。</p>
        <p class="mt-20 f18 textIndent font_bold">Q4、如何添加新数据、更新数据和删除数据？</p>
        <p class="mt-10 f18 textIndent">用户登录成功后，进入数据库，点击右上角的“添加资料”，即可添加单条新数据。 </p>
        <div class="text-center">
            <img src="../assets/img/help6.png" style="width:600px;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">添加数据必填的项目有：名称、EC编号、分类、类型、物种、突变类型和序列，其他为选填项目。 </p>
        <div class="text-center">
            <img src="../assets/img/help7.png" style="width:700px;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">为了统一分类，开设了“字典管理”的功能。如果在添加数据时，发现某些特征没有需要的选项，可以使用“新增字典”功能，选择要添加的类别，比如“EC编号”，然后输入名称，并保存，即可新增一个“EC编号”选项，在下一次添加同类型酶的数据的时候，可以直接选择新选项，减少重复输入。</p>
        <div class="text-center">
            <img src="../assets/img/help8.png" style="width:90%;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">在字典管理中，可对已存在的字典更新或删除。 </p>
        <p class="mt-10 f18 textIndent">除了在字典管理中新增字典，也可在添加资料的界面中新增字典。 </p>
        <div class="text-center">
            <img src="../assets/img/help9.png" style="width:600px;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">增加多条数据可使用Excel导入功能，目前支持的格式有.xls、.xlxs。</p>
        <div class="text-center">
            <img src="../assets/img/help10.png" style="width:70%;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">导入完毕后会提示导入成功的条数和导入失败的条数，也可在自己账号中点击查看日志查看具体导入情况。</p>
        <div class="text-center">
            <img src="../assets/img/help11.png" style="width:90%;height:100%"></img>
        </div>
        <div class="text-center">
            <img src="../assets/img/help12.png" style="width:90%;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">点击“删除”，可进行删除数据操作。</p>
        <div class="text-center">
            <img src="../assets/img/help13.png" style="width:90%;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">*导入说明：</p>
	    <div class="f18 flex ml-60">使用一键导入功能前请先下载<a class="red" target="_blank" :href='"http://www.ultrastable-enzyme.online:8083/enzyme/File/download"'>“超稳酶数据库导入文档”</a>，按照表中的格式填写数据后再导入。</div>
        <div class="text-center mt-10">
            <img src="../assets/img/help100.png" style="width:40%;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">点击“更新”，可对已有的数据进行更新。</p>
        <div class="text-center">
            <img src="../assets/img/help14.png" style="width:90%;height:100%"></img>
        </div>
        <div class="text-center">
            <img src="../assets/img/help15.png" style="width:70%;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">点击到处Excel可以到处数据库中所有数据。</p>
        <p class="mt-20 f18 textIndent font_bold">Q4、合并查看和复制序列</p>
        <p class="mt-10 f18 textIndent">为方便科研人员比对不同酶之间的特性，开发合并查看功能。用户勾选数据库中数据（不超过3条），即可在同一页面查看几个酶的全部信息。</p>
        <div class="text-center">
            <img src="../assets/img/help16.png" style="width:90%;height:100%"></img>
        </div>
        <p class="mt-10 f18 textIndent">勾选不同的酶（不限数量），按“复制序列”即可一键复制其中的序列，并以fasta的格式保存在粘贴板中，用户可以直接粘贴序列，利用左侧工具栏对酶进行进一步分析。</p>
        <div class="text-center">
            <img src="../assets/img/help17.png" style="width:70%;height:100%"></img>
        </div>
        <div class="text-center">
            <img src="../assets/img/help18.png" style="width:70%;height:100%"></img>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{}
    },

    methods:{
        getUp(){
            let a = document.createElement('a')
            a.href =""
            a.click();
        }
    }
}
</script>

<style scoped>
.x-main{padding-bottom: 40px; max-width: 1440px; margin: 0 auto}
.flex{display: flex; align-content: center;}
.red{color: red; cursor: pointer;}
</style>