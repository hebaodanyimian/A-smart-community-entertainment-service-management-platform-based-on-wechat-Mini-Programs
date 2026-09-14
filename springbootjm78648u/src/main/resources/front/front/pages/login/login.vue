<template>
	<view class="content">
		<view class="box" :style='{"width":"100%","padding":"0 60rpx","alignItems":"center","background":"url(http://codegen.caihongy.cn/20250222/87b074c3c23b4b738a1652e8b483d13b.png) no-repeat center bottom / 100% 100% ","display":"flex","height":"100%"}'>
			<view :style='{"width":"100%","padding":"40rpx","position":"relative","borderRadius":"20rpx","background":"none","height":"auto"}'>
				<image :style='{"width":"160rpx","margin":"0 auto 24rpx auto","borderRadius":"8rpx","display":"block","height":"160rpx"}' src="http://codegen.caihongy.cn/20250222/98ef1dbfdada496b97105c8d3c6f4aed.png" mode="aspectFill"></image>
				<view v-if="loginType==1" :style='{"border":"none","margin":"0 0 40rpx 0","alignItems":"center","flexWrap":"wrap","display":"flex","width":"100%","height":"auto"}' class="uni-form-item uni-column">
					<view :style='{"padding":"0 0 0 40rpx","color":"#232323","borderRadius":"0","textAlign":"left","background":"none","flex":"none","width":"100%","lineHeight":"50rpx","fontSize":"28rpx","height":"50rpx"}' class="label">账号：</view>
					<input v-model="username" :style='{"padding":"0px 24rpx","margin":"0px","borderColor":"#d0d0d0","color":"#000","borderRadius":"0","flex":"1","background":"none","borderWidth":"0 0 2rpx 0","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}' type="text" class="uni-input" name="" placeholder="请输入账号" />
				</view>
				<view v-if="loginType==1" :style='{"border":"none","margin":"0 0 40rpx 0","alignItems":"center","flexWrap":"wrap","display":"flex","width":"100%","height":"auto"}' class="uni-form-item uni-column">
					<view :style='{"padding":"0 0 0 40rpx","color":"#232323","borderRadius":"0","textAlign":"left","background":"none","flex":"none","width":"100%","lineHeight":"50rpx","fontSize":"28rpx","height":"50rpx"}' class="label">密码：</view>
					<input v-model="password" password :style='{"padding":"0px 24rpx","margin":"0px","borderColor":"#d0d0d0","color":"#000","borderRadius":"0","flex":"1","background":"none","borderWidth":"0 0 2rpx 0","fontSize":"28rpx","borderStyle":"solid","height":"80rpx"}' type="password" class="uni-input" name="" placeholder="请输入密码" />
				</view>
				<view v-if="roleNum>1" :style='{"border":"none","margin":"0 0 40rpx 0","alignItems":"center","flexWrap":"wrap","display":"flex","width":"100%","height":"auto"}'>
					<view :style='{"padding":"0 0 0 40rpx","color":"#232323","borderRadius":"0","textAlign":"left","background":"none","flex":"none","width":"100%","lineHeight":"50rpx","fontSize":"28rpx","height":"50rpx"}' class="label">用户类型：</view>
					<picker @change="optionsChange" :value="index" :range="options" :style='{"padding":"0 20rpx","lineHeight":"80rpx","fontSize":"28rpx","color":"#74868B","flex":"1"}'>
						<view class="uni-picker-type">{{options[index]}}</view>
					</picker>
				</view>
				

				
				<button v-if="loginType==1" class="btn-submit" @tap="onLoginTap" type="primary" :style='{"border":"0","padding":"0px","margin":"0 0 24rpx 0","color":"#fff","borderRadius":"8rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"100%","lineHeight":"80rpx","fontSize":"32rpx","fontWeight":"600","height":"80rpx"}'>登陆</button>
				<!-- #ifdef MP-WEIXIN -->
				<button v-if="loginType==1" class="btn-submit" open-type="getUserInfo" @getuserinfo="getUserInfo" type="primary" :style='{"border":"0","padding":"0px","margin":"0 0 24rpx 0","color":"#fff","borderRadius":"8rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"100%","lineHeight":"80rpx","fontSize":"32rpx","fontWeight":"600","height":"80rpx"}'>微信登录</button>
				<!-- #endif -->
				<button v-if="loginType==2" class="btn-submit" @tap="onFaceLoginTap" type="primary" :style='{"border":"0","padding":"0px","margin":"0 0 24rpx 0","color":"#fff","borderRadius":"8rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"100%","lineHeight":"80rpx","fontSize":"32rpx","fontWeight":"600","height":"80rpx"}'>人脸识别登录</button>
				<view class="links" :style='{"width":"100%","margin":"0 0 24rpx 0","flexWrap":"wrap","display":"flex","height":"auto"}'>
					<view class="link-highlight" @tap="onRegisterTap('jumin')" :style='{"color":"#74868B","padding":"0 8rpx","fontSize":"28rpx"}'>注册居民</view>
				</view>
				
				<view class="idea1" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea1</view>
				<view class="idea2" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea2</view>
				<view class="idea3" :style='{"width":"100%","background":"red","display":"none","height":"80rpx"}'>idea3</view>
			</view>
		</view>
	</view>
