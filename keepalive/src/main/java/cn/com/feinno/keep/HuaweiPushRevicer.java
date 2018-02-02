package cn.com.feinno.keep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;

/**
 * 版权所有 新媒传信科技有限公司。保留所有权利。<br>
 * 作者：chenbaoqiang on 2018/2/1
 * 项目名：RCSNative - Android客户端<br>
 * 描述：
 *
 * @version 1.0
 * @since JDK1.8.0_152
 */

public class HuaweiPushRevicer extends PushReceiver {
    public static final String TAG = Config.TAG + LiveActivity.class.getSimpleName();

    @Override
    public void onToken(Context context, String token, Bundle extras) {
        Log.e(TAG, "HuaweiPushRevicer onToken  token = " + token);
    }

    @Override
    public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
        Log.e(TAG, "HuaweiPushRevicer onPushMsg ");
        return true;

    }

    public void onEvent(Context context, Event event, Bundle extras) {
        Log.e(TAG, "HuaweiPushRevicer onEvent  ");

    }

    @Override
    public void onPushState(Context context, boolean pushState) {
        Log.e(TAG, "HuaweiPushRevicer onPushState  pushState = " + pushState);

    }


}
