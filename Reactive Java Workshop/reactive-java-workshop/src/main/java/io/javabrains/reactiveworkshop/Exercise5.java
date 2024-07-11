package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks

        // What can be expected out of Mono or a Flux??
        // 1. An item or a stream of item
        // 2. A complete event -
        // 3. A failure event - 2 and 3 are terminal events meaning no other event can be expected after their emission.

        // subscribe() method implementation types-
        // 1. subscribe() - When one doesn't make use of subscribed output
        // 2. subscribe(Consumer<? super T> consumer) - Usually used E.g., .subscribe(observer -> doSomethingWithOutput())
        // 3. subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> errorConsumer) - to throw error if something odd happens
        // 4. subscribe(Consumer<? super T> consumer,
        //              Consumer<? super Throwable> errorConsumer,
        //              Runnable completeConsumer);
        // 5. subscribe(Subscription subscription); - Instead on using observer, we use hooks on every action like subscribe, complete, next.

//        ReactiveSources.intNumbersFlux().subscribe(
//                number -> System.out.println(number),
//                err -> System.out.println("ERROR : " + err.getMessage()),
//                () -> System.out.println("Complete")
//        );


        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>());


        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {
    @Override
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribe happened");
        request(2);
    }

    // Helps to control or manage BackPressure - E.g., The below code tells the system that it is okay to receive 1 item to handle everytime.
    // If we don't specify this, the programs ends abruptly as it is unable to handle the Back Pressure of handling too many items at once.
    @Override
    public void hookOnNext(T value) {
        System.out.println(value.toString()+ " received");
        request(1);
    }

    @Override
    public void hookOnError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void hookOnComplete() {
        System.out.println("Complete happened");
    }
}