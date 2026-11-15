//This is where the program starts
public class Main {
    public static void main(String[] args) {

        // Study areas
        StudyArea humBach = new StudyArea("HumBach", true);
        StudyArea compSci = new StudyArea("Computer Science", false);
        StudyArea communications = new StudyArea("Communications", false);

        // Activities
        Course humBachCourses = new Course("HumBach Courses", 80, humBach);
        Project humBachProject1 = new Project("HumBach Project 1", 15, humBach);
        Project humBachProject2 = new Project("HumBach Project 2", 15, humBach);
        Course digitalMediaLandscape = new Course("The Digital Media Landscape", 20, communications);
        Project comProject = new Project("Communications Project", 15, communications);
        Course CompSci = new Course("Software Development", 20, compSci);
        Project compSciProject = new Project("Computer Science Project", 15, compSci);

        //Nora signs up for everything
        Student nora = new Student("Nora");
        nora.getProgramme().signUp(humBachCourses);
        nora.getProgramme().signUp(humBachProject1);
        nora.getProgramme().signUp(humBachProject2);
        nora.getProgramme().signUp(digitalMediaLandscape);
        nora.getProgramme().signUp(comProject);
        nora.getProgramme().signUp(CompSci);
        nora.getProgramme().signUp(compSciProject);

        //Imran only signs up for two things
        Student Imran = new Student("Imran");
        Imran.getProgramme().signUp(humBachProject1);
        Imran.getProgramme().signUp(digitalMediaLandscape);

        //Imran joins Nora's first group
        ProjectGroup group1 = new ProjectGroup(nora, "Digitalization and Mediatization", "Johan", humBachProject1);
        group1.addMember(Imran);
        new ProjectGroup(nora, "Robots", "Lucas", humBachProject2);
        new ProjectGroup(nora, "Drones", "Johan", compSciProject);

        //Nora is missing a group for the Communications project
        nora.getProgramme().checkCompletion();

        //Now Nora gets that group too
        new ProjectGroup(nora, "Political Communicative Patterns", "Dario", comProject);
        nora.getProgramme().checkCompletion();

        //checks Imran's programme
        Imran.getProgramme().checkCompletion();

        try {
            new ProjectGroup(Imran, "Not allowed", "Mads", compSciProject);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
