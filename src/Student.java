// A student has a name and their own bachelor programme
public class Student {
    private final String name;
    private final BachelorProgramme programme;

    //the name is stored and a new bachelor programme is created for this student
    public Student(String name) {
        this.name = name;
        this.programme = new BachelorProgramme (this);
    }

    //getters
    public String getName() {
        return name;
    }

    public BachelorProgramme getProgramme() {
        return programme;
    }
}
