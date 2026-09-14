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

import com.entity.CanyuxinxiEntity;
import com.entity.view.CanyuxinxiView;

import com.service.CanyuxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 参与信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-02 12:00:37
 */
@RestController
@RequestMapping("/canyuxinxi")
public class CanyuxinxiController {
    @Autowired
    private CanyuxinxiService canyuxinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CanyuxinxiEntity canyuxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jumin")) {
			canyuxinxi.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CanyuxinxiEntity> ew = new EntityWrapper<CanyuxinxiEntity>();


        //查询结果
		PageUtils page = canyuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, canyuxinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CanyuxinxiEntity canyuxinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CanyuxinxiEntity> ew = new EntityWrapper<CanyuxinxiEntity>();

        //查询结果
		PageUtils page = canyuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, canyuxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CanyuxinxiEntity canyuxinxi){
       	EntityWrapper<CanyuxinxiEntity> ew = new EntityWrapper<CanyuxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( canyuxinxi, "canyuxinxi")); 
        return R.ok().put("data", canyuxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CanyuxinxiEntity canyuxinxi){
        EntityWrapper< CanyuxinxiEntity> ew = new EntityWrapper< CanyuxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( canyuxinxi, "canyuxinxi")); 
		CanyuxinxiView canyuxinxiView =  canyuxinxiService.selectView(ew);
		return R.ok("查询参与信息成功").put("data", canyuxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CanyuxinxiEntity canyuxinxi = canyuxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(canyuxinxi,deSens);
        return R.ok().put("data", canyuxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CanyuxinxiEntity canyuxinxi = canyuxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(canyuxinxi,deSens);
        return R.ok().put("data", canyuxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CanyuxinxiEntity canyuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(canyuxinxi);
        canyuxinxiService.insert(canyuxinxi);
        return R.ok().put("data",canyuxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CanyuxinxiEntity canyuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(canyuxinxi);
        canyuxinxiService.insert(canyuxinxi);
        return R.ok().put("data",canyuxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CanyuxinxiEntity canyuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(canyuxinxi);
        //全部更新
        canyuxinxiService.updateById(canyuxinxi);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<CanyuxinxiEntity> list = new ArrayList<CanyuxinxiEntity>();
        for(Long id : ids) {
            CanyuxinxiEntity canyuxinxi = canyuxinxiService.selectById(id);
            canyuxinxi.setSfsh(sfsh);
            canyuxinxi.setShhf(shhf);
            list.add(canyuxinxi);
        }
        canyuxinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        canyuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
