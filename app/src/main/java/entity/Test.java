package entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static void main(String args[])
    {
      //  one();
       two();
      //  three();
    }
    public static void one()
    {
        Class   c1 =  PersonEntity.class;

        try {
            //获取无参公开构造器
            Constructor constructor = c1.getConstructor(null);
            //得到构造器并创建实例
            PersonEntity personEntity = (PersonEntity) constructor.newInstance(null);
            //获取有参私有构造器  并确定传值类型
            Constructor constructor1 = c1.getDeclaredConstructor(new Class[]{String.class, Integer.class});
            //开启私人访问权限
            constructor1.setAccessible(true);
            //得到构造器并创建实例  在创建的同时并进行传值
            PersonEntity personEntity1 = (PersonEntity) constructor1.newInstance("kessek", 1111);
            //对象调用方法
            personEntity1.fun();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void two()
    {
        PersonEntity personEntity = new PersonEntity();
        Class c2 = personEntity.getClass();
/*      无参方法
        try {
            //通过构造器得到对象
            PersonEntity personEntity1 = (PersonEntity) c2.getConstructor(null).newInstance();
            //通过类型实例得到方法  第一个参数为方法名字  第二个参数为方法的参数类型
            Method fun = c2.getMethod("fun", null);
            //开启方法 第一个参数为本类的对象  第二个参数为方法的参数值
            fun.invoke(personEntity1,null);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //有参构造
        try {
            //得到私有有参方法  第一个参数为方法名  第二个参数为参数类型的实例类型
            Method sun3 = c2.getDeclaredMethod("sun3", String.class, Integer.class);
            //开启方法的私有权限
            sun3.setAccessible(true);
            //开启方法 并进行传值
            sun3.invoke(personEntity,"sssssss",9999999);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void three()
    {
        try {
            Class c3 = Class.forName("entity.PersonEntity");
            //得到公开域值
            Field age = c3.getField("age");
            //得到私有域值
            Field name = c3.getDeclaredField("name");
            //获得构造器
            Constructor constructor = c3.getConstructor(null);
            //开启私有访问权限
            //修改域值  第一个参数为本类的对象  第二个为值
            PersonEntity personEntity = (PersonEntity) constructor.newInstance();
            name.setAccessible(true);
            name.set(personEntity,"sssssssss");
            age.set(personEntity,99);
            personEntity.fun();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
