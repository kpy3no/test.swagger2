package test.swagger2.validation;

import org.springframework.beans.factory.annotation.Autowired;
import test.swagger2.dto.TestInput;
import test.swagger2.service.TestService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValueTestInputValidator implements ConstraintValidator<ValidTestInput, TestInput> {

    private List<String> acceptedValues;
    private String errorMessage;

//    @Autowired
//    private TestService testService;

    @Override
    public void initialize(ValidTestInput annotation) {
        errorMessage = "more than one";
    }

    @Override
    public boolean isValid(TestInput testInput, ConstraintValidatorContext context) {
        boolean isValid = testInput.getId() > 0;
        if (isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation();
        }

        return isValid;
    }
}

