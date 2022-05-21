public class Main {

    public static boolean isSort(int[] array) {
        int lastVal = array[0];
        for (int val: array){
            if (lastVal > val){
                return false;
            }
            lastVal = val;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arraySort = { 2, 3, 4, 5, 5, 57, 100 };
        int[] arrayNoSort = { 3, 4, 5, 5, 2, 57, 100 };

        System.out.println(isSort(arraySort));
        System.out.println(isSort(arrayNoSort));
    }

}