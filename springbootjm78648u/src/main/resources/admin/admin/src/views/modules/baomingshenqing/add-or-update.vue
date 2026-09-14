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
				<el-form-item class="input" v-if="type!='info'" label="报名编号" prop="baomingbianhao" >
					<el-input v-model="ruleForm.baomingbianhao" placeholder="报名编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.baomingbianhao" label="报名编号" prop="baomingbianhao" >
					<el-input v-model="ruleForm.baomingbianhao" placeholder="报名编号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="活动名称" prop="huodongmingcheng" >
					<el-input v-model="ruleForm.huodongmingcheng" placeholder="活动名称" clearable  :readonly="ro.huodongmingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="活动名称" prop="huodongmingcheng" >
					<el-input v-model="ruleForm.huodongmingcheng" placeholder="活动名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="活动类型" prop="huodongleixing" >
					<el-input v-model="ruleForm.huodongleixing" placeholder="活动类型" clearable  :readonly="ro.huodongleixing"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="活动类型" prop="huodongleixing" >
					<el-input v-model="ruleForm.huodongleixing" placeholder="活动类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.huodongfengmian" label="活动封面" prop="huodongfengmian" >
					<file-upload
						tip="点击上传活动封面"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.huodongfengmian?ruleForm.huodongfengmian:''"
						@change="huodongfengmianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.huodongfengmian" label="活动封面" prop="huodongfengmian" >
					<img v-if="ruleForm.huodongfengmian.substring(0,4)=='http'&&ruleForm.huodongfengmian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.huodongfengmian" width="100" height="100">
					<img v-else-if="ruleForm.huodongfengmian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.huodongfengmian.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.huodongfengmian.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="时间" prop="shijian" >
					<el-input v-model="ruleForm.shijian" placeholder="时间" clearable  :readonly="ro.shijian"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="时间" prop="shijian" >
					<el-input v-model="ruleForm.shijian" placeholder="时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="地点" prop="didian" >
					<el-input v-model="ruleForm.didian" placeholder="地点" clearable  :readonly="ro.didian"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="地点" prop="didian" >
					<el-input v-model="ruleForm.didian" placeholder="地点" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="报名人数" prop="baomingrenshu" >
					<el-input v-model.number="ruleForm.baomingrenshu" placeholder="报名人数" clearable  :readonly="ro.baomingrenshu"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="报名人数" prop="baomingrenshu" >
					<el-input v-model="ruleForm.baomingrenshu" placeholder="报名人数" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="账号" prop="zhanghao" >
					<el-input v-model="ruleForm.zhanghao" placeholder="账号" clearable  :readonly="ro.zhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="账号" prop="zhanghao" >
					<el-input v-model="ruleForm.zhanghao" placeholder="账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="组织者" prop="xingming" >
					<el-input v-model="ruleForm.xingming" placeholder="组织者" clearable  :readonly="ro.xingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="组织者" prop="xingming" >
					<el-input v-model="ruleForm.xingming" placeholder="组织者" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="参与账号" prop="canyuzhanghao" >
					<el-input v-model="ruleForm.canyuzhanghao" placeholder="参与账号" clearable  :readonly="ro.canyuzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="参与账号" prop="canyuzhanghao" >
					<el-input v-model="ruleForm.canyuzhanghao" placeholder="参与账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="参与姓名" prop="canyuxingming" >
					<el-input v-model="ruleForm.canyuxingming" placeholder="参与姓名" clearable  :readonly="ro.canyuxingming"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="参与姓名" prop="canyuxingming" >
					<el-input v-model="ruleForm.canyuxingming" placeholder="参与姓名" readonly></el-input>
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
				<el-form-item class="date" v-if="type!='info'" label="报名时间" prop="baomingshijian" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.baomingshijian" 
						type="datetime"
						:readonly="ro.baomingshijian"
						placeholder="报名时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.baomingshijian" label="报名时间" prop="baomingshijian" >
					<el-input v-model="ruleForm.baomingshijian" placeholder="报名时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="报名备注" prop="baomingbeizhu" >
					<el-input v-model="ruleForm.baomingbeizhu" placeholder="报名备注" clearable  :readonly="ro.baomingbeizhu"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="报名备注" prop="baomingbeizhu" >
					<el-input v-model="ruleForm.baomingbeizhu" placeholder="报名备注" readonly></el-input>
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
	import { 
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					baomingbianhao : false,
					huodongmingcheng : false,
					huodongleixing : false,
					huodongfengmian : false,
					shijian : false,
					didian : false,
					baomingrenshu : false,
					zhanghao : false,
					xingming : false,
					canyuzhanghao : false,
					canyuxingming : false,
					xingbie : false,
					nianling : false,
					baomingshijian : false,
					baomingbeizhu : false,
					crossuserid : false,
					crossrefid : false,
					sfsh : false,
					shhf : false,
				},
			
				ruleForm: {
					baomingbianhao: this.getUUID(),
					huodongmingcheng: '',
					huodongleixing: '',
					huodongfengmian: '',
					shijian: '',
					didian: '',
					baomingrenshu: Number('1'),
					zhanghao: '',
					xingming: '',
					canyuzhanghao: '',
					canyuxingming: '',
					xingbie: '',
					nianling: '',
					baomingshijian: '',
					baomingbeizhu: '',
					crossuserid: '',
					crossrefid: '',
					shhf: '',
				},

				rules: {
					baomingbianhao: [
					],
					huodongmingcheng: [
					],
					huodongleixing: [
					],
					huodongfengmian: [
					],
					shijian: [
					],
					didian: [
					],
					baomingrenshu: [
						{ required: true, message: '报名人数不能为空', trigger: 'blur' },
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					zhanghao: [
					],
					xingming: [
					],
					canyuzhanghao: [
					],
					canyuxingming: [
					],
					xingbie: [
					],
					nianling: [
					],
					baomingshijian: [
					],
					baomingbeizhu: [
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
			this.ruleForm.baomingshijian = this.getCurDateTime()
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
						if(o=='baomingbianhao'){
							this.ruleForm.baomingbianhao = obj[o];
							this.ro.baomingbianhao = true;
							continue;
						}
						if(o=='huodongmingcheng'){
							this.ruleForm.huodongmingcheng = obj[o];
							this.ro.huodongmingcheng = true;
							continue;
						}
						if(o=='huodongleixing'){
							this.ruleForm.huodongleixing = obj[o];
							this.ro.huodongleixing = true;
							continue;
						}
						if(o=='huodongfengmian'){
							this.ruleForm.huodongfengmian = obj[o];
							this.ro.huodongfengmian = true;
							continue;
						}
						if(o=='shijian'){
							this.ruleForm.shijian = obj[o];
							this.ro.shijian = true;
							continue;
						}
						if(o=='didian'){
							this.ruleForm.didian = obj[o];
							this.ro.didian = true;
							continue;
						}
						if(o=='baomingrenshu'){
							this.ruleForm.baomingrenshu = obj[o];
							this.ro.baomingrenshu = true;
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
						if(o=='canyuzhanghao'){
							this.ruleForm.canyuzhanghao = obj[o];
							this.ro.canyuzhanghao = true;
							continue;
						}
						if(o=='canyuxingming'){
							this.ruleForm.canyuxingming = obj[o];
							this.ro.canyuxingming = true;
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
						if(o=='baomingshijian'){
							this.ruleForm.baomingshijian = obj[o];
							this.ro.baomingshijian = true;
							continue;
						}
						if(o=='baomingbeizhu'){
							this.ruleForm.baomingbeizhu = obj[o];
							this.ro.baomingbeizhu = true;
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
					this.ruleForm.baomingrenshu = Number('1'); 				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(this.$storage.get("role")!="管理员") {
							this.ro.baomingrenshu = true;
						}
						if(((json.zhanghao!=''&&json.zhanghao) || json.zhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.canyuzhanghao = json.zhanghao
							this.ro.canyuzhanghao = true;
						}
						if(((json.xingming!=''&&json.xingming) || json.xingming==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.canyuxingming = json.xingming
							this.ro.canyuxingming = true;
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
					url: `baomingshenqing/info/${id}`,
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
					if(this.ruleForm.baomingbianhao) {
						this.ruleForm.baomingbianhao = String(this.ruleForm.baomingbianhao)
					}
					if(this.ruleForm.huodongfengmian!=null) {
						this.ruleForm.huodongfengmian = this.ruleForm.huodongfengmian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					var table = this.$storage.getObj('crossTable');
					if(objcross!=null) {
						if(!this.ruleForm.baomingrenshu){
							this.$message.error("报名人数不能为空");
							return
						}
						objcross.baomingrenshu = parseFloat(objcross.baomingrenshu) + parseFloat(this.ruleForm.baomingrenshu)
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
										url: "baomingshenqing/page", 
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
								url: `baomingshenqing/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.baomingshenqingCrossAddOrUpdateFlag = false;
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
				this.parent.baomingshenqingCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			huodongfengmianUploadChange(fileUrls) {
				this.ruleForm.huodongfengmian = fileUrls;
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
