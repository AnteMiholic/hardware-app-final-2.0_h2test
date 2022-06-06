package hr.tvz.miholic.hardwareapp.Hardware.Commands;

import hr.tvz.miholic.hardwareapp.Hardware.Enums.HardwareTypeEnum;
import hr.tvz.miholic.hardwareapp.Hardware.Enums.HardwareTypeSubset;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class HardwareCommand {


    @NotBlank(message = "Product name must not be empty!")
    private String name;

    /*
    Ukoliko postoji pattern svih šifri proizvoda dodati slično sljedećem
    @Pattern(message = "Product code must have 10 digits", regexp="[\\d]{10}")
     */
    @NotBlank(message = "Product code must not be empty!")
    private String code;

    @NotNull(message = "Price must be entered!")
    @Positive(message = "Price must be positive!")
    private Double price;
    /*
    Trebao bi biti enum, moguće riješiti preko dokumentacije na linku: https://www.baeldung.com/javax-validations-enums
     */
    @NotNull
    @HardwareTypeSubset(anyOf = {HardwareTypeEnum.CPU, HardwareTypeEnum.GPU, HardwareTypeEnum.MBO, HardwareTypeEnum.RAM,
            HardwareTypeEnum.STORAGE, HardwareTypeEnum.HOUSING, HardwareTypeEnum.OTHER})
    private HardwareTypeEnum type;

    @NotNull(message = "Amount must be entered!")
    @PositiveOrZero(message = "Amount must be greater than 0!")
    private int stock;

    public HardwareCommand() {
    }

    public HardwareCommand(String test_name, String test_code, Double test_price, HardwareTypeEnum test_type, int test_amount) {
        this.name = test_name;
        this.code = test_code;
        this.price = test_price;
        this.type = test_type;
        this.stock = test_amount;

    }


    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Double getPrice() {
        return price;
    }

    public HardwareTypeEnum getType() {
        return type;
    }

    public int getStock() {
        return stock;
    }
}