</template>

<script>
	import menu from '@/utils/menu'
	export default {
		data() {
			return {
				username: '',
				password: '',
                loginType:1,
				codes: [{
				  num: 1,
				  color: '#000',
				  rotate: '10deg',
				  size: '16px'
				}, {
				  num: 2,
				  color: '#000',
				  rotate: '10deg',
				  size: '16px'
				}, {
				  num: 3,
				  color: '#000',
				  rotate: '10deg',
				  size: '16px'
				}, {
				  num: 4,
				  color: '#000',
				  rotate: '10deg',
				  size: '16px'
				}],
				options: [
					'请选择登录用户类型',
				],
                optionsValues: [
					'',
                    'jumin',
				],
				index: 0,
				roleNum:0,

				// #ifdef MP-WEIXIN
				code: '',
				// #endif
			}
		},
		onLoad() {
			let options = ['请选择登录用户类型'];
			let menus = menu.list();
			this.menuList = menus;
			for(let i=0;i<this.menuList.length;i++){
				if(this.menuList[i].hasFrontLogin=='是'){
					options.push(this.menuList[i].roleName);
					this.roleNum++;
				}
			}
			if(this.roleNum==1) {
				this.index = 1;
			}	
			this.options = options;
			this.styleChange()
		},
		onShow() {
			// #ifdef MP-WEIXIN
			let that = this
			uni.login({
				provider: 'weixin',
				success(res) {
					that.code = res.code
				}
			})
			// #endif
		},
		mounted() {
		},
		methods: {
			// #ifdef MP-WEIXIN
			async getUserInfo(e) {
				let that = this
				if (!this.optionsValues[this.index]) {
					this.$utils.msg('请选择登录用户类型')
					return
				}
				if (e.detail.errMsg === "getUserInfo:ok") {
					uni.showModal({
						content:'是否使用微信授权登录？',
						success:async (rs)=> {
							if(rs.confirm){
								let params = {
									code: that.code,
									encryptedData: e.detail.encryptedData,
									iv: e.detail.iv,
									rawData: e.detail.rawData,
									signature: e.detail.signature
								}
								let res = await that.$api.wxlogin(that.optionsValues[that.index], params)
								uni.removeStorageSync("useridTag");
								uni.setStorageSync("appToken", res.token);
								uni.setStorageSync("nickname", res.username);
								uni.setStorageSync("nowTable", `${that.optionsValues[that.index]}`);
								res = await that.$api.session(`${that.optionsValues[that.index]}`);
								if (res.data.touxiang) {
									uni.setStorageSync('headportrait', res.data.touxiang);
								} else if (res.data.headportrait) {
									uni.setStorageSync('headportrait', res.data.headportrait);
								}
								uni.setStorageSync('userSession',JSON.stringify(res.data))
								// 保存用户id
								uni.setStorageSync("appUserid", res.data.id);
								if (res.data.vip) {
									uni.setStorageSync("vip", res.data.vip);
								}
								uni.setStorageSync("appRole", `${that.options[that.index]}`);
								that.$utils.tab('../index/index');
							}
						}
					})
					
				}
			},
			// #endif
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.uni-input .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.loginFrom.content.input.backgroundColor
					// })
				})
			},
			onRegisterTap(tableName) {
                uni.setStorageSync("loginTable", tableName);
				this.$utils.jump('../register/register')
			},
			async onLoginTap() {
                if (!this.username) {
					this.$utils.msg('请输入用户名')
					return
				}
                if (!this.password) {
					this.$utils.msg('请输入用户密码')
					return
				}
                if (!this.optionsValues[this.index]) {
					this.$utils.msg('请选择登录用户类型')
					return
				}

				this.loginPost()

			},
			async loginPost() {
				
				let res = await this.$api.login(`${this.optionsValues[this.index]}`, {
					username: this.username,
					password: this.password
				});
				uni.removeStorageSync("useridTag");
				uni.setStorageSync("appToken", res.token);
				uni.setStorageSync("nickname",this.username);
				uni.setStorageSync("nowTable", `${this.optionsValues[this.index]}`);
				res = await this.$api.session(`${this.optionsValues[this.index]}`);
				if(res.data.touxiang) {
				    uni.setStorageSync('headportrait', res.data.touxiang);
				} else if(res.data.headportrait) {
				    uni.setStorageSync('headportrait', res.data.headportrait);
				}
				uni.setStorageSync('userSession',JSON.stringify(res.data))
				// 保存用户id
				uni.setStorageSync("appUserid", res.data.id);
				if(res.data.vip) {
					uni.setStorageSync("vip", res.data.vip);
				}
				uni.setStorageSync("appRole", `${this.options[this.index]}`);
				this.$utils.tab('../index/index');
			},
			optionsChange(e) {
				this.index = e.target.value
			}
		}
	}
</script>

<style lang="scss" scoped>
	page {
		height: 100%;
	}
	
	.content {
		height: 100%;
		box-sizing: border-box;
	}
	
</style>
