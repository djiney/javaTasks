package telran.additional._01_hasSumTwo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import telran.additional._01_hasSumTwo.Main;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MainTest
{
    @Test
    public void testSum()
    {
        short[] array = {1, 7, 8, 9, 2, 3, 4, 5};
        short sum = 10;

        Assertions.assertTrue(Main.hasSumTwo(array, sum));
    }

    @Test
    public void testSingleNumber()
    {
        short[] array = {10, 9};
        short sum = 10;

        assertFalse(Main.hasSumTwo(array, sum));
    }

    @Test
    public void testNoNumber()
    {
        short[] array = {1, 2, 3};
        short sum = 10;

        assertFalse(Main.hasSumTwo(array, sum));
    }
}