package com.edu.cn.dao;

import com.edu.cn.entity.SecKill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author 123
 * @create 2019/9/7
 * @since 1.0.0
 */
public interface SecKillDao {
    /**
     * 减库存
     * @param secKillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("secKillId") long secKillId, @Param("killTime")Date killTime);

    /**
     * 通过id查看秒杀商品
     * @param secKillId
     * @return
     */
    SecKill queryById(long secKillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     *java没有保存形参的记录，因此在运行queryAll(int offset,int limit)->queryAll(arg0,arg1)，
     *因此加上@Param来告诉实际形参的名字
     */
    List<SecKill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
