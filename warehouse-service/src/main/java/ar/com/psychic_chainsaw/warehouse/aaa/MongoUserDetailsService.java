package ar.com.psychic_chainsaw.warehouse.aaa;

import ar.com.psychic_chainsaw.warehouse.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MongoUserDetailsService implements ReactiveUserDetailsService {

    private UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress).map(CustomUserDetails::new);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
