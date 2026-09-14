package com.dao;

import com.entity.CanyuxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CanyuxinxiVO;
import com.entity.view.CanyuxinxiView;


/**
 * 参与信息
 * 
 * @author 
 * @email 
 * @date 2025-04-02 12:00:37
 */
public interface CanyuxinxiDao extends BaseMapper<CanyuxinxiEntity> {
	
	List<CanyuxinxiVO> selectListVO(@Param("ew") Wrapper<CanyuxinxiEntity> wrapper);
	
	CanyuxinxiVO selectVO(@Param("ew") Wrapper<CanyuxinxiEntity> wrapper);
	
	List<CanyuxinxiView> selectListView(@Param("ew") Wrapper<CanyuxinxiEntity> wrapper);

	List<CanyuxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<CanyuxinxiEntity> wrapper);

	
	CanyuxinxiView selectView(@Param("ew") Wrapper<CanyuxinxiEntity> wrapper);
	

}
