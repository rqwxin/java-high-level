package com.cgx.interview;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-03-30 16:00
 **/
public class LeftLight implements GuideLight {
    @Override
    public void handle(Cart cart) {
        System.out.println("现在是左向灯");
    }
}
