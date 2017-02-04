package ovh.crow.beerfinder;

import android.content.Context;
import android.widget.Spinner;

/**
 * Created by frede on 01-02-2017.
 */

public class BeerExpert {

    BeerType getBrands(Spinner color, Context context) {
        //Get string array
        String[] array = context.getResources().getStringArray(R.array.beer_descriptions);

        // get selected in string array and it's position + description
        int pos = color.getSelectedItemPosition();
        String chosen = color.getSelectedItem().toString();
        String desc = array[pos];

        //Make beertype and add beers based on the chosen value
        BeerType type = new BeerType(chosen, desc);

        switch (chosen) {
            case "light":
                type.add("Amstel Light");
                type.add("Sam Adams Light");
                break;
            case "amber":
                type.add("Jack Amber");
                type.add("Red Moose");
                break;
            case "brown":
                type.add("Samuel Smith's Nut Brown Ale");
                type.add("Newburgh Brown Ale");
                break;
            case "dark":
                type.add("Guinness");
                type.add("Belhaven");
                break;
        }
        return type;
    }
}
