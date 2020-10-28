package test;


import java.io.*;

//将文件复制到指定文件夹
public class FileCopy {
    public static void main(String[] args) {
        /**
         * 方法一：利用JAVA IO复制文件到指定文件夹
         */
        //定义一个File对象，即文件对象,source 代表源文件
        File source = new File("e:/tomcat8.zip");
        //定义目标对象的存储路径
        File target = new File("e:/target/tomcat8.zip");
        //因为zip文件是二进制文件，所以定义InputStream
        InputStream input = null;
        OutputStream output = null;

        try {
            //FileInputStream会抛出文件不存在异常，给其增加try...catch块
            input = new FileInputStream(source);
            //创建字节输出流
            output = new FileOutputStream(target);
            //定义数组byte,表示存储的缓冲区，缓冲区的大小为1KB
            byte[] buf = new byte[1024];
            //定义整型变量代表现在读取的位置
            int byteRead;
            //利用while循环对文件进行循环读取，没执行一次，读取1KB字节，当循环读取到达文件结尾时会返回-1，代表结束
            while ((byteRead = input.read(buf)) != -1) {
                //将读取的字节数组写入指定的流中
                output.write(buf, 0, byteRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //无论程序是否执行成功，我们对打开的输入输出流都要进行关闭
                input.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**
         * 方法二：利用FileChannel实现文件复制
         */
        /**
         * 方法三：使用Commons IO组件实现文件复制
         */
        //FileUtils.copyFile(Source, Target);
    }
}
