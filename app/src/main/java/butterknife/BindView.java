package butterknife;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注释的生命周期
@Retention(RetentionPolicy.RUNTIME)
//注释的修饰类型 此处为域
@Target(ElementType.FIELD)
public @interface BindView {
    int value();
}
