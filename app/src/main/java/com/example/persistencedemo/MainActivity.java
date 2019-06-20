package com.example.persistencedemo;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private EditText review;
    private EditText rating;
    private Button submit;

    private TextView previousStateReviewText;
    private TextView previousStateRatingText;

    private Review reviewObj;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.button);
        rating = findViewById(R.id.editText2);
        review = findViewById(R.id.editText);
        previousStateRatingText = findViewById(R.id.textView2);
        previousStateReviewText = findViewById(R.id.oldReview);
        gson = new Gson();


        //read file
        File path = getApplicationContext().getFilesDir();
        File file = new File(path, "Review.json");
        try {
            int length = (int) file.length();
            byte[] bytes = new byte[length];
            FileInputStream in = new FileInputStream(file);
            in.read(bytes);
            String contents = new String(bytes);
            in.close();
            Log.d("FileFromLastInstance", contents);
            if (!contents.equals("")) {
                Review previousStateReview = gson.fromJson(contents, Review.class);
                previousStateReviewText.setText(previousStateReview.getReview());
                previousStateRatingText.setText(previousStateReview.getRating() + "");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }






        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make object and JSON
                reviewObj = new Review(review.getText().toString(), Integer.parseInt(rating.getText().toString()));
                String json = gson.toJson(reviewObj);
                Log.d("CurrentFile", json);


                //Make file
                File path = getApplicationContext().getFilesDir();
                File file = new File(path, "Review.json");

                //write to file
                try {
                    FileOutputStream stream = new FileOutputStream(file);
                    stream.write(json.getBytes());
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });

    }
}
