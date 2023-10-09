package step.learning.armory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Armory {
    private List<Weapon> weapons;

    public Armory() {
        weapons = new ArrayList<>();
    }

    public void save() {
        String path = URLDecoder.decode(this.getClass().getClassLoader().getResource("./").getPath());
        String weaponsJson = new Gson().toJson(weapons);

        try (FileWriter writer = new FileWriter(path + "armory.json")) {
            writer.write(weaponsJson);

        } catch (Exception ex) {
        }
    }

    public void load() {
        String file = this.getClass().getClassLoader().getResource("armory.json").getFile();
        System.out.println(file.toString());
        try (FileReader reader = new FileReader(file)) {
            JsonArray armory = JsonParser.parseReader(reader).getAsJsonArray();

        } catch (Exception ex) {
        }
    }

//    public List<Weapon> getSerializable() {

//        List<Weapon> result = new List<Weapon>();
//
//        for (Weapon weapon : weapons) {
//            weapon.getClass().getAnnotation();
//        }


//    }

    public void add(Weapon weapon) {
        weapons.add(weapon);
    }

    public void remove(Weapon weapon) {
        weapons.remove(weapon);
    }

    public void print() {
        for (Weapon weapon : weapons) {
            System.out.println(weapon.getCard());
        }
    }

    public <T> void printByInterface(Class<T> interFace) {
        for (Weapon weapon : this.weapons) {
            if (interFace.isInstance(weapon)) {
                System.out.println(weapon.getCard());
            }
            Annotation[] annotations = weapon.getClass().getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == interFace) {
                    System.out.println(weapon.getCard());
                }
            }
        }
    }

    public <T> void printByNonInterface(Class<T> interFace) {
        for (Weapon weapon : this.weapons) {
            if (!interFace.isInstance(weapon)) {
                System.out.println(weapon.getCard());
            }
        }
    }
}
