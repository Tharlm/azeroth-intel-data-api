package fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model;

import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl.ParametersHelper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParameterBo {
    @NotBlank
    @Size(max = ParametersHelper.PARAMETER_TYPE_MAX_LENGTH)
    private String type;
    @Size(max = ParametersHelper.PARAMETER_NAME_MAX_LENGTH)
    private String name;
}
