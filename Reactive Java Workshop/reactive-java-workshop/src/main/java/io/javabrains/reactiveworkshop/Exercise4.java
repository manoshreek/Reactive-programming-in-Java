package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise4 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        //ReactiveSources.intNumberMono().subscribe(System.out::println);

        // Get the value from the Mono into an integer variable

        // We can use .subscribe() method just like the above example, but we are using block() method for mono,
        // because we know that a Mono emits at most one item and until the item arrives we have to block the processing of
        // other lines of code as the next line is printing the value which is possible only when the item arrives.
        // Using block() - We make async programming to follow sync or chaining process - So, it should we mostly avoided.
        //                 There's also a chance that no value is emitted and this situation creates an infinite waiting time.
        //A Mono can return a list or a Flux or a Mono but not advisable as flattering chained monos or flux is a complex process.
        Integer value = ReactiveSources.intNumberMono().block();
        System.out.println(value);

        // We have these 2 methods in every exercise as the reactive programming is a parallel programming paradigm
        // that will end abruptly without waiting for flux and mono to collect items
        System.out.println("Press a key to end");
        System.in.read();
    }

}
