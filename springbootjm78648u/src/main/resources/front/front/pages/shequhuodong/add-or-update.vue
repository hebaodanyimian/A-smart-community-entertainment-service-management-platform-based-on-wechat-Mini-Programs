<template>
<view class="content">
	<view :style='{"minHeight":"100%","width":"100%","padding":"0","position":"relative","background":"#e9e9e9","height":"auto"}'>
		<form :style='{"width":"100%","padding":"24rpx","background":"none","display":"block","height":"auto"}' class="app-update-pv">
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">活动名称</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.huodongmingcheng" v-model="ruleForm.huodongmingcheng" placeholder="活动名称"  type="text"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="" @tap="huodongfengmianTap">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">活动封面</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-if="ruleForm.huodongfengmian" :src="baseUrl+ruleForm.huodongfengmian.split(',')[0]" mode="aspectFill"></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' class="avator" v-else src="../../static/gen/upload.png" mode="aspectFill"></image>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">活动类型</view>
				<picker :disabled="ro.huodongleixing" :style='{"width":"100%","flex":"1","height":"auto"}' @change="huodongleixingChange" :value="huodongleixingIndex" :range="huodongleixingOptions">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"28rpx","color":"#000"}' class="uni-input">{{ruleForm.huodongleixing?ruleForm.huodongleixing:"请选择活动类型"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">时间</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.shijian" v-model="ruleForm.shijian" placeholder="时间"  type="text"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">报名人数</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.baomingrenshu" v-model.number="ruleForm.baomingrenshu" placeholder="报名人数" type="number"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">活动主题</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.huodongzhuti" v-model="ruleForm.huodongzhuti" placeholder="活动主题"  type="text"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">地点</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.didian" v-model="ruleForm.didian" placeholder="地点"  type="text"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">发布日期</view>
				<picker  :disabled="ro.faburiqi" :style='{"width":"100%","flex":"1","height":"auto"}' mode="date" :value="ruleForm.faburiqi" @change="faburiqiChange">
					<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"28rpx","color":"#000"}' class="uni-input">{{ruleForm.faburiqi?ruleForm.faburiqi:"请选择发布日期"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">账号</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.zhanghao" v-model="ruleForm.zhanghao" placeholder="账号"  type="text"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">组织者</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.xingming" v-model="ruleForm.xingming" placeholder="组织者"  type="text"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">手机号码</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' :disabled="ro.shoujihaoma" v-model="ruleForm.shoujihaoma" placeholder="手机号码"  type="text"></input>
			</view>
                  
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">注意事项</view>
				<textarea :style='{"border":"none","padding":"24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"240rpx"}' v-model="ruleForm.zhuyishixiang" placeholder="注意事项" :maxlength="-1"></textarea>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#ccc","borderWidth":"0 0 2rpx 0","width":"100%","borderStyle":"solid","height":"auto"}' class="">
				<view :style='{"width":"100%","lineHeight":"80rpx","fontSize":"28rpx","color":"#333","fontWeight":"500"}' class="title">活动详情</view>
				<xia-editor ref="huodongxiangqing" :style='{"minHeight":"300rpx","border":"2rpx solid #efefef","width":"100%","height":"auto"}' v-model="ruleForm.huodongxiangqing" placeholder="活动详情" @editorChange="huodongxiangqingChange"></xia-editor>
			</view>
			
			<view :style='{"width":"100%","flexWrap":"wrap","display":"flex","height":"auto"}' class="btn" >
				<button :style='{"border":"0","padding":"0px","margin":"0 0 20rpx 0","color":"#fff","borderRadius":"40rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="onSubmitTap" class="bg-red">提交</button>
			</view>
		</form>

	</view>
</view>
</template>

