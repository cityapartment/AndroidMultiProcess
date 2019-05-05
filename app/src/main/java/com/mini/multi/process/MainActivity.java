package com.mini.multi.process;
/**
 * @author leroy
 * @description:
 * @data: 19/4/20
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mini.client.AIDLClient;
import com.mini.library.utils.UserInfoSP;
import com.mini.server.RemoteService;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView clientShowText;

    AIDLClient aidlClient = new AIDLClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aidlClient.startRemoteService(MainActivity.this, RemoteService.class);

        button = findViewById(R.id.mainButton);
        clientShowText = findViewById(R.id.clientShowText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRemoteService();
//                launchRemoteActivity();
            }
        });

    }

    public void launchRemoteActivity() {
        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT);
        startActivity(intent);
    }

    //启动远端进程
    public void launchRemoteService() {
        aidlClient.requestServerMethod();
//        aidlClient.sendClientMessage("MainActivity");
    }
}
