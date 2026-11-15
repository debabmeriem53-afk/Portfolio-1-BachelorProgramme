// A project is an activity that needs a project group, which also inherits from Activity
public class Project extends Activity {

    public Project(String name, int ects, StudyArea area) {
        super(name, ects, area);
    }

    // Overrides the method from Activity: projects DO need a group.
    @Override
    public boolean needsGroup() {
        return true;
    }
}
