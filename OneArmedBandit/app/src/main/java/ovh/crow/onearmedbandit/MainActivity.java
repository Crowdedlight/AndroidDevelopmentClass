package ovh.crow.onearmedbandit;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //static for communication with betting
    private static int ACTIVITY_REQUEST_ID_BETTING = 1;

    //Get holders to imageviews
    private ImageView fruitAnim1;
    private ImageView fruitAnim2;
    private ImageView fruitAnim3;
    private AnimationDrawable fruitAnimation1;
    private AnimationDrawable fruitAnimation2;
    private AnimationDrawable fruitAnimation3;
    private TextView currBet;

    private int betAmount = 0;

    //Get start button
    private Button btnStart, btnStop1, btnStop2, btnStop3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set variables
        fruitAnim1 = (ImageView) findViewById(R.id.fruitAni1);
        fruitAnim2 = (ImageView) findViewById(R.id.fruitAni2);
        fruitAnim3 = (ImageView) findViewById(R.id.fruitAni3);
        fruitAnimation1 = (AnimationDrawable) fruitAnim1.getBackground();
        fruitAnimation2 = (AnimationDrawable) fruitAnim2.getBackground();
        fruitAnimation3 = (AnimationDrawable) fruitAnim3.getBackground();

        currBet = (TextView) findViewById(R.id.twCurrBet);
        currBet.setText(Integer.toString(betAmount));

        //Get buttons
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop1 = (Button) findViewById(R.id.btnStop1);
        btnStop2 = (Button) findViewById(R.id.btnStop2);
        btnStop3 = (Button) findViewById(R.id.btnStop3);

        //button styles before starting game
        btnStop1.getBackground().setColorFilter(Color.parseColor("#68ad6a"), PorterDuff.Mode.LIGHTEN);
        btnStop1.setEnabled(false);
        btnStop2.getBackground().setColorFilter(Color.parseColor("#68ad6a"), PorterDuff.Mode.LIGHTEN);
        btnStop2.setEnabled(false);
        btnStop3.getBackground().setColorFilter(Color.parseColor("#68ad6a"), PorterDuff.Mode.LIGHTEN);
        btnStop3.setEnabled(false);
    }

    public void StopWheel(View view) {

        switch(view.getId())
        {
            case R.id.btnStop1:
                //stop ani1
                if (fruitAnimation1.isRunning()) {
                    fruitAnimation1.stop();
                    btnStop1.getBackground().setColorFilter(Color.parseColor("#68ad6a"), PorterDuff.Mode.LIGHTEN);
                    btnStop1.setEnabled(false);
                }
                break;
            case R.id.btnStop2:
                //stop ani1
                fruitAnimation2.stop();
                btnStop2.getBackground().setColorFilter(Color.parseColor("#68ad6a"), PorterDuff.Mode.LIGHTEN);
                btnStop2.setEnabled(false);
                break;

            case R.id.btnStop3:
                //stop ani1
                fruitAnimation3.stop();
                btnStop3.getBackground().setColorFilter(Color.parseColor("#68ad6a"), PorterDuff.Mode.LIGHTEN);
                btnStop3.setEnabled(false);
                break;
        }

        //check if last animation stopped
        if (!fruitAnimation1.isRunning() && !fruitAnimation2.isRunning() && !fruitAnimation3.isRunning()) {
            //call method to check winnings
            CheckWin();
        }
    }

    public void CheckWin() {
        //check if won, calculate bet and so on
        int framePos1 = -1;
        int framePos2 = -2;
        int framePos3 = -3;

        Drawable currFrame1 = fruitAnimation1.getCurrent();
        Drawable currFrame2 = fruitAnimation2.getCurrent();
        Drawable currFrame3 = fruitAnimation3.getCurrent();

        for(int i = 0; i < fruitAnimation1.getNumberOfFrames(); i++) {
            if (currFrame1 == fruitAnimation1.getFrame(i)) {
                framePos1 = i+1;
                Log.d("winCheck", "FramePos1 = " + i + ", after correction = " + framePos1);
            }

            if (currFrame2 == fruitAnimation2.getFrame(i)) {
                framePos2 = i+1+2;
                if (framePos2 > 10) framePos2 -= 10;
                Log.d("winCheck", "FramePos2 = " + i + ", after correction = " + framePos2);
            }

            if (currFrame3 == fruitAnimation3.getFrame(i)) {
                framePos3 = i+1+4;
                if (framePos3 > 10) framePos3 -= 10;
                Log.d("winCheck", "FramePos3 = " + i + ", after correction = " + framePos3);
            }

            //if all is found, stop compareing
            if (framePos1 != -1 && framePos2 != -2 && framePos3 != -3)
                break;
        }

        //if 3 equals == big win, if 2 equals == half win // Split 2 equals into 3 ifs instead of having in same if, to see which two is equal
        String msg = "";
        if (framePos1 == framePos2 && framePos1 == framePos3) {
            //3 equals = 50 * bet
            msg = String.format(getResources().getString(R.string.threeEqual), 50 * betAmount);
        } else if (framePos1 == framePos2) {
            //2 equals = 5 * bet
            msg = String.format(getResources().getString(R.string.twoEquals), 1, 2, 5 * betAmount);
        } else if (framePos1 == framePos3) {
            //2 equals = 5 * bet
            msg = String.format(getResources().getString(R.string.twoEquals), 1, 3, 5 * betAmount);
        } else if (framePos2 == framePos3) {
            //2 equals = 5 * bet
            msg = String.format(getResources().getString(R.string.twoEquals), 2, 3, 5 * betAmount);
        } else {
            //no win
            msg = String.format(getResources().getString(R.string.nonEqual));
        }

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();

        //reset bet amount
        betAmount = 0;

        //enable start button for next round
        btnStart.setEnabled(true);
        btnStart.getBackground().clearColorFilter();
    }

    public void StartRound(View view) {
        //disable btn
        Log.d("events", "Start round has been clicked");
        btnStart.setEnabled(false);
        btnStart.getBackground().setColorFilter(Color.parseColor("#68ad6a"), PorterDuff.Mode.LIGHTEN);

        btnStop1.setEnabled(true);
        btnStop1.getBackground().clearColorFilter();

        btnStop2.setEnabled(true);
        btnStop2.getBackground().clearColorFilter();

        btnStop3.setEnabled(true);
        btnStop3.getBackground().clearColorFilter();

        fruitAnimation1.start();
        fruitAnimation2.start();
        fruitAnimation3.start();
    }

    public void ShowBetting(View view) {
        Intent intent = new Intent(this, BettingActivity.class);
        startActivityForResult(intent, ACTIVITY_REQUEST_ID_BETTING);
    }

    @Override
    public void onResume() {
        super.onResume();
        currBet.setText(Integer.toString(betAmount));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_ID_BETTING) {
            switch (resultCode) {
                case RESULT_OK:
                    Log.v("app", "RESULT_OK from RecieveMessageActivity");

                    //show respondmsg
                    betAmount = data.getIntExtra(BettingActivity.BETTING_MESSAGE, 0);
                    break;
                case RESULT_CANCELED:
                    Log.v("app", "RESULT_CANCELED from RecieveMessageActivity");
                    break;
            }
        }
    }
}
