package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RevertedActivity extends AppCompatActivity {
    NotificationManager notificationManager;
    TextView revertedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverted_text);
        revertedText = findViewById(R.id.revertedTextView);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            String text = extras.getString("text");
            revertedText.setText(revertText(text));
        }

        createNotificationChannel();
        sendNotification( "onCreate: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sendNotification( "onDestroy: ");
    }

    public void goBackToMainActivity(View view) {
        finish();
    }

    protected String revertText(String string) {
        StringBuilder builder = new StringBuilder()
                .append(string)
                .reverse();
        return builder.toString();
    }

    protected void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel("channelID", getPackageName(), NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(getLocalClassName());
        notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    protected void sendNotification(String content) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Notification notification = new Notification.Builder(this, "channelID")
                .setSmallIcon(R.drawable.cat_1583522)
                .setContentTitle(RevertedActivity.class.getSimpleName())
                .setContentText(content+formatter.format(localDateTime))
                .build();
        notificationManager.notify((int)System.currentTimeMillis(), notification);
    }
}
