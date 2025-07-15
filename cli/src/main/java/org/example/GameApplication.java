//Автор
//Зайцева А.А.
//9 группа
//3 курс
//Практика
//Задача
//Геймер Иннокентий ведет учет своих успехов, записывая в файл информацию в следующем виде:
//1. Название игры.
//2. Жанр.
//3. Дата прохождения.
//4. Время, потраченное на прохождение игры.
//5. Оценка по 5ти бальной шкале.
//Необходимо найти следующую информацию:
//1. Жанр, игры в котором получили самый высокий средний балл.
//2. В какой месяц, Иннокентий потратил больше время времени на игры.
//3. Все игры, которые Иннокентий проходил больше 1 раза.
package org.example;

import java.util.List;

public class GameApplication {
    private static final String PATH = "C:\\Users\\User\\IdeaProjects\\gamer1\\data\\data.txt";

    public static void main(String[] args) {
        List<GameRecord> records = FileHandler.readRecordsFromFile(PATH);

        System.out.println("Total play time: " +
                GameService.getTotalPlayTime(records) + " hours");

        Genre topGenre = GameService.getTopGenreOrNull(records);
        if (topGenre == null) {
            System.out.println("Error null");
        } else {
            System.out.println("Top genre: " + topGenre);
        }


        var mostTimeSpentMonth = GameService.getMonthWithMostHoursOrNull(records);
        if (mostTimeSpentMonth == null) {
            System.out.println("Error null");
        } else {
            System.out.println("Month with most hours spent: " + mostTimeSpentMonth);
        }

        List<String> repeatedGames = GameService.getRepeatedGames(records);
        System.out.println("Repeated games: " + repeatedGames);
    }
}