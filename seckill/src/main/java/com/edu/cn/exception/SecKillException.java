/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SecKillException
 * Author:   123
 * Date:     2019/9/8 9:54
 * Description: 秒杀相关异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.edu.cn.exception;

/**
 *秒杀相关异常
 * @author 123
 * @create 2019/9/8
 * @since 1.0.0
 */
public class SecKillException extends RuntimeException{
    public SecKillException(String message) {
        super(message);
    }

    public SecKillException(String message, Throwable cause) {
        super(message, cause);
    }
}