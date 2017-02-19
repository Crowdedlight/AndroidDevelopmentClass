package ovh.crow.onearmedbandit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ImageButton;
import android.widget.TextView;

public class BettingActivity extends AppCompatActivity {

    //image buttons
    private ImageButton euro1;
    private ImageButton euro2;
    private ImageButton euro5;
    private ImageButton euro10;
    private ImageButton euro20;
    private TextView currBet;

    //current bet amount
    private int betAmount = 0;
    static String BETTING_MESSAGE = "betting_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betting);

        //set buttons
        euro1 = (ImageButton) findViewById(R.id.euro1);
        euro2 = (ImageButton) findViewById(R.id.euro2);
        euro5 = (ImageButton) findViewById(R.id.euro5);
        euro10 = (ImageButton) findViewById(R.id.euro10);
        euro20 = (ImageButton) findViewById(R.id.euro20);

        currBet = (TextView) findViewById(R.id.tvCurrBet);
        currBet.setText(Integer.toString(betAmount));
    }

    public void clear(View view) {
        betAmount = 0;
        currBet.setText(Integer.toString(betAmount));
    }

    public void bet(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(BETTING_MESSAGE, betAmount);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void AddBetAmount(View view) {
        switch(view.getId()) {
            case R.id.euro1:
                betAmount += 1;
                break;
            case R.id.euro2:
                betAmount += 2;
                break;
            case R.id.euro5:
                betAmount += 5;
                break;
            case R.id.euro10:
                betAmount += 10;
                break;
            case R.id.euro20:
                betAmount += 20;
                break;
        }

        //update textview
        currBet.setText(Integer.toString(betAmount));
    }
}
