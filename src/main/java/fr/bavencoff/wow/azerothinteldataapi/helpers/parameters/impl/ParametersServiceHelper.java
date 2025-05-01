package fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.impl.ParameterTypeServiceExporter;
import org.springframework.stereotype.Service;

@Service
public class ParametersServiceHelper {
    private final ParameterTypeServiceExporter parameterTypeServiceExporter;

    public ParametersServiceHelper(ParameterTypeServiceExporter parameterTypeServiceExporter) {
        this.parameterTypeServiceExporter = parameterTypeServiceExporter;
    }

    public ParameterTypeDao getParameterType(
            KeyParameterType key,
            String type,
            String typeLabel
    ) {

        ParameterTypeDao param = this.parameterTypeServiceExporter.findByKeyAndType(
                key,
                type
        );
        if (param == null) {
            param = new ParameterTypeDao();
            param.setName(typeLabel);
            param.setKey(key);
            param.setType(type);
            return this.parameterTypeServiceExporter.save(param);
        }
        return param;
    }

}
