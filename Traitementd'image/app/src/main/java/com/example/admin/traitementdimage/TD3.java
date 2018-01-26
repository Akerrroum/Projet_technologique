package com.example.admin.traitementdimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TD3 extends AppCompatActivity {

    ImageView imageView;
    Button bBackToTD2, bGoToTD4, bContraste;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_td3);

        imageView = (ImageView) findViewById(R.id.imageView);
        bBackToTD2 = (Button) findViewById(R.id.buttBackToTD2);
        bGoToTD4 = (Button) findViewById(R.id.buttGoToTD4);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.zzz);
        bContraste = (Button) findViewById(R.id.buttContraste);

        bBackToTD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(TD3.this, TD2.class);
                startActivity(myIntent);
            }
        });

        bContraste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap newBitmap = toGray2(bitmap);
                imageView.setImageBitmap(newBitmap);
            }
        });
    }

    public static Bitmap toGray2(Bitmap original){
        Bitmap finalImage = Bitmap.createBitmap(original.getWidth(), original.getHeight(), original.getConfig());
        int width = original.getWidth();
        int height = original.getHeight();
        int [] pixels = new int[width*height];
        int colorPixel;
        int A, R, G, B;
        int min ;
        int max ;

        original.getPixels(pixels,0,width,0,0,width,height);
        min = max = pixels[0];
        for (int x = 0; x < (width*height); x++){

            colorPixel = pixels[x];
            A = Color.alpha(colorPixel);
            R = Color.red(colorPixel);
            G = Color.green(colorPixel);
            B = Color.blue(colorPixel);

            min = Math.min(min, colorPixel);
            max = Math.max(max, colorPixel);

           G = R = B = (R + G + B) / 3;

            int r = (255*(R - min))/(max-min);
            int g= (255*(G - min))/(max-min);
            int b = (255*(B - min))/(max-min);

            pixels[x] = Color.argb(A, r, g, b);
        }

        finalImage.setPixels(pixels,0,width,0,0,width,height);

        return finalImage;
    }
}
