package homework01;


import jdk.internal.util.ArraysSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public  class OwnArrayList<E> {

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
     *  Конструктор для создания списка с заданным количеством элементов
     */
    public OwnArrayList(int initialCapacity) {
        if(initialCapacity > 0) {
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

    }

    /**
     * Печать коллекции в консоль
     */
    public void print() {
        System.out.println(Arrays.toString(elements));
    }

}
