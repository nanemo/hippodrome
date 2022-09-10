import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @ParameterizedTest
    @NullSource
    void throwIfFirstParamIsNull(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 25, 4));
    }

    @ParameterizedTest
    @NullSource
    void checkMessageIfFirstParamIsNull(String name) {
        try {
            new Horse(name, 25, 4);
        } catch (IllegalArgumentException ex) {
            assertEquals("Name cannot be null.", ex.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  "})
    void throwIfFirstParamIsBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 25, 4));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  "})
    void checkMessageIfFirstParamIsBlank(String name) {
        try {
            new Horse(name, 25, 4);
        } catch (IllegalArgumentException ex) {
            assertEquals("Name cannot be blank.", ex.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1})
    void throwIfSecondParamIsNegative(int secondParam) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Name", secondParam, 4));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1})
    void checkMessageIfSecondParamIsNegative(int secondParam) {
        try {
            new Horse("Name", secondParam, 4);
        } catch (IllegalArgumentException ex) {
            assertEquals("Speed cannot be negative.", ex.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {-1})
    void throwIfThirdParamIsNegative(int thirdParam) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Name", 5, thirdParam));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1})
    void checkIfThirdParamIsNegative(int thirdParam) {
        try {
            new Horse("Name", 5, thirdParam);
        } catch (IllegalArgumentException ex) {
            assertEquals("Distance cannot be negative.", ex.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({"Carl, 5.0, 10.0"})
    void getName(String name, double speed, double distance) throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse(name, 5, 5);

        Field nameOfField = Horse.class.getDeclaredField("name");
        nameOfField.setAccessible(true);
        String horseName = (String) nameOfField.get(horse);
        assertEquals(name, horseName);
    }

    @ParameterizedTest
    @CsvSource({"Carl, 5.0, 10.0"})
    void getSpeed(String name, double speed, double distance) throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse(name, speed, distance);

        Field nameOfField = Horse.class.getDeclaredField("speed");
        nameOfField.setAccessible(true);
        double horseSpeed = (double) nameOfField.get(horse);
        assertEquals(speed, horseSpeed);
    }

    @ParameterizedTest
    @CsvSource({"Carl, 5.0, 10.0"})
    void getDistanceWithTripleParam(String name, double speed, double distance) throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse(name, speed, distance);

        Field nameOfField = Horse.class.getDeclaredField("distance");
        nameOfField.setAccessible(true);
        double horseDistance = (double) nameOfField.get(horse);
        assertEquals(distance, horseDistance);
    }

    @ParameterizedTest
    @CsvSource({"Carl, 5.0"})
    void getDistanceWithDoubleParam(String name, double speed) throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse(name, speed);

        Field nameOfField = Horse.class.getDeclaredField("distance");
        nameOfField.setAccessible(true);
        double horseDistance = (double) nameOfField.get(horse);
        assertEquals(0, horseDistance);
    }

    @Test
    void move() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            new Horse("name", 5, 5).move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

}