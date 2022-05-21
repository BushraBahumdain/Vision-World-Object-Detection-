package com.example.visionworldopencv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class WelcomePage extends AppCompatActivity {
    public static TextToSpeech ttsM;
    String WelcomeText = "Welcome to Vision World.." +
            " Object detection recognizes the objects around you. " +
            "and informs you of the object name through a voice-over. " +
            "First, click on the Get Object Detection button and allow your camera access. " +
            "After that, you just need to point your back-front camera to the wanted object and the program will recognize it for you." +
            " Click on the get started button at the bottom of the screen.";
    String prevStarted = "yes";
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        } else {
            WelcomeText = null;
            moveToSecondary();
        }
       // CameraActivity.tts.speak(WelcomeText, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

            ttsM = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR)
                        ttsM.setLanguage(Locale.US);
                    ttsM.speak(WelcomeText, TextToSpeech.QUEUE_FLUSH, null);

                }
            });

        Button button = (Button) findViewById(R.id.GetStartedButton);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(WelcomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
       // CameraActivity.tts.speak(WelcomeText, TextToSpeech.QUEUE_FLUSH, null);
    }
    public void moveToSecondary(){
        // use an intent to travel from one activity to another.
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}