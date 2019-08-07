package com.cgx.interview;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-03-30 16:00
 **/
public class StopLight implements GuideLight {
    @Override
    public void handle(Cart cart) {
        System.out.println("现在是停止灯");
    }
}
