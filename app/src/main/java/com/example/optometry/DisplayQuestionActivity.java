package com.example.optometry;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class DisplayQuestionActivity extends AppCompatActivity {

    public final static String EXTRA_NEXT_QUESTION = "com.example.optometry.NEXT_QUESTION";
    public final static String EXTRA_BUNDLE = "com.example.optometry.BUNDLE";
    public final static Map<Integer, Integer> map = getMap();

    private Integer questionNumber = null;

    private static Map<Integer, Integer> getMap() {

        final Map<Integer, Integer> map = new HashMap<>();

        map.put(1, R.string.test_1_question_1);
        map.put(2, R.string.test_1_question_2);
        map.put(3, R.string.test_1_question_3);
        map.put(4, R.string.test_1_question_4);
        map.put(5, R.string.test_1_question_5);
        map.put(6, R.string.test_1_question_6);
        map.put(7, R.string.test_1_question_7);
        map.put(8, R.string.test_1_question_8);
        map.put(9, R.string.test_1_question_9);
        map.put(10, R.string.test_1_question_10);
        map.put(11, R.string.test_1_question_11);
        map.put(12, R.string.test_1_question_12);
        map.put(13, R.string.test_1_question_13);
        map.put(14, R.string.test_1_question_14);
        map.put(15, R.string.test_1_question_15);

        return map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_question);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(DisplayQuestionActivity.EXTRA_BUNDLE);

        String questionString = bundle.getString(DisplayQuestionActivity.EXTRA_NEXT_QUESTION);
        questionNumber = Integer.parseInt(questionString);

        TextView messageQuestion = (TextView) findViewById(R.id.message_question);
        messageQuestion.setText(getText(R.string.message_question) + " #" + questionString);

        TextView questionText = (TextView) findViewById(R.id.message_question_text);
        questionText.setText(getText(map.get(questionNumber)));
    }

    public void enableNextButton(View view) {
        Button nextButton = (Button) findViewById(R.id.button_next);
        nextButton.setEnabled(true);
    }

    public void nextPage(View view) {

        final Bundle currentBundle = getIntent().getBundleExtra(DisplayQuestionActivity.EXTRA_BUNDLE);

        RadioGroup radioButtonGroup = (RadioGroup) findViewById(R.id.radio_group);
        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
        Button radioButton = (Button) radioButtonGroup.findViewById(radioButtonID);

        currentBundle.putString(questionNumber.toString(), radioButton.getText().toString());

        Intent intent;
        if (questionNumber >= 15) {
            currentBundle.putString(EXTRA_NEXT_QUESTION, null);
            intent = new Intent(this, DisplayReviewActivity.class);

        } else {
            final Integer newQuestionNumber = questionNumber + 1;
            currentBundle.putString(EXTRA_NEXT_QUESTION, newQuestionNumber.toString());
            intent = new Intent(this, DisplayQuestionActivity.class);
        }

        intent.putExtra(EXTRA_BUNDLE, currentBundle);
        startActivity(intent);
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
