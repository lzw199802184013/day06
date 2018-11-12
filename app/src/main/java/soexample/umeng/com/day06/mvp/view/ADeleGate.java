package soexample.umeng.com.day06.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Map;

import soexample.umeng.com.day06.RxJava_RetroFit.HttpUtils;

public abstract class ADeleGate implements DeleGate {
    private View rootView;

    @Override
    public void initData() {

    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = inflater.inflate(getLayoutId(), viewGroup, false);
    }

    @Override
    public View getrootView() {
        return rootView;
    }

    private Context context;

    @Override
    public void getContext(Context context) {
        this.context = context;
    }

    public void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public abstract int getLayoutId();

    //get 请求 获取字符串
    public void getString(int type, String url, Map<String, String> map) {

        doHttpString("GET", type, url, map);
    }

    //post请求 获取字符串
    public void postString(int type, String url, Map<String, String> map) {

        doHttpString("POST", type, url, map);
    }

    //get请求，获取Java对象， bean
    public <T> void getBean(int type, String url, Map<String, String> map, Class<T> cls) {
        doHttpBean("GET", type, url, map, cls);
    }

    //post请求，获取Java对象，bean
    public <T> void postBean(int type, String url, Map<String, String> map, Class<T> cls) {
        doHttpBean("POST", type, url, map, cls);
    }

    //请求，返回JavaBean
    private <T> void doHttpBean(String method, final int type, String url, Map<String, String> map, final Class<T> cls) {
        HttpUtils httpUtils = new HttpUtils();
        if ("GET".equals(method)) {
            httpUtils.get(url, map);
        } else {
            httpUtils.post(url, map);
        }
        httpUtils.result(new HttpUtils.HttpListener() {
            @Override
            public void success(String data) {
                T bean = new Gson().fromJson(data, cls);
                successBean(type, bean);
            }

            @Override
            public void fail(String error) {
                error(error);
            }
        });
    }

    //请求，返回字符串
    private void doHttpString(String method, final int type, String url, Map<String, String> map) {
        HttpUtils httpUtils = new HttpUtils();
        if ("GET".equals(method)) {
            httpUtils.get(url, map);
        } else {
            httpUtils.post(url, map);
        }
        httpUtils.result(new HttpUtils.HttpListener() {
            @Override
            public void success(String data) {
                successString(type, data);
            }

            @Override
            public void fail(String error) {
                error(error);
            }
        });
    }

    //请求成功，返回JavaBean
    public <T> void successBean(int type, T bean) {


    }

    //请求成功，返回字符串
    public void successString(int type, String data) {


    }

    //请求错误
    public void error(String error) {

    }


}
