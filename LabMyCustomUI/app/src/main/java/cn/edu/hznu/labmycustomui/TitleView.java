package cn.edu.hznu.labmycustomui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by del on 2017/10/19.
 */

public class TitleView extends RelativeLayout {
    //返回按钮
    private Button mLeftBtn;
    //标题TextView
    private TextView mTitle;
    public TitleView(Context context, AttributeSet attrs){
        super(context,attrs);
        //加载布局
        LayoutInflater.from(context).inflate(R.layout.title_bar,this);
        //获取控件
        mLeftBtn = (Button)findViewById(R.id.left_btn);
        mTitle = (TextView)findViewById(R.id.title_tv);
        //为左侧返回按钮添加自定义控件
        mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"点击了返回按钮",Toast.LENGTH_SHORT).show();
                ((Activity)getContext()).finish();
            }
        });
    }
    //设置标题方法
    public void setmTitleText(String title){
        mTitle.setText(title);
    }

}
