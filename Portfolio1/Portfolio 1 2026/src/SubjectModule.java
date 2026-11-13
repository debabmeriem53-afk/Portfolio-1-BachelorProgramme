public class SubjectModule extends StudyArea {

    public SubjectModule(String name) {
        super(name);
    }

    @Override
    public boolean isBasicStudies() {
        return false;
    }
}
