package fr.bavencoff.wow.azerothinteldataapi.common.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.time.Clock;

@Configuration
@Profile("!test")
public class ClockConfiguration {

    @Bean
    @Primary
    public Clock defaultClock() {
        return Clock.systemUTC();
    }
}
