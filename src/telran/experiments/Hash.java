package telran.experiments;

public class Hash {
    /**
     * [i] = 0 - не встречается,
     *       1 - есть положительный,
     *       2 - есть отрицательный,
     *       4 - есть оба
     *
     * @param array input array
     * @return hash array
     */
    public static byte[] getArrayHash(int[] array)
    {
        byte[] hash = new byte[Integer.MAX_VALUE];

        for (int value : array) {
            Hash.putInHash(hash, value);
        }

        return hash;
    }

    public static void putInHash(byte[] hash, int value)
    {
        boolean isNegative = value < 0;
        int positiveValue = isNegative ? -value : value;

        byte hashValue = hash[positiveValue];

        switch (hashValue) {
            case 0:
                hash[positiveValue] = (byte) (isNegative ? -1 : 1);
                break;
            case 1:
                if (isNegative) {
                    hash[positiveValue] = 2;
                }
                break;
            case -1:
                if (!isNegative) {
                    hash[positiveValue] = 2;
                }
                break;
            case 2:
            default:
                break;
        }
    }

    public static boolean isInHash(byte[] hash, int value)
    {
        int positiveValue = value < 0 ? -value : value;
        byte hashValue = hash[positiveValue];

        switch (hashValue) {
            case 0:
                return false;
            case 1:
                return value >= 0;
            case -1:
                return value < 0;
            case 2:
                return true;
        }

        return false;
    }
}
