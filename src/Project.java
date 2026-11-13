public class Project extends Activity {

    public Project(String name, int ects, StudyArea studyArea) {
        super(name, ects, studyArea);
    }

    @Override
    public boolean needsProjectGroup() {
        return true;
    }

    @Override
    public String toString() {
        return "Project: " + super.toString();
    }
}
