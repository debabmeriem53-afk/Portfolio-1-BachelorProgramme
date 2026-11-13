import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * One student's bachelor programme: the list of activities they signed up for
 * and the project groups they are in.
 */
public class BachelorProgramme {
    public static final int BASIC_ECTS_REQUIRED = 110;
    public static final int SUBJECT_ECTS_REQUIRED = 35;
    public static final int SUBJECT_MODULES_REQUIRED = 2;

    private final Student student;
    private final List<Activity> activities = new ArrayList<>();
    private final List<ProjectGroup> groups = new ArrayList<>();

    BachelorProgramme(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    /** Sign the student up for an activity. */
    public void signUp(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Cannot sign up for nothing");
        }
        if (isSignedUp(activity)) {
            throw new IllegalArgumentException(student + " is already signed up for " + activity.getName());
        }
        activities.add(activity);
    }

    public boolean isSignedUp(Activity activity) {
        return activities.contains(activity);
    }

    /** Returns a copy, so nobody outside can change our list by accident. */
    public List<Activity> getActivities() {
        return new ArrayList<>(activities);
    }

    public List<ProjectGroup> getGroups() {
        return new ArrayList<>(groups);
    }

    /** Find the student's group for a project, or null if they have none. */
    public ProjectGroup getGroupFor(Project project) {
        for (ProjectGroup group : groups) {
            if (group.getProject().equals(project)) {
                return group;
            }
        }
        return null;
    }

    /** Only ProjectGroup calls this (that's why it isn't public). */
    void addGroup(ProjectGroup group) {
        groups.add(group);
    }

    /**
     * Makes a list of everything that is still missing.
     * An empty list means the programme is complete.
     */
    public List<String> getMissingRequirements() {
        List<String> missing = new ArrayList<>();

        // 1. Add up ECTS points per study area.
        Map<StudyArea, Integer> ectsPerArea = new HashMap<>();
        for (Activity activity : activities) {
            StudyArea area = activity.getStudyArea();
            ectsPerArea.put(area, ectsPerArea.getOrDefault(area, 0) + activity.getEcts());
        }

        // 2. Basic studies: the best basic programme must have at least 110 ECTS.
        StudyArea bestBasic = null;
        int bestBasicEcts = 0;
        for (Map.Entry<StudyArea, Integer> entry : ectsPerArea.entrySet()) {
            if (entry.getKey().isBasicStudies() && entry.getValue() > bestBasicEcts) {
                bestBasic = entry.getKey();
                bestBasicEcts = entry.getValue();
            }
        }
        if (bestBasic == null) {
            missing.add("No activities from a basic studies programme (needs "
                    + BASIC_ECTS_REQUIRED + " ECTS).");
        } else if (bestBasicEcts < BASIC_ECTS_REQUIRED) {
            missing.add("Basic studies " + bestBasic + " has " + bestBasicEcts + " ECTS, needs "
                    + BASIC_ECTS_REQUIRED + " (missing " + (BASIC_ECTS_REQUIRED - bestBasicEcts) + ").");
        }

        // 3. Subject modules: need two modules with at least 35 ECTS each.
        int fullModules = 0;
        for (Map.Entry<StudyArea, Integer> entry : ectsPerArea.entrySet()) {
            StudyArea area = entry.getKey();
            int ects = entry.getValue();
            if (area.isBasicStudies()) continue;
            if (ects >= SUBJECT_ECTS_REQUIRED) {
                fullModules++;
            } else {
                missing.add("Subject module " + area + " has " + ects + " ECTS, needs "
                        + SUBJECT_ECTS_REQUIRED + " (missing " + (SUBJECT_ECTS_REQUIRED - ects) + ").");
            }
        }
        if (fullModules < SUBJECT_MODULES_REQUIRED) {
            missing.add("Only " + fullModules + " subject module(s) with " + SUBJECT_ECTS_REQUIRED
                    + " ECTS, needs " + SUBJECT_MODULES_REQUIRED + ".");
        }

        // 4. Every project needs a project group.
        for (Activity activity : activities) {
            if (activity.needsProjectGroup() && getGroupFor((Project) activity) == null) {
                missing.add("No project group for project " + activity.getName() + ".");
            }
        }

        return missing;
    }

    /** Part 4: true if complete. Prints the status in plain words either way. */
    public boolean checkCompletion() {
        List<String> missing = getMissingRequirements();
        if (missing.isEmpty()) {
            System.out.println(student + " has completed the bachelor programme.");
            return true;
        }
        System.out.println(student + " has NOT completed the bachelor programme yet, because:");
        for (String reason : missing) {
            System.out.println("  - " + reason);
        }
        return false;
    }
}
