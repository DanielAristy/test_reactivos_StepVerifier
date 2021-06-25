package co.com.sofka.test_reactivos_StepVerifier.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class Servicio {
    public Mono<String> buscarUno() {
        return Mono.just("Pedro");
    }
    public Flux<String> buscarTodos() {
        return Flux.just("Pedro", "Maria", "Jesus", "Carmen" ,"Daniel");
    }
    public Flux<String> buscarTodosLento() {
        return Flux.just("Pedro", "Maria", "Jesus", "Carmen" ,"Daniel").delaySequence(Duration.ofSeconds(20));
    }
    public Flux<String> buscarTodosFiltro(){
        Flux<String> source = Flux.just("Jhon", "Daniel", "Maria", "Jose", "Pedro", "Cate")
                .filter(name -> name.length() == 4)
                .map(String::toUpperCase);
        return source;
    }
}
