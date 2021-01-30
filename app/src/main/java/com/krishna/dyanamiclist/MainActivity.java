package com.krishna.dyanamiclist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final int camera_perm_code = 101;
    public static final int camera_request_code = 102;

    ListView listViewStd;
    List<model> studentList;
    listAdapter2 adapterStudent;

    EditText e1,e2,e3;
    ImageView imageView;
    ImageView img;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewStd = findViewById(R.id.listView);
        e1 = findViewById(R.id.edit1);
        e2 = findViewById(R.id.edit2);
        e3 = findViewById(R.id.edit3);

        imageView = findViewById(R.id.imgview);
        img = findViewById(R.id.imgview);
        cardView = findViewById(R.id.tab4);
        imageView.setOnClickListener(new View.OnClickListener() {   //capture image from camera
            @Override
            public void onClick(View v) {
                askcamerapermission();
            }
        });

        studentList = new ArrayList<>();


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (e1.getText().toString().isEmpty()==true && e2.getText().toString().isEmpty()==true && e3.getText().toString().isEmpty()==true)
                {
                    Toast.makeText(MainActivity.this, "Please Enter Name,Branch and College name", Toast.LENGTH_SHORT).show();
                }
                else if (e1.getText().toString().isEmpty()==true && e2.getText().toString().isEmpty()==true )
                {
                    Toast.makeText(MainActivity.this, "Please Enter Name and Branch", Toast.LENGTH_SHORT).show();
                }
                else if (e2.getText().toString().isEmpty()==true && e3.getText().toString().isEmpty()==true )
                {
                    Toast.makeText(MainActivity.this, "Please Enter Branch and College", Toast.LENGTH_SHORT).show();
                }
                else if (e3.getText().toString().isEmpty()==true && e1.getText().toString().isEmpty()==true )
                {
                    Toast.makeText(MainActivity.this, "Please Enter Name and College Name", Toast.LENGTH_SHORT).show();
                }
                else if (e1.getText().toString().isEmpty()==true )
                {
                    Toast.makeText(MainActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if (e2.getText().toString().isEmpty()==true )
                {
                    Toast.makeText(MainActivity.this, "Please Enter Branch", Toast.LENGTH_SHORT).show();
                }
                else if (e3.getText().toString().isEmpty()==true )
                {
                    Toast.makeText(MainActivity.this, "Please Enter College Name", Toast.LENGTH_SHORT).show();
                }
                else  if(img == null)
                {
                    Toast.makeText(MainActivity.this,"Please Capture Image",Toast.LENGTH_LONG).show();
                }

                else
                {

                    byte[] get_byt_array = new byte[0];

                    try
                    {
                        get_byt_array = convert_byet_array(img);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this,"Error"+e,Toast.LENGTH_LONG).show();
                    }

                    model student1 = new model();
                    student1.setRoleId(e3.getText().toString().trim());
                    student1.setfName(e1.getText().toString().trim());
                    student1.setlName("Java");
                    student1.setBranch(e2.getText().toString().trim());
                    student1.setImageView(get_byt_array);
                    studentList.add(student1);

                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    img.setImageResource(R.drawable.ic_baseline_person_24);

                }


            }
        });


        adapterStudent = new listAdapter2(this, studentList);

        listViewStd.setAdapter(adapterStudent);

        listViewStd.deferNotifyDataSetChanged();

        listViewStd.setOnItemClickListener(new ListViewClick());

    }


    private void askcamerapermission() {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)  //if permission is not given
        {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA},camera_perm_code);  //then ask permission for camera

        }
        else
        {
            openCamera(); //if permission is given then open camera
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == camera_perm_code)           //check where request code is equal to
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                // we can capture image now
                openCamera();
            }
            else
            {
                Toast.makeText(this,"Click Above To Click Image",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);      //through intent capture image
        startActivityForResult(intent, camera_request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == camera_request_code) {
            Bitmap image = (Bitmap) data.getExtras().get("data");      //set capture image to imageview
            img.setImageBitmap(image);

        }

    }

    byte[] convert_byet_array(ImageView im)
    {
        Bitmap image = ((BitmapDrawable) im.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytearray = stream.toByteArray();
        return bytearray;
    }


    class ListViewClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }
}