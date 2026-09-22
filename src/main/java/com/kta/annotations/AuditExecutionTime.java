package com.kta.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditExecutionTime {
    /**
     * Optional custom description/tag for the audited method.
     */
    String value() default "";

    /**
     * The time unit in which the execution duration should be displayed.
     * Default is milliseconds (TimeUnit.MILLISECONDS).
     */
    TimeUnit unit() default TimeUnit.MILLISECONDS;
}
