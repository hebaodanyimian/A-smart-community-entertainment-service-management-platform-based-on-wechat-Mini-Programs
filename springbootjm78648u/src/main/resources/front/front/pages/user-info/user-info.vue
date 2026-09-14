<template>
<view class="content">
	<view class="box" :style='{"minHeight":"100%","width":"100%","padding":"0","position":"relative","background":"url(http://codegen.caihongy.cn/20250222/87b074c3c23b4b738a1652e8b483d13b.png) no-repeat center bottom / 100% 100% ","height":"auto"}'>
		<view :style='{"width":"100%","padding":"24rpx","background":"none","display":"block","height":"auto"}'>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jumin'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}'>账号</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' disabled="true"  v-model="ruleForm.zhanghao" placeholder="账号"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jumin'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}'>姓名</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}'   v-model="ruleForm.xingming" placeholder="姓名"></input>
			</view>
			<view v-if="tableName=='jumin'" :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' class=" select">
				<view :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}' class="title">性别</view>
				<picker :style='{"width":"100%","flex":"1","height":"auto"}'  @change="juminxingbieChange" :value="juminxingbieIndex" :range="juminxingbieOptions">
					<view :style='{"border":"none","padding":"0 40rpx","color":"#000","borderRadius":"40rpx","width":"100%","lineHeight":"80rpx","fontSize":"28rpx"}' class="uni-input picker-select-input">{{ruleForm.xingbie?ruleForm.xingbie:"请选择性别"}}</view>
				</picker>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jumin'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}'>年龄</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}'   v-model="ruleForm.nianling" placeholder="年龄"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jumin'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}'>手机号码</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}'   v-model="ruleForm.shoujihaoma" placeholder="手机号码"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jumin'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}'>家庭住址</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}'   v-model="ruleForm.jiatingzhuzhi" placeholder="家庭住址"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jumin'" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}'>兴趣爱好</view>
				<input :style='{"border":"none","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"40rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}'   v-model="ruleForm.xingquaihao" placeholder="兴趣爱好"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}' v-if="tableName=='jumin'" @tap="jumintouxiangTap" class="">
				<view class="title" :style='{"width":"160rpx","padding":"0 20rpx 0 0","lineHeight":"80rpx","fontSize":"28rpx","color":"#5051B5","textAlign":"right"}'>头像</view>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' v-if="ruleForm.touxiang" style="margin: 0;" class="avator" :src="baseUrl+ruleForm.touxiang" mode=""></image>
				<image :style='{"width":"80rpx","borderRadius":"100%","objectFit":"cover","display":"block","height":"80rpx"}' v-else class="avator" style="margin: 0;" src="../../static/gen/upload.png" mode=""></image>
			</view>
			<view :style='{"width":"100%","flexDirection":"column","justifyContent":"center","display":"flex","height":"auto"}' class="btn">
				<!-- #ifdef MP-WEIXIN -->
				<button open-type="getUserInfo" @getuserinfo="getUserInfo" class="cu-btn lg" :style='{"border":"0","padding":"0px","margin":"40rpx 0","color":"#fff","borderRadius":"20rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}'>{{ruleForm.openid?'解绑':'绑定'}}微信</button>
				<!-- #endif -->
				<button @tap="update()" class="cu-btn lg" :style='{"border":"0","padding":"0px","margin":"40rpx 0","color":"#fff","borderRadius":"20rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}'>保存</button>
				<button @tap="logout()" class="cu-btn lg" :style='{"border":"none","padding":"0px","margin":"0","color":"#000000","borderRadius":"20rpx","background":"#D9D9D9","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}'>退出登录</button>
			</view>
		</view>
	</view>
</view>
</template>

