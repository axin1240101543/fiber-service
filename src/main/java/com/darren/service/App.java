package com.darren.service;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

/**
 * 测试线程和纤程的区别
 * JDK版本：jdk11
 * 线程和纤程的本质区别：
 * 是否通过内核空间
 *
 * 线程的切换实在内核空间，消耗资源比较多，纤程的切换是在用户空间，消耗资源比较低；
 * 线程需要的内存占用约1M内存，数量有限，纤程的数量可以比线程的数量多的多。
 *
 * JDK暂不支持。。。
 * 如果java想使用，就要使用第三方库，例如：Quasar
 *
 * -javaagent:E:/Repository/co/paralleluniverse/quasar-core/0.8.0/quasar-core-0.8.0.jar
 */
public class App {
    public static void main( String[] args ) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {

            Fiber<Void> fiber = new Fiber<Void>(new SuspendableRunnable(){
                @Override
                public void run() throws SuspendExecution, InterruptedException {
                    calc();
                }
            }).start();

            /*Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    calc();
                }
            };

            new Thread(runnable).start();*/

        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    static void calc(){
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 200; j++) {
                result += i;
            }
        }
    }


}
