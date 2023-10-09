package step.learning.armory;

import com.google.gson.JsonObject;

import java.util.MissingFormatArgumentException;

@Serializable
public class Gun extends Weapon {

    private int cartrige;

    public Gun(String name, int cartrige) {
        super.setName(name);
        this.setCartrige(cartrige);
    }

    public int getCartrige() {
        return cartrige;
    }

    public void setCartrige(int cartrige) {
        this.cartrige = cartrige;
    }


    @Override
    public String getCard() {
        return String.format("Gun: %s, cartrige: %d", super.getName(), this.getCartrige());
    }

    public static Gun fromJson(JsonObject jsonObject) {

        String[] requiredFileds = {"name", "cartrige"};

        for (String field : requiredFileds) {
            if (!jsonObject.has(field)) {
                throw new MissingFormatArgumentException("Missing " + field + " field");
            }
        }


        return new Gun(jsonObject.get("name").getAsString(), jsonObject.get("cartrige").getAsInt());
    }
}
