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
public class ImageAdapterkatakana extends BaseAdapter {
    private Context mContext;


    public ImageAdapterkatakana(Context c)
    {
        mContext = c;
    }

    public static Integer[] mThumbIds =
            {
                    //Gambar-gambar yang ada disimpan dalam array

                    R.drawable.kaa, R.drawable.kai, R.drawable.kau, R.drawable.kae , R.drawable.kao,
                    R.drawable.kaka, R.drawable.kaki, R.drawable.kaku, R.drawable.kake , R.drawable.kako,
                    R.drawable.kasa, R.drawable.kashi, R.drawable.kasu, R.drawable.kase , R.drawable.kaso,
                    R.drawable.kata, R.drawable.kachi, R.drawable.katsu, R.drawable.kate , R.drawable.kato,
                    R.drawable.kana, R.drawable.kani, R.drawable.kanu, R.drawable.kane , R.drawable.kano,
                    R.drawable.kaha, R.drawable.kahi, R.drawable.kafu, R.drawable.kahe , R.drawable.kaho,
                    R.drawable.kama, R.drawable.kami, R.drawable.kamu, R.drawable.kame , R.drawable.kamo,
                    R.drawable.kara, R.drawable.kari, R.drawable.karu, R.drawable.kare , R.drawable.karo,
                    R.drawable.kaya, R.drawable.kayu, R.drawable.kayo, R.drawable.kawa , R.drawable.kawo,
                    R.drawable.kan,
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