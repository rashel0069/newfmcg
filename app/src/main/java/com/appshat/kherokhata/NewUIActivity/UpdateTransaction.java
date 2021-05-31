package com.appshat.kherokhata.NewUIActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appshat.kherokhata.R;
import com.appshat.kherokhata.Room.ENTITY.NewtransactionEntity;
import com.appshat.kherokhata.Room.ENTITY.TransactionEntity;
import com.appshat.kherokhata.Room.model.TransactionViewModel;
import com.appshat.kherokhata.fragment.NewTransaction_Fragment;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class UpdateTransaction extends AppCompatActivity {
    TextView cmName, cmPhone, cmAcctype, cmTranstype;
    EditText cmAmmount;
    static TextView cmextendDate;
    String currentDate,dudate;
    MaterialButton updateTrans;
    int id;
    ConstraintLayout pickdate;
    public static final String DATE_DIALOG_1 = "datePicker1";
    TransactionViewModel transactionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transaction);
        cmName = findViewById(R.id.upname_id);
        cmPhone = findViewById(R.id.upprhone_id);
        cmAcctype = findViewById(R.id.upaccutype_id);
        cmTranstype = findViewById(R.id.uptranstype_id);
        cmextendDate = findViewById(R.id.updateTV_id);
        cmAmmount = findViewById(R.id.upammount_id);
        updateTrans = findViewById(R.id.uptranssave_id);
        pickdate = findViewById(R.id.uppickdate_id);

        cmName.setText(getIntent().getStringExtra("Name"));
        cmPhone.setText(getIntent().getStringExtra("Phone"));
        cmAcctype.setText(getIntent().getStringExtra("Accounttype"));
        cmTranstype.setText(getIntent().getStringExtra("Transtype"));
        cmAmmount.setText(getIntent().getStringExtra("Ammount"));
        currentDate = getIntent().getStringExtra("DuDate");
        id = getIntent().getIntExtra("Id",0);

        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        //date picker
        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new UpdateTransaction.DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(),DATE_DIALOG_1);


            }
        });

        //update button
        updateTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateTranData();
            }
        });
    }

    private void UpdateTranData() {
        if (!cmextendDate.getText().toString().isEmpty()){
            int uId = id;
            String uAccount = cmAcctype.getText().toString();
            String uTransaction = cmTranstype.getText().toString();
            String uName = cmName.getText().toString();
            String uPhone = cmPhone.getText().toString();
            String uAmmount = cmAmmount.getText().toString();
            String uDuedate = cmextendDate.getText().toString();


            NewtransactionEntity newtransactionEntity = new NewtransactionEntity(uAccount,uTransaction,
                    uName,uPhone,uAmmount,uDuedate,currentDate);
            newtransactionEntity.setId(uId);
            transactionViewModel.updateTrans(newtransactionEntity);
            finish();


        }else {
            Toast.makeText(getApplicationContext(), "Pick Extend Date", Toast.LENGTH_SHORT).show();
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Date Time NOW
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it

            DatePickerDialog date1 = new DatePickerDialog(getContext(), this, year, month, day);
            date1.getDatePicker().setMinDate(System.currentTimeMillis());
            return date1;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // get selected date
            Time ca = new Time(Time.getCurrentTimezone());
            ca.set(day,month+1,year);
            // show selected date to date button
            cmextendDate.setText(ca.monthDay + "-0" + ca.month +"-"+ca.year);
        }
    }
}