//Borrowed tools from JUnit 5
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectGroupTest {

    @Test //JUnit runs every method marked like this
    public void testCorrectGroup() {

        //Set up a project and a student and make a group
        StudyArea humBach = new StudyArea("HumBach", true);
        Project project = new Project("Project", 15, humBach);
        Student nora = new Student("Nora");
        nora.getProgramme().signUp(project);

        ProjectGroup group = new ProjectGroup(nora, "My Project", "Peter", project);

        //Checks if the group already has 1 member
        assertEquals(1, group.getMembers().size());
    }

    //A group must have at least one member
    @Test
    public void testNoMember() {
        StudyArea humTek = new StudyArea("HumBach", true);
        Project project = new Project("Project", 15, humTek);

        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectGroup(null, "My Project", "Elias", project);
        });
    }

    //The title must not be empty
    @Test
    public void testEmptyTitle() {
        StudyArea humTek = new StudyArea("HumBach", true);
        Project project = new Project("Project", 15, humTek);
        Student nora = new Student("Nora");
        nora.getProgramme().signUp(project);

        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectGroup(nora, "", "Elias", project);
        });
    }

    //The supervisor must not be empty
    @Test
    public void testEmptySupervisor() {
        StudyArea humBach = new StudyArea("HumBach", true);
        Project project = new Project("Project", 15, humBach);
        Student nora = new Student("Nora");
        nora.getProgramme().signUp(project);

        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectGroup(nora, "My Project", "", project);
        });
    }

    //The first member must be signed up for the project
    @Test
    public void testStudentNotSignedUp() {
        StudyArea humBach = new StudyArea("HumBach", true);
        Project project = new Project("Project", 15, humBach);
        Student nora = new Student("Nora");

        assertThrows(IllegalArgumentException.class, () -> {
            new ProjectGroup(nora, "My Project", "Elias", project);
        });
    }

    //An extra member must also be signed up for the project
    @Test
    public void testExtraMemberNotSignedUp() {
        StudyArea humBach = new StudyArea("HumBach", true);
        Project project = new Project("Project", 15, humBach);
        Student nora = new Student("Nora");
        Student Cecilie = new Student("Cecilie");
        nora.getProgramme().signUp(project);

        ProjectGroup group = new ProjectGroup(nora, "My Project", "Peter", project);

        assertThrows(IllegalArgumentException.class, () -> {
            group.addMember(Cecilie);
        });
    }
}
