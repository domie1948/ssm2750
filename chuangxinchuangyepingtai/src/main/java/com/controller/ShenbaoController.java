package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.ShenbaoEntity;

import com.service.ShenbaoService;
import com.entity.view.ShenbaoView;
import com.service.XiangmuService;
import com.entity.XiangmuEntity;
import com.service.YonghuService;
import com.entity.YonghuEntity;
import com.service.ZhuanjiService;
import com.entity.ZhuanjiEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 申报
 * 后端接口
 * @author
 * @email
 * @date 2021-04-06
*/
@RestController
@Controller
@RequestMapping("/shenbao")
public class ShenbaoController {
    private static final Logger logger = LoggerFactory.getLogger(ShenbaoController.class);

    @Autowired
    private ShenbaoService shenbaoService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;


    //级联表service
    @Autowired
    private XiangmuService xiangmuService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private ZhuanjiService zhuanjiService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "用户".equals(role)){
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        }
        if(StringUtil.isNotEmpty(role) && "教师".equals(role)){
            params.put("jiaoshiId",request.getSession().getAttribute("userId"));
        }
        if(StringUtil.isNotEmpty(role) && "专家".equals(role)){
            params.put("zhuanjiId",request.getSession().getAttribute("userId"));
        }
        params.put("orderBy","id");
        PageUtils page = shenbaoService.queryPage(params);

        //字典表数据转换
        List<ShenbaoView> list =(List<ShenbaoView>)page.getList();
        for(ShenbaoView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        ShenbaoEntity shenbao = shenbaoService.selectById(id);
        if(shenbao !=null){
            //entity转view
            ShenbaoView view = new ShenbaoView();
            BeanUtils.copyProperties( shenbao , view );//把实体数据重构到view中

            //级联表
            XiangmuEntity xiangmu = xiangmuService.selectById(shenbao.getXiangmuId());
            if(xiangmu != null){
                BeanUtils.copyProperties( xiangmu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setXiangmuId(xiangmu.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(shenbao.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
            }
            //级联表
            ZhuanjiEntity zhuanji = zhuanjiService.selectById(shenbao.getZhuanjiId());
            if(zhuanji != null){
                BeanUtils.copyProperties( zhuanji , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setZhuanjiId(zhuanji.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody ShenbaoEntity shenbao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shenbao:{}",this.getClass().getName(),shenbao.toString());
        Wrapper<ShenbaoEntity> queryWrapper = new EntityWrapper<ShenbaoEntity>()
            .eq("yonghu_id", shenbao.getYonghuId())
            .eq("xiangmu_id", shenbao.getXiangmuId())
            .eq("tongguo_types", shenbao.getTongguoTypes())
            .eq("zhuanji_id", shenbao.getZhuanjiId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenbaoEntity shenbaoEntity = shenbaoService.selectOne(queryWrapper);
        if(shenbaoEntity==null){
            shenbao.setInsertTime(new Date());
            shenbao.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      shenbao.set
        //  }
            shenbaoService.insert(shenbao);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShenbaoEntity shenbao, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,shenbao:{}",this.getClass().getName(),shenbao.toString());
        //根据字段查询是否有相同数据
        Wrapper<ShenbaoEntity> queryWrapper = new EntityWrapper<ShenbaoEntity>()
            .notIn("id",shenbao.getId())
            .andNew()
            .eq("yonghu_id", shenbao.getYonghuId())
            .eq("xiangmu_id", shenbao.getXiangmuId())
            .eq("tongguo_types", shenbao.getTongguoTypes())
            .eq("zhuanji_id", shenbao.getZhuanjiId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenbaoEntity shenbaoEntity = shenbaoService.selectOne(queryWrapper);
        if(shenbaoEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      shenbao.set
            //  }
            shenbaoService.updateById(shenbao);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        shenbaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }



}

