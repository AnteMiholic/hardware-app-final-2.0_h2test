package hr.tvz.miholic.hardwareapp.Hardware.Classes;

import hr.tvz.miholic.hardwareapp.Hardware.Enums.HardwareTypeEnum;
import hr.tvz.miholic.hardwareapp.Review.Classes.Review;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    private String code;
    private Double price;
    private HardwareTypeEnum type;

    @ManyToMany(targetEntity = Review.class)
    @JoinTable(
            name = "hardware_review",
            joinColumns = { @JoinColumn(name = "hardware_id") },
            inverseJoinColumns = { @JoinColumn(name = "review_id") }
    )
    private List<Review> reviews;

    public Hardware(Long id, String name, String code, Double price, HardwareTypeEnum type, int amount) {

        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.amount = amount;
    }

    public Hardware(String name, String code, Double price, HardwareTypeEnum type, int amount) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.amount = amount;
    }

    private int amount;



    public Hardware() {

    }
    public String dostupnost(){
        return "Ime: " + this.name + " - dostupno: " + this.amount;
    }
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
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return amount == hardware.amount && Objects.equals(id, hardware.id) && Objects.equals(name, hardware.name) && Objects.equals(code, hardware.code) && Objects.equals(price, hardware.price) && type == hardware.type && Objects.equals(reviews, hardware.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, price, type, reviews, amount);
    }
}
