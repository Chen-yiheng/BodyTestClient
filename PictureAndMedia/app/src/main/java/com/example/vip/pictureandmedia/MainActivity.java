package com.example.vip.pictureandmedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
	public final int TAKE_PHOTO = 1;
	public final int CROP_PHOTO = 2;
	private Button takePhoto, fromAlbum;
	private ImageView imageView;
	private Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		takePhoto = (Button) findViewById(R.id.take_photo);
		imageView = (ImageView) findViewById(R.id.image_view);
		fromAlbum = (Button) findViewById(R.id.from_album);
		takePhoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				createImageUri();
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, TAKE_PHOTO);
			}
		});

		fromAlbum.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				createImageUri();
				Intent intent = new Intent("android.intent.action.GET_CONTENT");
				intent.putExtra("scale", true);
//				intent.putExtra("crop", true);
				//下三句与上一句同等，不过一般手机用上一句无法实现其功能
				Bundle bundle = new Bundle();
				bundle.putString("crop", "true");
				intent.putExtras(bundle);
				intent.setType("image/*");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, CROP_PHOTO);
			}
		});


	}

	private void createImageUri() {
		File file = new File(Environment.getExternalStorageDirectory(), "tempImage" +
			   ".jpg");
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageUri = Uri.fromFile(file);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case TAKE_PHOTO:
				if (resultCode == RESULT_OK) {
					Intent intent = new Intent("com.android.camera.action.CROP");
					intent.setDataAndType(imageUri, "image/*");
					intent.putExtra("scale", true);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
					startActivityForResult(intent, CROP_PHOTO); // 启动裁剪程序
				}
				break;
			case CROP_PHOTO:
				if (resultCode == RESULT_OK) {
					try {
						Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
							   .openInputStream(imageUri));
						imageView.setImageBitmap(bitmap);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;
		}

	}
}
