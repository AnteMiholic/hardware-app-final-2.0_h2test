package hr.tvz.miholic.hardwareapp.Controllers;

import hr.tvz.miholic.hardwareapp.Classes.Hardware;
import hr.tvz.miholic.hardwareapp.Classes.HardwareDTO;
import hr.tvz.miholic.hardwareapp.Commands.HardwareCommand;
import hr.tvz.miholic.hardwareapp.Commands.PriceCommand;
import hr.tvz.miholic.hardwareapp.Service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<HardwareDTO> getAllHardware(){

        List<HardwareDTO> h = hardwareService.findAll();
        for(HardwareDTO ha : h){
            System.out.println(ha.toString());
        }
        return h;
    }
    /*
    @GetMapping(params = "code")
    public HardwareDTO getHardwareByCode(@RequestParam final String code){
        return hardwareService.findByCode(code);
    }*/
    @GetMapping(params = "type")
    public List<HardwareDTO> getHardwareByType(@RequestParam final String type){
        return hardwareService.findByType(type);
    }

    @PostMapping
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
    public void delete(@PathVariable String code) {
        hardwareService.delete(code);
    }

    @GetMapping("/{code}")
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

    @PutMapping("/{code}")
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
