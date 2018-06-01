package com.cgx.stream;

import java.util.stream.Stream;

public class StreamRecude {

	public static void main(String[] args) {
		//reduce 这个方法的主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），
		//然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合
		String[] strs = new String[]{"a","b","c"};
		String concat =  Stream.of(strs).reduce("", String::concat);
		System.out.println("Stream reduce 字符串连接:"+concat);
		
		// 求最小值，minValue = -3.0
		//以double最多值开始在列表中找最小值
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min); 
		System.out.println("Stream reduce 求最小值:"+minValue);
		// 求和，sumValue = 10, 有起始值
		int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		System.out.println("Stream reduce 求和:"+sumValue);
		// 求和，sumValue = 10, 无起始值
		int sumValue2 = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		System.out.println("Stream reduce 求和(无起始值):"+sumValue2);
		// 过滤，字符串连接，concat = "ace"
		concat = Stream.of("a", "B", "c", "D", "e", "F").
		 filter(x -> x.compareTo("Z") > 0).
		 reduce("", String::concat);
	}

}
