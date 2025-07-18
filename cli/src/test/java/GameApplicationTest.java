import org.example.GameRecord;
import org.example.GameService;
import org.example.Genre;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class  GameApplicationTest {
    @Test
    public void testGetTopGenre() {
        List<GameRecord> records = Arrays.asList(
                new GameRecord("Game1", Genre.RPG, LocalDate.parse("2024-01-15"), 20, 5),
                new GameRecord("Game2", Genre.ADVENTURE, LocalDate.parse("2024-02-10"), 15, 4),
                new GameRecord("Game3", Genre.RPG, LocalDate.parse("2024-03-05"), 30, 5)
        );
        assertEquals(Genre.RPG, GameService.getTopGenreOrNull(records));
    }

    @Test
    public void testGetMonthWithMostHours() {
        List<GameRecord> records = Arrays.asList(
                new GameRecord("Game1", Genre.RPG, LocalDate.parse("2024-01-15"), 20, 5),
                new GameRecord("Game2", Genre.ADVENTURE, LocalDate.parse("2024-01-20"), 25, 4),
                new GameRecord("Game3", Genre.RPG, LocalDate.parse("2024-02-05"), 30, 5)
        );
        assertEquals(java.time.Month.JANUARY, GameService.getMonthWithMostHoursOrNull(records));
    }

    @Test
    public void testGetRepeatedGames() {
        List<GameRecord> records = Arrays.asList(
                new GameRecord("Game1", Genre.RPG, LocalDate.parse("2024-01-15"), 20, 5),
                new GameRecord("Game2", Genre.STRATEGY, LocalDate.parse("2024-02-10"), 15, 4),
                new GameRecord("Game1", Genre.RPG, LocalDate.parse("2024-03-05"), 30, 5)
        );
        assertEquals(List.of("Game1"), GameService.getRepeatedGames(records));
    }

    @Test
    public void testGetTopGenreWithEmptyList() {
        List<GameRecord> records = List.of();
        assertNull(GameService.getTopGenreOrNull(records), "Top genre should be null when the list is empty.");
    }

    @Test
    public void testGetMonthWithMostHoursWithEmptyList() {
        List<GameRecord> records = List.of();
        assertNull(GameService.getMonthWithMostHoursOrNull(records), "Month with most hours should be null when the list is empty.");
    }

    @Test
    public void testGetRepeatedGamesWithNoRepeats() {
        List<GameRecord> records = Arrays.asList(
                new GameRecord("Game1", Genre.RPG, LocalDate.parse("2024-01-15"), 20, 5),
                new GameRecord("Game2", Genre.STRATEGY, LocalDate.parse("2024-02-10"), 15, 4),
                new GameRecord("Game3", Genre.RPG, LocalDate.parse("2024-03-05"), 30, 5)
        );
        assertTrue(GameService.getRepeatedGames(records).isEmpty(), "There should be no repeated games.");
    }

    @Test
    public void testGetLongestGame() {
        List<GameRecord> records = Arrays.asList(
                new GameRecord("Game1", Genre.RPG, LocalDate.parse("2024-01-15"), 20, 5),
                new GameRecord("Game2", Genre.STRATEGY, LocalDate.parse("2024-02-10"), 50, 4),
                new GameRecord("Game3", Genre.ADVENTURE, LocalDate.parse("2024-03-05"), 30, 5)
        );
        assertEquals("Game2", GameService.getLongestGame(records));
    }

    @Test
    public void testGetLongestGameWithEmptyList() {
        List<GameRecord> records = List.of();
        assertNull(GameService.getLongestGame(records));
    }
}
