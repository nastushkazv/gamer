package org.example;

import java.time.LocalDate;

public class GameRecord {
    private final String title;
    private final Genre genre;
    private final LocalDate completionDate;
    private final int hoursSpent;
    private final int rating;

    public GameRecord(String title, Genre genre, LocalDate completionDate, int hoursSpent, int rating) {
        this.title = title;
        this.genre = genre;
        this.completionDate = completionDate;
        this.hoursSpent = hoursSpent;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public int getHoursSpent() {
        return hoursSpent;
    }

    public int getRating() {
        return rating;
    }
}