package com.thagedy.leetcode.singleton;

/**
 * Created by Kaijia Wei on 2017/5/18.
 */

/**
 * 饿汉模式
 */
public class Singleton {
    private static Singleton instance = new Singleton();
    private Singleton(){}

    public static  Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Byte b = 1;
        long a = b;
    }
}
