package hr.tvz.miholic.hardwareapp.Enums;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum HardwareTypeEnum {
    CPU, GPU, MBO, RAM,
    STORAGE, HOUSING, OTHER;
    /*
    public static String printAll() {
        return Stream.of(HardwareTypeEnum.values()).
                map(HardwareTypeEnum::name).
                collect(Collectors.joining(", "));
    }*/
}
