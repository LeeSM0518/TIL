package Annotation;

// @Target과 ElementType을 정의하기 위해 import
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

// @Retention과 RetentionPolicy을 정의하기 위해 import
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Target({ElementType.METHOD})           // 메소드에만 적용
@Retention(RetentionPolicy.RUNTIME)     // 런타임 시까지 어노테이션 정보 유지

public @interface PrintAnnotation{
    String value() default "-";
    int number() default 15;
}