package com.example.myapplication65;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button scanbutton;
ListView scanlistview;
ArrayList<String> stringArrayList=new ArrayList<String>();
ArrayAdapter<String> arrayAdapter;
BluetoothAdapter myAdapter=BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanbutton=(Button) findViewById(R.id.scanbutton);
        scanlistview=(ListView) findViewById(R.id.scanlistview);

        scanbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.startDiscovery();

            }
        });
        IntentFilter intentFilter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(myReceiever,intentFilter);
        arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,stringArrayList);
        scanlistview.setAdapter(arrayAdapter);
    }
    BroadcastReceiver myReceiever=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                stringArrayList.add(device.getName());
                arrayAdapter.notifyDataSetChanged();
            }
        }
    };
}
