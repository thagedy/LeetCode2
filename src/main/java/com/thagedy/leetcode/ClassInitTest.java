package com.thagedy.leetcode;



/**
 * Created by Kaijia Wei on 2017/5/18.
 */
public class ClassInitTest {
    public static void main(String[] args) {
        int a =1;
        byte b = 2;
        b +=  a;

    }
}
class Father{
    public static int m = 33;
    static {
        System.out.println("父类被初始化");
    }
}

class Child extends Father{
    //public static String m = "45";
    public  static  final String NAME = "我是常量";
    static {
        System.out.println("子类被初始化");
    }
}
