public abstract class Activity {

    //every activity has a name, a number of ects points and a study area
    private final String name;
    private final int ects;
    private final StudyArea area;

    //constructor stores the three values
    public Activity(String name, int ects, StudyArea area) {
        this.name = name;
        this.ects = ects;
        this.area = area;
    }

    //getters so the other classes can read the information
    public String getName() {
        return name;
    }

    public int getEcts() {
        return ects;
    }

    public StudyArea getArea() {
        return area;
    }

    //method that asks if this activity needs a project group
    public boolean needsGroup() {
        return false;
    }
}
