package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.common.model.GenericTypeName;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.impl.ConnectedRealmDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.impl.RealmDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl.RegionDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl.ParametersServiceHelper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.mappers.RealmBoMapper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.ConnectedRealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.UpdateCrModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the RealmsServiceHelperImpl class.
 * <p>
 * These tests verify that the service correctly:
 * 1. Delegates calls to the DAO service exporters
 * 2. Maps DAO entities to API models using the mapper
 * 3. Returns the appropriate API models
 * 4. Handles exceptions properly
 */
@ExtendWith(MockitoExtension.class)
class RealmsServiceHelperImplTest {

    @Mock
    private RealmDaoServiceExporter realmDaoServiceExporter;

    @Mock
    private RegionDaoServiceExporter regionsServiceExporter;

    @Mock
    private ParametersServiceHelper typeServiceHelper;

    @Mock
    private ConnectedRealmDaoServiceExporter connectedRealmDaoServiceExporter;

    @Mock
    private RealmBoMapper realmMapper;

    @InjectMocks
    private RealmsServiceHelperImpl realmsServiceHelper;

    @Test
    @DisplayName("getConnectedRealm should return the correct API model when a valid ID is provided")
    void getConnectedRealm_ShouldReturnCorrectApiModel_WhenValidIdProvided() {
        // Arrange
        Integer connectedRealmId = 1;
        ConnectedRealmDao connectedRealmDao = new ConnectedRealmDao();
        connectedRealmDao.setId(connectedRealmId);

        ConnectedRealmBo expectedBo = new ConnectedRealmBo();
        expectedBo.setId(connectedRealmId);

        when(connectedRealmDaoServiceExporter.findById(connectedRealmId)).thenReturn(connectedRealmDao);
        when(realmMapper.daoToApi(connectedRealmDao)).thenReturn(expectedBo);

        // Act
        ConnectedRealmBo result = realmsServiceHelper.getConnectedRealm(connectedRealmId);

        // Assert
        assertNotNull(result);
        assertEquals(connectedRealmId, result.getId());

        verify(connectedRealmDaoServiceExporter, times(1)).findById(connectedRealmId);
        verify(realmMapper, times(1)).daoToApi(connectedRealmDao);
    }

    @Test
    @DisplayName("getConnectedRealms should return all connected realms")
    void getConnectedRealms_ShouldReturnAllConnectedRealms() {
        // Arrange
        ConnectedRealmDao connectedRealmDao1 = new ConnectedRealmDao();
        connectedRealmDao1.setId(1);

        ConnectedRealmDao connectedRealmDao2 = new ConnectedRealmDao();
        connectedRealmDao2.setId(2);

        List<ConnectedRealmDao> connectedRealmDaos = Arrays.asList(connectedRealmDao1, connectedRealmDao2);

        ConnectedRealmBo connectedRealmBo1 = new ConnectedRealmBo();
        connectedRealmBo1.setId(1);

        ConnectedRealmBo connectedRealmBo2 = new ConnectedRealmBo();
        connectedRealmBo2.setId(2);

        List<ConnectedRealmBo> expectedBos = Arrays.asList(connectedRealmBo1, connectedRealmBo2);

        when(connectedRealmDaoServiceExporter.findAll()).thenReturn(connectedRealmDaos);
        when(realmMapper.crDaosToApis(connectedRealmDaos)).thenReturn(expectedBos);

        // Act
        List<ConnectedRealmBo> result = realmsServiceHelper.getConnectedRealms();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());

