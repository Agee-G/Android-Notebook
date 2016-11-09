package com.bignerdranch.android.notebook;

import android.support.v4.app.Fragment;

/**
 * Created by G__Agee on 2016/11/3.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
