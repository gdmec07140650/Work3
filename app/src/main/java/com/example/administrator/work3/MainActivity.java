package com.example.administrator.work3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button but;
    private EditText weightEditText;
    private  CheckBox manCheckbox;
    private CheckBox womanCheckbox;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but= (Button) findViewById(R.id.button);
        weightEditText= (EditText) findViewById(R.id.editText1);
        manCheckbox= (CheckBox) findViewById(R.id.checkBox1);
        womanCheckbox= (CheckBox) findViewById(R.id.checkBox2);
        tv1= (TextView) findViewById(R.id.textView4);
        tv2= (TextView) findViewById(R.id.textView5);
        but.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void showMessage(String message)
    {
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("温馨提示");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
    public double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }
    @Override
    public void onClick(View v) {
        if(!weightEditText.getText().toString().trim().equals("")){
            if(womanCheckbox.isChecked() || manCheckbox.isChecked()){
                Double weight=Double.parseDouble(weightEditText.getText().toString());
                StringBuffer sb=new StringBuffer();
                if(manCheckbox.isChecked()&&womanCheckbox.isChecked()){
                    StringBuffer sb1=new StringBuffer();
                    StringBuffer sb2=new StringBuffer();
                    sb1.append("男性标准身高:");
                    double result=evaluateHeight(weight,"男");
                    sb1.append((int) result + "厘米");
                    tv1.setText(sb1);
                    sb2.append("女性标准身高：");
                    double result2=evaluateHeight(weight,"女");
                    sb2.append((int) result2 + "厘米");
                    tv2.setText(sb2);
                    sb1.delete(0, 15);
                    sb2.delete(0,15);
                }else{
                    if(manCheckbox.isChecked()){
                        sb.append("男性标准身高：");
                        double result=evaluateHeight(weight,"男");
                        sb.append((int) result + "厘米");
                        tv1.setText(sb);
                        sb.delete(0, 15);
                        tv2.setText(sb);
                    }
                    if(womanCheckbox.isChecked()){
                        sb.append("女性标准身高：");
                        double result2=evaluateHeight(weight,"女");
                        sb.append((int) result2 + "厘米");
                        tv1.setText(sb);
                        sb.delete(0, 15);
                        tv2.setText(sb);
                    }
                }

            }else{
                showMessage("请选择性别");
            }
        }else {
            showMessage("请输入体重");
        }
    }
}

