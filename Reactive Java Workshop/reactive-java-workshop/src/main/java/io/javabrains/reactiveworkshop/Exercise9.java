package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

public class Exercise9 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Print size of intNumbersFlux after the last item returns
        // Programming using Blocking
//        List<Integer> list = ReactiveSources.intNumbersFlux().collectList().block();
//        System.out.println(list.size());

// OR     Programming using non-blocking way
//        ReactiveSources.intNumbersFlux()
//                .count()
//                .subscribe(System.out::println);

        // Collect all items of intNumbersFlux into a single list and print it
        //ReactiveSources.intNumbersFlux().subscribe(System.out::println);

        // Transform to a sequence of sums of adjacent two numbers
        ReactiveSources.intNumbersFlux()
                .buffer(2)
                .map(list -> list.get(0) + list.get(1))
                .subscribe(System.out::println);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
