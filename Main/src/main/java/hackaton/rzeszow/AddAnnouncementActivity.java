package hackaton.rzeszow;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.File;

import app.tools.utils.ImageManager;

public class AddAnnouncementActivity extends AppCompatActivity {

    private static final int GALLERY_IMAGE_REQUEST_CODE = 4231;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcment_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dodaj ogłoszenie");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

//        ImageManager.selectPhoto(getBaseActivity(), "originalCameraImage");
//        @Override
//        public void fromGallery() {
//            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//            startActivityForResult(intent, BaseActivity.GALLERY_IMAGE_REQUEST_CODE);
//        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        /*
        * Obraz kamera
        * */
        if (requestCode == ImageManager.SELECT_CAMERA_PHOTO_ACTION) {
            try {
                Bitmap scaledCameraImage = ImageManager.getScalePhotoBitmap(this, ImageManager.originalPhotoPath, "scaledCameraImage", 800, 800);
                File photoFile = new File(new File(getExternalFilesDir(null), "photos"), "scaledCameraImage.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*
        * Obraz galeria
        * */
        if (requestCode == AddAnnouncementActivity.GALLERY_IMAGE_REQUEST_CODE) {
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.ImageColumns.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                String picturePath = cursor.getString(0);
                cursor.close();

                Bitmap scaledCameraImage = ImageManager.getScalePhotoBitmap(this, picturePath, "scaledGalleryImage", 800, 800);
                File photoFile = new File(new File(getExternalFilesDir(null), "photos"), "scaledGalleryImage.jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}