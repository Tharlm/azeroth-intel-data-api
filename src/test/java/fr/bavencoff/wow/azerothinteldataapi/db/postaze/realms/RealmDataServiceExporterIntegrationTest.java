package fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms;

import fr.bavencoff.wow.azerothinteldataapi.common.conf.ClockTestConfiguration;
import fr.bavencoff.wow.azerothinteldataapi.common.conf.TestCacheConfig;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.impl.RealmDataServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.impl.RealmsRepository;
import fr.bavencoff.wow.azerothinteldataapi.testutils.mocks.RealmsMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.mockito.Mockito.times;

@SpringBootTest
@ActiveProfiles("test")
@Import({TestCacheConfig.class, ClockTestConfiguration.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableCaching
public class RealmDataServiceExporterIntegrationTest {

    @Autowired
    private RealmDataServiceExporter service;

    @MockitoSpyBean
    private RealmsRepository spyRepository;

    @Test
    void shouldCacheResultAndEvictOnSave() {
        Integer id = 123;

        RealmDao dao = RealmsMock.realm123();

        // Mock repository behavior
        Mockito.when(spyRepository.findById(id)).thenReturn(java.util.Optional.of(dao));

        // First call → should hit DB
        service.findById(id);
        Mockito.verify(spyRepository, times(1)).findById(id);

        // Second call → should use cache
        service.findById(id);
        Mockito.verify(spyRepository, times(1)).findById(id);

        // Now save → should evict cache
        service.save(dao);

        // New call → should hit DB again (cache was evicted)
        service.findById(id);
        Mockito.verify(spyRepository, times(2)).findById(id);
    }
}
