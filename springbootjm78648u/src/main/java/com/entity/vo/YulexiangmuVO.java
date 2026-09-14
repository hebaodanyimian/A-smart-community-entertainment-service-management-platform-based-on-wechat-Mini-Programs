package com.entity.vo;

import com.entity.YulexiangmuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 娱乐项目
 * @author 
 * @email 
 * @date 2025-04-02 12:00:36
 */
public class YulexiangmuVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 图片
	 */
	
	private String tupian;
		
	/**
	 * 项目类型
	 */
	
	private String xiangmuleixing;
		
	/**
	 * 参与人数
	 */
	
	private Integer canyurenshu;
		
	/**
	 * 开放时间
	 */
	
	private String kaifangshijian;
		
	/**
	 * 所在位置
	 */
	
	private String suozaiweizhi;
		
	/**
	 * 注意事项
	 */
	
	private String zhuyishixiang;
		
	/**
	 * 详情简介
	 */
	
	private String xiangqingjianjie;
		
	/**
	 * 赞
	 */
	
	private Integer thumbsupnum;
		
	/**
	 * 踩
	 */
	
	private Integer crazilynum;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：图片
	 */
	 
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
				
	
	/**
	 * 设置：项目类型
	 */
	 
	public void setXiangmuleixing(String xiangmuleixing) {
		this.xiangmuleixing = xiangmuleixing;
	}
	
	/**
	 * 获取：项目类型
	 */
	public String getXiangmuleixing() {
		return xiangmuleixing;
	}
				
	
	/**
	 * 设置：参与人数
	 */
	 
	public void setCanyurenshu(Integer canyurenshu) {
		this.canyurenshu = canyurenshu;
	}
	
	/**
	 * 获取：参与人数
	 */
	public Integer getCanyurenshu() {
		return canyurenshu;
	}
				
	
	/**
	 * 设置：开放时间
	 */
	 
	public void setKaifangshijian(String kaifangshijian) {
		this.kaifangshijian = kaifangshijian;
	}
	
	/**
	 * 获取：开放时间
	 */
	public String getKaifangshijian() {
		return kaifangshijian;
	}
				
	
	/**
	 * 设置：所在位置
	 */
	 
	public void setSuozaiweizhi(String suozaiweizhi) {
		this.suozaiweizhi = suozaiweizhi;
	}
	
	/**
	 * 获取：所在位置
	 */
	public String getSuozaiweizhi() {
		return suozaiweizhi;
	}
				
	
	/**
	 * 设置：注意事项
	 */
	 
	public void setZhuyishixiang(String zhuyishixiang) {
		this.zhuyishixiang = zhuyishixiang;
	}
	
	/**
	 * 获取：注意事项
	 */
	public String getZhuyishixiang() {
		return zhuyishixiang;
	}
				
	
	/**
	 * 设置：详情简介
	 */
	 
	public void setXiangqingjianjie(String xiangqingjianjie) {
		this.xiangqingjianjie = xiangqingjianjie;
	}
	
	/**
	 * 获取：详情简介
	 */
	public String getXiangqingjianjie() {
		return xiangqingjianjie;
	}
				
	
	/**
	 * 设置：赞
	 */
	 
	public void setThumbsupnum(Integer thumbsupnum) {
		this.thumbsupnum = thumbsupnum;
	}
	
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupnum() {
		return thumbsupnum;
	}
				
	
	/**
	 * 设置：踩
	 */
	 
	public void setCrazilynum(Integer crazilynum) {
		this.crazilynum = crazilynum;
	}
	
	/**
	 * 获取：踩
	 */
	public Integer getCrazilynum() {
		return crazilynum;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
