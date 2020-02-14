package telran.common.test;

import org.junit.jupiter.api.Test;
import telran.common.Array;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest
{
    @Test
    void testAddGetSize()
    {
        Array array = new Array(4);
        int[] numbers = {10, -8, 70, 75, 30};

        for (int number : numbers) {
            array.add(number);
        }

        for (int i = 0; i < array.size(); i++) {
            assertEquals(numbers[i], array.get(i));
        }

        assertNull(array.get(array.size()));
    }

    /**
     * Неконсистентность: при установке в NULL последнего элемента, size не изменяется.
     * Аналогичная ситуация с remove последнего элемента (и вытекающие проблемы, если выставить сперва
     * предпоследний элемент в NULL, а затем удалить последний, то общий size должен уменьшиться на 2).
     *
     * Однако, если устанить данную проблему, то это отразится на сложности алгоритма, нужно будет перебирать
     * массив начиная с size, до тох пор, пока не кончатся NULL. Но если делать это в после каждой операции,
     * то частые опереации будут гораздо сложнее, чем O(1)
     *
     * Так что нужно либо придерживаться парадигмы, что size никогда не уменьшается,
     * либо добавить доп. класс, в котором была бы устранена данная проблема.
     */

    @Test
    void testRemoveSet()
    {
        Array array = new Array(4);

        int[] numbers = {10, -8, 70, 75, 30};

        for (int number : numbers) {
            array.add(number);
        }

        int number = -12;
        int index = 2;

        int indexRemove = 3;

        assertEquals(70, array.set(index, number));
        assertEquals(number, array.get(index));

        assertEquals(75, array.remove(indexRemove));
        assertEquals(number, array.remove(index));

        assertNull(array.get(indexRemove));
    }
}