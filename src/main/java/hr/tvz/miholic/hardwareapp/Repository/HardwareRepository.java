package hr.tvz.miholic.hardwareapp.Repository;

import hr.tvz.miholic.hardwareapp.Classes.Hardware;

import java.util.List;
import java.util.Optional;

public interface HardwareRepository {

    Optional<Hardware> save(Hardware hardware);


    List<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    List<Hardware> findByType(String type);

    Optional<Hardware> delete(String code);

    Optional<Hardware> getByCode(String code);

    Optional<Hardware> update(String code, Hardware hardware);

    Optional<Hardware> patchPrice(String code, Double price);
}
