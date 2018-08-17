package com.example.biul.kana;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Katakana extends Fragment implements AdapterView.OnItemClickListener {

    private View v;
    private AdView mAdView;
    private static Integer[] animasi =
            {
                    R.drawable.ika,R.drawable.iki,R.drawable.iku,R.drawable.ike,R.drawable.iko,
                    R.drawable.ikka,R.drawable.ikki,R.drawable.ikku, R.drawable.ikke, R.drawable.ikko,
                    R.drawable.iksa,R.drawable.ikshi,R.drawable.iksu,R.drawable.ikse,R.drawable.ikso,
                    R.drawable.ikta,R.drawable.ikchi,R.drawable.iktsu,R.drawable.ikte,R.drawable.ikto,
                    R.drawable.ikna,R.drawable.ikni,R.drawable.iknu,R.drawable.ikne,R.drawable.ikno,
                    R.drawable.ikha,R.drawable.ikhi,R.drawable.ikfu,R.drawable.ikhe,R.drawable.ikho,
                    R.drawable.ikma,R.drawable.ikmi,R.drawable.ikmu,R.drawable.ikme,R.drawable.ikmo,
                    R.drawable.ikra,R.drawable.ikri,R.drawable.ikru,R.drawable.ikre,R.drawable.ikro,
                    R.drawable.ikya,R.drawable.ikyu,R.drawable.ikyo,R.drawable.ikwa,R.drawable.ikwo, R.drawable.ikn,
            };
    private static String[] judul =
            {
                    "Katakana A","Katakana I","Katakana U","Katakana E","Katakana O",
                    "Katakana Ka","Katakana Ki","Katakana Ku","Katakana Ke", "Katakana Ko",
                    "Katakana Sa","Katakana Shi","Katakana Su","Katakana Se","Katakana So",
                    "Katakana Ta","Katakana Chi","Katakana Tsu","Katakana Te","Katakana To",
                    "Katakana Na","Katakana Ni","Katakana Nu","Katakana Ne","Katakana No",
                    "Katakana Ha","Katakana Hi","Katakana Fu","Katakana He","Katakana Ho",
                    "Katakana Ma","Katakana Mi","Katakana Mu","Katakana Me","Katakana Mo",
                    "Katakana Ra","Katakana Ri","Katakana Ru","Katakana Re","Katakana Ro",
                    "Katakana Ya","Katakana Yu","Katakana Yo","Katakana Wa","Katakana Wo","Katakana N",
            };
    public Katakana() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.lay_katakana, container, false);
        GridView gridView = (GridView) v.findViewById(R.id.gridview_followed);
        gridView.setAdapter(new ImageAdapterkatakana(v.getContext()));
        gridView.setOnItemClickListener(this);

        MobileAds.initialize(v.getContext(), "ca-app-pub-9261861370906826~4018739519");

        mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tampil_hurufbaru fragment = new tampil_hurufbaru();
        Bundle bundle = new Bundle();
        bundle.putInt("huruf", animasi[position]); // Put anything what you want
        fragment.setArguments(bundle);

        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        //GifView.gambargif=animasi[position];
        ((MainActivity)getActivity()).namatittlebar(judul[position]);

    }
}
