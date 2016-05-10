package com.example.andy.a0509_intent;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText m_tv_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findID();
    }

    private void findID() {
        m_tv_editText = (EditText) findViewById(R.id.tv_editText);
    }



    public void onClick(View view) {
        switch (view.getId()) {
            //方法1：顯性的意圖
            case R.id.bt_Go_Receive:
                Intent intent1 = new Intent(this, ReceiveActivity.class);
                startActivity(intent1);
                break;
            //方法1：顯性的意圖
            case R.id.bt_Go_Text:
                Intent intent2 = new Intent(this, TextActivity.class);
                startActivity(intent2);
                break;

            //方法2：隱性的意圖
            //這時候 我們就要在　AndroidManifest +寫filter（過濾器）
            case R.id.bt_Go_隱式Intent:
                Intent intent3 = new Intent("help");
                startActivity(intent3);
                break;

            //方法3：連結到Google
            case R.id.btn_From_Google:
                Intent intent4 = new Intent();
                intent4.setAction(Intent.ACTION_WEB_SEARCH);
                intent4.putExtra(SearchManager.QUERY, m_tv_editText.getText().toString());
                startActivity(intent4);
                break;

            case R.id.btn_傳送包裹:
                Intent intent5 = new Intent(this, TextActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("string", m_tv_editText.getText().toString());
                bundle.putInt("int", 1314520);
                bundle.putDouble("double", 3.14);
                intent5.putExtras(bundle);
                startActivity(intent5);
                break;

            case R.id.btn_傳送包裹並回傳:
                Intent intent6 = new Intent(this, ReceiveActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("string", m_tv_editText.getText().toString());
                bundle2.putInt("int", 30678);
                bundle2.putDouble("double", 3.141596);
                intent6.putExtras(bundle2);
                startActivityForResult(intent6,101);
                break;

            case R.id.btn_聲控的應用:
                Intent intent7 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //intent2.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                startActivityForResult(intent7, 201);
                break;

        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101 && resultCode==RESULT_OK){
            Toast.makeText(this,data.getIntExtra("random",0)+"",Toast.LENGTH_LONG).show();

        }else if(requestCode == 201 && resultCode == RESULT_OK) {
            ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            m_tv_editText.setText(text.get(0));
        }
    }
}
