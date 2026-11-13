public class Main {
    public static void main(String[] args) {

        BasicStudies humTek = new BasicStudies("HumTek");
        SubjectModule compSci = new SubjectModule("Computer Science");
        SubjectModule physics = new SubjectModule("Physics");

        Project basicProject1 = new Project("HumTek Project 1", 15, humTek);
        Project basicProject2 = new Project("HumTek Project 2", 15, humTek);
        Course basicCourses = new Course("HumTek Courses", 80, humTek);
        Course softwareDev = new Course("Software Development", 20, compSci);
        Project compSciProject = new Project("Computer Science Project", 15, compSci);
        Course mechanics = new Course("Mechanics", 20, physics);
        Project physicsProject = new Project("Physics Project", 15, physics);

        Student anna = new Student("Anna");
        Student bo = new Student("Bo");

        for (Activity a : new Activity[]{basicProject1, basicProject2, basicCourses,
                softwareDev, compSciProject, mechanics, physicsProject}) {
            anna.getProgramme().signUp(a);
        }
        bo.getProgramme().signUp(basicProject1);
        bo.getProgramme().signUp(softwareDev);

        ProjectGroup g1 = new ProjectGroup(anna, "Robots in the classroom", "Mads", basicProject1);
        g1.addMember(bo);
        new ProjectGroup(anna, "Sound and design", "Line", basicProject2);
        new ProjectGroup(anna, "A faster sorting tool", "Mads", compSciProject);
        System.out.println("Group created: " + g1);
        System.out.println();

        anna.getProgramme().checkCompletion();
        System.out.println();

        new ProjectGroup(anna, "Measuring gravity", "Jens", physicsProject);
        anna.getProgramme().checkCompletion();
        System.out.println();

        bo.getProgramme().checkCompletion();
        System.out.println();

        try {
            new ProjectGroup(bo, "Not allowed", "Mads", physicsProject);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception, as expected: " + e.getMessage());
        }
    }
}
