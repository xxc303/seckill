/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SecKillResult
 * Author:   123
 * Date:     2019/9/9 13:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.edu.cn.dto;

/**
 *所有ajax请求返回类型封装json结果
 * @author 123
 * @create 2019/9/9
 * @since 1.0.0
 */

public class SecKillResult<T> {

    //秒杀结果

    private Boolean success;

    private T data;

    private String error;

    public SecKillResult(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SecKillResult(Boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}