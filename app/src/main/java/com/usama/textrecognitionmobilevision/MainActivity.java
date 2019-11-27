package com.usama.textrecognitionmobilevision;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {

    // hello everyone

    // make sure you put this in the gradle com.google.android.gms:play-services-vision:19.0.0
    // now in the layout we need a button and imageView and textView
    // some style
    //make sure you have any image in drawable
    // let's code

    Button btnProcess;
    ImageView imageView;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProcess = findViewById(R.id.btn_process);
        imageView = findViewById(R.id.image_view);
        txtResult = findViewById(R.id.txt_result);

        final Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.maxresdefault);
        imageView.setImageBitmap(bitmap);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

                // now check if the device does not have enogh storage , or google play services can't download the OCR dependencies

                if (!textRecognizer.isOperational()) {
                    Log.d("ERROR", "DETECT dependencies are not yet available ! ");

                } else {
                    // now just put the text in array and append in it
                    // show it in a textView -- > it's easy :)
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = textRecognizer.detect(frame);
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 0; i < items.size(); i++) {
                        TextBlock item = items.valueAt(i);
                        stringBuilder.append(item.getValue());
                        stringBuilder.append("\n");
                    }
                    txtResult.setText(stringBuilder.toString());
                }
                // now run :)
                // good job
                // good bye
            }
        });
    }
}
