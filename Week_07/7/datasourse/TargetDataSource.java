package com.myproject.moods.distribute.datasourse;

import java.lang.annotation.*;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-08
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name()default "master";
}
