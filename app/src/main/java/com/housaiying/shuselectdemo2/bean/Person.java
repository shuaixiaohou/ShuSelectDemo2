package com.housaiying.shuselectdemo2.bean;

/**
 * Created by housaiying on 2020/1/8
 */
public class Person {
    private String person_id;
    private String personName;
    private String dep_id;

    public Person(String dep_id, String person_id, String personName) {
        this.person_id = person_id;
        this.personName = personName;
        this.dep_id = dep_id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDep_id() {
        return dep_id;
    }

    public void setDep_id(String dep_id) {
        this.dep_id = dep_id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }
}
