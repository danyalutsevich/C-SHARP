package step.learning.armory;

public abstract class Weapon {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getCard();
}
