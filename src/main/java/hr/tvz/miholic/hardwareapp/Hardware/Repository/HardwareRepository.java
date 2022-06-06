package hr.tvz.miholic.hardwareapp.Hardware.Repository;

import hr.tvz.miholic.hardwareapp.Hardware.Classes.Hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    Optional<Hardware> save(Hardware hardware);


    List<Hardware> findAll();

    List<Hardware> findByCode(String code);

    List<Hardware> findByType(String type);

    void delete(String code);

    Optional<Hardware> getByCode(String code);

    Optional<Hardware> update(String code, Hardware hardware);

    Optional<Hardware> patchPrice(String code, Double price);
}
