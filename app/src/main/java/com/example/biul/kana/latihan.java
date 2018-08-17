package com.example.biul.kana;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class latihan extends Fragment  implements View.OnClickListener {
    private View v;
    public TextView tvsoal;
    public FrameLayout frm_layout;
    private DrawView drawview;
    private Bitmap mBitmap,bitmap1,bitmap2,bitmap3,bitmap4,bitmap5,bitmap6,bitmap7,bitmap8;
    private String soal;
    private MediaPlayer benar;
    private MediaPlayer salah;
    private int k=1,acaksoal;
    ProgressBar Pbar;
    private AdView mAdView;

    private int next=0;
    private Boolean hasil=false;
    private String [] daftarsoal =
            {"Hiragana A","Hiragana I","Hiragana U","Hiragana E","Hiragana O",
             "Hiragana Ka","Hiragana Ki","Hiragana Ku","Hiragana Ke","Hiragana Ko",
             "Hiragana Sa","Hiragana Shi","Hiragana Su","Hiragana Se","Hiragana So",
             "Hiragana Ta","Hiragana Chi","Hiragana Tsu","Hiragana Te","Hiragana To",
             "Hiragana Na","Hiragana Ni","Hiragana Nu","Hiragana Ne","Hiragana No",
             "Hiragana Ha","Hiragana Hi","Hiragana Fu","Hiragana He","Hiragana Ho",
             "Hiragana Ma","Hiragana Mi","Hiragana Mu","Hiragana Me","Hiragana Mo",
             "Hiragana Ra","Hiragana Ri","Hiragana Ru","Hiragana Re","Hiragana Ro",
             "Hiragana Ya","Hiragana Yu","Hiragana Yo","Hiragana Wa","Hiragana Wo",
                    "Hiragana N",

                    "Katakana A","Katakana I","Katakana U","Katakana E","Katakana O",
                    "Katakana Ka","Katakana Ki","Katakana Ku","Katakana Ke","Katakana Ko",
                    "Katakana Sa","Katakana Shi","Katakana Su","Katakana Se","Katakana So",
                    "Katakana Ta","Katakana Chi","Katakana Tsu","Katakana Te","Katakana To",
                    "Katakana Na","Katakana Ni","Katakana Nu","Katakana Ne","Katakana No",
                    "Katakana Ha","Katakana Hi","Katakana Fu","Katakana He","Katakana Ho",
                    "Katakana Ma","Katakana Mi","Katakana Mu","Katakana Me","Katakana Mo",
                    "Katakana Ra","Katakana Ri","Katakana Ru","Katakana Re","Katakana Ro",
                    "Katakana Ya","Katakana Yu","Katakana Yo","Katakana Wa","Katakana Wo",
                    "Katakana N",
                                    };


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

                    R.drawable.ika,R.drawable.iki,R.drawable.iku,R.drawable.ike,R.drawable.iko,
                    R.drawable.ikka,R.drawable.ikki,R.drawable.ikku, R.drawable.ikke, R.drawable.ikko,
                    R.drawable.iksa,R.drawable.ikshi,R.drawable.iksu,R.drawable.ikse,R.drawable.ikso,
                    R.drawable.ikta,R.drawable.ikchi,R.drawable.iktsu,R.drawable.ikte,R.drawable.ikto,
                    R.drawable.ikna,R.drawable.ikni,R.drawable.iknu,R.drawable.ikne,R.drawable.ikno,
                    R.drawable.ikha,R.drawable.ikhi,R.drawable.ikfu,R.drawable.ikhe,R.drawable.ikho,
                    R.drawable.ikma,R.drawable.ikmi,R.drawable.ikmu,R.drawable.ikme,R.drawable.ikmo,
                    R.drawable.ikra,R.drawable.ikri,R.drawable.ikru,R.drawable.ikre,R.drawable.ikro,
                    R.drawable.ikya,R.drawable.ikyu,R.drawable.ikyo,R.drawable.ikwa,R.drawable.ikwo, R.drawable.ikn
            };
    private static Integer[][] database =
            {
                    {R.raw.ha1,R.raw.ha2,R.raw.ha3,R.raw.ha4,R.raw.ha5,R.raw.ha6,R.raw.ha7,R.raw.ha8},
                    {R.raw.hi1,R.raw.hi2,R.raw.hi3,R.raw.hi4,R.raw.hi5,R.raw.hi6,R.raw.hi7,R.raw.hi8},
                    {R.raw.hu1,R.raw.hu2,R.raw.hu3, R.raw.hu4,R.raw.hu5,R.raw.hu6,R.raw.hu7,R.raw.hu8},
                    {R.raw.he1,R.raw.he2,R.raw.he3,R.raw.he4,R.raw.he5,R.raw.he6,R.raw.he7,R.raw.he8},
                    {R.raw.ho1,R.raw.ho2,R.raw.ho3,R.raw.ho4,R.raw.ho5,R.raw.ho6,R.raw.ho7,R.raw.ho8},
                    {R.raw.hka1,R.raw.hka2,R.raw.hka3,R.raw.hka4,R.raw.hka5,R.raw.hka6,R.raw.hka7,R.raw.hka8},
                    {R.raw.hki1,R.raw.hki2,R.raw.hki3,R.raw.hki4,R.raw.hki5,R.raw.hki6,R.raw.hki7,R.raw.hki8},
                    {R.raw.hku1,R.raw.hku2,R.raw.hku3,R.raw.hku4,R.raw.hku5,R.raw.hku6,R.raw.hku7,R.raw.hku8},
                    {R.raw.hke1,R.raw.hke2,R.raw.hke3,R.raw.hke4,R.raw.hke5,R.raw.hke6,R.raw.hke7,R.raw.hke8},
                    {R.raw.hko1,R.raw.hko2,R.raw.hko3,R.raw.hko4,R.raw.hko5,R.raw.hko6,R.raw.hko7,R.raw.hko8},
                    {R.raw.hsa1,R.raw.hsa2,R.raw.hsa3,R.raw.hsa4,R.raw.hsa5,R.raw.hsa6,R.raw.hsa7,R.raw.hsa8},
                    {R.raw.hshi1,R.raw.hshi2,R.raw.hshi3,R.raw.hshi4,R.raw.hshi5,R.raw.hshi6,R.raw.hshi7,R.raw.hshi8},
                    {R.raw.hsu1,R.raw.hsu2,R.raw.hsu3,R.raw.hsu4,R.raw.hsu5,R.raw.hsu6,R.raw.hsu7,R.raw.hsu8},
                    {R.raw.hse1,R.raw.hse2,R.raw.hse3,R.raw.hse4,R.raw.hse5,R.raw.hse6,R.raw.hse7,R.raw.hse8},
                    {R.raw.hso1,R.raw.hso2,R.raw.hso3,R.raw.hso4,R.raw.hso5,R.raw.hso6,R.raw.hso7,R.raw.hso8},
                    {R.raw.hta1,R.raw.hta2,R.raw.hta3,R.raw.hta4,R.raw.hta5,R.raw.hta6,R.raw.hta7,R.raw.hta8},
                    {R.raw.hchi1,R.raw.hchi2,R.raw.hchi3,R.raw.hchi4,R.raw.hchi5,R.raw.hchi6,R.raw.hchi7,R.raw.hchi8},
                    {R.raw.htsu1,R.raw.htsu2,R.raw.htsu3,R.raw.htsu4,R.raw.htsu5,R.raw.htsu6,R.raw.htsu7,R.raw.htsu8},
                    {R.raw.hte1,R.raw.hte2,R.raw.hte3,R.raw.hte4,R.raw.hte5,R.raw.hte6,R.raw.hte7,R.raw.hte8},
                    {R.raw.hto1,R.raw.hto2,R.raw.hto3,R.raw.hto4,R.raw.hto5,R.raw.hto6,R.raw.hto7,R.raw.hto8},
                    {R.raw.hna1,R.raw.hna2,R.raw.hna3,R.raw.hna4,R.raw.hna5,R.raw.hna6,R.raw.hna7,R.raw.hna8},
                    {R.raw.hni1,R.raw.hni2,R.raw.hni3,R.raw.hni4,R.raw.hni5,R.raw.hni6,R.raw.hni7,R.raw.hni8},
                    {R.raw.hnu1,R.raw.hnu2,R.raw.hnu3,R.raw.hnu4,R.raw.hnu5,R.raw.hnu6,R.raw.hnu7,R.raw.hnu8},
                    {R.raw.hne1,R.raw.hne2,R.raw.hne3,R.raw.hne4,R.raw.hne5,R.raw.hne6,R.raw.hne7,R.raw.hne8},
                    {R.raw.hno1,R.raw.hno2,R.raw.hno3,R.raw.hno4,R.raw.hno5,R.raw.hno6,R.raw.hno7,R.raw.hno8},
                    {R.raw.hha1,R.raw.hha2,R.raw.hha3,R.raw.hha4,R.raw.hha5,R.raw.hha6,R.raw.hha7,R.raw.hha8},
                    {R.raw.hhi1,R.raw.hhi2,R.raw.hhi3,R.raw.hhi4,R.raw.hhi5,R.raw.hhi6,R.raw.hhi7,R.raw.hhi8},
                    {R.raw.hfu1,R.raw.hfu2,R.raw.hfu3,R.raw.hfu4,R.raw.hfu5,R.raw.hfu6,R.raw.hfu7,R.raw.hfu8},
                    {R.raw.hhe1,R.raw.hhe2,R.raw.hhe3,R.raw.hhe4,R.raw.hhe5,R.raw.hhe6,R.raw.hhe7,R.raw.hhe8},
                    {R.raw.hho1,R.raw.hho2,R.raw.hho3,R.raw.hho4,R.raw.hho5,R.raw.hho6,R.raw.hho7,R.raw.hho8},
                    {R.raw.hma1,R.raw.hma2,R.raw.hma3,R.raw.hma4,R.raw.hma5,R.raw.hma6,R.raw.hma7,R.raw.hma8},
                    {R.raw.hmi1,R.raw.hmi2,R.raw.hmi3,R.raw.hmi4,R.raw.hmi5,R.raw.hmi6,R.raw.hmi7,R.raw.hmi8},
                    {R.raw.hmu1,R.raw.hmu2,R.raw.hmu3,R.raw.hmu4,R.raw.hmu5,R.raw.hmu6,R.raw.hmu7,R.raw.hmu8},
                    {R.raw.hme1,R.raw.hme2,R.raw.hme3,R.raw.hme4,R.raw.hme5,R.raw.hme6,R.raw.hme7,R.raw.hme8},
                    {R.raw.hmo1,R.raw.hmo2,R.raw.hmo3,R.raw.hmo4,R.raw.hmo5,R.raw.hmo6,R.raw.hmo7,R.raw.hmo8},
                    {R.raw.hra1,R.raw.hra2,R.raw.hra3,R.raw.hra4,R.raw.hra5,R.raw.hra6,R.raw.hra7,R.raw.hra8},
                    {R.raw.hri1,R.raw.hri2,R.raw.hri3,R.raw.hri4,R.raw.hri5,R.raw.hri6,R.raw.hri7,R.raw.hri8},
                    {R.raw.hru1,R.raw.hru2,R.raw.hru3,R.raw.hru4,R.raw.hru5,R.raw.hru6,R.raw.hru7,R.raw.hru8},
                    {R.raw.hre1,R.raw.hre2,R.raw.hre3,R.raw.hre4,R.raw.hre5,R.raw.hre6,R.raw.hre7,R.raw.hre8},
                    {R.raw.hro1,R.raw.hro2,R.raw.hro3,R.raw.hro4,R.raw.hro5,R.raw.hro6,R.raw.hro7,R.raw.hro8},
                    {R.raw.hya1,R.raw.hya2,R.raw.hya3,R.raw.hya4,R.raw.hya5,R.raw.hya6,R.raw.hya7,R.raw.hya8},
                    {R.raw.hyu1,R.raw.hyu2,R.raw.hyu3,R.raw.hyu4,R.raw.hyu5,R.raw.hyu6,R.raw.hyu7,R.raw.hyu8},
                    {R.raw.hyo1,R.raw.hyo2,R.raw.hyo3,R.raw.hyo4,R.raw.hyo5,R.raw.hyo6,R.raw.hyo7,R.raw.hyo8},
                    {R.raw.hwa1,R.raw.hwa2,R.raw.hwa3,R.raw.hwa4,R.raw.hwa5,R.raw.hwa6,R.raw.hwa7,R.raw.hwa8},
                    {R.raw.hwo1,R.raw.hwo2,R.raw.hwo3,R.raw.hwo4,R.raw.hwo5,R.raw.hwo6,R.raw.hwo7,R.raw.hwo8},
                    {R.raw.hn1,R.raw.hn2,R.raw.hn3,R.raw.hn4,R.raw.hn5,R.raw.hn6,R.raw.hn7,R.raw.hn8},

                    {R.raw.ka1,R.raw.ka2,R.raw.ka3,R.raw.ka4,R.raw.ka5,R.raw.ka6,R.raw.ka7,R.raw.ka8},
                    {R.raw.ki1,R.raw.ki2,R.raw.ki3,R.raw.ki4,R.raw.ki5,R.raw.ki6,R.raw.ki7,R.raw.ki8},
                    {R.raw.ku1,R.raw.ku2,R.raw.ku3, R.raw.ku4,R.raw.ku5,R.raw.ku6,R.raw.ku7,R.raw.ku8},
                    {R.raw.ke1,R.raw.ke2,R.raw.ke3,R.raw.ke4,R.raw.ke5,R.raw.ke6,R.raw.ke7,R.raw.ke8},
                    {R.raw.ko1,R.raw.ko2,R.raw.ko3,R.raw.ko4,R.raw.ko5,R.raw.ko6,R.raw.ko7,R.raw.ko8},
                    {R.raw.kka1,R.raw.kka2,R.raw.kka3,R.raw.kka4,R.raw.kka5,R.raw.kka6,R.raw.kka7,R.raw.kka8},
                    {R.raw.kki1,R.raw.kki2,R.raw.kki3,R.raw.kki4,R.raw.kki5,R.raw.kki6,R.raw.kki7,R.raw.kki8},
                    {R.raw.kku1,R.raw.kku2,R.raw.kku3,R.raw.kku4,R.raw.kku5,R.raw.kku6,R.raw.kku7,R.raw.kku8},
                    {R.raw.kke1,R.raw.kke2,R.raw.kke3,R.raw.kke4,R.raw.kke5,R.raw.kke6,R.raw.kke7,R.raw.kke8},
                    {R.raw.kko1,R.raw.kko2,R.raw.kko3,R.raw.kko4,R.raw.kko5,R.raw.kko6,R.raw.kko7,R.raw.kko8},
                    {R.raw.ksa1,R.raw.ksa2,R.raw.ksa3,R.raw.ksa4,R.raw.ksa5,R.raw.ksa6,R.raw.ksa7,R.raw.ksa8},
                    {R.raw.kshi1,R.raw.kshi2,R.raw.kshi3,R.raw.kshi4,R.raw.kshi5,R.raw.kshi6,R.raw.kshi7,R.raw.kshi8},
                    {R.raw.ksu1,R.raw.ksu2,R.raw.ksu3,R.raw.ksu4,R.raw.ksu5,R.raw.ksu6,R.raw.ksu7,R.raw.ksu8},
                    {R.raw.kse1,R.raw.kse2,R.raw.kse3,R.raw.kse4,R.raw.kse5,R.raw.kse6,R.raw.kse7,R.raw.kse8},
                    {R.raw.kso1,R.raw.kso2,R.raw.kso3,R.raw.kso4,R.raw.kso5,R.raw.kso6,R.raw.kso7,R.raw.kso8},
                    {R.raw.kta1,R.raw.kta2,R.raw.kta3,R.raw.kta4,R.raw.kta5,R.raw.kta6,R.raw.kta7,R.raw.kta8},
                    {R.raw.kchi1,R.raw.kchi2,R.raw.kchi3,R.raw.kchi4,R.raw.kchi5,R.raw.kchi6,R.raw.kchi7,R.raw.kchi8},
                    {R.raw.ktsu1,R.raw.ktsu2,R.raw.ktsu3,R.raw.ktsu4,R.raw.ktsu5,R.raw.ktsu6,R.raw.ktsu7,R.raw.ktsu8},
                    {R.raw.kte1,R.raw.kte2,R.raw.kte3,R.raw.kte4,R.raw.kte5,R.raw.kte6,R.raw.kte7,R.raw.kte8},
                    {R.raw.kto1,R.raw.kto2,R.raw.kto3,R.raw.kto4,R.raw.kto5,R.raw.kto6,R.raw.kto7,R.raw.kto8},
                    {R.raw.kna1,R.raw.kna2,R.raw.kna3,R.raw.kna4,R.raw.kna5,R.raw.kna6,R.raw.kna7,R.raw.kna8},
                    {R.raw.kni1,R.raw.kni2,R.raw.kni3,R.raw.kni4,R.raw.kni5,R.raw.kni6,R.raw.kni7,R.raw.kni8},
                    {R.raw.knu1,R.raw.knu2,R.raw.knu3,R.raw.knu4,R.raw.knu5,R.raw.knu6,R.raw.knu7,R.raw.knu8},
                    {R.raw.kne1,R.raw.kne2,R.raw.kne3,R.raw.kne4,R.raw.kne5,R.raw.kne6,R.raw.kne7,R.raw.kne8},
                    {R.raw.kno1,R.raw.kno2,R.raw.kno3,R.raw.kno4,R.raw.kno5,R.raw.kno6,R.raw.kno7,R.raw.kno8},
                    {R.raw.kha1,R.raw.kha2,R.raw.kha3,R.raw.kha4,R.raw.kha5,R.raw.kha6,R.raw.kha7,R.raw.kha8},
                    {R.raw.khi1,R.raw.khi2,R.raw.khi3,R.raw.khi4,R.raw.khi5,R.raw.khi6,R.raw.khi7,R.raw.khi8},
                    {R.raw.kfu1,R.raw.kfu2,R.raw.kfu3,R.raw.kfu4,R.raw.kfu5,R.raw.kfu6,R.raw.kfu7,R.raw.kfu8},
                    {R.raw.khe1,R.raw.khe2,R.raw.khe3,R.raw.khe4,R.raw.khe5,R.raw.khe6,R.raw.khe7,R.raw.khe8},
                    {R.raw.kho1,R.raw.kho2,R.raw.kho3,R.raw.kho4,R.raw.kho5,R.raw.kho6,R.raw.kho7,R.raw.kho8},
                    {R.raw.kma1,R.raw.kma2,R.raw.kma3,R.raw.kma4,R.raw.kma5,R.raw.kma6,R.raw.kma7,R.raw.kma8},
                    {R.raw.kmi1,R.raw.kmi2,R.raw.kmi3,R.raw.kmi4,R.raw.kmi5,R.raw.kmi6,R.raw.kmi7,R.raw.kmi8},
                    {R.raw.kmu1,R.raw.kmu2,R.raw.kmu3,R.raw.kmu4,R.raw.kmu5,R.raw.kmu6,R.raw.kmu7,R.raw.kmu8},
                    {R.raw.kme1,R.raw.kme2,R.raw.kme3,R.raw.kme4,R.raw.kme5,R.raw.kme6,R.raw.kme7,R.raw.kme8},
                    {R.raw.kmo1,R.raw.kmo2,R.raw.kmo3,R.raw.kmo4,R.raw.kmo5,R.raw.kmo6,R.raw.kmo7,R.raw.kmo8},
                    {R.raw.kra1,R.raw.kra2,R.raw.kra3,R.raw.kra4,R.raw.kra5,R.raw.kra6,R.raw.kra7,R.raw.kra8},
                    {R.raw.kri1,R.raw.kri2,R.raw.kri3,R.raw.kri4,R.raw.kri5,R.raw.kri6,R.raw.kri7,R.raw.kri8},
                    {R.raw.kru1,R.raw.kru2,R.raw.kru3,R.raw.kru4,R.raw.kru5,R.raw.kru6,R.raw.kru7,R.raw.kru8},
                    {R.raw.kre1,R.raw.kre2,R.raw.kre3,R.raw.kre4,R.raw.kre5,R.raw.kre6,R.raw.kre7,R.raw.kre8},
                    {R.raw.kro1,R.raw.kro2,R.raw.kro3,R.raw.kro4,R.raw.kro5,R.raw.kro6,R.raw.kro7,R.raw.kro8},
                    {R.raw.kya1,R.raw.kya2,R.raw.kya3,R.raw.kya4,R.raw.kya5,R.raw.kya6,R.raw.kya7,R.raw.kya8},
                    {R.raw.kyu1,R.raw.kyu2,R.raw.kyu3,R.raw.kyu4,R.raw.kyu5,R.raw.kyu6,R.raw.kyu7,R.raw.kyu8},
                    {R.raw.kyo1,R.raw.kyo2,R.raw.kyo3,R.raw.kyo4,R.raw.kyo5,R.raw.kyo6,R.raw.kyo7,R.raw.kyo8},
                    {R.raw.kwa1,R.raw.kwa2,R.raw.kwa3,R.raw.kwa4,R.raw.kwa5,R.raw.kwa6,R.raw.kwa7,R.raw.kwa8},
                    {R.raw.kwo1,R.raw.kwo2,R.raw.kwo3,R.raw.kwo4,R.raw.kwo5,R.raw.kwo6,R.raw.kwo7,R.raw.kwo8},
                    {R.raw.kn1,R.raw.kn2,R.raw.kn3,R.raw.kn4,R.raw.kn5,R.raw.kn6,R.raw.kn7,R.raw.kn8},
            };
    public latihan() {
        // Required empty public constructor
    }


    private static Float[] ambang=
            {
                    81.58f,
                    88.66f,
                    93.48f,
                    79.2f,
                    68.84f,
                    78.96f,
                    81.2f,
                    96.3f,
                    81.78f,
                    85.98f,
                    84.06f,
                    86.12f,
                    82.34f,
                    76.14f,
                    79.84f,
                    77.12f,
                    68.78f,
                    84.54f,
                    83.62f,
                    84.9f,
                    87.62f,
                    62.6f,
                    81.98f,
                    82.3f,
                    68.16f,
                    82.82f,
                    74.92f,
                    70.74f,
                    85.8f,
                    81.12f,
                    78.4f,
                    63.8f,
                    85.82f,
                    68.3f,
                    84f,
                    79.62f,
                    73f,
                    64.12f,
                    86.08f,
                    77.74f,
                    73.06f,
                    78.22f,
                    77.38f,
                    83.94f,
                    78.86f,
                    73.14f,
                    82.84f,
                    77f,
                    82.26f,
                    77.18f,
                    81.4f,
                    62.26f,
                    80.52f,
                    81.32f,
                    92.42f,
                    81.48f,
                    92.58f,
                    90.34f,
                    71.9f,
                    77.4f,
                    86.22f,
                    73.02f,
                    80.52f,
                    81.04f,
                    81.3f,
                    82.18f,
                    88.24f,
                    84.54f,
                    71f,
                    79.4f,
                    93.5f,
                    95.4f,
                    81.86f,
                    81.3f,
                    95.32f,
                    86.54f,
                    77.88f,
                    82.58f,
                    88.16f,
                    80.7f,
                    78.68f,
                    79.02f,
                    71.74f,
                    88.66f,
                    90.26f,
                    81.06f,
                    80.98f,
                    84.5f,
                    77.9f,
                    76.5f,
                    83.34f,
                    76.64f

            };

    private static Float[] ambangmaks=
            {
                    85.3f,
                    92.5f,
                    98.3f,
                    89.7f,
                    76.7f,
                    86.6f,
                    94.6f,
                    99.3f,
                    88.2f,
                    92.8f,
                    93.9f,
                    95.7f,
                    86.4f,
                    78.6f,
                    87.2f,
                    83f,
                    81.2f,
                    90.4f,
                    89.6f,
                    87.7f,
                    90.9f,
                    62.6f,
                    87.5f,
                    86.6f,
                    72.8f,
                    91.1f,
                    87.5f,
                    78.6f,
                    90.4f,
                    87.7f,
                    82.6f,
                    63.8f,
                    90f,
                    73.1f,
                    92f,
                    83.1f,
                    81.7f,
                    74f,
                    89.5f,
                    89.5f,
                    77.5f,
                    86.4f,
                    84.9f,
                    91.5f,
                    83.6f,
                    82.4f,
                    88.3f,
                    79.4f,
                    86.3f,
                    86.4f,
                    85.9f,
                    75.5f,
                    94f,
                    89.7f,
                    97f,
                    84f,
                    94.3f,
                    92.1f,
                    76.7f,
                    86.2f,
                    88.8f,
                    80.5f,
                    86.8f,
                    86.8f,
                    83.1f,
                    86.6f,
                    92f,
                    91.3f,
                    79.9f,
                    88f,
                    96.6f,
                    98.2f,
                    87.2f,
                    85.4f,
                    96.3f,
                    88.2f,
                    88.2f,
                    91.2f,
                    94f,
                    83.5f,
                    85f,
                    84.8f,
                    83.6f,
                    95.7f,
                    93.4f,
                    90.6f,
                    91.1f,
                    89.6f,
                    81f,
                    84f,
                    86.5f,
                    86.3f


            };



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.lay_latihan, container, false);

        drawview=new DrawView(getContext());
        frm_layout=(FrameLayout) v.findViewById(R.id.main_frame);
        frm_layout.addView(drawview);
         benar=MediaPlayer.create(getContext(),R.raw.benar);
        salah=MediaPlayer.create(getContext(),R.raw.salah);



        Pbar = (ProgressBar)v.findViewById(R.id.progressBar1);
        Pbar.setVisibility(View.GONE);
        FloatingActionButton undo= (FloatingActionButton)v.findViewById(R.id.fab);
        undo.setOnClickListener( this);

        FloatingActionButton clear= (FloatingActionButton)v.findViewById(R.id.clear);
        clear.setOnClickListener( this);

        FloatingActionButton cek= (FloatingActionButton)v.findViewById(R.id.cek);
        cek.setOnClickListener(this);

        FloatingActionButton lanjut= (FloatingActionButton)v.findViewById(R.id.lanjut);
        lanjut.setOnClickListener(this);

        tvsoal=(TextView)v.findViewById(R.id.soal);

        //aktifkan onclick bila selesai testing
        onClick(lanjut);

        //nonaktifkan bila selesai testing
        /*
        soal=daftarsoal[next];
        tvsoal.setText("Tuliskan Huruf "+soal);
        bitmap1=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][0]));
        bitmap2=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][1]));
        bitmap3=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][2]));
        bitmap4=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][3]));
        bitmap5=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][4]));
        bitmap6=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][5]));
        bitmap7=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][6]));
        bitmap8=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][7]));

        */

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(v.getContext(), "ca-app-pub-9261861370906826~4018739519");

        mAdView = (AdView) v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        return v;
    }

    private class asyntes extends AsyncTask<Void,Void,Boolean>{
        //ProgressBar progress;
        private  Context c;
        public asyntes(Context a){
            this.c=a;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //progress = new ProgressBar(this.c);
            //progress.setTitle("Please Wait!!");
            //progress.setMessage("Wait!!");
            //progress.setCancelable(true);
            Pbar.setVisibility(View.VISIBLE);
            //progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //progress.isShown();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return pencocokan_persen();
        }

        @Override
        protected void onPostExecute(Boolean i) {
            super.onPostExecute(i);

            final Dialog settingsDialog = new Dialog(getContext());
            settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            settingsDialog.setContentView(R.layout.hasilpengenalan);
            ImageView gambar2= (ImageView)settingsDialog.findViewById(R.id.image1);
            gambar2.setImageBitmap(BitmapFactory.decodeResource(getResources(),animasi[acaksoal]));
            ImageView gambar= (ImageView)settingsDialog.findViewById(R.id.gambar);
            if (i==true){
                gambar.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bnr));
            }else {
                gambar.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.slh));
            }
            gambar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    settingsDialog.dismiss();
                }
            });
            gambar2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    settingsDialog.dismiss();
                }
            });
            Pbar.setVisibility(View.GONE);
            settingsDialog.show();
        }
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
            case R.id.cek:

                //Toast.makeText(getContext(),"Tunggu Sebentar",Toast.LENGTH_SHORT).show();
               drawview.setDrawingCacheEnabled(true);
                mBitmap=Bitmap.createBitmap(drawview.getDrawingCache());
                Canvas canvas = new Canvas(mBitmap);
               drawview.draw(canvas);


                //new CountDownTimer(80, 20) {
               //     public void onTick(long millisUntilFinished) {
               //     }
                //    public void onFinish() {
                      //  System.out.println("j");
                        /*String p=Integer.toString(next);
                        String i=Integer.toString(k);
                        simpan(prosescitranonlbp(mBitmap),p+""+soal+""+i);
                        k=k+1;
                        */
                        //pencocokan_custom();
                        new asyntes(v.getContext()).execute();
                        drawview.onClickClear();
                 //   }
            //    }.start();

                break;
            case R.id.lanjut:
                //aktifkan acak bila selesai testing

                acakhuruf();
                //k=1;

                bitmap1=BitmapFactory.decodeStream(this.getResources().openRawResource(database[acaksoal][0]));
                bitmap2=BitmapFactory.decodeStream(this.getResources().openRawResource(database[acaksoal][1]));
                bitmap3=BitmapFactory.decodeStream(this.getResources().openRawResource(database[acaksoal][2]));
                bitmap4=BitmapFactory.decodeStream(this.getResources().openRawResource(database[acaksoal][3]));
                bitmap5=BitmapFactory.decodeStream(this.getResources().openRawResource(database[acaksoal][4]));
                bitmap6=BitmapFactory.decodeStream(this.getResources().openRawResource(database[acaksoal][5]));
                bitmap7=BitmapFactory.decodeStream(this.getResources().openRawResource(database[acaksoal][6]));
                bitmap8=BitmapFactory.decodeStream(this.getResources().openRawResource(database[acaksoal][7]));

                //nonaktifkan bila selesai testing
                /* next=next+1;
               soal=daftarsoal[next];
               tvsoal.setText("Tuliskan Huruf "+soal);
                bitmap1=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][0]));
                bitmap2=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][1]));
                bitmap3=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][2]));
                bitmap4=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][3]));
                bitmap5=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][4]));
                bitmap6=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][5]));
                bitmap7=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][6]));
                bitmap8=BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][7]));
                */
                break;
        }
    }

    private Boolean pencocokan_persen(){
        float nilai_ambang = ambang[acaksoal];
        //System.out.println("nilai ="+nilai_ambang);
        boolean hsl=false;
        Bitmap uji=prosescitranonlbp(mBitmap);
        Bitmap ujilbp=lbp(uji);
        //GifView.gambargif=animasi[acaksoal];
        if (jarak_lbp_persen(uji,ujilbp,bitmap6)>=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp_persen(uji,ujilbp,bitmap7)>=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp_persen(uji,ujilbp,bitmap8)>=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp_persen(uji,ujilbp,bitmap1)>=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp_persen(uji,ujilbp,bitmap2)>=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp_persen(uji,ujilbp,bitmap3)>=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp_persen(uji,ujilbp,bitmap4)>=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp_persen(uji,ujilbp,bitmap5)>=nilai_ambang){
            hsl=true;
        }
        /*final Dialog settingsDialog = new Dialog(getContext());
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(R.layout.hasilpengenalan);
        ImageView gambar2= (ImageView)settingsDialog.findViewById(R.id.image1);
        gambar2.setImageBitmap(BitmapFactory.decodeResource(getResources(),animasi[acaksoal]));
        ImageView gambar= (ImageView)settingsDialog.findViewById(R.id.gambar);
        if (hsl==true){
            gambar.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bnr));
        }else {
            gambar.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.slh));
        }
        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsDialog.dismiss();
            }
        });
        gambar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsDialog.dismiss();
            }
        });

        settingsDialog.show();

        /*
        float jarak4=jarak_lbp(uji,ujilbp,bitmap1);
        float jarak5=jarak_lbp(uji,ujilbp,bitmap2);
        float jarak6=jarak_lbp(uji,ujilbp,bitmap3);
        float jarak7=jarak_lbp(uji,ujilbp,bitmap4);
        float jarak8=jarak_lbp(uji,ujilbp,bitmap5);
        float jarak1=jarak_lbp(uji,ujilbp,bitmap6);
        float jarak2=jarak_lbp(uji,ujilbp,bitmap7);
        float jarak3=jarak_lbp(uji,ujilbp,bitmap8);






        Dialog settingsDialog = new Dialog(getContext());
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(R.layout.hasilpengenalan);
        ImageView gambar= (ImageView)settingsDialog.findViewById(R.id.gambar);
        gambar.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.sbenar));



        settingsDialog.show();
        */
        return hsl;
    }


    private static int jarak_lbp(Bitmap uji,Bitmap ujilbp,Bitmap db){
        //Bitmap    bmOut = Bitmap.createBitmap(bmpori,xmin,ymin,lebar,tinggi);

        Bitmap A1=lbp(Bitmap.createBitmap(uji,0,0,64,64));
        Bitmap A2=lbp(Bitmap.createBitmap(uji,64,0,64,64));
        Bitmap A3=lbp(Bitmap.createBitmap(uji,0,64,64,64));
        Bitmap A4=lbp(Bitmap.createBitmap(uji,64,64,64,64));

        Bitmap B1=lbp(Bitmap.createBitmap(db,0,0,64,64));
        Bitmap B2=lbp(Bitmap.createBitmap(db,64,0,64,64));
        Bitmap B3=lbp(Bitmap.createBitmap(db,0,64,64,64));
        Bitmap B4=lbp(Bitmap.createBitmap(db,64,64,64,64));


        Bitmap ujidb=lbp(db);

        int jumlahpikselA[]=new int[256];
        int jumlahpikselA1[]=new int[256];
        int jumlahpikselA2[]=new int[256];
        int jumlahpikselA3[]=new int[256];
        int jumlahpikselA4[]=new int[256];
        //int jumlahpikselAtotal[]=new int[256];

        int jumlahpikselB[]=new int[256];
        int jumlahpikselB1[]=new int[256];
        int jumlahpikselB2[]=new int[256];
        int jumlahpikselB3[]=new int[256];
        int jumlahpikselB4[]=new int[256];
        //int jumlahpikselBtotal[]=new int[256];

        //cari nilai piksel a
        int widtha = ujilbp.getWidth();
        int heighta = ujilbp.getHeight();

        int Reda,piksela,Redb,pikselb;
        //original LBP
        for(int x = 0; x < widtha; ++x) {
            for(int y = 0; y < heighta; ++y) {

                piksela = ujilbp.getPixel(x, y);
                Reda= Color.red(piksela);
                jumlahpikselA[Reda]=jumlahpikselA[Reda]+1;

                pikselb = ujidb.getPixel(x, y);
                Redb= Color.red(pikselb);
                jumlahpikselB[Redb]=jumlahpikselB[Redb]+1;
            }
        }

        int RedA1,pikselA1,RedA2,pikselA2,RedA3,pikselA3,RedA4,pikselA4;
        int RedB1,pikselB1,RedB2,pikselB2,RedB3,pikselB3,RedB4,pikselB4;

        for(int x = 0; x < A1.getWidth(); ++x) {
            for(int y = 0; y < A1.getHeight(); ++y) {

                pikselA1 = A1.getPixel(x, y);
                RedA1= Color.red(pikselA1);
                jumlahpikselA1[RedA1]=jumlahpikselA1[RedA1]+1;

                pikselA2 = A2.getPixel(x, y);
                RedA2= Color.red(pikselA2);
                jumlahpikselA2[RedA2]=jumlahpikselA2[RedA2]+1;

                pikselA3 = A3.getPixel(x, y);
                RedA3= Color.red(pikselA3);
                jumlahpikselA3[RedA3]=jumlahpikselA3[RedA3]+1;

                pikselA4 = A4.getPixel(x, y);
                RedA4= Color.red(pikselA4);
                jumlahpikselA4[RedA4]=jumlahpikselA4[RedA4]+1;


                pikselB1 = B1.getPixel(x, y);
                RedB1= Color.red(pikselB1);
                jumlahpikselB1[RedB1]=jumlahpikselB1[RedB1]+1;

                pikselB2 = B2.getPixel(x, y);
                RedB2= Color.red(pikselB2);
                jumlahpikselB2[RedB2]=jumlahpikselB2[RedB2]+1;

                pikselB3 = B3.getPixel(x, y);
                RedB3= Color.red(pikselB3);
                jumlahpikselB3[RedB3]=jumlahpikselB3[RedB3]+1;

                pikselB4 = B4.getPixel(x, y);
                RedB4= Color.red(pikselB4);
                jumlahpikselB4[RedB4]=jumlahpikselB4[RedB4]+1;
            }
        }
        int jumlahpikselAtotal[]=new int[1280];
        int jumlahpikselBtotal[]=new int[1280];
        int t=0,tampungan=0,jarak=0,pos1=256,pos2=512,pos3=768,pos4=1024;

        for (int x=0; x<256; ++x){

            jumlahpikselAtotal[x]=jumlahpikselA[x];
            jumlahpikselAtotal[pos1]=jumlahpikselA1[x];
            jumlahpikselAtotal[pos2]=jumlahpikselA2[x];
            jumlahpikselAtotal[pos3]=jumlahpikselA3[x];
            jumlahpikselAtotal[pos4]=jumlahpikselA4[x];

            jumlahpikselBtotal[x]=jumlahpikselB[x];
            jumlahpikselBtotal[pos1]=jumlahpikselB1[x];
            jumlahpikselBtotal[pos2]=jumlahpikselB2[x];
            jumlahpikselBtotal[pos3]=jumlahpikselB3[x];
            jumlahpikselBtotal[pos4]=jumlahpikselB4[x];


            ++pos1;
            ++pos2;
            ++pos3;
            ++pos4;

           // jumlahpikselBtotal=jumlahpikselB[x]+jumlahpikselB1[x]+jumlahpikselB2[x]+jumlahpikselB3[x]+jumlahpikselB4[x];



        }
        //hitung jarak
        for (int x=0; x<1280; ++x){
            int tampung=0;
            tampung= (int) Math.pow(jumlahpikselAtotal[x]-jumlahpikselBtotal[x],2);
            tampungan=tampungan+tampung;
        }

        jarak=(int)Math.sqrt(tampungan);

        return jarak;

    }

    private static float jarak_lbp_persen(Bitmap uji,Bitmap ujilbp,Bitmap db){
        //Bitmap    bmOut = Bitmap.createBitmap(bmpori,xmin,ymin,lebar,tinggi);

        Bitmap A1=lbp(Bitmap.createBitmap(uji,0,0,64,64));
        Bitmap A2=lbp(Bitmap.createBitmap(uji,64,0,64,64));
        Bitmap A3=lbp(Bitmap.createBitmap(uji,0,64,64,64));
        Bitmap A4=lbp(Bitmap.createBitmap(uji,64,64,64,64));

        Bitmap B1=lbp(Bitmap.createBitmap(db,0,0,64,64));
        Bitmap B2=lbp(Bitmap.createBitmap(db,64,0,64,64));
        Bitmap B3=lbp(Bitmap.createBitmap(db,0,64,64,64));
        Bitmap B4=lbp(Bitmap.createBitmap(db,64,64,64,64));


        Bitmap ujidb=lbp(db);

        int jumlahpikselA[]=new int[256];
        int jumlahpikselA1[]=new int[256];
        int jumlahpikselA2[]=new int[256];
        int jumlahpikselA3[]=new int[256];
        int jumlahpikselA4[]=new int[256];
        //int jumlahpikselAtotal[]=new int[256];

        int jumlahpikselB[]=new int[256];
        int jumlahpikselB1[]=new int[256];
        int jumlahpikselB2[]=new int[256];
        int jumlahpikselB3[]=new int[256];
        int jumlahpikselB4[]=new int[256];
        //int jumlahpikselBtotal[]=new int[256];

        //cari nilai piksel a
        int widtha = ujilbp.getWidth();
        int heighta = ujilbp.getHeight();

        int Reda,piksela,Redb,pikselb;
        //original LBP
        for(int x = 0; x < widtha; ++x) {
            for(int y = 0; y < heighta; ++y) {

                piksela = ujilbp.getPixel(x, y);
                Reda= Color.red(piksela);
                jumlahpikselA[Reda]=jumlahpikselA[Reda]+1;

                pikselb = ujidb.getPixel(x, y);
                Redb= Color.red(pikselb);
                jumlahpikselB[Redb]=jumlahpikselB[Redb]+1;
            }
        }

        int RedA1,pikselA1,RedA2,pikselA2,RedA3,pikselA3,RedA4,pikselA4;
        int RedB1,pikselB1,RedB2,pikselB2,RedB3,pikselB3,RedB4,pikselB4;

        for(int x = 0; x < A1.getWidth(); ++x) {
            for(int y = 0; y < A1.getHeight(); ++y) {

                pikselA1 = A1.getPixel(x, y);
                RedA1= Color.red(pikselA1);
                jumlahpikselA1[RedA1]=jumlahpikselA1[RedA1]+1;

                pikselA2 = A2.getPixel(x, y);
                RedA2= Color.red(pikselA2);
                jumlahpikselA2[RedA2]=jumlahpikselA2[RedA2]+1;

                pikselA3 = A3.getPixel(x, y);
                RedA3= Color.red(pikselA3);
                jumlahpikselA3[RedA3]=jumlahpikselA3[RedA3]+1;

                pikselA4 = A4.getPixel(x, y);
                RedA4= Color.red(pikselA4);
                jumlahpikselA4[RedA4]=jumlahpikselA4[RedA4]+1;


                pikselB1 = B1.getPixel(x, y);
                RedB1= Color.red(pikselB1);
                jumlahpikselB1[RedB1]=jumlahpikselB1[RedB1]+1;

                pikselB2 = B2.getPixel(x, y);
                RedB2= Color.red(pikselB2);
                jumlahpikselB2[RedB2]=jumlahpikselB2[RedB2]+1;

                pikselB3 = B3.getPixel(x, y);
                RedB3= Color.red(pikselB3);
                jumlahpikselB3[RedB3]=jumlahpikselB3[RedB3]+1;

                pikselB4 = B4.getPixel(x, y);
                RedB4= Color.red(pikselB4);
                jumlahpikselB4[RedB4]=jumlahpikselB4[RedB4]+1;
            }
        }
        int jumlahpikselAtotal[]=new int[1280];
        int jumlahpikselBtotal[]=new int[1280];
        int t=0,tampungan=0,pos1=256,pos2=512,pos3=768,pos4=1024;
        float jarak=0;
        for (int x=0; x<256; ++x){

            jumlahpikselAtotal[x]=jumlahpikselA[x];
            jumlahpikselAtotal[pos1]=jumlahpikselA1[x];
            jumlahpikselAtotal[pos2]=jumlahpikselA2[x];
            jumlahpikselAtotal[pos3]=jumlahpikselA3[x];
            jumlahpikselAtotal[pos4]=jumlahpikselA4[x];

            jumlahpikselBtotal[x]=jumlahpikselB[x];
            jumlahpikselBtotal[pos1]=jumlahpikselB1[x];
            jumlahpikselBtotal[pos2]=jumlahpikselB2[x];
            jumlahpikselBtotal[pos3]=jumlahpikselB3[x];
            jumlahpikselBtotal[pos4]=jumlahpikselB4[x];


            ++pos1;
            ++pos2;
            ++pos3;
            ++pos4;

            // jumlahpikselBtotal=jumlahpikselB[x]+jumlahpikselB1[x]+jumlahpikselB2[x]+jumlahpikselB3[x]+jumlahpikselB4[x];



        }
        //hitung jarak
        for (int x=0; x<1280; ++x){
            int tampung=0;
            tampung= (int) Math.pow(jumlahpikselAtotal[x]-jumlahpikselBtotal[x],2);
            tampungan=tampungan+tampung;
        }

        jarak=(int)Math.sqrt(tampungan);
        //System.out.println("jarak ="+jarak);

        float normalisasi=(jarak-100)/1000;
        //System.out.println("normalisasi ="+normalisasi);
        if (normalisasi<0) normalisasi=0;
        if (normalisasi>1) normalisasi=1;
        float persen=(1-normalisasi)*100;
       // System.out.println("persen ="+persen);

        return persen;

    }





    private void simpansimpan(Bitmap as){


        simpan(as, "Asli");

        Bitmap gray=kegray(as);
        Bitmap potong=potong(gray);
        Bitmap resize=resize(potong);

        Bitmap lbp = lbp(resize);

        k=k+1;
        String q= String.valueOf(k);

    }



    private void acakhuruf (){
        acaksoal=(int) (Math.random()*daftarsoal.length);
        soal=daftarsoal[acaksoal];
        tvsoal.setText("Tuliskan Huruf "+soal);

    }







    private static Bitmap prosescitranonlbp(Bitmap bmpori){

        Bitmap gray=kegray(bmpori);
        Bitmap potong=potong(gray);
        Bitmap resize=resize(potong);
        //Bitmap lbp = lbp(resize);

        return resize;
    }
    private void simpan(Bitmap asli, String nama){

        try
        {

            /*
            File f = new File(Environment.getExternalStorageDirectory()
                    + File.separator +  ""+nama+".bmp");

            f.createNewFile();

            FileOutputStream os = new FileOutputStream(f);
            os = new FileOutputStream(f);
            asli.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
            */
            File db = new File("/sdcard/db/");
            if(!db.exists())
                db.mkdirs();

            String file = new File(Environment.getExternalStorageDirectory()
                    +File.separator
                    +"db" //folder name
                    +File.separator
                    +""+nama+".bmp").toString();
            new AndroidBmpUtil().save(asli,file);


            //Toast pesan = Toast.makeText(getActivity(), "Gambar Telah Disimpan", Toast.LENGTH_LONG);
            //pesan.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private static Bitmap kegray(Bitmap bmpori){
        int width = bmpori.getWidth();

        int height = bmpori.getHeight();

        int A,Red,Gre,Blu,pixel;
        int warna;

        Bitmap bmOut = Bitmap.createBitmap(width, height, bmpori.getConfig());


        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                pixel = bmpori.getPixel(x, y);
                A = Color.alpha(pixel);

                Red= Color.red(pixel);
                Gre= Color.green(pixel);
                Blu= Color.blue(pixel);
                //warna = Red+Gre+Blu;
                warna = (Red*1/3)+(Gre*1/3)+(Blu*1/3);
                //warna = warna/3;
                //  if (x<10 && y<25){
                //     Log.d("Pesan",""+x+","+y+"");
                // }else {

                //}


                bmOut.setPixel(x, y, Color.rgb( warna, warna, warna));

            }
        }
        //Log.d("Pesan",""+width+","+height+"");
        return bmOut;
    }
    private static Bitmap potong(Bitmap bmpori){
        //cari nilai piksel obyek
        int widthh = bmpori.getWidth();
        int jumlahpiksel[]=new int[256];
        int heighth = bmpori.getHeight();
        int Red2,piksel;
        for(int x = 0; x < widthh; ++x) {
            for(int y = 0; y < heighth; ++y) {

                piksel = bmpori.getPixel(x, y);
                Red2= Color.red(piksel);
                jumlahpiksel[Red2]=jumlahpiksel[Red2]+1;
            }
        }

        int maks=0,obyek=0;
        for (int i = 0; i < 254; i++) {
            if (jumlahpiksel[i]>maks){
                maks=jumlahpiksel[i];
                obyek=i;
            }

        }



        //cari titik potong
        int width = bmpori.getWidth();
        int height = bmpori.getHeight();
        int pixel,xmin = width,xmax=0,ymin=height,ymax=0,Red;
        int warna;


        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                pixel = bmpori.getPixel(x, y);
                Red= Color.red(pixel);
                if (Red==obyek){
                    if (x<xmin){
                        xmin=x;
                    }
                    if (x>xmax){
                        xmax=x;
                    }
                    if (y<ymin){
                        ymin=y;
                    }
                    if (y>ymax){
                        ymax=y;
                    }
                }
            }
        }
        xmin=xmin-5;
        ymin=ymin-5;
        int lebar=xmax-xmin;
        int tinggi=ymax-ymin;
        lebar=lebar+10;
        tinggi=tinggi+10;
        if (xmin<=0){
            xmin=0;
        }
        if (ymin<=0){
            ymin=0;
        }
        if (lebar>=width){
            lebar=width;
        }
        if (tinggi>=height){
            tinggi=height;
        }

        //Log.d("pesan","xmax="+xmax+" xmin="+xmin+" ymax="+ymax+" ymin="+ymin+" lebar=" +lebar+" tinggi="+tinggi+"");
        //Bitmap bmOut = Bitmap.createBitmap(lebar, tinggi, bmpori.getConfig());

        Bitmap    bmOut = Bitmap.createBitmap(bmpori,xmin,ymin,lebar,tinggi);




        return bmOut;
    }
    private static Bitmap resize(Bitmap bmpori){

        Bitmap bmOut = Bitmap.createScaledBitmap(bmpori, 128, 128, true);


        return bmOut;
    }
    private static Bitmap lbp(Bitmap bmpori){
        int width = bmpori.getWidth();
        int height = bmpori.getHeight();
        int Red,Gre,Blu,pixel;
        int warna;


        Bitmap bmOut = Bitmap.createBitmap(width, height, bmpori.getConfig());
        //Bitmap asav = Bitmap.createBitmap(bmpori);

        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {


                if (  x > 0 && x < width-1 && y > 0 && y < height-1) {

                    int xkiriatas =x-1;
                    int ykiriatas =y-1;
                    int pixelkiriatas=bmpori.getPixel(xkiriatas,ykiriatas);
                    int wkiriatas= Color.red(pixelkiriatas);

                    int xatas =x;
                    int yatas =y-1;
                    int pixelatas=bmpori.getPixel(xatas, yatas);
                    int watas= Color.red(pixelatas);

                    int xkananatas =x+1;
                    int ykananatas =y-1;
                    int pixelkananatas=bmpori.getPixel(xkananatas,ykananatas);
                    int wkananatas= Color.red(pixelkananatas);

                    int xkanan =x+1;
                    int ykanan =y;
                    int pixelkanan=bmpori.getPixel(xkanan,ykanan);
                    int wkanan= Color.red(pixelkanan);

                    int xkananbawah =x+1;
                    int ykananbawah =y+1;
                    int pixelkananbawah=bmpori.getPixel(xkananbawah,ykananbawah);
                    int wkananbawah= Color.red(pixelkananbawah);

                    int xbawah =x;
                    int ybawah =y+1;
                    int pixelbawah=bmpori.getPixel(xbawah,ybawah);
                    int wbawah= Color.red(pixelbawah);

                    int xkiribawah =x-1;
                    int ykiribawah =y+1;
                    int pixelkiribawah=bmpori.getPixel(xkiribawah,ykiribawah);
                    int wkiribawah= Color.red(pixelkiribawah);

                    int xkiri =x-1;
                    int ykiri =y;
                    int pixelkiri=bmpori.getPixel(xkiri,ykiri);
                    int wkiri = Color.red(pixelkiri);

                    int pixeltengah = bmpori.getPixel(x, y);
                    int wtengah= Color.red(pixeltengah);

                    int nilai=0;
                    int t;
                    t=wkiriatas-wtengah;
                    //System.out.println(wkiriatas+" "+watas+" "+wkananatas+" "+wkanan+" "+wkananbawah+" "+wbawah+" "+wkiribawah+" "+wkiri);
                    if (t>=0){
                        nilai=nilai+1;
                    }
                    t=watas-wtengah;
                    if (t>=0){
                        nilai=nilai+2;
                    }
                    t=wkananatas-wtengah;
                    if (t>=0){
                        nilai=nilai+4;
                    }
                    t=wkanan-wtengah;
                    if (t>=0){
                        nilai=nilai+8;
                    }
                    t=wkananbawah-wtengah;
                    if (t>=0){
                        nilai=nilai+16;
                    }
                    t=wbawah-wtengah;
                    if (t>=0){
                        nilai=nilai+32;
                    }
                    t=wkiribawah-wtengah;
                    if (t>=0){
                        nilai=nilai+64;
                    }
                    t=wkiri-wtengah;
                    if (t>=0){
                        nilai=nilai+128;
                    }
                    bmOut.setPixel(x, y, Color.rgb(nilai, nilai, nilai));

                    /*Log.i("Kampret","Tengah = "+x+","+y+ " warnatengah="+wtengah+
                          " kiriatas="+xkiriatas+","+ykiriatas+" warnakiriatas="+wkiriatas+
                          " Atas=" +xatas+","+yatas+" Warna Atas = "+watas+
                          " Kananatas="+xkananatas+","+ykananatas+" Warnakananatas="+wkananatas+
                          " Kanan="+xkanan+","+ykanan+" Warnakanan="+wkanan+
                          " Kananbawah="+xkananbawah+","+ykananbawah+" Warnakananbawah="+wkananbawah+
                          " Bawah="+xbawah+","+ybawah+" Warnabawah="+wbawah+
                          " KiriBawah="+xkiribawah+","+ykiribawah+" Warnakiribawah="+wkiribawah+
                          " Kiri="+xkiri+","+ykiri+" Warnakiri="+wkiri+
                          " Nilai="+nilai+""

                    );*/



                }
                else {
                    pixel = bmpori.getPixel(x, y);
                    warna= Color.red(pixel);
                    bmOut.setPixel(x, y, Color.rgb(warna, warna, warna));
                }






            }
        }

        return bmOut;
    }

