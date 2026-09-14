import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import jumin from '@/views/modules/jumin/list'
	import storeup from '@/views/modules/storeup/list'
	import discussyulexiangmu from '@/views/modules/discussyulexiangmu/list'
	import forum from '@/views/modules/forum/list'
	import forumtype from '@/views/modules/forumtype/list'
	import xiangmuleixing from '@/views/modules/xiangmuleixing/list'
	import forumreport from '@/views/modules/forumreport/list'
	import canyuxinxi from '@/views/modules/canyuxinxi/list'
	import discussshequhuodong from '@/views/modules/discussshequhuodong/list'
	import shequhuodong from '@/views/modules/shequhuodong/list'
	import yulexiangmu from '@/views/modules/yulexiangmu/list'
	import config from '@/views/modules/config/list'
	import huodongleixing from '@/views/modules/huodongleixing/list'
	import baomingshenqing from '@/views/modules/baomingshenqing/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '社区公告',
		component: news
	}
	,{
		path: '/jumin',
		name: '居民',
		component: jumin
	}
	,{
		path: '/storeup',
		name: '我的收藏',
		component: storeup
	}
	,{
		path: '/discussyulexiangmu',
		name: '娱乐项目评论',
		component: discussyulexiangmu
	}
	,{
		path: '/forum',
		name: '社区互动',
		component: forum
	}
	,{
		path: '/forumtype',
		name: '论坛分类',
		component: forumtype
	}
	,{
		path: '/xiangmuleixing',
		name: '项目类型',
		component: xiangmuleixing
	}
	,{
		path: '/forumreport',
		name: '举报记录',
		component: forumreport
	}
	,{
		path: '/canyuxinxi',
		name: '参与信息',
		component: canyuxinxi
	}
	,{
		path: '/discussshequhuodong',
		name: '社区活动评论',
		component: discussshequhuodong
	}
	,{
		path: '/shequhuodong',
		name: '社区活动',
		component: shequhuodong
	}
	,{
		path: '/yulexiangmu',
		name: '娱乐项目',
		component: yulexiangmu
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/huodongleixing',
		name: '活动类型',
		component: huodongleixing
	}
	,{
		path: '/baomingshenqing',
		name: '报名申请',
		component: baomingshenqing
	}
	,{
		path: '/newstype',
		name: '社区公告分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
