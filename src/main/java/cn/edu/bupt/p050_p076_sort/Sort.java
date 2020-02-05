package cn.edu.bupt.p050_p076_sort;

public class Sort {
    public static void main(String[] args) {

    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] arr) {
        BubbleSort.bubbleSort(arr);
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] arr) {
        SelectSort.selectSort(arr);
    }

    /**
     * 插入排序
     */
    public static void insertSort(int[] arr) {
        InsertSort.insertSort(arr);
    }

    /**
     * 希尔排序
     */
    public static void shellSort(int[] arr) {
        ShellSort.shellSort(arr);
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] arr) {
        QuickSort.quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 归并排序
     */
    public static void mergeSort(int[] arr) {
        MergeSort.mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
    }

    /**
     * 基数排序
     */
    public static void radixSort(int[] arr) {
        RadixSort.radixSort(arr);
    }
}
