package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YulexiangmuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YulexiangmuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YulexiangmuView;


/**
 * 娱乐项目
 *
 * @author 
 * @email 
 * @date 2025-04-02 12:00:36
 */
public interface YulexiangmuService extends IService<YulexiangmuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YulexiangmuVO> selectListVO(Wrapper<YulexiangmuEntity> wrapper);
   	
   	YulexiangmuVO selectVO(@Param("ew") Wrapper<YulexiangmuEntity> wrapper);
   	
   	List<YulexiangmuView> selectListView(Wrapper<YulexiangmuEntity> wrapper);
   	
   	YulexiangmuView selectView(@Param("ew") Wrapper<YulexiangmuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YulexiangmuEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<YulexiangmuEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<YulexiangmuEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<YulexiangmuEntity> wrapper);



}

