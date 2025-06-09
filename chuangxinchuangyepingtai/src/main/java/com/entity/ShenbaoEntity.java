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
 * 申报
 *
 * @author 
 * @email
 * @date 2021-04-06
 */
@TableName("shenbao")
public class ShenbaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShenbaoEntity() {

	}

	public ShenbaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 申报人
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 申报项目
     */
    @TableField(value = "xiangmu_id")

    private Integer xiangmuId;


    /**
     * 申报时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 是否通过
     */
    @TableField(value = "tongguo_types")

    private Integer tongguoTypes;


    /**
     * 审批人
     */
    @TableField(value = "zhuanji_id")

    private Integer zhuanjiId;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：申报人
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：申报人
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：申报项目
	 */
    public Integer getXiangmuId() {
        return xiangmuId;
    }


    /**
	 * 获取：申报项目
	 */

    public void setXiangmuId(Integer xiangmuId) {
        this.xiangmuId = xiangmuId;
    }
    /**
	 * 设置：申报时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申报时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：是否通过
	 */
    public Integer getTongguoTypes() {
        return tongguoTypes;
    }


    /**
	 * 获取：是否通过
	 */

    public void setTongguoTypes(Integer tongguoTypes) {
        this.tongguoTypes = tongguoTypes;
    }
    /**
	 * 设置：审批人
	 */
    public Integer getZhuanjiId() {
        return zhuanjiId;
    }


    /**
	 * 获取：审批人
	 */

    public void setZhuanjiId(Integer zhuanjiId) {
        this.zhuanjiId = zhuanjiId;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Shenbao{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", xiangmuId=" + xiangmuId +
            ", insertTime=" + insertTime +
            ", tongguoTypes=" + tongguoTypes +
            ", zhuanjiId=" + zhuanjiId +
            ", createTime=" + createTime +
        "}";
    }
}
