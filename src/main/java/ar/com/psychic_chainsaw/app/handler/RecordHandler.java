package ar.com.psychic_chainsaw.app.handler;

import ar.com.psychic_chainsaw.app.record.entity.Record;
import ar.com.psychic_chainsaw.app.record.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Component
public class RecordHandler {

    private RecordRepository recordRepository;

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(Record.class)
                .flatMap(recordRepository::save)
                .onErrorResume(e -> Mono.error(new Exception()))
                .as(stored -> ServerResponse
                        .ok()
                        .body(BodyInserters.fromPublisher(stored, Record.class)));

    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return recordRepository
                .findAll()
                .collect(Collectors.toList())
                .onErrorResume(e -> Mono.error(new Exception()))
                .flatMap(records -> ServerResponse
                        .ok()
                        .body(BodyInserters.fromObject(records)));
    }

    @Autowired
    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }
}
