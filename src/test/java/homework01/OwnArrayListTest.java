package homework01;

import org.junit.jupiter.api.*;

import java.util.Arrays;
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
    void OwnArrayList() {

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
        test1.add((String) "Ivan");
        test1.add((String) "Derek");
        test1.add((String) "Trevor");
        test1.add((String) "Ivy");
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
    OwnArrayList<String> test = new OwnArrayList<>();

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
    test1.add((String) "Ivan");
    test1.add((String) "Derek");
    test1.add((String) "Trevor");
    test1.clear();
    test1.print();
    System.out.println("[OK] - создаем коллекцию, очищаем и выводим в консоль\n");

}

    @Test
    void quicksort() {
        OwnArrayList<String> test1 = new OwnArrayList<>(20);
        test1.add((String) "Ivan");
        test1.add((String) "Derek");
        test1.add((String) "Anton");
        test1.sort();
        test1.print();
        System.out.println("[OK] - создаем коллекцию, сортируем и выводим в консоль\n");

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