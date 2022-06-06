package hr.tvz.miholic.hardwareapp.Hardware.Enums;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class HardwareTypeSubSetValidator implements
        ConstraintValidator<HardwareTypeSubset, HardwareTypeEnum> {
    private HardwareTypeEnum[] subset;

    @Override
    public void initialize(HardwareTypeSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(HardwareTypeEnum value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}

