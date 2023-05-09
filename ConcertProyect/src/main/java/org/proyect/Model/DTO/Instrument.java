package org.proyect.Model.DTO;

import java.util.Objects;

public class Instrument {
    private int id;
    private String name;
    private String sound;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Instrument(int id, String name, String sound, double price) {
        this.id = id;
        this.name = name;
        this.sound = sound;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return id == that.id && Double.compare(that.price, price) == 0 && Objects.equals(name, that.name) && Objects.equals(sound, that.sound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sound, price);
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sound='" + sound + '\'' +
                ", price=" + price +
                '}';
    }
}
