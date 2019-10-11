/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SecKillController
 * Author:   123
 * Date:     2019/9/9 13:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.edu.cn.web;

import com.edu.cn.dto.Exposer;
import com.edu.cn.dto.SecKillExecution;
import com.edu.cn.dto.SecKillResult;
import com.edu.cn.entity.SecKill;
import com.edu.cn.enums.SecKillStatEnum;
import com.edu.cn.exception.RepeatKillException;
import com.edu.cn.exception.SecKillCloseException;
import com.edu.cn.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author 123
 * @create 2019/9/9
 * @since 1.0.0
 */
@Controller
@RequestMapping("/secKill")
public class SecKillController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillService secKillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //获取列表页
        List<SecKill> list = secKillService.getSecKillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{secKillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("secKillId") Long secKillId, Model model){
        if (secKillId == null){
            return "redirect:/secKill/list";
        }
        SecKill secKill = secKillService.getById(secKillId);
        if (secKill == null){
            return "forward:/secKill/list";
        }
        model.addAttribute("secKill", secKill);
        return "detail";
    }

    @RequestMapping(value = "/{secKillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecKillResult<Exposer> exposer(@PathVariable("secKillId") Long secKillId){

        SecKillResult<Exposer> result;
        try {
            Exposer exposer = secKillService.exportSecKillUrl(secKillId);
            result = new SecKillResult<Exposer>(true, exposer);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            result = new SecKillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{secKillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecKillResult<SecKillExecution> execute(@PathVariable("secKillId") Long secKillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone", required = false) Long phone){

        if (phone == null){
            return new SecKillResult<SecKillExecution>(false,"未注册");
        }
        SecKillResult<SecKillExecution> result;
        try {
            SecKillExecution execution = secKillService.executeSecKill(secKillId, phone, md5);
            return new SecKillResult<SecKillExecution>(true,execution);
        }catch (RepeatKillException e){
            SecKillExecution execution = new SecKillExecution(secKillId, SecKillStatEnum.REPEAT_KILL);
            return new SecKillResult<SecKillExecution>(true,execution);
        }catch (SecKillCloseException e){
            SecKillExecution execution = new SecKillExecution(secKillId, SecKillStatEnum.END);
            return new SecKillResult<SecKillExecution>(true,execution);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            SecKillExecution execution = new SecKillExecution(secKillId,SecKillStatEnum.INNER_ERROR);
            return new SecKillResult<SecKillExecution>(true,execution);
        }
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SecKillResult<Long> time(){
        Date nowTime = new Date();
        return new SecKillResult<Long>(true, nowTime.getTime());
    }
}