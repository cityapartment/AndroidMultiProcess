package com.mini.multi.process;
/**
 * @author leroy
 * @description:
 * @data: 19/4/20
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mini.client.AIDLClient;
import com.mini.server.RemoteService;

public class MainActivity extends AppCompatActivity {

    private Button button;
    AIDLClient aidlClient = new AIDLClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aidlClient.startRemoteService(MainActivity.this, RemoteService.class);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRemoteService();
            }
        });
    }


    //启动远端进程
    public void launchRemoteService() {
        aidlClient.sendClientMessage("MainActivity");
    }
}
