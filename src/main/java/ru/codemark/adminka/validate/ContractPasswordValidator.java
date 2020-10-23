package ru.codemark.adminka.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContractPasswordValidator implements ConstraintValidator<ContractPassword, String> {
    @Override
    public void initialize(ContractPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean hasDigit = s.matches(".*\\d+.*");
        boolean hasUpper = s.matches(".*[A-Z]+.*");
        return hasDigit && hasUpper;
    }
}
