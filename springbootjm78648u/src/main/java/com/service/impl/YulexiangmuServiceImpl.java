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


import com.dao.YulexiangmuDao;
import com.entity.YulexiangmuEntity;
import com.service.YulexiangmuService;
import com.entity.vo.YulexiangmuVO;
import com.entity.view.YulexiangmuView;

@Service("yulexiangmuService")
public class YulexiangmuServiceImpl extends ServiceImpl<YulexiangmuDao, YulexiangmuEntity> implements YulexiangmuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YulexiangmuEntity> page = this.selectPage(
                new Query<YulexiangmuEntity>(params).getPage(),
                new EntityWrapper<YulexiangmuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YulexiangmuEntity> wrapper) {
		  Page<YulexiangmuView> page =new Query<YulexiangmuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YulexiangmuVO> selectListVO(Wrapper<YulexiangmuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YulexiangmuVO selectVO(Wrapper<YulexiangmuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YulexiangmuView> selectListView(Wrapper<YulexiangmuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YulexiangmuView selectView(Wrapper<YulexiangmuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<YulexiangmuEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<YulexiangmuEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<YulexiangmuEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
