import java.util.ArrayList;
import java.util.List;

//A group of students working on one project, with a title and a supervisor.
public class ProjectGroup {
    private final String title;
    private final String supervisor;
    private final Project project;
    private final List<Student> members = new ArrayList<>(); //its list of members start empty

    //the cosntructor that checks the rules
    public ProjectGroup(Student student, String title, String supervisor, Project project) {
        if (student == null) {
            throw new IllegalArgumentException("A group needs at least one member");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("A group needs a title");
        }
        if (supervisor == null || supervisor.isBlank()) {
            throw new IllegalArgumentException("A group needs a supervisor");
        }
        //if all passes, store the values
        this.title = title;
        this.supervisor = supervisor;
        this.project = project;
        addMember(student);
    }

    //Adds a student to the group, if the rules allow it
    public void addMember(Student student) {
        if (!student.getProgramme().isSignedUp(project)) {
            throw new IllegalArgumentException(student.getName() + " is not signed up for " + project.getName());
        }
        if (student.getProgramme().hasGroupFor(project)) {
            throw new IllegalArgumentException(student.getName() + " already has a group for " + project.getName());
        }
        members.add(student);
        student.getProgramme().addGroup(this);
    }

    //getters
    public String getTitle() {
        return title;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public Project getProject() {
        return project;
    }

    public List<Student> getMembers() {
        return members;
    }
}
