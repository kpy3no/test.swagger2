package test.swagger2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.swagger2.model.EntityType;
import test.swagger2.validation.ValidTestInput;
import test.swagger2.validation.ValueOfEnum;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidTestInput
public class TestInput {
    @NotNull
    @Max(5)
    private Long id;

    private Long otherId;

    @NotNull
    @ValueOfEnum(EntityType.class)
    private DictionaryItem entityType;

    @NotNull
    private Boolean active;

    @NotBlank
    private String temp;
}
