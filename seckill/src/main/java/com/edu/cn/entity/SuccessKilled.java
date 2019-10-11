/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SuccessKilled
 * Author:   123
 * Date:     2019/9/7 12:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.edu.cn.entity;

import java.util.Date;

/**
 * <成功秒杀产品 用户信息>
 * @author 123
 * @create 2019/9/7
 * @since 1.0.0
 */
public class SuccessKilled {

    private long secKillId;

    private long userPhone;

    private short state;

    private Date createTime;

    //多个用户可以对多个商品

    private SecKill secKill;

    public long getSecKillId() {
        return secKillId;
    }

    public void setSecKillId(long secKillId) {
        this.secKillId = secKillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SecKill getSecKill() {
        return secKill;
    }

    public void setSecKill(SecKill secKill) {
        this.secKill = secKill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "secKillId=" + secKillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", secKill=" + secKill +
                '}';
    }
}
