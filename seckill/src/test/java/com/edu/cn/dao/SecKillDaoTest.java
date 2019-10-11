package com.edu.cn.dao;

import com.edu.cn.entity.SecKill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SecKillDaoTest {

    @Autowired
    private SecKillDao secKillDao;

    @Test
    public void queryById() {
        long id = 1000;
        SecKill secKill = secKillDao.queryById(id);
        System.out.println(secKill.getName());
        System.out.println(secKill);
    }

    @Test
    public void queryAll() {
        List<SecKill> secKills = secKillDao.queryAll(0, 100);
        for (SecKill secKill:secKills) {
            System.out.println(secKill);
        }
    }

    @Test
    public void reduceNumber() {
        Date time = new Date();

        int reduceNumber = secKillDao.reduceNumber(1000L, time);
        System.out.println("reduceNumber=" + reduceNumber);

    }
}