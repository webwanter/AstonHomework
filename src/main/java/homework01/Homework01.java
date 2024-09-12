package homework01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Homework01 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("John", "Mark", "Sam");
        OwnArrayList<String> test = new OwnArrayList<>(strings);
        System.out.println(test.get(0));
    }
}
