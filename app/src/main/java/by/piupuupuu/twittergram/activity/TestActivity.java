package by.piupuupuu.twittergram.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import by.piupuupuu.twittergram.MainActivity;
import by.piupuupuu.twittergram.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.textView);

        textView.setText(intent.getStringExtra(MainActivity.NICKNAME_KEY));
    }
}
