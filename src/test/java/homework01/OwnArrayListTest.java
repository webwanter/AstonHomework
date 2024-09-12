package homework01;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OwnArrayListTest {

    @BeforeAll
    static void setup(){
        System.out.println("Начало тестирования\n");
    }

    @BeforeEach
    void setupThis(){
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

        OwnArrayList<String> test1 = new OwnArrayList<>();
        test1.add((String) "Ivan");
        test1.print();
        System.out.println("[OK] - добавляем элемент и распечатываем коллекцию\n");


    }

    @AfterEach
    void tearThis(){
        System.out.println("-------- Конец теста --------\n");
    }

    @AfterAll
    static void tear(){
        System.out.println("Тестирование окончено");
    }

}