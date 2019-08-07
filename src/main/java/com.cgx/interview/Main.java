package com.cgx.interview;

import org.apache.log4j.spi.ErrorCode;

import java.util.Objects;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-03-30 15:12
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Cart cart = new Cart("小鹏",false);
      //  answer1(cart);
       answer2(cart);
      //  answer3(Lightenums.GUIDELIGHT,cart);
        //
    }



    //第一题答案
    private static void answer1(Cart cart) throws InterruptedException {
        //红灯
        IAllLigth ordinaryLight = new RedLight();
        RoadContext context = new RoadContext(ordinaryLight, cart);
        context.doHandle();
        Thread.sleep(1000L);
        //绿灯
        ordinaryLight = new GreenLight();
        context.setLigth(ordinaryLight);
        context.doHandle();
        //黄灯
        Thread.sleep(1000L);
        ordinaryLight = new YellowLight();
        context.setLigth(ordinaryLight);
        context.doHandle();
    }

    public static  void answer2(Cart cart){
        GuideLight light = new GoLight();
        RoadContext context = new RoadContext(light, cart);
        context.setLigth(light);
        context.doHandle();
    }

    private static void answer3(Lightenums lightenums,Cart cart) throws InterruptedException {
        if (Objects.equals(Lightenums.GUIDELIGHT,lightenums)){
            /**导向灯**/
            answer2(cart);
        }else if (Objects.equals(Lightenums.ORDINARYLIGHT,lightenums)){
            /**灯绿灯**/
            answer1(cart);
        }


    }

    private static enum Lightenums  {
        /**导向灯**/
        GUIDELIGHT,
        /**灯绿灯**/
        ORDINARYLIGHT,
    }

}
