package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File find = new File("C:\\Users\\Alexander\\Downloads\\find.txt");
        File directory = new File("C:\\Users\\Alexander\\Downloads\\directory.txt");

       // long[] timePast = linearSearch.searchLinear(find, directory);
        System.out.println();
      //  jumpSearch.searchJump(find, directory, timePast);
        System.out.println();
       // binarySearch.searchBinary(find, directory);
        System.out.println();
        instantSearch.searchInstant(find, directory);
    }
}

