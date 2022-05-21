import java.util.HashMap;

public class Main {

    private static HashMap<Integer, Long> fib = new HashMap<>();

    // Сохраняем только новые значения
    private static void saveFibos(int value) {
        for (int i = 2; i <= value; i++) {
            if (!fib.containsKey(i)) {
                fib.put(i, Long.valueOf(fib.get(i - 1) + fib.get(i - 2)));
            }
        }
    }

    public static Long getFibo(int value) {
        if (fib.containsKey(value))
            return fib.get(value);
        else {
            saveFibos(value);
            return fib.get(value);
        }
    }

    public static void main(String[] args) {
        fib.put(0, 0L);
        fib.put(1, 1L);
        System.out.println(getFibo(12));
    }
}
