package com.example.mbella.helloworldapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.taplytics.sdk.Taplytics;
import com.taplytics.sdk.TaplyticsPushNotificationIntentListener;
import com.taplytics.sdk.TaplyticsPushNotificationListener;
import com.taplytics.sdk.TaplyticsPushOpenedListener;
import com.taplytics.sdk.TaplyticsVar;
import com.taplytics.sdk.TaplyticsVarListener;


import com.noveogroup.android.log.Logger;
import com.noveogroup.android.log.LoggerManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    // get a logger instance by class
    private static final Logger logger = LoggerManager.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logger.i("Entered MainActivity::onCreate");

        // Insert the following line of code to initialize the Taplytics SDK
//        Taplytics.startTaplytics(this, "9ddb1b771f5782dcf9c093e042c08f2ba587975f");

        HashMap<String, Object> options = new HashMap<>();
        options.put("shakeMenu", true);
        options.put("turnMenu", true);
        Taplytics.startTaplytics(this, "9ddb1b771f5782dcf9c093e042c08f2ba587975f", options);

        Taplytics.addPushNotificationListener(new TaplyticsPushNotificationListener() {
            @Override
            public void pushReceived(JSONObject customData) throws JSONException {
                logger.i("Taplytics push received: %s", customData.toString());
            }
        });

//        Taplytics.setPushNotificationIntentListener(new TaplyticsPushNotificationIntentListener() {
//            @Override
//            public Intent setPushNotificationIntent(JSONObject customData) throws JSONException {
//                //return your own custom intent (CustomData is the custom keys from the dashboard)
//            }
//        });

        Taplytics.setPushNotificationOpenedListener(new TaplyticsPushOpenedListener() {
            @Override
            public void pushOpened(Bundle bundle) {
                //The push has been opened. Bundle is the extras associated with the intent.
                logger.i("Taplytics push opened: %s", bundle.toString());
            }
        });


        TaplyticsVar<String> var = new TaplyticsVar<>("test1", "", new TaplyticsVarListener() {
            @Override
            public void variableUpdated(Object value) {
                // Update your interface/functionality with new value.
            }
        });


        // set user attributes

        JSONObject attributes = new JSONObject();

        try {
            attributes.put("email", "waqqas.jabbar@gmail.com");
            attributes.put("user_id", "1");
            attributes.put("firstname", "Waqqas");
            attributes.put("lastname", "Jabbar");
            attributes.put("age", 37);
            attributes.put("gender", "male");

            Taplytics.setUserAttributes(attributes);

        } catch (JSONException e) {
            logger.e("JSONException occured", e);

        }

        Taplytics.logEvent("test");

        setContentView(R.layout.activity_main);
    }
}
