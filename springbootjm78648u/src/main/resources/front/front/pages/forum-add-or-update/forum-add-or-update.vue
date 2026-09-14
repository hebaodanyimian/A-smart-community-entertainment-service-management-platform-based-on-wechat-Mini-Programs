<template>
<view class="content">
	<view :style='{"minHeight":"100%","width":"100%","padding":"0","position":"relative","background":"#e9e9e9","height":"auto"}'>
		<form :style='{"width":"100%","padding":"24rpx","background":"none","display":"block","height":"auto"}'>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<input :style='{"border":"0","padding":"0px 24rpx","margin":"0px","color":"rgb(0, 0, 0)","borderRadius":"8rpx","flex":"1","background":"rgba(255, 255, 255, 0)","fontSize":"28rpx","height":"80rpx"}' v-model="forum.title" placeholder="标题"></input>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<picker :style='{"width":"100%","padding":"0 20rpx","flex":"1","height":"auto"}' @change="setIsDoneTap" :value="index" :range="options">
					<view class="uni-picker-type" :style='{"width":"100%","lineHeight":"80rpx","fontSize":"28rpx","color":"#8A8A8A"}'>{{options[index]}}</view>
				</picker>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<picker :style='{"width":"100%","padding":"0 20rpx","flex":"1","height":"auto"}' @change="setIsanonTap" :value="index2" :range="options2">
					<view class="uni-picker-type" :style='{"width":"100%","lineHeight":"80rpx","fontSize":"28rpx","color":"#8A8A8A"}'>{{options2[index2]}}</view>
				</picker>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<picker :style='{"width":"100%","padding":"0 20rpx","flex":"1","height":"auto"}' @change="setCategoryTap" :value="categoryIndex" range-key="typename" :range="categoryList">
					<view class="uni-picker-type" :style='{"width":"100%","lineHeight":"80rpx","fontSize":"28rpx","color":"#8A8A8A"}'>{{categoryList[categoryIndex].typename}}</view>
				</picker>
			</view>
			<view style="display: flex;align-items: center;padding: 10px 0;">
				<image @click="imgView(item,index)" v-for="(item,index) in picList" :key="index" :src="baseUrl + item" style="width: 50px;margin: 0 5px 0 0;" mode="widthFix"></image>
				<view style="padding: 0 10px;line-height: 20px;background: #ccc;color: #333;" @click="uploadImg">上传图片</view>
			</view>
			<view :style='{"padding":"12rpx 0","margin":"0 0 24rpx 0","borderColor":"#d0d0d0","alignItems":"center","borderWidth":"0 0 2rpx 0","display":"flex","width":"100%","borderStyle":"solid","height":"auto"}'>
				<xia-editor :style='{"padding":"0","margin":"0px","flex":"1","background":"rgba(255, 255, 255, 0)","height":"auto"}' v-model="forum.content" @editorChange="contentChange" placeholder="内容"></xia-editor>
			</view>
			<view :style='{"width":"100%","padding":"0","justifyContent":"center","display":"flex","height":"auto"}'>
				<button :style='{"border":"0","padding":"0px","margin":"0","color":"#fff","borderRadius":"40rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' @tap="onSubmitTap" class="bg-red margin-tb-sm">确认提交</button>
			</view>
		</form>
	</view>
</view>
</template>

<script>
	import xiaEditor from '@/components/xia-editor/xia-editor';
	export default {
		data() {
			return {
				forum: {
					content: '',
					id: '',
					title: '',
					isdone: '开放',
					parentid: 0,
					typename: '',
					isanon: 0,
					cover: ''
				},
				picList: [],
				index: 0,
				options: ['开放', '关闭'],
				index2: 0,
				options2: ['实名', '匿名'],
				categoryIndex: 0,
				categoryList: [],
				username: '',
				user: {},
			}
		},
		components: {
			xiaEditor
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			}
		},
		async onLoad(options) {
			let table = uni.getStorageSync("nowTable");
			// 获取用户信息
			let res = await this.$api.session(table);
			this.user = res.data;
			if(table == `jumin`){
				this.username = this.user.zhanghao
			}
			if(this.userid) {
				res = await this.$api.page('forumtype', {page:1,limit:100});
			} else {
				res = await this.$api.list('forumtype', {page:1,limit:100});
			}
			this.forum.typename = res.data.list[0].typename
			this.categoryList = res.data.list;
			if (options.id) {
				this.id = options.id;
				let res = await this.$api.info('forum',this.id);
				this.forum = res.data
			}
			this.styleChange()
		},
		methods: {
			imgView(url,index){
				let that = this
				uni.showActionSheet({
					itemList: ['预览','删除'],
					success(res) {
						if(res.tapIndex==0){
							let arr = []
							for(let x in that.picList){
								arr.push(that.baseUrl + that.picList[x])
							}
							uni.previewImage({
								current: that.baseUrl + url,
								urls: arr
							})
						}else if(res.tapIndex==1){
							that.picList.splice(index,1)
						}
					}
				})
			},
			async uploadImg(){
				let _this = this;
				await this.$api.upload(function(res) {
					_this.picList.push('upload/' + res.file)
					_this.$forceUpdate();
				});
			},
			contentChange(e) {
				this.forum.content = e
			},
			styleChange() {
				this.$nextTick(()=>{
					// document.querySelectorAll('.my-publish-pv .uni-input-input').forEach(el=>{
					//   el.style.backgroundColor = this.myPublishForm.content.input.backgroundColor
					// })
				})
			},
			async onSubmitTap() {
				if(this.forum.title==''){
					this.$utils.msg('标题不能为空');
					return false
				}
				if(this.forum.content==''){
					this.$utils.msg('内容不能为空');
					return false
				}
				if(this.picList.length){
					this.forum.cover = this.picList.join(',')
				}
				this.forum.username = this.username;
				if (this.id) {
					await this.$api.update('forum',this.forum);
				} else {
					this.forum.toptime = this.$utils.getCurDateTime()
					await this.$api.save('forum',this.forum);
				}
				this.$utils.msgBack('操作成功');
			},
			setIsDoneTap(e) {
				// this.forum.isdone = e;
				this.index = e.target.value
				this.forum.isdone = this.options[this.index]
				_this.$forceUpdate();
			},
			setIsanonTap(e){
				this.index2 = e.target.value
				this.forum.isanon = this.index2
				_this.$forceUpdate();
			},
			setCategoryTap(e){
				this.categoryIndex = e.target.value
				this.forum.typename = this.categoryList[this.categoryIndex].typename
				_this.$forceUpdate();
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
</style>
