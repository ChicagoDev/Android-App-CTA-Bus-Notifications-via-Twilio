package io.chicagoan.ctawaittime;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class BusWaitTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_wait_time_activity);

        ActionBar barToHideSoAppGoesFullscreen = getSupportActionBar();
        barToHideSoAppGoesFullscreen.hide();

        final Button mainButton = (Button) findViewById(R.id.btnGetWaitTimes);

        final SmsManager smsManager = SmsManager.getDefault();

        final EditText busStopNumber = (EditText) findViewById(R.id.editText);

        final TextView welcome = (TextView) findViewById(R.id.tvWelcome);

        final TelephonyManager telephonyManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);

        final SmsStateListener stateListener = new SmsStateListener();

        telephonyManager.listen(stateListener, PhoneStateListener.LISTEN_SERVICE_STATE);


        mainButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String busNum = busStopNumber.getText().toString();

                int servState;
                servState = stateListener.getState();


                // We can send a text message
                if (servState != ServiceState.STATE_EMERGENCY_ONLY && servState != ServiceState.STATE_POWER_OFF) {



                    if (isValidBusNumber(busNum)){

                        smsManager.sendTextMessage("41411",null, busNum,null,null);
                        MessageSentAlert sent = new MessageSentAlert();
                        sent.show(getFragmentManager(),"SMS_SENT");

                    }

                    else {

                        InvalidBusStopAlert invalidAlert = new InvalidBusStopAlert();
                        invalidAlert.show(getFragmentManager(),"BAD_INPUT");
                    }


                } else { //Turn your phone radio back on

                    NoCellServiceAlert noCellServiceAlert = new NoCellServiceAlert();
                    noCellServiceAlert.show(getFragmentManager(),"NO_CELL");

                }


            }
        });


    }

    public boolean isValidBusNumber(String shouldBeNumberString) {

        // TODO: 8/29/15 REGEX testing for alphabetic chars
        if (shouldBeNumberString.length() > 0) {
            return true;
        }
        return false;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bus_wait_time, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

/*
* Background based on an image by Chris Smith
* Liscensed under Creative Commons: https://creativecommons.org/licenses/by-nc-sa/2.0/
* Link: https://www.flickr.com/photos/cjsmithphotography/6768289679/
*
*
* */
