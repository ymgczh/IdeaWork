package com.yzyy.zh;

import java.math.BigDecimal;

public class Test {
    private double money = 0.00;
    public static void main(String[] args) {
        fuck();
    }
    public static void fuck(){
        int a = 0;
        int b = 1;
        int c = a + b;
        double x = 3.33;
        double m = 2.2222;
        BigDecimal xb = new BigDecimal(x);
        BigDecimal mb = new BigDecimal(m);
        BigDecimal add = xb.add(mb);

        System.out.println(add.doubleValue());
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
