package step.learning.armory;

@Serializable
@Rifled
public class Rifle extends Weapon implements Classified{

    private float caliber;

    public Rifle(String name, float caliber) {
        super.setName(name);
        this.caliber = caliber;
    }

    public float getCaliber() {
        return caliber;
    }

    public void setCaliber(float caliber) {
        this.caliber = caliber;
    }

    @Override
    public String getLevel() {
        return "Level: 3";
    }

    @Override
    public String getCard() {
        return String.format("Rifle: %s, caliber: %.2f", super.getName(), this.getCaliber());
    }
}
