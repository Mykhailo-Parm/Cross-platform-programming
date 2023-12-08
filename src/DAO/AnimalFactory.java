package DAO;

public class AnimalFactory {
    Animal createAnimal(Animal animal) {
        if (animal.getBreed() == "Husky") {
            return new Husky(animal.getId(),
                    animal.getName(),
                    animal.getId(),
                    animal.getDateOfBirth(),
                    animal.getSpecies(),
                    animal.getBreed());
        } else if (animal.getBreed() == "Persian") {
            return new Persian(animal.getId(),
                    animal.getName(),
                    animal.getId(),
                    animal.getDateOfBirth(),
                    animal.getSpecies(),
                    animal.getBreed());
        } else {
            return animal;
        }
    }
}
