package com.cgx.interview;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-03-30 14:41
 **/
public class RoadContext {
    private IAllLigth ligth;
    private Cart cart;


    public RoadContext(IAllLigth ligth, Cart cart) {
        this.ligth = ligth;
        this.cart = cart;
    }


    public IAllLigth getLigth() {
        return ligth;
    }

    public void setLigth(IAllLigth ligth) {
        this.ligth = ligth;
    }

    public void doHandle(){
        ligth.handle(cart);
    }
}
