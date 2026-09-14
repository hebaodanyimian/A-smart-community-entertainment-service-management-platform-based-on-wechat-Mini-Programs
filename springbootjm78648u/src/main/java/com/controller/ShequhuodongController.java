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

import com.entity.ShequhuodongEntity;
import com.entity.view.ShequhuodongView;

import com.service.ShequhuodongService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 社区活动
 * 后端接口
 * @author 
 * @email 
 * @date 2025-04-02 12:00:36
 */
@RestController
@RequestMapping("/shequhuodong")
public class ShequhuodongController {
    @Autowired
    private ShequhuodongService shequhuodongService;

    @Autowired
    private StoreupService storeupService;





    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShequhuodongEntity shequhuodong,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jumin")) {
			shequhuodong.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ShequhuodongEntity> ew = new EntityWrapper<ShequhuodongEntity>();


        //查询结果
		PageUtils page = shequhuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shequhuodong), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ShequhuodongEntity shequhuodong, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ShequhuodongEntity> ew = new EntityWrapper<ShequhuodongEntity>();

        //查询结果
		PageUtils page = shequhuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shequhuodong), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShequhuodongEntity shequhuodong){
       	EntityWrapper<ShequhuodongEntity> ew = new EntityWrapper<ShequhuodongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shequhuodong, "shequhuodong")); 
        return R.ok().put("data", shequhuodongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShequhuodongEntity shequhuodong){
        EntityWrapper< ShequhuodongEntity> ew = new EntityWrapper< ShequhuodongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shequhuodong, "shequhuodong")); 
		ShequhuodongView shequhuodongView =  shequhuodongService.selectView(ew);
		return R.ok("查询社区活动成功").put("data", shequhuodongView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShequhuodongEntity shequhuodong = shequhuodongService.selectById(id);
        if(null==shequhuodong.getClicknum()){
            shequhuodong.setClicknum(0);
        }
		shequhuodong.setClicknum(shequhuodong.getClicknum()+1);
		shequhuodongService.updateById(shequhuodong);
        shequhuodong = shequhuodongService.selectView(new EntityWrapper<ShequhuodongEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shequhuodong,deSens);
        return R.ok().put("data", shequhuodong);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShequhuodongEntity shequhuodong = shequhuodongService.selectById(id);
        if(null==shequhuodong.getClicknum()){
            shequhuodong.setClicknum(0);
        }
		shequhuodong.setClicknum(shequhuodong.getClicknum()+1);
		shequhuodongService.updateById(shequhuodong);
        shequhuodong = shequhuodongService.selectView(new EntityWrapper<ShequhuodongEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shequhuodong,deSens);
        return R.ok().put("data", shequhuodong);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        ShequhuodongEntity shequhuodong = shequhuodongService.selectById(id);
        if(type.equals("1")) {
        	shequhuodong.setThumbsupnum(shequhuodong.getThumbsupnum()+1);
        } else {
        	shequhuodong.setCrazilynum(shequhuodong.getCrazilynum()+1);
        }
        shequhuodongService.updateById(shequhuodong);
        return R.ok("投票成功");
    }

    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShequhuodongEntity shequhuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shequhuodong);
        shequhuodongService.insert(shequhuodong);
        return R.ok().put("data",shequhuodong.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShequhuodongEntity shequhuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shequhuodong);
        shequhuodongService.insert(shequhuodong);
        return R.ok().put("data",shequhuodong.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ShequhuodongEntity shequhuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shequhuodong);
        //全部更新
        shequhuodongService.updateById(shequhuodong);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shequhuodongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    






    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_shequhuodong_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<ShequhuodongEntity> ew = new EntityWrapper<ShequhuodongEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
                                                    if(tableName.equals("jumin")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
                                            //获取结果
        List<Map<String, Object>> result = shequhuodongService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        Collections.sort(result, (map1, map2) -> {
            // 假设 total 总是存在并且是数值类型
            Number total1 = (Number) map1.get("total");
            Number total2 = (Number) map2.get("total");
            if(total1==null)
            {
                total1 = 0;
            }
            if(total2==null)
            {
                total2 = 0;
            }
            return Double.compare(total2.doubleValue(), total1.doubleValue());
        });
        return R.ok().put("data", result);
    }
    
    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul,HttpServletRequest request)  throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_shequhuodong_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<ShequhuodongEntity> ew = new EntityWrapper<ShequhuodongEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jumin")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = shequhuodongService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }
    
    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_shequhuodong_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<ShequhuodongEntity> ew = new EntityWrapper<ShequhuodongEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jumin")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = shequhuodongService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }
    
    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) throws IOException
    {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_shequhuodong_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<ShequhuodongEntity> ew = new EntityWrapper<ShequhuodongEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jumin")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = shequhuodongService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }
    
    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("group_shequhuodong_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<ShequhuodongEntity> ew = new EntityWrapper<ShequhuodongEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jumin")) {
            ew.eq("zhanghao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = shequhuodongService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }    




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,ShequhuodongEntity shequhuodong, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("jumin")) {
            shequhuodong.setZhanghao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<ShequhuodongEntity> ew = new EntityWrapper<ShequhuodongEntity>();
        int count = shequhuodongService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shequhuodong), params), params));
        return R.ok().put("data", count);
    }

}
