package fr.bavencoff.wow.azerothinteldataapi;

import fr.bavencoff.wow.azerothinteldataapi.common.conf.ClockTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@Import(ClockTestConfiguration.class)
class AzerothIntelDataApiApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("Application context should load successfully")
    void contextLoads() {
        System.out.println("[DEBUG_LOG] Testing if application context loads");
        System.out.println("[DEBUG_LOG] Application context loaded: " + (applicationContext != null));
        if (applicationContext != null) {
            System.out.println("[DEBUG_LOG] Active profiles: " + String.join(", ", applicationContext.getEnvironment().getActiveProfiles()));
            System.out.println("[DEBUG_LOG] Default profiles: " + String.join(", ", applicationContext.getEnvironment().getDefaultProfiles()));
        }
        assert applicationContext != null : "Application context should not be null";
    }

}
