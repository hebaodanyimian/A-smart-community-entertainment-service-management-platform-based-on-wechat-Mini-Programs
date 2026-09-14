package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
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

import com.entity.DiscussyulexiangmuEntity;
import com.entity.view.DiscussyulexiangmuView;

import com.service.DiscussyulexiangmuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 娱乐项目评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-02 12:00:38
 */
@RestController
@RequestMapping("/discussyulexiangmu")
public class DiscussyulexiangmuController {
    @Autowired
    private DiscussyulexiangmuService discussyulexiangmuService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussyulexiangmuEntity discussyulexiangmu,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyulexiangmuEntity> ew = new EntityWrapper<DiscussyulexiangmuEntity>();


        //查询结果
		PageUtils page = discussyulexiangmuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyulexiangmu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussyulexiangmuEntity discussyulexiangmu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussyulexiangmuEntity> ew = new EntityWrapper<DiscussyulexiangmuEntity>();

        //查询结果
		PageUtils page = discussyulexiangmuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyulexiangmu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussyulexiangmuEntity discussyulexiangmu){
       	EntityWrapper<DiscussyulexiangmuEntity> ew = new EntityWrapper<DiscussyulexiangmuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussyulexiangmu, "discussyulexiangmu")); 
        return R.ok().put("data", discussyulexiangmuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussyulexiangmuEntity discussyulexiangmu){
        EntityWrapper< DiscussyulexiangmuEntity> ew = new EntityWrapper< DiscussyulexiangmuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussyulexiangmu, "discussyulexiangmu")); 
		DiscussyulexiangmuView discussyulexiangmuView =  discussyulexiangmuService.selectView(ew);
		return R.ok("查询娱乐项目评论表成功").put("data", discussyulexiangmuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussyulexiangmuEntity discussyulexiangmu = discussyulexiangmuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyulexiangmu,deSens);
        return R.ok().put("data", discussyulexiangmu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussyulexiangmuEntity discussyulexiangmu = discussyulexiangmuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussyulexiangmu,deSens);
        return R.ok().put("data", discussyulexiangmu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussyulexiangmuEntity discussyulexiangmu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyulexiangmu);
        discussyulexiangmuService.insert(discussyulexiangmu);
        return R.ok().put("data",discussyulexiangmu.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussyulexiangmuEntity discussyulexiangmu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyulexiangmu);
        discussyulexiangmuService.insert(discussyulexiangmu);
        return R.ok().put("data",discussyulexiangmu.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussyulexiangmuEntity discussyulexiangmu = discussyulexiangmuService.selectOne(new EntityWrapper<DiscussyulexiangmuEntity>().eq("", username));
        return R.ok().put("data", discussyulexiangmu);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussyulexiangmuEntity discussyulexiangmu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussyulexiangmu);
        //全部更新
        discussyulexiangmuService.updateById(discussyulexiangmu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussyulexiangmuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussyulexiangmuEntity discussyulexiangmu, HttpServletRequest request,String pre){
        EntityWrapper<DiscussyulexiangmuEntity> ew = new EntityWrapper<DiscussyulexiangmuEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = discussyulexiangmuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussyulexiangmu), params), params));
        return R.ok().put("data", page);
    }








}
