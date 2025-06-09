package com.controller;


import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;

import com.entity.ShenbaoEntity;
import com.service.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;

import com.utils.StringUtil;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import com.entity.XiangmuEntity;

import com.entity.view.XiangmuView;
import com.entity.JiaoshiEntity;
import com.entity.YonghuEntity;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 项目
 * 后端接口
 * @author
 * @email
 * @date 2021-04-06
*/
@RestController
@Controller
@RequestMapping("/xiangmu")
public class XiangmuController {
    private static final Logger logger = LoggerFactory.getLogger(XiangmuController.class);

    @Autowired
    private XiangmuService xiangmuService;

    @Autowired
    private ShenbaoService shenbaoService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;


    //级联表service
    @Autowired
    private JiaoshiService jiaoshiService;
    @Autowired
    private YonghuService yonghuService;


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
        PageUtils page = xiangmuService.queryPage(params);

        //字典表数据转换
        List<XiangmuView> list =(List<XiangmuView>)page.getList();
        for(XiangmuView c:list){
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
        XiangmuEntity xiangmu = xiangmuService.selectById(id);
        if(xiangmu !=null){
            //entity转view
            XiangmuView view = new XiangmuView();
            BeanUtils.copyProperties( xiangmu , view );//把实体数据重构到view中

            //级联表
            JiaoshiEntity jiaoshi = jiaoshiService.selectById(xiangmu.getJiaoshiId());
            if(jiaoshi != null){
                BeanUtils.copyProperties( jiaoshi , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setJiaoshiId(jiaoshi.getId());
            }
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(xiangmu.getYonghuId());
            if(yonghu != null){
                BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody XiangmuEntity xiangmu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xiangmu:{}",this.getClass().getName(),xiangmu.toString());
        Wrapper<XiangmuEntity> queryWrapper = new EntityWrapper<XiangmuEntity>()
            .eq("yonghu_id", xiangmu.getYonghuId())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiangmuEntity xiangmuEntity = xiangmuService.selectOne(queryWrapper);
        if(xiangmuEntity==null){
            xiangmu.setCreateTime(new Date());
            xiangmuService.insert(xiangmu);
            return R.ok();
        }else {
            return R.error(511,"每个学生只能上传一个项目信息哦");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XiangmuEntity xiangmu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xiangmu:{}",this.getClass().getName(),xiangmu.toString());
        //根据字段查询是否有相同数据
        Wrapper<XiangmuEntity> queryWrapper = new EntityWrapper<XiangmuEntity>()
            .notIn("id",xiangmu.getId())
            .andNew()
            .eq("xiangmu_name", xiangmu.getXiangmuName())
            .eq("leix_types", xiangmu.getLeixTypes())
            .eq("yonghu_id", xiangmu.getYonghuId())
            .eq("jiaoshi_id", xiangmu.getJiaoshiId())
            .eq("shifou_types", xiangmu.getShifouTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XiangmuEntity xiangmuEntity = xiangmuService.selectOne(queryWrapper);
        if("".equals(xiangmu.getXiangmuPhoto()) || "null".equals(xiangmu.getXiangmuPhoto())){
                xiangmu.setXiangmuPhoto(null);
        }
        if(xiangmuEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      xiangmu.set
            //  }
            xiangmuService.updateById(xiangmu);//根据id更新
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
        xiangmuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
    * 申报
    */
    @RequestMapping("/shenbao")
    public R shenbao(Integer ids){
        XiangmuEntity xiangmu = xiangmuService.selectById(ids);
        if(xiangmu==null){
            return R.error();
        }
        ShenbaoEntity shenbao = new ShenbaoEntity();
        shenbao.setCreateTime(new Date());
        shenbao.setInsertTime(new Date());
        shenbao.setXiangmuId(xiangmu.getId());
        shenbao.setYonghuId(xiangmu.getYonghuId());
        shenbao.setTongguoTypes(0);
        Wrapper<ShenbaoEntity> queryWrapper = new EntityWrapper<ShenbaoEntity>()
                .eq("yonghu_id", shenbao.getYonghuId())
                .eq("xiangmu_id", shenbao.getXiangmuId())
                ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenbaoEntity shenbaoEntity = shenbaoService.selectOne(queryWrapper);
        if(shenbaoEntity != null){
            if(shenbaoEntity.getTongguoTypes() == 1){
                return R.error("你的申报已经通过请不要重复申请");
            }
        }
        boolean insert = shenbaoService.insert(shenbao);
        if(insert){
            xiangmu.setShifouTypes(1);
            boolean b = xiangmuService.updateById(xiangmu);
            if(b){
                return R.ok();
            }
        }
        return R.error();
    }
    /**
    * 同意
    */
    @RequestMapping("/shenpi")
    public R shenpi(Integer ids, HttpServletRequest request){
        ShenbaoEntity shenbao = shenbaoService.selectById(ids);
        if(shenbao==null){
            return R.error();
        }
        shenbao.setZhuanjiId((Integer)request.getSession().getAttribute("userId"));
        shenbao.setTongguoTypes(1);
        boolean b = shenbaoService.updateById(shenbao);
        if(b){
            return R.ok();
        }
        return R.error();
    }
    /**
    * 拒绝
    */
    @RequestMapping("/jujue")
    public R jujue(Integer ids, HttpServletRequest request){
        ShenbaoEntity shenbao = shenbaoService.selectById(ids);
        if(shenbao==null){
            return R.error();
        }
        shenbao.setZhuanjiId((Integer)request.getSession().getAttribute("userId"));
        shenbao.setTongguoTypes(2);
        boolean b = shenbaoService.updateById(shenbao);
        if(b){
            XiangmuEntity xiangmu = xiangmuService.selectById(shenbao.getXiangmuId());
            xiangmu.setShifouTypes(2);
            boolean b1 = xiangmuService.updateById(xiangmu);
            if(b1){
                return R.ok();
            }
        }
        return R.error();
    }



}

