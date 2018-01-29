package app.calcounterapplication.com.turnsapp.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by idoed on 27/01/2018.
 */

public class Util {
    static String names[]={"Choose Name",
            "Choose Name",
            "Choose Name",
            "Choose Name",
            "Choose Name",
            "Choose Name",
            "Choose Name",
            "Choose Name"
    };
    public static List<Player> getListPerson() {
        List<Player> models = new ArrayList<>();
        models.add(new Player( names[0]));
        return models;
    }
}
