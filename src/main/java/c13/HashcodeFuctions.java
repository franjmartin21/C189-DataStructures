package c13;

public class HashcodeFuctions {

    /**
     * A better hash code for a string. A better approach to generating a hash code for a string involves multiplying the
     * Unicode value of each character by a factor based on the character's position within the string. The hash code is
     * then the sum of these products. Specifically, if the string s has n characters, let ui be the Unicode value for the
     * ith character in s (i is zero for the first character). Then the hash code can have the form
     *
     * u0gn −1 + u1gn−2 + ... un − 2 g + un − 1
     *
     * for some positive constant g. This expression is a polynomial in g. To minimize the number of arithmetic operations,
     * write the polynomial in the following algebraically equivalent form:
     *
     * (…((u0 g + u1) g + u2) g+…+ un - 2) g + un - 1
     *
     * This way of evaluating a polynomial is called Horner's method.
     *
     * @return
     */
    public static int getHashcodeFromString(String stringToHash){
        int CONSTANT = 31;
        int hash = 0;
        int n = stringToHash.length();
        for (int i = 0; i < n; i++)
            hash = CONSTANT * hash + stringToHash.charAt(i);
        return hash;
    }

    /**
     * The hash code for a primitive type. This segment contains Java operations that might be unfamiliar to you. However,
     * they are not essential to the rest of this chapter.
     *
     * If the search key's data type is int, you can use the key itself as the hash code. If the search key is an instance
     * of either byte, short, or char, you can cast it to an int to get a hash code. Thus, casting to an int is one way to
     * generate a hash code.
     */
    public static int getHashCodeFromInt(int intToHash){
        return intToHash;
    }

    public static int getHashcodeFromByte(byte byteToHash){
        return byteToHash;
    }

    public static int getHascodeFromChar(char charToHash){
        return charToHash;
    }

    /**
     * LONG
     * The problem for long is that long is 64 bits while int is 32 so we will need to translate a 64 bits data to a 32 one
     *
     * A clue in the book says: Derive the hash code from the entire search key. Do not ignore part of it.
     *
     * let's divide a long search key into two 32-bit halves. To get the left half, we can shift the search key to the right
     * by a certain number of bits, or places. For example, if we shift the 8-bit binary number 10101100 to the right by 4 bits,
     * we will get 00001010. We have isolated the number's left half and discarded its right half. If we now combine 00001010
     * with the original value and ignore the left half of the result, we will effectively have combined the left and right
     * halves of the original key.
     *
     * Now let's see how to do this in Java. The expression key >> 32 shifts the 64-bit key to the right by 32 bits, in effect
     * eliminating its right half. Java's exclusive-or operator is ^ and has the following effect on 1-bit quantities:
     * 0 ^ 0 = 1
     * 1 ^ 1 = 1
     * 0 ^ 1 = 0
     * 1 ^ 0 = 0
     *
     * So: 1100 ^ 1010 is 0110
     */
    public static int getHashcodeFromLong(long longToChar){
        return (int)(longToChar ^ (longToChar >> 32));
    }

    /**
     * DOUBLE: It is also 64 bits
     * We can perform a similar computation for a search key of type double. Since key is a real value, we cannot use it in
     * the previous expression. Instead, we must get key's bit pattern by calling Double.doubleToLongBits(key). Thus, the
     * following statements produce the desired hash code
     */
    public static int getHashcodeFromDouble(double doubleToChar){
        long bits = Double.doubleToLongBits(doubleToChar);
        return getHashcodeFromLong(bits);
    }



}
