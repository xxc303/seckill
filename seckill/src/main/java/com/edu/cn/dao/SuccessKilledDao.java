package com.edu.cn.dao;

import com.edu.cn.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * @author 123
 * @create 2019/9/7
 * @since 1.0.0
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复（联合主键）
     * @param secKillId
     * @param userPhone
     * @return
     */

    int insertSuccessKilled(@Param("secKillId") long secKillId, @Param("userPhone") long userPhone);

    /**
     *
     * @param secKillId
     * @param userPhone
     * @return
     */

    SuccessKilled queryByIdWithSecKill(@Param("secKillId") long secKillId, @Param("userPhone") long userPhone);
}
