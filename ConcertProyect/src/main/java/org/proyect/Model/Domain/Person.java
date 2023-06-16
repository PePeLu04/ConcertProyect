package org.proyect.Model.Domain;

import java.util.Objects;

public class Person {
    private String id;
    private String mail;

    private String dni;

    public Person(String id, String mail, String dni) {
        this.id = id;
        this.mail = mail;
        this.dni = dni;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(mail, person.mail) && Objects.equals(dni, person.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, dni);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", mail='" + mail + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
