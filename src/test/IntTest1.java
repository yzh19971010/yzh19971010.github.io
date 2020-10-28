package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
public class IntTest1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int count = 0;
            while (n != 0) {
                n = n & (n - 1);
                count++;
            }
            System.out.println(count);
        }
    }
}*/
/*public class IntTest1 {
    public int countBit(int n) {
        // write code here
        while (n.hasNext()) {
            int count = 0;
            while (n != 0) {
                n = n & (n - 1);
                count++;
            }
        }
        return 0;
    }
    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        countBit(n);
    }
}*/

/**
 * 给定一个int数字，计算出int数字对应的二进制中1的个数
 */

public class IntTest1 {
    public static int toBinary(int num) {
        List<Integer> outList = new ArrayList<>();
        int count = 0;
        while (num > 0) {
            int tempData = num % 2;
            outList.add(tempData);
            if (outList.contains(tempData) && tempData == 1) {
                count++;
            }
            num /= 2;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println(toBinary(num));
    }
}
