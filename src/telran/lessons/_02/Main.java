package telran.lessons._02;

public class Main
{
    /*** searches for index of a given number in a given array
     * @param array - array of numbers
     * @param number - number for search
     * @return index value or -1
     */
    public static int search(int[] array, int number)
    {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                return i;
            }
        }
        return -1;
    }

    /*** sorting of numbers
     * @param array - array of numbers
     */
    public static void sort(int[] array)
    {
        boolean flSort;
        int length = array.length;
        do {
            flSort = true;
            length--;
            for (int i = 0; i < length; i++) {
                if (array[i] > array[i + 1]){
                    int tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                    flSort = false;
                }
            }
        } while (!flSort);
    }

    /*** searches for index of a given number in a given sorted array
     * @param array - array of numbers
     * @param number - number for search
     * @return index value or -1
     */
    public static int binarySearch(int[] array, int number)
    {
        if (array.length < 1) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        int middle = (left + right) / 2;

        while (left <= right && array[middle] != number) {
            if (number < array[middle]){
                right = middle - 1;
            } else {
                left = middle + 1;
            }
            middle = (left + right) / 2;
        }

        System.out.println(middle);
        return array[middle] == number ? middle : -left - 1;
    }
}