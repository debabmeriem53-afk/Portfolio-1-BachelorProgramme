public class StudyArea {
    private final String name;
    private final boolean basicStudies;

    //This is the constructor
    public StudyArea(String name, boolean basicStudies) {
        this.name = name;
        this.basicStudies = basicStudies;
    }

    //These are getter methods
    public String getName() {
        return name;
    }

    public boolean isBasic() {
        return basicStudies;
    }
}
