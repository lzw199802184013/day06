package soexample.umeng.com.day06.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import soexample.umeng.com.day06.mvp.view.ADeleGate;

public abstract class BaseActivityPresenter<T extends ADeleGate> extends AppCompatActivity {

    public T deleGate;

    public abstract Class<T> getClassDeleGate();

    public BaseActivityPresenter() {
        try {
            deleGate = getClassDeleGate().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deleGate.create(getLayoutInflater(), null, savedInstanceState);
        setContentView(deleGate.getrootView());
        //ButterKnife.bind(this);
        deleGate.getContext(this);
        initWeight();
        deleGate.initData();
    }

    //子类activity要实现得方法
    public void initWeight() {

    }
}
