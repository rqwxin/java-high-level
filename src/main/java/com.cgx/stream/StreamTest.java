package com.cgx.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

	public static void main(String[] args) {
		String [] strArray = new String[] {"a", "b", "c"};
		List<String> list = Arrays.asList(strArray);
		//将列表元素转换为大写
		List<String> upList = list.stream().map(String::toUpperCase).collect(Collectors.toList());
		upList.forEach(System.out::println);
		System.out.println("##########################");
		//-对多
		Stream<List<Integer>> inputStream = Stream.of(
				Arrays.asList(1),
				Arrays.asList(2, 3),
				Arrays.asList(4, 5, 6)
				);
		
		Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
		outputStream.forEach(System.out::println);
	}
}
