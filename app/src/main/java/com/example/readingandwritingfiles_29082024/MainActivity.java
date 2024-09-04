package com.example.readingandwritingfiles_29082024;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button read,write;
    EditText ed;
    TextView tvv;
    String file_name="file.txt";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        read=(Button) findViewById(R.id.btn1);
        write=(Button) findViewById(R.id.btn10);
        ed=(EditText) findViewById(R.id.ed1);
        tvv=(TextView) findViewById(R.id.tv2);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data =ed.getText().toString();
               try {
                   FileOutputStream fos= openFileOutput(file_name,MODE_APPEND);
                   fos.write(data.getBytes());
                   fos.close();
                   Toast.makeText(MainActivity.this, "File is saved", Toast.LENGTH_LONG).show();
               }catch (FileNotFoundException e){
                   throw new RuntimeException(e);
               }catch (IOException e){
                   throw new RuntimeException(e);
               }
        };
    });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text="";
                try {
                    FileInputStream fis=openFileInput(file_name);
                    int size= fis.available();
                    byte[]buffer=new byte[size];
                    fis.read(buffer);
                    text=new String(buffer);
                    fis.close();
                }catch (FileNotFoundException e){
                    throw new RuntimeException(e);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
                tvv.setText(text);
            }
        });

}
}