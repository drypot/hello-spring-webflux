package dom.drypot.hello;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class HelloFlux {

    public static void main(String[] args) {
        run2();
    }

    static void run1() {
        Flux.just("a", "b", "c")
            .subscribe(System.out::println);
    }

    static void run2() {
        Flux.just("alpha", "bravo", "charlie")
            .map(String::toUpperCase)
            .flatMap(s -> Flux.fromArray(s.split("")))
            .groupBy(String::toString)
            .sort((o1, o2) -> o1.key().compareTo(o2.key()))
            .flatMap(group -> Mono.just(group.key()).and(group.count()))
//            .map(keyAndCount -> keyAndCount.getT1() + " => " + keyAndCount.getT2())
            .subscribe(System.out::println);

    }
}
