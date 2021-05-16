package com.appshat.kherokhata.Notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.appshat.kherokhata.OldAcrivity.Constants;
import com.appshat.kherokhata.OldAcrivity.MainActivity;
import com.appshat.kherokhata.R;

public class MyNotificationManager {
    private static MyNotificationManager mInstance;
    private final Context mctx;

    private MyNotificationManager(Context context) {

        mctx = context;
    }

    public static synchronized MyNotificationManager getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new MyNotificationManager(context);

        }
        return mInstance;
    }

    public void displayNotification(String title, String body) {

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mctx, Constants.CHANNEL_ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(title)
                .setContentText(body);

        Intent intent = new Intent(mctx, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mctx, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) mctx.getSystemService(Context.NOTIFICATION_SERVICE);

        if (mNotificationManager != null) {
            mNotificationManager.notify(1, mBuilder.build());
        }

    }


}
