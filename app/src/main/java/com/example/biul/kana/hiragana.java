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
public class hiragana extends Fragment implements AdapterView.OnItemClickListener {
    private View v;
    private AdView mAdView;
    private static Integer[] animasi =
            {
                    R.drawable.iha,R.drawable.ihi,R.drawable.ihu,R.drawable.ihe,R.drawable.iho,
                    R.drawable.ihka,R.drawable.ihki,R.drawable.ihku, R.drawable.ihke, R.drawable.ihko,
                    R.drawable.ihsa,R.drawable.ihshi,R.drawable.ihsu,R.drawable.ihse,R.drawable.ihso,
                    R.drawable.ihta,R.drawable.ihchi,R.drawable.ihtsu,R.drawable.ihte,R.drawable.ihto,
                    R.drawable.ihna,R.drawable.ihni,R.drawable.ihnu,R.drawable.ihne,R.drawable.ihno,
                    R.drawable.ihha,R.drawable.ihhi,R.drawable.ihfu,R.drawable.ihhe,R.drawable.ihho,
                    R.drawable.ihma,R.drawable.ihmi,R.drawable.ihmu,R.drawable.ihme,R.drawable.ihmo,
                    R.drawable.ihra,R.drawable.ihri,R.drawable.ihru,R.drawable.ihre,R.drawable.ihro,
                    R.drawable.ihya,R.drawable.ihyu,R.drawable.ihyo,R.drawable.ihwa,R.drawable.ihwo, R.drawable.ihn,


            };
    private static String[] judul =
            {
                    "Hiragana A","Hiragana I","Hiragana U","Hiragana E","Hiragana O",
                    "Hiragana Ka","Hiragana Ki","Hiragana Ku","Hiragana Ke", "Hiragana Ko",
                    "Hiragana Sa","Hiragana Shi","Hiragana Su","Hiragana Se","Hiragana So",
                    "Hiragana Ta","Hiragana Chi","Hiragana Tsu","Hiragana Te","Hiragana To",
                    "Hiragana Na","Hiragana Ni","Hiragana Nu","Hiragana Ne","Hiragana No",
                    "Hiragana Ha","Hiragana Hi","Hiragana Fu","Hiragana He","Hiragana Ho",
                    "Hiragana Ma","Hiragana Mi","Hiragana Mu","Hiragana Me","Hiragana Mo",
                    "Hiragana Ra","Hiragana Ri","Hiragana Ru","Hiragana Re","Hiragana Ro",
                    "Hiragana Ya","Hiragana Yu","Hiragana Yo","Hiragana Wa","Hiragana Wo","Hiragana N",
            };
    public hiragana() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.lay_hiragana, container, false);
        GridView gridView = (GridView) v.findViewById(R.id.gridview_followed);
        gridView.setAdapter(new ImageAdapterHiragana(v.getContext()));
        gridView.setOnItemClickListener(this);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(v.getContext(), "ca-app-pub-9261861370906826~4018739519");

        mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        return v;
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {


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
