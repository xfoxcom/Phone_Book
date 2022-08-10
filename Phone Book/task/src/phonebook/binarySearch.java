package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class binarySearch {


    public static void searchBinary (File whatLookFor, File whereLookFor) throws FileNotFoundException {
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

        System.out.println("Start searching (quick sort + binary search)...");

        LocalTime start = LocalTime.now();
        quickSort(whereToFind, 0, whereToFind.size() - 1);

        LocalTime endSort = LocalTime.now();
        for (String s : whatToFind) {
            if(searchBinary(whereToFind, s)) count++;
        }
        LocalTime endSearch = LocalTime.now();

        long minutes = start.until(endSort, ChronoUnit.MINUTES);
        long seconds = start.until(endSort, ChronoUnit.SECONDS) - minutes*60;
        long millsec = start.until(endSort, ChronoUnit.MILLIS) - minutes*60*1000 - seconds*1000;

        long minutesSearch = endSort.until(endSearch, ChronoUnit.MINUTES);
        long secondsSearch = endSort.until(endSearch, ChronoUnit.SECONDS) - minutesSearch*60;
        long millsecSearch = endSort.until(endSearch, ChronoUnit.MILLIS) - minutesSearch*60*1000 - secondsSearch*1000;

        long minutesFull = start.until(endSearch, ChronoUnit.MINUTES);
        long secondsFull = start.until(endSearch, ChronoUnit.SECONDS) - minutesFull*60;
        long millsecFull = start.until(endSearch, ChronoUnit.MILLIS) - minutesFull*60*1000 - secondsFull*1000;

        System.out.println("Found " + count + "/" + whatToFind.size() + " entries. Time taken: " + minutesFull + " min. " + secondsFull + " sec. " + millsecFull + " ms.");
        System.out.println("Sorting time: " + minutes + " min. " + seconds + " sec. " + millsec + " ms.");
        System.out.println("Searching time: " + minutesSearch + " min. " + secondsSearch + " sec. " + millsecSearch + " ms.");


    }
    public static boolean searchBinary (List<String> whereToFind, String whatToFind) {
        int left = 0;
        int right = whereToFind.size() - 1;

        while (left <= right) {
            int middle = left + (right - left)/2;
            if (whereToFind.get(middle).split("\\d+ ")[1].compareTo(whatToFind) == 0) {
                return true;
            }
            if (whereToFind.get(middle).split("\\d+ ")[1].compareTo(whatToFind) > 0) {
                right = middle - 1;
            } else left = middle + 1;
        }
        return false;
    }
    public static void quickSort (List<String> list, int begin, int end) {

        if (begin >= end) return;

        String pivot = list.get(end).split("\\d+ ")[1];

        int leftPointer = begin;
        int rightPointer = end;

        while (leftPointer < rightPointer) {

            while (list.get(leftPointer).split("\\d+ ")[1].compareTo(pivot) <= 0 && leftPointer < rightPointer) {
                leftPointer++;
            }

            while (list.get(rightPointer).split("\\d+ ")[1].compareTo(pivot) >= 0 && leftPointer < rightPointer) {
                rightPointer--;
            }
            swap(list, leftPointer, rightPointer);
        }

        swap(list, leftPointer, end);

        quickSort(list, begin, leftPointer - 1);
        quickSort(list, leftPointer + 1, end);
    }
    private static void swap (List<String> list, int index1, int index2) {
        String temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
