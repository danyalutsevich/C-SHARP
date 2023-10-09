package step.learning.armory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShootingRange {
    public void run() {
//        gson();
        Armory armory = new Armory();

        armory.add(new Gun("Glock 19", 17));
        armory.add(new MachineGun("ДШК", 600));
        armory.add(new Rifle("Mauser 98k", 7.92f));
        armory.add(new Sniper("Barrett M82A1",1800));
        armory.print();

        System.out.println("--------- Automatic ---------");
        armory.printByInterface(Automatic.class);

        System.out.println("\n\n--------- NonAutomatic ---------");
        armory.printByNonInterface(Automatic.class);

        System.out.println("\n\n--------- Classified ---------");
        armory.printByInterface(Classified.class);

        System.out.println("\n\n--------- Rifled ---------");
        armory.printByInterface(Rifled.class);

        System.out.println("\n\n--------- Used ---------");
        armory.printByInterface(Used.class);

        armory.save();
        armory.load();
    }

    public void gson() {

        String json = "{\"name\":\"Glock\",\"cartrige\":10}";
        System.out.println(new Gson().fromJson(json, Gun.class).getCard());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Gun gun = new Gun("Glock 19", 17);

        String gunJson = gson.toJson(gun);
        System.out.println(gunJson);
        try (
                InputStreamReader reader = new InputStreamReader(
                        Objects.requireNonNull(
                                this.getClass().getClassLoader().getResourceAsStream("glock.json"))
                )) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            Weapon weapon = null;

            if (jsonObject.has("cartrige")) {
                weapon = new Gun(jsonObject.get("name").getAsString(), jsonObject.get("cartrige").getAsInt());
            }
            if (jsonObject.has("fireRate")) {
                weapon = new MachineGun(jsonObject.get("name").getAsString(), jsonObject.get("fireRate").getAsDouble());
            }
            if (jsonObject.has("caliber")) {
                weapon = new Rifle(jsonObject.get("name").getAsString(), jsonObject.get("cartrige").getAsFloat());
            }
            System.out.println("Weapon from resource:\n" + weapon.getCard());

        } catch (IOException ex) {
            System.out.println("IO Error" + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Resource not found " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}