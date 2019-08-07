package com.cgx.interview;

/**********
 * @program: java-high-level
 * @description:
 * @author: cgx
 * @create: 2019-03-30 14:42
 **/
public class Cart {
    private String name;
    private boolean passLine;

    public Cart(String name, boolean passLine) {
        this.name = name;
        this.passLine = passLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPassLine() {
        return passLine;
    }

    public void setPassLine(boolean passLine) {
        this.passLine = passLine;
    }
}
