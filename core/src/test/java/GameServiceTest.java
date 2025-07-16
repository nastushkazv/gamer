import org.example.GameRecord;
import org.example.GameService;
import org.example.Genre;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    @Test
    void getTopGenreOrNull_ShouldReturnNullForEmptyList() {
        assertNull(GameService.getTopGenreOrNull(List.of()));
    }

    @Test
    void getMonthWithMostHours_ShouldReturnJanuary() {
        List<GameRecord> records = List.of(
                new GameRecord("Game1", Genre.RPG, LocalDate.of(2023, 1, 15), 20, 5),
                new GameRecord("Game2", Genre.STRATEGY, LocalDate.of(2023, 1, 20), 30, 4)
        );
        assertEquals(Month.JANUARY, GameService.getMonthWithMostHoursOrNull(records));
    }

    @Test
    void getRepeatedGames_ShouldFindDuplicates() {
        List<GameRecord> records = List.of(
                new GameRecord("Skyrim", Genre.RPG, LocalDate.now(), 50, 5),
                new GameRecord("Skyrim", Genre.RPG, LocalDate.now(), 70, 5)
        );
        assertEquals(List.of("Skyrim"), GameService.getRepeatedGames(records));
    }
}