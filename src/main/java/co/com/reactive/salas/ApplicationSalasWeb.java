package co.com.reactive.salas;


import co.com.reactive.salas.model.Salas;
import co.com.reactive.salas.repository.SalasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ApplicationSalasWeb {

    public  static void main(String[] args) {
        SpringApplication.run(ApplicationSalasWeb.class, args);
    }

    /**
     * CommandLineRunner se usa para ejecutar codigo una vez finalice el inicio.
     * Para este ejercicio inicializa valores en la base de datos
     * @param repository
     * @return lambda expresion
     */
    @Bean
    CommandLineRunner init(SalasRepository repository){
        return args -> {

            Flux<Salas> books = Flux.just(
                    Salas.builder().bloque("C").piso(1).capacidad(100).nombre("Nevados").horasDiponible(4).disponible(true).build(),
                    Salas.builder().bloque("C").piso(1).capacidad(100).nombre("Tierra").horasDiponible(4).disponible(true).build(),
                    Salas.builder().bloque("C").piso(1).capacidad(100).nombre("Playa").horasDiponible(4).disponible(true).build()
            ).flatMap(p-> repository.save(p));

            books.thenMany(repository.findAll())
                    .subscribe(System.out::println);

        };
    }
}
