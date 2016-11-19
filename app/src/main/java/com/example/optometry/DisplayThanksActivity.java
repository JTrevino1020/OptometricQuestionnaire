package com.example.optometry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DisplayThanksActivity extends AppCompatActivity {

    private static final String SAVED_PASSWORD = "4447";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_thanks);
    }

    public void continueToResults(View view) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(getText(R.string.message_enter_password));

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setTransformationMethod(PasswordTransformationMethod.getInstance());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton(getText(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final String password = input.getText().toString();
                        if (!password.isEmpty()) {
                            if (password.equals(SAVED_PASSWORD)) {
                                Toast.makeText(getApplicationContext(),
                                        getText(R.string.message_password_matched), Toast.LENGTH_SHORT).show();
                                continueToResults();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        getText(R.string.message_wrong_password), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        alertDialog.setNegativeButton(getText(R.string.message_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    private void continueToResults() {

        Bundle bundle = getIntent().getBundleExtra(DisplayQuestionActivity.EXTRA_BUNDLE);

        Intent intent = new Intent(this, DisplayResultsActivity.class);
        intent.putExtra(DisplayQuestionActivity.EXTRA_BUNDLE, bundle);

        startActivity(intent);
    }
}
