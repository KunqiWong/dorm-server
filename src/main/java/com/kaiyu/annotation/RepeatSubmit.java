package com.kaiyu.annotation;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {

    /**
     * 间隔时间ms，小于此时间即为重复提交
     */
    int value() default 10000;


    /**
     * 排除指定的请求参数,防止影响重复请求判断
     */
    String[] excludeParamNames() default {};

}
