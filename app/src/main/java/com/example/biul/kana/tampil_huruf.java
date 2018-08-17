package com.example.biul.kana;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class tampil_huruf extends Fragment implements View.OnClickListener {

    FrameLayout frm_layout;
    View mView;
    Bitmap mBitmap;


    private View v;


    private DrawView drawview;
    private MainActivity main;
    public tampil_huruf() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       v= inflater.inflate(R.layout.lay_tampil_huruf, container, false);




        drawview = new DrawView(getContext());
        main = new MainActivity();


         frm_layout=(FrameLayout) v.findViewById(R.id.main_frame);
         frm_layout.addView(drawview);




        FloatingActionButton undo = (FloatingActionButton)v.findViewById(R.id.fab);
        undo.setOnClickListener(this);

        FloatingActionButton clear = (FloatingActionButton)v.findViewById(R.id.clear);
        clear.setOnClickListener(this);

        FloatingActionButton menu = (FloatingActionButton)v.findViewById(R.id.menu);
        menu.setOnClickListener(this);


        return v;

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                drawview.onClickUndo();
                break;
            case R.id.clear:
                drawview.onClickClear();
                break;
            case R.id.menu:
                ((MainActivity)getActivity()).namatittlebar("Pilih Huruf");
                if (getFragmentManager().getBackStackEntryCount() == 0) {
                    main.finish();
                } else {
                    getFragmentManager().popBackStack();
                }
                break;


           /* case R.id.proses:

                drawview.setDrawingCacheEnabled(true);

                mBitmap=Bitmap.createBitmap(drawview.getDrawingCache());
                Canvas canvas = new Canvas(mBitmap);

               // drawview.draw(canvas);
                Simpanasli(mBitmap);
                drawview.setDrawingCacheEnabled(false);

            break;*/
        }
    }




    public void Simpanasli(Bitmap asli) {


        /*try {
            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOutputStream = null;
            File file = new File(path + "/Captures/", "screen.jpg");
            fOutputStream = new FileOutputStream(file);

            asli.compress(Bitmap.CompressFormat.JPEG, 100, fOutputStream);

            fOutputStream.flush();
            fOutputStream.close();

            MediaStore.Images.Media.insertImage(getContext().getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
            //MediaStore.Images.Media.insertImage()
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Save Failed", Toast.LENGTH_SHORT).show();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Save Failed", Toast.LENGTH_SHORT).show();
            return;
        }*/

        /*
        ContextWrapper cw = new ContextWrapper(getContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("Kana", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            asli.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try
        {


            File f = new File(Environment.getExternalStorageDirectory()
                    + File.separator +  "asli.bmp");

                f.createNewFile();

            FileOutputStream os = new FileOutputStream(f);
            os = new FileOutputStream(f);
            asli.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.close();




            Toast pesan = Toast.makeText(getActivity(), "Gambar Telah Disimpan", Toast.LENGTH_LONG);
            pesan.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }





    }



}
