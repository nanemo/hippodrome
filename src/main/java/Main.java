import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        logger.info("Log started --------");
        List<Horse> horses = List.of(
                new Horse("Butsefal", 2.4),
                new Horse("Tuz Pik", 2.5),
                new Horse("Zefir", 2.6),
                new Horse("Pojar", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Peqas", 2.9),
                new Horse("Vishnya", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        logger.info("Start of the race. Number of participants: {}", horses.size());

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
            watch(hippodrome);
            TimeUnit.MILLISECONDS.sleep(200);
        }

        String winnerName = hippodrome.getWinner().getName();
        System.out.println("Победил " + winnerName + "!");

        logger.info("End of the race. Winner: " + winnerName);
    }

    private static void watch(Hippodrome hippodrome) {
        hippodrome.getHorses().stream()
                .map(horse -> ".".repeat((int) horse.getDistance()) + horse.getName())
                .forEach(System.out::println);
        System.out.println("\n".repeat(10));
    }

    @Override
    public String toString() {
        return "Main{}";
    }
}