        verify(connectedRealmDaoServiceExporter, times(1)).findAll();
        verify(realmMapper, times(1)).crDaosToApis(connectedRealmDaos);
    }

    @Test
    @DisplayName("findConnectedRealmsByRegion should return connected realms for the specified region")
    void findConnectedRealmsByRegion_ShouldReturnConnectedRealmsForRegion() {
        // Arrange
        GlobalRegion region = GlobalRegion.EU;

        ConnectedRealmDao connectedRealmDao1 = new ConnectedRealmDao();
        connectedRealmDao1.setId(1);

        ConnectedRealmDao connectedRealmDao2 = new ConnectedRealmDao();
        connectedRealmDao2.setId(2);

        List<ConnectedRealmDao> connectedRealmDaos = Arrays.asList(connectedRealmDao1, connectedRealmDao2);

        ConnectedRealmBo connectedRealmBo1 = new ConnectedRealmBo();
        connectedRealmBo1.setId(1);

        ConnectedRealmBo connectedRealmBo2 = new ConnectedRealmBo();
        connectedRealmBo2.setId(2);

        when(connectedRealmDaoServiceExporter.findAllByRegion_Tag(region)).thenReturn(connectedRealmDaos);
        when(realmMapper.daoToApi(connectedRealmDao1)).thenReturn(connectedRealmBo1);
        when(realmMapper.daoToApi(connectedRealmDao2)).thenReturn(connectedRealmBo2);

        // Act
        List<ConnectedRealmBo> result = realmsServiceHelper.findConnectedRealmsByRegion(region);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());

        verify(connectedRealmDaoServiceExporter, times(1)).findAllByRegion_Tag(region);
        verify(realmMapper, times(1)).daoToApi(connectedRealmDao1);
        verify(realmMapper, times(1)).daoToApi(connectedRealmDao2);
    }

    @Test
    @DisplayName("createNewConnectedRealm should create and return a new connected realm")
    void createNewConnectedRealm_ShouldCreateAndReturnNewConnectedRealm() {
        // Arrange
        Integer id = 1;
        GlobalRegion region = GlobalRegion.EU;
        UpdateCrModel infos = new UpdateCrModel();
        infos.setQueue(true);

        GenericTypeName populationName = new GenericTypeName();
        populationName.setName("High");
        populationName.setType("POPULATION");
        infos.setPopulation(populationName);

        GenericTypeName statusName = new GenericTypeName();
        statusName.setName("Up");
        statusName.setType("STATUS");
        infos.setStatus(statusName);

        RegionDao regionDao = new RegionDao();
        regionDao.setId((short) 3);
        regionDao.setTag(GlobalRegion.EU);

        ConnectedRealmDao connectedRealmDao = new ConnectedRealmDao();
        connectedRealmDao.setId(id);
        connectedRealmDao.setRegion(regionDao);

        ConnectedRealmBo expectedBo = new ConnectedRealmBo();
        expectedBo.setId(id);

        when(regionsServiceExporter.findByTag(region)).thenReturn(regionDao);
        when(connectedRealmDaoServiceExporter.findOptionalById(id)).thenReturn(Optional.empty());
        when(connectedRealmDaoServiceExporter.saveConnectedRealmDao(any(ConnectedRealmDao.class))).thenReturn(connectedRealmDao);
        when(realmMapper.daoToApi(connectedRealmDao)).thenReturn(expectedBo);

        // Act
        ConnectedRealmBo result = realmsServiceHelper.createNewConnectedRealm(id, region, infos);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());

        verify(regionsServiceExporter, times(1)).findByTag(region);
        verify(realmMapper, times(1)).daoToApi(any(ConnectedRealmDao.class));
    }

    @Test
    @DisplayName("createOrUpdateConnectedRealm should update existing connected realm")
    void createOrUpdateConnectedRealm_ShouldUpdateExistingConnectedRealm() {
        // Arrange
        Integer id = 1;
        GlobalRegion region = GlobalRegion.EU;
        UpdateCrModel infos = new UpdateCrModel();
        infos.setQueue(true);

        GenericTypeName populationName = new GenericTypeName();
        populationName.setName("High");
        populationName.setType("POPULATION");
        infos.setPopulation(populationName);

        GenericTypeName statusName = new GenericTypeName();
        statusName.setName("Up");
        statusName.setType("STATUS");
        infos.setStatus(statusName);

        RegionDao regionDao = new RegionDao();
        regionDao.setId((short) 3);
        regionDao.setTag(GlobalRegion.EU);

        ConnectedRealmDao existingConnectedRealmDao = new ConnectedRealmDao();
        existingConnectedRealmDao.setId(id);
        existingConnectedRealmDao.setRegion(regionDao);

        ParameterTypeDao populationDao = new ParameterTypeDao();
        populationDao.setKey(KeyParameterType.CRP);
        populationDao.setType("POPULATION");
        populationDao.setName("High");

        ParameterTypeDao statusDao = new ParameterTypeDao();
        statusDao.setKey(KeyParameterType.CRS);
        statusDao.setType("STATUS");
        statusDao.setName("Up");

        ConnectedRealmDao updatedConnectedRealmDao = new ConnectedRealmDao();
        updatedConnectedRealmDao.setId(id);
        updatedConnectedRealmDao.setRegion(regionDao);
        updatedConnectedRealmDao.setQueue(true);
        updatedConnectedRealmDao.setPopulation(populationDao);
        updatedConnectedRealmDao.setStatus(statusDao);

        ParameterBo populationBo = new ParameterBo();
        populationBo.setType("POPULATION");
        populationBo.setName("High");

        ParameterBo statusBo = new ParameterBo();
        statusBo.setType("STATUS");
        statusBo.setName("Up");

        ConnectedRealmBo expectedBo = new ConnectedRealmBo();
        expectedBo.setId(id);
        expectedBo.setQueue(true);
        expectedBo.setPopulation(populationBo);
        expectedBo.setStatus(statusBo);

        when(regionsServiceExporter.findByTag(region)).thenReturn(regionDao);
        when(connectedRealmDaoServiceExporter.findOptionalById(id)).thenReturn(Optional.of(existingConnectedRealmDao));
        when(connectedRealmDaoServiceExporter.saveConnectedRealmDao(any(ConnectedRealmDao.class))).thenReturn(updatedConnectedRealmDao);
        when(realmMapper.daoToApi(updatedConnectedRealmDao)).thenReturn(expectedBo);

        // Act
        ConnectedRealmBo result = realmsServiceHelper.createOrUpdateConnectedRealm(id, region, infos);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());

        verify(regionsServiceExporter, times(1)).findByTag(region);
        verify(connectedRealmDaoServiceExporter, times(1)).findOptionalById(id);
        verify(realmMapper, times(1)).daoToApi(any(ConnectedRealmDao.class));
    }

    @Test
    @DisplayName("getRealm should return the correct API model when a valid ID is provided")
    void getRealm_ShouldReturnCorrectApiModel_WhenValidIdProvided() {
        // Arrange
        Integer realmId = 1;
        RealmDao realmDao = new RealmDao();
        realmDao.setId(realmId);
        realmDao.setName("Test Realm");

        RealmBo expectedBo = new RealmBo();
        expectedBo.setId(realmId);
        expectedBo.setName("Test Realm");

        when(realmDaoServiceExporter.findById(realmId)).thenReturn(realmDao);
        when(realmMapper.daoToApi(realmDao)).thenReturn(expectedBo);

        // Act
        RealmBo result = realmsServiceHelper.getRealm(realmId);

        // Assert
        assertNotNull(result);
        assertEquals(realmId, result.getId());
        assertEquals("Test Realm", result.getName());

        verify(realmDaoServiceExporter, times(1)).findById(realmId);
        verify(realmMapper, times(1)).daoToApi(realmDao);
    }

    @Test
    @DisplayName("getRealms should return all realms")
    void getRealms_ShouldReturnAllRealms() {
        // Arrange
        RealmDao realmDao1 = new RealmDao();
        realmDao1.setId(1);
        realmDao1.setName("Realm 1");

        RealmDao realmDao2 = new RealmDao();
        realmDao2.setId(2);
        realmDao2.setName("Realm 2");

        List<RealmDao> realmDaos = Arrays.asList(realmDao1, realmDao2);

        RealmBo realmBo1 = new RealmBo();
        realmBo1.setId(1);
        realmBo1.setName("Realm 1");

        RealmBo realmBo2 = new RealmBo();
        realmBo2.setId(2);
        realmBo2.setName("Realm 2");

        List<RealmBo> expectedBos = Arrays.asList(realmBo1, realmBo2);

        when(realmDaoServiceExporter.findAll()).thenReturn(realmDaos);
        when(realmMapper.daosToApis(realmDaos)).thenReturn(expectedBos);

        // Act
        List<RealmBo> result = realmsServiceHelper.getRealms();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Realm 1", result.get(0).getName());
        assertEquals(2, result.get(1).getId());
        assertEquals("Realm 2", result.get(1).getName());

        verify(realmDaoServiceExporter, times(1)).findAll();
        verify(realmMapper, times(1)).daosToApis(realmDaos);
    }
}
