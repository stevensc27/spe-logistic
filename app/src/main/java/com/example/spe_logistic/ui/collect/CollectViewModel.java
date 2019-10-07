package com.example.spe_logistic.ui.collect;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class CollectViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CollectViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}