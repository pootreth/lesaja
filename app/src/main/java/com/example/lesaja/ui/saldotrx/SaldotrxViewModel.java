package com.example.lesaja.ui.saldotrx;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SaldotrxViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public SaldotrxViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is saldo fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}