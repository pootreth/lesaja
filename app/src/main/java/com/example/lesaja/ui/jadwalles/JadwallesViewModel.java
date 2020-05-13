package com.example.lesaja.ui.jadwalles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JadwallesViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public JadwallesViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is jadwalles fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}