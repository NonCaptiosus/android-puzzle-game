package aldodiku.com.androidpuzzlegame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PrivacyPolicy extends AppCompatActivity {

    Button button;

    private Boolean disclaimer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        button = findViewById(R.id.button_disclaimer);
        final TextView title = findViewById(R.id.text_title);
        final TextView body = findViewById(R.id.text_main_body);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // changing the value of disclaimer to opposite of what it was
                disclaimer = !disclaimer;

                // if disclaimer is false we show policy
                String changed_title;
                String changed_body;
                String button_name;

                if (!disclaimer){
                    changed_title = getResources().getString(R.string.title_privacy_policy);
                    changed_body = getResources().getString(R.string.policy_text);
                    button_name = getResources().getString(R.string.disclaimer_button_name);
                }

                //if disclaimer bool is true, we show disclaimer title and text

                else
                    {
                        changed_title = getResources().getString(R.string.title_disclaimer);
                        changed_body = getResources().getString(R.string.disclaimer_main_body);
                        button_name = getResources().getString(R.string.privacy_policy_button);
                    }

                title.setText(changed_title);
                body.setText(changed_body);
                button.setText(button_name);
            }
        });
    }
}