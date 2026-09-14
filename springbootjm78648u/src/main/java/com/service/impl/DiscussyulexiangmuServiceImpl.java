package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.DiscussyulexiangmuDao;
import com.entity.DiscussyulexiangmuEntity;
import com.service.DiscussyulexiangmuService;
import com.entity.vo.DiscussyulexiangmuVO;
import com.entity.view.DiscussyulexiangmuView;

@Service("discussyulexiangmuService")
public class DiscussyulexiangmuServiceImpl extends ServiceImpl<DiscussyulexiangmuDao, DiscussyulexiangmuEntity> implements DiscussyulexiangmuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussyulexiangmuEntity> page = this.selectPage(
                new Query<DiscussyulexiangmuEntity>(params).getPage(),
                new EntityWrapper<DiscussyulexiangmuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussyulexiangmuEntity> wrapper) {
		  Page<DiscussyulexiangmuView> page =new Query<DiscussyulexiangmuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussyulexiangmuVO> selectListVO(Wrapper<DiscussyulexiangmuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussyulexiangmuVO selectVO(Wrapper<DiscussyulexiangmuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussyulexiangmuView> selectListView(Wrapper<DiscussyulexiangmuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussyulexiangmuView selectView(Wrapper<DiscussyulexiangmuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
