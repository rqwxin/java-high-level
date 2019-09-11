package com.cgx.test;

import java.util.ArrayList;
import java.util.Collections;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-08-07 09:36
 **/
public class Recursion {
    public static void recur(int n_input){
        if (n_input>0){
            System.out.println(n_input+"");
            recur(n_input-1);
            System.out.println(n_input+"");
        }
    }

    public static void main(String[] args) {
        //recur(10);
        System.out.println(tryReturn());
    }

    public static int tryReturn(){
        int i =1;
        try {
          return   i=1;
        }catch ( Exception e){
            return i= 2;
        }finally {
            return i= 3;
        }

     // Collections c = new ArrayList<String>();

    }
}
