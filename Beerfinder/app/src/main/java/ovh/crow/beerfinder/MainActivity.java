package ovh.crow.beerfinder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickFindBeer(View view) {
        TextView brands = (TextView) findViewById(R.id.textViewResults);
        TextView description = (TextView) findViewById(R.id.textViewDescriptions);
        Spinner color = (Spinner) findViewById(R.id.spinnerBeer);

        //BeerType
        //List<String> brands;
        //String Name
        //String Description
        BeerType beerType = expert.getBrands(color, this);

        StringBuilder brandsFormatted = new StringBuilder();
        for(String brand : beerType.getBrands()) {
            brandsFormatted.append(brand).append("\n");
        }
        //display
        brands.setText(brandsFormatted);
        description.setText(beerType.getDescription());
        description.setVisibility(View.VISIBLE);
    }
}
