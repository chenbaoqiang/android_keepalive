package cn.com.feinno.keepalive;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;

import cn.com.feinno.keep.Config;

/**
 * 版权所有 新媒传信科技有限公司。保留所有权利。<br>
 * 作者：chenbaoqiang on 2018/1/31
 * 项目名：RCSNative - Android客户端<br>
 * 描述：
 *
 * @version 1.0
 * @since JDK1.8.0_152
 */

public class MyApplication extends Application {
    private static final String TAG = Config.TAG + "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"MyApplication onCreate");
        HMSAgent.init(this);
        Toast.makeText(this,"我起来了 ",Toast.LENGTH_SHORT).show();

    }
}
