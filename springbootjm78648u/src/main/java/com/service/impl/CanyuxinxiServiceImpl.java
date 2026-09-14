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


import com.dao.CanyuxinxiDao;
import com.entity.CanyuxinxiEntity;
import com.service.CanyuxinxiService;
import com.entity.vo.CanyuxinxiVO;
import com.entity.view.CanyuxinxiView;

@Service("canyuxinxiService")
public class CanyuxinxiServiceImpl extends ServiceImpl<CanyuxinxiDao, CanyuxinxiEntity> implements CanyuxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CanyuxinxiEntity> page = this.selectPage(
                new Query<CanyuxinxiEntity>(params).getPage(),
                new EntityWrapper<CanyuxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CanyuxinxiEntity> wrapper) {
		  Page<CanyuxinxiView> page =new Query<CanyuxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CanyuxinxiVO> selectListVO(Wrapper<CanyuxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CanyuxinxiVO selectVO(Wrapper<CanyuxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CanyuxinxiView> selectListView(Wrapper<CanyuxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CanyuxinxiView selectView(Wrapper<CanyuxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
