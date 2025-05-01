package fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ParameterTypeDaoServiceExporter {

    private final ParameterTypeDaoRepository repository;

    @Autowired
    public ParameterTypeDaoServiceExporter(
            final ParameterTypeDaoRepository repository
    ) {
        this.repository = repository;
    }

    @Cacheable(
            cacheNames = "findByKeyAndType",
            key = "#key.name() + '_' + #type",
            unless = "#result == null"
    )
    public ParameterTypeDao findByKeyAndType(
            KeyParameterType key,
            String type
    ) {
        return this.repository.findByKeyAndType(key, type);
    }

    @CacheEvict(
            cacheNames = "findByKeyAndType",
            key = "#parameterTypeDao.key.name() + '_' + #parameterTypeDao.type"
    )
    public ParameterTypeDao save(final ParameterTypeDao parameterTypeDao) {
        return this.repository.save(parameterTypeDao);
    }

}
