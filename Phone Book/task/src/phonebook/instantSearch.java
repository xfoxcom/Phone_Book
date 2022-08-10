package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class instantSearch {
    public static void searchInstant (File whatLookFor, File whereLookFor) throws FileNotFoundException {
        Scanner scannerWhat = new Scanner(whatLookFor);
        Scanner scannerWhere = new Scanner(whereLookFor);
        int i = 0;
        int count = 0;
        Map<Integer, String> whatToFind = new HashMap<>();
        Map<Integer, String> whereToFind = new HashMap<>();


        System.out.println("Start searching (hash table)...");
        LocalTime startTable = LocalTime.now();
        while(scannerWhat.hasNextLine()) {
            whatToFind.put(i++, scannerWhat.nextLine());
        }

        while (scannerWhere.hasNextLine()) {
            String info = scannerWhere.nextLine();
            whereToFind.put(i++, info.split("\\d+ ")[1]);
        }
        LocalTime endTable = LocalTime.now();

        for (String value : whatToFind.values()) {
            if (whereToFind.containsValue(value)) count++;
        }

        LocalTime endOfSearch = LocalTime.now();
        System.out.println(whereToFind.size());


        long minutesFull = startTable.until(endOfSearch, ChronoUnit.MINUTES);
        long secondsFull = startTable.until(endOfSearch, ChronoUnit.SECONDS) - minutesFull*60;
        long millsecFull = startTable.until(endOfSearch, ChronoUnit.MILLIS) - minutesFull*60*1000 - secondsFull*1000;

        long minutesSearch = endTable.until(endOfSearch, ChronoUnit.MINUTES);
        long secondsSearch = endTable.until(endOfSearch, ChronoUnit.SECONDS) - minutesSearch*60;
        long millsecSearch = endTable.until(endOfSearch, ChronoUnit.MILLIS) - minutesSearch*60*1000 - secondsSearch*1000;

        long minutes = startTable.until(endTable, ChronoUnit.MINUTES);
        long seconds = startTable.until(endTable, ChronoUnit.SECONDS) - minutes*60;
        long millsec = startTable.until(endTable, ChronoUnit.MILLIS) - minutes*60*1000 - seconds*1000;

        System.out.println("Found " + count + "/" + whatToFind.size() + " entries. Time taken: " + minutesFull + " min. " + secondsFull + " sec. " + millsecFull + " ms.");
        System.out.println("Creating time: "  + minutes + " min. " + seconds + " sec. " + millsec + " ms.");
        System.out.println("Searching time: " + minutesSearch + " min. " + secondsSearch + " sec. " + millsecSearch + " ms.");
    }
}
