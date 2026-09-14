package com.dao;

import com.entity.DiscussyulexiangmuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussyulexiangmuVO;
import com.entity.view.DiscussyulexiangmuView;


/**
 * 娱乐项目评论表
 * 
 * @author 
 * @email 
 * @date 2025-04-02 12:00:38
 */
public interface DiscussyulexiangmuDao extends BaseMapper<DiscussyulexiangmuEntity> {
	
	List<DiscussyulexiangmuVO> selectListVO(@Param("ew") Wrapper<DiscussyulexiangmuEntity> wrapper);
	
	DiscussyulexiangmuVO selectVO(@Param("ew") Wrapper<DiscussyulexiangmuEntity> wrapper);
	
	List<DiscussyulexiangmuView> selectListView(@Param("ew") Wrapper<DiscussyulexiangmuEntity> wrapper);

	List<DiscussyulexiangmuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussyulexiangmuEntity> wrapper);

	
	DiscussyulexiangmuView selectView(@Param("ew") Wrapper<DiscussyulexiangmuEntity> wrapper);
	

}
