package pl.pjatk.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kamilw on 30.10.2016.
 */
public class MyReceiver extends BroadcastReceiver {

    public static int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getExtras().getString("test");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        NOTIFICATION_ID += 1;


        PackageManager pm = context.getPackageManager();
        Intent LaunchIntent = null;
        String apppack = "pl.pjatk.guiapp";
        String name = "";
        try {
            if (pm != null) {
                ApplicationInfo app = context.getPackageManager().getApplicationInfo(apppack, 0);
                name = (String) pm.getApplicationLabel(app);
                LaunchIntent = pm.getLaunchIntentForPackage(apppack);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Intent in = LaunchIntent;

        mBuilder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("My notification 2")
                .setContentText(state);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(in);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}
