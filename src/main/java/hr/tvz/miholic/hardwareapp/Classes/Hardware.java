package hr.tvz.miholic.hardwareapp.Classes;

import hr.tvz.miholic.hardwareapp.Enums.HardwareTypeEnum;

public class Hardware {
    private String name;
    private String code;
    private Double price;
    private HardwareTypeEnum type;
    private int amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public HardwareTypeEnum getType() {
        return type;
    }

    public void setType(HardwareTypeEnum type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Hardware(String name, String code, double price, HardwareTypeEnum type, int amount) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
