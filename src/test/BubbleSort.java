package test;

public class BubbleSort {
    public void sort(int[] n) {
        //第一个for循环表示第几次循环
        for (int i = 1; i < n.length; i++) {
            //第二个for循环表示该次循环进行几次比较
            for (int j = 0; j < n.length - i; j++) {
                //判断比较是否进行换位
                if (n[j] > n[j + 1]) {
                    int temp = n[j];
                    n[j] = n[j + 1];
                    n[j + 1] = temp;
                }
            }
            print(n);
        }
    }
    public void print(int[] n) {
        for (int i = 0; i < n.length; i++)
            //输出排完序的数组
            System.out.print(n[i] + "\t");
        System.out.println();
    }
    public static void main(String[] args) {
        BubbleSort s = new BubbleSort();
        //此处为需要排序的数组
        int[] n = {100, 60, 80, 90, 75, 38};
        s.sort(n);
        s.print(n);
    }
}

