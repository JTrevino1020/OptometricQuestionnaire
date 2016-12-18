package com.example.optometry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DisplayReviewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_review);

        LinearLayout sectionReviewResponses = (LinearLayout) findViewById(R.id.section_review_responses);

        final Bundle bundle = getIntent().getBundleExtra(DisplayQuestionActivity.EXTRA_BUNDLE);

        for (Integer questionNumber = 1; questionNumber <= 15; questionNumber++) {

            final String questionNumberString = questionNumber.toString();

            final String question = questionNumberString + ". " + getText(DisplayQuestionActivity.map.get(questionNumber));
            TextView textView = new TextView(this);
            textView.setText(question);
            textView.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);

            sectionReviewResponses.addView(textView);

            final List<String> spinnerArray = createSpinnerArray();

            final String userChoice = bundle.getString(questionNumberString);
            final int userChoiceIndex = spinnerArray.indexOf(userChoice);

            final Spinner spinner = new Spinner(this);

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArray);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerArrayAdapter);
            spinner.setSelection(userChoiceIndex);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    String selectedItem = spinner.getSelectedItem().toString();

                    bundle.putString(questionNumberString, selectedItem);

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });


            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            spinner.setLayoutParams(params);

            sectionReviewResponses.addView(spinner);
        }
    }

    private List<String> createSpinnerArray() {

        final List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add(getText(R.string.choice_1).toString());
        spinnerArray.add(getText(R.string.choice_2).toString());
        spinnerArray.add(getText(R.string.choice_3).toString());
        spinnerArray.add(getText(R.string.choice_4).toString());
        spinnerArray.add(getText(R.string.choice_5).toString());

        return spinnerArray;
    }

    public void submitSurvey(View view) {

        Bundle bundle = getIntent().getBundleExtra(DisplayQuestionActivity.EXTRA_BUNDLE);

        Intent intent = new Intent(this, DisplayThanksActivity.class);
        intent.putExtra(DisplayQuestionActivity.EXTRA_BUNDLE, bundle);

        startActivity(intent);
    }


}
