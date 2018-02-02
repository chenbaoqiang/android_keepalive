package cn.com.feinno.keepalive;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;
import com.huawei.hms.support.api.push.TokenResult;

import cn.com.feinno.keep.Config;
import cn.com.feinno.keep.service.MyJobService;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = Config.TAG + "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HMSAgent.connect(this, new ConnectHandler() {
            @Override
            public void onConnect(int rst) {
                Log.e(TAG, "HMS connect end:" + rst);
                getToken();
                HMSAgent.Push.enableReceiveNormalMsg(true);
                HMSAgent.Push.enableReceiveNotifyMsg(true);

            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            MyJobService.startService(this);
        }
//        OnePixLiveService.toLiveService(this);



    }

    /**
     * 获取token
     */
    private void getToken() {
        Log.e(TAG,"get token: begin");
        HMSAgent.Push.getToken(new GetTokenHandler() {
            @Override
            public void onResult(int rtnCode, TokenResult tokenResult) {
                Log.e(TAG,"get token: end" + rtnCode);
            }
        });
    }
}
