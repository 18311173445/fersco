package butterknife;


import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ButterKnife {
    public static  void  bind(Activity activity)
    {
        bindContentView(activity);
        try {
            bindView(activity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static  void bindContentView(Activity activity)
    {
        Class<?> c = activity.getClass();
        ContentView contentView = c.getAnnotation(ContentView.class);
        if(contentView!=null)
        {
            try {
                int value = contentView.value();
                Method setContentView = c.getMethod("setContentView", int.class);
                setContentView.invoke(activity,value);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    public static  void bindView(Activity activity) throws IllegalAccessException {
        Class<?> c = activity.getClass();
        Field[] fields = c.getFields();
        if(fields!=null&&fields.length>0)
        {
            for (Field field : fields) {
                BindView bindView = field.getAnnotation(BindView.class);
                if(bindView!=null)
                {
                    int i = bindView.value();
                    View view = activity.findViewById(i);
                    field.set(activity,view);
                }
            }
        }
    }
}
