package ar.com.psychic_chainsaw.app.model.record.repository;

import ar.com.psychic_chainsaw.app.model.record.entity.Record;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecordRepository extends ReactiveMongoRepository<Record, String> {
}
