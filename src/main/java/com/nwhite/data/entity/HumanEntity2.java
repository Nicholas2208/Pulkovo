package com.nwhite.data.entity;

import java.time.LocalDate;
import javax.persistence.*;

//@Entity
public class HumanEntity2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate birthday;
    private String nationality;
    private String qualification;
    private String education;

    public HumanEntity2() {
    }

    public HumanEntity2(String name, LocalDate birthday, String nationality, String qualification, String education) {
        this.name = name;
        this.birthday = birthday;
        this.nationality = nationality;
        this.qualification = qualification;
        this.education = education;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
}
