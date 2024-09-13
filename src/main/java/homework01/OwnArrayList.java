package homework01;

import java.util.*;

/**
 * Моя реализация коллекции, по типу и функионалу схожая с <a>java.util.ArrayList</a>
 *
 * @author Eduard Kamalov
 */
public class OwnArrayList<E> {

    /**
     * DEFAULT_CAPACITY отвечает за стандартный размер массива
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Переменная size определяет актуальное количество элементов в коллекции, увеличивается при добавлении и уменьшается при удалении
     */
    private int size;

    /**
     * Поле для хранения элементов коллекции
     */

    Object[] elements;

    /**
     * Пустой массив для создания пустых сущностей
     */
    private static final Object[] EMPTY_elements = {};

    /**
     * Конструктор для создания списка с заданным количеством элементов
     */
    public OwnArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = EMPTY_elements;
        } else throw new IllegalArgumentException("Wrong capacity value" + initialCapacity);
    }

    /**
     * Создание пустого списка
     */
    public OwnArrayList() {
        this.elements = EMPTY_elements;
    }

    /**
     * Создание коллекции, на основе другой коллекции
     */
    public OwnArrayList(Collection<E> collection) {
        Object[] temp = collection.toArray();
        if ((size = temp.length) != 0) {
            if (collection.getClass() == ArrayList.class) {
                elements = temp;
            } else {
                elements = Arrays.copyOf(temp, size, Object[].class);
            }
        } else {
            elements = EMPTY_elements;
        }
    }

    /**
     * Доступ к элементу по индексу с реализацией дженериков
     */
    E elements(int index) {
        return (E) elements[index];
    }

    /**
     * Получить элемент по индексу
     */
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elements(index);
    }


    private Object[] grow(int minCapacity) {
        int oldCapacity = elements.length;

        if (oldCapacity > 0 || elements != EMPTY_elements) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            newCapacity = Math.max(newCapacity, minCapacity);
            return elements = Arrays.copyOf(elements, newCapacity);
        } else {
            return elements = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }


    /**
     * Геттер для переменной size
     */
    public int getSize() {
        return size;
    }

    private Object[] grow() {
        return grow(size + 1);
    }


    /**
     * Добавить элемент
     */
    public void add(E el) {
        if (size == elements.length) {
            elements = grow();
        }
        elements[size] = el;
        size++;
    }

    /**
     * Добавить элемент в указанный индекс
     */
    public void add(int index, E el) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Значение индекса превышает размер коллекции: " + size);
        }

        final int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elements).length)
            elementData = grow();

        System.arraycopy(elementData, index, elementData, index + 1, s - index);
        elementData[index] = el;
        size = s + 1;
    }

    /**
     * Добавить коллекцию
     */
    public void addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elements).length - (s = size))
            elementData = grow(s + numNew);
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
    }


    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    /**
     * Удалить элемент по индексу
     */
    public void remove(int i) throws IllegalArgumentException {
        if (i >= 0 && i < elements.length) {
            elements[i] = null;
            size--;
        } else throw new IllegalArgumentException("Неверное значение индекса");

    }

    /**
     * Удалить элемент по equals
     */
    public void remove(Object o) {
        final Object[] es = elements;
        final int size = this.size;
        int i = 0;

        if (o == null) {
            for (; i < size; i++) {
                if (es[i] == null) {
                    break;
                }
            }
        } else {
            for (; i < size; i++) {
                if (o.equals(es[i])) {
                    break;
                }
            }
        }

        if (i < size) {
            fastRemove(es, i);
        }
    }


    /**
     * Быстрая сортировка с компаратором
     */
    public void sort() {
        quicksort(0, size - 1, null);
    }


    public void sort(Comparator<? super E> comparator) {
        quicksort(0, size - 1, comparator);
    }

    private void quicksort(int low, int high, Comparator<? super E> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quicksort(low, pivotIndex - 1, comparator);
            quicksort(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<? super E> comparator) {
        E pivot = (E) elements[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare((E) elements[j], pivot, comparator) < 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        E temp = (E) elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private int compare(E o1, E o2, Comparator<? super E> comparator) {
        if (comparator != null) {
            return comparator.compare(o1, o2);
        } else {
            return ((Comparable<? super E>) o1).compareTo(o2);
        }
    }

    /**
     * Очистить коллекцию
     */
    public void clear() {
        final Object[] es = elements;
        for (int to = size, i = size = 0; i < to; i++)
            es[i] = null;
    }


    /**
     * Печать коллекции в консоль
     */
    public void print() {
        System.out.println(Arrays.toString(elements));
    }

}
