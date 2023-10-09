package step.learning.armory;

@Serializable
public class MachineGun extends Weapon implements Automatic{

    private double fireRate;

    public MachineGun(String name, double fireRate) {
        super.setName(name);
        this.setFireRate(fireRate);
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }


    @Override
    public String getCard() {
        return String.format("MachineGun: %s, fireRate: %.1f", super.getName(), this.getFireRate());
    }
}
