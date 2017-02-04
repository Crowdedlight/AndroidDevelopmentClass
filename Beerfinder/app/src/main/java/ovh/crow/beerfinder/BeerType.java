package ovh.crow.beerfinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frede on 04-02-2017.
 */

public class BeerType {
    public List<String> brands;
    public String name;
    public String description;

    public BeerType(String name, String description)
    {
        this.brands = new ArrayList<String>();
        this.name = name;
        this.description = description;
    }

    public void add(String brand)
    {
        this.brands.add(brand);
    }
}
