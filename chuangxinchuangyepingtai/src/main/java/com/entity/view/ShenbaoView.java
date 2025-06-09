package com.entity.view;

import com.entity.ShenbaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;

/**
 * 申报
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-04-06
 */
@TableName("shenbao")
public class ShenbaoView extends ShenbaoEntity implements Serializable {
    private static final long serialVersionUID = 1L;
		/**
		* 是否通过的值
		*/
		private String tongguoValue;



		//级联表 xiangmu
			/**
			* 项目名称
			*/
			private String xiangmuName;
			/**
			* 项目预览图
			*/
			private String xiangmuPhoto;
			/**
			* 项目类型名称
			*/
			private Integer leixTypes;
				/**
				* 项目类型名称的值
				*/
				private String leixValue;
			/**
			* 发布学生
			*/
			private Integer yonghuId;
			/**
			* 指导老师
			*/
			private Integer jiaoshiId;
			/**
			* 是否申报
			*/
			private Integer shifouTypes;
				/**
				* 是否申报的值
				*/
				private String shifouValue;
			/**
			* 项目描述
			*/
			private String xiangmuContent;

		//级联表 yonghu
			/**
			* 学生姓名
			*/
			private String yonghuName;
			/**
			* 性别
			*/
			private Integer sexTypes;
				/**
				* 性别的值
				*/
				private String sexValue;
			/**
			* 身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 手机号
			*/
			private String yonghuPhone;
			/**
			* 照片
			*/
			private String yonghuPhoto;

		//级联表 zhuanji
			/**
			* 专家姓名
			*/
			private String zhuanjiName;
			/**
			* 身份证号
			*/
			private String zhuanjiIdNumber;
			/**
			* 手机号
			*/
			private String zhuanjiPhone;
			/**
			* 照片
			*/
			private String zhuanjiPhoto;

	public ShenbaoView() {

	}

	public ShenbaoView(ShenbaoEntity shenbaoEntity) {
		try {
			BeanUtils.copyProperties(this, shenbaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 是否通过的值
			*/
			public String getTongguoValue() {
				return tongguoValue;
			}
			/**
			* 设置： 是否通过的值
			*/
			public void setTongguoValue(String tongguoValue) {
				this.tongguoValue = tongguoValue;
			}

















				//级联表的get和set xiangmu
					/**
					* 获取： 项目名称
					*/
					public String getXiangmuName() {
						return xiangmuName;
					}
					/**
					* 设置： 项目名称
					*/
					public void setXiangmuName(String xiangmuName) {
						this.xiangmuName = xiangmuName;
					}
					/**
					* 获取： 项目预览图
					*/
					public String getXiangmuPhoto() {
						return xiangmuPhoto;
					}
					/**
					* 设置： 项目预览图
					*/
					public void setXiangmuPhoto(String xiangmuPhoto) {
						this.xiangmuPhoto = xiangmuPhoto;
					}
					/**
					* 获取： 项目类型名称
					*/
					public Integer getLeixTypes() {
						return leixTypes;
					}
					/**
					* 设置： 项目类型名称
					*/
					public void setLeixTypes(Integer leixTypes) {
						this.leixTypes = leixTypes;
					}


						/**
						* 获取： 项目类型名称的值
						*/
						public String getLeixValue() {
							return leixValue;
						}
						/**
						* 设置： 项目类型名称的值
						*/
						public void setLeixValue(String leixValue) {
							this.leixValue = leixValue;
						}
					/**
					* 获取： 发布学生
					*/
					public Integer getYonghuId() {
						return yonghuId;
					}
					/**
					* 设置： 发布学生
					*/
					public void setYonghuId(Integer yonghuId) {
						this.yonghuId = yonghuId;
					}
					/**
					* 获取： 指导老师
					*/
					public Integer getJiaoshiId() {
						return jiaoshiId;
					}
					/**
					* 设置： 指导老师
					*/
					public void setJiaoshiId(Integer jiaoshiId) {
						this.jiaoshiId = jiaoshiId;
					}
					/**
					* 获取： 是否申报
					*/
					public Integer getShifouTypes() {
						return shifouTypes;
					}
					/**
					* 设置： 是否申报
					*/
					public void setShifouTypes(Integer shifouTypes) {
						this.shifouTypes = shifouTypes;
					}


						/**
						* 获取： 是否申报的值
						*/
						public String getShifouValue() {
							return shifouValue;
						}
						/**
						* 设置： 是否申报的值
						*/
						public void setShifouValue(String shifouValue) {
							this.shifouValue = shifouValue;
						}
					/**
					* 获取： 项目描述
					*/
					public String getXiangmuContent() {
						return xiangmuContent;
					}
					/**
					* 设置： 项目描述
					*/
					public void setXiangmuContent(String xiangmuContent) {
						this.xiangmuContent = xiangmuContent;
					}







				//级联表的get和set yonghu
					/**
					* 获取： 学生姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 学生姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 照片
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}






				//级联表的get和set zhuanji
					/**
					* 获取： 专家姓名
					*/
					public String getZhuanjiName() {
						return zhuanjiName;
					}
					/**
					* 设置： 专家姓名
					*/
					public void setZhuanjiName(String zhuanjiName) {
						this.zhuanjiName = zhuanjiName;
					}
					/**
					* 获取： 性别
					*/
					public Integer getSexTypes() {
						return sexTypes;
					}
					/**
					* 设置： 性别
					*/
					public void setSexTypes(Integer sexTypes) {
						this.sexTypes = sexTypes;
					}


						/**
						* 获取： 性别的值
						*/
						public String getSexValue() {
							return sexValue;
						}
						/**
						* 设置： 性别的值
						*/
						public void setSexValue(String sexValue) {
							this.sexValue = sexValue;
						}
					/**
					* 获取： 身份证号
					*/
					public String getZhuanjiIdNumber() {
						return zhuanjiIdNumber;
					}
					/**
					* 设置： 身份证号
					*/
					public void setZhuanjiIdNumber(String zhuanjiIdNumber) {
						this.zhuanjiIdNumber = zhuanjiIdNumber;
					}
					/**
					* 获取： 手机号
					*/
					public String getZhuanjiPhone() {
						return zhuanjiPhone;
					}
					/**
					* 设置： 手机号
					*/
					public void setZhuanjiPhone(String zhuanjiPhone) {
						this.zhuanjiPhone = zhuanjiPhone;
					}
					/**
					* 获取： 照片
					*/
					public String getZhuanjiPhoto() {
						return zhuanjiPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setZhuanjiPhoto(String zhuanjiPhoto) {
						this.zhuanjiPhoto = zhuanjiPhoto;
					}




}
