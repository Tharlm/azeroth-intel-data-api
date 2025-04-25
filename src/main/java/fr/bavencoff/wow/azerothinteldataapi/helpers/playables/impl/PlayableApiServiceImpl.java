package fr.bavencoff.wow.azerothinteldataapi.helpers.playables.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.playableraces.PlayableRaceAlreadyExistsException;
import fr.bavencoff.wow.azerothinteldataapi.common.utils.NumberUtils;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playableclass.dao.PlayableClassDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playableclass.impl.PlayableClassServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablerace.dao.PlayableRaceDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablerace.impl.PlayableRaceServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablespe.dao.PlayableSpecializationDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablespe.impl.PlayableSpecializationDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl.ParametersServiceHelper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableClassApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableRaceApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableSpecializationApi;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlayableApiServiceImpl implements PlayableClassApiService, PlayableSpeApiService, PlayableRaceApiService {

    private final PlayableRaceServiceExporter playableRaceServiceExporter;
    private final PlayableClassServiceExporter playableClassServiceExporter;
    private final ParametersServiceHelper parameterTypeServiceExporter;
    private final PlayableSpecializationDaoServiceExporter playableSpecializationServiceExporter;
    private final PlayableRaceApiMapper raceMapper;

    @Autowired
    public PlayableApiServiceImpl(
            final PlayableRaceServiceExporter playableRaceServiceExporter,
            final PlayableClassServiceExporter playableClassServiceExporter,
            final ParametersServiceHelper parameterTypeServiceExporter,
            final PlayableSpecializationDaoServiceExporter playableSpecializationDaoServiceExporter,
            final PlayableRaceApiMapper raceMapper
    ) {
        this.playableRaceServiceExporter = playableRaceServiceExporter;
        this.playableClassServiceExporter = playableClassServiceExporter;
        this.parameterTypeServiceExporter = parameterTypeServiceExporter;
        this.playableSpecializationServiceExporter = playableSpecializationDaoServiceExporter;
        this.raceMapper = raceMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public PlayableRaceApi createNewPlayableRace(
            Integer id,
            PlayableRaceApi information
    ) {
        Optional<PlayableRaceDao> raceDaoOptional = this.playableRaceServiceExporter.findOptionalById(id);
        if (raceDaoOptional.isPresent()) {
            throw new PlayableRaceAlreadyExistsException(id);
        }
        PlayableRaceDao playableRaceDao = new PlayableRaceDao();
        playableRaceDao.setId(id);
        return this.raceMapper.daoToApi(this.savePlayableRace(playableRaceDao, information));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public PlayableRaceApi createOrUpdatePlayableRace(
            Integer id,
            PlayableRaceApi information
    ) {
        Optional<PlayableRaceDao> raceDaoOptional = this.playableRaceServiceExporter.findOptionalById(id);
        if (raceDaoOptional.isEmpty()) {
            PlayableRaceDao playableRaceDao = new PlayableRaceDao();
            playableRaceDao.setId(id);
            return this.raceMapper.daoToApi(this.savePlayableRace(playableRaceDao, information));
        }
        return this.raceMapper.daoToApi(this.savePlayableRace(raceDaoOptional.get(), information));
    }


    private PlayableRaceDao savePlayableRace(
            PlayableRaceDao playableRaceDao,
            PlayableRaceApi information
    ) {

        playableRaceDao.setName(information.getName());
        playableRaceDao.setAlliedRace(information.isAlliedRace());
        playableRaceDao.setSelectable(information.isSelectable());

        // gestion du type/label faction
        if (information.getFaction() == null) {
            playableRaceDao.setFaction(null);
        } else if (
                playableRaceDao.getFaction() == null ||
                        !StringUtils.equals(playableRaceDao.getFaction().getType(), information.getFaction().getType())
        ) {
            ParameterTypeDao factionParam = this.parameterTypeServiceExporter.getParameterType(
                    KeyParameterType.PRF,
                    information.getFaction().getType(),
                    information.getFaction().getLabel()
            );
            playableRaceDao.setFaction(factionParam);
        } else {
            playableRaceDao.getFaction().setName(information.getFaction().getLabel());
        }

        // pour chaque PC fournies
        information.getClasses().forEach(pcInfos -> {

            // on regarde si elle existe en DB
            final Optional<PlayableClassDao> optionalPlayableClassDao = playableRaceDao.getClasses().stream().filter(pc ->
                    NumberUtils.equals(pcInfos.getId(), pc.getId())
            ).findFirst();

            PlayableClassDao playableClass;

            // si elle, on ne fait rien
            // sis elle existe pas, on check en DB si elle existe.
            if (optionalPlayableClassDao.isEmpty()) {
                final Optional<PlayableClassDao> optionalById = playableClassServiceExporter.findOptionalById(pcInfos.getId());

                // si la PC n'existe toujours pas, on l'insére, sinon on l'attache simplement à la race
                if (optionalById.isEmpty()) {
                    playableClass = new PlayableClassDao();
                    playableClass.setId(pcInfos.getId());
                    playableClass.setName(pcInfos.getName());
                    playableClass.setRaces(Set.of(playableRaceDao));
                    playableRaceDao.getClasses().add(playableClass);
                } else {
                    playableClass = optionalById.get();
                    playableRaceDao.addPlayableClass(playableClass);
                }
            }

        });

        return this.playableRaceServiceExporter.save(playableRaceDao);
    }

    @Override
    @Transactional
    public PlayableClassApi updatePlayableClass(Integer idPc, PlayableClassApi information) {
        Optional<PlayableClassDao> classDaoOptional = this.playableClassServiceExporter.findOptionalById(idPc);
        if (classDaoOptional.isEmpty()) {
            PlayableClassDao playableClassDao = new PlayableClassDao();
            playableClassDao.setId(idPc);
            return this.raceMapper.daoToApi(this.savePlayableClass(playableClassDao, information));
        }
        return this.raceMapper.daoToApi(this.savePlayableClass(classDaoOptional.get(), information));
    }

    private PlayableClassDao savePlayableClass(
            PlayableClassDao playableClass,
            PlayableClassApi information
    ) {
        playableClass.setName(information.getName());


        // pour chaque races fournies
        information.getRaces().forEach(prInfos -> {
            // on regarde si elle existe en DB
            final Optional<PlayableRaceDao> optionalPlayableRaceDao = playableClass.getRaces().stream().filter(pr ->
                    NumberUtils.equals(prInfos.getId(), pr.getId())
            ).findFirst();
            // si elle existe, on ne fait rien
            // si elle n'existe pas, on check en DB si elle existe.
            if (optionalPlayableRaceDao.isEmpty()) {
                final Optional<PlayableRaceDao> optionalById = playableRaceServiceExporter.findOptionalById(prInfos.getId());

                // si la PR n'existe toujours pas, cas foireux, sinon on fait l'association
                if (optionalById.isEmpty()) {
                    // cas foireux, on associe une classe a une race inexistance
                    // TODO a faire, on renvoie une execption pour le moment
                    throw new RuntimeException("Could not find PlayableRaceDao");
                } else {
                    PlayableRaceDao playableRaceDao = optionalById.get();
                    playableRaceDao.addPlayableClass(playableClass);
                }
            }
        });

        // pour chaque PS fournies
        information.getSpes().forEach(psInfos -> {

            // on regarde si elle existe en DB
            final Optional<PlayableSpecializationDao> optionalSpe = playableClass.getSpecializations().stream().filter(ps ->
                    NumberUtils.equals(psInfos.getId(), ps.getId())
            ).findFirst();

            PlayableSpecializationDao playableSpecialization;
            // si elle existe, on ne fait rien
            // si elle n'existe pas, on check en DB si elle existe.
            if (optionalSpe.isEmpty()) {
                final Optional<PlayableSpecializationDao> optionalById = playableSpecializationServiceExporter.findOptionalById(psInfos.getId());

                // si la PS n'existe toujours pas, on l'insére, sinon on l'attache simplement à la race
                if (optionalById.isEmpty()) {
                    playableSpecialization = new PlayableSpecializationDao();
                    playableSpecialization.setId(psInfos.getId());
                    playableSpecialization.setName(psInfos.getName());
//                    playableSpecialization.setClassDao(playableClass);
                } else {
                    playableSpecialization = optionalById.get();
                }
                playableClass.addPlayableSpecialization(playableSpecialization);
            }

        });

        return this.playableClassServiceExporter.save(playableClass);
    }

    @Override
    @Transactional
    public PlayableSpecializationApi updatePlayableSpecializationApi(Integer idPs, PlayableSpecializationApi api) {
        Optional<PlayableSpecializationDao> specializationDaoOptional = this.playableSpecializationServiceExporter.findOptionalById(idPs);
        if (specializationDaoOptional.isEmpty()) {
            PlayableSpecializationDao playableSpecializationDao = new PlayableSpecializationDao();
            playableSpecializationDao.setId(idPs);
            return this.raceMapper.daoToApi(this.savePlayableSpecializationDao(playableSpecializationDao, api));
        }
        return this.raceMapper.daoToApi(this.savePlayableSpecializationDao(specializationDaoOptional.get(), api));
    }

    private PlayableSpecializationDao savePlayableSpecializationDao(
            PlayableSpecializationDao dao,
            PlayableSpecializationApi api
    ) {
        dao.setName(api.getName());

        // gestion du type/label role
        if (api.getRole() == null) {
            dao.setRole(null);
        } else if (
                dao.getRole() == null ||
                        !StringUtils.equals(dao.getRole().getType(), api.getRole().getType())
        ) {
            ParameterTypeDao roleParam = this.parameterTypeServiceExporter.getParameterType(
                    KeyParameterType.PSR,
                    api.getRole().getType(),
                    api.getRole().getName()
            );
            dao.setRole(roleParam);
        } else {
            dao.getRole().setName(api.getRole().getName());
        }
        // gestion du type/label primary stat type
        if (api.getPrimaryStatType() == null) {
            dao.setPrimaryStat(null);
        } else if (
                dao.getPrimaryStat() == null ||
                        !StringUtils.equals(dao.getPrimaryStat().getType(), api.getPrimaryStatType().getType())
        ) {
            ParameterTypeDao primaryStatParam = this.parameterTypeServiceExporter.getParameterType(
                    KeyParameterType.PSP,
                    api.getRole().getType(),
                    api.getRole().getName()
            );
            dao.setPrimaryStat(primaryStatParam);
        } else {
            dao.getPrimaryStat().setName(api.getPrimaryStatType().getName());
        }
        return this.playableSpecializationServiceExporter.save(dao);
    }
}
