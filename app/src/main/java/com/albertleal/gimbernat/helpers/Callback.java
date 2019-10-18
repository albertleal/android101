package com.albertleal.gimbernat.helpers;

public interface Callback<Object> {

    void onSuccess(Object responseObject);
    void onError();

}

