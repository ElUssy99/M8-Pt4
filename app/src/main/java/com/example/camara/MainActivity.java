package com.example.camara;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.button);

        b.setOnClickListener(new View().OnClickListener() {
                                 @Override
                                 public void onClick(View v) {
                                     Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                     if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                         startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                                     }
                                 }
                             }

        );
        nImageView = findViewById(R.id.foto);
        File file = new File("../src/main/java/com.example.tnb_20.capturaimagen/files/imgRD.jpg");
        Uri uir = Uri.fromFile(file);
        nImageView.setImageURI(uir);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            nImageView = findViewById(R.id.foto);
            nImageView.setImageBitmap(imageBitmap);

            try {
                FileOutputStream out = openFileOutput("imgRD.jpg", MODE_PRIVATE);
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.close();
                Log.v("TAG OK", "Archivo creado correctamente");
            } catch (Exception e) {
                Log.v("FILE ERROR", "Error creando el archivo");
                e.printStackTrace();
            }
        }
    }
}
