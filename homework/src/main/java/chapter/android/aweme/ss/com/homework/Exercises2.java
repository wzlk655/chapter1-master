package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    ViewGroup mView;
    TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);
        mView=findViewById(R.id.root_view);
        mTextView=findViewById(R.id.tv_center);
        mTextView.setText(((Integer)getAllChildViewCount(mView)).toString());
    }

    public int getAllChildViewCount(ViewGroup view) {
        //todo 补全你的代码
        return view.getChildCount();
    }
}
