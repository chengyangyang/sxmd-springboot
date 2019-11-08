package com.sxmd.javafx;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author cy
 * @date 2019年11月08日 16:01
 * Version 1.0
 */
public class MyTest {

    public static void main(String[] args){
        List<Student> list = new ArrayList<Student>();
        // 构造数据
        for (int i = 0; i < 10; i++) {
            list.add(new Student("aaaa",i));
        }
        long start = System.currentTimeMillis();
        System.out.println("时间"+start);

        list.stream().parallel().forEach(x->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x.age = 2;
        });

        long end = System.currentTimeMillis() - start;
        System.out.println("时间"+end);
        list.stream().forEach(x->{
            System.out.println(x.age);
        });
    }




    static class Student{
        public Student(String name,int age){
            this.name = name;
            this.age = age;
        };
        public String name;
        public int age;
    }

}