//hapus nanti
/*
    private void testing(){
        hasil("Hiragana A",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ha1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ha2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ha3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ha4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ha5)));

        hasil("Hiragana I",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hi1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hi2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hi3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hi4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hi5)));

        hasil("Hiragana U",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hu5)));
        hasil("Hiragana E",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.he1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.he2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.he3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.he4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.he5)));
        hasil("Hiragana O",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ho1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ho2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ho3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ho4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ho5)));
        hasil("Hiragana Ka",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hka1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hka2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hka3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hka4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hka5)));
        hasil("Hiragana Ki",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hki1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hki2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hki3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hki4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hki5)));
        hasil("Hiragana Ku",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hku1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hku2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hku3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hku4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hku5)));
        hasil("Hiragana Ke",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hke1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hke2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hke3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hke4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hke5)));
        hasil("Hiragana Ko",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hko1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hko2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hko3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hko4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hko5)));
        hasil("Hiragana Sa",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hsa1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hsa2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hsa3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hsa4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hsa5)));
        hasil("Hiragana Shi",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hshi1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hshi2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hshi3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hshi4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hshi5)));
        hasil("Hiragana Su",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hsu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hsu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hsu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hsu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hsu5)));
        hasil("Hiragana Se",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hse1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hse2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hse3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hse4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hse5)));
        hasil("Hiragana So",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hso1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hso2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hso3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hso4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hso5)));
        hasil("Hiragana Ta",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hta1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hta2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hta3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hta4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hta5)));
        hasil("Hiragana Chi",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hchi1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hchi2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hchi3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hchi4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hchi5)));
        hasil("Hiragana Tsu",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.htsu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.htsu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.htsu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.htsu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.htsu5)));
        hasil("Hiragana Te",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hte1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hte2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hte3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hte4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hte5)));
        hasil("Hiragana To",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hto1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hto2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hto3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hto4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hto5)));
        hasil("Hiragana Na",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hna1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hna2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hna3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hna4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hna5)));
        hasil("Hiragana Ni",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hni1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hni2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hni3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hni4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hni5)));
        hasil("Hiragana Nu",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hnu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hnu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hnu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hnu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hnu5)));
        hasil("Hiragana Ne",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hne1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hne2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hne3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hne4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hne5)));
        hasil("Hiragana No",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hno1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hno2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hno3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hno4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hno5)));
        hasil("Hiragana Ha",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hha1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hha2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hha3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hha4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hha5)));
        hasil("Hiragana Hi",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hhi1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hhi2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hhi3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hhi4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hhi5)));
        hasil("Hiragana Fu",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hfu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hfu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hfu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hfu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hfu5)));
        hasil("Hiragana He",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hhe1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hhe2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hhe3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hhe4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hhe5)));
        hasil("Hiragana Ho",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hho1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hho2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hho3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hho4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hho5)));
        hasil("Hiragana Ma",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hma1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hma2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hma3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hma4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hma5)));
        hasil("Hiragana Mi",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmi1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmi2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmi3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmi4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmi5)));
        hasil("Hiragana Mu",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmu5)));
        hasil("Hiragana Me",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hme1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hme2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hme3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hme4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hme5)));
        hasil("Hiragana Mo",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmo1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmo2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmo3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmo4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hmo5)));
        hasil("Hiragana Ra",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hra1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hra2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hra3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hra4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hra5)));
        hasil("Hiragana Ri",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hri1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hri2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hri3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hri4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hri5)));
        hasil("Hiragana Ru",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hru1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hru2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hru3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hru4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hru5)));
        hasil("Hiragana Re",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hre1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hre2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hre3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hre4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hre5)));
        hasil("Hiragana Ro",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hro1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hro2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hro3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hro4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hro5)));
        hasil("Hiragana Ya",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hya1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hya2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hya3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hya4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hya5)));
        hasil("Hiragana Yu",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hyu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hyu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hyu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hyu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hyu5)));
        hasil("Hiragana Yo",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hyo1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hyo2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hyo3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hyo4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hyo5)));
        hasil("Hiragana Wa",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hwa1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hwa2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hwa3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hwa4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hwa5)));
        hasil("Hiragana Wo",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hwo1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hwo2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hwo3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hwo4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hwo5)));
        hasil("Hiragana N",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hn1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hn2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hn3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hn4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.hn5)));


        hasil("Katakana A",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ka1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ka2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ka3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ka4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ka5)));
        hasil("Katakana I",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ki1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ki2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ki3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ki4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ki5)));
        hasil("Katakana U",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ku1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ku2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ku3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ku4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ku5)));
        hasil("Katakana E",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ke1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ke2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ke3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ke4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ke5)));
        hasil("Katakana O",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ko1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ko2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ko3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ko4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ko5)));
        hasil("Katakana Ka",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kka1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kka2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kka3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kka4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kka5)));
        hasil("Katakana Ki",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kki1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kki2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kki3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kki4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kki5)));
        hasil("Katakana Ku",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kku1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kku2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kku3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kku4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kku5)));
        hasil("Katakana Ke",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kke1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kke2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kke3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kke4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kke5)));
        hasil("Katakana Ko",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kko1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kko2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kko3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kko4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kko5)));
        hasil("Katakana Sa",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ksa1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ksa2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ksa3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ksa4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ksa5)));
        hasil("Katakana Shi",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kshi1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kshi2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kshi3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kshi4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kshi5)));
        hasil("Katakana Su",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ksu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ksu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ksu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ksu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ksu5)));
        hasil("Katakana Se",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kse1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kse2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kse3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kse4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kse5)));
        hasil("Katakana So",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kso1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kso2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kso3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kso4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kso5)));
        hasil("Katakana Ta",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kta1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kta2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kta3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kta4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kta5)));
        hasil("Katakana Chi",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kchi1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kchi2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kchi3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kchi4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kchi5)));
        hasil("Katakana Tsu",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ktsu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ktsu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ktsu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ktsu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.ktsu5)));
        hasil("Katakana Te",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kte1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kte2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kte3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kte4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kte5)));
        hasil("Katakana To",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kto1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kto2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kto3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kto4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kto5)));
        hasil("Katakana Na",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kna1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kna2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kna3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kna4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kna5)));
        hasil("Katakana Ni",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kni1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kni2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kni3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kni4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kni5)));
        hasil("Katakana Nu",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.knu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.knu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.knu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.knu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.knu5)));
        hasil("Katakana Ne",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kne1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kne2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kne3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kne4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kne5)));
        hasil("Katakana No",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kno1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kno2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kno3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kno4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kno5)));
        hasil("Katakana Ha",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kha1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kha2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kha3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kha4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kha5)));
        hasil("Katakana Hi",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.khi1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.khi2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.khi3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.khi4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.khi5)));
        hasil("Katakana Fu",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kfu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kfu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kfu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kfu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kfu5)));
        hasil("Katakana He",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.khe1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.khe2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.khe3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.khe4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.khe5)));
        hasil("Katakana Ho",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kho1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kho2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kho3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kho4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kho5)));
        hasil("Katakana Ma",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kma1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kma2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kma3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kma4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kma5)));
        hasil("Katakana Mi",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmi1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmi2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmi3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmi4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmi5)));
        hasil("Katakana Mu",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmu5)));
        hasil("Katakana Me",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kme1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kme2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kme3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kme4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kme5)));
        hasil("Katakana Mo",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmo1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmo2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmo3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmo4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kmo5)));
        hasil("Katakana Ra",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kra1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kra2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kra3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kra4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kra5)));
        hasil("Katakana Ri",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kri1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kri2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kri3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kri4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kri5)));
        hasil("Katakana Ru",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kru1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kru2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kru3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kru4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kru5)));
        hasil("Katakana Re",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kre1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kre2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kre3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kre4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kre5)));
        hasil("Katakana Ro",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kro1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kro2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kro3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kro4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kro5)));
        hasil("Katakana Ya",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kya1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kya2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kya3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kya4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kya5)));
        hasil("Katakana Yu",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kyu1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kyu2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kyu3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kyu4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kyu5)));
        hasil("Katakana Yo",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kyo1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kyo2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kyo3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kyo4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kyo5)));
        hasil("Katakana Wa",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kwa1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kwa2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kwa3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kwa4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kwa5)));
        hasil("Katakana Wo",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kwo1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kwo2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kwo3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kwo4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kwo5)));
        hasil("Katakana N",
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kn1)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kn2)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kn3)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kn4)),
                BitmapFactory.decodeStream(this.getResources().openRawResource(R.raw.kn5)));
    }
/*
    private void hasil(String ar,Bitmap bmuji,Bitmap bmsampel1,Bitmap bmsampel2,Bitmap bmsampel3,Bitmap bmsampel4){

        Bitmap uji1=bmuji;
        Bitmap uji2=bmsampel1;
        Bitmap uji3=bmsampel2;
        Bitmap uji4=bmsampel3;
        Bitmap uji5=bmsampel4;

        Bitmap uji1_lbp=lbp(bmuji);
        Bitmap uji2_lbp=lbp(bmsampel1);
        Bitmap uji3_lbp=lbp(bmsampel2);
        Bitmap uji4_lbp=lbp(bmsampel3);
        Bitmap uji5_lbp=lbp(bmsampel4);

        String arr=ar+"1";
        int a=jarak_lbp(uji1,uji1_lbp,uji2);
        int b=jarak_lbp(uji1,uji1_lbp,uji3);
        int c=jarak_lbp(uji1,uji1_lbp,uji4);
        int d=jarak_lbp(uji1,uji1_lbp,uji5);

        System.out.println("Jaraknya "+arr+" "+String.valueOf(a)+" "+String.valueOf(b)+" "+String.valueOf(c)+" "+String.valueOf(d));

        arr=ar+"2";
        a=jarak_lbp(uji2,uji2_lbp,uji3);
        b=jarak_lbp(uji2,uji2_lbp,uji4);
        c=jarak_lbp(uji2,uji2_lbp,uji5);

        System.out.println("Jaraknya "+arr+" "+String.valueOf(a)+" "+String.valueOf(b)+" "+String.valueOf(c));

        arr=ar+"3";

        a=jarak_lbp(uji3,uji3_lbp,uji4);
        b=jarak_lbp(uji3,uji3_lbp,uji5);

        System.out.println("Jaraknya "+arr+" "+String.valueOf(a)+" "+String.valueOf(b));

        arr=ar+"4";
        a=jarak_lbp(uji4,uji4_lbp,uji5);
        System.out.println("Jaraknya "+arr+" "+String.valueOf(a));

    }

*/
    private static int jarak(Bitmap a,Bitmap b){
        int jarak = 0;
        int width = a.getWidth();
        int height = a.getHeight();
        int Reda,piksela;
        int Redb,pikselb;
        int tampungan=0;
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                piksela = a.getPixel(x, y);
                Reda= Color.red(piksela);
                pikselb = b.getPixel(x, y);
                Redb= Color.red(pikselb);
                int tampung=0;
                int tes=Reda-Redb;
                tampung= (int) Math.pow(tes,2);
                tampungan=tampungan+tampung;
                //Log.d("Kampret",Reda+" dan "+Redb);
            }
        }
        jarak=(int)Math.sqrt(tampungan);
        //System.out.println("Jaraknya T"+tampungan);
        System.out.println("Jarak LBP="+jarak);

        return jarak;


    }

    private static int jarakhistogram(Bitmap a,Bitmap b){


        int jumlahpikselb[]=new int[256];
        int Redb,pikselb;
        //cari nilai piksel a
        int widtha = a.getWidth();
        int jumlahpiksela[]=new int[256];
        int heighta = a.getHeight();
        int Reda,piksela;
        for(int x = 0; x < widtha; ++x) {
            for(int y = 0; y < heighta; ++y) {

                piksela = a.getPixel(x, y);
                Reda= Color.red(piksela);
                jumlahpiksela[Reda]=jumlahpiksela[Reda]+1;

                pikselb = b.getPixel(x, y);
                Redb= Color.red(pikselb);
                jumlahpikselb[Redb]=jumlahpikselb[Redb]+1;
            }
        }





        int jarak=0;
        int tampungan=0;
        int total=0;
        for(int x = 0; x < 256; ++x) {

            // System.out.println("jumlah piksel "+x+"="+jumlahpiksela[x]);
            //   total=total+jumlahpiksela[x];
            int tampung=0;
            tampung= (int) Math.pow(jumlahpiksela[x]-jumlahpikselb[x],2);
            tampungan=tampungan+tampung;
            //Log.d("Kampret",Reda+" dan "+Redb);

        }
        jarak=(int)Math.sqrt(tampungan);
        System.out.println("Jarak Histogram"+jarak);

        return jarak;


    }
    private void pencocokan_custom(){
        switch (soal) {
            case "Hiragana A":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.ha1),
                        this.getResources().openRawResource(R.raw.ha2),
                        this.getResources().openRawResource(R.raw.ha3),
                        this.getResources().openRawResource(R.raw.ha4),
                        this.getResources().openRawResource(R.raw.ha5));
                GifView.gambargif=R.drawable.aha;
                break;
            case "Hiragana I":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.hi1),
                        this.getResources().openRawResource(R.raw.hi2),
                        this.getResources().openRawResource(R.raw.hi3),
                        this.getResources().openRawResource(R.raw.hi4),
                        this.getResources().openRawResource(R.raw.hi5));
                GifView.gambargif=R.drawable.ahi;
                break;
            case "Hiragana U":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.hu1),
                        this.getResources().openRawResource(R.raw.hu2),
                        this.getResources().openRawResource(R.raw.hu3),
                        this.getResources().openRawResource(R.raw.hu4),
                        this.getResources().openRawResource(R.raw.hu5));
                GifView.gambargif=R.drawable.ahu;
                break;
            case "Hiragana E":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.he1),
                        this.getResources().openRawResource(R.raw.he2),
                        this.getResources().openRawResource(R.raw.he3),
                        this.getResources().openRawResource(R.raw.he4),
                        this.getResources().openRawResource(R.raw.he5));
                GifView.gambargif=R.drawable.ahe;
                break;
            case "Hiragana O":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.ho1),
                        this.getResources().openRawResource(R.raw.ho2),
                        this.getResources().openRawResource(R.raw.ho3),
                        this.getResources().openRawResource(R.raw.ho4),
                        this.getResources().openRawResource(R.raw.ho5));
                GifView.gambargif=R.drawable.aho;
                break;
            case "Hiragana Ka":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.hka1),
                        this.getResources().openRawResource(R.raw.hka2),
                        this.getResources().openRawResource(R.raw.hka3),
                        this.getResources().openRawResource(R.raw.hka4),
                        this.getResources().openRawResource(R.raw.hka5));
                GifView.gambargif=R.drawable.ahka;
                break;
            case "Hiragana Ki":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.hki1),
                        this.getResources().openRawResource(R.raw.hki2),
                        this.getResources().openRawResource(R.raw.hki3),
                        this.getResources().openRawResource(R.raw.hki4),
                        this.getResources().openRawResource(R.raw.hki5));
                GifView.gambargif=R.drawable.ahki;
                break;
            case "Hiragana Ku":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.hku1),
                        this.getResources().openRawResource(R.raw.hku2),
                        this.getResources().openRawResource(R.raw.hku3),
                        this.getResources().openRawResource(R.raw.hku4),
                        this.getResources().openRawResource(R.raw.hku5));
                GifView.gambargif=R.drawable.ahku;
                break;
            case "Hiragana Ke":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.hke1),
                        this.getResources().openRawResource(R.raw.hke2),
                        this.getResources().openRawResource(R.raw.hke3),
                        this.getResources().openRawResource(R.raw.hke4),
                        this.getResources().openRawResource(R.raw.hke5));
                GifView.gambargif=R.drawable.ahke;
                break;
            case "Hiragana Ko":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.hko1),
                        this.getResources().openRawResource(R.raw.hko2),
                        this.getResources().openRawResource(R.raw.hko3),
                        this.getResources().openRawResource(R.raw.hko4),
                        this.getResources().openRawResource(R.raw.hko5));
                GifView.gambargif=R.drawable.ahko;
                break;
            case "Hiragana Sa":
                hasil=hasil_pencocokan_custom(
                        mBitmap,
                        this.getResources().openRawResource(R.raw.hsa1),
                        this.getResources().openRawResource(R.raw.hsa2),
                        this.getResources().openRawResource(R.raw.hsa3),
                        this.getResources().openRawResource(R.raw.hsa4),
                        this.getResources().openRawResource(R.raw.hsa5));
                GifView.gambargif=R.drawable.ahsa;
                break;
            case "Hiragana Shi": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hshi1),
                    this.getResources().openRawResource(R.raw.hshi2),
                    this.getResources().openRawResource(R.raw.hshi3),
                    this.getResources().openRawResource(R.raw.hshi4),
                    this.getResources().openRawResource(R.raw.hshi5));
                GifView.gambargif=R.drawable.ahshi;
                break;
            case "Hiragana Su": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hsu1),
                    this.getResources().openRawResource(R.raw.hsu2),
                    this.getResources().openRawResource(R.raw.hsu3),
                    this.getResources().openRawResource(R.raw.hsu4),
                    this.getResources().openRawResource(R.raw.hsu5));
                GifView.gambargif=R.drawable.ahsu;
                break;
            case "Hiragana Se": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hse1),
                    this.getResources().openRawResource(R.raw.hse2),
                    this.getResources().openRawResource(R.raw.hse3),
                    this.getResources().openRawResource(R.raw.hse4),
                    this.getResources().openRawResource(R.raw.hse5));
                GifView.gambargif=R.drawable.ahse;
                break;
            case "Hiragana So": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hso1),
                    this.getResources().openRawResource(R.raw.hso2),
                    this.getResources().openRawResource(R.raw.hso3),
                    this.getResources().openRawResource(R.raw.hso4),
                    this.getResources().openRawResource(R.raw.hso5));
                GifView.gambargif=R.drawable.ahso;
                break;
            case "Hiragana Ta": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hta1),
                    this.getResources().openRawResource(R.raw.hta2),
                    this.getResources().openRawResource(R.raw.hta3),
                    this.getResources().openRawResource(R.raw.hta4),
                    this.getResources().openRawResource(R.raw.hta5));
                GifView.gambargif=R.drawable.ahta;
                break;
            case "Hiragana Chi": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hchi1),
                    this.getResources().openRawResource(R.raw.hchi2),
                    this.getResources().openRawResource(R.raw.hchi3),
                    this.getResources().openRawResource(R.raw.hchi4),
                    this.getResources().openRawResource(R.raw.hchi5));
                GifView.gambargif=R.drawable.ahchi;
                break;
            case "Hiragana Tsu": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.htsu1),
                    this.getResources().openRawResource(R.raw.htsu2),
                    this.getResources().openRawResource(R.raw.htsu3),
                    this.getResources().openRawResource(R.raw.htsu4),
                    this.getResources().openRawResource(R.raw.htsu5));
                GifView.gambargif=R.drawable.ahtsu;
                break;
            case "Hiragana Te": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hte1),
                    this.getResources().openRawResource(R.raw.hte2),
                    this.getResources().openRawResource(R.raw.hte3),
                    this.getResources().openRawResource(R.raw.hte4),
                    this.getResources().openRawResource(R.raw.hte5));
                GifView.gambargif=R.drawable.ahte;
                break;
            case "Hiragana To": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hto1),
                    this.getResources().openRawResource(R.raw.hto2),
                    this.getResources().openRawResource(R.raw.hto3),
                    this.getResources().openRawResource(R.raw.hto4),
                    this.getResources().openRawResource(R.raw.hto5));
                GifView.gambargif=R.drawable.ahto;
                break;
            case "Hiragana Na": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hna1),
                    this.getResources().openRawResource(R.raw.hna2),
                    this.getResources().openRawResource(R.raw.hna3),
                    this.getResources().openRawResource(R.raw.hna4),
                    this.getResources().openRawResource(R.raw.hna5));
                GifView.gambargif=R.drawable.ahna;
                break;
            case "Hiragana Ni": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hni1),
                    this.getResources().openRawResource(R.raw.hni2),
                    this.getResources().openRawResource(R.raw.hni3),
                    this.getResources().openRawResource(R.raw.hni4),
                    this.getResources().openRawResource(R.raw.hni5));
                GifView.gambargif=R.drawable.ahni;
                break;
            case "Hiragana Nu": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hnu1),
                    this.getResources().openRawResource(R.raw.hnu2),
                    this.getResources().openRawResource(R.raw.hnu3),
                    this.getResources().openRawResource(R.raw.hnu4),
                    this.getResources().openRawResource(R.raw.hnu5));
                GifView.gambargif=R.drawable.ahnu;
                break;
            case "Hiragana Ne": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hne1),
                    this.getResources().openRawResource(R.raw.hne2),
                    this.getResources().openRawResource(R.raw.hne3),
                    this.getResources().openRawResource(R.raw.hne4),
                    this.getResources().openRawResource(R.raw.hne5));
                GifView.gambargif=R.drawable.ahne;
                break;
            case "Hiragana No": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hno1),
                    this.getResources().openRawResource(R.raw.hno2),
                    this.getResources().openRawResource(R.raw.hno3),
                    this.getResources().openRawResource(R.raw.hno4),
                    this.getResources().openRawResource(R.raw.hno5));
                GifView.gambargif=R.drawable.ahno;
                break;
            case "Hiragana Ha": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hha1),
                    this.getResources().openRawResource(R.raw.hha2),
                    this.getResources().openRawResource(R.raw.hha3),
                    this.getResources().openRawResource(R.raw.hha4),
                    this.getResources().openRawResource(R.raw.hha5));
                GifView.gambargif=R.drawable.ahha;
                break;
            case "Hiragana Hi": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hhi1),
                    this.getResources().openRawResource(R.raw.hhi2),
                    this.getResources().openRawResource(R.raw.hhi3),
                    this.getResources().openRawResource(R.raw.hhi4),
                    this.getResources().openRawResource(R.raw.hhi5));
                GifView.gambargif=R.drawable.ahhi;
                break;
            case "Hiragana Fu": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hfu1),
                    this.getResources().openRawResource(R.raw.hfu2),
                    this.getResources().openRawResource(R.raw.hfu3),
                    this.getResources().openRawResource(R.raw.hfu4),
                    this.getResources().openRawResource(R.raw.hfu5));
                GifView.gambargif=R.drawable.ahfu;
                break;
            case "Hiragana He": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hhe1),
                    this.getResources().openRawResource(R.raw.hhe2),
                    this.getResources().openRawResource(R.raw.hhe3),
                    this.getResources().openRawResource(R.raw.hhe4),
                    this.getResources().openRawResource(R.raw.hhe5));
                GifView.gambargif=R.drawable.ahhe;
                break;
            case "Hiragana Ho": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hho1),
                    this.getResources().openRawResource(R.raw.hho2),
                    this.getResources().openRawResource(R.raw.hho3),
                    this.getResources().openRawResource(R.raw.hho4),
                    this.getResources().openRawResource(R.raw.hho5));
                GifView.gambargif=R.drawable.ahho;
                break;
            case "Hiragana Ma": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hma1),
                    this.getResources().openRawResource(R.raw.hma2),
                    this.getResources().openRawResource(R.raw.hma3),
                    this.getResources().openRawResource(R.raw.hma4),
                    this.getResources().openRawResource(R.raw.hma5));
                GifView.gambargif=R.drawable.ahma;
                break;
            case "Hiragana Mi": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hmi1),
                    this.getResources().openRawResource(R.raw.hmi2),
                    this.getResources().openRawResource(R.raw.hmi3),
                    this.getResources().openRawResource(R.raw.hmi4),
                    this.getResources().openRawResource(R.raw.hmi5));
                GifView.gambargif=R.drawable.ahmi;
                break;
            case "Hiragana Mu": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hmu1),
                    this.getResources().openRawResource(R.raw.hmu2),
                    this.getResources().openRawResource(R.raw.hmu3),
                    this.getResources().openRawResource(R.raw.hmu4),
                    this.getResources().openRawResource(R.raw.hmu5));
                GifView.gambargif=R.drawable.ahmu;
                break;
            case "Hiragana Me": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hme1),
                    this.getResources().openRawResource(R.raw.hme2),
                    this.getResources().openRawResource(R.raw.hme3),
                    this.getResources().openRawResource(R.raw.hme4),
                    this.getResources().openRawResource(R.raw.hme5));
                GifView.gambargif=R.drawable.ahme;
                break;
            case "Hiragana Mo": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hmo1),
                    this.getResources().openRawResource(R.raw.hmo2),
                    this.getResources().openRawResource(R.raw.hmo3),
                    this.getResources().openRawResource(R.raw.hmo4),
                    this.getResources().openRawResource(R.raw.hmo5));
                GifView.gambargif=R.drawable.ahmo;
                break;
            case "Hiragana Ra": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hra1),
                    this.getResources().openRawResource(R.raw.hra2),
                    this.getResources().openRawResource(R.raw.hra3),
                    this.getResources().openRawResource(R.raw.hra4),
                    this.getResources().openRawResource(R.raw.hra5));
                GifView.gambargif=R.drawable.ahra;
                break;
            case "Hiragana Ri": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hri1),
                    this.getResources().openRawResource(R.raw.hri2),
                    this.getResources().openRawResource(R.raw.hri3),
                    this.getResources().openRawResource(R.raw.hri4),
                    this.getResources().openRawResource(R.raw.hri5));
                GifView.gambargif=R.drawable.ahri;
                break;
            case "Hiragana Ru": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hru1),
                    this.getResources().openRawResource(R.raw.hru2),
                    this.getResources().openRawResource(R.raw.hru3),
                    this.getResources().openRawResource(R.raw.hru4),
                    this.getResources().openRawResource(R.raw.hru5));
                GifView.gambargif=R.drawable.ahru;
                break;
            case "Hiragana Re": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hre1),
                    this.getResources().openRawResource(R.raw.hre2),
                    this.getResources().openRawResource(R.raw.hre3),
                    this.getResources().openRawResource(R.raw.hre4),
                    this.getResources().openRawResource(R.raw.hre5));
                GifView.gambargif=R.drawable.ahre;
                break;
            case "Hiragana Ro": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hro1),
                    this.getResources().openRawResource(R.raw.hro2),
                    this.getResources().openRawResource(R.raw.hro3),
                    this.getResources().openRawResource(R.raw.hro4),
                    this.getResources().openRawResource(R.raw.hro5));
                GifView.gambargif=R.drawable.ahro;
                break;
            case "Hiragana Ya": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hya1),
                    this.getResources().openRawResource(R.raw.hya2),
                    this.getResources().openRawResource(R.raw.hya3),
                    this.getResources().openRawResource(R.raw.hya4),
                    this.getResources().openRawResource(R.raw.hya5));
                GifView.gambargif=R.drawable.ahya;
                break;
            case "Hiragana Yu": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hyu1),
                    this.getResources().openRawResource(R.raw.hyu2),
                    this.getResources().openRawResource(R.raw.hyu3),
                    this.getResources().openRawResource(R.raw.hyu4),
                    this.getResources().openRawResource(R.raw.hyu5));
                GifView.gambargif=R.drawable.ahyu;
                break;
            case "Hiragana Yo": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hyo1),
                    this.getResources().openRawResource(R.raw.hyo2),
                    this.getResources().openRawResource(R.raw.hyo3),
                    this.getResources().openRawResource(R.raw.hyo4),
                    this.getResources().openRawResource(R.raw.hyo5));
                GifView.gambargif=R.drawable.ahyo;
                break;
            case "Hiragana Wa": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hwa1),
                    this.getResources().openRawResource(R.raw.hwa2),
                    this.getResources().openRawResource(R.raw.hwa3),
                    this.getResources().openRawResource(R.raw.hwa4),
                    this.getResources().openRawResource(R.raw.hwa5));
                GifView.gambargif=R.drawable.ahwa;
                break;
            case "Hiragana Wo": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hwo1),
                    this.getResources().openRawResource(R.raw.hwo2),
                    this.getResources().openRawResource(R.raw.hwo3),
                    this.getResources().openRawResource(R.raw.hwo4),
                    this.getResources().openRawResource(R.raw.hwo5));
                GifView.gambargif=R.drawable.ahwo;
                break;
            case "Hiragana N": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.hn1),
                    this.getResources().openRawResource(R.raw.hn2),
                    this.getResources().openRawResource(R.raw.hn3),
                    this.getResources().openRawResource(R.raw.hn4),
                    this.getResources().openRawResource(R.raw.hn5));
                GifView.gambargif=R.drawable.ahn;
                break;


            case "Katakana A": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.ka1),
                    this.getResources().openRawResource(R.raw.ka2),
                    this.getResources().openRawResource(R.raw.ka3),
                    this.getResources().openRawResource(R.raw.ka4),
                    this.getResources().openRawResource(R.raw.ka5));
                GifView.gambargif=R.drawable.aka;
                break;
            case "Katakana I": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.ki1),
                    this.getResources().openRawResource(R.raw.ki2),
                    this.getResources().openRawResource(R.raw.ki3),
                    this.getResources().openRawResource(R.raw.ki4),
                    this.getResources().openRawResource(R.raw.ki5));
                GifView.gambargif=R.drawable.aki;
                break;
            case "Katakana U": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.ku1),
                    this.getResources().openRawResource(R.raw.ku2),
                    this.getResources().openRawResource(R.raw.ku3),
                    this.getResources().openRawResource(R.raw.ku4),
                    this.getResources().openRawResource(R.raw.ku5));
                GifView.gambargif=R.drawable.aku;
                break;
            case "Katakana E": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.ke1),
                    this.getResources().openRawResource(R.raw.ke2),
                    this.getResources().openRawResource(R.raw.ke3),
                    this.getResources().openRawResource(R.raw.ke4),
                    this.getResources().openRawResource(R.raw.ke5));
                GifView.gambargif=R.drawable.ake;
                break;
            case "Katakana O": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.ko1),
                    this.getResources().openRawResource(R.raw.ko2),
                    this.getResources().openRawResource(R.raw.ko3),
                    this.getResources().openRawResource(R.raw.ko4),
                    this.getResources().openRawResource(R.raw.ko5));
                GifView.gambargif=R.drawable.ako;
                break;
            case "Katakana Ka": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kka1),
                    this.getResources().openRawResource(R.raw.kka2),
                    this.getResources().openRawResource(R.raw.kka3),
                    this.getResources().openRawResource(R.raw.kka4),
                    this.getResources().openRawResource(R.raw.kka5));
                GifView.gambargif=R.drawable.akka;
                break;
            case "Katakana Ki": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kki1),
                    this.getResources().openRawResource(R.raw.kki2),
                    this.getResources().openRawResource(R.raw.kki3),
                    this.getResources().openRawResource(R.raw.kki4),
                    this.getResources().openRawResource(R.raw.kki5));
                GifView.gambargif=R.drawable.akki;
                break;
            case "Katakana Ku": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kku1),
                    this.getResources().openRawResource(R.raw.kku2),
                    this.getResources().openRawResource(R.raw.kku3),
                    this.getResources().openRawResource(R.raw.kku4),
                    this.getResources().openRawResource(R.raw.kku5));
                GifView.gambargif=R.drawable.akku;
                break;
            case "Katakana Ke": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kke1),
                    this.getResources().openRawResource(R.raw.kke2),
                    this.getResources().openRawResource(R.raw.kke3),
                    this.getResources().openRawResource(R.raw.kke4),
                    this.getResources().openRawResource(R.raw.kke5));
                GifView.gambargif=R.drawable.akke;
                break;
            case "Katakana Ko": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kko1),
                    this.getResources().openRawResource(R.raw.kko2),
                    this.getResources().openRawResource(R.raw.kko3),
                    this.getResources().openRawResource(R.raw.kko4),
                    this.getResources().openRawResource(R.raw.kko5));
                GifView.gambargif=R.drawable.akko;
                break;
            case "Katakana Sa": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.ksa1),
                    this.getResources().openRawResource(R.raw.ksa2),
                    this.getResources().openRawResource(R.raw.ksa3),
                    this.getResources().openRawResource(R.raw.ksa4),
                    this.getResources().openRawResource(R.raw.ksa5));
                GifView.gambargif=R.drawable.aksa;
                break;
            case "Katakana Shi": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kshi1),
                    this.getResources().openRawResource(R.raw.kshi2),
                    this.getResources().openRawResource(R.raw.kshi3),
                    this.getResources().openRawResource(R.raw.kshi4),
                    this.getResources().openRawResource(R.raw.kshi5));
                GifView.gambargif=R.drawable.akshi;
                break;
            case "Katakana Su": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.ksu1),
                    this.getResources().openRawResource(R.raw.ksu2),
                    this.getResources().openRawResource(R.raw.ksu3),
                    this.getResources().openRawResource(R.raw.ksu4),
                    this.getResources().openRawResource(R.raw.ksu5));
                GifView.gambargif=R.drawable.aksu;
                break;
            case "Katakana Se": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kse1),
                    this.getResources().openRawResource(R.raw.kse2),
                    this.getResources().openRawResource(R.raw.kse3),
                    this.getResources().openRawResource(R.raw.kse4),
                    this.getResources().openRawResource(R.raw.kse5));
                GifView.gambargif=R.drawable.akse;
                break;
            case "Katakana So": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kso1),
                    this.getResources().openRawResource(R.raw.kso2),
                    this.getResources().openRawResource(R.raw.kso3),
                    this.getResources().openRawResource(R.raw.kso4),
                    this.getResources().openRawResource(R.raw.kso5));
                GifView.gambargif=R.drawable.akso;
                break;
            case "Katakana Ta": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kta1),
                    this.getResources().openRawResource(R.raw.kta2),
                    this.getResources().openRawResource(R.raw.kta3),
                    this.getResources().openRawResource(R.raw.kta4),
                    this.getResources().openRawResource(R.raw.kta5));
                GifView.gambargif=R.drawable.akta;
                break;
            case "Katakana Chi": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kchi1),
                    this.getResources().openRawResource(R.raw.kchi2),
                    this.getResources().openRawResource(R.raw.kchi3),
                    this.getResources().openRawResource(R.raw.kchi4),
                    this.getResources().openRawResource(R.raw.kchi5));
                GifView.gambargif=R.drawable.akchi;
                break;
            case "Katakana Tsu": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.ktsu1),
                    this.getResources().openRawResource(R.raw.ktsu2),
                    this.getResources().openRawResource(R.raw.ktsu3),
                    this.getResources().openRawResource(R.raw.ktsu4),
                    this.getResources().openRawResource(R.raw.ktsu5));
                GifView.gambargif=R.drawable.aktsu;
                break;
            case "Katakana Te": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kte1),
                    this.getResources().openRawResource(R.raw.kte2),
                    this.getResources().openRawResource(R.raw.kte3),
                    this.getResources().openRawResource(R.raw.kte4),
                    this.getResources().openRawResource(R.raw.kte5));
                GifView.gambargif=R.drawable.akte;
                break;
            case "Katakana To": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kto1),
                    this.getResources().openRawResource(R.raw.kto2),
                    this.getResources().openRawResource(R.raw.kto3),
                    this.getResources().openRawResource(R.raw.kto4),
                    this.getResources().openRawResource(R.raw.kto5));
                GifView.gambargif=R.drawable.akto;
                break;
            case "Katakana Na": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kna1),
                    this.getResources().openRawResource(R.raw.kna2),
                    this.getResources().openRawResource(R.raw.kna3),
                    this.getResources().openRawResource(R.raw.kna4),
                    this.getResources().openRawResource(R.raw.kna5));
                GifView.gambargif=R.drawable.akna;
                break;
            case "Katakana Ni": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kni1),
                    this.getResources().openRawResource(R.raw.kni2),
                    this.getResources().openRawResource(R.raw.kni3),
                    this.getResources().openRawResource(R.raw.kni4),
                    this.getResources().openRawResource(R.raw.kni5));
                GifView.gambargif=R.drawable.akni;
                break;
            case "Katakana Nu": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.knu1),
                    this.getResources().openRawResource(R.raw.knu2),
                    this.getResources().openRawResource(R.raw.knu3),
                    this.getResources().openRawResource(R.raw.knu4),
                    this.getResources().openRawResource(R.raw.knu5));
                GifView.gambargif=R.drawable.aknu;
                break;
            case "Katakana Ne": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kne1),
                    this.getResources().openRawResource(R.raw.kne2),
                    this.getResources().openRawResource(R.raw.kne3),
                    this.getResources().openRawResource(R.raw.kne4),
                    this.getResources().openRawResource(R.raw.kne5));
                GifView.gambargif=R.drawable.akne;
                break;
            case "Katakana No": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kno1),
                    this.getResources().openRawResource(R.raw.kno2),
                    this.getResources().openRawResource(R.raw.kno3),
                    this.getResources().openRawResource(R.raw.kno4),
                    this.getResources().openRawResource(R.raw.kno5));
                GifView.gambargif=R.drawable.akno;
                break;
            case "Katakana Ha": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kha1),
                    this.getResources().openRawResource(R.raw.kha2),
                    this.getResources().openRawResource(R.raw.kha3),
                    this.getResources().openRawResource(R.raw.kha4),
                    this.getResources().openRawResource(R.raw.kha5));
                GifView.gambargif=R.drawable.akha;
                break;
            case "Katakana Hi": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.khi1),
                    this.getResources().openRawResource(R.raw.khi2),
                    this.getResources().openRawResource(R.raw.khi3),
                    this.getResources().openRawResource(R.raw.khi4),
                    this.getResources().openRawResource(R.raw.khi5));
                GifView.gambargif=R.drawable.akhi;
                break;
            case "Katakana Fu": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kfu1),
                    this.getResources().openRawResource(R.raw.kfu2),
                    this.getResources().openRawResource(R.raw.kfu3),
                    this.getResources().openRawResource(R.raw.kfu4),
                    this.getResources().openRawResource(R.raw.kfu5));
                GifView.gambargif=R.drawable.akfu;
                break;
            case "Katakana He": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.khe1),
                    this.getResources().openRawResource(R.raw.khe2),
                    this.getResources().openRawResource(R.raw.khe3),
                    this.getResources().openRawResource(R.raw.khe4),
                    this.getResources().openRawResource(R.raw.khe5));
                GifView.gambargif=R.drawable.akhe;
                break;
            case "Katakana Ho": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kho1),
                    this.getResources().openRawResource(R.raw.kho2),
                    this.getResources().openRawResource(R.raw.kho3),
                    this.getResources().openRawResource(R.raw.kho4),
                    this.getResources().openRawResource(R.raw.kho5));
                GifView.gambargif=R.drawable.akho;
                break;
            case "Katakana Ma": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kma1),
                    this.getResources().openRawResource(R.raw.kma2),
                    this.getResources().openRawResource(R.raw.kma3),
                    this.getResources().openRawResource(R.raw.kma4),
                    this.getResources().openRawResource(R.raw.kma5));
                GifView.gambargif=R.drawable.akma;
                break;
            case "Katakana Mi": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kmi1),
                    this.getResources().openRawResource(R.raw.kmi2),
                    this.getResources().openRawResource(R.raw.kmi3),
                    this.getResources().openRawResource(R.raw.kmi4),
                    this.getResources().openRawResource(R.raw.kmi5));
                GifView.gambargif=R.drawable.akmi;
                break;
            case "Katakana Mu": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kmu1),
                    this.getResources().openRawResource(R.raw.kmu2),
                    this.getResources().openRawResource(R.raw.kmu3),
                    this.getResources().openRawResource(R.raw.kmu4),
                    this.getResources().openRawResource(R.raw.kmu5));
                GifView.gambargif=R.drawable.akmu;
                break;
            case "Katakana Me": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kme1),
                    this.getResources().openRawResource(R.raw.kme2),
                    this.getResources().openRawResource(R.raw.kme3),
                    this.getResources().openRawResource(R.raw.kme4),
                    this.getResources().openRawResource(R.raw.kme5));
                GifView.gambargif=R.drawable.akme;
                break;
            case "Katakana Mo": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kmo1),
                    this.getResources().openRawResource(R.raw.kmo2),
                    this.getResources().openRawResource(R.raw.kmo3),
                    this.getResources().openRawResource(R.raw.kmo4),
                    this.getResources().openRawResource(R.raw.kmo5));
                GifView.gambargif=R.drawable.akmo;
                break;
            case "Katakana Ra": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kra1),
                    this.getResources().openRawResource(R.raw.kra2),
                    this.getResources().openRawResource(R.raw.kra3),
                    this.getResources().openRawResource(R.raw.kra4),
                    this.getResources().openRawResource(R.raw.kra5));
                GifView.gambargif=R.drawable.akra;
                break;
            case "Katakana Ri": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kri1),
                    this.getResources().openRawResource(R.raw.kri2),
                    this.getResources().openRawResource(R.raw.kri3),
                    this.getResources().openRawResource(R.raw.kri4),
                    this.getResources().openRawResource(R.raw.kri5));
                GifView.gambargif=R.drawable.akri;
                break;
            case "Katakana Ru": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kru1),
                    this.getResources().openRawResource(R.raw.kru2),
                    this.getResources().openRawResource(R.raw.kru3),
                    this.getResources().openRawResource(R.raw.kru4),
                    this.getResources().openRawResource(R.raw.kru5));
                GifView.gambargif=R.drawable.akru;
                break;
            case "Katakana Re": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kre1),
                    this.getResources().openRawResource(R.raw.kre2),
                    this.getResources().openRawResource(R.raw.kre3),
                    this.getResources().openRawResource(R.raw.kre4),
                    this.getResources().openRawResource(R.raw.kre5));
                GifView.gambargif=R.drawable.akre;
                break;
            case "Katakana Ro": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kro1),
                    this.getResources().openRawResource(R.raw.kro2),
                    this.getResources().openRawResource(R.raw.kro3),
                    this.getResources().openRawResource(R.raw.kro4),
                    this.getResources().openRawResource(R.raw.kro5));
                GifView.gambargif=R.drawable.akro;
                break;
            case "Katakana Ya": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kya1),
                    this.getResources().openRawResource(R.raw.kya2),
                    this.getResources().openRawResource(R.raw.kya3),
                    this.getResources().openRawResource(R.raw.kya4),
                    this.getResources().openRawResource(R.raw.kya5));
                GifView.gambargif=R.drawable.akya;
                break;
            case "Katakana Yu": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kyu1),
                    this.getResources().openRawResource(R.raw.kyu2),
                    this.getResources().openRawResource(R.raw.kyu3),
                    this.getResources().openRawResource(R.raw.kyu4),
                    this.getResources().openRawResource(R.raw.kyu5));
                GifView.gambargif=R.drawable.akyu;
                break;
            case "Katakana Yo": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kyo1),
                    this.getResources().openRawResource(R.raw.kyo2),
                    this.getResources().openRawResource(R.raw.kyo3),
                    this.getResources().openRawResource(R.raw.kyo4),
                    this.getResources().openRawResource(R.raw.kyo5));
                GifView.gambargif=R.drawable.akyo;
                break;
            case "Katakana Wa": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kwa1),
                    this.getResources().openRawResource(R.raw.kwa2),
                    this.getResources().openRawResource(R.raw.kwa3),
                    this.getResources().openRawResource(R.raw.kwa4),
                    this.getResources().openRawResource(R.raw.kwa5));
                GifView.gambargif=R.drawable.akwa;
                break;
            case "Katakana Wo": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kwo1),
                    this.getResources().openRawResource(R.raw.kwo2),
                    this.getResources().openRawResource(R.raw.kwo3),
                    this.getResources().openRawResource(R.raw.kwo4),
                    this.getResources().openRawResource(R.raw.kwo5));
                GifView.gambargif=R.drawable.akwo;
                break;
            case "Katakana N": hasil=hasil_pencocokan_custom(
                    mBitmap,
                    this.getResources().openRawResource(R.raw.kn1),
                    this.getResources().openRawResource(R.raw.kn2),
                    this.getResources().openRawResource(R.raw.kn3),
                    this.getResources().openRawResource(R.raw.kn4),
                    this.getResources().openRawResource(R.raw.kn5));
                GifView.gambargif=R.drawable.akn;
                break;
        }
        Dialog settingsDialog = new Dialog(getContext());
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(R.layout.hasilpengenalan);


        settingsDialog.show();
    }

    private void pencocokan(){
        Boolean hsl=false;
        int nilai_ambang = 250;
        Bitmap uji=prosescitranonlbp(mBitmap);
        Bitmap ujilbp=lbp(uji);

        String ak="Salah";

        //testing yg digunakan next... acak soal untuk program jadi...
        GifView.gambargif=animasi[next];
        for (int x=0; x<=4;x++){
            if (jarak_lbp(uji,ujilbp,BitmapFactory.decodeStream(this.getResources().openRawResource(database[next][x])))<=nilai_ambang){
                hsl=true;
                ak="Benar";
                x=5;
            }

        }

        Dialog settingsDialog = new Dialog(getContext());
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(R.layout.hasilpengenalan);

        String i=Integer.toString(k);
        simpan(uji,soal+""+i+ak);
        k=k+1;

        settingsDialog.show();

    }
    private static Boolean hasil_pencocokan_custom(Bitmap bmuji,InputStream is1,InputStream is2,InputStream is3,InputStream is4,InputStream is5){
        Boolean hsl=false;
        int nilai_ambang = 250;
        Bitmap uji=prosescitranonlbp(bmuji);
        Bitmap ujilbp=lbp(uji);
        if (jarak_lbp(uji,ujilbp,BitmapFactory.decodeStream(is1))<=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp(uji,ujilbp,BitmapFactory.decodeStream(is2))<=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp(uji,ujilbp,BitmapFactory.decodeStream(is3))<=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp(uji,ujilbp,BitmapFactory.decodeStream(is4))<=nilai_ambang){
            hsl=true;
        }
        else if (jarak_lbp(uji,ujilbp,BitmapFactory.decodeStream(is5))<=nilai_ambang){
            hsl=true;
        }
        return hsl;
    }



}
