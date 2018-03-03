package c9;

import c8.InsertionSort;
import c8.SelectionSort;

import java.util.Random;

public class TestSortAlgorithms {
    private static int SIZE_ARRAY = 100000;

    public static void main(String[] args) {
        testSelectionSort();
        testInsertionSort();
        testMergeSort();
        testQuickSort();
    }

    public static void testSelectionSort(){
        System.out.println("Testing Selection Sort time");
        Integer[] arrayInt = getArrayIntegers();
        long start = System.currentTimeMillis();
        //Integer[] arrayIntegers = {1,5,9,4,7,2,3,8,6};
        //MergeSort.sort(arrayIntegers);
        SelectionSort.sort(arrayInt, arrayInt.length);
        long timeLasted = System.currentTimeMillis() - start;
        System.out.println("*** Array Sorted in " + timeLasted + " milliseconds");
    }

    public static void testInsertionSort(){
        System.out.println("Testing Insertion Sort time");
        Integer[] arrayInt = getArrayIntegers();
        long start = System.currentTimeMillis();
        //Integer[] arrayIntegers = {1,5,9,4,7,2,3,8,6};
        //MergeSort.sort(arrayIntegers);
        InsertionSort.sort(arrayInt, 0, arrayInt.length);
        long timeLasted = System.currentTimeMillis() - start;
        System.out.println("*** Array Sorted in " + timeLasted + " milliseconds");
    }

    public static void testMergeSort(){
        System.out.println("Testing Merge Sort time");
        Integer[] arrayInt = getArrayIntegers();
        long start = System.currentTimeMillis();
        //Integer[] arrayIntegers = {1,5,9,4,7,2,3,8,6};
        //MergeSort.sort(arrayIntegers);
        MergeSort.sort(arrayInt);
        long timeLasted = System.currentTimeMillis() - start;
        System.out.println("*** Array Sorted in " + timeLasted + " milliseconds");
    }
    public static void testQuickSort(){
        System.out.println("Testing Quick Sort time");
        Integer[] arrayInt = getArrayIntegers();
        long start = System.currentTimeMillis();
        //Integer[] arrayIntegers = {1,5,9,4,7,2,3,8,6};
        //MergeSort.sort(arrayIntegers);
        QuickSort.sort(arrayInt);
        long timeLasted = System.currentTimeMillis() - start;
        System.out.println("*** Array Sorted in " + timeLasted + " milliseconds");
    }

    private static Integer[] getArrayIntegers() {
        Integer[] arrayInt= new Integer[SIZE_ARRAY];
        Random random = new Random();

        for (int i = 0; i < arrayInt.length; i++)
        {
            arrayInt[i] = random.nextInt(1000);
        }
        return arrayInt;
    }
}
