package homework01;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OwnArrayListTest {

    @BeforeAll
    static void setup() {
        System.out.println("Начало тестирования\n");
    }

    @BeforeEach
    void setupThis() {
        System.out.println("-------- Начало теста --------");
    }

    @Test
    void ownArrayList() {

        OwnArrayList<String> test = new OwnArrayList<>();
        test.print();
        System.out.println("[OK] - создаем пустой список и выводим в консоль\n");

        List<String> names = Arrays.asList("John", "Michael", "Carl");
        OwnArrayList<String> test1 = new OwnArrayList<>(names);
        test1.print();
        System.out.println("[OK] - создаем список на основе коллекции и выводим в консоль\n");
    }


    @Test
    void add() {

        OwnArrayList<String> test1 = new OwnArrayList<>(20);
        test1.add("Ivan");
        test1.add("Derek");
        test1.add("Trevor");
        test1.add("Ivy");
        test1.print();
        System.out.println("[OK] - добавляем элемент и распечатываем коллекцию\n");
        test1.add(3, "Lisa");
        test1.print();
        System.out.println("[OK] - добавляем элемент по индексу и распечатываем коллекцию\n");

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            test1.add(13, "Lisa");
        });
        System.out.println("[OK] - добавляем элемент по индексу превывшающему размер коллекции, проверяем на срабатывание исключения\n");

        assertEquals("Значение индекса превышает размер коллекции: " + test1.getSize(), exception.getMessage());

        OwnArrayList<Integer> testNumbers = new OwnArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testNumbers.add((int) (Math.random() * 1000000));
        }
        testNumbers.print();
        System.out.println("[OK] - создаем новую коллекцию интов и наполняем ее тысячей случайных чисел от 0 до 999999 включительно\n");

        for (int i = 0; i < 1000; i++) {
            testNumbers.remove(i);
        }
        testNumbers.print();
        System.out.println("[OK] - удаляем все элементы через цикл и выводим результат\n");


    }



    @Test
    void addAll() {
        OwnArrayList<String> test1 = new OwnArrayList<>();
        List<String> names = Arrays.asList("John", "Michael", "Carl");
        test1.addAll(names);
        test1.print();
        System.out.println("[OK] - добавляем коллекцию в наш список и распечатываем разультат\n");
    }

    @Test
    void get() {

        List<String> names = Arrays.asList("John", "Michael", "Carl");
        OwnArrayList<String> test1 = new OwnArrayList<>(names);

        System.out.println("[OK] - вывести элемент по индексу - " + test1.get(1) + "\n");
    }

    @Test
    void remove() {
        OwnArrayList<String> test1 = new OwnArrayList<>();
        List<String> names = Arrays.asList("John", "Michael", "Carl");
        test1.addAll(names);
        test1.remove("Carl");
        test1.print();
        System.out.println("[OK] - создаем коллекцию, удаляем элемент \"Carl\"\n");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            test1.remove(-1);
        });
        System.out.println("[OK] - проверяем на срабатывание исключения\n");

        assertEquals("Неверное значение индекса", exception.getMessage());
    }

    @Test
    void clear() {
        OwnArrayList<String> test1 = new OwnArrayList<>(20);
        test1.add("Ivan");
        test1.add("Derek");
        test1.add("Trevor");
        test1.clear();
        test1.print();
        System.out.println("[OK] - создаем коллекцию, очищаем и выводим в консоль\n");

    }

    @Test
    void quicksort() {
        OwnArrayList<String> test1 = new OwnArrayList<>(20);
        test1.add("Ivan");
        test1.add("Derek");
        test1.add("Anton");
        test1.sort();
        test1.print();
        System.out.println("[OK] - создаем коллекцию, сортируем и выводим в консоль\n");

        test1.sort(Comparator.reverseOrder());
        test1.print();
        System.out.println("[OK] - проверяем работу метода sort с компаратором, запустив обратную сортировку\n");


    }

    @AfterEach
    void tearThis() {
        System.out.println("-------- Конец теста --------\n");
    }

    @AfterAll
    static void tear() {
        System.out.println("Тестирование окончено");
    }

}