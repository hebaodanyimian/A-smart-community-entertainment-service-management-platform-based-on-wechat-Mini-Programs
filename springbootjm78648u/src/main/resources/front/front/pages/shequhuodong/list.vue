<template>
<!-- category 1 -->
	<mescroll-uni @init="mescrollInit" :up="upOption" :down="downOption" @down="downCallback" @up="upCallback">
		<view class="content">
			<view :style='{"minHeight":"100%","padding":"0 20rpx","flexWrap":"wrap","background":"#E9E9E9","display":"flex","width":"100%","position":"relative","height":"auto"}'>
				<view class="cu-bar bg-white search" :style='{"padding":"20rpx 0 0 0","margin":"0","background":"none","display":"flex","width":"100%","position":"relative","height":"auto","order":"1"}'>
					<view :style='{"border":"2rpx solid #3d4e81","margin":"0","borderRadius":"20rpx","flex":"1","background":"#fff","lineHeight":"80rpx","position":"relative","height":"80rpx"}' class="search-form round">
						<text class="icon iconfont icon-fangdajing07" :style='{"color":"rgb(153, 153, 153)","left":"0px","textAlign":"center","width":"80rpx","fontSize":"40rpx","lineHeight":"80rpx","position":"absolute","right":"0px"}'></text>
						<input placeholder-style="color: #333" :style='{"border":"0","padding":"12rpx 20rpx 12rpx 80rpx","color":"#333","borderRadius":"40rpx","background":"none","width":"100%","lineHeight":"80rpx","fontSize":"28rpx","height":"80rpx"}' v-model="searchForm.huodongmingcheng" type="text" placeholder="活动名称" ></input>
					</view>
					<button :style='{"border":"0","padding":"0px","margin":"0","color":"#fff","right":"0px","borderRadius":"20rpx","top":"20rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"160rpx","lineHeight":"80rpx","fontSize":"28rpx","position":"absolute","height":"80rpx","zIndex":"99"}' @tap="search" class="cu-btn shadow-blur round">搜索</button>
				</view>
			

				<view :style='{"width":"100%","margin":"20rpx 0","flexWrap":"wrap","display":"flex","order":"4"}'>
					<view @click="sortClick('clicknum')" :style='{"border":"2rpx solid rgb(153, 152, 152)","padding":"0 12rpx","margin":"0 20rpx 0 0","outline":"0","borderRadius":"8rpx","display":"flex"}'>
						<text :style='{"color":"#333","lineHeight":"48rpx","fontSize":"24rpx"}'>点击量</text>
						<text v-if="listSort!='clicknum'" class="icon iconfont icon-xiaoliang13" :style='{"margin":"0 4rpx 0 0","lineHeight":"48rpx","fontSize":"24rpx","color":"#333"}'></text>
						<text v-else-if="listSort=='clicknum'&&listOrder=='asc'" class="icon iconfont icon-xiaoliang13" :style='{"margin":"0 4rpx 0 0","lineHeight":"48rpx","fontSize":"24rpx","color":"#333"}'></text>
						<text v-else-if="listSort=='clicknum'&&listOrder=='desc'" class="icon iconfont icon-xiaoliang13" :style='{"margin":"0 4rpx 0 0","lineHeight":"48rpx","fontSize":"24rpx","color":"#333"}'></text>
					</view>
					<view @click="sortClick('addtime')" :style='{"border":"2rpx solid rgb(153, 152, 152)","padding":"0 12rpx","margin":"0 20rpx 0 0","outline":"0","borderRadius":"8rpx","display":"flex"}'>
						<text :style='{"color":"#333","lineHeight":"48rpx","fontSize":"24rpx"}'>按日期</text>
						<text v-if="listSort!='addtime'" class="icon iconfont icon-shijian18" :style='{"margin":"0 4rpx 0 0","lineHeight":"48rpx","fontSize":"24rpx","color":"#333"}'></text>
						<text v-else-if="listSort=='addtime'&&listOrder=='asc'" class="icon iconfont icon-shijian18" :style='{"margin":"0 4rpx 0 0","lineHeight":"48rpx","fontSize":"24rpx","color":"#333"}'></text>
						<text v-else-if="listSort=='addtime'&&listOrder=='desc'" class="icon iconfont icon-shijian18" :style='{"margin":"0 4rpx 0 0","lineHeight":"48rpx","fontSize":"24rpx","color":"#333"}'></text>
					</view>
				</view>
				<view :style='{"width":"100%","flexWrap":"wrap","background":"none","display":"flex","height":"auto","order":"5"}'>
					<scroll-view scroll-x="true" class="category-one" :style='{"padding":"0","margin":"20rpx 0","whiteSpace":"nowrap","borderRadius":"20rpx","background":"none","flexDirection":"row","display":"flex","width":"100%","height":"auto"}'>
						<view :class='categoryName === item.huodongleixing ? "active" : ""' class="tab" v-for="(item,index) in categoryList" :key="index" @tap="categoryClick(item.huodongleixing)">{{item.huodongleixing}}</view>
					</scroll-view>
					<!-- 样式2 -->
					<view class="list" :style='{"width":"100%","padding":"0","margin":"0","borderRadius":"20rpx","background":"none","height":"auto"}'>
						<view @tap="onDetailTap(product)" class="listm flex flex-between" :style='{"boxShadow":"0 2rpx 12rpx rgba(0,0,0,.3)","padding":"20rpx","margin":"0 0 20rpx","borderRadius":"20rpx","flexWrap":"wrap","background":"#fff","display":"flex","width":"100%","height":"auto"}' v-for="(product,index) in list" :key="index">
							<image :style='{"width":"200rpx","objectFit":"cover","display":"block","height":"200rpx"}' mode="aspectFill" class="listmpic" v-if="preHttp(product.huodongfengmian)" :src="product.huodongfengmian.split(',')[0]"></image>
							<image :style='{"width":"200rpx","objectFit":"cover","display":"block","height":"200rpx"}' mode="aspectFill" class="listmpic" v-else :src="product.huodongfengmian?baseUrl+product.huodongfengmian.split(',')[0]:''"></image>

							<view class="listmr" :style='{"width":"calc(100% - 200rpx)","padding":"0","margin":"0","height":"auto"}'>
								<view class="col3 f30 elip mb15" :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","color":"#333","width":"100%","lineHeight":"48rpx","fontSize":"28rpx"}'>{{product.huodongmingcheng}}</view>
								<view class="col3 f30 elip mb15" :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","color":"#333","width":"100%","lineHeight":"48rpx","fontSize":"28rpx"}'>{{product.huodongleixing}}</view>
								<view class="col3 f30 elip mb15" :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","color":"#333","width":"100%","lineHeight":"48rpx","fontSize":"28rpx"}'>{{product.shijian}}</view>
								<view class="col3 f30 elip mb15" :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","color":"#333","width":"100%","lineHeight":"48rpx","fontSize":"28rpx"}'>报名人数:{{product.baomingrenshu}}</view>
								<view class="col3 f30 elip mb15" :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","color":"#333","width":"100%","lineHeight":"48rpx","fontSize":"28rpx"}'>{{product.didian}}</view>
								<view :style='{"padding":"0 20rpx"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
									<text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{product.addtime.replace(/\-/g,'-').replace(/\:/g,':')}}</text>
								</view>
								<view :style='{"padding":"0 20rpx"}'>
									<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
									<text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{product.zhanghao}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
									<text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{product.thumbsupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
									<text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{product.storeupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
									<text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{product.clicknum}}</text>
								</view>
							</view>
							<!-- #ifdef MP-WEIXIN -->
							<view :style='{"width":"100%","padding":"8rpx 20rpx","justifyContent":"space-between","display":"flex","height":"auto"}'>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('shequhuodong','修改')) || (!userid && isAuthFront('shequhuodong','修改'))" @tap.stop.proevent="onUpdate" :data-row="product">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-edit"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>修改</text>
								</view>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('shequhuodong','删除')) || (!userid && isAuthFront('shequhuodong','删除'))" @tap.stop.proevent="onDelete" :data-row="product">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-delete"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>删除</text>
								</view>
							</view>
							<!-- #endif -->
							<!-- #ifndef MP-WEIXIN -->
							<view :style='{"width":"100%","padding":"8rpx 20rpx","justifyContent":"space-between","display":"flex","height":"auto"}'>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('shequhuodong','修改')) || (!userid && isAuthFront('shequhuodong','修改'))" @tap.stop.proevent="onUpdateTap(product)">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-edit"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>修改</text>
								</view>
								<view :style='{"display":"flex"}' v-if="(userid && isAuth('shequhuodong','删除')) || (!userid && isAuthFront('shequhuodong','删除'))" @tap.stop.proevent="onDeleteTap(product.id)">
									<text :style='{"margin":"0 8rpx 0 0","fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}' class="cuIcon-delete"></text>
									<text :style='{"fontSize":"28rpx","lineHeight":"1","color":"#666","display":"inline-block"}'>删除</text>
								</view>
							</view>
							<!-- #endif -->
						</view>
					</view>
			
			
			


			
			
			
			
				</view>
				<button :style='{"border":"0","boxShadow":"0 2rpx 12rpx #00000030","margin":"20rpx 0 0","color":"#fff","bottom":"0","outline":"none","borderRadius":"20rpx","left":"0","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"120rpx","lineHeight":"70rpx","fontSize":"28rpx","position":"relative","height":"70rpx","zIndex":"1","order":"2"}' class="add-btn" @click="screenBoxShow=true">筛</button>
				<button :style='{"border":"0","boxShadow":"0 2rpx 12rpx rgba(0,0,0,.3)","margin":"20rpx ","color":"rgb(255, 255, 255)","outline":"none","borderRadius":"20rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"120rpx","lineHeight":"70rpx","fontSize":"28rpx","position":"relative","height":"70rpx","zIndex":"1","order":"3"}' v-if="userid && isAuth('shequhuodong','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<button :style='{"border":"0","boxShadow":"0 2rpx 12rpx rgba(0,0,0,.3)","margin":"20rpx ","color":"rgb(255, 255, 255)","outline":"none","borderRadius":"20rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"120rpx","lineHeight":"70rpx","fontSize":"28rpx","position":"relative","height":"70rpx","zIndex":"1","order":"3"}' v-if="!userid && isAuthFront('shequhuodong','新增')" class="add-btn" @click="onAddTap()">新增</button>
				<view :style='{"top":"0","left":"0","background":"rgba(0, 0, 0, .3)","width":"100%","position":"absolute","height":"100%","zIndex":"665"}' v-if="screenBoxShow" @click="screenBoxShow=false"></view>
				<view class="screenBox" :class="screenBoxShow?'screenBoxActive':''">
					<view :style='{"width":"100%","padding":"20rpx 0 20rpx 0","alignItems":"center","flexWrap":"wrap","justifyContent":"space-around","display":"flex"}'>
						<view :style='{"width":"100%","padding":"0 0 0 20rpx","fontSize":"24rpx"}'>地点</view>
						<input :style='{"border":"2rpx solid rgb(162, 144, 104)","padding":"0 20rpx 0 20rpx","borderRadius":"40rpx","background":"none","width":"90%","fontSize":"24rpx","height":"80rpx"}' placeholder="请输入地点" v-model="searchForm.didian">
					</view>
					<view :style='{"width":"100%","padding":"40rpx 0 0","alignItems":"center","justifyContent":"space-around","display":"flex"}'>
						<div :style='{"color":"#888","borderRadius":"20rpx","textAlign":"center","background":"#EDEDED","width":"40%","lineHeight":"60rpx","height":"60rpx"}' @click="screenReset">重置</div>
						<div :style='{"color":"#fff","borderRadius":"20rpx","textAlign":"center","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","width":"40%","lineHeight":"60rpx","height":"60rpx"}' @click="search">搜索</div>
					</view>
				</view>
			</view>
		</view>
	</mescroll-uni>
