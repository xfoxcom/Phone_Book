package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class jumpSearch {
    public jumpSearch(File whatLookFor, File whereLookFor) {
    }
    public static void searchJump(File whatLookFor, File whereLookFor, long[] timePast) throws FileNotFoundException {
        Scanner scannerWhat = new Scanner(whatLookFor);
        Scanner scannerWhere = new Scanner(whereLookFor);
        int count = 0;

        List<String> whatToFind = new ArrayList<>();
        List<String> whereToFind = new ArrayList<>();

        while (scannerWhat.hasNextLine()) {
            whatToFind.add(scannerWhat.nextLine());
        }

        while (scannerWhere.hasNextLine()) {
            whereToFind.add(scannerWhere.nextLine());
        }

        System.out.println("Start searching (bubble sort + jump search)...");

        LocalTime start = LocalTime.now();
        long[] sortTime = bubleSort(whereToFind, timePast);
        LocalTime searchS = LocalTime.now();
        if (sortTime[3] == 0) {
            for (String s : whatToFind) {
                if (jumpS(whereToFind, s)) count++;
            }
        } else {
            for (String s : whatToFind) {
                for (String s1 : whereToFind) {
                    if (s.equals(s1.split("\\d+ ")[1])) count++;
                }
            }
        }
        LocalTime end = LocalTime.now();
        long spendMin = start.until(end, ChronoUnit.MINUTES);
        long spendSec = start.until(end, ChronoUnit.SECONDS) - spendMin*60;
        long spendMilliSec = start.until(end, ChronoUnit.MILLIS) - spendMin*60*1000 - spendSec*1000;

        long spendMinSearch = searchS.until(end, ChronoUnit.MINUTES);
        long spendSecSearch = searchS.until(end, ChronoUnit.SECONDS) - spendMinSearch*60;
        long spendMilliSecSearch = searchS.until(end, ChronoUnit.MILLIS) - spendMinSearch*60*1000 - spendSecSearch*1000;

        System.out.println("Found " + count + "/" + whatToFind.size() + " entries. Time taken: " + spendMin + " min. " + spendSec + " sec. " + spendMilliSec + " ms.");
        if (sortTime[3] == 0) {
            System.out.println("Sorting time: " + sortTime[0] + " min. " + sortTime[1] + " sec. " + sortTime[2] + " ms.");
        } else System.out.println("Sorting time: " + sortTime[0] + " min. " + sortTime[1] + " sec. " + sortTime[2] + " ms. - STOPPED, moved to linear search");
        System.out.println("Searching time: " + spendMinSearch + " min. " + spendSecSearch + " sec. " + spendMilliSecSearch + " ms.");
    }
    public static long[] bubleSort (List<String> list, long[] time) {
        long[] info = new long[4];
        LocalTime timeS = LocalTime.now();
        for (int i = 0; i < list.size(); i ++) {
            for (int j = 0; j < list.size(); j ++) {
                if (i!=j && list.get(i).split("\\d+")[1].compareTo(list.get(j).split("\\d+")[1]) < 0) {
                    String temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
            LocalTime check = LocalTime.now();
            long spendMin = timeS.until(check, ChronoUnit.MINUTES);
            long spendSec = timeS.until(check, ChronoUnit.SECONDS) - spendMin*60;
            long spendMilliSec = timeS.until(check, ChronoUnit.MILLIS) - spendMin*60*1000 - spendSec*1000;
            if (spendMin > time[0]*10 & spendSec > time[1] & spendMilliSec > time[2]) {
                info[3] = 1;
                break;
            } else info[3] = 0;
        }
         LocalTime timeE = LocalTime.now();
         info[0] = timeS.until(timeE, ChronoUnit.MINUTES);
         info[1] = timeS.until(timeE, ChronoUnit.SECONDS) - info[0]*60;
         info[2] = timeS.until(timeE, ChronoUnit.MILLIS) - info[0]*60*1000 - info[1]*1000;
         return info;
    }
    public static boolean jumpS (List<String> whereToFind, String whatToFind) {
        int n = whereToFind.size();
        int step = (int)Math.floor(Math.sqrt(n));
        int curr = 0;
        int prev = 0;
        while (whereToFind.get(curr).split("\\d+ ")[1].compareTo(whatToFind) < 0) {
            if (curr == n - 1) return false;
            prev = curr;
            curr = Math.min(curr + step, n - 1);
        }
        while (whereToFind.get(curr).split("\\d+ ")[1].compareTo(whatToFind) > 0) {
            curr--;
            if (curr <= prev) return false;
        }
        return whereToFind.get(curr).split("\\d+ ")[1].compareTo(whatToFind) == 0;
    }
}
