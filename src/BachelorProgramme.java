import java.util.ArrayList;
import java.util.List;

public class BachelorProgramme {
    private final Student student; //A programme holds a student
    private final List<Activity> activities = new ArrayList<>(); //List of activities the student signed up for, which starts empty
    private final List<ProjectGroup> groups = new ArrayList<>(); //List of groups the student is in, which also starts empty

    //Constructor stores which student this programme belongs to
    public BachelorProgramme(Student student) {
        this.student = student;
    }

    //This signs the student up for an activity, and doesn't return anything back
    public void signUp(Activity activity) {
        activities.add(activity);
    }

    //Is the student signed up for this activity? true or false
    public boolean isSignedUp(Activity activity) {
        return activities.contains(activity);
    }

    //Adds a group to the student's list of groups.
    public void addGroup(ProjectGroup group) {
        groups.add(group);
    }

    //Checks if the student already has a group
    public boolean hasGroupFor(Activity project) {
        for (ProjectGroup group : groups) {
            if (group.getProject() == project) {
                return true;
            }
        }
        return false;
    }

    //Adds up the ECTS from one study area
    private int ectsIn(StudyArea area) {
        int sum = 0;
        for (Activity activity : activities) {
            if (activity.getArea() == area) {
                sum = sum + activity.getEcts();
            }
        }
        return sum;
    }

    //Checks if the programme is complete. If not, prints the reasons.
    public boolean checkCompletion() {
        List<String> problems = new ArrayList<>();

        //Need 110 ECTS from basic studies
        int basicEcts = 0;
        for (Activity activity : activities) {
            if (activity.getArea().isBasic()) {
                basicEcts = basicEcts + activity.getEcts();
            }
        }
        if (basicEcts < 110) {
            problems.add("Only " + basicEcts + " ECTS from basic studies (needs 110)");
        }

        //Need two subject modules with 35 ECTS each
        List<StudyArea> modules = new ArrayList<>();
        for (Activity activity : activities) {
            StudyArea area = activity.getArea();
            if (!area.isBasic() && !modules.contains(area)) {
                modules.add(area);
            }
        }

        int fullModules = 0;
        for (StudyArea module : modules) {
            int ects = ectsIn(module);
            if (ects >= 35) {
                fullModules++; //Add 1
            } else {
                problems.add("Only " + ects + " ECTS in " + module.getName() + " (needs 35)");
            }
        }
        if (fullModules < 2) {
            problems.add("Only " + fullModules + " subject module(s) completed (needs 2)");
        }

        //Finds out who/what doesn't have a group yet
        for (Activity activity : activities) {
            if (activity.needsGroup() && !hasGroupFor(activity)) {
                problems.add("No project group for " + activity.getName());
            }
        }

        //Print the result
        if (problems.isEmpty()) {
            System.out.println(student.getName() + " has completed the bachelor programme.");
            return true;
        }
        System.out.println(student.getName() + " has NOT completed the bachelor programme:");
        for (String problem : problems) {
            System.out.println("  - " + problem);
        }
        return false;
    }
}
