package com.example.iacademia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class tela_principal extends AppCompatActivity {
    Button btpedido, btsair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        btpedido = findViewById(R.id.btpedido);
        btsair = findViewById(R.id.btsair);

        btsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            };
        });
    }
}
