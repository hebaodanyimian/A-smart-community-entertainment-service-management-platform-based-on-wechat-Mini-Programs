package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussyulexiangmuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussyulexiangmuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussyulexiangmuView;


/**
 * 娱乐项目评论表
 *
 * @author 
 * @email 
 * @date 2025-04-02 12:00:38
 */
public interface DiscussyulexiangmuService extends IService<DiscussyulexiangmuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussyulexiangmuVO> selectListVO(Wrapper<DiscussyulexiangmuEntity> wrapper);
   	
   	DiscussyulexiangmuVO selectVO(@Param("ew") Wrapper<DiscussyulexiangmuEntity> wrapper);
   	
   	List<DiscussyulexiangmuView> selectListView(Wrapper<DiscussyulexiangmuEntity> wrapper);
   	
   	DiscussyulexiangmuView selectView(@Param("ew") Wrapper<DiscussyulexiangmuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussyulexiangmuEntity> wrapper);

   	

}

