//abstract means you can't create StudyArea directly. You can only use one of its subclasses.
public abstract class StudyArea {
    private final String name; // this creates a field

    protected StudyArea(String name) { // this is the constructor that runs when a study area is created

        //name == null means no text was given at all, || means or the text is empty
        if (name == null || name.isBlank()) { // this checks whether the name is bad

            //illegalArgument... means you passed a value that is not allowed
            throw new IllegalArgumentException("A study area must have a name");//this only runs if the name was bad
        }

        //name after the equal sign is the parameter that was passed into the constructor
        this.name = name; // this is the field from earlier
    }

    //anyone can call it. it gives back text. the methods name is getName(), return gives back the value of the field.
    public String getName() { //this is a getter method
        return name;
    }

    public abstract boolean isBasicStudies();

    @Override
    public String toString() {
        return name;
    }
}

