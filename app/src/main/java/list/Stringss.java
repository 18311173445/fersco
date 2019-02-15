package list;

import java.util.ArrayList;

public class Stringss {
    public static void  main(String args[])
    {
        ArrayList<String> list = new ArrayList<>();
        try {
            ArrayList arrayList = list.getClass().newInstance();
            for (int i=0 ; i< 5;i++) {
                arrayList.add("ss"+i);
            }
            arrayList.add(33);
            for (Object o : arrayList) {
                System.out.println(o);
            }
            ArrayList<String>  arrayLists = ( ArrayList<String>)arrayList ;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
