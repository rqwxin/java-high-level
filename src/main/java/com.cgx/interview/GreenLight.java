package com.cgx.interview;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-03-30 14:51
 **/
public class GreenLight implements OrdinaryLight {
    @Override
    public void handle(Cart cart) {
        System.out.println("现在是绿灯，小车前进");
    }
}
