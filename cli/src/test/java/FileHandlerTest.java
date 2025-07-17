import org.example.FileHandler;
import org.example.GameRecord;
import org.example.Genre;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void readRecordsFromFile_validFile_returnsGameRecords() throws IOException {
        Path testFilePath = Path.of("src/test/resources/test_data.txt");
        Files.write(testFilePath, Arrays.asList(
                "Game1;RPG;15-01-2024;5;5",
                "Game2;STRATEGY;20-02-2024;10;4"
        ));

        List<GameRecord> records = FileHandler.readRecordsFromFile(testFilePath.toString());
        assertFalse(records.isEmpty());
        assertEquals(2, records.size());
        assertEquals("Game1", records.get(0).getTitle());
        assertEquals(Genre.RPG, records.get(0).getGenre());
        assertEquals(LocalDate.of(2024, 1, 15), records.get(0).getCompletionDate());
        assertEquals(5, records.get(0).getHoursSpent());
        assertEquals(5, records.get(0).getRating());
    }

    @Test
    void readRecordsFromFile_invalidFileFormat_throwsException() throws IOException {
        Path invalidTestFilePath = Path.of("src/test/resources/invalid_data.txt");
        Files.write(invalidTestFilePath, Arrays.asList(
                "Game1;RPG;15-01-2024;5", // Не хватает оценки
                "Game2;STRATEGY;20-02-2024;10;4"
        ));

        assertThrows(RuntimeException.class, () -> FileHandler.readRecordsFromFile(invalidTestFilePath.toString()));
    }

    @Test
    void readRecordsFromFile_emptyFile_returnsEmptyList() throws IOException {
        Path emptyTestFilePath = Path.of("src/test/resources/empty_data.txt");
        Files.write(emptyTestFilePath, Collections.emptyList());

        List<GameRecord> records = FileHandler.readRecordsFromFile(emptyTestFilePath.toString());
        assertTrue(records.isEmpty());
    }

    @Test
    void readRecordsFromFile_fileNotFound_throwsException() {
        String nonExistentFilePath = "src/test/resources/non_existent_file.txt";
        assertThrows(RuntimeException.class, () -> FileHandler.readRecordsFromFile(nonExistentFilePath));
    }

    @Test
    void readRecordsFromFile_invalidDateFormat_throwsException() throws IOException {
        Path testFilePath = Path.of("src/test/resources/invalid_date.txt");
        Files.write(testFilePath, Arrays.asList("Game1;RPG;2024/01/15;5;5"));

        assertThrows(RuntimeException.class, () -> FileHandler.readRecordsFromFile(testFilePath.toString()));
    }

    @Test
    void readRecordsFromFile_invalidRatingFormat_throwsException() throws IOException {
        Path testFilePath = Path.of("src/test/resources/invalid_rating.txt");
        Files.write(testFilePath, Arrays.asList("Game1;RPG;15-01-2024;abc;5"));

        assertThrows(RuntimeException.class, () -> FileHandler.readRecordsFromFile(testFilePath.toString()));
    }

    @Test
    void readRecordsFromFile_invalidHoursSpentFormat_throwsException() throws IOException {
        Path testFilePath = Path.of("src/test/resources/invalid_hours.txt");
        Files.write(testFilePath, Arrays.asList("Game1;RPG;15-01-2024;5;xyz"));

        assertThrows(RuntimeException.class, () -> FileHandler.readRecordsFromFile(testFilePath.toString()));
    }

    @Test
    void readRecordsFromFile_invalidGenre_throwsException() throws IOException {
        Path testFilePath = Path.of("src/test/resources/invalid_genre.txt");
        Files.write(testFilePath, Arrays.asList("Game1;INVALID_GENRE;15-01-2024;5;5"));

        assertThrows(IllegalArgumentException.class, () -> FileHandler.readRecordsFromFile(testFilePath.toString()));
    }
}
