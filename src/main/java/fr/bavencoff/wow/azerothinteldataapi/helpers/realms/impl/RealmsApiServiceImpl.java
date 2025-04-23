package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.realms.RealmAlreadyExistException;
import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.connectedrealms.ConnectedRealmAlreadyExistsException;
import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.impl.ConnectedRealmServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.impl.ParameterTypeServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.impl.RealmServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl.RegionDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.ConnectedRealmApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmApi;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class RealmsApiServiceImpl implements RealmApiService {

    private final RealmServiceExporter realmServiceExporter;
    private final RegionDaoServiceExporter regionsServiceExporter;
    private final ParameterTypeServiceExporter typeServiceExporter;
    private final ConnectedRealmServiceExporter connectedRealmServiceExporter;
    private final RealmUtilsApiMapper realmMapper;

    @Autowired
    public RealmsApiServiceImpl(
            final RealmServiceExporter realmServiceExporter,
            final RegionDaoServiceExporter regionsServiceExporter,
            final ParameterTypeServiceExporter typeServiceExporter,
            final ConnectedRealmServiceExporter connectedRealmServiceExporter,
            final RealmUtilsApiMapper realmMapper
    ) {
        this.realmServiceExporter = realmServiceExporter;
        this.regionsServiceExporter = regionsServiceExporter;
        this.typeServiceExporter = typeServiceExporter;
        this.connectedRealmServiceExporter = connectedRealmServiceExporter;
        this.realmMapper = realmMapper;
    }

    @Override
    public RealmApi getRealm(Integer idRealm) {
        return realmMapper.daoToApi(realmServiceExporter.findById(idRealm));
    }

    @Override
    public List<RealmApi> getRealms() {
        return realmMapper.daosToApis(realmServiceExporter.findAll());
    }

    @Override
    public RealmApi createNewRealm(
            Integer id,
            Integer idConnectedRealm,
            Short idRegion,
            RealmApi info
    ) {
        ConnectedRealmDao connectedRealmDao = this.connectedRealmServiceExporter.findById(idConnectedRealm);
        RegionDao regionDao = this.regionsServiceExporter.findById(idRegion);
        return this.realmMapper.daoToApi(createNewRealm(id, connectedRealmDao, regionDao, info));
    }

    @Override
    public RealmApi createOrUpdateRealm(
            Integer id,
            Integer idConnectedRealm,
            Short idRegion,
            RealmApi info
    ) {
        ConnectedRealmDao connectedRealmDao = this.connectedRealmServiceExporter.findById(idConnectedRealm);
        RegionDao regionDao = this.regionsServiceExporter.findById(idRegion);
        return this.realmMapper.daoToApi(this.createOrUpdateRealm(id, connectedRealmDao, regionDao, info));
    }

    /**
     * Créé ou modifie un realm
     *
     * @param id             id du realm
     * @param connectedRealm CR parent
     * @param region         region
     * @param info           information sur le realm
     * @return le realm créé ou modifié
     */
    private RealmDao createOrUpdateRealm(
            Integer id,
            ConnectedRealmDao connectedRealm,
            RegionDao region,
            RealmApi info
    ) {
        Optional<RealmDao> optionalRealm = this.realmServiceExporter.findOptionalById(id);
        if (optionalRealm.isEmpty()) {
            RealmDao realmDao = new RealmDao();
            realmDao.setId(id);

            return this.saveRealm(
                    realmDao,
                    connectedRealm,
                    region,
                    info
            );
        }
        return this.saveRealm(optionalRealm.get(), connectedRealm, region, info);
    }

    private RealmDao createNewRealm(
            Integer id,
            ConnectedRealmDao connectedRealm,
            RegionDao region,
            RealmApi info
    ) {
        Optional<RealmDao> optionalRealm = this.realmServiceExporter.findOptionalById(id);
        if (optionalRealm.isPresent()) {
            throw new RealmAlreadyExistException(id);
        }

        RealmDao realmDao = new RealmDao();
        realmDao.setId(id);

        return this.saveRealm(
                realmDao,
                connectedRealm,
                region,
                info
        );
    }

    /**
     * Enregistre les informations un realm
     * @param realmDao le realm
     * @param connectedRealm le CR parent
     * @param region la region
     * @param info les informations à mettre à jour
     * @return
     */
    private RealmDao saveRealm(
            RealmDao realmDao,
            ConnectedRealmDao connectedRealm,
            RegionDao region,
            RealmApi info
    ) {
        // maj des entites parents
        realmDao.setRegion(region);
        realmDao.setConnectedRealm(connectedRealm);

        // maj du type de realm
        if (info.getType() == null) {
            realmDao.setType(null);
        } else if (realmDao.getType() == null || !StringUtils.equals(
                realmDao.getType().getType(),
                info.getType().getType()
        )) {
            ParameterTypeDao parameterTypeDao = this.typeServiceExporter.getParameterType(
                    KeyParameterType.REA,
                    info.getType().getType(),
                    info.getType().getLabel()
            );
            realmDao.setType(parameterTypeDao);
        } else {
            realmDao.getType().setName(info.getType().getLabel());
        }

        // maj des infos
        realmDao.setName(info.getName());
        realmDao.setCategory(info.getCategory());
        realmDao.setLocale(info.getLocale());
        realmDao.setTimezone(info.getTimezone());
        realmDao.setSlug(info.getSlug());
        realmDao.setTournament(info.isTournament());
        realmDao.setDateLastUpdate(Instant.now());
        realmDao.setActive(true);
        return this.realmServiceExporter.save(realmDao);
    }

    /**
     * {@inheritDoc}
     * @param id ID du CR
     */
    @Override
    public ConnectedRealmApi getConnectedRealm(Integer id) {
        return realmMapper.daoToApi(connectedRealmServiceExporter.findById(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConnectedRealmApi> getConnectedRealms() {
        final List<ConnectedRealmDao> all = connectedRealmServiceExporter.findAll();
        return realmMapper.crDaosToApis(all);
    }

    @Override
    public List<ConnectedRealmApi> searchConnectedRealms(GlobalRegion region) {
        return connectedRealmServiceExporter.findAllByRegion_Tag(region)
                .stream()
                .map(realmMapper::daoToApi)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConnectedRealmApi createOrUpdateConnectedRealm(
            Integer id,
            Short idRegion,
            ConnectedRealmApi infos
    ) {
        RegionDao regionDao = this.regionsServiceExporter.findById(idRegion);
        return this.realmMapper.daoToApi(this.createOrUpdateConnectedRealm(id, regionDao, infos));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConnectedRealmApi createNewConnectedRealm(
            Integer id,
            Short idRegion,
            ConnectedRealmApi infos
    ) {
        RegionDao region = this.regionsServiceExporter.findById(idRegion);
        return this.realmMapper.daoToApi(this.createNewConnectedRealm(id, region, infos));
    }

    /**
     * Enregistre ou modifie un CR
     * @param id id du CR
     * @param region region
     * @param infos information
     * @return CR modifié / créé
     */
    private ConnectedRealmDao createOrUpdateConnectedRealm(
            Integer id,
            RegionDao region,
            ConnectedRealmApi infos
    ) {
        Optional<ConnectedRealmDao> optionalById = this.connectedRealmServiceExporter.findOptionalById(id);
        if (optionalById.isEmpty()) {
            ConnectedRealmDao connectedRealmDao = new ConnectedRealmDao();
            connectedRealmDao.setId(id);
            return this.saveConnectedRealm(connectedRealmDao, region, infos);
        }
        return this.updateConnectedRelm(optionalById.get(), region, infos);
    }

    /**
     * Créé un CR. Retourne une exception s'il existe deja un CR avec la même ID
     * @param id ID du CR
     * @param region region
     * @param infos information
     * @return CR
     */
    private ConnectedRealmDao createNewConnectedRealm(
            Integer id,
            RegionDao region,
            ConnectedRealmApi infos
    ) {

        Optional<ConnectedRealmDao> optionalById = this.connectedRealmServiceExporter.findOptionalById(id);
        if (optionalById.isPresent()) {
            throw new ConnectedRealmAlreadyExistsException(id);
        }

        ConnectedRealmDao connectedRealmDao = new ConnectedRealmDao();
        connectedRealmDao.setId(id);
        return this.saveConnectedRealm(connectedRealmDao, region, infos);
    }

    /**
     * Modifie un CR
     * @param connectedRealmDao entité du CR
     * @param region region
     * @param infos information
     * @return entité màj
     */
    private ConnectedRealmDao updateConnectedRelm(
            ConnectedRealmDao connectedRealmDao,
            RegionDao region,
            ConnectedRealmApi infos
    ) {
        return this.saveConnectedRealm(connectedRealmDao, region, infos);
    }

    /**
     * Enregistre les infos sur un CR
     * @param connectedRealmDao entité du CR
     * @param regionDao region
     * @param infos infos à mettre à jour
     * @return entité màj
     */
    private ConnectedRealmDao saveConnectedRealm(
            ConnectedRealmDao connectedRealmDao,
            RegionDao regionDao,
            ConnectedRealmApi infos
    ) {

        // ajout des informations sur la population du CR
        if (infos.getPopulation() == null) {
            connectedRealmDao.setPopulation(null);
        } else if (
                connectedRealmDao.getPopulation() == null ||
                        !StringUtils.equals(connectedRealmDao.getPopulation().getType(), infos.getPopulation().getType())
        ) {
            ParameterTypeDao populationParam = this.typeServiceExporter.getParameterType(
                    KeyParameterType.CRP,
                    infos.getPopulation().getType(),
                    infos.getPopulation().getLabel()
            );
            connectedRealmDao.setPopulation(populationParam);
        } else {
            connectedRealmDao.getPopulation().setName(infos.getPopulation().getLabel());
        }

        // ajout des informations sur le statut du CR
        if (infos.getStatus() == null) {
            connectedRealmDao.setStatus(null);
        } else if (
                connectedRealmDao.getStatus() == null ||
                        !StringUtils.equals(connectedRealmDao.getStatus().getType(), infos.getStatus().getType())
        ) {
            ParameterTypeDao statusParam = this.typeServiceExporter.getParameterType(
                    KeyParameterType.CRS,
                    infos.getStatus().getType(),
                    infos.getStatus().getLabel()
            );
            connectedRealmDao.setStatus(statusParam);
        } else {
            connectedRealmDao.getStatus().setName(infos.getStatus().getLabel());
        }

        // ajout des infos sur le CR.
        connectedRealmDao.setQueue(infos.isQueue());
        connectedRealmDao.setRegion(regionDao);
        connectedRealmDao.setActive(true);
        connectedRealmDao.setDateLastUpdate(Instant.now());
        connectedRealmDao = this.connectedRealmServiceExporter.saveConnectedRealmDao(connectedRealmDao);

        if (infos.getRealms() == null) {
            return connectedRealmDao;
        }

        // création / modification des realms
        for (RealmApi realm : infos.getRealms()) {
            this.createOrUpdateRealm(realm.getId(), connectedRealmDao, regionDao, realm);
        }
        return connectedRealmDao;
    }

}
