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
        Spinner color = (Spinner) findViewById(R.id.spinnerBeer);

        String chosen = String.valueOf(color.getSelectedItem());

        List<String> brandsList = expert.getBrands(chosen);

        StringBuilder brandsFormatted = new StringBuilder();

        for(String brand : brandsList) {
            brandsFormatted.append(brand).append("\n");
        }
        //display
        brands.setText(brandsFormatted);
    }
}
