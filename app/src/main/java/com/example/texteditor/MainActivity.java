package com.example.texteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText textInput;
    EditText textInput2;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInput = (EditText) findViewById(R.id.textInput);
        textInput2 = (EditText) findViewById(R.id.textInput2);
        context = MainActivity.this;
    }

    public void saveFile(View v) {
        CharSequence file = textInput2.getText();
        CharSequence s = "Input file name first!";
        if (file.toString().isEmpty()) {
            textInput2.setHint(s);
            return;
        }
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(file.toString(), Context.MODE_PRIVATE));
            CharSequence txt = textInput.getText();
            osw.write(txt.toString());
            osw.close();
            txt = "File saved.";
            textInput.setText(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadFile(View v) {
        CharSequence file = textInput2.getText();
        CharSequence s = "Input file name first!";
        CharSequence t = "";
        if (file.toString().isEmpty()) {
            textInput2.setHint(s);
            return;
        }
        try {
            InputStream isr = context.openFileInput(file.toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(isr));
            String txt;
            textInput.setText(t);
            while ((txt = br.readLine()) != null) {
                textInput.append(txt);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
