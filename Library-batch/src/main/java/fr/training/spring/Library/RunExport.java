package fr.training.spring.Library;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
public class RunExport {
    public static void main(final String[] args) {
        final ApplicationContext context = SpringApplication.run(RunExport.class, args);
        System.exit(SpringApplication.exit(context));
    }
}
