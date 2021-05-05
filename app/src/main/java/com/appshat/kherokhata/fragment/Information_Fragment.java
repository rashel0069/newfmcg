package com.appshat.kherokhata.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.appshat.kherokhata.OldAcrivity.Helper;
import com.appshat.kherokhata.OldAcrivity.Localhelper;
import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.DAO.CashboxDao;
import com.appshat.kherokhata.Room.DB.Databaseroom;
import com.appshat.kherokhata.Room.ENTITY.CashboxEntity;
import com.appshat.kherokhata.Room.ENTITY.InformationEntity;
import com.appshat.kherokhata.Room.model.CashBoxViewModel;
import com.appshat.kherokhata.Room.model.InformationViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class Information_Fragment extends Fragment {
    public static final String MY_PREF_NAME = "myPrefFile";
    TextView shoppnameTV, ownernameTV,categoryTV, addressTV, nidnumberTV, treadlnTV, openingTV, receivableTV, payableTV;
    EditText shoppnameEt, ownernameET, addressET, nidnumberET, categoryET,tradelnET, openingET, receivableET, payableET;
    TextView saveBtn;
    String usernid, shopname, shopkeepername,shopcategory,tradelnc, shopaddress, opening, receivable, payable, dayend, withdrawal, deposit, datetime, imageuri;
    Context context;
    Resources resources;
    ImageView photoUp, profileImage;
    Uri uri;
    InformationViewModel informationViewModel;
    Databaseroom databaseroom;
    CashboxDao cashboxDBdao;
    CashBoxViewModel cashBoxViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.account_info, container, false);

//        //database
        cashBoxViewModel = ViewModelProviders.of(getActivity()).get(CashBoxViewModel.class);
        informationViewModel = ViewModelProviders.of(getActivity()).get(InformationViewModel.class);
        shoppnameEt = view.findViewById(R.id.sname_ET);
        ownernameET = view.findViewById(R.id.sownername_ET);
        categoryET = view.findViewById(R.id.selectcategory_id);
        tradelnET = view.findViewById(R.id.tradeln_id);
        addressET = view.findViewById(R.id.saddress_ET);
        nidnumberET = view.findViewById(R.id.nid_id);
        openingET = view.findViewById(R.id.openingamount_ET);
        receivableET = view.findViewById(R.id.receivableamount_ET);
        payableET = view.findViewById(R.id.payableamount_ET);
        saveBtn = view.findViewById(R.id.saved_id);
        photoUp = view.findViewById(R.id.editphoto_id);
        profileImage = view.findViewById(R.id.profile_img_id);

        //Date time
        String currentdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new java.util.Date());
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        cal.add(Calendar.DAY_OF_YEAR, -1);
        datetime = s.format(cal.getTime());

        //upload photo
        photoUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK);
                pickPhoto.setType("image/*");
                startActivityForResult(pickPhoto, 1);
            }
        });

//language setter
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(shoppnameEt.getText().toString()) && !TextUtils.isEmpty(ownernameET.getText().toString()) &&
                        !TextUtils.isEmpty(addressET.getText().toString()) && !TextUtils.isEmpty(nidnumberET.getText().toString().trim())
                        && !TextUtils.isEmpty(categoryET.getText().toString().trim())  &&
                        !TextUtils.isEmpty(openingET.getText().toString().trim()) && !TextUtils.isEmpty(receivableET.getText().toString().trim())
                        && !TextUtils.isEmpty(payableET.getText().toString().trim())) {

                    shopname = shoppnameEt.getText().toString();
                    shopkeepername = ownernameET.getText().toString();
                    shopaddress = addressET.getText().toString();
                    usernid = nidnumberET.getText().toString().trim();
                    shopcategory = shoppnameEt.getText().toString().trim();
                    tradelnc = tradelnET.getText().toString().trim();
                    opening = openingET.getText().toString().trim();
                    receivable = receivableET.getText().toString().trim();
                    payable = payableET.getText().toString().trim();
                    profileImage.setDrawingCacheEnabled(true);
                    profileImage.buildDrawingCache();

                    Bitmap bitmap = profileImage.getDrawingCache();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] data = baos.toByteArray();

                    InformationEntity informationEntity = new InformationEntity(usernid, shopkeepername,shopname,shopcategory, shopaddress, tradelnc, opening, receivable, payable, data);
                    informationViewModel.insertInfo(informationEntity);
                    CashboxEntity cashboxEntity = new CashboxEntity(datetime, opening, "0", "0");
                    cashBoxViewModel.insertCashbox(cashboxEntity);
                    //save data
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE).edit();
                    editor.putString("opencash", opening);
                    editor.putString("receivablecash", receivable);
                    editor.putString("payablecash", payable);
                    editor.putBoolean("visibility", true);
                    editor.apply();
                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                    Toast.makeText(getContext(), "Thank you for your kind informations", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please fill up all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            uri = data.getData();
            final InputStream imageStream;
            imageStream = getActivity().getContentResolver().openInputStream(uri);
            final Bitmap image = BitmapFactory.decodeStream(imageStream);
            //imageuri = uri.toString();
            profileImage.setImageBitmap(image);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
