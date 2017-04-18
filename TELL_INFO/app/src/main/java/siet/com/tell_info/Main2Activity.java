package siet.com.tell_info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {
    ImageButton mainbutton;
    ImageButton mainbutton1;
    ImageButton mainbutton2;
    ImageButton mainbutton3;
    ImageButton mainbutton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mainbutton=(ImageButton) findViewById(R.id.imageButton13);
        mainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
        mainbutton1=(ImageButton) findViewById(R.id.imageButton20);
        mainbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main4Activity.class);
                startActivity(intent);
            }
        });
        mainbutton2=(ImageButton) findViewById(R.id.imageButton15);
        mainbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main5Activity.class);
                startActivity(intent);
            }
        });
        mainbutton3=(ImageButton) findViewById(R.id.imageButton2);
        mainbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main6Activity.class);
                startActivity(intent);
            }
        });
        mainbutton4=(ImageButton) findViewById(R.id.imageButton8);
        mainbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,Main7Activity.class);
                startActivity(intent);
            }
        });
    }
}
