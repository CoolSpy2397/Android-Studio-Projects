package com.synyster.destructor.counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity = 0,cost=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 10) {
            Toast.makeText(this, "Maximum Reached", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 0) {
            Toast.makeText(this, "Minimum Reached", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity--;
        display(quantity);
    }

    public void reset(View view) {
        quantity = 0;
        display(quantity);
        Toast.makeText(this, "Reset Complete", Toast.LENGTH_SHORT).show();
    }

    void display(int n) {
        TextView tv1 = (TextView) findViewById(R.id.text);
        tv1.setText(String.format("%d", n));
        TextView tv2=(TextView)findViewById(R.id.cost);
        tv2.setText(String.format("%d",(cost*quantity)));
    }
}
