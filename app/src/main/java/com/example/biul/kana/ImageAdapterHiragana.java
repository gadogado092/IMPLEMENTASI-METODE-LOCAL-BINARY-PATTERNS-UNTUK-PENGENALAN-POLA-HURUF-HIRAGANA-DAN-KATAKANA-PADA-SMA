package com.example.biul.kana;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.biul.kana.R;

/**
 * Created by Biul on 18/03/2016.
 */
public class ImageAdapterHiragana extends BaseAdapter {
    private Context mContext;


    public ImageAdapterHiragana(Context c)
    {
        mContext = c;
    }

    public static Integer[] mThumbIds =
            {
                    //Gambar-gambar yang ada disimpan dalam array

                    R.drawable.hia, R.drawable.hii, R.drawable.hiu, R.drawable.hie , R.drawable.hio,
                    R.drawable.hika, R.drawable.hiki, R.drawable.hiku, R.drawable.hike , R.drawable.hiko,
                    R.drawable.hisa, R.drawable.hishi, R.drawable.hisu, R.drawable.hise , R.drawable.hiso,
                    R.drawable.hita, R.drawable.hichi, R.drawable.hitsu, R.drawable.hite , R.drawable.hito,
                    R.drawable.hina, R.drawable.hini, R.drawable.hinu, R.drawable.hine , R.drawable.hino,
                    R.drawable.hiha, R.drawable.hihi, R.drawable.hifu, R.drawable.hihe , R.drawable.hiho,
                    R.drawable.hima, R.drawable.himi, R.drawable.himu, R.drawable.hime , R.drawable.himo,
                    R.drawable.hira, R.drawable.hiri, R.drawable.hiru, R.drawable.hire , R.drawable.hiro,
                    R.drawable.hiya, R.drawable.hiyu, R.drawable.hiyo, R.drawable.hiwa , R.drawable.hiwo,
                    R.drawable.hin,
            };



    @Override
    public int getCount() {
        // Jumlah total gambar
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Mengambil satu gambar dari gallery
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 93));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setPadding(4, 4, 4, 4);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}