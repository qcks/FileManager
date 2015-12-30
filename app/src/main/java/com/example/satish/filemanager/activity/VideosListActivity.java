package com.example.satish.filemanager.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import com.example.satish.filemanager.R;
import com.example.satish.filemanager.adapter.VideoListAdapter;
import com.example.satish.filemanager.model.AudioListModel;

import java.util.ArrayList;

/**
 * Created by Satish on 28-12-2015.
 */
public class VideosListActivity extends AppCompatActivity {
    private ArrayList<AudioListModel> videoListModelsArray;
    private Toolbar toolbar;
    private VideoListAdapter audioListAdapter;
    private ListView listview;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_list);
        listview = (ListView) findViewById(R.id.audio_listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        videoListModelsArray = new ArrayList<>();
        getVideoList();
        audioListAdapter = new VideoListAdapter(this, videoListModelsArray);
        listview.setAdapter(audioListAdapter);

    }

    private void getVideoList() {
        final Cursor mCursor = managedQuery(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.DATA}, null, null,
                "LOWER(" + MediaStore.Video.Media.TITLE + ") ASC");
        Log.d("length is", "" + mCursor.getCount());
        if (mCursor.moveToFirst()) {
            do {
                AudioListModel audioListModel = new AudioListModel();
                audioListModel.setAudio_name(mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));
                audioListModel.setAudio_file_path(mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                videoListModelsArray.add(audioListModel);
            } while (mCursor.moveToNext());
        }
        mCursor.close();
    }
}

