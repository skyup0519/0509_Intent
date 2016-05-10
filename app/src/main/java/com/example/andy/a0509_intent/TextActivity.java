package com.example.andy.a0509_intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {
    private TextView m_tv_TextActivity_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        findID();
        setText();
    }

    private void findID() {
        m_tv_TextActivity_text = (TextView) findViewById(R.id.tv_TextActivity_text);
    }

    private void setText() {
        Bundle bundle = getIntent().getExtras();

        m_tv_TextActivity_text.setText(bundle.getString("string")
                + "\n" + bundle.getInt("int")
                + "\n" + bundle.getDouble("double"));

    }

    public void onClick(View view) {
        finish();
    }


}
