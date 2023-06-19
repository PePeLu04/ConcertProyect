package org.proyect.Model.Domain;

public class Person {
    private String id;

    private String dni;

    public Person(String id, String dni) {
        this.id = id;
        this.dni = dni;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

}
