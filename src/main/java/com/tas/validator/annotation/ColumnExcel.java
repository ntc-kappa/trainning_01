package com.tas.validator.annotation;

import com.tas.dto.CustomCellType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnExcel {

	public int col() default 0;

	public String title() default "";

	public CustomCellType type() default CustomCellType._STRING;
}
