package fr.bavencoff.wow.azerothinteldataapi.common.conf;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;


@TestConfiguration
public class ClockTestConfiguration {
    @Bean
    @Primary
    public Clock defaultClock() {
        return Clock.fixed(Instant.parse("2024-01-20T23:28:40.703917Z"), ZoneId.systemDefault());
    }
}
