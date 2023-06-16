package org.proyect.Model.Domain;

import java.util.Objects;

public class Instrument {
    private String instr_id;
    private String name1;
    private String sound;
    private String price;

    private String name_band;

    public String getInstr_id() {
        return instr_id;
    }

    public void setInstr_id(String id) {
        this.instr_id = instr_id;
    }

    public String getName() {
        return name1;
    }

    public void setName(String name) {
        this.name1 = name1;
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

    public String getName_band() {
        return name_band;
    }

    public void setName_band(String name_band) {
        this.name_band = name_band;
    }

    public Instrument(String instr_id, String name1, String sound, String price, String name_band) {
        this.instr_id = instr_id;
        this.name1 = name1;
        this.sound = sound;
        this.price = price;
        this.name_band = name_band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instrument that = (Instrument) o;
        return instr_id == that.instr_id && CharSequence.compare(that.price, price) == 0 && Objects.equals(name1, that.name1) && Objects.equals(sound, that.sound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instr_id, name1, sound, price);
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "id=" + instr_id +
                ", name='" + name1 + '\'' +
                ", sound='" + sound + '\'' +
                ", price=" + price +
                '}';
    }


}
