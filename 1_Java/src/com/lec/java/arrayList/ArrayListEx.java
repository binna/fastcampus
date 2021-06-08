package com.lec.java.arrayList;

import java.util.ArrayList;

public class ArrayListEx {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        list.add("a");
        list.add("b");
        list.add(3);
        list.add(true);

        for (Object s : list) {
            System.out.println(s);
        }
    }

}