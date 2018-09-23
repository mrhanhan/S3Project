package com.xmbcit.xj808.arms.base.routing;

import java.lang.annotation.*;

/**
 * 路由注解<br/>
 * 路由的注解，
 * 此注注解包含在方法中<br/>
 * 添加此注解的方法Controller类，必须实现RoutController类<br/>
 * 方法可选参数类型：顺序谁便:
 * <br/>
 * javax.servlet.http.HttpServletRequest;
 * javax.servlet.http.HttpServletResponse;
 * java.io.PrintWriter;
 * javax.servlet.http.HttpSession;
 * 文件上传模式下使用com.jspsmart.upload.Files;
 *  * com.jspsmart.upload.Request;
 *  * com.jspsmart.upload.SmartUpload;
 *  */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Rout {
    /**
     * 需要路由的Url 例如 root
     *
     * @return
     */
    String url();

    /**
     * 请求类型
     *
     * @return
     */
    String method() default "";

    /**
     * 是否适用文件上传模式
     * @return
     */
    boolean isUpload() default false;
}
