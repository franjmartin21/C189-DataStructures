package c8;

public class TestSortAlgorithms {
    public static void main(String[] args) {
        Integer[] arrayIntegers = {1,5,9,4,7,2,3,6,8};
        RecursiveInsertionSort.sort(arrayIntegers, 0, arrayIntegers.length - 1);
        for(Integer integer: arrayIntegers)
            System.out.println(integer);
    }
}
