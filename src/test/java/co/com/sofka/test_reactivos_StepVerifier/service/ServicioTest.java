package co.com.sofka.test_reactivos_StepVerifier.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServicioTest {
    @Autowired
    Servicio servicio;
    @Test
    void testMono() {
        Mono<String> uno = servicio.buscarUno();
        StepVerifier
                .create(uno)
                .expectNext("Pedro")
                .verifyComplete();
    }
    @Test
    void testVarios() {
        Flux<String> uno = servicio.buscarTodos();
        StepVerifier
                .create(uno)
                .expectNext("Pedro")
                .expectNext("Maria")
                .expectNext("Jesus")
                .expectNext("Carmen")
                .expectNext("Daniel")
                .verifyComplete();
    }

    @Test
    void testVariosLento(){
        Flux<String> uno = servicio.buscarTodosLento();
        StepVerifier.create(uno)
                .expectNext("Pedro")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Maria")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Jesus")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Carmen")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("Daniel")
                .thenAwait(Duration.ofSeconds(1))
                .verifyComplete();
    }

    @Test
    void testBuscarTodosFiltro(){
        Flux<String> source = servicio.buscarTodosFiltro();
        StepVerifier.create(source)
                .expectNext("JHON")
                //Condicional para verificar de los valores filtrados que empiecen por "JO"
                .expectNextMatches(name -> name.startsWith("JO"))
                .expectNext("CATE")
                .expectComplete()
                .verify();
    }
}