import java.util.ArrayList;
import java.util.List;
public class ProjectGroup {
    private final Project project;
    private final String title;
    private final String supervisor;
    private final List<Student> members = new ArrayList<>();

    public ProjectGroup(Student firstMember, String title, String supervisor, Project project) {
        if (firstMember == null) {
            throw new IllegalArgumentException("A project group needs at least one member");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("A project group needs a title");
        }
        if (supervisor == null || supervisor.isBlank()) {
            throw new IllegalArgumentException("A project group needs a supervisor");
        }
        if (project == null) {
            throw new IllegalArgumentException("A project group needs a project");
        }
        this.project = project;
        this.title = title;
        this.supervisor = supervisor;
        addMember(firstMember);
    }

    /** Add one more student to the group. */
    public void addMember(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot add nobody to a group");
        }
        if (members.contains(student)) {
            throw new IllegalArgumentException(student + " is already in this group");
        }
        if (!student.getProgramme().isSignedUp(project)) {
            throw new IllegalArgumentException(student + " is not signed up for " + project.getName());
        }
        if (student.getProgramme().getGroupFor(project) != null) {
            throw new IllegalArgumentException(student + " already has a group for " + project.getName());
        }
        members.add(student);
        student.getProgramme().addGroup(this);
    }

    public Project getProject() {
        return project;
    }

    public String getTitle() {
        return title;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public List<Student> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public String toString() {
        return "\"" + title + "\" (" + project.getName() + ", supervisor: " + supervisor
                + ", members: " + members + ")";
    }
}
