package telran.additional._04_StackMax.tests;

import org.junit.jupiter.api.Test;
import telran.additional._04_StackMax.StackMax;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class StackMaxTest {

    @Test
    void testPushPopIsEmpty()
    {
        StackMax stack = new StackMax(4);
        int[] numbers = {10, -8, 70, 75, 30};

        assertTrue(stack.isEmpty());

        for (int number : numbers) {
            stack.push(number);
            assertFalse(stack.isEmpty());
        }

        for (int i = numbers.length - 1; i >= 0; i--) {
            assertEquals(numbers[i], stack.pop());
        }

        assertTrue(stack.isEmpty());
    }

    @Test
    void max()
    {
        StackMax stack = new StackMax(4);
        int[] numbers = {10, -8, 70, 75, 30};
        int[] maxNumbers = {10, 10, 70, 75, 75};

        assertThrows(NoSuchElementException.class, stack::pop);
        assertThrows(NoSuchElementException.class, stack::max);

        for (int i = 0; i < numbers.length; i++) {
            stack.push(numbers[i]);
            assertEquals(maxNumbers[i], stack.max());
        }

        assertEquals(75, stack.max());

        for (int i = numbers.length - 1; i >= 0; i--) {
            assertEquals(maxNumbers[i], stack.max());
            assertEquals(numbers[i], stack.pop());
        }

        assertThrows(NoSuchElementException.class, stack::max);
    }
}