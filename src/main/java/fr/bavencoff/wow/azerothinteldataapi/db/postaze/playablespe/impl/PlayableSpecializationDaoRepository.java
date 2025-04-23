package fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablespe.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablespe.dao.PlayableSpecializationDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayableSpecializationDaoRepository extends JpaRepository<PlayableSpecializationDao, Integer> {
}
