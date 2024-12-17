package guru.learningjournal.examples.kafka.avroposgen.services;

import guru.learningjournal.examples.kafka.model.PosInvoice;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
// @Log4j2
public class KafkaProducerService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(KafkaProducerService.class);

    @Value("${application.configs.topic.name}")
    private String TOPIC_NAME;

    @Autowired
    private KafkaTemplate<String, PosInvoice> kafkaTemplate;

    public void sendMessage(PosInvoice invoice) {
        log.info(String.format("Producing Invoice No: %s", invoice.getInvoiceNumber()));
        this.kafkaTemplate.send(this.TOPIC_NAME, invoice.getStoreID(), invoice);
    }

}
