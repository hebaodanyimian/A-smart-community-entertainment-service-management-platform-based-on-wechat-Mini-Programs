<template>
	<view class="content">
		<view :style='{"width":"100%","padding":"0 0 40rpx 0","background":"#E9E9E9","flexDirection":"column","display":"flex","height":"auto"}'>
			<view class="list-swiper-4" :style='{"width":"100%","position":"relative","height":"360rpx"}' @touchstart="touchStart" @touchmove="touchMove" @touchend="touchEnd">
				<view :style='{"width":"100%","position":"absolute","top":"0%","left":"0%","background":"#fff","height":"360rpx"}' class="item animate__animated" :class="prevNumList4 == index  ? 'animate__backOutRight' : (numList4 == index  ? 'animate__backInLeft' : '')" v-for="(swiper,index) in swiperList" :key="index" v-if="numList4 == index || prevNumList4 == index">
					<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"360rpx"}' mode="aspectFill" :src="baseUrl+swiper.img" @tap="onSwiperTap(swiper)"></image>
					<view :style='{"padding":"8rpx 20rpx","margin":"-20rpx 0 0 0","transform":"translate3d(-50%, -50%, 0)","top":"50%","color":"#000","left":"50%","background":"rgba(255, 255, 255, 0.3)","display":"none","lineHeight":"1.5","fontSize":"40rpx","position":"absolute"}'>{{ swiper.title }}</view>
				</view>
				<view class="animate__navigation" :style='{"alignItems":"center","left":"0%","bottom":"0%","background":"rgba(0,0,0,.3)","display":"flex","width":"100%","position":"absolute","justifyContent":"center","height":"40rpx"}'>
					<block v-for="(swiper,index) in swiperList" :key="index">
						<text class="navigation-item" v-if="numList4 == index" :style='{"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#F7EFDB","height":"16rpx"}'></text>
						<text class="navigation-item" v-if="numList4 != index" :style='{"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#fff","height":"16rpx"}'></text>
					</block>
				</view>
			</view>
			<!-- menu -->
			<view v-if="true" class="menu_view">
				<block v-for="(item,index1) in menuList" v-bind:key="item.roleName">
					<block v-if="index1==0" v-bind:key="index" v-for=" (menu,index) in item.frontMenu">
						<block v-bind:key="sort" v-for=" (child,sort) in menu.child">
							<block v-bind:key="sort2" v-for=" (button,sort2) in child.buttons">
								<view class="menu-item" v-if="button=='查看' && child.tableName!='yifahuodingdan' && child.tableName!='yituikuandingdan' &&child.tableName!='yiquxiaodingdan' && child.tableName!='weizhifudingdan' && child.tableName!='yizhifudingdan' && child.tableName!='yiwanchengdingdan' " @tap="onPageTap2(child.tableName)">
									<view class="iconarr" :class="child.appFrontIcon" :style="{'color':menuColor[index]}"></view>
									
									<view class="text">{{child.menu.split("列表")[0]}}</view>
								</view>
							</block>
						</block>
					</block>
				</block>
			</view>
			<!-- 商品推荐 -->
			<view class="listBox recommend" :style='{"padding":"0 20rpx","margin":"40rpx 0 0","background":"none","order":"2"}'>
				<view class="title" :style='{"padding":"0 0 0 32rpx","margin":"0 0 20rpx 0","color":"#000","alignItems":"center","display":"flex","letterSpacing":"2rpx","justifyContent":"flex-start","borderRadius":"0","background":"url(http://codegen.caihongy.cn/20250222/13a5b152f35d4a53a5ddbc4d83bab2ff.png) no-repeat left center / auto 100%","width":"100%","fontSize":"32rpx","position":"relative","fontWeight":"600","height":"72rpx"}'>
					<view :style='{"letterSpacing":"2rpx","fontSize":"32rpx","lineHeight":"100rpx","color":"#000","fontWeight":"600"}'>娱乐项目推荐</view>
				</view>
				<!-- 样式9 -->
				<view class="list-box style4 style9" :style='{"width":"100%","padding":"20rpx","margin":"0","borderRadius":"20rpx","background":"#fff","height":"auto"}'>
					<view v-if="yulexiangmulist.length > 0" @tap="onDetailTap('yulexiangmu',yulexiangmulist[0].id)" class="box box1" :style='{"width":"100%","padding":"0","margin":"0 0 20rpx","position":"relative","height":"auto"}'>
						<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="yulexiangmulist[0].tupian.substring(0,4)=='http'" :src="yulexiangmulist[0].tupian"></image>
						<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="yulexiangmulist[0].tupian?baseUrl+yulexiangmulist[0].tupian.split(',')[0]:''"></image>
						<view :style='{"width":"100%","position":"absolute","left":"0","bottom":"0","background":"rgba(0,0,0,.3)"}'>
							<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">{{yulexiangmulist[0].yulemingcheng}}</view>
							<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">参与人数:{{yulexiangmulist[0].canyurenshu}}</view>
							<view :style='{"padding":"0 20rpx"}'>
								<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
								<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[0].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
							</view>
							<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
								<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
								<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[0].thumbsupnum}}</text>
							</view>
							<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
								<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
								<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[0].storeupnum}}</text>
							</view>
							<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
								<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
								<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[0].clicknum}}</text>
							</view>
						</view>
					</view>
					<view class="list-item" :style='{"width":"100%","padding":"0","margin":"0 0 20rpx","justifyContent":"space-between","display":"flex","height":"auto"}'>
						<view v-if="yulexiangmulist.length > 1" @tap="onDetailTap('yulexiangmu',yulexiangmulist[1].id)" class="box box2" :style='{"width":"48%","padding":"0","margin":"0","position":"relative","height":"auto"}'>
							<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"672rpx"}' class="list-item-image" mode="aspectFill" v-if="yulexiangmulist[1].tupian.substring(0,4)=='http'" :src="yulexiangmulist[1].tupian"></image>
							<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"672rpx"}' class="list-item-image" mode="aspectFill" v-else :src="yulexiangmulist[1].tupian?baseUrl+yulexiangmulist[1].tupian.split(',')[0]:''"></image>
							<view :style='{"width":"100%","position":"absolute","left":"0","bottom":"0","background":"rgba(0,0,0,.3)"}'>
								<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">{{yulexiangmulist[1].yulemingcheng}}</view>
								<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">参与人数:{{yulexiangmulist[1].canyurenshu}}</view>
								<view :style='{"padding":"0 20rpx"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[1].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[1].thumbsupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[1].storeupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[1].clicknum}}</text>
								</view>
							</view>
						</view>
						<view class="list-item-body" :style='{"width":"48%","padding":"0","margin":"0","height":"auto"}'>
							<view v-if="yulexiangmulist.length > 2" @tap="onDetailTap('yulexiangmu',yulexiangmulist[2].id)" class="box box3" :style='{"width":"100%","padding":"0","margin":"0 0 20rpx","position":"relative","height":"auto"}'>
								<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="yulexiangmulist[2].tupian.substring(0,4)=='http'" :src="yulexiangmulist[2].tupian"></image>
								<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="yulexiangmulist[2].tupian?baseUrl+yulexiangmulist[2].tupian.split(',')[0]:''"></image>
								<view :style='{"width":"100%","position":"absolute","left":"0","bottom":"0","background":"rgba(0,0,0,.3)"}'>
									<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">{{yulexiangmulist[2].yulemingcheng}}</view>
									<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">参与人数:{{yulexiangmulist[2].canyurenshu}}</view>
									<view :style='{"padding":"0 20rpx"}'>
										<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
										<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[2].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
									</view>
									<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
										<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
										<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[2].thumbsupnum}}</text>
									</view>
									<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
										<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
										<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[2].storeupnum}}</text>
									</view>
									<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
										<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
										<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[2].clicknum}}</text>
									</view>
								</view>
							</view>
							<view v-if="yulexiangmulist.length > 3" @tap="onDetailTap('yulexiangmu',yulexiangmulist[3].id)" class="box box4" :style='{"width":"100%","padding":"0","margin":"0","position":"relative","height":"auto"}'>
								<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="yulexiangmulist[3].tupian.substring(0,4)=='http'" :src="yulexiangmulist[3].tupian"></image>
								<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="yulexiangmulist[3].tupian?baseUrl+yulexiangmulist[3].tupian.split(',')[0]:''"></image>
								<view :style='{"width":"100%","position":"absolute","left":"0","bottom":"0","background":"rgba(0,0,0,.3)"}'>
									<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">{{yulexiangmulist[3].yulemingcheng}}</view>
									<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">参与人数:{{yulexiangmulist[3].canyurenshu}}</view>
									<view :style='{"padding":"0 20rpx"}'>
										<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
										<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[3].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
									</view>
									<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
										<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
										<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[3].thumbsupnum}}</text>
									</view>
									<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
										<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
										<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[3].storeupnum}}</text>
									</view>
									<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
										<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
										<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[3].clicknum}}</text>
									</view>
								</view>
							</view>
						</view>
					</view>
					<view class="list-item" :style='{"width":"100%","padding":"0","margin":"0","justifyContent":"space-between","display":"flex","height":"auto"}'>
						<view v-if="yulexiangmulist.length > 4" @tap="onDetailTap('yulexiangmu',yulexiangmulist[4].id)" class="box box5" :style='{"width":"48%","padding":"0","margin":"0","position":"relative","height":"auto"}'>
							<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="yulexiangmulist[4].tupian.substring(0,4)=='http'" :src="yulexiangmulist[4].tupian"></image>
							<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="yulexiangmulist[4].tupian?baseUrl+yulexiangmulist[4].tupian.split(',')[0]:''"></image>
							<view :style='{"width":"100%","position":"absolute","left":"0","bottom":"0","background":"rgba(0,0,0,.3)"}'>
								<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">{{yulexiangmulist[4].yulemingcheng}}</view>
								<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">参与人数:{{yulexiangmulist[4].canyurenshu}}</view>
								<view :style='{"padding":"0 20rpx"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[4].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[4].thumbsupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[4].storeupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[4].clicknum}}</text>
								</view>
							</view>
						</view>
						<view v-if="yulexiangmulist.length > 5" @tap="onDetailTap('yulexiangmu',yulexiangmulist[5].id)" class="box box6" :style='{"width":"48%","padding":"0","margin":"0","position":"relative","height":"auto"}'>
							<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-if="yulexiangmulist[5].tupian.substring(0,4)=='http'" :src="yulexiangmulist[5].tupian"></image>
							<image :style='{"width":"100%","objectFit":"cover","display":"block","height":"328rpx"}' class="list-item-image" mode="aspectFill" v-else :src="yulexiangmulist[5].tupian?baseUrl+yulexiangmulist[5].tupian.split(',')[0]:''"></image>
							<view :style='{"width":"100%","position":"absolute","left":"0","bottom":"0","background":"rgba(0,0,0,.3)"}'>
								<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">{{yulexiangmulist[5].yulemingcheng}}</view>
								<view :style='{"padding":"0 20rpx","overflow":"hidden","whiteSpace":"nowrap","color":"#fff","lineHeight":"1.5","fontSize":"32rpx","textOverflow":"ellipsis"}' class="title ">参与人数:{{yulexiangmulist[5].canyurenshu}}</view>
								<view :style='{"padding":"0 20rpx"}'>
									<text class="icon iconfont icon-shijian21" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[5].addtime.split(' ')[0].replace(/\-/g,'-')}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[5].thumbsupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[5].storeupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#fff"}'></text>
									<text :style='{"color":"#fff","lineHeight":"1.5","fontSize":"24rpx"}'>{{yulexiangmulist[5].clicknum}}</text>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
			<!-- 商品推荐 -->
			
			<!-- 商品列表 -->
			<!-- 商品列表 -->
			<!-- 新闻资讯 -->
			<view class="listBox news" :style='{"width":"100%","padding":"0 20rpx","margin":"40rpx 0 0","background":"none","order":"6"}'>
				<view class="title" :style='{"padding":"0 0 0 32rpx","margin":"0 0 20rpx 0","color":"#000","alignItems":"center","display":"flex","letterSpacing":"2rpx","justifyContent":"flex-start","borderRadius":"0","background":"url(http://codegen.caihongy.cn/20250222/13a5b152f35d4a53a5ddbc4d83bab2ff.png) no-repeat left center / auto 100%","width":"100%","fontSize":"32rpx","position":"relative","fontWeight":"600","height":"72rpx"}'>
					<view :style='{"color":"#000","borderRadius":"0 20rpx 0 0","textAlign":"center","background":"none","display":"inline-block","width":"auto","fontSize":"32rpx","lineHeight":"72rpx","fontWeight":"600"}'>社区公告</view>
					<view :style='{"alignItems":"center","top":"0px","color":"#fff","borderRadius":"40rpx","background":"linear-gradient(45deg, #3d4e81, #5753c9, #6e7ff3)","display":"flex","width":"180rpx","position":"absolute","right":"0px","justifyContent":"center","height":"56rpx"}' @tap="onPageTap('news')">
					  <text :style='{"color":"inherit","fontSize":"24rpx"}'>查看更多</text>
					  <text class="icon iconfont icon-gengduo1" :style='{"color":"inherit","fontSize":"24rpx"}'></text>
					</view>
				</view>
				<!-- 样式2 -->
				<view class="list-box style2" :style='{"width":"100%","padding":"20rpx","borderRadius":"20rpx","background":"#fff","height":"auto"}'>
					<view class="tabView" :style='{"width":"100%","flexWrap":"wrap","justifyContent":"center","display":"none"}'>
						<view class="tab" :class="newsIndex2==index?'tabActive':''" v-for="(item,index) in newsCateList2" :key="index" @click="newsTabClick2(index)">
							<text class="icon iconfont icon-zhankai19" :style='{"margin":"0 4rpx 0 0","lineHeight":"68rpx","fontSize":"28rpx","color":"inherit"}'></text>
							<text :style='{"color":"inherit","lineHeight":"68rpx","fontSize":"28rpx"}'>{{item.typename}}</text>
						</view>
					</view>
					<view :style='{"width":"100%","background":"#fff"}'>
						<view @tap="onNewsDetailTap(item.id)" v-for="(item,index) in news" :key="index" class="list-item" :style='{"width":"100%","padding":"20rpx","borderColor":"#5051B5","borderStyle":"dashed","borderWidth":"0 0 2rpx 0","display":"flex"}'>
							<view :style='{"alignItems":"center","color":"#000","background":"#F3F9FF","flexDirection":"column","display":"flex","width":"176rpx","fontSize":"28rpx","justifyContent":"center"}'>
								<view :style='{"color":"inherit","fontSize":"28rpx","lineHeight":"1.5"}'>{{item.addtime.split(" ")[0].split("-")[2]}}</view>
								<view :style='{"color":"inherit","fontSize":"28rpx","lineHeight":"1.5"}'>{{item.addtime.split(" ")[0].split("-")[0]}}-{{item.addtime.split(" ")[0].split("-")[1]}}</view>
							</view>
							<view class="list-item-body" :style='{"padding":"0 0 0 20rpx","flex":"1"}'>
								<view :style='{"padding":"0 20rpx","margin":"0","overflow":"hidden","whiteSpace":"nowrap","color":"#333","width":"100%","lineHeight":"48rpx","fontSize":"28rpx","textOverflow":"ellipsis"}' class="title">{{item.title}}</view>
								<view :style='{"padding":"0 20rpx","overflow":"hidden","color":"#666","width":"100%","lineHeight":"40rpx","fontSize":"24rpx","height":"120rpx"}' class="text">{{item.introduction}}</view>
								<view :style='{"padding":"0 20rpx"}'>
									<text class="icon iconfont icon-geren16" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
									<text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.name}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-zan10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
									<text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.thumbsupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-shoucang10" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
									<text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.storeupnum}}</text>
								</view>
								<view :style='{"padding":"0 20rpx","display":"inline-block"}'>
									<text class="icon iconfont icon-chakan9" :style='{"margin":"0 4rpx 0 0","lineHeight":"1.5","fontSize":"24rpx","color":"#666"}'></text>
									<text :style='{"color":"#666","lineHeight":"1.5","fontSize":"24rpx"}'>{{item.clicknum}}</text>
								</view>
							</view>
						</view>
					</view>
				</view>
			</view>
			<!-- 新闻资讯 -->
			<view v-if="scrollTop>200" @tap="scrollTopClick" :style='{"boxShadow":"0 8rpx 16rpx rgba(0,0,0,.3)","borderRadius":"50%","textAlign":"center","bottom":"20%","background":"#ff000030","width":"60rpx","lineHeight":"60rpx","position":"fixed","right":"20rpx","height":"60rpx"}'>
				<span class="icon iconfont icon-jiantou07" :style='{"color":"#fff"}'></span>
			</view>
		</view>
	</view>
