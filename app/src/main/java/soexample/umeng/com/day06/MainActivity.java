package soexample.umeng.com.day06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.day06.RxJava_RetroFit.HttpUtils;
import soexample.umeng.com.day06.mvp.presenter.BaseActivityPresenter;

public class MainActivity extends BaseActivityPresenter<MainActivityPresenter> {
    @BindView(R.id.dianji)
    public Button dianji;

    @Override
    public Class<MainActivityPresenter> getClassDeleGate() {
        return MainActivityPresenter.class;
    }

    @Override
    public void initWeight() {
        super.initWeight();
        ButterKnife.bind(this);
        deleGate.initView();
    }
    @OnClick(R.id.dianji)
    public  void  click(){

        deleGate.doCrarte();
    }
    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        findViewById(R.id.dianji).setOnClickListener(this);
//
//    }
//
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.dianji:
//                doHttp();
//                break;
//        }
//    }
//
//    private void doHttp() {
/////product/getCarts?uid=71
//        Map<String, String> map = new HashMap<>();
//        map.put("uid", "71");
//        new HttpUtils().get("/product/getCarts", map).result(new HttpUtils.HttpListener() {
//            @Override
//            public void success(String data) {
//                Toast.makeText(MainActivity.this, data + "", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void fail(String error) {
//
//            }
//        });
//    }
}
