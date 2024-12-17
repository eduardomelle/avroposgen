package guru.learningjournal.examples.kafka.avroposgen;

import guru.learningjournal.examples.kafka.avroposgen.services.datagenerator.InvoiceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import guru.learningjournal.examples.kafka.avroposgen.services.KafkaProducerService;

@SpringBootApplication
public class AvroPosGeneratorApplication implements ApplicationRunner {

	@Value("${application.configs.invoice.count}")
	private int INVOICE_COUNT;

	@Autowired
	private KafkaProducerService producerService;

	@Autowired
	private InvoiceGenerator invoiceGenerator;

	public static void main(String[] args) {
		SpringApplication.run(AvroPosGeneratorApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for (int i = 0; i < INVOICE_COUNT; i++) {
			this.producerService.sendMessage(this.invoiceGenerator.getNextInvoice());
			// Thread.sleep(1000);
			Thread.sleep(500);
		}
	}

}
