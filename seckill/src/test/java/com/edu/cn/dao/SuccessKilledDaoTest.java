package com.edu.cn.dao;

import com.edu.cn.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        Long seckillId = 1000L;
        Long userPhone = 1515419852L;

        int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
        System.out.println("insertCount=" + insertCount);
    }

    @Test
    public void queryByIdWithSecKill() {
        Long secKillId = 1000L;
        Long userPhone = 1515419852L;

        SuccessKilled successKilled = successKilledDao.queryByIdWithSecKill(secKillId, userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSecKill());

    }
}