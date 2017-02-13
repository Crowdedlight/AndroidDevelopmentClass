package ovh.crow.beerfinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frede on 04-02-2017.
 */

public class BeerType {
    private List<String> brands;
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getBrands() {
        return brands;
    }

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
