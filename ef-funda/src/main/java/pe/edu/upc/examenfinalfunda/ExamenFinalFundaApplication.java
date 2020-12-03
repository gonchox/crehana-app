package pe.edu.upc.examenfinalfunda;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ExamenFinalFundaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenFinalFundaApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }
}
