package test.swagger2.validation;

import org.springframework.beans.factory.annotation.Autowired;
import test.swagger2.dto.DictionaryItem;
import test.swagger2.service.TestService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.util.StringUtils.hasText;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, DictionaryItem> {

    private List<String> acceptedValues;
    private String errorMessage;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.value().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
        errorMessage = "value must be one of [" + String.join(", ", acceptedValues) + "]";
    }

    @Override
    public boolean isValid(DictionaryItem enumVal, ConstraintValidatorContext context) {
        if (enumVal == null || !hasText(enumVal.getValue()) ) {
            return true;
        }

        var isValid = acceptedValues.contains(enumVal.getValue());
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();
        }

        return isValid;

    }
}

