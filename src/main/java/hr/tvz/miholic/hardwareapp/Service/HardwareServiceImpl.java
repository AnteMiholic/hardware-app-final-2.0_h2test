package hr.tvz.miholic.hardwareapp.Service;

import hr.tvz.miholic.hardwareapp.Classes.Hardware;
import hr.tvz.miholic.hardwareapp.Classes.HardwareDTO;
import hr.tvz.miholic.hardwareapp.Commands.HardwareCommand;
import hr.tvz.miholic.hardwareapp.Commands.PriceCommand;
import hr.tvz.miholic.hardwareapp.Repository.HardwareRepository;
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
    /*
    @Override
    public HardwareDTO findByCode(String code) {
        return hardwareRepository.findByCode(code).map(this::mapHardwareToDTO).orElse(null);
    }*/

    @Override
    public List<HardwareDTO> findByType(String type) {
        return hardwareRepository.findByType(type).stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand command) {
        return hardwareRepository.save(mapCommandToHardware(command)).map(this::mapHardwareToDTO);
    }

    @Override
    public Optional<HardwareDTO> delete(String code) {
        return hardwareRepository.delete(code).map(this::mapHardwareToDTO);
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

    private Double mapCommandToPrice(PriceCommand priceCommand) {
        return priceCommand.getPrice();
    }

    public HardwareDTO mapHardwareToDTO(final Hardware hardware){

      return new HardwareDTO(hardware.getName(), hardware.getPrice());


    }
    public Hardware mapCommandToHardware(HardwareCommand hardwareCommand){

        return new Hardware(hardwareCommand.getName(), hardwareCommand.getCode(), hardwareCommand.getPrice(), hardwareCommand.getType(), hardwareCommand.getAmount());
    }
}
