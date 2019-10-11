package com.edu.cn.enums;

/**
 *使用枚举表述常量字段
 */
public enum SecKillStatEnum {
    /**
     *
     */
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"你已秒杀成功，不能重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");
    /**
     *
     */
    private int state;

    private String stateInfo;

    SecKillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SecKillStatEnum stateOf(int index) {
        for (SecKillStatEnum statEnum : values()) {
            if (statEnum.getState() == index) {
                return statEnum;
            }
        }
        return null;
    }

}
