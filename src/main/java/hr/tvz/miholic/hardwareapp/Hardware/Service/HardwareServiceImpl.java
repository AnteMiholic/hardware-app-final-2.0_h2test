package hr.tvz.miholic.hardwareapp.Hardware.Service;

import hr.tvz.miholic.hardwareapp.Hardware.Classes.Hardware;
import hr.tvz.miholic.hardwareapp.Hardware.Classes.HardwareDTO;
import hr.tvz.miholic.hardwareapp.Hardware.Commands.HardwareCommand;
import hr.tvz.miholic.hardwareapp.Hardware.Commands.PriceCommand;
import hr.tvz.miholic.hardwareapp.Hardware.Repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;


    public HardwareServiceImpl(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }



    @Override
    public List<HardwareDTO> findByType(String type) {
        return hardwareRepository.findByType(type).stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand command) {
        return hardwareRepository.save(mapCommandToHardware(command)).map(this::mapHardwareToDTO);
    }

    @Override
    public void delete(String code) {
        hardwareRepository.delete(code);
    }

    @Override
    public Optional<HardwareDTO> getByCode(String code) {
        return hardwareRepository.getByCode(code).map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> update(String code, HardwareCommand updateHardwareCommand) {
        return hardwareRepository.update(code, mapCommandToHardware(updateHardwareCommand)).map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> patchPrice(String code, PriceCommand priceCommand) {
        return hardwareRepository.patchPrice(code, mapCommandToPrice(priceCommand)).map(this::mapHardwareToDTO);
    }

    @Override
    public List<HardwareDTO> findByCode(String code) {
        return hardwareRepository.findByCode(code).stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    private Double mapCommandToPrice(PriceCommand priceCommand) {
        return priceCommand.getPrice();
    }

    public HardwareDTO mapHardwareToDTO(final Hardware hardware){

      return new HardwareDTO(hardware.getName(), hardware.getPrice(), hardware.getCode());


    }
    public Hardware mapCommandToHardware(HardwareCommand hardwareCommand){

        return new Hardware(hardwareCommand.getName(), hardwareCommand.getCode(), hardwareCommand.getPrice(), hardwareCommand.getType(), hardwareCommand.getStock());
    }
}
