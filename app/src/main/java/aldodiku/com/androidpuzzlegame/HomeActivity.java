package aldodiku.com.androidpuzzlegame;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Locale;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);


        Button shqip = findViewById(R.id.button_shqip);
        Button english = findViewById(R.id.button_anglisht);

        Button policy_disclaimer_button = findViewById(R.id.button_privacy_disclaimer);
        Button exit_app = findViewById(R.id.button_exit_app);

        //by default the language is english
        //setting the language to albanian
        // we are also sending the user to the other tab (gallery) after he is clicking one of the languages
        shqip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setAppLocale("al");
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //setting the language to english
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLocale("en");
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //sends you to the privacy/disclaimer View/Activity
        policy_disclaimer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PrivacyPolicy.class);
                startActivity(intent);

            }
        });

        //exiting application
        exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        //changes language to en
                // check if you have set this up correctly
                // setAppLocale("en");
    }

    private void setAppLocale(String localeCode){

        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            conf.setLocale(new Locale(localeCode.toLowerCase()));
        }
        else {
            conf.locale = new Locale(localeCode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);
    }
}