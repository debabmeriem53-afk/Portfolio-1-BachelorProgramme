import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectGroupTest {

    // A correct group can be created
    @Test
    public void testCorrectGroup() {
        BasicStudies humTek = new BasicStudies("HumTek");
        Project project = new Project("Project", 15, humTek);
        Student anna = new Student("Anna");
        anna.getProgramme().signUp(project);

        ProjectGroup group = new ProjectGroup(anna, "My Project", "Peter", project);

        assertEquals(1, group.getMembers().size());
    }

    // A group must have at least one member
    @Test
    public void testNoMember() {
        BasicStudies humTek = new BasicStudies("HumTek");
        Project project = new Project("Project", 15, humTek);

        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectGroup(null, "My Project", "Peter", project);
        });
    }

    // The title must not be empty
    @Test
    public void testEmptyTitle() {
        BasicStudies humTek = new BasicStudies("HumTek");
        Project project = new Project("Project", 15, humTek);
        Student anna = new Student("Anna");
        anna.getProgramme().signUp(project);

        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectGroup(anna, "", "Peter", project);
        });
    }

    // The supervisor must not be empty
    @Test
    public void testEmptySupervisor() {
        BasicStudies humTek = new BasicStudies("HumTek");
        Project project = new Project("Project", 15, humTek);
        Student anna = new Student("Anna");
        anna.getProgramme().signUp(project);

        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectGroup(anna, "My Project", "", project);
        });
    }

    // The first member must be signed up for the project
    @Test
    public void testStudentNotSignedUp() {
        BasicStudies humTek = new BasicStudies("HumTek");
        Project project = new Project("Project", 15, humTek);
        Student anna = new Student("Anna");

        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectGroup(anna, "My Project", "Peter", project);
        });
    }

    // An extra member must also be signed up for the project
    @Test
    public void testExtraMemberNotSignedUp() {
        BasicStudies humTek = new BasicStudies("HumTek");
        Project project = new Project("Project", 15, humTek);
        Student anna = new Student("Anna");
        Student bob = new Student("Bob");
        anna.getProgramme().signUp(project);

        ProjectGroup group = new ProjectGroup(anna, "My Project", "Peter", project);

        assertThrows(IllegalArgumentException.class, () -> {
            group.addMember(bob);
        });
    }
}
