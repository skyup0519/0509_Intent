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
            //方法1：顯性的意圖(最基本的用法)
            case R.id.bt_Go_Receive:
                //創造一個意圖 Intent(目前的位置,要過去的位置);
                Intent intent1 = new Intent(this, ReceiveActivity.class);

                //開始
                startActivity(intent1);
                break;

            //方法1：顯性的意圖(同上)
            case R.id.bt_Go_Text:
                Intent intent2 = new Intent(this, TextActivity.class);
                startActivity(intent2);
                break;

            //方法2：隱性的意圖
            case R.id.bt_Go_隱式Intent:
                Intent intent3 = new Intent("help");
                //例如： 我喊了"help" 誰可以救我?? (誰可以接受我的意圖?
                //這時候 我們就要在　AndroidManifest中 把接收的Activity+寫filter（過濾器）
                //代表說 我這個Activity可以"help"他
                //<intent-filter>
                //<action android:name="help"></action>
                //<category android:name="android.intent.category.DEFAULT"/>
                //</intent-filter>

                startActivity(intent3);
                break;

            //方法3：連結到Google
            case R.id.btn_From_Google:
                //創造一個意圖
                Intent intent4 = new Intent();

                //內建的用法
                intent4.setAction(Intent.ACTION_WEB_SEARCH);

                //意圖(搜索管理<制式寫法>，想要搜尋的文字)
                intent4.putExtra(SearchManager.QUERY, m_tv_editText.getText().toString());
                startActivity(intent4);
                break;

            //方法4：如何新增一個Bundle(包裹)，並讓Intent(意圖)傳送過去
            case R.id.btn_傳送包裹:
                Intent intent5 = new Intent(this, TextActivity.class);
                //建立一個包包
                Bundle bundle = new Bundle();

                //包包放入一個"String"
                bundle.putString("string", m_tv_editText.getText().toString());

                //包包放入一個"Int"
                bundle.putInt("int", 1314520);

                //包包放入一個"Double"
                bundle.putDouble("double", 3.14);

                //使意圖裝入這個包包
                intent5.putExtras(bundle);

                //開始傳送
                startActivity(intent5);
                break;

            //方法5：如何傳送包裹並回傳
            case R.id.btn_傳送包裹並回傳:
                //同上方法4
                Intent intent6 = new Intent(this, ReceiveActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("string", m_tv_editText.getText().toString());
                bundle2.putInt("int", 30678);
                bundle2.putDouble("double", 3.141596);
                intent6.putExtras(bundle2);

                //如果你希望回傳資料 你這邊就必須改成startActivityForResult(意圖,意圖的編號)
                //然後在下方 @override onActivityResult這個方法
                startActivityForResult(intent6,101);
                break;

            case R.id.btn_聲控的應用:
                //新增一個意圖，內容其實是一個字串(可點ctrl進去看)，內建的方法
                Intent intent7 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                //其實這段可以省略，主要是用來設定語言(根據手機設定即可)
                //intent7.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                //startActivityForResult(意圖，意圖的編號)
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
