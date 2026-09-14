const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootjm78648u/",
            name: "springbootjm78648u",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootjm78648u/front/h5/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于微信小程序的智慧社区娱乐服务管理平台"
        } 
    }
}
export default base
