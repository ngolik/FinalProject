package by.golik.finalproject.entity;

/**
 * @author Nikita Golik
 */
public enum Role {
    ADMINISTRATOR("администратор"),
    MODERATOR("модератор"),
    USER("пользователь"),
    GUEST("гость");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }
}
