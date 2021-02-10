package aldodiku.com.androidpuzzlegame;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class foto_discription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_discription);

        final ImageView image = findViewById(R.id.photo);
        final TextView discription = findViewById(R.id.foto_description);

        Intent intent = getIntent();

        final String extras = intent.getStringExtra("assetName");


        image.post(new Runnable() {
            @Override
            public void run() {
                if (extras != null) {

                    String[] array_string = getResources().getStringArray(R.array.photos_description);
                    String photo_desc = "";
                    for(String s : array_string){
                        if (s.contains(extras)){
                            photo_desc = s.substring(s.indexOf('|') + 1);
                        }
                    }

                    setPicFromAsset(extras, image);

                    discription.setText(photo_desc);
                }
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(foto_discription.this, PuzzleActivity.class);

                intent.putExtra("assetName", extras);

                startActivity(intent);

            }
        });


        }

        private void setPicFromAsset(String assetName, ImageView imageView) {

            // Get the dimensions of the View

            int targetW = imageView.getWidth();
            int targetH = imageView.getHeight();

            AssetManager am = getAssets();
            try {
                InputStream is = am.open("img/" + assetName);

                // Get the dimensions of the bitmap

                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

                // Determine how much to scale down the image

                int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

                is.reset();

                // Decode the image file into a Bitmap sized to fill the View

                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;
                bmOptions.inPurgeable = true;

                Bitmap bitmap = BitmapFactory.decodeStream(is, new Rect(-1, -1, -1, -1), bmOptions);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        // where and why is this used for, searching for the string to use in intents for the puzzle activity java class
        //
        //
        //
        //
        //
        private String searchForDescription(String message){

            try{
                int resId = getResources().getIdentifier(message, "string", getPackageName());
                return getResources().getString(resId);
            } catch (Exception e){
                return null;
            }
        }

    }


