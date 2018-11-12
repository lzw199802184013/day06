package soexample.umeng.com.day06.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface DeleGate {

    void initData();

    void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle);

    View getrootView();

    void getContext(Context context);
}

