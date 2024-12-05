package com.example.iacademia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class tela_principal extends AppCompatActivity {
    Button bttreinos, btsair , btfeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        bttreinos = findViewById(R.id.bttreinos);
        btsair = findViewById(R.id.btsair);
        btfeedback = findViewById(R.id.btfeedback);
        bttreinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), tela_treinos.class);
                startActivity(i);
            }
        });

        btfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), tela_feedback.class);
                startActivity(i);
            }
        });

        btsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             finish();
            };
        });
    }
}
