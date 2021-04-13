<template>
    <div class="x-main">
        <div class="search">
            <el-input placeholder="请输入你要搜索的~" v-model="searchParams.typeValue[0]" class="conditionalSearch">
                <el-select v-model="searchParams.type[0]" class="condition" @change="classification" slot="prepend" placeholder="请选择">
                    <el-option v-for="(item,index) in options" :label="item.label" :key="item.id" :value="item.id"></el-option>
                </el-select>
            </el-input>
            <el-input v-if="addBtn == true" placeholder="请输入你要搜索的~" v-model="searchParams.typeValue[1]" class="conditionalSearch2">
                <el-select v-model="searchParams.condition" class="condition" @change="isChange" slot="prepend" placeholder="请选择">
                    <el-option v-for="(item,index) in isOptions" :label="item.label" :key="item.id" :value="item.id"></el-option>
                </el-select>
                <el-select v-model="searchParams.type[1]" class="condition" @change="classification" slot="prepend" placeholder="请选择">
                    <el-option v-for="(item,index) in options" :label="item.label" :key="item.id" :value="item.id"></el-option>
                </el-select>
            </el-input>
            <el-button slot="append" @click="searchEngines" class="searchBtn" icon="el-icon-search"></el-button>
            <div class="ml-10" v-if="addBtn == false" @click="addBtn = true">
                <i class="el-icon-circle-plus f18"></i>
            </div>
            <div class="ml-10" v-if="addBtn == true" @click="addBtn = false">
                <i class="el-icon-remove f18"></i>
            </div>
        </div>
        <!-- <div class="toolBox">
            <span></span>
            <div class="c0a0" style="display:flex;height:20px;line-height:20px;margin-right:20px">
                工具栏：
                <a target="_blank" class="c191" href="https://blast.ncbi.nlm.nih.gov/Blast.cgi">Blast</a>&nbsp;|&nbsp;
                <a target="_blank" class="c191" href="https://swissmodel.expasy.org/">swissmodel</a>&nbsp;|&nbsp;
                <a target="_blank" class="c191" href="https://npsa-prabi.ibcp.fr/cgi-bin/secpred_sopma.pl">二级结构预测SOPMA</a>&nbsp;|&nbsp;
                <a target="_blank" class="c191" href="https://web.expasy.org/Protscale/">蛋白质亲疏水性ProtScale</a>&nbsp;|&nbsp;
                <a target="_blank" class="c191" href="http://www.expasy.org/tools/protparam.html">理化成分分析用ProtParam</a>&nbsp;|&nbsp;
                <a target="_blank" class="c191" href="http://www.cbs.dtu.dk/services/SignalP-5.0/index.php">信号肽预测SignalP</a>
            </div>
        </div> -->
        <div class="x-view">
            <aside>
                <!-- <el-input v-model="catalogSearch" class="muluSearch" placeholder="搜索目录~"></el-input> -->
                <!-- 菜单栏 -->
                <el-menu
                    default-active="2"
                    class="cSearch"
                    unique-opened
                    @select="handleselect"
                    @open="handleOpen"
                    @close="handleClose">
                    <el-submenu index="1" class="ecList">
                        <template slot="title">
                        <span>EC编号</span>
                        </template>
                        <el-menu-item v-for="(item,index) in characteristicList.ECBH" :key="index" :index="JSON.stringify({name:item.enzymeDictName,type:item.enzymeDictCode})"><i class="el-icon-circle-check c009"></i>{{item.enzymeDictName}}</el-menu-item>
                    </el-submenu>
                    <el-submenu index="2" class="lxList">
                        <template slot="title">
                        <span>酶类型</span>
                        </template>
                        <el-menu-item v-for="(item,index) in characteristicList.LX" :key="index" :index="JSON.stringify({name:item.enzymeDictName,type:item.enzymeDictCode})"><i class="el-icon-circle-check c009"></i>{{item.enzymeDictName}}</el-menu-item>
                    </el-submenu>
                    <el-submenu index="3" class="flList">
                        <template slot="title">
                        <span>温度耐受性</span>
                        </template>
                        <el-menu-item v-for="(item,index) in characteristicList.WD" :key="index" :index="JSON.stringify({name:item.enzymeDictName,type:item.enzymeDictCode})"><i class="el-icon-circle-check c009"></i>{{item.enzymeDictName}}</el-menu-item>
                    </el-submenu>
                    <el-submenu index="4" class="lxList">
                        <template slot="title">
                        <span>酸碱耐受性</span>
                        </template>
                        <el-menu-item v-for="(item,index) in characteristicList.SJ" :key="index" :index="JSON.stringify({name:item.enzymeDictName,type:item.enzymeDictCode})"><i class="el-icon-circle-check c009"></i>{{item.enzymeDictName}}</el-menu-item>
                    </el-submenu>
                    <el-submenu index="5" class="nsList">
                        <template slot="title">
                        <span>耐受性分类</span>
                        </template>
                        <el-menu-item v-for="(item,index) in characteristicList.NSFL" :key="index" :index="JSON.stringify({name:item.enzymeDictName,type:item.enzymeDictCode})"><i class="el-icon-circle-check c009"></i>{{item.enzymeDictName}}</el-menu-item>
                    </el-submenu>
                    <!-- <el-submenu index="2" class="flList">
                        <template slot="title">
                        <span>分类</span>
                        </template>
                        <el-menu-item v-for="(item,index) in characteristicList.FL" :key="index" :index="item.enzymeDictName+''"><i class="el-icon-circle-check c009"></i>{{item.enzymeDictName}}</el-menu-item>
                    </el-submenu> -->
                    <!-- <el-submenu index="4">
                        <template slot="title">
                        <span>物种</span>
                        </template>
                        <el-menu-item v-for="(item,index) in characteristicList.WZ" :key="index" :index="item.enzymeDictName+''"><i class="el-icon-circle-check c009"></i>{{item.enzymeDictName}}</el-menu-item>
                    </el-submenu> -->
                </el-menu>

                <ul class="toolbar">
                    <li>工具栏:</li>
                    <li class="mt-10 text-center"><a target="_blank" class="c191" href="https://blast.ncbi.nlm.nih.gov/Blast.cgi"><img src="../assets/img/blast.png" style="width:120px; height:40px"></a></li>
                    <li class="mt-10 text-center"><a target="_blank" class="c191" href="https://swissmodel.expasy.org/"><img src="../assets/img/swissmodel.png" style="width:120px; height:40px"></a></li>
                    <li class="mt-10 text-center"><a target="_blank" class="c191" href="https://npsa-prabi.ibcp.fr/cgi-bin/npsa_automat.pl?page=npsa_sopma.html"><img src="../assets/img/SOPMA.png" style="width:120px; height:40px"></a></li>
                    <li class="f12 c666 text-center">二级结构预测</li>
                    <li class="mt-10 text-center"><a target="_blank" class="c191" href="https://www.expasy.org/resources/protscale"><img src="../assets/img/ProtScale.png" style="width:120px; height:40px"></a></li>
                    <li class="f12 c666 text-center">蛋白质亲疏水性</li>
                    <li class="mt-10 text-center"><a target="_blank" class="c191" href="https://www.expasy.org/resources/protparam"><img src="../assets/img/ProtParam.png" style="width:120px; height:40px"></a></li>
                    <li class="f12 c666 text-center">理化成分分析</li>
                    <li class="mt-10 text-center"><a target="_blank" class="c191" href="https://www.expasy.org/"><img src="../assets/img/Expasy.png" style="width:120px; height:40px"></a></li>
                    <li class="mt-10 text-center"><a target="_blank" class="c191" href="http://www.cbs.dtu.dk/services/SignalP-5.0/index.php"><img src="../assets/img/SignalP.png" style="width:120px; height:40px"></a></li>
                    <li class="f12 c666 text-center">信号肽预测</li>
                    
                </ul>
            </aside>

            <div class="sec-right">
                <div class="x-BasicInfo">
                    <span class="x-title">基本信息</span>
                    <div class="flex">
                        <textarea id="copyBox" style="display:none"></textarea>                        
                        <el-button class="addBtn" style="background:rgba(36, 41, 46, .9); color:#fff"  @click="toggleCopy()">复制序列</el-button>
                        <el-button class="addBtn mr-5" style="background:rgba(36, 41, 46, .9); color:#fff"  @click="toggleSelection()">合并查看</el-button>
                        <el-upload
                            v-if="token"
                            :action="UploadUrl()"
                            :headers="headers()"
                            :on-change="handleChange"
                            :file-list="fileListUpload"
                            :show-file-list="false"
                            :on-success='handleSuccess'
                            accept=".xls,.xlsx">
                            <el-button :loading="disbtn" class="addBtn mr-5" type="primary">导入Excel</el-button>
                        </el-upload>
                        <el-button  type="success" plain :loading="disbtn" class="addBtn"  @click="doOut">导出Excel</el-button>
                        <!-- <el-button  type="success" plain :loading="disbtn" class="addBtn"  @click="doDown">下载Excel</el-button> -->
                        <el-button class="addBtn" style="background:rgba(36, 41, 46, .9); color:#fff" v-if="token" @click="addAndModify(1)">添加资料</el-button>
                    </div>
                </div>
                <el-table 
                    :data="tableData"
                    stripe
                    :header-cell-style="{'background':'rgba(36, 41, 46, .8)','color':'#fff'}"
                    style="width: 100%; margin-top:15px"
                    :default-sort = "{prop: 'date', order: 'descending'}"
                    ref="multipleTable"
                    @selection-change="handleSelectionChange"
                    >
                    <el-table-column
                        type="selection"
                        width="45">
                    </el-table-column>
                    <el-table-column
                        prop="enzymeName"
                        label="名称"
                        align="center"
                        min-width='15%'>
                    </el-table-column>
                    <el-table-column
                        prop="enzymeEcCode"
                        label="EC编号"
                        align="center"
                        min-width='15%'>
                    </el-table-column>
                    <el-table-column
                        prop="enzymeClassification"
                        label="分类"
                        align="center"
                        min-width='20%'>
                    </el-table-column>
                    <el-table-column
                        prop="enzymeType"
                        label="类型"    
                        align="center"
                        min-width='20%'>
                    </el-table-column>
                    <el-table-column
                        prop="enzymeSpecies"
                        label="所属物种"
                        align="center"
                        min-width='40%'>
                    </el-table-column>
                    <el-table-column
                        prop="enzymeMutationType"
                        label="类型"
                        align="center"
                        min-width='10%'>
                    </el-table-column>
                    <el-table-column
                        prop="enzymeMutant"
                        label="突变体"
                        align="center"
                        min-width='25%'>
                    </el-table-column>
                    <el-table-column label="操作" align="center"  min-width='25%'>
                        <template slot-scope="scope">
                            <el-button class="look bg_328" @click='see(scope.$index,scope.row.id)'>查看全部资料</el-button>
                            <el-button class="look bg_ff5" v-if="isAdmin == true" @click='addAndModify(2,scope.$index,scope.row.id)'>更新</el-button>
                            <el-button class="look bg_c10" v-if="isAdmin == true" @click='del(scope.$index,scope.row.id)'>删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <br>
                <p class="mt-5 ml-10"><span class="c1f2">总条数：</span>{{total}}</p>
                <el-pagination class="fy"
                    layout="prev, pager, next"
                    :current-page="this.currentPage"
                    @current-change="current_change"
                    :total="total"
                    background
                >
                </el-pagination>
                <!-- <Page :total="100" show-sizer show-elevator/> -->
            </div>

            <el-dialog class="allBox" title="查看全部资料" :visible.sync="seeInfo">
                <div class="table" v-for="(item,index) in meInfo" :key="index">
                    <div class="flex x-table">
                        <span class="x-title">名称</span>
                        <span class="x-info">{{item.enzymeName}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">EC编号</span>
                        <span class="x-info">{{item.enzymeEcCode}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">分类</span>
                        <span class="x-info">{{item.enzymeClassification}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">类型</span>
                        <span class="x-info">{{item.enzymeType}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">所属物种</span>
                        <span class="x-info">{{item.enzymeSpecies}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">类型</span>
                        <span class="x-info">{{item.enzymeMutationType}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">突变型</span>
                        <span class="x-info">{{item.enzymeMutant}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">酶gi号</span>
                        <span class="x-info">{{item.enzymeGiNum}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">分子量（kDa）</span>
                        <span class="x-info">{{item.enzymeKda}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">氨基酸数量</span>
                        <span class="x-info">{{item.enzymeAminoAcidNum}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">突变前</span>
                        <span class="x-info">{{item.enzymeMutationBefore}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">突变后</span>
                        <span class="x-info">{{item.enzymeMutationAfter}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">序列</span>
                        <span class="x-info">{{item.enzymeSequence}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">底物</span>
                        <span class="x-info">{{item.enzymeSubstrate}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">产物</span>
                        <span class="x-info">{{item.enzymeProduct}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">所属途径</span>
                        <span class="x-info">{{item.enzymeBelong}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">最小pH范围</span>
                        <span class="x-info">{{item.enzymeMinPhScope}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">最大pH范围</span>
                        <span class="x-info">{{item.enzymeMaxPhScope}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">最适pH</span>
                        <span class="x-info">{{item.enzymePerfectScope}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">酸碱耐受性</span>
                        <span class="x-info">{{item.enzymeAcidAlkaliTolerance}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">最小温度范围</span>
                        <span class="x-info">{{item.enzymeMinTemperatureScope}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">最大温度范围</span>
                        <span class="x-info">{{item.enzymeMaxTemperatureScope}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">最适温度</span>
                        <span class="x-info">{{item.enzymePerfectTemperatureScope}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">Tm值（℃）</span>
                        <span class="x-info">{{item.enzymeTm}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">半衰期</span>
                        <span class="x-info">{{item.enzymeHalfLife}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">温度耐受性</span>
                        <span class="x-info">{{item.enzymeTemperatureTolerance}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">耐受性分类</span>
                        <span class="x-info">{{item.enzymeToleranceClassification}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">其他耐受性</span>
                        <span class="x-info">{{item.enzymeTolerance}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">V<span style="font-size:10px">max</span>评价</span>
                        <span class="x-info">{{item.enzymeVmaxMmoEvaluation}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">K<span style="font-size:10px">m</span>评价</span>
                        <span class="x-info">{{item.enzymeKmMmolEvaluation}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">K<span style="font-size:10px">kcat</span>评价</span>
                        <span class="x-info">{{item.enzymeKcatEvaluation}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">K<span style="font-size:10px">cat</span>/K<span style="font-size:10px">m</span>评价</span>
                        <span class="x-info">{{item.enzymeKcatKmEvaluation}}</span>
                    </div>
                    <!-- <div class="flex x-table">
                        <span class="x-title">比活评价</span>
                        <span class="x-info">{{meInfo.enzymeBhEvaluation}}</span>
                    </div> -->
                    <div class="flex x-table">
                        <span class="x-title">总分评价</span>
                        <span class="x-info">{{item.enzymeOverallEvaluation}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">参考文献</span>
                        <span class="x-info">{{item.enzymeReference}}</span>
                    </div>
                    <div class="flex x-table">
                        <span class="x-title">参考文献地址</span>
                        <span class="x-info"><a target="_blank" :href="item.enzymeReferenceUrl">{{item.enzymeReferenceUrl}}</a></span>
                    </div>
                </div>
            </el-dialog>

            <el-dialog :title="addOrChange" :close-on-click-modal="false" :visible.sync="dialogFormVisible">
                <p class="text-right"><el-button style="background:rgba(36, 41, 46, .9); color:#fff" @click="addDictionaries()">新增字典</el-button></p>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="160px" class="demo-ruleForm mt-10">
                    <el-form-item label="名称" prop="enzymeName">
                        <el-input v-model="ruleForm.enzymeName"></el-input>
                    </el-form-item>
                    <el-form-item label="EC编号" prop="enzymeEcCode">
                        <!-- <el-input v-model="ruleForm.enzymeEcCode"></el-input> -->
                        <el-select v-model="ruleForm.enzymeEcCode" filterable placeholder="请选择">
                            <el-option
                                v-for="item in characteristicList.ECBH"
                                :key="item.enzymeDictName"
                                :label="item.enzymeDictName"
                                :value="item.enzymeDictName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="分类" prop="enzymeClassification">
                        <!-- <el-input v-model="ruleForm.enzymeClassification"></el-input> -->
                        <el-select v-model="ruleForm.enzymeClassification" filterable placeholder="请选择">
                            <el-option
                                v-for="item in characteristicList.FL"
                                :key="item.enzymeDictName"
                                :label="item.enzymeDictName"
                                :value="item.enzymeDictName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="类型" prop="enzymeType">
                        <!-- <el-input v-model="ruleForm.enzymeType"></el-input> -->
                        <el-select v-model="ruleForm.enzymeType" filterable placeholder="请选择">
                            <el-option
                                v-for="item in characteristicList.LX"
                                :key="item.enzymeDictName"
                                :label="item.enzymeDictName"
                                :value="item.enzymeDictName">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <!-- <el-form-item label="物种" prop="enzymeSpecies">
                        <el-select v-model="ruleForm.enzymeSpecies" filterable placeholder="请选择">
                            <el-option
                                v-for="item in characteristicList.WZ"
                                :key="item.enzymeDictName"
                                :label="item.enzymeDictName"
                                :value="item.enzymeDictName">
                            </el-option>
                        </el-select>
                    </el-form-item> -->
                    <el-form-item label="突变类型" prop="enzymeMutationType">
                        <el-select v-model="ruleForm.enzymeMutationType" placeholder="请选择突变类型" @change="seeChange">
                        <el-option label="野生型" value="W"></el-option>
                        <el-option label="突变型" value="M"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item v-if="ruleForm.enzymeMutationType == 'W' || ruleForm.enzymeMutationType == '野生型'" label="有无突变体" prop="enzymeMutant">
                        <el-input v-model="ruleForm.enzymeMutant"></el-input>
                    </el-form-item>
                    <!-- <el-form-item v-if="ruleForm.enzymeMutationType == ''" label="有无突变体" prop="enzymeMutant">
                        <el-input v-model="ruleForm.enzymeMutant"></el-input>
                    </el-form-item> -->
                    <el-form-item label="物种" prop="enzymeSpecies">
                        <el-input v-model="ruleForm.enzymeSpecies"></el-input>
                    </el-form-item>
                    <el-form-item label="酶gi号" prop="enzymeGiNum">
                        <el-input v-model="ruleForm.enzymeGiNum"></el-input>
                    </el-form-item>
                    <el-form-item label="分子量（kDa）" prop="enzymeKda">
                        <el-input v-model="ruleForm.enzymeKda"></el-input>
                    </el-form-item>
                    <el-form-item label="氨基酸数量" prop="enzymeAminoAcidNum">
                        <el-input v-model="ruleForm.enzymeAminoAcidNum"></el-input>
                    </el-form-item>
                    <el-form-item label="突变前" prop="enzymeMutationBefore">
                        <el-input v-model="ruleForm.enzymeMutationBefore"></el-input>
                    </el-form-item>
                    <el-form-item label="突变后" prop="enzymeMutationAfter">
                        <el-input v-model="ruleForm.enzymeMutationAfter"></el-input>
                    </el-form-item>
                    <el-form-item label="序列" prop="enzymeSequence">
                        <el-input v-model="ruleForm.enzymeSequence"></el-input>
                    </el-form-item>
                    <el-form-item label="底物" prop="enzymeSubstrate">
                        <el-input v-model="ruleForm.enzymeSubstrate"></el-input>
                    </el-form-item>
                    <el-form-item label="产物" prop="enzymeProduct">
                        <el-input v-model="ruleForm.enzymeProduct"></el-input>
                    </el-form-item>
                    <el-form-item label="所属途径" prop="enzymeBelong">
                        <el-input v-model="ruleForm.enzymeBelong"></el-input>
                    </el-form-item>
                    <el-form-item label="最小pH" prop="enzymeMinPhScope">
                        <el-input v-model="ruleForm.enzymeMinPhScope"></el-input>
                    </el-form-item>
                    <el-form-item label="最大pH" prop="enzymeMaxPhScope">
                        <el-input v-model="ruleForm.enzymeMaxPhScope"></el-input>
                    </el-form-item>
                    <el-form-item label="最适合pH" prop="enzymePerfectScope">
                        <el-input v-model="ruleForm.enzymePerfectScope"></el-input>
                    </el-form-item>
                    <el-form-item label="酸碱耐受性" prop="enzymeAcidAlkaliTolerance">
                        <el-input v-model="ruleForm.enzymeAcidAlkaliTolerance"></el-input>
                    </el-form-item>
                    <el-form-item label="最小温度" prop="enzymeMinTemperatureScope">
                        <el-input v-model="ruleForm.enzymeMinTemperatureScope"></el-input>
                    </el-form-item>
                    <el-form-item label="最大温度" prop="enzymeMaxTemperatureScope">
                        <el-input v-model="ruleForm.enzymeMaxTemperatureScope"></el-input>
                    </el-form-item>
                    <el-form-item label="最适宜温度" prop="enzymePerfectTemperatureScope">
                        <el-input v-model="ruleForm.enzymePerfectTemperatureScope"></el-input>
                    </el-form-item>
                    <el-form-item label="Tm值（℃）" prop="enzymeTm">
                        <el-input v-model="ruleForm.enzymeTm"></el-input>
                    </el-form-item>
                    <el-form-item label="半衰期" prop="enzymeHalfLife">
                        <el-input v-model="ruleForm.enzymeHalfLife"></el-input>
                    </el-form-item>
                    <el-form-item label="温度耐受性" prop="enzymeTemperatureTolerance">
                        <el-input v-model="ruleForm.enzymeTemperatureTolerance"></el-input>
                    </el-form-item>
                    <el-form-item label="耐受性分类" prop="enzymeToleranceClassification">
                        <el-input v-model="ruleForm.enzymeToleranceClassification"></el-input>
                    </el-form-item>
                    <el-form-item label="其他耐受性" prop="enzymeTolerance">
                        <el-input v-model="ruleForm.enzymeTolerance"></el-input>
                    </el-form-item>
                    <el-form-item label="Vmax(mmo/(L·s))评价" prop="enzymeVmaxMmoEvaluation">
                        <el-input v-model="ruleForm.enzymeVmaxMmoEvaluation"></el-input>
                    </el-form-item>
                    <el-form-item label="Km(mmol/L)评价" prop="enzymeKmMmolEvaluation">
                        <el-input v-model="ruleForm.enzymeKmMmolEvaluation"></el-input>
                    </el-form-item>
                    <el-form-item label="Kcat(s-1)评价" prop="enzymeKcatEvaluation">
                        <el-input v-model="ruleForm.enzymeKcatEvaluation"></el-input>
                    </el-form-item>
                    <el-form-item label="Kcat/Km(%)评价" prop="enzymeKcatKmEvaluation">
                        <el-input v-model="ruleForm.enzymeKcatKmEvaluation"></el-input>
                    </el-form-item>
                    <!-- <el-form-item label="比活评价" prop="enzymeBhEvaluation">
                        <el-input v-model="ruleForm.enzymeBhEvaluation" type="number"></el-input>
                    </el-form-item> -->
                    <el-form-item label="总分评价" prop="enzymeOverallEvaluation">
                        <el-input v-model="ruleForm.enzymeOverallEvaluation"></el-input>
                    </el-form-item>
                    <el-form-item label="参考文献" prop="enzymeReference">
                        <el-input v-model="ruleForm.enzymeReference"></el-input>
                    </el-form-item>
                    <el-form-item label="参考文献链接" prop="enzymeReferenceUrl">
                        <el-input v-model="ruleForm.enzymeReferenceUrl"></el-input>
                    </el-form-item>
                    <!-- <el-form-item label="参考文献" prop="enzymeReference">
                        <el-input v-model="ruleForm.enzymeReference"></el-input>
                    </el-form-item>
                    <el-form-item label="参考文献链接" prop="enzymeReferenceUrl">
                        <el-input v-model="ruleForm.enzymeReferenceUrl"></el-input>
                    </el-form-item>
                    <el-form-item label="参考文献" prop="enzymeReference">
                        <el-input v-model="ruleForm.enzymeReference"></el-input>
                    </el-form-item>
                    <el-form-item label="参考文献链接" prop="enzymeReferenceUrl">
                        <el-input v-model="ruleForm.enzymeReferenceUrl"></el-input>
                    </el-form-item> -->

                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">{{addOrChange}}</el-button>
                        <!-- <el-button @click="resetForm('ruleForm')">重置</el-button> -->
                    </el-form-item>
                </el-form>
            </el-dialog>

            <el-dialog title="新增字典" class="addNew" :visible.sync="addNewDictionaries">
                <el-form label-width="70px" class="demo-ruleForm mt-10">
                    <el-form-item label="名称">
                        <el-col :span="20">
                            <el-input v-model="dicName"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="类别">
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
                        <el-button type="primary" @click="saveOrUpdateDict">保存</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
                    
        </div>
    </div>
</template>


<script>
import Clipboard from 'clipboard';
export default {
    data(){
        return{
            isAdmin:false,
            addBtn:false,
            allSearch:'',
            catalogSearch:'',
            isSelect:'AND',
            typeSelect:'',
            // select:'全部',
            total:0,//默认数据总数
            currentPage: 1,
            pageSize:10,
            seeInfo:false,
            dialogFormVisible:false,
            addNewDictionaries:false,
            addOrChange:'添加',
            features:'',
            characteristicList:[],
            limitUpload:1,
            fileListUpload: [],
            fileTemp: [],
            disbtn: false,
            token:sessionStorage.getItem('token') ? sessionStorage.getItem('token') : '',
            searchParams:{
                type:[],
                condition:'',
                typeValue:[]
            },
            isOptions:[
                {
                    id:'AND',
                    label:'AND',
                    name:'AND'
                },
                {
                    id:'OR',
                    label:'OR',
                    name:'OR'
                },
                {
                    id:'NOR',
                    label:'NOR',
                    name:'NOR'
                },
            ],
            options:[
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
                    id:'WZ',
                    label:'物种',
                    name:'物种'
                },    
                {
                    id:'FYGC',
                    label:'反应过程',
                    name:'反应过程'
                },    
                {
                    id:'FYTJ',
                    label:'反应条件',
                    name:'反应条件'
                },      
                {
                    id:'NSX',
                    label:'耐受性',
                    name:'耐受性'
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
                    label:'分类',
                    name:'分类'
                },
                {
                    id:'LX',
                    label:'类型',
                    name:'类型'
                },               
            ],
            // menuItems:[
            //     {
            //         text:"ec编号"
            //     },
            //     {
            //         text:"类型"
            //     },
            //     {
            //         text:"物种",
            //     },
            //     {
            //         text:"物种",
            //     }
            // ],
            tableData: [],
            ruleForm: {
                enzymeId:'',
                enzymeName: '',
                enzymeEcCode:'',
                enzymeEcCode:'',
                enzymeClassification:'',
                enzymeType:'',
                enzymeSpecies:'',
                enzymeMutationType:'',
                enzymeMutant:'',
                enzymeId:'',
                enzymeGiNum:'',
                enzymeKda:'',
                enzymeAminoAcidNum:'',
                enzymeMutationBefore:'',
                enzymeMutationAfter:'',
                enzymeSequence:'',
                enzymeSubstrate:'',
                enzymeProduct:'',
                enzymeBelong:'',
                enzymeMinPhScope:'',
                enzymeMaxPhScope:'',
                enzymePerfectScope:'',
                enzymeMinTemperatureScope:'',
                enzymeMaxTemperatureScope:'',
                enzymePerfectTemperatureScope:'',
                enzymeTolerance:'',
                enzymeVmaxMmoEvaluation:'',
                enzymeKmMmolEvaluation:'',
                enzymePerfectTemperatureScope:'',
                enzymeKcatEvaluation:'',
                enzymeKcatKmEvaluation:'',
                enzymeBhEvaluation:'',
                enzymeReference:'',
                enzymeReferenceUrl:'',
                enzymeTm:'',
                enzymeHalfLife:'',
                enzymeTemperatureTolerance:'',
                enzymeAcidAlkaliTolerance:'',
                enzymeToleranceClassification:'',
                enzymeOverallEvaluation:'',
            },
            rules: {
                enzymeName: [
                    { required: true, message: '请输入活动名称', trigger: 'blur' },
                ],
                enzymeEcCode:[
                    {required: true, message: '请选择EC编号', trigger: 'change' }    
                ],
                enzymeClassification:[
                    {required: true, message: '请选择分类', trigger: 'change' }    
                ],
                enzymeType:[
                    {required: true, message: '请选择类型', trigger: 'change' }    
                ],
                enzymeSpecies:[
                    {required: true, message: '请输入物种', trigger: 'blur' }    
                ],
                enzymeMutationType:[
                    {required: true, message: '请选择突变类型', trigger: 'change' }    
                ],
                enzymeSequence:[
                    {required: true, message: '请输入序列', trigger: 'blur' }    
                ],
            },
            meInfo:[],
            newDic:'',
            dicName:'',
            newDictCode:'',
            dicLaber:'',
            isSee:false,
            addId:'',
            copyText:'',
            type:'1'
        }
    },

    created(){
        var userId = sessionStorage.getItem('token')
        if(userId){
            this.isAdmin = true
        }
        this.getInfo()
        this.getAllInfo(this.currentPage)
    },
    methods:{
        getInfo(){ //获取字典
            this.$http({method:'post',url: this.$api.user.user_search},{}).then(res=>{
            //   console.log(res)
              if(res.code == 200){
                  this.characteristicList = res.data
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

        getAllInfo(page){ //获取数据列表
            this.type = '1'
            let params = {currentPage:page,pageSize:this.pageSize}
            this.$Header_http_1({method:'post',url: this.$api.user.get_info},params).then(res=>{
            //   console.log(res)
              if(res.code == 200){
                  this.disbtn = false
                  for(var i = 0; i<res.data.list.length; i++){
                      if(res.data.list[i].enzymeMutationType == 'W'){
                          res.data.list[i].enzymeMutationType = '野生型'
                      }else{
                          res.data.list[i].enzymeMutationType = '突变型'
                      }
                  }
                  this.tableData = res.data.list
                  this.total = res.data.total
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

        searchEngines(){ //搜索
            this.type = '2'
            let Params = {condition:this.searchParams.condition, type:this.searchParams.type.toString(), typeValue:this.searchParams.typeValue.toString(), pageSize:this.pageSize, currentPage:1}
            this.$Header_http({method:'post',url: this.$api.user.enzymeComplexFindToEnzyme},Params).then(res=>{
            //   console.log(res)
              if(res.code == 200){
                  for(var i = 0; i<res.data.list.length; i++){
                      if(res.data.list[i].enzymeMutationType == 'W'){
                          res.data.list[i].enzymeMutationType = '野生型'
                      }else{
                          res.data.list[i].enzymeMutationType = '突变型'
                      }
                  }
                  this.tableData = res.data.list
                  this.total = res.data.total
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

        isChange(id){ //获取特性值
            // this.features = id
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
        },

        handleselect(data){ //左边点击搜索
            this.type = '3'
            if(data){
                this.currentPage = 1
                this.newData = JSON.parse(data)
            }
            let Params = {keyword:this.newData.name,type:this.newData.type,currentPage:this.currentPage,pageSize:this.pageSize}
            this.$Header_http({method:'post',url: this.$api.user.findEnzymeByDict},Params).then(res=>{
            //   console.log(res)
              if(res.code == 200){
                  for(var i = 0; i<res.data.list.length; i++){
                      if(res.data.list[i].enzymeMutationType == 'W'){
                          res.data.list[i].enzymeMutationType = '野生型'
                      }else{
                          res.data.list[i].enzymeMutationType = '突变型'
                      }
                  }
                  this.tableData = res.data.list
                  this.total = res.data.total
                //   console.log(this.tableData)
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

        handleOpen(){

        },

        handleClose(){

        },

        see(index,id){ //查看全部资料
            this.meInfo = []
            // console.log(id)
            this.seeInfo = true
            var Params = {"enzymeId": id}
            this.$Header_http_1({method:'post',url: this.$api.user.findEnzymeInfo},Params).then(res=>{
                // console.log(res)
                if(res.code == 200){
                    if(res.data.enzymeMutationType == 'W'){
                        res.data.enzymeMutationType = '野生型'
                    }else{
                        res.data.enzymeMutationType = '突变型'
                    }
                    this.meInfo.push(res.data)
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

        addAndModify(id,index,item){
            this.addId = id;
            // console.log(id,index)
            if(id == 1){
                this.addOrChange = "添加资料"
                this.isSee = false
                this.ruleForm = {
                    enzymeId:'',
                    enzymeName: '',
                    enzymeEcCode:'',
                    enzymeEcCode:'',
                    enzymeClassification:'',
                    enzymeType:'',
                    enzymeSpecies:'',
                    enzymeMutationType:'',
                    enzymeMutant:'',
                    enzymeId:'',
                    enzymeGiNum:'',
                    enzymeKda:'',
                    enzymeAminoAcidNum:'',
                    enzymeMutationBefore:'',
                    enzymeMutationAfter:'',
                    enzymeSequence:'',
                    enzymeSubstrate:'',
                    enzymeProduct:'',
                    enzymeBelong:'',
                    enzymeMinPhScope:'',
                    enzymeMaxPhScope:'',
                    enzymePerfectScope:'',
                    enzymeMinTemperatureScope:'',
                    enzymeMaxTemperatureScope:'',
                    enzymeTolerance:'',
                    enzymeVmaxMmoEvaluation:'',
                    enzymePerfectTemperatureScope:'',
                    enzymeKcatEvaluation:'',
                    enzymeKcatKmEvaluation:'',
                    enzymeBhEvaluation:'',
                    enzymeReference:'',
                    enzymeReferenceUrl:'',
                }
            }else{
                this.addOrChange = "更新资料"
                this.isSee = true
                var Params = {"enzymeId":item}
                this.$Header_http_1({method:'post',url: this.$api.user.findEnzymeInfo},Params).then(res=>{
                    // console.log(res)
                    if(res.code == 200){
                        if(res.data.enzymeMutationType == 'W'){
                            res.data.enzymeMutationType = '野生型'
                        }else{
                            res.data.enzymeMutationType = '突变型'
                        }
                        this.ruleForm = res.data
                        // console.log(this.ruleForm)
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
            this.dialogFormVisible = true
        },

        addDictionaries(){ //新增字典
            this.addNewDictionaries = true
        },

        del(index,id){
            this.$confirm('确认删除此条资料？', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(() => {
                this.$Header_http({method:'post',url: this.$api.user.deleteEnzyme},{enzymeId:id}).then(res=>{
                    console.log(res)
                    if(res.code == 200){
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getAllInfo(1)
                        //   console.log(this.characteristicList)
                    }else{
                        this.$message({
                            message: res.msg,
                            type: 'error'
                        });
                    }
                });
            })
        },

        current_change(currentPage){
            this.currentPage = currentPage
            if(this.type == '1'){
                this.getAllInfo(currentPage)
            }else if(this.type == '2'){
                this.searchEngines()
            }else{
                this.handleselect()
            }
        },

        submitForm(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    // alert('submit!');
                    var Params = this.ruleForm
                    for(let key in Params){
                        if(Params[key] == '' ){
                            Params[key] = 'NaN'
                        }
                    }
                    
                    if(Params.enzymeId == 'NaN'){
                        Params.enzymeId = ''
                        // Params.enzymeMinPhScope = '',
                        // Params.enzymeMaxPhScope = '',
                        // Params.enzymePerfectScope = '',
                        // Params.enzymeMinTemperatureScope = '',
                        // Params.enzymeMaxTemperatureScope = '',
                        // Params.enzymePerfectTemperatureScope = ''
                        // console.log(Params)
                    }else{
                        if(Params.enzymeMutationType == '野生型'){
                            Params.enzymeMutationType = 'W'
                        }else if(Params.enzymeMutationType == '突变型'){
                            Params.enzymeMutationType = 'M'
                        }
                    }

                    // console.log(Params)
                    this.$Header_http({method:'post',url: this.$api.user.saveOrUpdateEnzyme},Params).then(res=>{
                        console.log(res)
                          this.logining = false;
                          if(res.code == 200){
                              this.dialogFormVisible =false
                              this.$message({
                                message: this.addOrChange + '成功',
                                type: 'success'
                              });
                              if(this.addId == '1'){
                                this.getAllInfo(1)
                              }else{

                              }
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

        seeChange(){
            // console.log(this.ruleForm.enzymeMutationType)
        },

        handleChange (file, fileList) {
            this.fileTemp = file.raw
            this.doBegin()
        },
        
        doBegin(){
            let name = this.fileTemp.name.substr(this.fileTemp.name.length-4)
            //  ((this.fileTemp.type == 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') || (this.fileTemp.type == 'application/vnd.ms-excel')) 
            if ((name == '.xls') || (name == 'xlsx')) {
                this.disbtn = true
                // this.importfxx(this.fileTemp)
            } else {
                this.$message({
                    type: 'warning',
                    message: '附件格式错误，请删除后重新上传！'
                })
            }
        },

        // Excel 转化
        importfxx (obj) {
            let _this = this
            // setTimeout(()=>{
                
            // },3000)
            
            // 通过DOM取文件数据
            this.file = obj
            var rABS = false // 是否将文件读取为二进制字符串
            var f = this.file
            var reader = new FileReader()
            // if (!FileReader.prototype.readAsBinaryString) {
            FileReader.prototype.readAsBinaryString = function (f) {
                var binary = ''
                var rABS = false // 是否将文件读取为二进制字符串
                var pt = this
                var wb // 读取完成的数据
                var outdata
                var reader = new FileReader()
                reader.onload = function (e) {
                var bytes = new Uint8Array(reader.result)
                var length = bytes.byteLength
                for (var i = 0; i < length; i++) {
                    binary += String.fromCharCode(bytes[i])
                }
                var XLSX = require('xlsx')
                if (rABS) {
                    wb = XLSX.read(btoa(fixdata(binary)), { // 手动转化
                    type: 'base64'
                    })
                } else {
                    wb = XLSX.read(binary, {
                    type: 'binary'
                    })
                }
                outdata = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]])// outdata就是你想要的东西
                this.da = [...outdata]
                let arr = []
                this.da.map(v => {
                    let obj = {}
                    obj.enzymeName = v['酶名称']
                    obj.enzymeEcCode = v['EC编号']
                    obj.enzymeClassification = v['分类']
                    obj.enzymeType = v['酶类型']
                    obj.enzymeSpecies = v['所属物种']
                    obj.enzymeMutationType = v['野生型（W）/突变体（M）']
                    obj.enzymeMutant = v['是否有突变体']
                    obj.enzymeGiNum = v['gi号']
                    obj.enzymeKda = v['分子量(kDa)']
                    obj.enzymeAminoAcidNum = v['氨基酸数量']
                    obj.enzymeMutationBefore = v['突变前']
                    obj.enzymeMutationAfter = v['突变后']
                    obj.enzymeSequence = v['序列']
                    obj.enzymeSubstrate = v['底物']
                    obj.enzymeProduct = v['产物']
                    obj.enzymeBelong = v['所属途径']
                    obj.enzymeMinPhScope = v['最小pH']
                    obj.enzymeMaxPhScope = v['最大pH']
                    obj.enzymePerfectScope = v['最适温度']
                    obj.enzymeAcidAlkaliTolerance = v['酸碱耐受性']
                    obj.enzymeMinTemperatureScope = v['最小温度']
                    obj.enzymeMaxTemperatureScope = v['最大温度']
                    obj.enzymePerfectTemperatureScope = v['最适温度']
                    obj.enzymeTm = v['Tm值（℃）']
                    obj.enzymeHalfLife = v['半衰期']
                    obj.enzymeTemperatureTolerance = v['温度耐受性']
                    obj.enzymeToleranceClassification = v['其他耐受性']
                    obj.enzymeTolerance = v['耐受性分类']
                    obj.enzymeVmaxMmoEvaluation = v['Vmax（mmo/（L·s））评价']
                    obj.enzymeKmMmolEvaluation = v['Km（mmol/L）评价']
                    obj.enzymeKcatEvaluation = v['Kcat（s-1）评价']
                    obj.enzymeKcatKmEvaluation = v['Kcat/Km （%）评价']
                    obj.enzymeOverallEvaluation = v['总分评价']
                    obj.enzymeReference = v['参考文献']
                    obj.enzymeReferenceUrl = v['参考文献地址']
                    arr.push(obj)
                    // _this.tableData = _this.tableData.concat([...arr])
                    
                })
                }
                reader.readAsArrayBuffer(f)
            }
            if (rABS) {
                reader.readAsArrayBuffer(f)
            } else {
                reader.readAsBinaryString(f)
            }
        },

        handleSuccess(res,file,fileList){
            var _this = this
            if(res.code == 200){
                _this.getAllInfo(1)
                _this.$message({
                    message: res.data,
                    type: 'success'
                });
            }else {
                _this.$message({
                    message: res.msg,
                    type: 'error',
                    duration:0,
                    showClose:true
                });
            }   
        },

        doOut(){
            this.disbtn = true
            if(this.type == '1'){
                let params = {currentPage:1,pageSize:999999}
                this.$Header_http_1({method:'post',url: this.$api.user.get_info},params).then(res=>{
                //   console.log(res)
                if(res.code == 200){
                    this.disbtn = false
                    for(var i = 0; i<res.data.list.length; i++){
                        if(res.data.list[i].enzymeMutationType == 'W'){
                            res.data.list[i].enzymeMutationType = '野生型'
                        }else{
                            res.data.list[i].enzymeMutationType = '突变型'
                        }
                    }
                    this.getExcel(res.data.list)
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
            }else if(this.type == '2'){
                let Params = {condition:this.searchParams.condition, type:this.searchParams.type.toString(), typeValue:this.searchParams.typeValue.toString(), pageSize:this.total, currentPage:1}
                this.$Header_http({method:'post',url: this.$api.user.enzymeComplexFindToEnzyme},Params).then(res=>{
                    if(res.code == 200){
                        for(var i = 0; i<res.data.list.length; i++){
                            if(res.data.list[i].enzymeMutationType == 'W'){
                                res.data.list[i].enzymeMutationType = '野生型'
                            }else{
                                res.data.list[i].enzymeMutationType = '突变型'
                            }
                        }
                        this.getExcel(res.data.list)
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
                let Params = {keyword:this.newData.name,type:this.newData.type,currentPage:1,pageSize:this.total}
                this.$Header_http({method:'post',url: this.$api.user.findEnzymeByDict},Params).then(res=>{
                    if(res.code == 200){
                        for(var i = 0; i<res.data.list.length; i++){
                            if(res.data.list[i].enzymeMutationType == 'W'){
                                res.data.list[i].enzymeMutationType = '野生型'
                            }else{
                                res.data.list[i].enzymeMutationType = '突变型'
                            }
                        }
                        this.getExcel(res.data.list)
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

        //  数组导入Excel
        getExcel(arr) { 　　　　　　
            require.ensure([], () => {
                // 这里 require 的地址指向你项目中 Export2Excel.js 文件存放地址
                const { export_json_to_excel } = require('../vendor/Export2Excel.js')
                const tHeader = ['酶名称', 'EC编号','分类','酶类型','所属物种','野生型（W）/突变体（M）','是否有突变体','gi号','分子量(kDa)','氨基酸数量','突变前','突变后','序列','底物','产物','所属途径','最小pH','最大pH','最适pH','酸碱耐受性','最小温度','最大温度','最适温度','Tm值（℃）','半衰期','温度耐受性','其他耐受性','耐受性分类','Vmax（mmo/（L·s））评价','Km（mmol/L）评价','Kcat（s-1）评价','Kcat/Km （%）评价','总分评价','参考文献','参考文献地址',]
                const filterVal = ['enzymeName', 'enzymeEcCode','enzymeClassification','enzymeType','enzymeSpecies','enzymeMutationType','enzymeMutant','enzymeGiNum','enzymeKda','enzymeAminoAcidNum','enzymeMutationBefore','enzymeMutationAfter','enzymeSequence','enzymeSubstrate','enzymeProduct','enzymeBelong','enzymeMinPhScope','enzymeMaxPhScope','enzymePerfectScope','enzymeAcidAlkaliTolerance','enzymeMinTemperatureScope','enzymeMaxTemperatureScope','enzymePerfectTemperatureScope','enzymeTm','enzymeHalfLife','enzymeTemperatureTolerance','enzymeToleranceClassification','enzymeTolerance','enzymeVmaxMmoEvaluation','enzymeKmMmolEvaluation','enzymeKcatEvaluation','enzymeKcatKmEvaluation','enzymeOverallEvaluation','enzymeReference','enzymeReferenceUrl']
                const list = arr
                const data = this.formatJson(filterVal, list)
                export_json_to_excel(tHeader, data, '酶数据库')
                this.disbtn = false
            })
        },

        formatJson(filterVal, jsonData) {
            return jsonData.map(v => filterVal.map(j => v[j]))
        },

        handleSelectionChange(val) {
            if(val.length>3){
                this.$message({
                    message: '一次最多只能选3个~',
                    type: 'error'
                });
            }else{
                this.multipleSelection = val;
            }
        },

        toggleSelection(rows) {
            if(this.multipleSelection.length < 4){
                console.log(this.multipleSelection)
                var arr = []
                for(var i = 0;i<this.multipleSelection.length; i++){
                    arr.push(this.multipleSelection[i].id)
                }
                var ids = arr.toString()
                this.$Header_http({method:'post',url: this.$api.user.findEnzymeByIdList},{ids:ids}).then(res=>{
                    // console.log(res)
                    if(res.code == 200){
                        // let enzymeAcidAlkaliTolerance = []
                        // let enzymeAminoAcidNum = []
                        // let enzymeBelong = []
                        // let enzymeBhEvaluation = []
                        // let enzymeClassification = []
                        // let enzymeEcCode = []
                        // let enzymeGiNum = []
                        // let enzymeHalfLife = []
                        // let enzymeKcatEvaluation = []
                        // let enzymeKcatKmEvaluation = []
                        // let enzymeKda = []
                        // let enzymeKmMmolEvaluation = []
                        // let enzymeMaxPhScope = []
                        // let enzymeMaxTemperatureScope = []
                        // let enzymeMinPhScope = []
                        // let enzymeMinTemperatureScope = []
                        // let enzymeMutant = []
                        // let enzymeMutationAfter = []
                        // let enzymeMutationBefore = []
                        // let enzymeMutationType = []
                        // let enzymeName = []
                        // let enzymePerfectTemperatureScope = []
                        // let enzymeProduct = []
                        // let enzymeReference = []
                        // let enzymeReferenceUrl = []
                        // let enzymeOverallEvaluation = []
                        // let enzymePerfectScope = []
                        // let enzymeSequence = []
                        // let enzymeSpecies = []
                        // let enzymeSubstrate = []
                        // let enzymeTemperatureTolerance = []
                        // let enzymeTm = []
                        // let enzymeTolerance = []
                        // let enzymeToleranceClassification = []
                        // let enzymeType = []
                        // let enzymeVmaxMmoEvaluation = []
                        // let enzymeId = []
                        // for(var i = 0; i<res.data.length;i++){
                        //     if(res.data[i].enzymeMutationType == 'W'){
                        //         res.data[i].enzymeMutationType = '野生型'
                        //     }else{
                        //         res.data[i].enzymeMutationType = '突变型'
                        //     }
                        //     enzymeMinPhScope.push(res.data[i].enzymeMinPhScope),
                        //     enzymeAcidAlkaliTolerance.push(res.data[i].enzymeAcidAlkaliTolerance)
                        //     enzymeId.push(res.data[i].enzymeId)
                        //     enzymeVmaxMmoEvaluation.push(res.data[i].enzymeVmaxMmoEvaluation)
                        //     enzymeType.push(res.data[i].enzymeType)
                        //     enzymeToleranceClassification.push(res.data[i].enzymeToleranceClassification)
                        //     enzymeTolerance.push(res.data[i].enzymeTolerance)
                        //     enzymeTm.push(res.data[i].enzymeTm)
                        //     enzymeTemperatureTolerance.push(res.data[i].enzymeTemperatureTolerance)
                        //     enzymeSubstrate.push(res.data[i].enzymeSubstrate)
                        //     enzymeReferenceUrl.push(res.data[i].enzymeReferenceUrl)
                        //     enzymeReference.push(res.data[i].enzymeReference)
                        //     enzymeProduct.push(res.data[i].enzymeProduct)
                        //     enzymePerfectTemperatureScope.push(res.data[i].enzymePerfectTemperatureScope)
                        //     enzymeName.push(res.data[i].enzymeName)
                        //     enzymeMutationType.push(res.data[i].enzymeMutationType)
                        //     enzymeMutationBefore.push(res.data[i].enzymeMutationBefore)
                        //     enzymeMutationAfter.push(res.data[i].enzymeMutationAfter)
                        //     enzymeAminoAcidNum.push(res.data[i].enzymeAminoAcidNum)
                        //     enzymeBelong.push(res.data[i].enzymeBelong)
                        //     enzymeBhEvaluation.push(res.data[i].enzymeBhEvaluation)
                        //     enzymeClassification.push(res.data[i].enzymeClassification)
                        //     enzymeEcCode.push(res.data[i].enzymeEcCode)
                        //     enzymeGiNum.push(res.data[i].enzymeGiNum)
                        //     enzymeHalfLife.push(res.data[i].enzymeHalfLife)
                        //     enzymeKcatEvaluation.push(res.data[i].enzymeKcatEvaluation)
                        //     enzymeKcatKmEvaluation.push(res.data[i].enzymeKcatKmEvaluation)
                        //     enzymeKda.push(res.data[i].enzymeKda)
                        //     enzymeKmMmolEvaluation.push(res.data[i].enzymeKmMmolEvaluation)
                        //     enzymeMaxPhScope.push(res.data[i].enzymeMaxPhScope)
                        //     enzymeMaxTemperatureScope.push(res.data[i].enzymeMaxTemperatureScope)
                        //     enzymeMinTemperatureScope.push(res.data[i].enzymeMinTemperatureScope)
                        //     enzymeMutant.push(res.data[i].enzymeMutant)
                        //     enzymeSpecies.push(res.data[i].enzymeSpecies)
                        //     enzymeSequence.push(res.data[i].enzymeSequence)
                        //     enzymePerfectScope.push(res.data[i].enzymePerfectScope)
                        //     enzymeOverallEvaluation.push(res.data[i].enzymeOverallEvaluation)
                        // }
                        this.meInfo = res.data
                       
                        // this.meInfo = {
                        //     enzymeMinPhScope:enzymeMinPhScope.toString(),
                        //     enzymeMutant:enzymeMutant.toString(),
                        //     enzymeSpecies:enzymeSpecies.toString(),
                        //     enzymeSequence:enzymeSequence.toString(),
                        //     enzymePerfectScope:enzymePerfectScope.toString(),
                        //     enzymeMinTemperatureScope: enzymeMinTemperatureScope.toString(),
                        //     enzymeMaxTemperatureScope: enzymeMaxTemperatureScope.toString(),
                        //     enzymeMaxPhScope : enzymeMaxPhScope.toString(),
                        //     enzymeKmMmolEvaluation :  enzymeKmMmolEvaluation.toString(),
                        //     enzymeKda :  enzymeKda.toString(),
                        //     enzymeKcatKmEvaluation :  enzymeKcatKmEvaluation.toString(),
                        //     enzymeKcatEvaluation :  enzymeKcatEvaluation.toString(),
                        //     enzymeHalfLife :  enzymeHalfLife.toString(),
                        //     enzymeGiNum :  enzymeGiNum.toString(),
                        //     enzymeEcCode :  enzymeEcCode.toString(),
                        //     enzymeClassification :  enzymeClassification.toString(),
                        //     enzymeBhEvaluation :  enzymeBhEvaluation.toString(),
                        //     enzymeAminoAcidNum :  enzymeAminoAcidNum.toString(),
                        //     enzymeBelong : enzymeBelong.toString(),
                        //     enzymeMutationAfter : enzymeMutationAfter.toString(),
                        //     enzymeMutationBefore :  enzymeMutationBefore.toString(),
                        //     enzymeMutationType : enzymeMutationType.toString(),
                        //     enzymeName : enzymeName.toString(),
                        //     enzymeAcidAlkaliTolerance : enzymeAcidAlkaliTolerance.toString(),
                        //     enzymeId : enzymeId.toString(),
                        //     enzymeVmaxMmoEvaluation: enzymeVmaxMmoEvaluation.toString(),
                        //     enzymeType : enzymeType.toString(),
                        //     enzymeToleranceClassification : enzymeToleranceClassification.toString(),
                        //     enzymeTolerance : enzymeTolerance.toString(),
                        //     enzymeTm : enzymeTm.toString(),
                        //     enzymeTemperatureTolerance: enzymeTemperatureTolerance.toString(),
                        //     enzymeSubstrate : enzymeSubstrate.toString(),
                        //     enzymeReferenceUrl : enzymeReferenceUrl.toString(),
                        //     enzymeReference : enzymeReference.toString(),
                        //     enzymeProduct: enzymeProduct.toString(),
                        //     enzymePerfectTemperatureScope : enzymePerfectTemperatureScope.toString(),
                        //     enzymeOverallEvaluation:enzymeOverallEvaluation.toString()
                        // }
                        this.seeInfo =true
                    }else{
                        this.$message({
                            message: res.msg,
                            type: 'error'
                        });
                    }
                });
            }else{
                this.$message({
                    message: '合并查看,一次最多只能选3个~',
                    type: 'error'
                });
            }
            // console.log(this.multipleSelection)
            // if (rows) {
            //     rows.forEach(row => {
            //         this.$refs.multipleTable.toggleRowSelection(row);
            //     });
            // } else {
            //     this.$refs.multipleTable.clearSelection();
            // }
        },

        toggleCopy(row){
            console.log(this.multipleSelection)
            if(this.multipleSelection.length < 4){
                if(this.multipleSelection.length == 1){
                    this.copyText = '>'+ this.multipleSelection[0].enzymeName +'|'+ this.multipleSelection[0].enzymeSpecies + '\n' + this.multipleSelection[0].enzymeSequence
                    const input = document.createElement('textarea')
                    input.value = this.copyText
                    document.body.appendChild(input)
                    input.select()
                    document.execCommand('Copy')
                    document.body.removeChild(input)
                    this.$message({
                        message: '复制成功',
                        type: 'success'
                    });
                }else if(this.multipleSelection.length == 2){
                    this.copyText = '>'+ this.multipleSelection[0].enzymeName +'|'+ this.multipleSelection[0].enzymeSpecies +'\n'  + this.multipleSelection[0].enzymeSequence + '\n>' + this.multipleSelection[1].enzymeName + '|'+ this.multipleSelection[1].enzymeSpecies + '\n'  + this.multipleSelection[1].enzymeSequence
                    const input = document.createElement('textarea')
                    input.value = this.copyText
                    document.body.appendChild(input)
                    input.select()
                    document.execCommand('Copy')
                    document.body.removeChild(input)
                    this.$message({
                        message: '复制成功',
                        type: 'success'
                    });
                }else if(this.multipleSelection.length == 3){
                    this.copyText = '>'+ this.multipleSelection[0].enzymeName +'|'+ this.multipleSelection[0].enzymeSpecies +'\n'  + this.multipleSelection[0].enzymeSequence + '\n>' + this.multipleSelection[1].enzymeName + '|' + this.multipleSelection[1].enzymeSpecies + '\n' + this.multipleSelection[1].enzymeSequence  + '\n>' + this.multipleSelection[2].enzymeName + '|'+ this.multipleSelection[2].enzymeSpecies + '\n' + this.multipleSelection[2].enzymeSequence
                    const input = document.createElement('textarea')
                    input.value = this.copyText
                    document.body.appendChild(input)
                    input.select()
                    document.execCommand('Copy')
                    document.body.removeChild(input)
                    this.$message({
                        message: '复制成功',
                        type: 'success'
                    });
                }
            }
        },

        UploadUrl(){
            return "http://www.ultrastable-enzyme.online:8083/enzymeAdminUser/importExcel";
            // return "https://enzyme.utools.club/enzymeAdminUser/importExcel";
        },

        headers(){
            var token = sessionStorage.getItem('token') ? sessionStorage.getItem('token') : '';
            var headers={
                'jwt_token':token.substring(1,token.length-1),
                // 'Accept':'application/json',
                // 'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'
            }
            return headers
        },
        

        // doDown(){
        //     let url = 'https://raw.githubusercontent.com/xrkffgg/Kvue/master/src/assets/excel/demo.xlsx'
        //     window.location.href = url
        // },
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
    justify-content:flex-end;
    align-items: center;
    padding: 0 0px 15px 0px;
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
    min-width: 220px;
    background: #fff;
    height: 100%;
    transition: all .5s;
    overflow: auto;
    font-size: 16px;
}

.toolbar{
    overflow: hidden;
    margin-top: 30px;
    padding: 0 20px !important;
}

.sec-right {
    min-width: 78%;
    display: flex;
    flex-direction: column;
    transition: width .5s;
    padding:0 10px 15px 10px;
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
.x-table{border: 1px solid #d3d3d3; border-bottom: 0}
.x-table .x-title{border-right:1px solid #d3d3d3; background: #393d49; color: #fff; display: block; min-width: 30%; padding: 10px; display: flex; align-items:center}
.x-table .x-info{padding: 10px;}
.x-table:last-child{border: 1px solid #d3d3d3;}
.toolBox{max-width: 1440px;display: flex; align-items: center; justify-content: space-between; padding: 0 0px 15px 0px;}
</style>