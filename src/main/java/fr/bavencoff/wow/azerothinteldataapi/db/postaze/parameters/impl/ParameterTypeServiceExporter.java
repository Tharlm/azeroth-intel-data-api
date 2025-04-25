package fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ParameterTypeServiceExporter {

    private final ParameterTypeDaoRepository repository;

    @Autowired
    public ParameterTypeServiceExporter(
            final ParameterTypeDaoRepository repository
    ) {
        this.repository = repository;
    }

    @Cacheable(cacheNames = "findByKeyAndType")
    public ParameterTypeDao findByKeyAndType(
            KeyParameterType key,
            String type
    ) {
        return this.repository.findByKeyAndType(key, type);
    }

}
