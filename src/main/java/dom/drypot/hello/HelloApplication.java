package dom.drypot.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

    @GetMapping("/images")
    Flux<Image> images() {
        return Flux.just(
            new Image("1", "learning-spring-boot-cover.jpg"),
            new Image("2", "learning-spring-boot-2nd-edition-cover.jpg"),
            new Image("3", "bazinga.png")
        );
    }

    @PostMapping("/images")
    Mono<Void> create(@RequestBody Flux<Image> images) {
        return images
            .map(image -> {
                //log.info("We will save " + image + " to a Reactive database soon!");
                return image;
            })
            .then();
    }

}
