package org.proyect.Model.Domain;

import java.util.Objects;

public class Instrument {
    private String id;
    private String name;
    private String sound;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Instrument(String id, String name, String sound, String price) {
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
        return id == that.id && CharSequence.compare(that.price, price) == 0 && Objects.equals(name, that.name) && Objects.equals(sound, that.sound);
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
