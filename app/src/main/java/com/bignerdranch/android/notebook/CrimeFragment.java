package com.bignerdranch.android.notebook;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.LayoutInflaterCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by G__Agee on 2016/11/3.
 */
public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID="crime_id";
    private static final String DIALOG_DATE="DialogDate";
    private static final int REQUEST_DATE=0;
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    public String TAG;

    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args=new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeId);
        CrimeFragment fragment=new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //mCrime=new Crime();
        //getIntent()方法返回用来启动CrimeActivity的intent，然后用intent的getSerializableExtra（String)方法UUID来获取并存入变量crimeId中

        UUID crimeId=(UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime=CrimeLab.get(getActivity()).getCrime(crimeId);
    }
    @Override
    public void onPause(){
        super.onPause();
        CrimeLab.get(getActivity())
                .updateCrime(mCrime);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View v=inflater.inflate(R.layout.fragment_crime,container,false);
        mTitleField=(EditText)v.findViewById(R.id.crime_title);

        mTitleField.setText(mCrime.getTitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //this space intentionally left blank

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this one too

            }
        });
        mDateButton=(Button)v.findViewById(R.id.crime_date);
        updateDate();
        //mDateButton.setEnabled(false);
        //禁用按钮
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager manager=getFragmentManager();
                //DatePickerFragment dialog=new DatePickerFragment();
                DatePickerFragment dialog= DatePickerFragment
                        .newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                dialog.show(manager,DIALOG_DATE);
            }
        });
        mSolvedCheckBox=(CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Set the crime's solved property
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode==REQUEST_DATE){
            Date date=(Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
    }


}
