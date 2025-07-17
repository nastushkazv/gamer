import org.example.FileHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FileHandlerMockTest {

    @Test
    void readRecordsFromFile_shouldHandleIOException(@TempDir Path tempDir) {
        Path testFile = tempDir.resolve("test.txt");

        try (MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {
            filesMock.when(() -> Files.lines(any(Path.class)))
                    .thenThrow(new IOException("Test exception"));

            assertThrows(RuntimeException.class, () -> FileHandler.readRecordsFromFile(testFile.toString()));
        }
    }

    @Test
    void readRecordsFromFile_shouldCallFilesLinesOnce(@TempDir Path tempDir) throws IOException {
        Path testFile = tempDir.resolve("test.txt");
        Files.write(testFile, List.of("Game1;RPG;15-01-2024;20;5"));

        try (MockedStatic<Files> filesMock = Mockito.mockStatic(Files.class)) {
            filesMock.when(() -> Files.lines(any(Path.class)))
                    .thenReturn(Stream.of("Game1;RPG;15-01-2024;20;5"));

            FileHandler.readRecordsFromFile(testFile.toString());

            filesMock.verify(() -> Files.lines(any(Path.class)), times(1));
        }
    }
}
