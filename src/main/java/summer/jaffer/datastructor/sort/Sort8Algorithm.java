package summer.jaffer.datastructor.sort;

/**
 * 8大排序算法
 * 分为5类：插入排序、选择排序、比较排序、归并排序、基数排序
 */
public class Sort8Algorithm {

    /**
     * 插入排序
     * 1.直接插入排序
     * @param arr
     * @return
     */
    public static int[] insert(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        for(int i = 1; i < arr.length; i++) { // 从第二个元素开始
            for(int j = i; j > 0; j--) { // 以i为起点进行比较
                if(arr[j - 1] > arr[j]) { // 如果逆序则交换
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }


    /**
     * 插入排序
     * 2.希尔排序
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        int gap = arr.length; // 初始gap大小
        while (gap > 1) { // gap == 1 时结束
            gap = gap / 3 + 1; // 缩小gap
            for (int i = gap; i < arr.length; i++) { // 第一个起始点的位置为gap
                int j = i - gap; // 初始化j
                while (j >= 0) {
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = temp;
                    }
                    j = j - gap; // j以gap为间隔进行更新
                }
            }
        }
        return arr;
    }

    /**
     *  选择排序
     *  1.直接选择排序
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}
