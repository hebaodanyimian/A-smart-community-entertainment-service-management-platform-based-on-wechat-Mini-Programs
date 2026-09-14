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

import com.entity.BaomingshenqingEntity;
import com.entity.view.BaomingshenqingView;

import com.service.BaomingshenqingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 报名申请
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-02 12:00:36
 */
@RestController
@RequestMapping("/baomingshenqing")
public class BaomingshenqingController {
    @Autowired
    private BaomingshenqingService baomingshenqingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BaomingshenqingEntity baomingshenqing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<BaomingshenqingEntity> ew = new EntityWrapper<BaomingshenqingEntity>();

        String tableName = request.getSession().getAttribute("tableName").toString();
        ew.andNew();
        if(tableName.equals("jumin")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("jumin")) {
            ew.or();
            ew.eq("canyuzhanghao", (String)request.getSession().getAttribute("username"));
        }
        ew.andNew("1=1");

        //查询结果
		PageUtils page = baomingshenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baomingshenqing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,BaomingshenqingEntity baomingshenqing, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<BaomingshenqingEntity> ew = new EntityWrapper<BaomingshenqingEntity>();

        //查询结果
		PageUtils page = baomingshenqingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, baomingshenqing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BaomingshenqingEntity baomingshenqing){
       	EntityWrapper<BaomingshenqingEntity> ew = new EntityWrapper<BaomingshenqingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( baomingshenqing, "baomingshenqing")); 
        return R.ok().put("data", baomingshenqingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BaomingshenqingEntity baomingshenqing){
        EntityWrapper< BaomingshenqingEntity> ew = new EntityWrapper< BaomingshenqingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( baomingshenqing, "baomingshenqing")); 
		BaomingshenqingView baomingshenqingView =  baomingshenqingService.selectView(ew);
		return R.ok("查询报名申请成功").put("data", baomingshenqingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BaomingshenqingEntity baomingshenqing = baomingshenqingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(baomingshenqing,deSens);
        return R.ok().put("data", baomingshenqing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BaomingshenqingEntity baomingshenqing = baomingshenqingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(baomingshenqing,deSens);
        return R.ok().put("data", baomingshenqing);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BaomingshenqingEntity baomingshenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baomingshenqing);
        baomingshenqingService.insert(baomingshenqing);
        return R.ok().put("data",baomingshenqing.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BaomingshenqingEntity baomingshenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baomingshenqing);
        baomingshenqingService.insert(baomingshenqing);
        return R.ok().put("data",baomingshenqing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BaomingshenqingEntity baomingshenqing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(baomingshenqing);
        //全部更新
        baomingshenqingService.updateById(baomingshenqing);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<BaomingshenqingEntity> list = new ArrayList<BaomingshenqingEntity>();
        for(Long id : ids) {
            BaomingshenqingEntity baomingshenqing = baomingshenqingService.selectById(id);
            baomingshenqing.setSfsh(sfsh);
            baomingshenqing.setShhf(shhf);
            list.add(baomingshenqing);
        }
        baomingshenqingService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        baomingshenqingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
