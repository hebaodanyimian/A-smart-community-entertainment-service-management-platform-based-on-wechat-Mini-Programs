package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.entity.TokenEntity;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.JuminEntity;
import com.entity.view.JuminView;

import com.service.JuminService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.entity.WxLoginParam;
import com.utils.WechatUtil;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 居民
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-02 12:00:35
 */
@RestController
@RequestMapping("/jumin")
public class JuminController {
    @Autowired
    private JuminService juminService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        JuminEntity u = juminService.selectOne(new EntityWrapper<JuminEntity>().eq("zhanghao", username));
        // 判断用户锁定状态
        if(u!=null && u.getStatus().intValue()==1) {
            //返回已锁定提示
            return R.error("账号已锁定，请联系管理员。");
        }
        // 当用户不存在或验证密码不通过时
		if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
		
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"jumin",  "居民" );
        //返回token
		return R.ok().put("token", token);
	}


    /**
     * 微信登录
     */
    @RequestMapping(value = "/wx/login")
    @IgnoreAuth
    public R wxLogin(@RequestBody WxLoginParam param) {
        String token = null;
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        com.alibaba.fastjson.JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(param.getCode());

        // 3.接收微信接口服务  获取返回的参数
        String openId = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        if (StringUtils.isBlank(openId) && StringUtils.isBlank(sessionKey)) {
            return R.error("接口请求失败！");
        }

        com.alibaba.fastjson.JSONObject object = WechatUtil.getUserInfo(param.getEncryptedData(), sessionKey, param.getIv());
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(object));
        
        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(param.getRawData() + sessionKey);
        if (!param.getSignature().equals(signature2)) {
            return R.error("签名校验失败");
        }

        JuminEntity user = juminService.selectOne(new EntityWrapper<JuminEntity>().eq("openid", openId));
        if (user == null) {
            return R.error("请登录账号绑定微信后再进行微信登录。");
        } else {
            //已绑定，登录成功
            token = tokenService.generateToken(user.getId(), user.getZhanghao(),"jumin", "居民");
        }

        //返回token及登录账号
        return R.ok().put("token", token).put("username", user.getZhanghao());
    }
    
    /**
     * 微信账号绑定
     */
    @RequestMapping(value = "/wx/bind")
    public R wxBind(@RequestBody WxLoginParam param , HttpServletRequest request){
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        com.alibaba.fastjson.JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(param.getCode());

        // 3.接收微信接口服务  获取返回的参数
        String openId = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");

        if (StringUtils.isBlank(openId) && StringUtils.isBlank(sessionKey)) {
            return R.error("接口请求失败！");
        }

        com.alibaba.fastjson.JSONObject object = WechatUtil.getUserInfo(param.getEncryptedData(), sessionKey, param.getIv());
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(object));
        
        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(param.getRawData() + sessionKey);
        if (!param.getSignature().equals(signature2)) {
            return R.error("签名校验失败");
        }
        String rawData = param.getRawData();
        com.alibaba.fastjson.JSONObject rawDataJson = com.alibaba.fastjson.JSON.parseObject(rawData);
        JuminEntity user = juminService.selectOne(new EntityWrapper<JuminEntity>().eq("openid", openId));
        if (user == null) {
            Long id = (Long)request.getSession().getAttribute("userId");
            user = juminService.selectById(id);
            if(user!=null) {
                user.setOpenid(openId);
                user.setNickname(rawDataJson.getString("nickName"));
                user.setAvatarurl(rawDataJson.getString("avatarUrl"));
            }
            juminService.updateById(user);
        } else {
            return R.error("账号已被绑定");
        }
        return R.ok("绑定成功");
    }
    
    /**
     * 微信账号解绑
     */
    @RequestMapping(value = "/wx/unbind")
    public R wxUnbind(HttpServletRequest request){
        Long id = (Long)request.getSession().getAttribute("userId");
        JuminEntity user = juminService.selectById(id);
        if(StringUtils.isNotBlank(user.getOpenid())) {
            user.setOpenid("");
            user.setNickname("");
            user.setAvatarurl("");
        } else {
            return R.error("账号已解绑");
        }
        juminService.updateById(user);
        return R.ok("解绑成功");
    }
	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JuminEntity jumin){
    	//ValidatorUtils.validateEntity(jumin);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	JuminEntity u = juminService.selectOne(new EntityWrapper<JuminEntity>().eq("zhanghao", jumin.getZhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同账号，否则返回错误信息
        if(juminService.selectCount(new EntityWrapper<JuminEntity>().eq("zhanghao", jumin.getZhanghao()))>0) {
            return R.error("账号已存在");
        }
		Long uId = new Date().getTime();
		jumin.setId(uId);
        //保存用户
        juminService.insert(jumin);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        JuminEntity u = juminService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        JuminEntity u = juminService.selectOne(new EntityWrapper<JuminEntity>().eq("zhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        juminService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JuminEntity jumin,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JuminEntity> ew = new EntityWrapper<JuminEntity>();


        //查询结果
		PageUtils page = juminService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jumin), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JuminEntity jumin, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JuminEntity> ew = new EntityWrapper<JuminEntity>();

        //查询结果
		PageUtils page = juminService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jumin), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JuminEntity jumin){
       	EntityWrapper<JuminEntity> ew = new EntityWrapper<JuminEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jumin, "jumin")); 
        return R.ok().put("data", juminService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JuminEntity jumin){
        EntityWrapper< JuminEntity> ew = new EntityWrapper< JuminEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jumin, "jumin")); 
		JuminView juminView =  juminService.selectView(ew);
		return R.ok("查询居民成功").put("data", juminView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JuminEntity jumin = juminService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jumin,deSens);
        return R.ok().put("data", jumin);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JuminEntity jumin = juminService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jumin,deSens);
        return R.ok().put("data", jumin);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JuminEntity jumin, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(juminService.selectCount(new EntityWrapper<JuminEntity>().eq("zhanghao", jumin.getZhanghao()))>0) {
            return R.error("账号已存在");
        }
        //ValidatorUtils.validateEntity(jumin);
        //验证账号唯一性，否则返回错误信息
        JuminEntity u = juminService.selectOne(new EntityWrapper<JuminEntity>().eq("zhanghao", jumin.getZhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	jumin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		jumin.setId(new Date().getTime());
        juminService.insert(jumin);
        return R.ok().put("data",jumin.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JuminEntity jumin, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(juminService.selectCount(new EntityWrapper<JuminEntity>().eq("zhanghao", jumin.getZhanghao()))>0) {
            return R.error("账号已存在");
        }
        //ValidatorUtils.validateEntity(jumin);
        //验证账号唯一性，否则返回错误信息
        JuminEntity u = juminService.selectOne(new EntityWrapper<JuminEntity>().eq("zhanghao", jumin.getZhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	jumin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		jumin.setId(new Date().getTime());
        juminService.insert(jumin);
        return R.ok().put("data",jumin.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JuminEntity jumin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jumin);
        //验证字段唯一性，否则返回错误信息
        if(juminService.selectCount(new EntityWrapper<JuminEntity>().ne("id", jumin.getId()).eq("zhanghao", jumin.getZhanghao()))>0) {
            return R.error("账号已存在");
        }
        //全部更新
        juminService.updateById(jumin);
        if(null!=jumin.getZhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(jumin.getZhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", jumin.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        juminService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    







    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,JuminEntity jumin, HttpServletRequest request){
        EntityWrapper<JuminEntity> ew = new EntityWrapper<JuminEntity>();
        int count = juminService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jumin), params), params));
        return R.ok().put("data", count);
    }

}
