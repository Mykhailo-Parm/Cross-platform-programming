package DAO;

import java.time.LocalDate;

public class Cat extends Animal {
    public Cat(int id, String name, int age, LocalDate dateOfBirth, String species, String breed) {
        super(id, name, age, dateOfBirth, species, breed);
    }
}
