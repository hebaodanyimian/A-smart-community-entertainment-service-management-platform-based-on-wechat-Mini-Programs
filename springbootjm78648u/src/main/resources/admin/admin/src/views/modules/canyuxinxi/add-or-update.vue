<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="娱乐名称" prop="yulemingcheng" >
					<el-input v-model="ruleForm.yulemingcheng" placeholder="娱乐名称" clearable  :readonly="ro.yulemingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="娱乐名称" prop="yulemingcheng" >
					<el-input v-model="ruleForm.yulemingcheng" placeholder="娱乐名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="项目类型" prop="xiangmuleixing" >
					<el-input v-model="ruleForm.xiangmuleixing" placeholder="项目类型" clearable  :readonly="ro.xiangmuleixing"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="项目类型" prop="xiangmuleixing" >
					<el-input v-model="ruleForm.xiangmuleixing" placeholder="项目类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.tupian" label="图片" prop="tupian" >
					<file-upload
						tip="点击上传图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.tupian?ruleForm.tupian:''"
						@change="tupianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.tupian" label="图片" prop="tupian" >
					<img v-if="ruleForm.tupian.substring(0,4)=='http'&&ruleForm.tupian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian" width="100" height="100">
					<img v-else-if="ruleForm.tupian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.tupian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.tupian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="所在位置" prop="suozaiweizhi" >
					<el-input v-model="ruleForm.suozaiweizhi" placeholder="所在位置" clearable  :readonly="ro.suozaiweizhi"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="所在位置" prop="suozaiweizhi" >
					<el-input v-model="ruleForm.suozaiweizhi" placeholder="所在位置" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="参与时间" prop="canyushijian" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.canyushijian" 
						type="datetime"
						:readonly="ro.canyushijian"
						placeholder="参与时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.canyushijian" label="参与时间" prop="canyushijian" >
					<el-input v-model="ruleForm.canyushijian" placeholder="参与时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="参与人数" prop="canyurenshu" >
					<el-input v-model="ruleForm.canyurenshu" placeholder="参与人数" clearable  :readonly="ro.canyurenshu"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="参与人数" prop="canyurenshu" >
					<el-input v-model="ruleForm.canyurenshu" placeholder="参与人数" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="账号" prop="zhanghao" >
					<el-input v-model="ruleForm.zhanghao" placeholder="账号" clearable  :readonly="ro.zhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="账号" prop="zhanghao" >
					<el-input v-model="ruleForm.zhanghao" placeholder="账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="姓名" prop="xingming" >
					<el-input v-model="ruleForm.xingming" placeholder="姓名" clearable  :readonly="ro.xingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="姓名" prop="xingming" >
					<el-input v-model="ruleForm.xingming" placeholder="姓名" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="性别" prop="xingbie" >
					<el-input v-model="ruleForm.xingbie" placeholder="性别" clearable  :readonly="ro.xingbie"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="性别" prop="xingbie" >
					<el-input v-model="ruleForm.xingbie" placeholder="性别" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="年龄" prop="nianling" >
					<el-input v-model="ruleForm.nianling" placeholder="年龄" clearable  :readonly="ro.nianling"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="年龄" prop="nianling" >
					<el-input v-model="ruleForm.nianling" placeholder="年龄" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="参与备注" prop="canyubeizhu" >
					<el-input v-model="ruleForm.canyubeizhu" placeholder="参与备注" clearable  :readonly="ro.canyubeizhu"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="参与备注" prop="canyubeizhu" >
					<el-input v-model="ruleForm.canyubeizhu" placeholder="参与备注" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-tijiao04"></span>
					保存
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-quxiao03"></span>
					撤销
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-fanhui"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	export default {
		data() {
			return {
				id: '',
				type: '',
			
			
				ro:{
					yulemingcheng : false,
					xiangmuleixing : false,
					tupian : false,
					suozaiweizhi : false,
					canyushijian : false,
					canyurenshu : false,
					zhanghao : false,
					xingming : false,
					xingbie : false,
					nianling : false,
					canyubeizhu : false,
					crossuserid : false,
					crossrefid : false,
					sfsh : false,
					shhf : false,
				},
			
				ruleForm: {
					yulemingcheng: '',
					xiangmuleixing: '',
					tupian: '',
					suozaiweizhi: '',
					canyushijian: '',
					canyurenshu: '1',
					zhanghao: '',
					xingming: '',
					xingbie: '',
					nianling: '',
					canyubeizhu: '',
					crossuserid: '',
					crossrefid: '',
					shhf: '',
				},

				rules: {
					yulemingcheng: [
					],
					xiangmuleixing: [
					],
					tupian: [
					],
					suozaiweizhi: [
					],
					canyushijian: [
					],
					canyurenshu: [
					],
					zhanghao: [
					],
					xingming: [
					],
					xingbie: [
					],
					nianling: [
					],
					canyubeizhu: [
					],
					crossuserid: [
					],
					crossrefid: [
					],
					sfsh: [
					],
					shhf: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
			this.ruleForm.canyushijian = this.getCurDateTime()
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='yulemingcheng'){
							this.ruleForm.yulemingcheng = obj[o];
							this.ro.yulemingcheng = true;
							continue;
						}
						if(o=='xiangmuleixing'){
							this.ruleForm.xiangmuleixing = obj[o];
							this.ro.xiangmuleixing = true;
							continue;
						}
						if(o=='tupian'){
							this.ruleForm.tupian = obj[o];
							this.ro.tupian = true;
							continue;
						}
						if(o=='suozaiweizhi'){
							this.ruleForm.suozaiweizhi = obj[o];
							this.ro.suozaiweizhi = true;
							continue;
						}
						if(o=='canyushijian'){
							this.ruleForm.canyushijian = obj[o];
							this.ro.canyushijian = true;
							continue;
						}
						if(o=='canyurenshu'){
							this.ruleForm.canyurenshu = obj[o];
							this.ro.canyurenshu = true;
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
						if(o=='xingbie'){
							this.ruleForm.xingbie = obj[o];
							this.ro.xingbie = true;
							continue;
						}
						if(o=='nianling'){
							this.ruleForm.nianling = obj[o];
							this.ro.nianling = true;
							continue;
						}
						if(o=='canyubeizhu'){
							this.ruleForm.canyubeizhu = obj[o];
							this.ro.canyubeizhu = true;
							continue;
						}
						if(o=='crossuserid'){
							this.ruleForm.crossuserid = obj[o];
							this.ro.crossuserid = true;
							continue;
						}
						if(o=='crossrefid'){
							this.ruleForm.crossrefid = obj[o];
							this.ro.crossrefid = true;
							continue;
						}
					}
					this.ruleForm.canyurenshu = '1'; 				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(this.$storage.get("role")!="管理员") {
							this.ro.canyurenshu = true;
						}
						if(((json.zhanghao!=''&&json.zhanghao) || json.zhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.zhanghao = json.zhanghao
							this.ro.zhanghao = true;
						}
						if(((json.xingming!=''&&json.xingming) || json.xingming==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.xingming = json.xingming
							this.ro.xingming = true;
						}
						if(((json.xingbie!=''&&json.xingbie) || json.xingbie==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.xingbie = json.xingbie
							this.ro.xingbie = true;
						}
						if(((json.nianling!=''&&json.nianling) || json.nianling==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.nianling = json.nianling
							this.ro.nianling = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `canyuxinxi/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.tupian!=null) {
						this.ruleForm.tupian = this.ruleForm.tupian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					var table = this.$storage.getObj('crossTable');
					if(objcross!=null) {
						if(!this.ruleForm.canyurenshu){
							this.$message.error("参与人数不能为空");
							return
						}
						objcross.canyurenshu = parseFloat(objcross.canyurenshu) + parseFloat(this.ruleForm.canyurenshu)
											}
					//更新跨表属性
					var crossuserid;
					var crossrefid;
					var crossoptnum;
					var finishNum = 0;
					if(!this.ruleForm.id) {
						delete this.ruleForm.userid
					}
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: objcross
										}).then(({ data }) => {});
									}
									else {
										crossuserid=this.$storage.get('userid');
										crossrefid=obj['id'];
										crossoptnum=this.$storage.get('statusColumnName');
										crossoptnum=crossoptnum.replace(/\[/,"").replace(/\]/,"");
									}
								}
								if(crossrefid && crossuserid) {
									this.ruleForm.crossuserid = crossuserid;
									this.ruleForm.crossrefid = crossrefid;
									let params = { 
										page: 1, 
										limit: 10, 
										crossuserid:this.ruleForm.crossuserid,
										crossrefid:this.ruleForm.crossrefid,
									} 
									await this.$http({ 
										url: "canyuxinxi/page", 
										method: "get", 
										params: params 
									}).then(({ 
										data 
									}) => { 
										if (data && data.code === 0) {
											finishNum = data.data.total
										}
									})
								}
								if(finishNum>=crossoptnum) {
									this.$message.error(this.$storage.get('tips'));
									return false;
								}
							}
							
							await this.$http({
								url: `canyuxinxi/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.canyuxinxiCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
									var table = this.$storage.get('crossTable');
									await this.$http({
										url: `${table}/update`,
										method: "post",
										data: objcross
									}).then(({ data }) => {});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.canyuxinxiCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			tupianUploadChange(fileUrls) {
				this.ruleForm.tupian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		border-radius: 10px;
		padding: 20px 30px;
		margin: 20px 0 0 auto;
		background: #fff;
		width: calc(100% - 40px);
		font-size: 15px;
	}
	.add-update-preview {
		padding: 0px;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 49%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #666;
		font-weight: 600;
		width: 180px;
		font-size: 16px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		padding: 0 10px;
		color: #333;
		background: none;
		font-weight: 500;
		display: inline-block;
		font-size: 16px;
		line-height: 40px;
		min-width: 50%;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: #E8E8E8;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 0px solid #ccc;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		background: #E8E8E8;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 0;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		background: #E8E8E8;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 0;
		cursor: not-allowed;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		background: #E8E8E8;
		width: 100%;
		font-size: 16px;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 15px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 1px solid #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 15px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: #E8E8E8;
		width: auto;
		font-size: 16px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px dashed #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 200px;
		font-size: 24px;
		line-height: 90px;
		text-align: center;
		height: 90px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px dashed #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 200px;
		font-size: 24px;
		line-height: 90px;
		text-align: center;
		height: 90px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px dashed #E8E8E8;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 200px;
		font-size: 24px;
		line-height: 90px;
		text-align: center;
		height: 90px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #999;
		font-size: 12px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #E8E8E8;
		border-radius: 0px;
		padding: 12px;
		color: #666;
		background: #fff;
		width: 100%;
		font-size: 14px;
		height: 120px;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 0;
				cursor: not-allowed;
				border-radius: 0px;
				padding: 12px;
				color: #666;
				background: #E8E8E8;
				width: 100%;
				font-size: 14px;
				height: 120px;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 30px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #107ED6;
			width: auto;
			font-size: 16px;
			min-width: 120px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 30px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #FF5F32;
			width: auto;
			font-size: 16px;
			min-width: 120px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				font-size: 16px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 30px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #437BC7;
			width: auto;
			font-size: 16px;
			min-width: 120px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 30px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #B3B3B3;
			width: auto;
			font-size: 16px;
			min-width: 120px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 30px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #477ECB;
			width: auto;
			font-size: 16px;
			min-width: 120px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				font-size: 16px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
