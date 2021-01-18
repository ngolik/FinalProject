package by.golik.finalproject.entity;

/**
 * @author Nikita Golik
 */
public enum Position {
    PRODUCER("producer"),
    DIRECTOR("director"),
    ACTOR("actor");
    private String position;

    private Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Position getByIdentity(Integer identity) {
        return Position.values()[identity];
    }
}
