/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SecKillServiceImpl
 * Author:   123
 * Date:     2019/9/8 10:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.edu.cn.service.impl;

import com.edu.cn.dao.SecKillDao;
import com.edu.cn.dao.SuccessKilledDao;
import com.edu.cn.dto.Exposer;
import com.edu.cn.dto.SecKillExecution;
import com.edu.cn.entity.SecKill;
import com.edu.cn.entity.SuccessKilled;
import com.edu.cn.enums.SecKillStatEnum;
import com.edu.cn.exception.RepeatKillException;
import com.edu.cn.exception.SecKillCloseException;
import com.edu.cn.exception.SecKillException;
import com.edu.cn.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author 123
 * @create 2019/9/8
 * @since 1.0.0
 */
@Service
public class SecKillServiceImpl implements SecKillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecKillDao secKillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    private final String salt = "sajfioaenfasnc235bo";

    /**
     * 查询多条秒杀记录
     *
     * @return
     */
    @Override
    public List<SecKill> getSecKillList() {
        return secKillDao.queryAll(0, 5);
    }

    /**
     * 查询单个秒杀记录
     *
     * @param secKillId
     * @return
     */
    @Override
    public SecKill getById(long secKillId) {
        return secKillDao.queryById(secKillId);
    }

    /**
     * 秒杀开启时输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     *
     * @param secKillId
     * @return
     */
    @Override
    public Exposer exportSecKillUrl(long secKillId) {
        SecKill secKill = secKillDao.queryById(secKillId);
        if (secKill == null) {
            //秒杀结束
            return new Exposer(false, secKillId);
        }
        Date startTime = secKill.getStartTime();
        Date endTime = secKill.getEndTime();
        //系统当前时间
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            //秒杀没有开启
            return new Exposer(false, secKillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMd5(secKillId);
        //开启秒杀
        return new Exposer(true, md5, secKillId);
    }

    /**
     * 执行秒杀操作
     * @param secKillId
     * @param userPhone
     * @param md5       匹配MD5是否一致，判断用户秒杀地址是否正常
     */
    @Override
    @Transactional
    public SecKillExecution executeSecKill(long secKillId, long userPhone, String md5) throws SecKillException, RepeatKillException, SecKillCloseException {
        //如果MD5的值不匹配，则判断秒杀地址错误，返回秒杀数据被重写
        if (md5 == null || !md5.equals(getMd5(secKillId))) {
            throw new SecKillException("secKill data rewrite");
        }
        //执行秒杀逻辑：减库存 + 记录购买行为
        Date nowTime = new Date();

        try {
            //1.减库存
            int updateCount = secKillDao.reduceNumber(secKillId, nowTime);
            if (updateCount <= 0) {
                //没有更新到记录，秒杀结束
                throw new SecKillCloseException("secKill is closed");
            } else {
                //2.记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(secKillId, userPhone);
                if (insertCount <= 0) {
                    //重复秒杀
                    throw new RepeatKillException("secKill repeated");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSecKill(secKillId, userPhone);
                    return new SecKillExecution(secKillId, SecKillStatEnum.SUCCESS, successKilled);
                }
            }
        } catch (RepeatKillException e1) {
            throw e1;
        } catch (SecKillCloseException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            //所有编译器异常转化为运行期异常
            throw new SecKillException("SecKill inner error:" + e.getMessage());
        }
    }

    private String getMd5(long secKillId) {
        String base = secKillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        System.out.println("加密后的MD5：" + md5);
        return md5;
    }

}


