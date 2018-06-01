package com.cgx.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFilter {

	public static void main(String[] args) {
		Integer[] sixNums = {1, 2, 3, 4, 5, 6};
		
		Integer[] evensArr =Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
		//转换为列表
		List<Integer> evens =Stream.of(sixNums).filter(n -> n%2 == 0).collect(Collectors.toList());
		evens.forEach(System.out::println);
		System.out.println("###############");
		List<String> ls = Stream.of("one", "two", "three", "four")
						 .filter(e -> e.length() > 3)
						 .peek(e -> System.out.println("Filtered value: " + e))
						 .map(String::toUpperCase)
						 .peek(e -> System.out.println("Mapped value: " + e))
						 .collect(Collectors.toList());
		ls.forEach(System.out::println);
		
		//Optional<T>.ofNullable
	}
}
