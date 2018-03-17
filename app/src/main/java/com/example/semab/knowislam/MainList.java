package com.example.semab.knowislam;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainList extends TabActivity{

    private static final String AYAT = "AYAT";
    private static final String HADESS = "HADESS";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        TabHost tabHost = getTabHost();

        // AYAT Tab
        TabSpec ayat = tabHost.newTabSpec(AYAT);
        ayat.setIndicator(AYAT, getResources().getDrawable(R.drawable.icon_inbox));
        Intent inboxIntent = new Intent(this, AyatActivity.class);
        // Tab Content
        ayat.setContent(inboxIntent);

        // Outbox Tab
        TabSpec hadees = tabHost.newTabSpec(HADESS);
        hadees.setIndicator(HADESS, getResources().getDrawable(R.drawable.icon_inbox));
        Intent outboxIntent = new Intent(this, HadessActivity.class);
        hadees.setContent(outboxIntent);




        // Adding all TabSpec to TabHost
        tabHost.addTab(ayat); // Adding Inbox tab
        tabHost.addTab(hadees); // Adding Outbox tab

    }
}
