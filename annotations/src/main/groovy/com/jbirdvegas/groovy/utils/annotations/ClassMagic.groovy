package com.jbirdvegas.groovy.utils.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@interface ClassMagic {
    boolean enabled() default true
}
