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

import com.entity.XiangmuleixingEntity;
import com.entity.view.XiangmuleixingView;

import com.service.XiangmuleixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 项目类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-02 12:00:36
 */
@RestController
@RequestMapping("/xiangmuleixing")
public class XiangmuleixingController {
    @Autowired
    private XiangmuleixingService xiangmuleixingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XiangmuleixingEntity xiangmuleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<XiangmuleixingEntity> ew = new EntityWrapper<XiangmuleixingEntity>();


        //查询结果
		PageUtils page = xiangmuleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiangmuleixing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,XiangmuleixingEntity xiangmuleixing, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<XiangmuleixingEntity> ew = new EntityWrapper<XiangmuleixingEntity>();

        //查询结果
		PageUtils page = xiangmuleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiangmuleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XiangmuleixingEntity xiangmuleixing){
       	EntityWrapper<XiangmuleixingEntity> ew = new EntityWrapper<XiangmuleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xiangmuleixing, "xiangmuleixing")); 
        return R.ok().put("data", xiangmuleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XiangmuleixingEntity xiangmuleixing){
        EntityWrapper< XiangmuleixingEntity> ew = new EntityWrapper< XiangmuleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xiangmuleixing, "xiangmuleixing")); 
		XiangmuleixingView xiangmuleixingView =  xiangmuleixingService.selectView(ew);
		return R.ok("查询项目类型成功").put("data", xiangmuleixingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XiangmuleixingEntity xiangmuleixing = xiangmuleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xiangmuleixing,deSens);
        return R.ok().put("data", xiangmuleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XiangmuleixingEntity xiangmuleixing = xiangmuleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xiangmuleixing,deSens);
        return R.ok().put("data", xiangmuleixing);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XiangmuleixingEntity xiangmuleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmuleixing);
        xiangmuleixingService.insert(xiangmuleixing);
        return R.ok().put("data",xiangmuleixing.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XiangmuleixingEntity xiangmuleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmuleixing);
        xiangmuleixingService.insert(xiangmuleixing);
        return R.ok().put("data",xiangmuleixing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XiangmuleixingEntity xiangmuleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmuleixing);
        //全部更新
        xiangmuleixingService.updateById(xiangmuleixing);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xiangmuleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
