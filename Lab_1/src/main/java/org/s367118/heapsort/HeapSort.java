package org.s367118.heapsort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HeapSort<T> {
    Comparator<T> comparator;

    public HeapSort(Comparator<T> comparator){
        this.comparator = comparator;
    }

    public void sort(T[] arr) {
        buildMaxHeap(arr);

        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, i, 0);
            heapify(arr, i, 0);
        }
    }

    public List<Step<T>> sortSteps(T[] arr){
        List<Step<T>> steps = new ArrayList<>();
        steps.add(new Step<>(arr.clone(), "initial array"));

        buildMaxHeap(arr);
        steps.add(new Step<>(arr.clone(), "build max heap"));

        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, i, 0);
            heapify(arr, i, 0);
            steps.add(new Step<>(arr.clone(), "root is " + i));
        }

        return steps;
    }

    protected void buildMaxHeap(T[] arr){
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    protected void heapify(T[] arr, int n, int i)
    {
        int l = 2*i + 1;
        int r = 2*i + 2;
        int largest = indexOfLargest(arr, n, i, l, r);

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);  // as i element moved to subtree with 'largest' index
        }
    }

    protected void swap(T[] arr, int i, int j){
        T temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    protected int indexOfLargest(T[] arr, int n, int i, int j, int k){
        int largest = i;
        if (j < n && comparator.compare(arr[j], arr[largest]) > 0 )
            largest = j;

        if (k < n && comparator.compare(arr[k], arr[largest]) > 0)
            largest = k;

        return largest;
    }
}
