package com.cgx.interview;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-03-30 14:51
 **/
public class RedLight implements OrdinaryLight {
    @Override
    public void handle(Cart cart) {
        if (cart.isPassLine()){
            System.out.println("现在是红灯，小车已过线，要扣分");
            return;
        }
        System.out.println("现在是红灯，小车停止前进");
    }
}
