package com.cgx.concurrent;

import java.math.BigDecimal;

public class Test {
	
	public static void main(String[] args) {
		BigDecimal order = new BigDecimal(1);
		BigDecimal buv = new BigDecimal(3);
		
		BigDecimal s = order.divide(buv,4,BigDecimal.ROUND_HALF_UP);
		System.out.println(s.doubleValue());
	}
}
