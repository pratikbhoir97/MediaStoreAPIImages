package com.example.mediastoreimagesapi;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import java.util.ArrayList;

public class MediaStoreAPIUitility {

    public ArrayList<String> loadImageFolderName(Context context) {
        ArrayList<Uri> bucketId = new ArrayList<>();
        ArrayList<String> bucketName = new ArrayList<>();
        Uri collection;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        } else {
            collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        String projection[]={ MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.BUCKET_ID};
        Cursor imageCursor =  context.getContentResolver().query(collection, projection, null, null, null);
        if (imageCursor!=null){
            while(imageCursor.moveToNext()){

                if (!bucketId.contains(Uri.parse(imageCursor.getString(1)))) {
                    bucketName.add(imageCursor.getString(0));
                    bucketId.add(Uri.parse(imageCursor.getString(1)));
                }
            }
        }
        //loadImagesFromFolder(bucketName.get(0));
        return bucketName;
    }





    public ArrayList<Uri> loadImagesFromFolder(String folderName, Context context) {
        ArrayList<Uri> imagesByFolder = new ArrayList<>();
        imagesByFolder.clear();
        ArrayList<Uri> arrayList = new ArrayList<>();
        Uri collection;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        } else {
            collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID
        };
        String selection = MediaStore.Images.Media.BUCKET_DISPLAY_NAME +
                " = ?";
        String[] selectionArgs = new String[]{
                folderName
        };
        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";
        Cursor cursor = context.getContentResolver().query(collection, projection, selection, selectionArgs, sortOrder);
        int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
        while (cursor.moveToNext()){
            long id = cursor.getLong(idColumn);
            Uri contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
            imagesByFolder.add(contentUri);
        }
       // Toast.makeText(activity, "te "+imagesByFolder.size(), Toast.LENGTH_SHORT).show();
        return imagesByFolder;
    }


    public ArrayList<Uri> loadAllImages(Context context) {
        ArrayList<Uri> imagesByFolder = new ArrayList<>();
        imagesByFolder.clear();
        ArrayList<Uri> arrayList = new ArrayList<>();
        Uri collection;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        } else {
            collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }
        String[] projection = new String[]{
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.BUCKET_ID
        };
//        String selection = MediaStore.Images.Media.BUCKET_DISPLAY_NAME +
//                " = ?";
//        String[] selectionArgs = new String[]{
//                folderName
//        };
        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";
        Cursor cursor = context.getContentResolver().query(collection, projection, null, null, sortOrder);
        int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
        while (cursor.moveToNext()){
            long id = cursor.getLong(idColumn);
            Uri contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
            imagesByFolder.add(contentUri);
        }
        // Toast.makeText(activity, "te "+imagesByFolder.size(), Toast.LENGTH_SHORT).show();
        return imagesByFolder;
    }


}
