package cn.com.feinno.keep.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;

import cn.com.feinno.keep.Config;

/**
 * 版权所有 新媒传信科技有限公司。保留所有权利。<br>
 * 作者：chenbaoqiang on 2018/1/24
 * 项目名：RCSNative - Android客户端<br>
 * 描述：
 *
 * @version 1.0
 * @since JDK1.8.0_152
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    private static final String TAG = Config.TAG + "MyJobService";
    private static Context mContext;
    private final int INTERVAL_MILLIS = 30000;
    private final int MAXDELAY_MILLIS = 60000;

    public static void startService(Context context) {
        mContext = context;
        Intent intent = new Intent(context, MyJobService.class);
        ComponentName co = context.startService(intent);
        Log.i(TAG, "startService : " + co);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e(TAG, "MyJobService onCreate");
        startJobScheduler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "MyJobService onStartCommand");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e(TAG, "MyJobService onDestroy");
        Intent intent = new Intent(this, MyJobService.class);
        startService(intent);
    }

    public void startJobScheduler() {
        try {
            JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(getPackageName(), MyJobService.class.getName()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //设置任务运行最少延迟时间
                builder.setMinimumLatency(INTERVAL_MILLIS);
            } else {
                //设置间隔时间
                builder.setPeriodic(INTERVAL_MILLIS);
            }
            //设备重启之后你的任务是否还要继续执行
            builder.setPersisted(true);
            //设置deadline，若到期还没有达到规定的条件则会开始执行
//            builder.setOverrideDeadline(MAXDELAY_MILLIS);
            JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            int scheduleResult = jobScheduler.schedule(builder.build());
            Log.i(TAG, "startJobScheduler scheduleResult = " + scheduleResult);
        } catch (Exception e) {
            Log.e(TAG, "startJobScheduler Exception", e);
        }
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Message m = Message.obtain();
        m.obj = params;
        mHandler.sendMessage(m);
        Log.e(TAG, "onStartJob");
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        mHandler.removeCallbacksAndMessages(null);
        startService(mContext);
        Log.e(TAG, "onStopJob");
        return false;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.i(TAG, "LiveService handleMessage");
            JobParameters param = (JobParameters) msg.obj;
            jobFinished(param, true);
        }
    };

}

