package com.appshat.fmcgapp;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (true){
            String cn = intent.getExtras().getString("clientname");
            String cml= intent.getExtras().getString("clientmobilenumber");
            String ca = intent.getExtras().getString("clientamount");
            String cat = intent.getExtras().getString("accounttype");
            String date = intent.getExtras().getString("date");
            String title=context.getResources().getString(R.string.reminder);
            String description = "Name: " +cn + "\n"+"Mobile no: " + cml + "\n"+"Sales or Purchase : " + cat + "\n"+"Amount : " + ca +"\n"+"Date : " + date;
            Log.e("hoho","tess");
            Calendar now = GregorianCalendar.getInstance();
            int dayOfWeek = now.get(Calendar.DATE);
            //declare an id for your notification
            //id is used in many things especially when setting action buttons and their intents
            int notificationId = 0;
            //init notification and declare specifications
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"NOTIFICATION_CHANEL")
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle(title)
                    .setContentText(description)
                    .setAutoCancel(true).setDefaults(NotificationCompat.DEFAULT_ALL);//set a tone when notification appears
            Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            builder.setSound(path);

            Intent i = new Intent(context,Broadcast_Activity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra("description",description);

            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);



            //call notification manager so it can build and deliver the notification to the OS
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            //Android 8 introduced a new requirement of setting the channelId property by using a NotificationChannel.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "YOUR_CHANNEL_ID";
                NotificationChannel channel = new NotificationChannel(channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }

            notificationManager.notify(notificationId,builder.build());
        }

    }


}