<script>

    import xiaEditor from '@/components/xia-editor/xia-editor';
    import multipleSelect from "@/components/momo-multipleSelect/momo-multipleSelect";
	export default {
		data() {
			return {
				ruleForm: {
				},
				tableName:"",
				juminxingbieOptions: [],
				juminxingbieIndex: 0,
				// #ifdef MP-WEIXIN
				code: '',
				// #endif
			}
		},
        components: {
            multipleSelect,
			xiaEditor
        },
		computed: {
			baseUrl() {
				return this.$base.url;
			}
		},
		async onLoad() {
			let table = uni.getStorageSync("nowTable");
			let that = this
			let res = {}
			this.tableName = table;
			this.getSession()
			// 自定义下拉框值
			if(this.tableName=='jumin'){
				this.juminxingbieOptions = "男,女".split(',');
				this.juminxingbieOptions.forEach((item, index) => {
					if(item==this.ruleForm.xingbie) {
						this.juminxingbieIndex = index;
					}
				});
			}
			// #ifdef MP-WEIXIN
			uni.login({
				provider: 'weixin',
				success(res) {
					that.code = res.code
				}
			})
			// #endif
			this.styleChange()
            this.$forceUpdate()
		},
		methods: {
			// #ifdef MP-WEIXIN
			async getUserInfo(e) {
				let that = this
				if (e.detail.errMsg === "getUserInfo:ok") {
					if(that.ruleForm.openid){
						uni.showModal({
							title: '提示',
							content: '是否解绑微信？',
							success:async (rs)=> {
								if(rs.confirm){
									let res = await that.$api.wxunbind(that.tableName)
									if(res.code==0) that.$utils.msg('解绑成功，可以再次绑定其他账号')
									that.getSession()
								}
							}
						})
						return false
					}
					uni.showModal({
						title: '提示',
						content: '是否绑定微信？',
						success:async (rs)=> {
							if(rs.confirm){
								let params = {
									code: that.code,
									encryptedData: e.detail.encryptedData,
									iv: e.detail.iv,
									rawData: e.detail.rawData,
									signature: e.detail.signature
								}
								let res = await that.$api.wxbind(that.tableName, params)
								if(res.code==0) that.$utils.msg('绑定成功，下次登录可使用微信登录')
								that.getSession()
							}
						}
					})
					
				}
			},
			// #endif
			async getSession(){
				let res = await this.$api.session(this.tableName)
				this.ruleForm = res.data;
				uni.setStorageSync('userSession',JSON.stringify(res.data))
			},
            // 下拉变化
            juminxingbieChange(e) {
                    this.juminxingbieIndex = e.target.value
                    this.ruleForm.xingbie = this.juminxingbieOptions[this.juminxingbieIndex]
            },
			jumintouxiangTap() {
				let _this = this;
				this.$api.upload(function(res) {
					_this.ruleForm.touxiang = 'upload/' + res.file;
					_this.$forceUpdate();
				});
			},

            toggleTab(str) {
                this.$refs[str].show();
            },

			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('. .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.userInfoForm.list.input.backgroundColor
					// })
				})
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			logout() {
				uni.clearStorageSync()
				this.$utils.jump('../login/login');
			},
			// 注册
			async update() {

				if((!this.ruleForm.zhanghao) && `jumin` == this.tableName){
					this.$utils.msg(`账号不能为空`);
					return
				}

				if((!this.ruleForm.mima) && `jumin` == this.tableName){
					this.$utils.msg(`密码不能为空`);
					return
				}

				if((!this.ruleForm.xingming) && `jumin` == this.tableName){
					this.$utils.msg(`姓名不能为空`);
					return
				}


				if(`jumin` == this.tableName && this.ruleForm.nianling&&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
					this.$utils.msg(`年龄应输入整数`);
					return
				}

				if(`jumin` == this.tableName && this.ruleForm.shoujihaoma&&(!this.$validate.isMobile(this.ruleForm.shoujihaoma))){
					this.$utils.msg(`手机号码应输入手机格式`);
					return
				}




				if(`jumin` == this.tableName && this.ruleForm.status&&(!this.$validate.isIntNumer(this.ruleForm.status))){
					this.$utils.msg(`状态应输入整数`);
					return
				}



				let table = uni.getStorageSync("nowTable");
				await this.$api.update(table, this.ruleForm);
				this.$utils.msgBack('修改成功');;
				this.getSession()
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
