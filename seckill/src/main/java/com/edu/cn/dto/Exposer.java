/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Exposer
 * Author:   123
 * Date:     2019/9/8 9:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.edu.cn.dto;

/**
 *暴露秒杀地址
 * @author 123
 * @create 2019/9/8
 * @since 1.0.0
 */
public class Exposer {
    private boolean exposer;

    //加密措施

    private String md5;


    private long secKillId;

    private long now;

    private long start;

    private long end;

    public Exposer(boolean exposer, String md5, long secKillId) {
        this.exposer = exposer;
        this.md5 = md5;
        this.secKillId = secKillId;
    }

    public Exposer(boolean exposer, long secKillId, long now, long start, long end) {
        this.exposer = exposer;
        this.secKillId = secKillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposer, long secKillId) {
        this.exposer = exposer;
        this.secKillId = secKillId;
    }

    public boolean isExposer() {
        return exposer;
    }

    public void setExposer(boolean exposer) {
        this.exposer = exposer;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSecKillId() {
        return secKillId;
    }

    public void setSecKillId(long secKillId) {
        this.secKillId = secKillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposer=" + exposer +
                ", md5='" + md5 + '\'' +
                ", secKillId=" + secKillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}