package org.proyect.Model.Domain;

import java.util.Objects;

public class Band {
    private String id;
    private String name;
    private String components;

    public Band(String id, String name, String components) {
        this.id = id;
        this.name = name;
        this.components = components;
    }

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

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Band band = (Band) o;
        return Objects.equals(id, band.id) && Objects.equals(name, band.name) && Objects.equals(components, band.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, components);
    }

    @Override
    public String toString() {
        return "Band{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", components='" + components + '\'' +
                '}';
    }
}