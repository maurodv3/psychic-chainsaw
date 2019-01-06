package ar.com.psychic_chainsaw.warehouse.listener;

import ar.com.psychic_chainsaw.warehouse.model.record.repository.RecordRepository;
import ar.com.psychic_chainsaw.warehouse.model.user.entity.User;
import ar.com.psychic_chainsaw.warehouse.model.user.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

    private static final Logger LOG = LogManager.getLogger(StartupListener.class);

    private RecordRepository recordRepository;
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        LOG.info("Running startup tasks...");

        //Clean up users.
        userRepository
                .deleteAll()
                .subscribe();

        //Add admin user.
        userRepository
                .save(new User().setEmailAddress("admin").setPassword(passwordEncoder.encode("admin")))
                .subscribe();

        //Clean up records.
        recordRepository
                .deleteAll()
                .subscribe();
    }

    @Autowired
    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
