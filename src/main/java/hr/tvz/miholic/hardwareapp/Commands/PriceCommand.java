package hr.tvz.miholic.hardwareapp.Commands;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PriceCommand {

    public Double getPrice() {
        return price;
    }

    @NotNull(message = "Price must be entered!")
    @Positive(message = "Price must be positive!")
    private Double price;
}