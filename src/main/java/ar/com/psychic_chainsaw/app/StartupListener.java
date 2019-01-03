package ar.com.psychic_chainsaw.app;

import ar.com.psychic_chainsaw.app.record.repository.RecordRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener {

    private static final Logger LOG = LogManager.getLogger(StartupListener.class);

    private RecordRepository recordRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        LOG.info("Running startup tasks...");
        recordRepository
                .deleteAll()
                .subscribe();
    }

    @Autowired
    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }
}
