package fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParameterTypeDaoRepository extends JpaRepository<ParameterTypeDao, Integer> {
    ParameterTypeDao findByKeyAndType(KeyParameterType key, String type);
}
