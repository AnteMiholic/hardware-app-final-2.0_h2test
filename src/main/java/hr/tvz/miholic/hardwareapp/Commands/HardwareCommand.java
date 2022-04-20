package hr.tvz.miholic.hardwareapp.Commands;

import hr.tvz.miholic.hardwareapp.Enums.HardwareTypeEnum;
import hr.tvz.miholic.hardwareapp.Enums.HardwareTypeSubset;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

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
    private int amount;

    public HardwareCommand() {
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

    public int getAmount() {
        return amount;
    }
}
