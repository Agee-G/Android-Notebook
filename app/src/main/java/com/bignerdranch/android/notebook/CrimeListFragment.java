package com.bignerdranch.android.notebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by G__Agee on 2016/11/3.
 */
public class CrimeListFragment extends Fragment{
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdaptere;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView=(RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }
    private void updateUI(){
        CrimeLab crimeLab=CrimeLab.get(getActivity());
        List<Crime> crimes=crimeLab.getCrimes();
        if(mAdaptere==null){
            //该方法创建CrimeAdapter，然后设置给RecyclerView
            mAdaptere=new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdaptere);
        }else {
            mAdaptere.notifyDataSetChanged();
        }

    }

    private class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView=(TextView)
                    itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView=(TextView)
                    itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox=(CheckBox)
                    itemView.findViewById(R.id.list_item_crime_solved_check_box);

        }
        public void bindCrime(Crime crime){
            mCrime=crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());

        }
        //点击监听器
        @Override
        public void onClick(View v){
           /* Toast.makeText(getActivity(),mCrime.getTitle()+"Hello World!",Toast.LENGTH_SHORT)
            .show();*/
            //Intent intent=new Intent(getActivity(),CrimeActivity.class);
            //现crime ID已安全存储在CrimeActivity的intent中
            Intent intent=CrimeActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }
    }



    public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes){
            mCrimes=crimes;
        }

        //RecyclerView需要新的View视图来显示表项时，会调用此方法
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent,int viewType){
            LayoutInflater LayoutInflater= android.view.LayoutInflater.from(getActivity());
            //创建View视图 封装到ViewHolder中
            View view=LayoutInflater
                    .inflate(R.layout.list_item_crime,parent,false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder,int position){
            //onBindViewHolder该方法会把ViewHolder的View视图和模型层数据绑定起来
            Crime crime=mCrimes.get(position);
           // holder.mTitleTextView.setText(crime.getTitle());
            holder.bindCrime(crime);
        }
        @Override
        public int getItemCount(){
            return mCrimes.size();
        }
    }

    //此方法是将Adapter和RecyclerView关联起来



}
