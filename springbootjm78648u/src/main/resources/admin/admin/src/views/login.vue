<template>
	<div>
		<div class="login-container">
			<el-form class="login_form animate__animated animate__zoomIn">
				<div class="login_form2">
					<div class="title-container">基于微信小程序的智慧社区娱乐服务管理平台</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							账号
						</div>
						<input placeholder="请输入账号" name="username" type="text" v-model="rulesForm.username">
					</div>
					<div v-if="loginType==1" class="list-item">
						<div class="lable">
							密码
						</div>
						<div class="password-box">
							<input placeholder="请输入密码" name="password" :type="showPassword?'text':'password'" v-model="rulesForm.password">
							<span class="icon iconfont" :class="showPassword?'icon-liulan13':'icon-liulan17'" @click="showPassword=!showPassword"></span>
						</div>
					</div>

					<div class="list-item select" v-if="roles.length>1">
						<div class="lable">
							角色
						</div>
						<el-select v-model="rulesForm.role" placeholder="请选择角色">
							<el-option v-if="loginType==1||(loginType==2&&item.roleName!='管理员')" v-for="item in roles" :key="item.roleName" :label="item.roleName" :value="item.roleName" />
						</el-select>
					</div>

		
					<div class="login-btn">
						<div class="login-btn1">
							<el-button v-if="loginType==1" type="primary" @click="login()" class="loginInBt">登录</el-button>
						</div>
						<div class="login-btn2">
						</div>
						<div class="login-btn3">
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>
<script>
	import 'animate.css'
	import menu from "@/utils/menu";
	export default {
		data() {
			return {
				verifyCheck2: false,
				flag: false,
				baseUrl:this.$base.url,
				loginType: 1,
				rulesForm: {
					username: "",
					password: "",
					role: "",
				},
				menus: [],
				roles: [],
				tableName: "",
				showPassword: false,
			};
		},
		mounted() {
			let menus = menu.list();
			this.menus = menus;

			for (let i = 0; i < this.menus.length; i++) {
				if (this.menus[i].hasBackLogin=='是') {
					this.roles.push(this.menus[i])
				}
			}

		},
		created() {

		},
		destroyed() {
		},
		components: {
		},
		methods: {

			//注册
			register(tableName){
				this.$storage.set("loginTable", tableName);
				this.$router.push({path:'/register',query:{pageFlag:'register'}})
			},
			// 登陆
			login() {

				if (!this.rulesForm.username) {
					this.$message.error("请输入用户名");
					return;
				}
				if (!this.rulesForm.password) {
					this.$message.error("请输入密码");
					return;
				}
				if(this.roles.length>1) {
					if (!this.rulesForm.role) {
						this.$message.error("请选择角色");
						return;
					}

					let menus = this.menus;
					for (let i = 0; i < menus.length; i++) {
						if (menus[i].roleName == this.rulesForm.role) {
							this.tableName = menus[i].tableName;
						}
					}
				} else {
					this.tableName = this.roles[0].tableName;
					this.rulesForm.role = this.roles[0].roleName;
				}
		
				this.loginPost()
			},
			loginPost() {
				this.$http({
					url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
					method: "post"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.$storage.set("Token", data.token);
						this.$storage.set("role", this.rulesForm.role);
						this.$storage.set("sessionTable", this.tableName);
						this.$storage.set("adminName", this.rulesForm.username);
						this.$nextTick(()=>{
							this.$http({
								url: this.tableName + '/session',
								method: "get"
							}).then(({
								data
							}) => {
								if (data && data.code === 0) {
									if(this.tableName == 'jumin') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'users') {
										this.$storage.set('headportrait',data.data.image)
									}
									this.$storage.set('userForm',JSON.stringify(data.data))
									this.$storage.set('userid',data.data.id);
								} else {
									let message = this.$message
									message.error(data.msg);
								}
								this.$router.replace({ path: "/" });
							});
						})
					} else {
						this.$message.error(data.msg);
					}
				});
			},
		}
	}
</script>

