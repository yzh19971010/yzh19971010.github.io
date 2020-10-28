package test;

public class test3 {
    public static void main(String[] args){
        new GeneralClass();
    }
}
class ParentClass{
    static {
        System.out.println("①.我是父类静态块！");
    }
    {
        System.out.println("②.我是父类非静态块！");
    }
    public ParentClass(){
        System.out.println("③.我是父类构造函数！");
    }
}
class GeneralClass extends ParentClass{
    static{
        System.out.println("④.我是子类静态块。");
    }
    {
        System.out.println("②.我是子类非静态块！");
    }
    public GeneralClass(){
        System.out.println("⑥.我是子类构造函数！");
    }
}
