package test;/*随机生成30-100之间的整数*/
import java.util.Random;

public class test1 {
    public Integer random1(){
        int min = 30;
        int max = 100;
        int result = new Random().nextInt(max-min) +min;
        return result;
    }
    public Integer random2(){
        int min = 30;
        int max = 100;
        int result = (int) (Math.random()*(max-min) +min);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new test1().random1());
        System.out.println(new test1().random2());
    }
}
