import com.sun.source.tree.Tree;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        
        // Создаем список
        Map<Long, Long> synchMap = Collections.synchronizedMap(new HashMap());

        // Создаем потоки
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                long id = Thread.currentThread().getId();
                for (long i = 100 * id; i < 100*(id+1); i++) {
                        synchMap.put(i, id);
                        System.out.println("Работает поток: " + id);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                long id = Thread.currentThread().getId();
                for (long i = 100 * id; i < 100 * (id+1); i++) {
                    synchMap.put(i, id);
                    System.out.println("Работает поток: " + id);
                }
            }
        });

        thread1.start();
        thread2.start();

        // Выполнение
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Выводим результат
        for (Map.Entry<Long, Long> elem: synchMap.entrySet())
            System.out.println(elem.getKey() + ": " + elem.getValue());
    }
}