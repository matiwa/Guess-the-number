package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText eean;
    TextView tvnot;
    Button btndan,btnchio;
    ImageButton imge;
    private int not;//number of tries
    private int nd;//number drawn
    private int ne;//number entered
    private Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializecomponents();
        buttonsoperation();
    }

    private void initializecomponents(){
        eean=findViewById(R.id.eean); eean.setEnabled(false);
        tvnot=findViewById(R.id.tvnot); tvnot.setEnabled(false);
        btndan=findViewById(R.id.btndan);
        btnchio=findViewById(R.id.btnchio); btnchio.setEnabled(false);
        imge=findViewById(R.id.imge);
    }

    private void buttonsoperation(){
        btndan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnchio.setEnabled(true);
                eean.setEnabled(true);
                tvnot.setEnabled(true);
                r=new Random();
                nd=r.nextInt(101);
                not=10;
                tvnot.setText(""+not+" tries left.\r\n\r\nThe number you are looking for is between 0 and 100.");
                btndan.setEnabled(false);
            }
        });
        btnchio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ne=Integer.parseInt(eean.getText().toString());
                    if(ne!=nd && not>0){
                        if(ne<nd){
                            Toast.makeText(getApplicationContext(),"The number is too small",Toast.LENGTH_LONG).show();
                        }else if(ne>nd){
                            Toast.makeText(getApplicationContext(),"The number is too big",Toast.LENGTH_LONG).show();
                        }
                        not--;
                        tvnot.setText("Try again. You have "+not+" more tries.\r\n\r\nThe number you are looking for is between 0 and 100.");
                    }else if(ne==nd){
                        btndan.setEnabled(true);
                        btnchio.setEnabled(false);
                        tvnot.setEnabled(false); tvnot.setText("");
                        eean.setEnabled(false); eean.setText("");
                        Toast.makeText(getApplicationContext(),"Bravo!!! The drawn number is actually "+ne,
                                Toast.LENGTH_LONG).show();
                    }
                    if(not<1){
                        btndan.setEnabled(true);
                        btnchio.setEnabled(false);
                        tvnot.setEnabled(false); tvnot.setText("");
                        eean.setEnabled(false); eean.setText("");
                        Toast.makeText(getApplicationContext(),"You were looking for a number "+ne,
                                Toast.LENGTH_LONG).show();
                    }
                }catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        imge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
