package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bFlash;
    private LightClass lightClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
    bFlash = findViewById(R.id.b1);
    lightClass = new LightClass(this);
}
    public void onClickFlash(View vew){
    if (lightClass.isLightStatus()){
    lightClass.lightOff();
    bFlash.setText("Вкл");
        bFlash.setBackgroundResource(R.drawable.circle_green);
    }
    else {
        lightClass.lightOn();
        bFlash.setText("Выкл");
        bFlash.setBackgroundResource(R.drawable.circle_red);
    }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lightClass.isLightStatus()) {
            lightClass.lightOff();

        }
    }
}