import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @ParameterizedTest
    @NullSource
    void throwIfParamIsNull(List<Horse> horses) {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @ParameterizedTest
    @NullSource
    void checkMessageIfParamIsNull(List<Horse> horses) {
        try {
            new Hippodrome(horses);
        } catch (IllegalArgumentException ex) {
            assertEquals("Horses cannot be null.", ex.getMessage());
        }
    }

    @ParameterizedTest
    @EmptySource
    void throwIfParamIsBlank(List<Horse> list) {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(list));

        assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @EmptySource
    void checkMessageIfParamIsBlank(List<Horse> list) {
        try {
            new Hippodrome(list);
        } catch (IllegalArgumentException ex) {
            assertEquals("Horses cannot be empty.", ex.getMessage());
        }
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Name", 5, 5));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(mock(Horse.class));
        }
        Hippodrome hippodromeObjectForMoveMethod = new Hippodrome(horseList);
        hippodromeObjectForMoveMethod.move();

        for (Horse horse : horseList) {
            verify(horse).move();
        }


    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Name", 5,2);
        Horse horse2 = new Horse("Name", 5,6);
        Horse horse3 = new Horse("Name", 5,3);
        Horse horse4 = new Horse("Name", 5,7);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2, horse3, horse4));

        assertSame(horse4, hippodrome.getWinner());
    }


}