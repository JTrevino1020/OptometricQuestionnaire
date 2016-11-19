package com.example.optometry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSurvey(View view) {

        Integer startQuestionNumber = 1;
        Intent intent = new Intent(this, DisplayQuestionActivity.class);

        Bundle bundle = new Bundle();

        bundle.putString(DisplayQuestionActivity.EXTRA_NEXT_QUESTION, startQuestionNumber.toString());
        intent.putExtra(DisplayQuestionActivity.EXTRA_BUNDLE, bundle);

        startActivity(intent);
    }
}
