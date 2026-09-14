<template>
	<div class="main-content" :style='{"width":"calc(100% - 40px)","padding":"20px 30px","margin":"20px 0 0 auto","fontSize":"15px","borderRadius":"10px","background":"#fff"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"margin":"0 10px 20px"}' :inline="true" :model="searchForm">
				<el-row :style='{"padding":"10px","flexWrap":"wrap","background":"#fff","display":"flex"}' >
					<div :style='{"alignItems":"center","margin":"0 10px 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">活动名称</label>
						<el-input v-model="searchForm.huodongmingcheng" placeholder="活动名称" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div :style='{"alignItems":"center","margin":"0 10px 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#666","display":"inline-block","lineHeight":"40px","fontSize":"inherit","fontWeight":"500","height":"40px"}' class="item-label">地点</label>
						<el-input v-model="searchForm.didian" placeholder="地点" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<el-button class="search" type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing07" :style='{"margin":"0 2px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>

				<el-row class="actions" :style='{"padding":"10px","margin":"20px 0","flexWrap":"wrap","background":"#fff","display":"flex"}'>
					<el-button class="add" v-if="isAuth('shequhuodong','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-tianjia16" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","height":"34px"}'></span>
						添加
					</el-button>
					<el-button class="del" v-if="isAuth('shequhuodong','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-shanchu1" :style='{"margin":"0 2px","fontSize":"14px","color":"#107ED6","height":"34px"}'></span>
						删除
					</el-button>


					<el-button class="btn18" v-if="isAuth('shequhuodong','报名人数统计')" type="success" @click="chartDialog1()">
						<span class="icon iconfont icon-qita17" :style='{"margin":"0 2px","fontSize":"14px","color":"#107ED6","height":"34px"}'></span>
						报名人数统计
					</el-button>
				</el-row>
			</el-form>
			<div :style='{"width":"100%","padding":"0 10px","fontSize":"14px","color":"#000"}'>
				<el-table class="tables"
					:stripe='false'
					:style='{"padding":"10px 0","borderColor":"#eee","borderRadius":"20px 20px 0 0","borderWidth":"0","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}' 
					:border='false'
					v-if="isAuth('shequhuodong','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'  
						prop="huodongmingcheng"
						label="活动名称">
						<template slot-scope="scope">
							{{scope.row.huodongmingcheng}}
						</template>
					</el-table-column>
					<el-table-column  :resizable='true' prop="huodongfengmian" width="200" label="活动封面">
						<template slot-scope="scope">
							<div v-if="scope.row.huodongfengmian">
								<img v-if="scope.row.huodongfengmian.substring(0,4)=='http'&&scope.row.huodongfengmian.split(',w').length>1" :src="scope.row.huodongfengmian" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.huodongfengmian)">
								<img v-else-if="scope.row.huodongfengmian.substring(0,4)=='http'" :src="scope.row.huodongfengmian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.huodongfengmian.split(',')[0])">
								<img v-else :src="$base.url+scope.row.huodongfengmian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.huodongfengmian.split(',')[0])">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="huodongleixing"
						label="活动类型">
						<template slot-scope="scope">
							{{scope.row.huodongleixing}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="shijian"
						label="时间">
						<template slot-scope="scope">
							{{scope.row.shijian}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="baomingrenshu"
						label="报名人数">
						<template slot-scope="scope">
							{{scope.row.baomingrenshu}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="huodongzhuti"
						label="活动主题">
						<template slot-scope="scope">
							{{scope.row.huodongzhuti}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="didian"
						label="地点">
						<template slot-scope="scope">
							{{scope.row.didian}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="faburiqi"
						label="发布日期">
						<template slot-scope="scope">
							{{scope.row.faburiqi}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="zhanghao"
						label="账号">
						<template slot-scope="scope">
							{{scope.row.zhanghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="xingming"
						label="组织者">
						<template slot-scope="scope">
							{{scope.row.xingming}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="shoujihaoma"
						label="手机号码">
						<template slot-scope="scope">
							{{scope.row.shoujihaoma}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="clicknum"
						label="点击次数">
						<template slot-scope="scope">
							{{scope.row.clicknum}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="discussnum"
						label="评论数">
						<template slot-scope="scope">
							{{scope.row.discussnum}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="storeupnum"
						label="收藏数">
						<template slot-scope="scope">
							{{scope.row.storeupnum}}
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('shequhuodong','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="btn8" v-if="isAuth('shequhuodong','立即报名')" @click="baomingshenqingCrossAddOrUpdateHandler(scope.row,'cross','','','[1]','已报名')" type="success">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								立即报名
							</el-button>
							<el-button class="edit" v-if=" isAuth('shequhuodong','修改') " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								修改
							</el-button>

							<el-button class="view" v-if="isAuth('shequhuodong','查看评论')" type="success" @click="disscussListHandler(scope.row.id)">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								查看评论
							</el-button>



							<el-button class="del" v-if="isAuth('shequhuodong','删除') " type="primary" @click="deleteHandler(scope.row.id )">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="上一页 "
				next-text="下一页 "
				:hide-on-single-page="false"
				:style='{"padding":"0","boxShadow":"inset 0px 3px 6px 0px #ccc","margin":"20px auto 0","whiteSpace":"nowrap","color":"#333","textAlign":"center","background":"#fff","width":"60%","fontSize":"inherit","fontWeight":"500"}'
			></el-pagination>
		</template>
		
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>

		<baomingshenqing-cross-add-or-update v-if="baomingshenqingCrossAddOrUpdateFlag" :parent="this" ref="baomingshenqingCrossaddOrUpdate"></baomingshenqing-cross-add-or-update>



		<el-dialog
			:visible.sync="chartVisiable1"
			width="800">
			<div id="baomingrenshuChart1" style="width:100%;height:600px;"></div>
			<span slot="footer" class="dialog-footer">
				<el-button @click="chartDialog1">返回</el-button>
			</span>
		</el-dialog>

		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>

<script>
	import * as echarts from 'echarts'
	import chinaJson from "@/components/echarts/china.json";
	import axios from 'axios';
	import AddOrUpdate from "./add-or-update";
	import baomingshenqingCrossAddOrUpdate from "../baomingshenqing/add-or-update";
	import {
		Loading
	} from 'element-ui';
	export default {
		data() {
			return {
				indexQueryCondition: '',
				searchForm: {
					key: ""
				},
				form:{},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: true,
				chartVisiable1: false,
				line: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":15,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":false,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":4,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":false,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#686DFF","#FDC274","#FE7978","#B797FF","#35DB9F","#FF5F32","#107ED6","#33E3FF","#FF5F32"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"showSymbol":true,"symbol":"emptyCircle","symbolSize":1},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"}},
				bar: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":12,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":4,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#686DFF","#FDC274","#FE7978","#B797FF","#35DB9F","#FF5F32","#107ED6","#33E3FF","#FF5F32"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"barWidth":"auto","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#fff","shadowOffsetY":0,"color":"","shadowBlur":4,"borderWidth":1,"opacity":1,"shadowColor":"#fff"},"colorBy":"data","barCategoryGap":"20%"},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"base":{"animate":false,"interval":2000}},
				pie: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#686DFF","#FDC274","#FE7978","#B797FF","#35DB9F","#FF5F32","#107ED6","#33E3FF","#FF5F32"],"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":14,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":0,"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#fff","shadowOffsetY":0,"color":"","shadowBlur":5,"borderWidth":1,"opacity":1,"shadowColor":"#fff"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#666","color":"inherit","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#666","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
				funnel: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#686DFF","#FDC274","#FE7978","#B797FF","#35DB9F","#FF5F32","#107ED6","#33E3FF","#FF5F32"],"title":{"borderType":"solid","padding":2,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"center","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#666","textShadowColor":"transparent","fontSize":14,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"#ccc","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":5,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"orient":"vertical","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"left","borderWidth":0,"width":"auto","itemWidth":8,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":20,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#fff","shadowOffsetY":0,"color":"","shadowBlur":4,"borderWidth":1,"opacity":1,"shadowColor":"#fff"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#fff","color":"","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#fff","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
				boardBase: {"funnelNum":8,"lineNum":8,"gaugeNum":8,"barNum":8,"pieNum":8},
				gauge: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#686DFF","#FDC274","#FE7978","#B797FF","#35DB9F","#FF5F32","#107ED6","#33E3FF","#FF5F32"],"title":{"top":"top","left":"left","textStyle":{"fontSize":14,"lineHeight":24,"color":"#333","fontWeight":600}},"series":{"pointer":{"offsetCenter":[0,"10%"],"icon":"path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z","width":8,"length":"80%"},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"opacity":0.5,"shadowBlur":1,"shadowColor":"#000"},"roundCap":true},"anchor":{"show":true,"itemStyle":{"color":"inherit"},"size":18,"showAbove":true},"emphasis":{"disabled":false},"progress":{"show":true,"roundCap":true,"overlap":true},"splitNumber":25,"detail":{"formatter":"{value}","backgroundColor":"inherit","color":"#fff","borderRadius":3,"width":20,"fontSize":12,"height":10},"title":{"fontSize":14},"animation":true}},
				addOrUpdateFlag:false,
				baomingshenqingCrossAddOrUpdateFlag: false,
				layouts: ["total","prev","pager","next","sizes","jumper"],
				previewImg: '',
				previewVisible: false,
			};
		},
		created() {
			this.init();
			this.getDataList();
			this.contentStyleChange();
		},
		mounted() {
		},
		filters: {
			htmlfilter: function (val) {
				return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
			}
		},
		computed: {
			tablename(){
				return this.$storage.get('sessionTable')
			},
		},
		components: {
			AddOrUpdate,
			baomingshenqingCrossAddOrUpdate,
		},
		methods: {
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
				
			},
			contentStyleChange() {
				this.contentPageStyleChange()
			},
			// 分页
			contentPageStyleChange(){
				let arr = []

				// if(this.contents.pageTotal) arr.push('total')
				// if(this.contents.pageSizes) arr.push('sizes')
				// if(this.contents.pagePrevNext){
				//   arr.push('prev')
				//   if(this.contents.pagePager) arr.push('pager')
				//   arr.push('next')
				// }
				// if(this.contents.pageJumper) arr.push('jumper')
				// this.layouts = arr.join()
				// this.contents.pageEachNum = 10
			},
			baomingshenqingCrossAddOrUpdateHandler(row,type,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue){
				this.showFlag = false;
				this.addOrUpdateFlag = false;
				this.baomingshenqingCrossAddOrUpdateFlag = true;
				this.$storage.set('crossObj',row);
				this.$storage.set('crossTable','shequhuodong');
				this.$storage.set('statusColumnName',statusColumnName);
				this.$storage.set('statusColumnValue',statusColumnValue);
				this.$storage.set('tips',tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$message({
								message: tips,
								type: "success",
								duration: 1500,
								onClose: () => {
									this.getDataList();
								}
							});
							this.showFlag = true;
							this.baomingshenqingCrossAddOrUpdateFlag = false;
							return;
						}
					}
				}
				this.$nextTick(() => {
					this.$refs.baomingshenqingCrossaddOrUpdate.init(row.id,type);
				});
			},


			// 统计接口
			chartDialog1() {
				this.chartVisiable1 = !this.chartVisiable1;
				this.$nextTick(()=>{
					var baomingrenshuChart1 = echarts.init(document.getElementById("baomingrenshuChart1"),'macarons');
					this.$http({
						url: `shequhuodong/value/huodongmingcheng/baomingrenshu`,
						method: "get",
					}).then(({ data }) => {
						if (data && data.code === 0) {
							let res = data.data;
							let xAxis = [];
							let yAxis = [];
							let pArray = []
							for(let i=0;i<res.length;i++){
								if(this.boardBase&&i==this.boardBase.lineNum){
									break;
								}
								xAxis.push(res[i].huodongmingcheng);
								yAxis.push(parseFloat((res[i].total)));
								pArray.push({
									value: parseFloat((res[i].total)),
									name: res[i].huodongmingcheng
								})
							}
							var option = {};
							let titleObj = this.line.title
							titleObj.text = '报名人数统计'
							
							const legendObj = this.line.legend
							let tooltipObj = { trigger: 'item',formatter: '{b} : {c}'}
							tooltipObj = Object.assign(tooltipObj , this.line.tooltip?this.line.tooltip:{})
							let xAxisObj = this.line.xAxis
							xAxisObj.type = 'category'
							xAxisObj.boundaryGap = false
							xAxisObj.data = xAxis
							
							let yAxisObj = this.line.yAxis
							yAxisObj.type = 'value'
							const gridObj = this.line.grid
							let seriesObj = {
								data: yAxis,
								type: 'line',
								areaStyle: {},
								smooth: true
							}
							seriesObj = Object.assign(seriesObj , this.line.series)
							option = {
								backgroundColor: this.line.backgroundColor,
								color: this.line.color,
								title: titleObj,
								legend: legendObj,
								grid: gridObj,
								tooltip: tooltipObj,
								xAxis: xAxisObj,
								yAxis: yAxisObj,
								series: [seriesObj]
							};
							// 使用刚指定的配置项和数据显示图表。
							baomingrenshuChart1.setOption(option);
							  //根据窗口的大小变动图表
							window.onresize = function() {
								baomingrenshuChart1.resize();
							};
						}else{
							this.$message({
								message: data.msg,
								type: "warning",
								duration: 1500,
							})
						}
					});
				})
			},



			init () {
			},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},

			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				let params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: 'id',
					order: 'desc',
				}
 				if(this.searchForm.huodongmingcheng!='' && this.searchForm.huodongmingcheng!=undefined){
					params['huodongmingcheng'] = '%' + this.searchForm.huodongmingcheng + '%'
				}
				if(this.searchForm.huodongleixing!='' && this.searchForm.huodongleixing!=undefined){
					params['huodongleixing'] = this.searchForm.huodongleixing
				}
 				if(this.searchForm.didian!='' && this.searchForm.didian!=undefined){
					params['didian'] = '%' + this.searchForm.didian + '%'
				}
				let user = JSON.parse(this.$storage.getObj('userForm'))
				this.$http({
					url: "shequhuodong/page",
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			// 添加/修改
			addOrUpdateHandler(id,type) {
				this.showFlag = false;
				this.addOrUpdateFlag = true;
				this.crossAddOrUpdateFlag = false;
				if(type!='info'&&type!='msg'){
					type = 'else';
				}
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(id,type);
				});
			},
			// 查看评论
			disscussListHandler(id,type) {
				this.$router.push({path:'/discussshequhuodong',query:{refid:id}});
			},
			// 删除
			async deleteHandler(id ) {
				var ids = id? [Number(id)]: this.dataListSelections.map(item => {
					return Number(item.id);
				});
				await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(async () => {
					await this.$http({
						url: "shequhuodong/delete",
						method: "post",
						data: ids
					}).then(async ({ data }) => {
						if (data && data.code === 0) {
							for(let x in ids){
								await this.$http.get('storeup/list',{params: {
									page: 1,
									limit: 100,
									refid: ids[x],
									tablename: 'shequhuodong'
								}}).then(async obj=>{
									if(obj.data&&obj.data.code==0){
										if(obj.data.data.list.length){
											let arr = []
											for(let i in obj.data.data.list){
												arr.push(obj.data.data.list[i].id)
											}
											await this.$http.post('storeup/delete',arr).then(()=>{})
										}
									}
								})
							}
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
			
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			},


		}

	};
</script>
<style lang="scss" scoped>
	
	.center-form-pv {
		.el-date-editor.el-input {
			width: auto;
		}
	}
	
	.el-input {
		width: auto;
	}
	
	// form
	.center-form-pv .el-input {
		width: 100%;
	}
	.center-form-pv .el-input /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 10px;
		padding: 0 12px;
		color: #666;
		width: 170px;
		font-size: 16px;
		height: 40px;
	}
	.center-form-pv .el-select {
		width: 100%;
	}
	.center-form-pv .el-select /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 10px;
		padding: 0 10px;
		color: #666;
		width: 170px;
		font-size: 16px;
		height: 40px;
	}
	.center-form-pv .el-date-editor {
		width: 100%;
	}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 10px;
		padding: 0 10px 0 30px;
		color: #666;
		width: 170px;
		font-size: 16px;
		height: 40px;
	}
	
	.center-form-pv .search {
		border: 0;
		cursor: pointer;
		border-radius: 10px;
		padding: 0 10px;
		color: #fff;
		background: #107ED6;
		width: auto;
		font-size: 16px;
		height: 40px;
	}
	
	.center-form-pv .search:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .add {
		border: 0px solid #ddd;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 14px;
		margin: 4px;
		color: #fff;
		background: #107ED6;
		width: auto;
		font-size: 14px;
		height: 34px;
	}
	
	.center-form-pv .actions .add:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .del {
		border: 1px solid #107ED6;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 14px;
		margin: 4px;
		color: #107ED6;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 34px;
	}
	
	.center-form-pv .actions .del:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .statis {
		border: 1px solid #107ED6;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 10px;
		margin: 4px;
		color: #107ED6;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 34px;
	}
	
	.center-form-pv .actions .statis:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .btn18 {
		border: 1px solid #107ED6;
		cursor: pointer;
		border-radius: 5px;
		padding: 0 14px;
		margin: 4px;
		color: #107ED6;
		background: #fff;
		width: auto;
		font-size: 14px;
		height: 34px;
	}
	
	.center-form-pv .actions .btn18:hover {
		opacity: 0.8;
	}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
		color: #999;
		font-weight: 500;
		width: 100%;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
		background: #fff;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
		padding: 4px 0;
		background: none;
		border-color: #eee;
		border-width: 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
		padding: 0 0 0 5px;
		word-wrap: normal;
		color: #666;
		white-space: normal;
		font-weight: bold;
		display: flex;
		vertical-align: middle;
		font-size: 14px;
		line-height: 24px;
		text-overflow: ellipsis;
		word-break: break-all;
		width: 100%;
		align-items: center;
		position: relative;
		min-width: 110px;
	}

	.el-table /deep/ .el-table__body-wrapper {
		position: relative;
	}
	.el-table /deep/ .el-table__body-wrapper tbody {
		width: 100%;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
		background: #fff;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #333;
		background: #fff;
		font-size: inherit;
		border-color: #eee;
		border-width: 0;
		border-style: solid;
		text-align: left;
	}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
		padding: 4px 0;
		color: #333;
		background: #f0f0f0;
		border-color: #eee;
		border-width: 0;
		border-style: solid;
		text-align: left;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #333;
		background: #fff;
		font-size: inherit;
		border-color: #eee;
		border-width: 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
		padding: 0 0 0 5px;
		overflow: hidden;
		word-break: break-all;
		white-space: normal;
		font-size: inherit;
		line-height: 24px;
		text-overflow: ellipsis;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
		border: 2px solid #1B1265;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 14px;
		margin: 0 5px 5px 0;
		color: #1B1265;
		background: none;
		width: auto;
		font-size: 14px;
		border-width: 2px 2px 4px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
		border: 2px solid #1B1265;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 14px;
		margin: 0 5px 5px 0;
		color: #1B1265;
		background: none;
		width: auto;
		font-size: 14px;
		border-width: 2px 2px 4px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
		border: 2px solid #1B1265;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 14px;
		margin: 0 5px 5px 0;
		color: #1B1265;
		background: none;
		width: auto;
		font-size: 14px;
		border-width: 2px 2px 4px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
		border: 2px solid #1B1265;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 14px;
		margin: 0 5px 5px 0;
		color: #1B1265;
		background: none;
		width: auto;
		font-size: 14px;
		border-width: 2px 2px 4px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
		opacity: 0.8;
	}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
		margin: 0 10px 0 0;
		color: #666;
		font-weight: 400;
		display: inline-block;
		vertical-align: top;
		font-size: inherit;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev {
		border: none;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		min-width: 35px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-next {
		border: none;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		min-width: 35px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		height: 48px;
	}

	.main-content .el-pagination /deep/ .el-pager {
		padding: 0;
		margin: 0;
		display: inline-block;
		vertical-align: top;
	}

	.main-content .el-pagination /deep/ .el-pager .number {
		cursor: pointer;
		border-radius: 2px;
		padding: 0 20px;
		margin: -6px -5px 0;
		color: #666;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 62px;
		text-align: center;
		height: 56px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
		cursor: pointer;
		border-radius: 2px;
		padding: 0 20px;
		margin: -6px -5px 0;
		color: #fff;
		background: url(http://codegen.caihongy.cn/20240805/c2bc28aaef6e4abe81601f536c3c929e.png) no-repeat center top / 100% 100%;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 62px;
		text-align: center;
		height: 56px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
		cursor: default;
		border-radius: 2px;
		padding: 0 20px;
		margin: -6px -5px 0;
		color: #fff;
		background: url(http://codegen.caihongy.cn/20240805/c2bc28aaef6e4abe81601f536c3c929e.png) no-repeat center top / 100% 100%;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 62px;
		text-align: center;
		height: 56px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 14px;
		line-height: 48px;
		text-align: center;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 14px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	// list one
	.one .list1-view {
		border: 2px solid #1B1265;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 14px;
		margin: 0 10px 5px 0;
		color: #1B1265;
		background: none;
		width: auto;
		font-size: 14px;
		border-width: 2px 2px 4px;
		height: 32px;
	}
	
	.one .list1-view:hover {
		opacity: 0.8;
	}
	
	.one .list1-edit {
		border: 2px solid #1B1265;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 14px;
		margin: 0 10px 5px 0;
		color: #1B1265;
		background: none;
		width: auto;
		font-size: 14px;
		border-width: 2px 2px 4px;
		height: 32px;
	}
	
	.one .list1-edit:hover {
		opacity: 0.8;
	}
	
	.one .list1-del {
		border: 2px solid #1B1265;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 14px;
		margin: 0 10px 5px 0;
		color: #1B1265;
		background: none;
		width: auto;
		font-size: 14px;
		border-width: 2px 2px 4px;
		height: 32px;
	}
	
	.one .list1-del:hover {
		opacity: 0.8;
	}
	
	.one .list1-btn8 {
		border: 2px solid #1B1265;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 14px;
		margin: 0 10px 5px 0;
		color: #1B1265;
		background: none;
		width: auto;
		font-size: 14px;
		border-width: 2px 2px 4px;
		height: 32px;
	}
	
	.one .list1-btn8:hover {
		opacity: 0.8;
	}
	
	.main-content .el-table .el-switch {
		display: inline-flex;
		vertical-align: middle;
		line-height: 30px;
		position: relative;
		align-items: center;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--left {
		cursor: pointer;
		margin: 0 10px 0 0;
		color: #333;
		font-weight: 500;
		display: inline-block;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--right {
		cursor: pointer;
		margin: 0 0 0 10px;
		color: #333;
		font-weight: 500;
		display: inline-block;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core {
		border: 1px solid #75c0d6;
		cursor: pointer;
		border-radius: 15px;
		margin: 0;
		background: #75c0d6;
		display: inline-block;
		width: 42px;
		box-sizing: border-box;
		transition: border-color .3s,background-color .3s;
		height: 20px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core::after {
		border-radius: 100%;
		top: 1px;
		left: 1px;
		background: #fff;
		width: 16px;
		position: absolute;
		transition: all .3s;
		height: 16px;
	}
	.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
		margin: 0 0 0 -18px;
		left: 100%;
	}
	
	.main-content .el-table .el-rate /deep/ .el-rate__item {
		cursor: pointer;
		display: inline-block;
		vertical-align: middle;
		font-size: 0;
		position: relative;
	}
	.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
		margin: 0 3px;
		display: inline-block;
		font-size: 18px;
		position: relative;
		transition: .3s;
	}

</style>
