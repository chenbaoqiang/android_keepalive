package cn.com.feinno.keep.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import cn.com.feinno.keep.Config;
import cn.com.feinno.keep.ScreenBroadcastListener;
import cn.com.feinno.keep.ScreenManager;

/**
 * 版权所有 新媒传信科技有限公司。保留所有权利。<br>
 * 作者：chenbaoqiang on 2018/1/25
 * 项目名：RCSNative - Android客户端<br>
 * 描述：
 *
 * @version 1.0
 * @since JDK1.8.0_152
 */

public class OnePixLiveService extends Service {

    public static final String TAG = Config.TAG + OnePixLiveService.class.getSimpleName();

    public static void toLiveService(Context pContext) {
        Intent intent = new Intent(pContext, OnePixLiveService.class);
        pContext.startService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "OnePixLiveService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        //屏幕关闭的时候启动一个1像素的Activity，开屏的时候关闭Activity
        final ScreenManager screenManager = ScreenManager.getInstance(OnePixLiveService.this);
        ScreenBroadcastListener listener = new ScreenBroadcastListener(this);
        listener.registerListener(new ScreenBroadcastListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                Log.e(TAG, "onScreenOn");
                screenManager.finishActivity();
            }

            @Override
            public void onScreenOff() {
                Log.e(TAG, "onScreenOff");
                screenManager.startActivity();
            }
        });
        Log.e(TAG, "OnePixLiveService onStartCommand");

        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "OnePixLiveService onDestroy");

    }
}
