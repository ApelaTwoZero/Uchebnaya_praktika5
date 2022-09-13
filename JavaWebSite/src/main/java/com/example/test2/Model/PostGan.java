package com.example.test2.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PostGan {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Заполните поле")
    @Size(min = 2, max = 20, message = "Размер: 2-50")
    public String name;
    @NotNull
    @Min(value = 0, message = "Минимальное: 1")
    public int boolets, gans_range, disassembly;
    @NotNull
    @Min(value = 0, message = "Минимальное: 1")
    public double speed;



    public PostGan(String name, int boolets, double speed, int gans_range, int disassembly) {
        this.name = name;
        this.boolets = boolets;
        this.speed = speed;
        this.gans_range = gans_range;
        this.disassembly = disassembly;
    }

    public PostGan() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBoolets() {
        return boolets;
    }

    public void setBoolets(int boolets) {
        this.boolets = boolets;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getGans_range() {
        return gans_range;
    }

    public void setGans_range(int gans_range) {
        this.gans_range = gans_range;
    }

    public int getDisassembly() {
        return disassembly;
    }

    public void setDisassembly(int disassembly) {
        this.disassembly = disassembly;
    }


}
