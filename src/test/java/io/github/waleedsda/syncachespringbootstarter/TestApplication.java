package io.github.waleedsda.syncachespringbootstarter;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("io.github.waleedsda.syncachespringbootstarter")
@EnableJpaRepositories("io.github.waleedsda.syncachespringbootstarter")
public class TestApplication {
}
