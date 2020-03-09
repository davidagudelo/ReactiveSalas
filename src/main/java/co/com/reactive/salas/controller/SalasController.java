package co.com.reactive.salas.controller;

import co.com.reactive.salas.model.Salas;
import co.com.reactive.salas.repository.SalasRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/salas")
@AllArgsConstructor
public class SalasController {

    private SalasRepository salasRepository;

    /**
     * Controlador que Busca todas las salas
     * @param
     * @return Flux<Salas>
     */

    @GetMapping
    public Flux<Salas> getAllSalas(){
        return salasRepository.findAll();
    }

    /**
     * Controlador que Busca Sala por id
     * @param id
     * @return Mono<ResponseEntity<Salas>>
     */

    @GetMapping("/id/{id}")
    public Mono<ResponseEntity<Salas>> getBook(@PathVariable String id){
        return salasRepository.findById(id).map(book -> ResponseEntity.ok(book))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Controlador que Busca Sala por nombre
     * @param Sala
     * @return Flux<Salas>
     */

    @GetMapping("/nombre/{nombre}")
    public Flux<Salas> getSalaName(@PathVariable String nombre){
        return salasRepository.findAll().filter(salas -> salas.getNombre().equals(nombre) );
   }

    /**
     * Controlador que Crea una sala
     * @param Sala
     * @return  Mono<Salas>
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Salas> saveSala(@RequestBody Salas sala){
        return salasRepository.save(sala);

    }

    /**
     * Controlador que elimina un sala en base a su id
     * @param id
     * @return Mono<ResponseEntity<Void>>
     */
    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteSala(@PathVariable(value = "id") String id){
        return salasRepository.findById(id)
                .flatMap(existingBook -> salasRepository.delete(existingBook)
                        .then(Mono.just(ResponseEntity.ok().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Controlador que elimina todas los Salas
     * @return Mono<Void>
     */
    @DeleteMapping
    public Mono<Void> deleteAllSalas(){
        return salasRepository.deleteAll();
    }
}
