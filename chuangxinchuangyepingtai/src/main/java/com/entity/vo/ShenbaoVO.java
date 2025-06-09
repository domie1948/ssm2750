package com.entity.vo;

import com.entity.ShenbaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 申报
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-04-06
 */
@TableName("shenbao")
public class ShenbaoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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

    @TableField(value = "insert_time")
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

    @TableField(value = "create_time")
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

}
