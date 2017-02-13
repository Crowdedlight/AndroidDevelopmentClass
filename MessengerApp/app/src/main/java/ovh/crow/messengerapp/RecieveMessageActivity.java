package ovh.crow.messengerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class RecieveMessageActivity extends AppCompatActivity {

    public static final String MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_message);

        Intent intent = getIntent();
        String msgText = intent.getStringExtra(MESSAGE);
        TextView msgView = (TextView) findViewById(R.id.twRecieveMessage);
        msgView.setText(msgText);
    }

    public void btnOKClicked(View view)
    {
        Intent intent = getIntent();

        RadioButton msgReciv = (RadioButton) findViewById(R.id.radioMsgRecieved);

        //check for checked button
        if (msgReciv.isChecked())
            intent.putExtra(CreateMessageActivity.MESSAGERECIEVED, "yes");
        else
            intent.putExtra(CreateMessageActivity.MESSAGERECIEVED, "no");

        setResult(RESULT_OK, intent);
        finish();
    }
}
