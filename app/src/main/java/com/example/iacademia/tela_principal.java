package com.example.iacademia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class tela_principal extends AppCompatActivity {
    Button bttreinos, btsair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        bttreinos = findViewById(R.id.bttreinos);
        btsair = findViewById(R.id.btsair);

        btsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            };
        });
    }
}
