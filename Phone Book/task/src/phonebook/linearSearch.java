package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class linearSearch {
   public linearSearch(File whatLookFor, File whereLookFor) {
    }
    public static long[] searchLinear (File whatLookFor, File whereLookFor) throws FileNotFoundException {
        List<String> whatToFind = new ArrayList<>();
        List<String> whereToFind = new ArrayList<>();
        int countFound = 0;

        Scanner scannerWhat = new Scanner(whatLookFor);
        Scanner scannerWhere = new Scanner(whereLookFor);

        while (scannerWhat.hasNextLine()) {
            whatToFind.add(scannerWhat.nextLine());
        }

        while (scannerWhere.hasNextLine()) {
            whereToFind.add(scannerWhere.nextLine());
        }

        System.out.println("Start searching (linear search)...");
        LocalTime timeS = LocalTime.now();

        for (String s : whatToFind) {
            for (String s1 : whereToFind) {
                if (s.equals(s1.split("\\d+ ")[1])) countFound++;
            }
        }

        LocalTime timeE = LocalTime.now();

        long spendMin = timeS.until(timeE, ChronoUnit.MINUTES);
        long spendSec = timeS.until(timeE, ChronoUnit.SECONDS) - spendMin*60;
        long spendMilliSec = timeS.until(timeE, ChronoUnit.MILLIS) - spendMin*60*1000 - spendSec*1000;

        System.out.println("Found " + countFound + "/" + whatToFind.size() + " entries. Time taken: " + spendMin + " min. " + spendSec + "sec. " + spendMilliSec + "ms.");
        return new long[]{spendMin, spendSec, spendMilliSec};
    }
}
