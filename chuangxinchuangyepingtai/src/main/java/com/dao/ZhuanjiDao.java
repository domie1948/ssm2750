package com.dao;

import com.entity.ZhuanjiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhuanjiView;

/**
 * 专家 Dao 接口
 *
 * @author 
 * @since 2021-04-06
 */
public interface ZhuanjiDao extends BaseMapper<ZhuanjiEntity> {

   List<ZhuanjiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
