package soexample.umeng.com.day06.RxJava_RetroFit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpUtils {
    private BaseService baseService;

    public HttpUtils() {
        Retrofit retrofit = new Retrofit.Builder()
                //适配器工厂
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://www.zhaoapi.cn/")
                .build();
        baseService = retrofit.create(BaseService.class);

    }

    //get
    public HttpUtils get(String url, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        baseService.get(url, map)
                //设置调度器
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//主线程
                .subscribe(observer);
        return this;
    }

    //post请求
    public HttpUtils post(String url, Map<String, String> map) {
        if (map == null) {

            map = new HashMap<>();
        }
        baseService.post(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);


        return this;
    }

    //公用观察者
    private Observer observer = new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String data = responseBody.string();
                listener.success(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            String eroor = e.getMessage();
            listener.fail(eroor);
        }

        @Override
        public void onComplete() {

        }
    };

    private HttpListener listener;

    public void result(HttpListener listener) {
        this.listener = listener;
    }

    //接口回调
    public interface HttpListener {
        void success(String data);
        void fail(String error);

    }

    ;
}
