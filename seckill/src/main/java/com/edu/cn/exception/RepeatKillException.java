/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: RepeatKillException
 * Author:   123
 * Date:     2019/9/8 9:56
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.edu.cn.exception;

/**
 *重复秒杀异常（运行期异常）
 * @author 123
 * @create 2019/9/8
 * @since 1.0.0
 */
public class RepeatKillException extends SecKillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}