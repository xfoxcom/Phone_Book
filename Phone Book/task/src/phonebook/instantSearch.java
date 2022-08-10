package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class instantSearch {
    public static void searchInstant (File whatLookFor, File whereLookFor) throws FileNotFoundException {
        Scanner scannerWhat = new Scanner(whatLookFor);
        Scanner scannerWhere = new Scanner(whereLookFor);

        int count = 0;
        Map<Integer, String> whatToFind = new HashMap<>();
        Map<Integer, String> whereToFind = new HashMap<>();

        int i = 0;
        LocalTime startTable = LocalTime.now();
        while(scannerWhat.hasNextLine()) {
            whatToFind.put(i++, scannerWhat.nextLine());
        }

        while (scannerWhere.hasNextLine()) {
            String info = scannerWhere.nextLine();

            whereToFind.put(Integer.valueOf(info.split("\\s+")[0]), info.split("\\d+ ")[1]);
        }
        LocalTime endTable = LocalTime.now();

        System.out.println("Start searching (hash table)...");






    }
}
