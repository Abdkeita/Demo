package com.example.abd.intermediate_challenge.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abd.intermediate_challenge.R;

/**
 * Created by abd on 9/14/2017.
 */

public class DetailActivity extends AppCompatActivity {
    TextView Link, Username;
    Toolbar mActionToolbar;
    ImageView imageView;

    public void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.user_image_header);
        Username = (TextView) findViewById(R.id.username);
        Link = (TextView) findViewById(R.id.link);

        String username = getIntent().getExtras().getString("login");
        String avatarurl = getIntent().getExtras().getString("Avatarurl");
        String link = getIntent().getExtras().getString("linkurl");

        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        Username.setText(username);
        Glide.with(this)
                .load(avatarurl)
                .placeholder(R.drawable.loader)
                .into(imageView);

        getSupportActionBar().setTitle("User Detail");
    }

    // method for the Menu of the share icon
    private Intent createshareforecastIntent() {
        String username = getIntent().getExtras().getString("login");
        String link = getIntent().getExtras().getString("linkurl");
        Intent shareIntent = ShareCompat.IntentBuilder.from(this) // this call on any messaging application either facebook, whatsapp etc
                .setType("text/plain")
                .setText("Check out this awesome user @ " + username + ", " + link) // this is the message that will be sent to the receiver form the user.
                .getIntent();
        return shareIntent;
    }

    // Menu that handles the share icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createshareforecastIntent());
        return true;
    }
}
