package com.socks.jandan.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.volley.Request;
import com.socks.jandan.BuildConfig;
import com.socks.jandan.net.RequestManager;
import com.socks.jandan.utils.logger.LogLevel;
import com.socks.jandan.utils.logger.Logger;
import com.socks.jandan.view.imageloader.ImageLoadProxy;


public class BaseFragment extends Fragment implements ConstantString {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BuildConfig.DEBUG) {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.FULL).hideThreadInfo();
        } else {
            Logger.init(getClass().getSimpleName()).setLogLevel(LogLevel.NONE).hideThreadInfo();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JDApplication.getRefWatcher(getActivity()).watch(this);
        RequestManager.cancelAll(this);
        ImageLoadProxy.getImageLoader().clearMemoryCache();
    }

    protected void executeRequest(Request request) {
        RequestManager.addRequest(request, this);
    }
}
