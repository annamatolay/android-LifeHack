package app.zionroad.com.lifehack;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static String TAG = ">>> ¤#¤ >>>";

    TextView mDateView, mRemainingTimeView, mLoggedTimeView;
    EditText mTimeEditText;
    Spinner mTimeType;
    Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDateView = (TextView) findViewById(R.id.date);
        mRemainingTimeView = (TextView) findViewById(R.id.remainingTime);
        mLoggedTimeView = (TextView) findViewById(R.id.loggedTime);
        mTimeEditText = (EditText) findViewById(R.id.timeEditText);
        mTimeType = (Spinner) findViewById(R.id.timeType);
        mSubmitButton = (Button) findViewById(R.id.submitButton);

        // Setting the current date as text on the screen
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy. MMM. dd.", Locale.getDefault());
        String formattedDate = df.format(c.getTime());
        mDateView.setText(formattedDate);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timeType, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mTimeType.setAdapter(adapter);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            String action = mTimeType.getSelectedItem().toString();

            @Override
            public void onClick(View view) {
                try{
                    Integer hour = Integer.valueOf(mTimeEditText.getText().toString());
                    Log.d(TAG, "HOUR: "+String.valueOf(hour)+" ACTION: "+action);
                    Integer remaining = Integer.valueOf(mRemainingTimeView.getText().toString());
                    Integer logged = Integer.valueOf(mLoggedTimeView.getText().toString());
                    Integer i = remaining-hour;
                    if (i<0) {
                        Toast.makeText(getApplicationContext(), "The given number is too much!",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mRemainingTimeView.setText(String.valueOf(remaining-hour));
                    mLoggedTimeView.setText(String.valueOf(logged+hour));
                }
                catch (NumberFormatException e ) {
                    Toast.makeText(getApplicationContext(), "INVALID NUMBER!",
                            Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
                mTimeEditText.setText("");
            }
        });
    }
}
