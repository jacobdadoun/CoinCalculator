package com.example.coincounterjd;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CoinCounter cCounter;

    private EditText pennyInput;
    private EditText nickelInput;
    private EditText dimeInput;
    private EditText quarterInput;

    private FloatingActionButton fab;

    private TextView totalOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar init
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ExitTexts from content_main.xml
        pennyInput = findViewById(R.id.pennyEditText);
        nickelInput = findViewById(R.id.nickelEditText);
        dimeInput = findViewById(R.id.dimeEditText);
        quarterInput = findViewById(R.id.quarterEditText);

        fab = findViewById(R.id.fab);

        // The output textView
        totalOutput = (TextView) findViewById(R.id.statusBarTextView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cCounter = new CoinCounter();
                // calculate coins and print to Status Bar
                updateStatusBar(calculateCoins());
            }
        });

    }


    private int calculateCoins(){
        System.err.println("PENNIES: " + pennyInput.getText().toString());
        System.err.println("NICKLES: " + nickelInput.getText().toString());
        System.err.println("DIMES: " + dimeInput.getText().toString());
        System.err.println("QUARTERS: " + quarterInput.getText().toString());

        if(pennyInput != null){
            cCounter.setCountOfPennies(pennyInput.getText().toString());
        }

        if(nickelInput != null){
            cCounter.setCountOfNickels(nickelInput.getText().toString());
        }

        if(dimeInput != null){
            cCounter.setCountOfDimes(dimeInput.getText().toString());
        }

        if(quarterInput != null){
            cCounter.setCountOfQuarters(quarterInput.getText().toString());
        }

        return cCounter.getCentsValueTotal();
    }

    private void updateStatusBar(int coinsTotal){
        // Print to our Status bar
        totalOutput.setText(String.format(Locale.getDefault(), "%s: %d %s",
                "Total Change: ", coinsTotal, "Cents"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_clearAll: {
                startNextNewGame();
                return true;
            }
            case R.id.about_tab: {
                Utils.showInfoDialog (MainActivity.this, R.string.about_tab_string, R.string.about_text);
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startNextNewGame() {
        cCounter = new CoinCounter();
        updateUI();
    }

    private void updateUI() {
        totalOutput.setText(
                String.format(Locale.getDefault(), "%s",
                        "JD"));
    }

}