<style lang="scss" scoped>
.login-container {
	min-height: 100vh;
	position: relative;
	background-repeat: no-repeat;
	background-position: center center;
	background-size: cover;
	background-repeat: no-repeat;
	background-size: cover;
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	background-image: url(http://codegen.caihongy.cn/20240928/55113b15649548ff80812e93c861a31f.png);
	background-position: center center;

	.login_form {
		padding: 70px 40px 60px;
		margin: 0;
		z-index: 1000;
		display: flex;
		flex-wrap: wrap;
		border-radius: 10px;
		box-shadow: 5px 4px 5px 0px rgba(0,0,0,0.2);
		flex-direction: column;
		background: rgba(255,255,255,.5);
		width: 40%;
		align-items: flex-start;
		position: relative;
		height: auto;
		.login_form2 {
			border-radius: 10px;
			padding: 40px 80px;
			background: #1B1265;
			width: 100%;
		}
		.title-container {
			padding: 0 40px;
			margin: 0 0 20px 0;
			color: #fff;
			top: -60px;
			left: 0;
			background: none;
			font-weight: 600;
			width: 100%;
			font-size: 28px;
			line-height: 40px;
			position: absolute;
			text-align: center;
		}
		.list-item {
			border-radius: 5px;
			padding: 0;
			margin: 0 0 30px;
			overflow: hidden;
			background: #fff;
			display: flex;
			width: calc(100% - 0px);
			align-items: center;
			position: relative;
			.lable {
				color: #fff;
				background: linear-gradient( 360deg, #FF5F32 0%, #FF5F32 74%, #FFB19B 100%);
				width: 80px;
				font-size: 16px;
				line-height: 40px;
				text-align: center;
			}
			input {
				border: 0px solid #efeff7;
				border-radius: 4px;
				padding: 0 10px;
				color: #000;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			input:focus {
				border: 0px solid #efeff7;
				padding: 0 10px;
				outline: none;
				color: #000;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.password-box {
				display: flex;
				width: 100%;
				position: relative;
				align-items: center;
				input {
					border: 0px solid #efeff7;
					padding: 0 10px;
					color: #000;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				input:focus {
					border: 0px solid #efeff7;
					padding: 0 10px;
					outline: none;
					color: #000;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.iconfont {
					cursor: pointer;
					z-index: 1;
					color: #000;
					top: 0;
					font-size: 16px;
					line-height: 44px;
					position: absolute;
					right: 5px;
				}
			}
			input::placeholder {
				color: #999;
				font-size: 14px;
			}
			/deep/ .el-select {
				width: 100%;
			}
			/deep/ .el-select .el-input__inner {
				border: 0px solid rgba(64, 158, 255, 1);
				padding: 0 10px;
				color: #000;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			/deep/ .el-select .is-focus .el-input__inner {
				border: 0px solid rgba(85, 170, 0, 1.0);
				padding: 0 10px;
				color: #000;
				width: 288px;
				font-size: 14px;
				height: 40px;
			}
			/deep/ .el-select .el-input__inner::placeholder{
				color: #999;
				font-size: 14px;
			}
		}
		.login-btn {
			margin: 20px auto 0;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			.login-btn1 {
				margin: 0 0 20px;
				display: flex;
				width: 100%;
				justify-content: center;
				order: 2;
			}
			.login-btn2 {
				display: flex;
				width: 100%;
				justify-content: space-between;
				align-items: center;
				flex-wrap: wrap;
				order: 3;
			}
			.login-btn3 {
				margin: 10px 0;
				width: 100%;
				order: 1;
			}
			.loginInBt {
				border: 0px solid rgba(0, 0, 0, 1);
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 0 0 10px;
				color: #fff;
				background: linear-gradient( 360deg, #FF5F32 0%, #FF5F32 74%, #FFB19B 100%);
				font-weight: 600;
				width: 35%;
				font-size: 24px;
				min-width: 68px;
				height: 50px;
			}
			.loginInBt:hover {
				opacity: 0.8;
			}
			.register {
				border: 1px solid#fff;
				cursor: pointer;
				border-radius: 4px;
				padding: 0;
				margin: 0 0 10px;
				color: #fff;
				background: transparent;
				width: 30%;
				font-size: 18px;
				text-align: center;
				height: 40px;
			}
			.register:hover {
				opacity: 0.8;
			}
			.forget {
				border: 0;
				cursor: pointer;
				border-radius: 0;
				padding: 0;
				margin: 0 0 20px;
				color: #fff;
				background: none;
				width: 100%;
				font-size: 17px;
				text-align: center;
				height: 34px;
			}
			.forget:hover {
				color: #0d6efd;
				opacity: 1;
			}
		}
	}
}

</style>
