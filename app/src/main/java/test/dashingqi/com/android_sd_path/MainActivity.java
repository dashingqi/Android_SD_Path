package test.dashingqi.com.android_sd_path;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et_text;
    private Button save_data;
    private Button read_data;
    private StringBuffer buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String text = et_text.getText().toString();
                if (!TextUtils.isEmpty(text)){
                    saveData(text);
                }
            }
        });

        read_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = readData();
                Toast.makeText(MainActivity.this,data,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        et_text = (EditText) findViewById(R.id.et_text);
        save_data = (Button) findViewById(R.id.save_data);
        read_data = (Button) findViewById(R.id.read_data);
    }

    /**
     * @param data
     * 写数据
     */
    public void saveData(String data){
        FileOutputStream output = null;
        BufferedWriter bufferedWriter  = null;

        try {
            output = openFileOutput("myself", Context.MODE_APPEND);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(output));
            bufferedWriter.write(data);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 写数据
     */
    public String readData(){
        FileInputStream input =null;
        BufferedReader reader = null;
        try {
            input = openFileInput("myself");
            reader = new BufferedReader(new InputStreamReader(input));
            buffer = new StringBuffer();
            String line = null;
            while((line =reader.readLine())!=null){
                buffer.append(line+"\r\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (reader!=null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
