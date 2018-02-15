package com.example.matheus.speechrecognizersample;

import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class Sample extends AppCompatActivity implements RecognitionListener{

    Button record_btn;
    TextView speech_result;

    private SpeechRecognizer speechRecognizer;
    private Intent recognizerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        record_btn = findViewById(R.id.record_btn);
        speech_result = findViewById(R.id.speech_result);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(Sample.this);
        speechRecognizer.setRecognitionListener(Sample.this);
        // Configure the recognizer
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE,RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, Sample.this.getPackageName()); // Replace by your package.
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt_BR");

        SamplePermissionsDispatcher.listenWithCheck(this);
        record_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen();
            }
        });
    }


    @NeedsPermission(value = {Manifest.permission.RECORD_AUDIO})
    public void listen(){
        speechRecognizer.startListening(recognizerIntent);
    }


    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int i) {

    }

    @Override
    public void onResults(Bundle bundle) {
        ArrayList<String> strList = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        speech_result.setText(strList.get(0));
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
}
