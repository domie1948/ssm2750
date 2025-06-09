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

import com.entity.ZhuanjiEntity;

import com.service.ZhuanjiService;
import com.entity.view.ZhuanjiView;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 专家
 * 后端接口
 * @author
 * @email
 * @date 2021-04-06
*/
@RestController
@Controller
@RequestMapping("/zhuanji")
public class ZhuanjiController {
    private static final Logger logger = LoggerFactory.getLogger(ZhuanjiController.class);

    @Autowired
    private ZhuanjiService zhuanjiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;


    //级联表service


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isNotEmpty(role) && "专家".equals(role)){
            params.put("zhuajiId",request.getSession().getAttribute("userId"));
        }
        params.put("orderBy","id");
        PageUtils page = zhuanjiService.queryPage(params);

        //字典表数据转换
        List<ZhuanjiView> list =(List<ZhuanjiView>)page.getList();
        for(ZhuanjiView c:list){
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
        ZhuanjiEntity zhuanji = zhuanjiService.selectById(id);
        if(zhuanji !=null){
            //entity转view
            ZhuanjiView view = new ZhuanjiView();
            BeanUtils.copyProperties( zhuanji , view );//把实体数据重构到view中

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
    public R save(@RequestBody ZhuanjiEntity zhuanji, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhuanji:{}",this.getClass().getName(),zhuanji.toString());
        Wrapper<ZhuanjiEntity> queryWrapper = new EntityWrapper<ZhuanjiEntity>()
            .eq("username", zhuanji.getUsername())
            .eq("password", zhuanji.getPassword())
            .eq("zhuanji_name", zhuanji.getZhuanjiName())
            .eq("sex_types", zhuanji.getSexTypes())
            .eq("zhuanji_id_number", zhuanji.getZhuanjiIdNumber())
            .eq("zhuanji_phone", zhuanji.getZhuanjiPhone())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhuanjiEntity zhuanjiEntity = zhuanjiService.selectOne(queryWrapper);
        if(zhuanjiEntity==null){
            zhuanji.setCreateTime(new Date());
            zhuanji.setPassword("123456");
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      zhuanji.set
        //  }
            zhuanjiService.insert(zhuanji);
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhuanjiEntity zhuanji, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zhuanji:{}",this.getClass().getName(),zhuanji.toString());
        //根据字段查询是否有相同数据
        Wrapper<ZhuanjiEntity> queryWrapper = new EntityWrapper<ZhuanjiEntity>()
            .notIn("id",zhuanji.getId())
            .andNew()
            .eq("username", zhuanji.getUsername())
            .eq("password", zhuanji.getPassword())
            .eq("zhuanji_name", zhuanji.getZhuanjiName())
            .eq("sex_types", zhuanji.getSexTypes())
            .eq("zhuanji_id_number", zhuanji.getZhuanjiIdNumber())
            .eq("zhuanji_phone", zhuanji.getZhuanjiPhone())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhuanjiEntity zhuanjiEntity = zhuanjiService.selectOne(queryWrapper);
        if("".equals(zhuanji.getZhuanjiPhoto()) || "null".equals(zhuanji.getZhuanjiPhoto())){
                zhuanji.setZhuanjiPhoto(null);
        }
        if(zhuanjiEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      zhuanji.set
            //  }
            zhuanjiService.updateById(zhuanji);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者身份证号或者手机号已经被使用");
        }
    }


    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        zhuanjiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        ZhuanjiEntity zhuanji = zhuanjiService.selectOne(new EntityWrapper<ZhuanjiEntity>().eq("username", username));
        if(zhuanji==null || !zhuanji.getPassword().equals(password)) {
            return R.error("账号或密码不正确");
        }
        String token = tokenService.generateToken(zhuanji.getId(),username, "zhuanji", "专家");
        R r = R.ok();
        r.put("token", token);
        r.put("role","专家");
        r.put("username",zhuanji.getZhuanjiName());
        r.put("tableName","zhuanji");
        r.put("userId",zhuanji.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody ZhuanjiEntity zhuanji){
    //    	ValidatorUtils.validateEntity(user);
        if(zhuanjiService.selectOne(new EntityWrapper<ZhuanjiEntity>().eq("username", zhuanji.getUsername()).orNew().eq("zhuanji_phone",zhuanji.getZhuanjiPhone()).orNew().eq("zhuanji_id_number",zhuanji.getZhuanjiIdNumber())) !=null) {
            return R.error("账户已存在或手机号或身份证号已经被使用");
        }
        zhuanjiService.insert(zhuanji);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        ZhuanjiEntity zhuanji = new ZhuanjiEntity();
        zhuanji.setPassword("123456");
        zhuanji.setId(id);
        zhuanjiService.updateById(zhuanji);
        return R.ok();
    }

    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrZhuanji(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        ZhuanjiEntity zhuanji = zhuanjiService.selectById(id);
        return R.ok().put("data", zhuanji);
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



}

