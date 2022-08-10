import java.time.Period;
import java.util.*;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Instant> instantList = createInstantList(scanner);

        long result = getMaxMinusMin(instantList); 

        System.out.println(result);
    }  

    public static List<Instant> createInstantList(Scanner scanner) {
        List<Instant> instantList = new ArrayList<>();
        instantList.add(Instant.parse(scanner.nextLine()));
        instantList.add(Instant.parse(scanner.nextLine()));
        instantList.add(Instant.parse(scanner.nextLine()));
        instantList.add(Instant.parse(scanner.nextLine()));

        return instantList;
    }

    private static long getMaxMinusMin(List<Instant> instantList) {
       Instant max = instantList.stream().max(Comparator.naturalOrder()).get();
       Instant min = instantList.stream().min(Comparator.naturalOrder()).get();
       return max.minusSeconds(min.getEpochSecond()).getEpochSecond();
    }
}