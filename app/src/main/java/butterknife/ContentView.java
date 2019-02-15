package butterknife;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注释的生命周期
@Retention(RetentionPolicy.RUNTIME)
//注释修饰的类型
@Target(ElementType.TYPE)
public @interface ContentView {
    int value();
}
