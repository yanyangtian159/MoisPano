package com.deepai.moispano.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.deepai.moispano.R;
import com.deepai.moispano.network.NetWorks;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/11/24.
 */

public class TestActivity extends Activity implements View.OnClickListener {
    Button post;
    TextView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        post = (Button) findViewById(R.id.post);
        view = (TextView) findViewById(R.id.txt);
        post.setOnClickListener(this);
//        request();

    }

    private void request() {
//        Map<String,String> params = new HashMap<>();
//        params.put("currentPage", "1");
        NetWorks.getStringCodePostMap(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                view.setText("=====onNext======>>"+s);

            }

            @Override
            public void onError(Throwable e) {
                view.setText("=======onError========>>"+e.getMessage());
            }

            @Override
            public void onComplete() {
            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.post:
                request();
                break;
        }
    }
}
