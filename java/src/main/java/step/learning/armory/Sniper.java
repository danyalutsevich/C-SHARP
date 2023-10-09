package step.learning.armory;

public class Sniper extends Weapon {
    private double range;

    public Sniper(String name, double range) {
        super.setName(name);
        this.range = range;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    @Override
    public String getCard() {
        return String.format("Sniper: %s, caliber: %.2f", super.getName(), this.getRange());
    }
}
