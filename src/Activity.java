public abstract class Activity {
    private final String name;
    private final int ects;
    private final StudyArea studyArea;

    protected Activity(String name, int ects, StudyArea studyArea) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("An activity must have a name");
        }
        if (ects <= 0) {
            throw new IllegalArgumentException("ECTS points must be positive");
        }
        if (studyArea == null) {
            throw new IllegalArgumentException("An activity must belong to a study area");
        }
        this.name = name;
        this.ects = ects;
        this.studyArea = studyArea;
    }

    public String getName() {
        return name;
    }

    public int getEcts() {
        return ects;
    }

    public StudyArea getStudyArea() {
        return studyArea;
    }

    public boolean needsProjectGroup() {
        return false;
    }

    @Override
    public String toString() {
        return name + " (" + ects + " ECTS, " + studyArea + ")";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Activity)) return false;
        return name.equals(((Activity) other).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
