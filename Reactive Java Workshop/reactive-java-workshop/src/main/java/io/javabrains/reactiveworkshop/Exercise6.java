package io.javabrains.reactiveworkshop;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Exercise6 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.unresponsiveFlux() and ReactiveSources.unresponsiveMono()

        // Get the value from the Mono into a String variable but give up after 5 seconds
//        try{
//            String result = ReactiveSources.unresponsiveMono().block(Duration.ofSeconds(5));
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }

        // Get the value from unresponsiveFlux into a String list but give up after 5 seconds
        // Come back and do this when you've learnt about operators!
        try{
            List<String> list = ReactiveSources.unresponsiveFlux()
                    .collectList()
                    .timeout(Duration.ofSeconds(5))
                    .block();
            System.out.println("Resultant List : " + list);
        }catch (Exception e){
            System.out.println("Flux timed out : " + e.getMessage());
        }

        System.out.println("Press a key to end");
        System.in.read();
    }

}
