package hr.tvz.miholic.hardwareapp.Hardware.Controllers;

import hr.tvz.miholic.hardwareapp.Hardware.Classes.HardwareDTO;
import hr.tvz.miholic.hardwareapp.Hardware.Commands.HardwareCommand;
import hr.tvz.miholic.hardwareapp.Hardware.Commands.PriceCommand;
import hr.tvz.miholic.hardwareapp.Hardware.Service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
@CrossOrigin(origins = "http://localhost:4200")

public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }


    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<HardwareDTO> getAllHardware(){
        List<HardwareDTO> h = hardwareService.findAll();
        for(HardwareDTO ha : h){
            System.out.println(ha.toString());
        }
        return h;
    }

    @GetMapping(params = "type")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<HardwareDTO> getHardwareByType(@RequestParam final String type){
        return hardwareService.findByType(type);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<HardwareDTO> save(@Valid @RequestBody final HardwareCommand command){
        return hardwareService.save(command)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.CREATED).body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    @Secured({"ROLE_ADMIN"})
    public void delete(@PathVariable String code) {
        hardwareService.delete(code);
    }

    @GetMapping("/{code}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<HardwareDTO> getHardwareByCode(@PathVariable String code) {
        System.out.println(code);
        return hardwareService.getByCode(code)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }
    @GetMapping("/codes/{code}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<HardwareDTO> findByCode(@PathVariable String code){
        return hardwareService.findByCode(code);
    }

    @PutMapping("/{code}")
    @Secured({"ROLE_ADMIN", "ROLE_UPDATER"})
    public ResponseEntity<HardwareDTO> update(@PathVariable String code, @Valid @RequestBody final HardwareCommand updateHardwareCommand){
        return hardwareService.update(code, updateHardwareCommand)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }

    @PatchMapping("{code}")
    @Secured({
            "ROLE_ADMIN"})
    public ResponseEntity<HardwareDTO> patchName(@PathVariable String code, @Valid @RequestBody PriceCommand priceCommand){
        return hardwareService.patchPrice(code, priceCommand)
                .map(
                        hardwareDTO -> ResponseEntity.status(HttpStatus.OK).body(hardwareDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }



}
