/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SecKillCloseException
 * Author:   123
 * Date:     2019/9/8 10:00
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.edu.cn.exception;

/**
 * 秒杀关闭异常
 * @author 123
 * @create 2019/9/8
 * @since 1.0.0
 */
public class SecKillCloseException extends SecKillException{

    public SecKillCloseException(String message) {
        super(message);
    }

    public SecKillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}