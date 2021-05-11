package com.appshat.kherokhata.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.OldAcrivity.MainActivity;
import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DAO.InformationDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.InformationEntity;
import com.appshat.kherokhata.Room.model.InformationViewModel;
import com.appshat.kherokhata.adapter.DataConverter;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class Profile_Fragment extends Fragment {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    RelativeLayout languageselector;
    TextView editTV, langTV, logoutTV, shopkeepName, shopAddress,about,cpTV,helpTV;
    MaterialButton webBtn,fbBtn;
    ImageView photoUp, profileImage;
    String shopN, shopAd;
    boolean lang_selected = true;
    Context context;
    Resources resources;
    InformationViewModel informationViewModel;
    InformationDao informationDao;
    Databaseroom databaseroom;
    byte[] imageUrl;
    Uri uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile__nav_, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);


        languageselector = view.findViewById(R.id.cardView3);
        editTV = view.findViewById(R.id.editprofile_id);
        langTV = view.findViewById(R.id.lang_id);
        logoutTV = view.findViewById(R.id.logout_id);
        profileImage = view.findViewById(R.id.profile_img_id);
        shopkeepName = view.findViewById(R.id.textView3);
        shopAddress = view.findViewById(R.id.textView2);
        cpTV=view.findViewById(R.id.changepass_pro_id);
        helpTV=view.findViewById(R.id.help_pro_id);
        about=view.findViewById(R.id.abk_pro_id);
        webBtn=view.findViewById(R.id.cardView4);
        fbBtn=view.findViewById(R.id.cardView5);

        databaseroom = Databaseroom.getDatabaseroomref(getActivity());
        informationDao = databaseroom.getInformationDao();
        informationViewModel = ViewModelProviders.of(getActivity()).get(InformationViewModel.class);


        // using login swtiching the language

        //language setter
        if (!Helper.getBangla()) {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "en");
            resources = context.getResources();
            langTV.setText(resources.getString(R.string.selector));
            langTV.setText(resources.getString(R.string.selector));
            editTV.setText(resources.getString(R.string.edit));
            shopkeepName.setText(resources.getString(R.string.sownername));
            shopAddress.setText(resources.getString(R.string.shopaddress));
            logoutTV.setText(resources.getString(R.string.logout));
            cpTV.setText(resources.getString(R.string.cp));
            helpTV.setText(resources.getString(R.string.help));
            about.setText(resources.getString(R.string.aboutk));
            webBtn.setText(resources.getString(R.string.digitalistic));
            fbBtn.setText(resources.getString(R.string.facebook));



        } else {
            Log.e("Bangla1", String.valueOf(Helper.getBangla()));
            context = Localhelper.setLocale(getActivity(), "bn");
            resources = context.getResources();
            langTV.setText(resources.getString(R.string.selector));
            editTV.setText(resources.getString(R.string.edit));
            shopkeepName.setText(resources.getString(R.string.sownername));
            shopAddress.setText(resources.getString(R.string.shopaddress));
            logoutTV.setText(resources.getString(R.string.logout));
            cpTV.setText(resources.getString(R.string.cp));
            helpTV.setText(resources.getString(R.string.help));
            about.setText(resources.getString(R.string.aboutk));
            webBtn.setText(resources.getString(R.string.digitalistic));
            fbBtn.setText(resources.getString(R.string.facebook));

        }
// from in this fragment switching the language
        languageselector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] language = {"English", "Bangla"};
                int checkeditem;

                if (Helper.getBangla()) {
                    checkeditem = 1;
                } else {
                    checkeditem = 0;
                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select a language").setSingleChoiceItems(language, checkeditem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        langTV.setText(language[which]);
                        if (language[which].equals("English")) {
                            Helper.setBangla(false);
                            context = Localhelper.setLocale(getActivity(), "en");
                            resources = context.getResources();

                            langTV.setText(resources.getString(R.string.selector));
                            editTV.setText(resources.getString(R.string.edit));
                            logoutTV.setText(resources.getString(R.string.logout));
                            cpTV.setText(resources.getString(R.string.cp));
                            helpTV.setText(resources.getString(R.string.help));
                            about.setText(resources.getString(R.string.aboutk));
                            webBtn.setText(resources.getString(R.string.digitalistic));
                            fbBtn.setText(resources.getString(R.string.facebook));

                        }

                        if (language[which].equals("Bangla")) {
                            Helper.setBangla(true);
                            context = Localhelper.setLocale(getActivity(), "bn");
                            resources = context.getResources();
                            langTV.setText(resources.getString(R.string.selector));
                            editTV.setText(resources.getString(R.string.edit));
                            logoutTV.setText(resources.getString(R.string.logout));
                            cpTV.setText(resources.getString(R.string.cp));
                            helpTV.setText(resources.getString(R.string.help));
                            about.setText(resources.getString(R.string.aboutk));
                            webBtn.setText(resources.getString(R.string.digitalistic));
                            fbBtn.setText(resources.getString(R.string.facebook));

                        }

                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        // edit profile
        RelativeLayout edit = view.findViewById(R.id.cardView2);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Information_Fragment information_fragment = new Information_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, information_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //website
        MaterialButton web = view.findViewById(R.id.cardView4);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserInt = new Intent(Intent.ACTION_VIEW, Uri.parse("http://digitalistic.co/"));
                startActivity(browserInt);
                Toast.makeText(context, "Open WebSite", Toast.LENGTH_SHORT).show();
            }
        });
        //facebook
        MaterialButton fb = view.findViewById(R.id.cardView5);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserInt = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/digitalistic7"));
                startActivity(browserInt);
                Toast.makeText(context, "Open Facebook", Toast.LENGTH_SHORT).show();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                About_Fragment about_fragment = new About_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout_container_id, about_fragment);
                transaction.addToBackStack("null");
                transaction.commit();
            }
        });
        //logout
        RelativeLayout logout = view.findViewById(R.id.logout_card_id);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().clear().clear().commit();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        try {
            String s = new GetInformation().execute().get();
            if (shopN != null && shopAd != null) {
                shopkeepName.setText(shopN);
                shopAddress.setText(shopAd);
                profileImage.setImageBitmap(DataConverter.convertByteArrayToImage(imageUrl));
//                if (imageUrl.length > 0){
//                    profileImage.setImageBitmap( DataConverter.convertByteArrayToImage( imageUrl ) );
//                }else{
//                    profileImage.setImageDrawable( R.drawable.nopreview );
//                }
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error" + e, Toast.LENGTH_SHORT).show();
        }


        return view;
    }

    public class GetInformation extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            List<InformationEntity> informationEntities = informationDao.findAllInfo();
            try {
                if (informationEntities != null) {
                    for (int i = 0; i <= informationEntities.size(); i++) {
                        shopN = informationEntities.get(i).getShopkeepername();
                        shopAd = informationEntities.get(i).getShopaddress();
                        imageUrl = informationEntities.get(i).getImageurl();
                    }
                }

            } catch (Exception e) {

            }
            return null;
        }

    }

}