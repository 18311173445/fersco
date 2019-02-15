package entity;

public class PersonEntity {
    private String name ;
    public Integer age ;
    public PersonEntity() {
    }
    private PersonEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public void fun() {
        System.out.println("age:"+age+" name:"+name);

        System.out.println("fun");
    }
    private void sun3(String name,Integer age){
        System.out.println("age:"+age+" name:"+name);

        System.out.println("sun3");
    }
}
