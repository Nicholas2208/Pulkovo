package com.nwhite.data.entity;

import com.nwhite.pojo.Education;
import com.nwhite.pojo.Nationality;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class ChildEntity extends HumanEntity {
    private Nationality nationality;
    private Education education;

    public ChildEntity() {}

    public ChildEntity(int id, String name, LocalDate birthday) {
        super(id, name, birthday, null);
    }

    public ChildEntity(int id, String name,
                       LocalDate birthday, Nationality nationality,
                       Education education) {
        super(id, name, birthday, null);
        this.nationality = nationality;
        this.education = education;
    }

    // Геттеры и сеттеры

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    // Дополнительные поля и методы, если необходимо
}
