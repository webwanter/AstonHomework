package homework01;


public class Homework01 {
    public static void main(String[] args) {
        OwnArrayList<Integer> numbers = new OwnArrayList<>();

        for (int i = 0; i < 1000; i++) {
            numbers.add((int) (Math.random() * 1000000));
        }
        numbers.sort();
        numbers.print();
        numbers.remove(-1);
//        for (int i = 0; i < 1000; i++) {
//            numbers.remove(i);
//        }
        numbers.print();

    }
}
