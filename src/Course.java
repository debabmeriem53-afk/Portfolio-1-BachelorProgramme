//Course is a kind of activity, therefor it inherits everything Activity has
public class Course extends Activity {

    public Course(String name, int ects, StudyArea area) {
        super(name, ects, area); //This runs Activity's constructor
    }
}
