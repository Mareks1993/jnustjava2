/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import java.text.NumberFormat;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;

import org.w3c.dom.Text;

import java.util.Locale;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int val = 0;
    int valRun = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val = getIntent().getIntExtra("keywalk",0);
        valRun = getIntent().getIntExtra("keyRun",0);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 0;
    private int minute = 0;
    private int second = 0;
    private int intervalTracker = 0;
    private int millisecond = 0;
    private boolean tracker = false;
    public void submitOrder(View view) {
        //String priceMessage = "Total: $" + (quantity*5) +"\nThank you!";

        if (tracker== false){
            tracker = true;
            displayMessage();
        }


    }
    public void increment(View view) {
        quantity++;
        display(quantity);
        displayPrice(quantity*5);
    }
    public void pauseTimer(View view) {
        if (tracker == true){
            tracker = false;
        }
    }
    public void setIntervals(View view) {
        Intent intent = new Intent(this, Interval.class);
        startActivity(intent);
    }
    public void decrement(View view) {
        quantity--;
        display(quantity);
        displayPrice(quantity*5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);


    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayMessage() {
        final TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        final Handler handler = new Handler();
        final TextView intervalTrackerTv = (TextView) findViewById(R.id.intervalTracker);
        handler.post(new Runnable() {
            public void run() {

                String stopWatch = "";
                if(tracker==true) {
                    second = ++second;
                    if(intervalTracker == 0){
                        intervalTrackerTv.setText("Walking");
                    }
                    else{
                        intervalTrackerTv.setText("Running");
                    }

                /*if (millisecond == 1000) {
                    millisecond = 0;
                    second = ++second;
                }*/
                    if (second == 60) {
                        second = 0;
                        minute = ++minute;
                        if(intervalTracker == 0 ){
                            intervalTracker = 1;

                        }
                        else{
                            intervalTracker = 0;
                        }
                    }
                    if (minute < 10) {
                        stopWatch = "0" + minute;
                    } else {
                        stopWatch = "" + minute;
                    }
                    if (second < 10) {
                        stopWatch = stopWatch + ":0" + second;
                    } else {
                        stopWatch = stopWatch + ":" + second;
                    }
               /* if (millisecond < 10) {
                    stopWatch = stopWatch + ":00" + millisecond;
                } else if (millisecond < 100) {
                    stopWatch = stopWatch + ":0" + (millisecond);
                } else {
                    stopWatch = stopWatch + ":" + millisecond;
                }*/
                    priceTextView.setText(stopWatch);
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }
}