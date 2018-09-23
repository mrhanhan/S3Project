package com.xmbcit.xj808.arms.base.routing.auto;

import java.lang.annotation.*;

/**
 * 参数注解
 */
@Target(ElementType.PARAMETER)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
    /**
     * 参数名称
     * @return
     */
    String value() default "";

}
