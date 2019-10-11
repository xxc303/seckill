/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SecKill
 * Author:   123
 * Date:     2019/9/7 13:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.edu.cn.entity;

import java.util.Date;

/**
 * <秒杀商品信息>
 * @author 123
 * @create 2019/9/7
 * @since 1.0.0
 */
public class SecKill {

    private long secKillId;

    private String name;

    private int number;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    public long getsecKillId() {
        return secKillId;
    }

    public void setsecKillId(long secKillId) {
        this.secKillId = secKillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SecKill{" +
                "secKillId=" + secKillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}