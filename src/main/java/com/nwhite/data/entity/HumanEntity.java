package com.nwhite.data.entity;

import com.nwhite.pojo.Nationality;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public abstract class HumanEntity {
    @Id
    private int id;
    private String name;
    private LocalDate birthday;
    private Nationality nationality;

    public HumanEntity(){}

    public HumanEntity(int id,
                       String name,
                       LocalDate birthday,
                       Nationality nationality) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.nationality = nationality;
    }

    // Геттеры и сеттеры

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    // Дополнительные поля и методы, если необходимо
}
