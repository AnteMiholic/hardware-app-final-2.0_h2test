package hr.tvz.miholic.hardwareapp.Enums;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = HardwareTypeSubSetValidator.class)
public @interface HardwareTypeSubset {
    HardwareTypeEnum[] anyOf();
    String message() default "Type must be any of {anyOf}! Fix combo box front end monkey!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
