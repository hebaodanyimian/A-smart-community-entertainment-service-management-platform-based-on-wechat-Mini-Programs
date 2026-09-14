package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 居民
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-04-02 12:00:35
 */
@TableName("jumin")
public class JuminEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public JuminEntity() {
		
	}
	
	public JuminEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId
	private Long id;
	/**
	 * 账号
	 */
					
	private String zhanghao;
	
	/**
	 * 密码
	 */
					
	private String mima;
	
	/**
	 * 姓名
	 */
					
	private String xingming;
	
	/**
	 * 性别
	 */
					
	private String xingbie;
	
	/**
	 * 年龄
	 */
					
	private Integer nianling;
	
	/**
	 * 手机号码
	 */
					
	private String shoujihaoma;
	
	/**
	 * 家庭住址
	 */
					
	private String jiatingzhuzhi;
	
	/**
	 * 兴趣爱好
	 */
					
	private String xingquaihao;
	
	/**
	 * 头像
	 */
					
	private String touxiang;
	
	/**
	 * 状态
	 */
					
	private Integer status;
	
	/**
	 * 微信openid
	 */
					
	private String openid;
	
	/**
	 * 微信昵称
	 */
					
	private String nickname;
	
	/**
	 * 微信头像
	 */
					
	private String avatarurl;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	 * 设置：密码
	 */
	public void setMima(String mima) {
		this.mima = mima;
	}
	/**
	 * 获取：密码
	 */
	public String getMima() {
		return mima;
	}
	/**
	 * 设置：姓名
	 */
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
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
	public void setNianling(Integer nianling) {
		this.nianling = nianling;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getNianling() {
		return nianling;
	}
	/**
	 * 设置：手机号码
	 */
	public void setShoujihaoma(String shoujihaoma) {
		this.shoujihaoma = shoujihaoma;
	}
	/**
	 * 获取：手机号码
	 */
	public String getShoujihaoma() {
		return shoujihaoma;
	}
	/**
	 * 设置：家庭住址
	 */
	public void setJiatingzhuzhi(String jiatingzhuzhi) {
		this.jiatingzhuzhi = jiatingzhuzhi;
	}
	/**
	 * 获取：家庭住址
	 */
	public String getJiatingzhuzhi() {
		return jiatingzhuzhi;
	}
	/**
	 * 设置：兴趣爱好
	 */
	public void setXingquaihao(String xingquaihao) {
		this.xingquaihao = xingquaihao;
	}
	/**
	 * 获取：兴趣爱好
	 */
	public String getXingquaihao() {
		return xingquaihao;
	}
	/**
	 * 设置：头像
	 */
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	/**
	 * 获取：头像
	 */
	public String getTouxiang() {
		return touxiang;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：微信openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：微信openid
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：微信昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：微信昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：微信头像
	 */
	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}
	/**
	 * 获取：微信头像
	 */
	public String getAvatarurl() {
		return avatarurl;
	}

}
