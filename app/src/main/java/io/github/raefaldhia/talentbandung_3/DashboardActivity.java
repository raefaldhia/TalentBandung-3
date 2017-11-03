package io.github.raefaldhia.talentbandung_3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raefaldhia on 10/31/17.
 */

public class DashboardActivity extends AppCompatActivity {
    SQLiteOpenHelper dbHandler;
    List<DashboardItem> itemList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

         dbHandler = new SQLiteOpenHelper(this, "DataUser", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE USER (Id INTEGER PRIMARY KEY, Username TEXT, Email TEXT)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXIST USER");
                onCreate(db);
            }
        };

        DashboardItem item;

        Cursor cursor = dbHandler.getWritableDatabase().rawQuery("SELECT  * FROM USER", null);
        if (cursor.moveToFirst()) {
            do {
                item = new DashboardItem(cursor.getString(1), cursor.getString(2));
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        DashboardItemAdapter itemAdapter = new DashboardItemAdapter(itemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);

        itemAdapter.notifyDataSetChanged();

        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(DashboardActivity.this, AddActivity.class), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK)
        {
            ContentValues values = new ContentValues();

            values.put("Username", data.getStringExtra("Username"));
            values.put("Email", data.getStringExtra("Email"));

            dbHandler.getWritableDatabase().insert("USER", null, values);
            itemList.add(new DashboardItem(data.getStringExtra("Username"), data.getStringExtra("Email")));
        }
    };
}
