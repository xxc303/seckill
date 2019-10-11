package com.edu.cn.service;

import com.edu.cn.dto.Exposer;
import com.edu.cn.dto.SecKillExecution;
import com.edu.cn.entity.SecKill;
import com.edu.cn.exception.RepeatKillException;
import com.edu.cn.exception.SecKillCloseException;
import com.edu.cn.service.impl.SecKillServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class SecKillServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecKillServiceImpl.class);

    @Autowired
    private SecKillService secKillService;

    @Test
    public void getSecKillList() {
        List<SecKill> list = secKillService.getSecKillList();
        LOGGER.info("list={}",list);
    }

    @Test
    public void getById() {
        long secKillId = 1000L;
        SecKill secKill = secKillService.getById(secKillId);
        LOGGER.info("list={}",secKill);
    }

   /* @Test
    public void exportSecKillUrl() {
        long secKillId = 1000L;
        Exposer exposer = secKillService.exportSecKillUrl(secKillId);
        LOGGER.info("exposer={}",exposer);
        //exposer=Exposer{exposer=true,
        // md5='6f554312b4425f6b3a3edc2253b2bbeb',
        // secKillId=1000,
        // now=0, start=0, end=0}
    }

    @Test
    public void executeSecKill() {
        try {
            long secKillId = 1000L;
            long userPhone = 15154198542L;
            String md5 = "6f554312b4425f6b3a3edc2253b2bbeb";
            SecKillExecution execution = secKillService.executeSecKill(secKillId, userPhone, md5);
            LOGGER.info("execution={}",execution);
        }catch (RepeatKillException e){
            LOGGER.error(e.getMessage());
        }catch (SecKillCloseException e){
            LOGGER.error(e.getMessage());
        }
    }*/

    /**
     * 将第三四个测试整合，集成测试
     */
   @Test
   public void executeSecKIllLogic(){
       long secKillId = 1000L;
       Exposer exposer = secKillService.exportSecKillUrl(secKillId);
       if (exposer.isExposer()){
           LOGGER.info("exposer={}",exposer);
           long userPhone = 15162128542L;
           String md5 = exposer.getMd5();
           try {
               SecKillExecution execution = secKillService.executeSecKill(secKillId, userPhone, md5);
               //execution=SecKillExecution{secKillId=1000, state=1, stateInfo='秒杀成功', successKilled=SuccessKilled{secKillId=1000, userPhone=15162128542, state=0, createTime=Sun Sep 08 20:15:09 CST 2019, secKill=SecKill{secKillId=1000, name='1元秒杀坚果tNT工作站', number=96, startTime=Sun Sep 08 20:15:09 CST 2019, endTime=Tue Jun 02 00:00:00 CST 2020, createTime=Sun Sep 08 20:15:09 CST 2019}}}
               LOGGER.info("execution={}",execution);
           }catch (RepeatKillException e){
               LOGGER.error(e.getMessage());
           }catch (SecKillCloseException e){
               LOGGER.error(e.getMessage());
           }
       }else {
           //秒杀未开启 exposer
           LOGGER.warn("exposer",exposer);
       }
   }
}