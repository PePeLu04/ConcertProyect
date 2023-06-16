package org.proyect.Model.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Band{
    private String band_id;
    private String name;
    private String components;
    private List<Instrument> instruments;

    public Band(String band_id, String name, String components) {
        this.band_id = band_id;
        this.name = name;
        this.components = components;
        this.instruments = new ArrayList<>();
    }

    public String getBand_id() {
        return band_id;
    }

    public void setBand_id(String band_id) {
        this.band_id = band_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Band band = (Band) o;
        return Objects.equals(band_id, band.band_id) && Objects.equals(name, band.name) && Objects.equals(components, band.components);
    }
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    public void removeInstrument(String instrumentName) {
        Instrument instrumentToRemove = null;

        for (Instrument instrument : instruments) {
            if (instrument.getName().equals(instrumentName)) {
                instrumentToRemove = instrument;
                break;
            }
        }

        if (instrumentToRemove != null) {
            instruments.remove(instrumentToRemove);
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(band_id, name, components);
    }

    @Override
    public String toString() {
        return "Band{" +
                "band_id='" + band_id + '\'' +
                ", name='" + name + '\'' +
                ", components='" + components + '\'' +
                '}';
    }

}