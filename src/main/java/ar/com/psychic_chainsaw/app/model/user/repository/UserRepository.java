package ar.com.psychic_chainsaw.app.model.user.repository;

import ar.com.psychic_chainsaw.app.model.user.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByEmailAddress(String emailAddress);

}
