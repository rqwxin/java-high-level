package com.cgx.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamLimit {
	
	public static void main(String[] args) {
		Stream se = Stream.of("a","b","c","d","e");
		//limit 返回 Stream 的前面 n 个元素
		List<String> s =  (List<String>) se.limit(3).collect(Collectors.toList());
		System.out.println(s.size());
		s.forEach(System.out::print);
	}
}