</template>

<script>
    import menu from '@/utils/menu'
	import '@/assets/css/global-restaurant.css'
	import uniIcons from "@/components/uni-ui/lib/uni-icons/uni-icons.vue"
	export default {
		components: {
			uniIcons
		},
		data() {
			return {
				startX: 0,
				prevNumList4: '',
				numList4: 0,
				timerList4: null,
				flagList4: false,
				navigationActive: {"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#F7EFDB","height":"16rpx"},
				navigationDefault: {"width":"16rpx","margin":"0 4rpx","borderRadius":"100%","background":"#fff","height":"16rpx"},
				options2: {
					effect: 'flip',
					loop : true
				},
				options3: {
					effect: 'cube',
					loop : true,
					cubeEffect: {
						shadow: true,
						slideShadows: true,
						shadowOffset: 20,
						shadowScale: 0.94,
					}
				},
				rows: 2,
				column: 4,
				iconArr: [
					'cuIcon-same',
					'cuIcon-deliver',
					'cuIcon-evaluate',
					'cuIcon-shop',
					'cuIcon-ticket',
					'cuIcon-cascades',
					'cuIcon-discover',
					'cuIcon-question',
					'cuIcon-pic',
					'cuIcon-filter',
					'cuIcon-footprint',
					'cuIcon-pulldown',
					'cuIcon-pullup',
					'cuIcon-moreandroid',
					'cuIcon-refund',
					'cuIcon-qrcode',
					'cuIcon-remind',
					'cuIcon-profile',
					'cuIcon-home',
					'cuIcon-message',
					'cuIcon-link',
					'cuIcon-lock',
					'cuIcon-unlock',
					'cuIcon-vip',
					'cuIcon-weibo',
					'cuIcon-activity',
					'cuIcon-friendadd',
					'cuIcon-friendfamous',
					'cuIcon-friend',
					'cuIcon-goods',
					'cuIcon-selection'
				],
				role : '',
				menuList: [],
				swiperMenuList:[],
				user: {},
				tableName:'',
				menuColor: '#3e3aa6,#67c23a,#909399,#e6a23c,#f56c6c,#356c6c,#351c6c,#f093a9,#a7c23a,#104eff,#10441f,#a21233,#503319'.split(','),

				//轮播
				swiperList: [],
				yulexiangmulist: [],
				news: [],
				newsCateList2: [],
				newsIndex2: 0,
				scrollTop: 0,
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop
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
		async onLoad(){
			this.menuColor = this.menuColor.sort(()=> {
				return (0.5-Math.random());
			});
		},
		async onShow() {
			if (this.timerList4&&this.timerList4!=null) clearInterval(this.timerList4)
			this.swiperMenuList = []
			this.role = uni.getStorageSync("appRole");
			let table = uni.getStorageSync("nowTable");
			let res = {}
			if(table) {
				res = await this.$api.session(table);
				this.user = res.data;
				this.tableName = table;
			}
			let menus = menu.list();
			this.menuList = menus;
			this.menuList.forEach((item,key) => {
				if(key==0) {
					item.frontMenu.forEach((item2,key2) => {
						if(item2.child[0].buttons.indexOf("查看")>-1) {
							this.swiperMenuList.push(item2);
						}
					})
				}
			})
			// let res;
			// 轮播图
			let swiperList = []
			res = await this.$api.list('config', {
				page: 1,
				limit: 5
			});
			for (let item of res.data.list) {
				if (item.name.indexOf('picture') >= 0 && item.value && item.value!="" && item.value!=null ) {
					swiperList.push({
						img: item.value,
						title: item.name,
						url: item.url
					});
				}
			}
			if (swiperList) {
				this.swiperList = swiperList;
			}
			
			this.prevNumList4 = this.swiperList.length - 1
			this.timerList4 = setInterval(this.autoPlayList4, 3000)

			if(uni.getStorageSync("appUserid")) {
				res = await this.$api.page('newstype', {page:1,limit:100});
			} else {
				res = await this.$api.list('newstype', {page:1,limit:100});
			}
			res.data.list.splice(0,0,{id:0,typename:'全部'})
			this.newsIndex2 = 0
			this.newsCateList2 = res.data.list
			// 推荐信息
			this.getRecommendList()
			this.getHomeList()
			this.getNewsList()
		},
		methods: {
			scrollTopClick(){
				uni.pageScrollTo({
					scrollTop: 0
				})
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
			newsTabClick2(index){
				this.newsIndex2 = index
				this.getNewsList()
			},
			async getNewsList(){
				let res;
				let params = {
					page: 1,
					limit: 4,
					sort: 'id',
					order: 'desc',
				}
				if(this.newsIndex2){
					params.typename = this.newsCateList2[this.newsIndex2].typename
				}
				// 社区公告
				res = await this.$api.list('news', params)
				this.news = res.data.list
			},
			homeTabClick2(index,name){
				this['home' + name + 'Index2'] = index
				this.getHomeList()
			},
			async getHomeList(){
				let res;
				let params;
			},
			recommendTabClick2(index,name){
				this[name + 'Index2'] = index
				this.getRecommendList()
			},
			async getRecommendList(){
				let res;
				let params;
				// 推荐信息
				params = {
					page: 1,
					limit: 6,
				}
				if(uni.getStorageSync("appUserid")) {
					res = await this.$api.recommend2('yulexiangmu', params);
				} else {
					res = await this.$api.recommend('yulexiangmu', params);
				}
				this.yulexiangmulist = res.data.list
				

			},
			autoPlayList4() {
				this.prevNumList4 = this.numList4
			
				this.numList4++
				if (this.numList4 == this.swiperList.length) this.numList4 = 0
			},
			touchStart(event) {
				this.startX = event.touches[0].clientX
				
				clearInterval(this.timerList4)
				this.flagList4 = true
			},
			touchMove(event) {
				const currentX = event.touches[0].clientX;
				const deltaX = currentX - this.startX;
				
				if (deltaX > 50) {
					// 向右滑动逻辑
					if (this.flagList4) {
						this.flagList4 = false
						
						this.prevNumList4 = this.numList4
						this.numList4++
						if (this.numList4 == this.swiperList.length) this.numList4 = 0
					}
					
				} else if (deltaX < -50) {
					// 向左滑动逻辑
					if (this.flagList4) {
						this.flagList4 = false
						
						this.prevNumList4 = this.numList4
						this.numList4--
						if (this.numList4 < 0) this.numList4 = this.swiperList.length - 1
					}
				}
			},
			touchEnd() {
				this.startX = 0
				this.timerList4 = setInterval(this.autoPlayList4, 3000)
				this.flagList4 = false
			},
			//轮播图跳转
			onSwiperTap(e) {
				if(e.url) {
					if (e.url.indexOf('https') != -1) {
						// #ifdef MP-WEIXIN
						uni.navigateTo({
						    url: '../../common/linkOthers/linkOthers?url=' + encodeURIComponent(e.url),
						});
						return false
						// #endif
						window.open(e.url)
					} else {
						this.$utils.jump(e.url)
					}
				}
			},
			// 新闻详情
			onNewsDetailTap(id) {
				this.$utils.jump(`../news-detail/news-detail?id=${id}`)
			},
			// 推荐列表点击详情
			onDetailTap(tableName, id) {
				this.$utils.jump(`../${tableName}/detail?id=${id}`)
			},
			onPageTap(tableName){
				uni.navigateTo({
					url: `../${tableName}/list`,
					fail: function(){
						uni.switchTab({
							url: `../${tableName}/list`
						});
					}
				});
				// this.$utils.jump(`../${tableName}/list`)
			},
			onPageTap2(index) {
				let url = '../' + index + '/list'
				if(index=='forum'){
					url = '../forum-index/forum-index'
				}
				uni.setStorageSync("useridTag",0);
				uni.navigateTo({
					url: url,
					fail: function() {
						uni.switchTab({
							url: url
						});
					}
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		min-height: calc(100vh - 44px);
		box-sizing: border-box;
	}
	.list-swiper-4 .animate__animated {
		--animate-delay: 300ms;
	}

	.news {
		.style2 {
			.tabView {
				.tab {
					border: 2rpx solid #13b400;
					border-radius: 40rpx;
					padding: 0 30rpx;
					margin: 0 10rpx;
					color: #13b400;
					background: #fff;
				}
				.tabActive {
					border: 2rpx solid #13b400;
					border-radius: 40rpx;
					padding: 0 20rpx;
					margin: 0 10rpx;
					color: #fff;
					background: #13b400;
				}
			}
		}
	}
	.menu_view {
		border-radius: 20rpx;
		padding: 0;
		margin: 0 auto;
		background: #FFFFFF;
		display: flex;
		width: calc(100% - 40rpx);
		justify-content: flex-start;
		flex-wrap: wrap;
		height: auto;
		.menu-item {
			padding: 12rpx 0;
			margin: 10rpx 0;
			width: 25%;
			height: auto;
			.iconarr {
				border-radius: 100%;
				padding: 0;
				margin: 0px auto;
				color: #3e3aa6;
				background: none;
				display: block;
				width: 64rpx;
				font-size: 64rpx;
				line-height: 64rpx;
				height: 64rpx;
			}
			.text {
				padding: 0;
				margin: 12rpx auto 0;
				color: #3e3aa6;
				width: 100%;
				font-size: 28rpx;
				line-height: 28rpx;
				text-align: center;
			}
		}
	}
</style>
