import org.example.FileHandler;
import org.example.GameApplication;
import org.example.GameRecord;
import org.example.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameApplicationMockTest {

    @Mock
    private FileHandler fileHandler;

    @Test
    void main_shouldCallAllServices() throws IOException {

        List<GameRecord> mockRecords = Arrays.asList(
                new GameRecord("Game1", Genre.RPG, LocalDate.now(), 10, 5),
                new GameRecord("Game2", Genre.ADVENTURE, LocalDate.now(), 15, 4)
        );

        try (MockedStatic<FileHandler> fileHandlerMock = Mockito.mockStatic(FileHandler.class)) {
            fileHandlerMock.when(() -> FileHandler.readRecordsFromFile(anyString()))
                    .thenReturn(mockRecords);

            GameApplication.main(new String[]{});

            fileHandlerMock.verify(() -> FileHandler.readRecordsFromFile(anyString()), times(1));
        }

    }
}
