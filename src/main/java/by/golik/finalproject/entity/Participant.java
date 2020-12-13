package by.golik.finalproject.entity;

import java.util.Objects;

/**
 * @author Nikita Golik
 */
public class Participant {
    private int id;
    private String name;
    private String surname;
    private String secondName;

    public Participant() {

    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return id == that.id &&
                name.equals(that.name) &&
                surname.equals(that.surname) &&
                secondName.equals(that.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, secondName);
    }

    @Override
    public String toString() {
        return "Participants{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
