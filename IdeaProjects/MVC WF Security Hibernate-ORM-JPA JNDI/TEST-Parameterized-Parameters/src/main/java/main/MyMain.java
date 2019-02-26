package main;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyMain {

    public static void main(String[] args) {

//        List<String> list = Stream.of(1, 1, 2, 3, 1, 4, 5)
//                .filter(x -> x < 4)
//                .collect(Collectors.collectingAndThen(
//                        Collectors.toMap(
//                                Function.identity(),
//                                s -> String.format("-%d-", s),
//                                (k, v) -> String.join(", ", k, v)),
//                        map -> map.entrySet().stream()))
//                .map(x -> x.toString())
//                .collect(Collectors.toList());

        Long s = Stream.of("a", "b", "c", 1)
                .collect(Collectors.counting());

        System.out.println(s);

    }

    public static int sum(int a, int b) {
        return a + b;
    }
}
