package com.edu.cn.service;

import com.edu.cn.dto.Exposer;
import com.edu.cn.dto.SecKillExecution;
import com.edu.cn.entity.SecKill;
import com.edu.cn.exception.RepeatKillException;
import com.edu.cn.exception.SecKillCloseException;
import com.edu.cn.exception.SecKillException;

import java.util.List;

/**
 *
 */
public interface SecKillService {

    /**
     * 查询多条秒杀记录
     * @return
     */
    List<SecKill> getSecKillList();

    /**
     * 查询单个秒杀记录
     * @param secKillId
     * @return
     */
    SecKill getById(long secKillId);

    /**
     * 秒杀开启时输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     * @param secKillId
     */
    Exposer exportSecKillUrl(long secKillId);

    /**
     * 执行秒杀操作
     * @param secKillId
     * @param userPhone
     * @param md5 匹配MD5是否一致，判断用户秒杀地址是否正常
     * @return
     * @throws SecKillException
     * @throws RepeatKillException
     * @throws SecKillCloseException
     */
    SecKillExecution executeSecKill(long secKillId, long userPhone, String md5) throws SecKillException, RepeatKillException, SecKillCloseException;

}
