package soexample.umeng.com.day06;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import soexample.umeng.com.day06.mvp.view.ADeleGate;

public class MainActivityPresenter extends ADeleGate {
    private Context context;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        Map<String, String> map = new HashMap<>();
        map.put("uid", "71");
//        getString(0, "/product/getCarts", map);
        getBean(0, "/product/getCarts", map, DataBeans.class);
    }
    //请求成功，返回JavaBean

    @Override
    public <T> void successBean(int type, T bean) {
        super.successBean(type, bean);
        switch (type) {
            case 0:
                DataBeans dataBeans = (DataBeans)bean;
                Log.i("MainActivityPresenter", dataBeans.getData().size() + "===");
                break;
        }
    }


//        //请求成功，返回字符串
//    @Override
//    public void successString(int type, String data) {
//        super.successString(type, data);
//        switch (type) {
//            case 0:
//                Log.i("MainActivityPresenter",data);
//                break;
//        }
//
//    }

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    public void initView() {

    }

    //点击click
    public void doCrarte() {
    }
}
