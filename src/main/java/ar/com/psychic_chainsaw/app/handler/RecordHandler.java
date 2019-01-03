package ar.com.psychic_chainsaw.app.handler;

import ar.com.psychic_chainsaw.app.model.record.entity.Record;
import ar.com.psychic_chainsaw.app.model.record.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class RecordHandler {

    private RecordRepository recordRepository;

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(Record.class)
                .flatMap(recordRepository::save)
                .onErrorResume(Mono::error)
                .as(stored -> ok().body(fromPublisher(stored, Record.class)));
    }

    public Mono<ServerResponse> get(ServerRequest request) {
        return recordRepository
                .findById(request.pathVariable("id"))
                .onErrorResume(Mono::error)
                .flatMap(record -> ok().body(fromObject(record)));
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return recordRepository
                .findAll()
                .collect(Collectors.toList())
                .onErrorResume(Mono::error)
                .flatMap(records -> ok().body(fromObject(records)));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return recordRepository
                .deleteById(request.pathVariable("id"))
                .onErrorResume(Mono::error)
                .as(nothing -> ok().build(nothing));
    }

    @Autowired
    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }
}
