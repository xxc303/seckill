/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SecKillExecution
 * Author:   123
 * Date:     2019/9/8 9:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.edu.cn.dto;

import com.edu.cn.entity.SuccessKilled;
import com.edu.cn.enums.SecKillStatEnum;

/**
 *封装秒杀后的结果
 * @author 123
 * @create 2019/9/8
 * @since 1.0.0
 */
public class SecKillExecution {

    private long secKillId;

    //秒杀结果状态

    private int state;

    //状态标识

    private String stateInfo;

    //秒杀成功对象

    private SuccessKilled successKilled;

    public SecKillExecution(long secKillId, SecKillStatEnum statEnum, SuccessKilled successKilled) {
        this.secKillId = secKillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public SecKillExecution(long secKillId, SecKillStatEnum statEnum) {
        this.secKillId = secKillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    public long getSecKillId() {
        return secKillId;
    }

    public void setSecKillId(long secKillId) {
        this.secKillId = secKillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SecKillExecution{" +
                "secKillId=" + secKillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}