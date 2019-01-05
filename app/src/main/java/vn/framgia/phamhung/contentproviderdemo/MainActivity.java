package vn.framgia.phamhung.contentproviderdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {
    public static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_CONTACTS,
            Manifest.permission.CALL_PHONE};
    public static final int ALL_PERMISSIONS = 101;
    public static final String STRING_PHONE ="tel:";
    private List<MyContact> mMyContacts = new ArrayList<>();
    private MyAdapter.OnItemClickListener mListener= this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, PERMISSIONS, ALL_PERMISSIONS);
        MyContentProvider myContentProvider = new MyContentProvider();
        mMyContacts.addAll(myContentProvider.getContact(this));
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        MyAdapter myAdapter = new MyAdapter(mMyContacts,mListener);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(STRING_PHONE+phoneNumber));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(callIntent);
    }
}
