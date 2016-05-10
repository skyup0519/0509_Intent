package com.example.andy.a0509_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class ReceiveActivity extends AppCompatActivity {
private TextView m_tv_ReceiveActivity_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        findID();
        setText();
    }
    private void findID() {
        m_tv_ReceiveActivity_text = (TextView) findViewById(R.id.tv_ReceiveActivity_text);
    }

    private void setText() {
        Bundle bundle = getIntent().getExtras();

        m_tv_ReceiveActivity_text.setText(bundle.getString("string")
                + "\n" + bundle.getInt("int")
                + "\n" + bundle.getDouble("double"));

    }
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("random",new Random().nextInt(100));

        setResult(RESULT_OK,intent);
        finish();
    }
}
