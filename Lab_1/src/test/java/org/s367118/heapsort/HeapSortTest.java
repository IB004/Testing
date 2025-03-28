package org.s367118.heapsort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


class HeapSortTest {
    @ParameterizedTest
    @MethodSource("integerArraysProvider")
    public void simpleIntegerArraysTest(Integer[] arr){
        Comparator<Integer> comparator = Integer::compare;
        HeapSort<Integer> heapSort = new HeapSort<>(comparator);

        Integer[] heapSorted = arr.clone();
        heapSort.sort(heapSorted);

        Integer[] arraysSorted = arr.clone();
        Arrays.sort(arraysSorted, comparator);
        Assertions.assertArrayEquals(arraysSorted, heapSorted);
    }

    private static Stream<Arguments> integerArraysProvider(){
        return Stream.of(
                Arguments.of((Object) new Integer[]{42}),
                Arguments.of((Object) new Integer[]{5, 5, 5, 5}),
                Arguments.of((Object) new Integer[]{1, 2, 3, 4, 5}),
                Arguments.of((Object) new Integer[]{5, 4, 3, 2, 1}),
                Arguments.of((Object) new Integer[]{23, 124, -234, 0}),
                Arguments.of((Object) new Integer[]{0, 0, -1, -1, -1, 1, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("stringArraysProvider")
    public void simpleStringArraysTest(String[] arr){
        Comparator<String> comparator = Comparator.comparing(String::toString);
        HeapSort<String> heapSort = new HeapSort<>(comparator);

        String[] heapSorted = arr.clone();
        heapSort.sort(heapSorted);

        String[] arraysSorted = arr.clone();
        Arrays.sort(arraysSorted, comparator);
        Assertions.assertArrayEquals(arraysSorted, heapSorted);
    }

    private static Stream<Arguments> stringArraysProvider(){
        return Stream.of(
                Arguments.of((Object) new String[]{""}),
                Arguments.of((Object) new String[]{"a", "a", "a"}),
                Arguments.of((Object) new String[]{"a", "b", "c"}),
                Arguments.of((Object) new String[]{"DddD", "Cc", "Bbb", "Aaa"})
        );
    }


    @Test
    public void emptyArrayTest(){
        Comparator<Integer> comparator = Integer::compare;
        HeapSort<Integer> heapSort = new HeapSort<>(comparator);

        Integer[] emptyArr = {};
        heapSort.sort(emptyArr);

        Assertions.assertArrayEquals(new Integer[] {}, emptyArr);
    }

    @Test
    public void nullArrayTest(){
        Comparator<Integer> comparator = Integer::compare;
        HeapSort<Integer> heapSort = new HeapSort<>(comparator);

        Assertions.assertThrows(NullPointerException.class, () -> heapSort.sort(null));
    }


    @Test
    public void stepTest() {
        Comparator<Integer> comparator = Integer::compare;
        HeapSort<Integer> heapSort = new HeapSort<>(comparator);

        Integer[] arr = {3,8,4,7,6,2,1};
        Integer[][] prepared = {
                {3, 8, 4, 7, 6, 2, 1},
                {8, 7, 4, 3, 6, 2, 1},
                {7, 6, 4, 3, 1, 2, 8},
                {6, 3, 4, 2, 1, 7, 8},
                {4, 3, 1, 2, 6, 7, 8},
                {3, 2, 1, 4, 6, 7, 8},
                {2, 1, 3, 4, 6, 7, 8},
                {1, 2, 3, 4, 6, 7, 8},
                {1, 2, 3, 4, 6, 7, 8}
        };
        List<Step<Integer>> steps = heapSort.sortSteps(arr.clone());
        Integer[][] stepsHistory = steps.stream().map(Step::getHistory).toArray(Integer[][]::new);

        Assertions.assertArrayEquals(prepared, stepsHistory);
    }


    @Test
    public void swapTest(){
        HeapSortWhite<Integer> heapSortWhite = new HeapSortWhite<>(null);
        Integer[] arr = {0, 1, 2, 3, 4, 5};

        heapSortWhite.swap(arr, 0, arr.length - 1);

        Assertions.assertArrayEquals(new Integer[] {5, 1, 2, 3, 4, 0}, arr);
    }

    @ParameterizedTest
    @MethodSource("largestIndexProvider")
    public void indexOfLargestTest(Integer[] arr, int n, int i, int j, int k, int answer){
        Comparator<Integer> comparator = Integer::compare;
        HeapSortWhite<Integer> heapSortWhite = new HeapSortWhite<>(comparator);

        int largest = heapSortWhite.indexOfLargest(arr, n,  i, j, k);

        Assertions.assertEquals(answer, largest);
    }

    private static Stream<Arguments> largestIndexProvider(){
        return Stream.of(
                Arguments.of(new Integer[]{0, 1, 2, 3, 4}, 5, 0, 1, 2, 2),
                Arguments.of(new Integer[]{0, 1, 2, 3, 4}, 5, 3, 3, 3, 3),
                Arguments.of(new Integer[]{0, 1, 2, 3, 4}, 3, 1, 2, 3, 2)
        );
    }

}
