public class Course extends Activity {

    public Course(String name, int ects, StudyArea studyArea) {
        super(name, ects, studyArea);
    }

    @Override
    public String toString() {
        return "Course: " + super.toString();
    }
}
