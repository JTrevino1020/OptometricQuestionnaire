package com.example.optometry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class DisplayResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(DisplayQuestionActivity.EXTRA_BUNDLE);

        final int numericResult = getNumericResult(bundle);

        TextView numericResultTextView = (TextView) findViewById(R.id.message_numeric_result);
        numericResultTextView.setText(String.format(Locale.getDefault(), "%d", numericResult));


        TextView stringResultTextView = (TextView) findViewById(R.id.message_string_result);
        if (numericResult <= 15) {
            stringResultTextView.setText(R.string.message_test_pass);
        } else {
            stringResultTextView.setText(R.string.message_test_fail);
        }
    }

    private int getNumericResult(final Bundle bundle) {

        int sum = 0;
        for (Integer questionNumber = 1; questionNumber <= 15; questionNumber++) {
            final String questionResponse = bundle.getString(questionNumber.toString());
            sum += weightFactor(questionResponse);
        }

        return sum;
    }

    private int weightFactor(final String response) {
        if (response.equals(getText(R.string.choice_5).toString())) {
            return 4;
        } else if (response.equals(getText(R.string.choice_4).toString())) {
            return 3;
        } else if (response.equals(getText(R.string.choice_3).toString())) {
            return 2;
        } else if (response.equals(getText(R.string.choice_2).toString())) {
            return 1;
        } else {
            return 0;
        }
    }

    public void restart(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getText(R.string.message_restart))
                .setCancelable(false)
                .setPositiveButton(getText(R.string.message_confirm), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishRestart();
                    }
                })
                .setNegativeButton(getText(R.string.message_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void finishRestart() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
