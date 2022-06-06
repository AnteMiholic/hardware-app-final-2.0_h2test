package hr.tvz.miholic.hardwareapp.Hardware.Service;

import hr.tvz.miholic.hardwareapp.Hardware.Classes.HardwareDTO;
import hr.tvz.miholic.hardwareapp.Hardware.Commands.HardwareCommand;
import hr.tvz.miholic.hardwareapp.Hardware.Commands.PriceCommand;

import java.util.List;
import java.util.Optional;

public interface HardwareService {
    List<HardwareDTO> findAll();

    List<HardwareDTO> findByType(String type);

    Optional<HardwareDTO> save(HardwareCommand command);

    void delete(String code);

    Optional<HardwareDTO> getByCode(String code);

    Optional<HardwareDTO> update(String code, HardwareCommand updateHardwareCommand);

    Optional<HardwareDTO> patchPrice(String code, PriceCommand priceCommand);

    List<HardwareDTO> findByCode(String code);
}
