package fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.KeyParameterType;
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

    public ParameterTypeDao getParameterType(
            KeyParameterType key,
            String type,
            String typeLabel
    ) {

        ParameterTypeDao param = this.findByKeyAndType(
                key,
                type
        );
        if (param == null) {
            param = new ParameterTypeDao();
            param.setName(typeLabel);
            param.setKey(key);
            param.setType(type);
        }
        return param;
    }
}