</template>

<script>
	export default {
		data() {
			return {
				btnColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				clicknumColor: ['#409eff','#67c23a','#909399','#e6a23c','#f56c6c','#356c6c','#351c6c','#f093a9','#a7c23a','#104eff','#10441f','#a21233','#503319'],
				list: [],
				lists: [],
                userid: '',
				mescroll: null, //mescroll实例对象
				downOption: {
					auto: false //是否在初始化后,自动执行下拉回调callback; 默认true
				},
				upOption: {
					noMoreSize: 5, //如果列表已无数据,可设置列表的总数量要大于半页才显示无更多数据;避免列表数据过少(比如只有一条数据),显示无更多数据会不好看; 默认5
					textNoMore: '~ 没有更多了 ~',
				},
				hasNext: true,
				searchForm:{
					huodongmingcheng: '',
					didian: '',
				},
				categoryList:[],
				categoryName :'全部',
				CustomBar: '0',
				listSort: 'id',
				listOrder: 'desc',
				screenBoxShow: false,
			};
		},
		watch: {
		},
		mounted() {
		},
		computed: {
			baseUrl() {
				return this.$base.url;
			},
		},
		async onShow() {
			this.btnColor = this.btnColor.sort(()=> {
				return (0.5-Math.random());
			});
			this.clicknumColor = this.clicknumColor.sort(()=> {
				return (0.5-Math.random());
			});
            let res = {};
            if(this.userid) {
                res = await this.$api.page('huodongleixing', {page:1,limit:100});
            } else {
                res = await this.$api.list('huodongleixing', {page:1,limit:100});
            }
			res.data.list.splice(0,0,{id:0,huodongleixing:'全部'})
			this.categoryList = res.data.list;
			this.hasNext = true
			// 重新加载数据
			if (this.mescroll) this.mescroll.resetUpScroll()
		},
		async onLoad(options) {
            if(options.userid) {
                this.userid=options.userid;
            } else {
                this.userid = "";
            }
			this.hasNext = true
			// 重新加载数据
			// if (this.mescroll) this.mescroll.resetUpScroll()
		},
		components: {
		},
		methods: {
			screenReset(){
				this.searchForm = {}
				this.search()
				this.$forceUpdate()
			},
			uGetRect(selector, all) {
				return new Promise(resolve => {
					uni.createSelectorQuery()
					.in(this)
					[all ? 'selectAll' : 'select'](selector)
					.boundingClientRect(rect => {
						if (all && Array.isArray(rect) && rect.length) {
							resolve(rect);
						}
						if (!all && rect) {
							resolve(rect);
						}
					})
					.exec();
				});
			},
			cloneData(data) {
				return JSON.parse(JSON.stringify(data));
			},
			sortClick(type){
				if(this.listSort==type){
					if(this.listOrder == 'desc'){
						this.listOrder = 'asc'
					}else{
						this.listOrder = 'desc'
					}
				}else{
					this.listSort = type
					this.listOrder = 'desc'
				}
				this.search()
			},
            priceChange(price) {
                return Number(price).toFixed(2);
            },
            preHttp(str) {
                return str && str.substr(0,4)=='http';
            },
			//类别搜索
			categoryClick(categoryName){
				this.categoryName = categoryName;
				this.mescroll.resetUpScroll();
			},
			// mescroll组件初始化的回调,可获取到mescroll对象
			mescrollInit(mescroll) {
				this.mescroll = mescroll;
			},
			/*下拉刷新的回调 */
			downCallback(mescroll) {
				this.hasNext = true
				// 重置分页参数页数为1
				mescroll.resetUpScroll()
			},
			/*上拉加载的回调: mescroll携带page的参数, 其中num:当前页 从1开始, size:每页数据条数,默认10 */
			async upCallback(mescroll) {
				let params = {
					page: mescroll.num,
					limit: mescroll.size,
				}
				params['sort'] = this.listSort;
				params['order'] = this.listOrder;

				if(this.categoryName!='全部'){
					params.huodongleixing = '%' + this.categoryName + '%'
				}
				if(this.searchForm.huodongmingcheng){
					params['huodongmingcheng'] = '%' + this.searchForm.huodongmingcheng + '%'
				}
				if(this.searchForm.didian){
					params['didian'] = '%' + this.searchForm.didian + '%'
				}
				let user = uni.getStorageSync("appUserid")?JSON.parse(uni.getStorageSync('userSession')):{}
                let res = {}
                if(this.userid) {
                    res = await this.$api.page(`shequhuodong`, params);
                } else {
                    res = await this.$api.list(`shequhuodong`, params);
                }

				// 如果是第一页数据置空
				if (mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				this.$forceUpdate()
				
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				mescroll.endSuccess(mescroll.size, this.hasNext);
			},
			// 详情
			onDetailTap(item) {
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./detail?id=${item.id}&userid=`+this.userid)
			},
			onUpdate(e){
				this.onUpdateTap(e.currentTarget.dataset.row)
			},
			// 修改
			onUpdateTap(row){
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update?id=${row.id}`)
			},
			// 添加
			onAddTap(){
                uni.setStorageSync("useridTag",this.userid);
				this.$utils.jump(`./add-or-update`)
			},
			onDelete(e){
				this.onDeleteTap(e.currentTarget.dataset.row.id)
			},
			onDeleteTap(id){
				var _this = this;
				uni.showModal({
					title: '提示',
					content: '是否确认删除',
					success: async function(res) {
						if (res.confirm) {
							await _this.$api.del('shequhuodong', JSON.stringify([id]));
							let obj = await _this.$api.list('storeup',{
								page: 1,
								limit: 100,
								tablename: 'shequhuodong',
								refid: id,
							})
							if(obj.data.list.length){
								let arr = []
								for(let x in obj.data.list){
									arr.push(obj.data.list[x].id)
								}
								await _this.$api.del('storeup',JSON.stringify(arr))
							}
							_this.$utils.msg('删除成功');
							_this.hasNext = true
							// 重置分页参数页数为1
							_this.search()
						}
					}
				});
			},
			// 搜索
			async search(){
				this.mescroll.num = 1
				let searchForm = {
					page: this.mescroll.num,
					limit: this.mescroll.size,
				}
				searchForm['sort'] = this.listSort;
				searchForm['order'] = this.listOrder;

				if(this.searchForm.huodongmingcheng){
					searchForm['huodongmingcheng'] = '%' + this.searchForm.huodongmingcheng + '%'
				}
				if(this.categoryName!='全部'){
					searchForm.huodongleixing = '%' + this.categoryName + '%'
				}
				if(this.searchForm.didian){
					searchForm['didian'] = '%' + this.searchForm.didian + '%'
				}
                let res = {};
                if(this.userid) {
                    res = await this.$api.page(`shequhuodong`, searchForm);
                } else {
                    res = await this.$api.list(`shequhuodong`, searchForm);
                }
				// 如果是第一页数据置空
				if (this.mescroll.num == 1) this.list = [];
				this.list = this.list.concat(res.data.list);
				
				let length = Math.ceil(this.list.length/6)
				let arr = [];
				for (let i = 0; i<length; i++){
					arr[i] = this.list.slice(i*6, (i+1)*6)
				}
				this.lists = arr
				if (res.data.list.length == 0) this.hasNext = false;
				this.mescroll.endSuccess(this.mescroll.size, this.hasNext);
				this.screenBoxShow = false
			}
		}
	};
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.category-one .tab {
		cursor: pointer;
		border-radius: 0;
		padding: 0 20rpx;
		margin: 0 20rpx 0 0;
		color: #000;
		background: none;
		display: inline-block;
		width: auto;
		font-size: 28rpx;
		line-height: 64rpx;
	}
	
	.category-one .tab.active {
		cursor: pointer;
		border-radius: 20rpx;
		padding: 0 20rpx;
		margin: 0 20rpx 0 0;
		color: #fff;
		background: #5051b5;
		display: inline-block;
		width: auto;
		font-size: 28rpx;
		line-height: 64rpx;
	}
	.screenBox {
		padding: 20rpx 0 20rpx 0;
		transform: translate3d(100%, 0, 0);
		z-index: 666;
		top: 0;
		background: #fff;
		width: 80%;
		position: absolute;
		right: 0;
		transition: transform .3s;
		height: 100%;
		.screenTab {
			border: 2rpx solid rgb(162, 144, 104);
			border-radius: 40rpx;
			margin: 10rpx 0 10rpx 0;
			background: none;
			width: calc(100% / 3 - 24rpx);
			line-height: 72rpx;
			text-align: center;
		}
		.screenTabActive {
			border-radius: 40rpx;
			margin: 10rpx 0 10rpx 0;
			color: #fff;
			background: #3d4e81;
			width: calc(100% / 3 - 20rpx);
			line-height: 72rpx;
			text-align: center;
		}
	}
	.screenBoxActive {
		transform: translate3d(0, 0, 0);
	}
</style>
