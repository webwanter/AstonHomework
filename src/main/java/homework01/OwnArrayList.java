package homework01;


import jdk.internal.util.ArraysSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class OwnArrayList<E> {

    /**
     * DEFAULT_CAPACITY отвечает за стандартный размер массива
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * переменная size определяет актуальное количество элементов в коллекции, увеличивается при добавлении и уменьшается при удалении
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

    /**
     * Увеличивает вместимость коллекции, учитывая что коллекция содержит хотя бы минимальную емкость
     */
    private Object[] grow(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > 0 || elements != EMPTY_elements) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elements = Arrays.copyOf(elements, newCapacity);
        } else {
            return elements = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
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
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
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
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;
        Object[] elementData;
        final int s;
        if (numNew > (elementData = this.elements).length - (s = size))
            elementData = grow(s + numNew);
        System.arraycopy(a, 0, elementData, s, numNew);
        size = s + numNew;
        return true;
    }


    /**
     * Метод для удаления элемента без возврата значения
     */
    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
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
     * Быстрая сортировка
     */




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
