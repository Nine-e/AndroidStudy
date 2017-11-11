package cn.edu.hznu.labfilepersist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    /*private SharedPreferences pref;
    private SharedPreferences.Editor editor;*/

    private EditText fileNameEdit;
    private EditText contentEdit;
    private Button saveBtn;
    private Button loadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*pref = PreferenceManager.getDefaultSharedPreferences(this);*/

        fileNameEdit = (EditText)findViewById(R.id.fileName);
        contentEdit = (EditText)findViewById(R.id.content);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        loadBtn = (Button)findViewById(R.id.loadBtn);
        
        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String fileName = fileNameEdit.getText().toString();
                String content = contentEdit.getText().toString();
                if(!TextUtils.isEmpty(fileName)){
                   /* editor = pref.edit();
                    editor.putString("content",content);
                    contentEdit.setText("");
                    editor.apply();*/
                    save(fileName,content);
                    contentEdit.setText("");
                    Toast.makeText(MainActivity.this, "Data have been saved", Toast.LENGTH_SHORT).show();
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Warning");
                    dialog.setMessage("文件名不能为空");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.show();
                }

            }
        });

        loadBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String fileName = fileNameEdit.getText().toString();
                if (!TextUtils.isEmpty(fileName)){
                    String content = load(fileName);
                    /*String content = pref.getString("content","");*/
                    contentEdit.setText(content);
                    contentEdit.setSelection(content.length());
                    Toast.makeText(MainActivity.this, "Data have been loaded", Toast.LENGTH_SHORT).show();
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Warning");
                    dialog.setMessage("文件名不能为空");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("OK",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.show();
                }
            }
        });

    }

    public void save(String fileName,String content){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput(fileName, Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(content);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String load(String fileName){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!= null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
