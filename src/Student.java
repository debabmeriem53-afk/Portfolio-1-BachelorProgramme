public class Student {
    private final String name;
    private final BachelorProgramme programme;

    public Student(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("A student must have a name");
        }
        this.name = name;
        this.programme = new BachelorProgramme(this);
    }

    public String getName() {
        return name;
    }

    public BachelorProgramme getProgramme() {
        return programme;
    }

    @Override
    public String toString() {
        return name;
    }
}
