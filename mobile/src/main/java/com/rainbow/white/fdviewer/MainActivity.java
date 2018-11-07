package com.rainbow.white.fdviewer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private final static String TAG = "MainActivity";

    private final int GALLERY_REQUEST_CODE = 0001;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = (ListView) findViewById(R.id.viewer_list);
        mListView.setOnItemClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

    }

    private void selectImage(Uri image) {
        String imagePath = getPathFromUri(image);

        ExifInterface exif = null;

        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int degree = exifOrientationToDegrees(orientation);

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);


    }

    private int exifOrientationToDegrees(int orientation) {
        if( orientation == ExifInterface.ORIENTATION_ROTATE_90 ){
            return 90;
        } else if( orientation == ExifInterface.ORIENTATION_ROTATE_180 ) {
            return 180;
        } else if( orientation == ExifInterface.ORIENTATION_ROTATE_270 ) {
            return 270;
        }

        return 0;
    }

    private String getPathFromUri(Uri content) {
        int index = 0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(content, proj, null, null, null);

        if( cursor.moveToFirst() ) {
            index = cursor.getColumnIndexOrThrow(proj.toString());
        }

        return  cursor.getString(index);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        TODO : ItemView Activity로 이동
//               Android Auto 연동 된 상태에서는 사진 전달
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == GALLERY_REQUEST_CODE ) {
            if( resultCode == RESULT_OK ) {
//                Image mListView에 add
                selectImage(data.getData());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
