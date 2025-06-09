package com.entity.model;

import com.entity.ShenbaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 申报
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-04-06
 */
public class ShenbaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 申报人
     */
    private Integer yonghuId;


    /**
     * 申报项目
     */
    private Integer xiangmuId;


    /**
     * 申报时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 是否通过
     */
    private Integer tongguoTypes;


    /**
     * 审批人
     */
    private Integer zhuanjiId;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：申报人
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：申报人
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：申报项目
	 */
    public Integer getXiangmuId() {
        return xiangmuId;
    }


    /**
	 * 设置：申报项目
	 */
    public void setXiangmuId(Integer xiangmuId) {
        this.xiangmuId = xiangmuId;
    }
    /**
	 * 获取：申报时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申报时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：是否通过
	 */
    public Integer getTongguoTypes() {
        return tongguoTypes;
    }


    /**
	 * 设置：是否通过
	 */
    public void setTongguoTypes(Integer tongguoTypes) {
        this.tongguoTypes = tongguoTypes;
    }
    /**
	 * 获取：审批人
	 */
    public Integer getZhuanjiId() {
        return zhuanjiId;
    }


    /**
	 * 设置：审批人
	 */
    public void setZhuanjiId(Integer zhuanjiId) {
        this.zhuanjiId = zhuanjiId;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
