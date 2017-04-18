package siet.com.tell_info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button GoToNewActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        GoToNewActivity = (Button)findViewById(R.id.button16);
        GoToNewActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Intent code for open new activity through intent.

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

            }
        });
    }
    public void dismisWelcomeMessageBox(View view) {
        GoToNewActivity  .setVisibility(View.INVISIBLE);
        //appContent.setVisibility(View.VISIBLE);

    }
}
