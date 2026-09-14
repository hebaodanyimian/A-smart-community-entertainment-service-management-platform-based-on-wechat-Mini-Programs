package com.dao;

import com.entity.YulexiangmuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YulexiangmuVO;
import com.entity.view.YulexiangmuView;


/**
 * 娱乐项目
 * 
 * @author 
 * @email 
 * @date 2025-04-02 12:00:36
 */
public interface YulexiangmuDao extends BaseMapper<YulexiangmuEntity> {
	
	List<YulexiangmuVO> selectListVO(@Param("ew") Wrapper<YulexiangmuEntity> wrapper);
	
	YulexiangmuVO selectVO(@Param("ew") Wrapper<YulexiangmuEntity> wrapper);
	
	List<YulexiangmuView> selectListView(@Param("ew") Wrapper<YulexiangmuEntity> wrapper);

	List<YulexiangmuView> selectListView(Pagination page,@Param("ew") Wrapper<YulexiangmuEntity> wrapper);

	
	YulexiangmuView selectView(@Param("ew") Wrapper<YulexiangmuEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YulexiangmuEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YulexiangmuEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YulexiangmuEntity> wrapper);



}
