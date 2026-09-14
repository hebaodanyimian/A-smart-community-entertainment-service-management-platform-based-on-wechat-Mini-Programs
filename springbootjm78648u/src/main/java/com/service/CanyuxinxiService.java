package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CanyuxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CanyuxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CanyuxinxiView;


/**
 * 参与信息
 *
 * @author 
 * @email 
 * @date 2025-04-02 12:00:37
 */
public interface CanyuxinxiService extends IService<CanyuxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CanyuxinxiVO> selectListVO(Wrapper<CanyuxinxiEntity> wrapper);
   	
   	CanyuxinxiVO selectVO(@Param("ew") Wrapper<CanyuxinxiEntity> wrapper);
   	
   	List<CanyuxinxiView> selectListView(Wrapper<CanyuxinxiEntity> wrapper);
   	
   	CanyuxinxiView selectView(@Param("ew") Wrapper<CanyuxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CanyuxinxiEntity> wrapper);

   	

}

