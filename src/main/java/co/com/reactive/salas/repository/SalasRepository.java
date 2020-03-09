package co.com.reactive.salas.repository;

import co.com.reactive.salas.model.Salas;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public  interface SalasRepository extends ReactiveMongoRepository<Salas, String> {
}
