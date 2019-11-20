package pl.waskicompany.netguru.netgururecruitmenttask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import pl.waskicompany.netguru.netgururecruitmenttask.models.Movie;

@SpringBootApplication
@EnableJpaAuditing
public class NetguruRecruitmentTaskApplication {

	private static final Logger log = LoggerFactory.getLogger(NetguruRecruitmentTaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NetguruRecruitmentTaskApplication.class, args);
	}

}
