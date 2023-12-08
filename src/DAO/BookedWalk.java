package DAO;

import java.time.LocalDate;

public class BookedWalk {
    private int idUser;
    private int idAnimal;
    private LocalDate dateOfWalk;
    private int duration;
    public BookedWalk(int idUser, int idAnimal, LocalDate dateOfWalk, int duration) {
        this.idUser = idUser;
        this.idAnimal = idAnimal;
        this.dateOfWalk = dateOfWalk;
        this.duration = duration;
    }
    public BookedWalk() {}

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdAnimal() {
        return idAnimal;
    }
    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }
    public LocalDate getDateOfWalk() {
        return dateOfWalk;
    }
    public void setDateOfWalk(LocalDate dateOfWalk) {
        this.dateOfWalk = dateOfWalk;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "BookedWalk{" +
                "idUser=" + idUser +
                ", idAnimal=" + idAnimal +
                ", dateOfWalk=" + dateOfWalk +
                ", duration=" + duration +
                '}';
    }
}
