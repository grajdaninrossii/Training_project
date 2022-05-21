import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

public class Main {

    public static String [] names = {"Оля", "Олег", "Глеб", "Алина", "Самвел"};
    public static Random est = new Random();

    public static void main(String[] args) {

        TreeSet<Student> students = new TreeSet<Student>();
        for (int i = 0; i < 5; i++){
            students.add(new Student(names[i], est.nextInt(4) + 2 ));
        }

        for (Student s: students){
            System.out.println(s.getName() +  " "  + Integer.toString(s.getEstimation()));
        }
        System.out.println();

        for (Student s: students.headSet(new Student("comparated", 3))){
            System.out.println(s.getName() +  " "  + Integer.toString(s.getEstimation()));
        }

    }
}
