import DAO.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Demonstrating USER interaction");
        UserDAO userDAO = new UserDAOImpl();
        List<User> users = userDAO.getAll();
        for (User user: users) {
            System.out.println(user);
        }

        System.out.println("Demonstrating ANIMAL interaction");
        AnimalDAO animalDAO = new AnimalDAOImpl();
        List<Animal> animals = animalDAO.getAll();
        for (Animal animal: animals) {
            System.out.println(animal);
        }

        System.out.println("Demonstrating BOOKEDWALKS interaction");
        BookedWalkDAO bookedWalkDAO = new BookedWalkDAOImpl() {
        };
        List<BookedWalk> bookedWalks = bookedWalkDAO.getAll();
        for (BookedWalk bookedWalk: bookedWalks) {
            System.out.println(bookedWalk);
        }

//        System.out.println("Inserting new user and animal");
//        userDAO.insert(new User(10, "David", "daviddavid@gmail.com", LocalDate.parse("1999-01-23")));
//        animalDAO.insert(new Animal(11, "Ricky", 6, LocalDate.parse("2017-09-23"), "Dog", "Husky"));
//
//        System.out.println("Deleting booked walk");
//        bookedWalkDAO.delete(bookedWalks.get(1));

    }
}
