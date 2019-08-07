package com.cgx.interview;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-03-30 14:51
 **/
public class YellowLight implements OrdinaryLight {
    @Override
    public void handle(Cart cart) {
        if (cart.isPassLine()){
            System.out.println("现在是黄灯，小车已过线可以继续前进");
        }else {
            System.out.println("现在是黄灯，小车停止前进");
        }
    }
}
