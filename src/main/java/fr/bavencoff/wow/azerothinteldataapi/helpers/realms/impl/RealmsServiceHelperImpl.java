package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.impl.ConnectedRealmDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.impl.RealmDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl.RegionDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl.ParametersServiceHelper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.mappers.RealmBoMapper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.ConnectedRealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.UpdateCrModel;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.UpdateRealmModel;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.exceptions.ConnectedRealmAlreadyExistsResponseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RealmsServiceHelperImpl implements RealmServiceHelper {

    private final RealmDaoServiceExporter realmDaoServiceExporter;
    private final RegionDaoServiceExporter regionsServiceExporter;
    private final ParametersServiceHelper typeServiceHelper;
    private final ConnectedRealmDaoServiceExporter connectedRealmDaoServiceExporter;
    private final RealmBoMapper realmMapper;

    @Autowired
    public RealmsServiceHelperImpl(
            final RealmDaoServiceExporter realmDaoServiceExporter,
            final RegionDaoServiceExporter regionsServiceExporter,
            final ParametersServiceHelper typeServiceHelper,
            final ConnectedRealmDaoServiceExporter connectedRealmDaoServiceExporter,
            final RealmBoMapper realmMapper
    ) {
        this.realmDaoServiceExporter = realmDaoServiceExporter;
        this.regionsServiceExporter = regionsServiceExporter;
        this.typeServiceHelper = typeServiceHelper;
        this.connectedRealmDaoServiceExporter = connectedRealmDaoServiceExporter;
        this.realmMapper = realmMapper;
    }


    @Override
    public ConnectedRealmBo getConnectedRealm(Integer id) {
        return realmMapper.daoToApi(connectedRealmDaoServiceExporter.findById(id));
    }

    @Override
    public List<ConnectedRealmBo> getConnectedRealms() {
        final List<ConnectedRealmDao> all = connectedRealmDaoServiceExporter.findAll();
        return realmMapper.crDaosToApis(all);
    }

    @Override
    public List<ConnectedRealmBo> findConnectedRealmsByRegion(GlobalRegion region) {
        return connectedRealmDaoServiceExporter.findAllByRegion_Tag(region)
                .stream()
                .map(realmMapper::daoToApi)
                .toList();
    }

    @Override
    public List<ConnectedRealmBo> findConnectedRealmsByRegions(Set<GlobalRegion> regions) {
        return connectedRealmDaoServiceExporter.findAllByRegions(regions).stream()
                .map(realmMapper::daoToApi)
                .toList();
    }

    @Override
    public ConnectedRealmBo createNewConnectedRealm(
            Integer id,
            GlobalRegion tag,
            UpdateCrModel infos
    ) {
        RegionDao region = this.regionsServiceExporter.findByTag(tag);
        return this.realmMapper.daoToApi(this.createNewConnectedRealm(id, region, infos));
    }

    @Override
    public ConnectedRealmBo createOrUpdateConnectedRealm(
            Integer id,
            GlobalRegion tag,
            UpdateCrModel infos
    ) {
        RegionDao regionDao = this.regionsServiceExporter.findByTag(tag);
        return this.realmMapper.daoToApi(this.createOrUpdateConnectedRealm(id, regionDao, infos));
    }

    @Override
    public RealmBo getRealm(Integer idRealm) {
        return realmMapper.daoToApi(realmDaoServiceExporter.findById(idRealm));
    }

    @Override
    public List<RealmBo> getRealms() {
        return realmMapper.daosToApis(realmDaoServiceExporter.findAll());
    }


    /**
     * Enregistre ou modifie un CR
     *
     * @param id     id du CR
     * @param region region
     * @param infos  information
     * @return CR modifié / créé
     */
    private ConnectedRealmDao createOrUpdateConnectedRealm(
            Integer id,
            RegionDao region,
            UpdateCrModel infos
    ) {
        Optional<ConnectedRealmDao> optionalById = this.connectedRealmDaoServiceExporter.findOptionalById(id);
        if (optionalById.isEmpty()) {
            ConnectedRealmDao connectedRealmDao = new ConnectedRealmDao();
            connectedRealmDao.setId(id);
            return this.saveConnectedRealm(connectedRealmDao, region, infos);
        }
        return this.updateConnectedRealm(optionalById.get(), region, infos);
    }

    /**
     * Créé un CR. Retourne une exception s'il existe deja un CR avec la même ID
     *
     * @param id     ID du CR
     * @param region region
     * @param infos  information
     * @return CR
     */
    private ConnectedRealmDao createNewConnectedRealm(
            Integer id,
            RegionDao region,
            UpdateCrModel infos
    ) {

        Optional<ConnectedRealmDao> optionalById = this.connectedRealmDaoServiceExporter.findOptionalById(id);
        if (optionalById.isPresent()) {
            throw new ConnectedRealmAlreadyExistsResponseException(id);
        }

        ConnectedRealmDao connectedRealmDao = new ConnectedRealmDao();
        connectedRealmDao.setId(id);
        return this.saveConnectedRealm(connectedRealmDao, region, infos);
    }

    /**
     * Modifie un CR
     *
     * @param connectedRealmDao entité du CR
     * @param region            region
     * @param infos             information
     * @return entité màj
     */
    private ConnectedRealmDao updateConnectedRealm(
            ConnectedRealmDao connectedRealmDao,
            RegionDao region,
            UpdateCrModel infos
    ) {
        return this.saveConnectedRealm(connectedRealmDao, region, infos);
    }

    /**
     * Enregistre les infos sur un CR
     *
     * @param connectedRealmDao entité du CR
     * @param regionDao         region
     * @param infos             infos à mettre à jour
     * @return entité màj
     */
    private ConnectedRealmDao saveConnectedRealm(
            ConnectedRealmDao connectedRealmDao,
            RegionDao regionDao,
            UpdateCrModel infos
    ) {

        // ajout des informations sur la population du CR
        if (infos.getPopulation() == null) {
            connectedRealmDao.setPopulation(null);
        } else if (
                connectedRealmDao.getPopulation() == null ||
                        !StringUtils.equals(connectedRealmDao.getPopulation().getType(), infos.getPopulation().getType())
        ) {
            ParameterTypeDao populationParam = this.typeServiceHelper.getParameterType(
                    KeyParameterType.CRP,
                    infos.getPopulation().getType(),
                    infos.getPopulation().getName()
            );
            connectedRealmDao.setPopulation(populationParam);
        } else {
            connectedRealmDao.getPopulation().setName(infos.getPopulation().getName());
        }

        // ajout des informations sur le statut du CR
        if (infos.getStatus() == null) {
            connectedRealmDao.setStatus(null);
        } else if (
                connectedRealmDao.getStatus() == null ||
                        !StringUtils.equals(connectedRealmDao.getStatus().getType(), infos.getStatus().getType())
        ) {
            ParameterTypeDao statusParam = this.typeServiceHelper.getParameterType(
                    KeyParameterType.CRS,
                    infos.getStatus().getType(),
                    infos.getStatus().getName()
            );
            connectedRealmDao.setStatus(statusParam);
        } else {
            connectedRealmDao.getStatus().setName(infos.getStatus().getName());
        }

        // ajout des infos sur le CR.
        connectedRealmDao.setQueue(infos.isQueue());
        connectedRealmDao.setRegion(regionDao);
        connectedRealmDao.setActive(true);
        connectedRealmDao.setDateLastUpdate(Instant.now());
        connectedRealmDao = this.connectedRealmDaoServiceExporter.saveConnectedRealmDao(connectedRealmDao);

        if (infos.getRealms() == null) {
            return connectedRealmDao;
        }

        // création / modification des realms
        for (UpdateCrModel.RealmInfoModel realm : infos.getRealms()) {
            this.createOrUpdateRealm(realm.getId(), connectedRealmDao, regionDao, realm.getInfos());
        }
        return connectedRealmDao;
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
            UpdateRealmModel info
    ) {
        Optional<RealmDao> optionalRealm = this.realmDaoServiceExporter.findOptionalById(id);
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

    /**
     * Enregistre les informations un realm
     *
     * @param realmDao       le realm
     * @param connectedRealm le CR parent
     * @param region         la region
     * @param info           les informations à mettre à jour
     * @return
     */
    private RealmDao saveRealm(
            RealmDao realmDao,
            ConnectedRealmDao connectedRealm,
            RegionDao region,
            UpdateRealmModel info
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
            ParameterTypeDao parameterTypeDao = this.typeServiceHelper.getParameterType(
                    KeyParameterType.REA,
                    info.getType().getType(),
                    info.getType().getName()
            );
            realmDao.setType(parameterTypeDao);
        } else {
            realmDao.getType().setName(info.getType().getName());
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
        return this.realmDaoServiceExporter.save(realmDao);
    }

}
