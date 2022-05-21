public class Student implements Comparable<Student>{

    private String name;
    private int estimation;

    public Student(String name, int estimation){
        this.name = name;
        this.estimation = estimation;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public String getName() {
        return this.name;
    }

    public int getEstimation(){
        return this.estimation;
    }

    @Override
    public int compareTo(Student s) {
        return estimation - s.estimation == 0 ? -1: -(estimation - s.estimation);
    }
}