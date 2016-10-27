package com.betaussoft.androidmpermissions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCamera, btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = (Button) findViewById(R.id.btnCall);
        btnCamera = (Button) findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // calling method
                askForPermission(Manifest.permission.CAMERA, 2);

            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // calling method
                askForPermission(Manifest.permission.CALL_PHONE, 1);


            }
        });


    }



    //  1.	Check permissions.
    private void askForPermission(String permission, Integer requestCode){

         if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
             //  2.	Request Permissions.
             //permission Granted
              if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

               ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

              }else{

               }
         }else{
             // Already have permissions.
             //  2.	Request Permissions.

             if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                 ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

             }else{

             }
         }
    }


    // 3.	Permission Request responding.


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            switch (requestCode){
                case 1:
                    //call
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        Intent  callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "01855333129"));
                        startActivity(callIntent);
                    }else{
                        //permission denied
                    }
                    break;

                case 2:
                    // camera
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                        startActivity(intent);
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, 1);
                    }else{
                        //permission denied
                    }

                    break;
            }

        }else{

        }



    }
}