<script>
	import wPicker from "@/components/w-picker/w-picker.vue";
	import xiaEditor from '@/components/xia-editor/xia-editor';
	import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				cross:'',
				ruleForm: {
				huodongmingcheng: '',
				huodongfengmian: '',
				huodongleixing: '',
				shijian: '',
					baomingrenshu: Number('0') ,
				huodongzhuti: '',
				didian: '',
				zhuyishixiang: '',
				huodongxiangqing: '',
				faburiqi: '',
				zhanghao: '',
				xingming: '',
				shoujihaoma: '',
				discussnum: '',
				storeupnum: '',
				},
				huodongleixingOptions: [],
				huodongleixingIndex: 0,
				// 登录用户信息
				user: {},
				ro:{
				   huodongmingcheng : false,
				   huodongfengmian : false,
				   huodongleixing : false,
				   shijian : false,
				   baomingrenshu : false,
				   huodongzhuti : false,
				   didian : false,
				   zhuyishixiang : false,
				   huodongxiangqing : false,
				   faburiqi : false,
				   zhanghao : false,
				   xingming : false,
				   shoujihaoma : false,
				   thumbsupnum : false,
				   crazilynum : false,
				   clicknum : false,
				   discussnum : false,
				   storeupnum : false,
				},
				virtualPay: false,
			}
		},
		components: {
			wPicker,
			xiaEditor,
			multipleSelect,
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},



		},
		async onLoad(options) {
			if(options.virtualPay){
				this.virtualPay = true
			}
			this.ruleForm.faburiqi = this.$utils.getCurDate();
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			
			// ss读取
			this.ruleForm.zhanghao = this.user.zhanghao
			this.ro.zhanghao = true;
			this.ruleForm.xingming = this.user.xingming
			this.ro.xingming = true;
			this.ruleForm.shoujihaoma = this.user.shoujihaoma
			this.ro.shoujihaoma = true;


			// 下拉框
			res = await this.$api.option(`huodongleixing`,`huodongleixing`,{});
			this.huodongleixingOptions = res.data;
			this.huodongleixingOptions.unshift("请选择活动类型");

			// 如果有登录，获取登录后保存的userid
			this.ruleForm.userid = uni.getStorageSync("appUserid")
			if (options.refid) {
				// 如果上一级页面传递了refid，获取改refid数据信息
				this.ruleForm.refid = Number(options.refid);
				this.ruleForm.nickname = uni.getStorageSync("nickname");
			}
			// 如果是更新操作
			if (options.id) {
				this.ruleForm.id = options.id;
				// 获取信息
				res = await this.$api.info(`shequhuodong`, this.ruleForm.id);
				this.ruleForm = res.data;
			}
			// 跨表
			this.cross = options.cross;
			if(options.cross){
				var obj = uni.getStorageSync('crossObj');
				for (var o in obj){
					if(o=='huodongmingcheng'){
						this.ruleForm.huodongmingcheng = obj[o];
						this.ro.huodongmingcheng = true;
						continue;
					}
					if(o=='huodongfengmian'){
						this.ruleForm.huodongfengmian = obj[o].split(",")[0];
						this.ro.huodongfengmian = true;
						continue;
					}
					if(o=='huodongleixing'){
						this.ruleForm.huodongleixing = obj[o];
						this.ro.huodongleixing = true;
						continue;
					}
					if(o=='shijian'){
						this.ruleForm.shijian = obj[o];
						this.ro.shijian = true;
						continue;
					}
					if(o=='baomingrenshu'){
						this.ruleForm.baomingrenshu = obj[o];
						this.ro.baomingrenshu = true;
						continue;
					}
					if(o=='huodongzhuti'){
						this.ruleForm.huodongzhuti = obj[o];
						this.ro.huodongzhuti = true;
						continue;
					}
					if(o=='didian'){
						this.ruleForm.didian = obj[o];
						this.ro.didian = true;
						continue;
					}
					if(o=='zhuyishixiang'){
						this.ruleForm.zhuyishixiang = obj[o];
						this.ro.zhuyishixiang = true;
						continue;
					}
					if(o=='huodongxiangqing'){
						this.ruleForm.huodongxiangqing = obj[o];
						this.ro.huodongxiangqing = true;
						continue;
					}
					if(o=='faburiqi'){
						this.ruleForm.faburiqi = obj[o];
						this.ro.faburiqi = true;
						continue;
					}
					if(o=='zhanghao'){
						this.ruleForm.zhanghao = obj[o];
						this.ro.zhanghao = true;
						continue;
					}
					if(o=='xingming'){
						this.ruleForm.xingming = obj[o];
						this.ro.xingming = true;
						continue;
					}
					if(o=='shoujihaoma'){
						this.ruleForm.shoujihaoma = obj[o];
						this.ro.shoujihaoma = true;
						continue;
					}
					if(o=='thumbsupnum'){
						this.ruleForm.thumbsupnum = obj[o];
						this.ro.thumbsupnum = true;
						continue;
					}
					if(o=='crazilynum'){
						this.ruleForm.crazilynum = obj[o];
						this.ro.crazilynum = true;
						continue;
					}
					if(o=='clicknum'){
						this.ruleForm.clicknum = obj[o];
						this.ro.clicknum = true;
						continue;
					}
					if(o=='discussnum'){
						this.ruleForm.discussnum = obj[o];
						this.ro.discussnum = true;
						continue;
					}
					if(o=='storeupnum'){
						this.ruleForm.storeupnum = obj[o];
						this.ro.storeupnum = true;
						continue;
					}
				}
			}
			this.styleChange()
			this.$forceUpdate()
			if (uni.getStorageSync('raffleType') && uni.getStorageSync('raffleType') != null) {
				uni.removeStorageSync('raffleType')
				setTimeout(() => {
					this.onSubmitTap()
				}, 300)
			}
		},
		methods: {
			huodongxiangqingChange(e) {
				this.ruleForm.huodongxiangqing = e
			},
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.app-update-pv . .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.addUpdateForm.input.content.backgroundColor
					// })
				})
			},

			// 多级联动参数

			faburiqiChange(e) {
				this.ruleForm.faburiqi = e.target.value;
				this.$forceUpdate();
			},


			// 下拉变化
			huodongleixingChange(e) {
				this.huodongleixingIndex = e.target.value
				this.ruleForm.huodongleixing = this.huodongleixingOptions[this.huodongleixingIndex]
			},

			huodongfengmianTap() {
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.huodongfengmian = 'upload/' + res.file;
					_this.$forceUpdate();
					_this.$nextTick(()=>{
						_this.styleChange()
					})
				});
			},

			getUUID () {
				return new Date().getTime();
			},
			async onSubmitTap() {
				let that = this
				//跨表计算判断
				var obj;
				if((!this.ruleForm.huodongmingcheng)){
					this.$utils.msg(`活动名称不能为空`);
					return
				}
				if((!this.ruleForm.huodongleixing)){
					this.$utils.msg(`活动类型不能为空`);
					return
				}
				if((!this.ruleForm.baomingrenshu)){
					this.$utils.msg(`报名人数不能为空`);
					return
				}
				if(this.ruleForm.baomingrenshu&&(!this.$validate.isIntNumer(this.ruleForm.baomingrenshu))){
					this.$utils.msg(`报名人数应输入整数`);
					return
				}
				if((!this.ruleForm.didian)){
					this.$utils.msg(`地点不能为空`);
					return
				}
				if(this.ruleForm.thumbsupnum&&(!this.$validate.isIntNumer(this.ruleForm.thumbsupnum))){
					this.$utils.msg(`赞应输入整数`);
					return
				}
				if(this.ruleForm.crazilynum&&(!this.$validate.isIntNumer(this.ruleForm.crazilynum))){
					this.$utils.msg(`踩应输入整数`);
					return
				}
				if(this.ruleForm.clicknum&&(!this.$validate.isIntNumer(this.ruleForm.clicknum))){
					this.$utils.msg(`点击次数应输入整数`);
					return
				}
				if(this.ruleForm.discussnum&&(!this.$validate.isIntNumer(this.ruleForm.discussnum))){
					this.$utils.msg(`评论数应输入整数`);
					return
				}
				if(this.ruleForm.storeupnum&&(!this.$validate.isIntNumer(this.ruleForm.storeupnum))){
					this.$utils.msg(`收藏数应输入整数`);
					return
				}
				//更新跨表属性
				var crossuserid;
				var crossrefid;
				var crossoptnum;
				if(this.cross){
					var statusColumnName = uni.getStorageSync('statusColumnName');
					var statusColumnValue = uni.getStorageSync('statusColumnValue');
					if(statusColumnName!='') {
						if(!obj) {
							obj = uni.getStorageSync('crossObj');
						}
						if(!statusColumnName.startsWith("[")) {
							for (var o in obj){
								if(o==statusColumnName){
									obj[o] = statusColumnValue;
								}

							}
							var table = uni.getStorageSync('crossTable');
							await this.$api.update(`${table}`, obj);
						} else {
							   crossuserid=Number(uni.getStorageSync('appUserid'));
							   crossrefid=obj['id'];
							   crossoptnum=uni.getStorageSync('statusColumnName');
							   crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
						}
					}
				}
				if(crossrefid && crossuserid) {
					this.ruleForm.crossuserid=crossuserid;
					this.ruleForm.crossrefid=crossrefid;
					let params = {
						page: 1,
						limit:10,
						crossuserid:crossuserid,
						crossrefid:crossrefid,
					}
					let res = await this.$api.list(`shequhuodong`, params);
					if (res.data.total >= crossoptnum) {
						this.$utils.msg(uni.getStorageSync('tips'));
						uni.removeStorageSync('crossCleanType');
						return false;
					} else {
				//跨表计算
						let oet = {}
						if(this.ruleForm.id){
							await this.$api.update(`shequhuodong`, this.ruleForm);
						}else{
							oet = await this.$api.add(`shequhuodong`, this.ruleForm);
						}
						if(this.cross){
							uni.setStorageSync('crossCleanType',true);
						}
						this.$utils.msgBack('提交成功');
					}
				} else {
				//跨表计算
					let oet = {}
					if(this.ruleForm.id){
						await this.$api.update(`shequhuodong`, this.ruleForm);
					}else{
						oet = await this.$api.add(`shequhuodong`, this.ruleForm);
					}
					if(this.cross){
						uni.setStorageSync('crossCleanType',true);
					}
					this.$utils.msgBack('提交成功');
				}
			},
			optionsChange(e) {
				this.index = e.target.value
			},
			bindDateChange(e) {
				this.date = e.target.value
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			toggleTab(str) {
				if(this.ro[str]){
					return false
				}
				this.$refs[str].show();
			},
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
