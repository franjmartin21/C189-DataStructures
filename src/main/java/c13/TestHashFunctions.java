package c13;

public class TestHashFunctions {
    public static void main(String[] args) {
        String stringToHash = "Java";
        int intToHash = 10;
        byte byteToHash = 10;
        char charToHash = 'f';
        long longToHash = 3634653456343456345L;
        double doubleToHash = 62345325.3245234;
        System.out.println(HashcodeFuctions.getHashcodeFromString(stringToHash));
        System.out.println(HashcodeFuctions.getHashCodeFromInt(intToHash));
        System.out.println(HashcodeFuctions.getHashcodeFromByte(byteToHash));
        System.out.println(HashcodeFuctions.getHascodeFromChar(charToHash));
        System.out.println(HashcodeFuctions.getHashcodeFromLong(longToHash));
        System.out.println(HashcodeFuctions.getHashcodeFromDouble(doubleToHash));
    }
}
