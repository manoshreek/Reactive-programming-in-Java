package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException() - ERRORS and ERROR HANDLING

        // Print values from intNumbersFluxWithException and print a message when error happens
//        ReactiveSources.intNumbersFluxWithException()
//                .subscribe(
//                        num -> System.out.println(num),
//                        err -> System.out.println("ERROR!")
//                );
// or
//        ReactiveSources.intNumbersFluxWithException()
//                .doOnError(err -> System.out.println("ERROR!" + err.getMessage()))
//                .doOnComplete(() -> System.out.println("COMPLETE"))
//                .subscribe(System.out::println);

        // Print values from intNumbersFluxWithException and continue on errors
        // item - The item or element of Flux that caused the error = this can be captured!
//        ReactiveSources.intNumbersFluxWithException()
//                .onErrorContinue((err, item) -> System.out.println("ERROR!" + err.getMessage() + "Item that is erroneous : " + item))
//                .subscribe(System.out::println);

        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        // doFinally - is same like the Finally block of try-catch.
        ReactiveSources.intNumbersFluxWithException()
                .doFinally(signal -> {
                    if (signal == signal.ON_COMPLETE) {
                        System.out.println("Completed successfully");
                    } else if (signal == signal.ON_ERROR) {
                        System.out.println("Ended with Error");
                    }
                })
                .onErrorResume(err -> Flux.just(-1, -2))
                .subscribe(System.out::println);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
