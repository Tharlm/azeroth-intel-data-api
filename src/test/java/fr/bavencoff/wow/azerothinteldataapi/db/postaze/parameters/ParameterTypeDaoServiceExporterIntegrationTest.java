package fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters;

import fr.bavencoff.wow.azerothinteldataapi.common.conf.ClockTestConfiguration;
import fr.bavencoff.wow.azerothinteldataapi.common.conf.TestCacheConfig;
import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.impl.ParameterTypeDaoRepository;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.impl.ParameterTypeDaoServiceExporter;
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
@Import({TestCacheConfig.class, ClockTestConfiguration.class}) // see config below
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableCaching
public class ParameterTypeDaoServiceExporterIntegrationTest {


    @Autowired
    private ParameterTypeDaoServiceExporter service;

    @MockitoSpyBean
    private ParameterTypeDaoRepository spyRepository;

    @Test
    void shouldCacheResultAndEvictOnSave() {
        KeyParameterType key = KeyParameterType.REA; // use your enum value
        String type = "TEST";

        ParameterTypeDao dao = new ParameterTypeDao();
        dao.setKey(key);
        dao.setType(type);
        dao.setName("Test");

        dao = spyRepository.save(dao);// save manually

        // First call → should hit DB
        service.findByKeyAndType(key, type);
        Mockito.verify(spyRepository, times(1)).findByKeyAndType(key, type);

        // Second call → should use cache
        service.findByKeyAndType(key, type);
        Mockito.verify(spyRepository, times(1)).findByKeyAndType(key, type);

        // Now save → should evict cache
        service.save(dao);

        // New call → should hit DB again (cache was evicted)
        service.findByKeyAndType(key, type);
        Mockito.verify(spyRepository, times(2)).findByKeyAndType(key, type);
    }
}
