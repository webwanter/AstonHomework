package homework01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Homework01 {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(20);
        strings.addAll(Arrays.asList("John", "Mark", "Sam"));
        strings.remove("Mark");
        OwnArrayList<String> test = new OwnArrayList<>(strings);
        System.out.println(test.get(0));
//        strings.set(15, "Malva");
        System.out.println(Arrays.toString(strings.toArray()));
    }
}
