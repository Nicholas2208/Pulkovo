package com.nwhite.data.entity;

import com.nwhite.pojo.Nationality;
import com.nwhite.pojo.Qualification;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class AdultEntity extends HumanEntity {
    private Nationality nationality;
    private Qualification qualification;

    public AdultEntity() {}

    public AdultEntity(int id, String name, LocalDate birthday) {
        super(id, name, birthday, null);
    }

    public AdultEntity(int id, String name,
                       LocalDate birthday, Nationality nationality,
                       Qualification qualification) {
        super(id, name, birthday, nationality);
        this.nationality = nationality;
        this.qualification = qualification;
    }


    // Геттеры и сеттеры

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    // Дополнительные поля и методы, если необходимо
}
