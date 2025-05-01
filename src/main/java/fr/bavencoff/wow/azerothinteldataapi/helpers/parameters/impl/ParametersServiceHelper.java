package fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.common.model.GenericTypeName;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.impl.ParameterTypeDaoServiceExporter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ParametersServiceHelper {
    private final ParameterTypeDaoServiceExporter parameterTypeDaoServiceExporter;

    public ParametersServiceHelper(ParameterTypeDaoServiceExporter parameterTypeDaoServiceExporter) {
        this.parameterTypeDaoServiceExporter = parameterTypeDaoServiceExporter;
    }

    public ParameterTypeDao getParameterType(
            final GenericTypeName genericTypeName,
            ParameterTypeDao dao
    ) {
        if (genericTypeName == null) {
            return null;
        } else if (
                dao == null || !StringUtils.equals(dao.getType(), genericTypeName.getType())
        ) {
            return this.getParameterType(
                    KeyParameterType.CRP,
                    genericTypeName.getType(),
                    genericTypeName.getName()
            );
        } else {
            dao.setName(genericTypeName.getName());
            return dao;
        }
    }

    public ParameterTypeDao getParameterType(
            KeyParameterType key,
            String type,
            String typeLabel
    ) {

        ParameterTypeDao param = this.parameterTypeDaoServiceExporter.findByKeyAndType(
                key,
                type
        );
        if (param == null) {
            param = new ParameterTypeDao();
            param.setName(typeLabel);
            param.setKey(key);
            param.setType(type);
            return this.parameterTypeDaoServiceExporter.save(param);
        }
        return param;
    }

}
