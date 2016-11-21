package com.bignerdranch.android.notebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by G__Agee on 2016/11/3.
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
   // private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static CrimeLab get(Context context){

        if(sCrimeLab==null){
            sCrimeLab=new CrimeLab(context);
        }
        return sCrimeLab;
    }
    private CrimeLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase=new CrimeBaseHelper(mContext)
                .getWritableDatabase();
       // mCrimes=new ArrayList<>();

    }
    public void addCrime(Crime c){
        //mCrimes.add(c);
    }
    public List<Crime> getCrimes(){
        //return mCrimes;
        return new ArrayList<>();
    }
    public Crime getCrime(UUID id){
        /**for(Crime crime:mCrimes){
            if (crime.getId().equals(id)) {
                return crime;
            }
        }*/
        return null;
    }
    private static ContentValues getContentValues(Crime crime){
        ContentValues values=new ContentValues();
        values.put(CrimeDbSchema.CrimeTable.Cols.UUID,crime.getId().toString());
        values.put(CrimeDbSchema.CrimeTable.Cols.TIYLE,crime.getTitle().toString());
        values.put(CrimeDbSchema.CrimeTable.Cols.DATE,crime.getDate().toString());
        values.put(CrimeDbSchema.CrimeTable.Cols.SOLVED,crime.getSolved()?1:0);

        return values;
    }
}
