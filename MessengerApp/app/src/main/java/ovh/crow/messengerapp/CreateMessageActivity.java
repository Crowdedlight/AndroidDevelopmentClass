package ovh.crow.messengerapp;

import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.annotation.Target;

public class CreateMessageActivity extends AppCompatActivity {

    private static int ACTIVITY_REQUEST_ID_SEND_MESSAGE = 1;
    public static final String MESSAGERECIEVED = "msgRecieved";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
    }

    public void OnSendMessage(View view)
    {
        EditText etMsg = (EditText) findViewById(R.id.etMessage);
        Intent intent = new Intent(this, RecieveMessageActivity.class);
        intent.putExtra(RecieveMessageActivity.MESSAGE, etMsg.getText().toString());

        //clear input field
        etMsg.setText("");

        startActivityForResult(intent, ACTIVITY_REQUEST_ID_SEND_MESSAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_REQUEST_ID_SEND_MESSAGE) {
            switch (resultCode) {
                case RESULT_OK:
                    Log.v("app", "RESULT_OK from RecieveMessageActivity");

                    //show respondmsg
                    String respondMsg = data.getStringExtra(MESSAGERECIEVED);
                    String displayMsg;
                    if (respondMsg.equalsIgnoreCase("yes"))
                    {
                        displayMsg = "Message Recieved";
                    } else {
                        displayMsg = "Message Not Recieved";
                    }

                    View view = (View) findViewById(R.id.myCoordinatorLayout);
                    Snackbar mySnackbar = Snackbar.make(view, displayMsg, BaseTransientBottomBar.LENGTH_LONG);
                    mySnackbar.show();
                    break;
                case RESULT_CANCELED:
                    Log.v("app", "RESULT_CANCELED from RecieveMessageActivity");
                    break;
            }
        }
    }
}
