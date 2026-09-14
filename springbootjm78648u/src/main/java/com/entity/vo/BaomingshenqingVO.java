package com.entity.vo;

import com.entity.BaomingshenqingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 报名申请
 * @author 
 * @email 
 * @date 2025-04-02 12:00:36
 */
public class BaomingshenqingVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 活动名称
	 */
	
	private String huodongmingcheng;
		
	/**
	 * 活动类型
	 */
	
	private String huodongleixing;
		
	/**
	 * 活动封面
	 */
	
	private String huodongfengmian;
		
	/**
	 * 时间
	 */
	
	private String shijian;
		
	/**
	 * 地点
	 */
	
	private String didian;
		
	/**
	 * 报名人数
	 */
	
	private Integer baomingrenshu;
		
	/**
	 * 账号
	 */
	
	private String zhanghao;
		
	/**
	 * 组织者
	 */
	
	private String xingming;
		
	/**
	 * 参与账号
	 */
	
	private String canyuzhanghao;
		
	/**
	 * 参与姓名
	 */
	
	private String canyuxingming;
		
	/**
	 * 性别
	 */
	
	private String xingbie;
		
	/**
	 * 年龄
	 */
	
	private String nianling;
		
	/**
	 * 报名时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date baomingshijian;
		
	/**
	 * 报名备注
	 */
	
	private String baomingbeizhu;
		
	/**
	 * 跨表用户id
	 */
	
	private Long crossuserid;
		
	/**
	 * 跨表主键id
	 */
	
	private Long crossrefid;
		
	/**
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
				
	
	/**
	 * 设置：活动名称
	 */
	 
	public void setHuodongmingcheng(String huodongmingcheng) {
		this.huodongmingcheng = huodongmingcheng;
	}
	
	/**
	 * 获取：活动名称
	 */
	public String getHuodongmingcheng() {
		return huodongmingcheng;
	}
				
	
	/**
	 * 设置：活动类型
	 */
	 
	public void setHuodongleixing(String huodongleixing) {
		this.huodongleixing = huodongleixing;
	}
	
	/**
	 * 获取：活动类型
	 */
	public String getHuodongleixing() {
		return huodongleixing;
	}
				
	
	/**
	 * 设置：活动封面
	 */
	 
	public void setHuodongfengmian(String huodongfengmian) {
		this.huodongfengmian = huodongfengmian;
	}
	
	/**
	 * 获取：活动封面
	 */
	public String getHuodongfengmian() {
		return huodongfengmian;
	}
				
	
	/**
	 * 设置：时间
	 */
	 
	public void setShijian(String shijian) {
		this.shijian = shijian;
	}
	
	/**
	 * 获取：时间
	 */
	public String getShijian() {
		return shijian;
	}
				
	
	/**
	 * 设置：地点
	 */
	 
	public void setDidian(String didian) {
		this.didian = didian;
	}
	
	/**
	 * 获取：地点
	 */
	public String getDidian() {
		return didian;
	}
				
	
	/**
	 * 设置：报名人数
	 */
	 
	public void setBaomingrenshu(Integer baomingrenshu) {
		this.baomingrenshu = baomingrenshu;
	}
	
	/**
	 * 获取：报名人数
	 */
	public Integer getBaomingrenshu() {
		return baomingrenshu;
	}
				
	
	/**
	 * 设置：账号
	 */
	 
	public void setZhanghao(String zhanghao) {
		this.zhanghao = zhanghao;
	}
	
	/**
	 * 获取：账号
	 */
	public String getZhanghao() {
		return zhanghao;
	}
				
	
	/**
	 * 设置：组织者
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：组织者
	 */
	public String getXingming() {
		return xingming;
	}
				
	
	/**
	 * 设置：参与账号
	 */
	 
	public void setCanyuzhanghao(String canyuzhanghao) {
		this.canyuzhanghao = canyuzhanghao;
	}
	
	/**
	 * 获取：参与账号
	 */
	public String getCanyuzhanghao() {
		return canyuzhanghao;
	}
				
	
	/**
	 * 设置：参与姓名
	 */
	 
	public void setCanyuxingming(String canyuxingming) {
		this.canyuxingming = canyuxingming;
	}
	
	/**
	 * 获取：参与姓名
	 */
	public String getCanyuxingming() {
		return canyuxingming;
	}
				
	
	/**
	 * 设置：性别
	 */
	 
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	
	/**
	 * 获取：性别
	 */
	public String getXingbie() {
		return xingbie;
	}
				
	
	/**
	 * 设置：年龄
	 */
	 
	public void setNianling(String nianling) {
		this.nianling = nianling;
	}
	
	/**
	 * 获取：年龄
	 */
	public String getNianling() {
		return nianling;
	}
				
	
	/**
	 * 设置：报名时间
	 */
	 
	public void setBaomingshijian(Date baomingshijian) {
		this.baomingshijian = baomingshijian;
	}
	
	/**
	 * 获取：报名时间
	 */
	public Date getBaomingshijian() {
		return baomingshijian;
	}
				
	
	/**
	 * 设置：报名备注
	 */
	 
	public void setBaomingbeizhu(String baomingbeizhu) {
		this.baomingbeizhu = baomingbeizhu;
	}
	
	/**
	 * 获取：报名备注
	 */
	public String getBaomingbeizhu() {
		return baomingbeizhu;
	}
				
	
	/**
	 * 设置：跨表用户id
	 */
	 
	public void setCrossuserid(Long crossuserid) {
		this.crossuserid = crossuserid;
	}
	
	/**
	 * 获取：跨表用户id
	 */
	public Long getCrossuserid() {
		return crossuserid;
	}
				
	
	/**
	 * 设置：跨表主键id
	 */
	 
	public void setCrossrefid(Long crossrefid) {
		this.crossrefid = crossrefid;
	}
	
	/**
	 * 获取：跨表主键id
	 */
	public Long getCrossrefid() {
		return crossrefid;
	}
				
	
	/**
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
			
}
