<template>
	<div>
		<div class="register-container">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__zoomIn" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">基于微信小程序的智慧社区娱乐服务管理平台</div>
					<el-form-item class="list-item" v-if="tableName=='jumin'">
						<div class="lable" :class="changeRules('zhanghao')?'required':''">账号：</div>
						<el-input  v-model="ruleForm.zhanghao"  autocomplete="off" placeholder="账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jumin'">
						<div class="lable" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input  v-model="ruleForm.mima"  autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jumin'">
						<div class="lable" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jumin'">
						<div class="lable" :class="changeRules('xingming')?'required':''">姓名：</div>
						<el-input  v-model="ruleForm.xingming"  autocomplete="off" placeholder="姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jumin'">
						<div class="lable" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select v-model="ruleForm.xingbie" placeholder="请选择性别" >
							<el-option
								v-for="(item,index) in juminxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jumin'">
						<div class="lable" :class="changeRules('nianling')?'required':''">年龄：</div>
						<el-input  v-model.number="ruleForm.nianling"  autocomplete="off" placeholder="年龄"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jumin'">
						<div class="lable" :class="changeRules('shoujihaoma')?'required':''">手机号码：</div>
						<el-input  v-model="ruleForm.shoujihaoma"  autocomplete="off" placeholder="手机号码"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jumin'">
						<div class="lable" :class="changeRules('jiatingzhuzhi')?'required':''">家庭住址：</div>
						<el-input  v-model="ruleForm.jiatingzhuzhi"  autocomplete="off" placeholder="家庭住址"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jumin'">
						<div class="lable" :class="changeRules('xingquaihao')?'required':''">兴趣爱好：</div>
						<el-input  v-model="ruleForm.xingquaihao"  autocomplete="off" placeholder="兴趣爱好"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='jumin'">
						<div class="lable" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="jumintouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()">已有账号，直接登录</div>
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
            juminxingbieOptions: [],
		};
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='jumin'){
				this.ruleForm = {
					zhanghao: '',
					mima: '',
					xingming: '',
					xingbie: '',
					nianling: '',
					shoujihaoma: '',
					jiatingzhuzhi: '',
					xingquaihao: '',
					touxiang: '',
					status: '',
					openid: '',
					nickname: '',
					avatarurl: '',
				}
			}
			if ('jumin' == this.tableName) {
				this.rules.zhanghao = [{ required: true, message: '请输入账号', trigger: 'blur' }]
			}
			if ('jumin' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('jumin' == this.tableName) {
				this.rules.xingming = [{ required: true, message: '请输入姓名', trigger: 'blur' }]
			}
			this.juminxingbieOptions = "男,女".split(',')
		}
	},
	created() {
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        jumintouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			if((!this.ruleForm.zhanghao) && `jumin` == this.tableName){
				this.$message.error(`账号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `jumin` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `jumin` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.xingming) && `jumin` == this.tableName){
				this.$message.error(`姓名不能为空`);
				return
			}
			if(`jumin` == this.tableName && this.ruleForm.nianling &&(!this.$validate.isIntNumer(this.ruleForm.nianling))){
				this.$message.error(`年龄应输入整数`);
				return
			}
			if(`jumin` == this.tableName && this.ruleForm.shoujihaoma &&(!this.$validate.isMobile(this.ruleForm.shoujihaoma))){
				this.$message.error(`手机号码应输入手机格式`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		}
	}
};
</script>

<style lang="scss" scoped>
.register-container {
	position: relative;
	border-radius: 10px;
	background-repeat: no-repeat;
	background-size: cover;
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	background-image: url(http://codegen.caihongy.cn/20240928/55113b15649548ff80812e93c861a31f.png);
	background-position: center center;
	.rgs-form {
		.rgs-form2 {
		border-radius: 10px;
		padding: 40px 80px;
		background: #1B1265;
		width: 100%;
		}
		padding: 70px 40px 60px;
		margin: 80px 0;
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
		.title {
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
			padding: 0 0 0 0px;
			margin: 0 0 10px;
			width: 100%;
			position: relative;
			height: auto;
			/deep/ .el-form-item__content {
				display: flex;
				align-items: flex-start;
			}
			.lable {
				border-radius: 4px 0 0 4px;
				color: #fff;
				background: linear-gradient( 360deg, #FF5F32 0%, #FF5F32 74%, #FFB19B 100%);
				width: 90px;
				font-size: 16px;
				line-height: 40px;
				text-align: center;
				min-width: 120px;
			}
			.el-input {
				width: 100%;
			}
			.el-input /deep/ .el-input__inner {
				border: 0px solid #efeff7;
				border-radius: 0 4px 4px 0;
				padding: 0 10px;
				color: #666;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-input /deep/ .el-input__inner:focus {
				border: 0px solid #efeff7;
				border-radius: 0 4px 4px 0;
				padding: 0 10px;
				color: #000;
				width: 100%;
				font-size: 14px;
				height: 38px;
			}
			.el-input-number {
				width: 100%;
			}
			.el-input-number /deep/ .el-input__inner {
				text-align: center;
				border: 0px solid #efeff7;
				border-radius: 0 4px 4px 0;
				padding: 0 10px;
				color: #666;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-input-number /deep/ .el-input__inner:focus {
				border: 0px solid #efeff7;
				border-radius: 0 4px 4px 0;
				padding: 0 10px;
				color: #000;
				width: 100%;
				font-size: 14px;
				height: 38px;
			}
			.el-input-number /deep/ .el-input-number__decrease {
				display: none;
			}
			.el-input-number /deep/ .el-input-number__increase {
				display: none;
			}
			.el-select {
				width: 100%;
			}
			.el-select /deep/ .el-input__inner {
				border: 0px solid #efeff7;
				border-radius: 0 4px 4px 0;
				padding: 0 10px;
				color: #000;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-select /deep/ .el-input__inner:focus {
				border: 0px solid #efeff7;
				border-radius: 0 4px 4px 0;
				padding: 0 10px;
				color: #000;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-date-editor {
				width: 100%;
			}
			.el-date-editor /deep/ .el-input__inner {
				border: 0px solid #efeff7;
				border-radius: 0 4px 4px 0;
				padding: 0 10px 0 30px;
				color: #000;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-date-editor /deep/ .el-input__inner:focus {
				border: 0px solid #efeff7;
				border-radius: 0 4px 4px 0;
				padding: 0 10px 0 30px;
				color: #000;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			.el-date-editor.el-input {
				width: 100%;
			}
			/deep/ .el-upload--picture-card {
				background: transparent;
				border: 0;
				border-radius: 0;
				width: auto;
				height: auto;
				line-height: initial;
				vertical-align: middle;
			}
			/deep/ .upload .upload-img {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload-list .el-upload-list__item {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload .el-icon-plus {
				border: 1px solid #efeff7;
				cursor: pointer;
				border-radius: 0px;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 24px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			/deep/ .el-upload__tip {
				color: #666;
				font-size: 15px;
			}
			/deep/ .el-input__inner::placeholder {
				color: #999;
				font-size: 16px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: #fff;
				left: 0;
				position: inherit;
				content: "*";
				order: -1;
			}
			.editor {
				background: #fff;
				width: 100%;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border: 1px solid #efeff7;
				border-radius: 0;
				padding: 0 10px;
				margin: 0;
				color: #000;
				background: #fff;
				flex: 1;
				width: 100%;
				font-size: 14px;
				height: 40px;
			}
			input:focus {
				border: 1px solid #efeff7;
				border-radius: 4px 0 0 4px;
				padding: 0 10px;
				color: #666;
				width: 100%;
				font-size: 14px;
				height: 38px;
			}
			input::placeholder {
				color: #999;
				font-size: 16px;
			}
			button {
				border: 0px solid #efeff7;
				cursor: pointer;
				border-radius: 0 4px 4px 0;
				padding: 0;
				margin: 1px 0 0;
				color: #666;
				background: rgba(255,211,199,1);
				width: 150px;
				font-size: 15px;
				height: 38px;
			}
			button:hover {
				opacity: 0.8;
			}
		}
		.register-btn {
			width: 100%;
		}
		.register-btn1 {
			margin: 40px 0 20px;
			display: flex;
			width: 100%;
			justify-content: center;
			order: 2;
		}
		.register-btn2 {
			display: flex;
			width: 100%;
			justify-content: center;
		}
		.r-btn {
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
		.r-btn:hover {
			border: 0px solid rgba(0, 0, 0, 1);
			opacity: 0.8;
		}
		.r-login {
			cursor: pointer;
			border: 1px solid #fff;
			border-radius: 10px;
			padding: 0 20px;
			margin: 0 auto;
			color: #fff;
			display: inline-block;
			text-decoration: none;
			width: auto;
			font-size: 20px;
			line-height: 50px;
			text-align: center;
		}
		.r-login:hover {
			opacity: 1;
		}
	}
}
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
