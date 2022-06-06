package hr.tvz.miholic.hardwareapp.Hardware.Repository;

import hr.tvz.miholic.hardwareapp.Hardware.Classes.Hardware;
import hr.tvz.miholic.hardwareapp.Hardware.Enums.HardwareTypeEnum;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;


@Repository
public class MockHardwareRepository implements HardwareRepository {

    private final List<Hardware> MOCKED_HARDWARE = new ArrayList<>(
            Arrays.asList(
                    new Hardware("i5 9500", "123456789", 2000.55, HardwareTypeEnum.CPU, 5),
                    new Hardware("Nvidia GeForce GTX 1650 OC", "123123123231321", 10000.99, HardwareTypeEnum.GPU, 0),
                    new Hardware("Nvidia GeForce RTX 3090", "3222222", 100000, HardwareTypeEnum.GPU, 0),
                    new Hardware("Genious MK-500", "746453", 20, HardwareTypeEnum.OTHER, 250),
                    new Hardware("Genious MK-500 Mechanical", "74643253", 100.05, HardwareTypeEnum.OTHER, 20)

            )
    );

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        String code = hardware.getCode();
        System.out.println(hardware);
        if(!MOCKED_HARDWARE.stream().anyMatch(e -> e.getCode().equals(code))){
            MOCKED_HARDWARE.add(hardware);
            return Optional.of(hardware);
        }
        else{
            return Optional.empty();
        }
    }

    @Override
    public List<Hardware> findAll() {
        return MOCKED_HARDWARE;
    }

    @Override
    public List<Hardware> findByCode(String code) {
        return null;
    }

    @Override
    public List<Hardware> findByType(String type) {
        return MOCKED_HARDWARE.stream().filter(it -> Objects.equals(it.getType(), HardwareTypeEnum.valueOf(type))).collect(Collectors.toList());
    }

    @Override
    public void delete(String code) {

        if(MOCKED_HARDWARE.stream().anyMatch(e -> e.getCode().equals(code))){

            Optional<Hardware> h = MOCKED_HARDWARE.stream().filter(it -> Objects.equals(it.getCode(), code)).findAny();
            MOCKED_HARDWARE.removeIf(it -> Objects.equals(it.getCode(), code));

        }


    }

    @Override
    public Optional<Hardware> getByCode(String code) {
        if(MOCKED_HARDWARE.stream().anyMatch(e -> e.getCode().equals(code))){
            return MOCKED_HARDWARE.stream().filter(it -> Objects.equals(it.getCode(), code)).findAny();
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<Hardware> update(String code, Hardware hardware) {

        if(MOCKED_HARDWARE.stream().anyMatch(e -> e.getCode().equals(code))){
            MOCKED_HARDWARE.removeIf(e -> e.getCode().equals(code));
            MOCKED_HARDWARE.add(hardware);
            return Optional.of(hardware);
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<Hardware> patchPrice(String code, Double price) {
        if(MOCKED_HARDWARE.stream().anyMatch(e -> e.getCode().equals(code))){
            MOCKED_HARDWARE.forEach(h -> {
                if (h.getCode().equals(code)) {
                    h.setPrice(price);
                }
            });
            return MOCKED_HARDWARE.stream().filter(it -> Objects.equals(it.getCode(), code)).findAny();
        }
        else
            return Optional.empty();
    }

}
