package com.example.spe_logistic.ui.references;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class ReferencesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ReferencesVo>> references_list;

    public ReferencesViewModel() {
        //SQLiteConnectionHelper con = new SQLiteConnectionHelper(MyApp.getContext(),"SPEDB",null,1);
        //SQLiteDatabase db = con.getWritableDatabase();

        references_list = new MutableLiveData<>();

        ArrayList<ReferencesVo> references_array_list = new ArrayList<>();
        
        /*
        SELECT  id,codigo_barras,nombre
        FROM    referencias
        WHERE   cliente_id = ***
        */

        references_array_list.add(new ReferencesVo("123","022426","2222222222222","producto prueba 123"));
        references_array_list.add(new ReferencesVo("456","022466","3333333333333","producto prueba 3454"));
        references_array_list.add(new ReferencesVo("789","035151","4444444444444","producto prueba 54"));
        references_array_list.add(new ReferencesVo("123","022426","2222222222222","producto prueba 123"));
        references_array_list.add(new ReferencesVo("456","022466","3333333333333","producto prueba 3454"));
        references_array_list.add(new ReferencesVo("789","035151","4444444444444","producto prueba 54"));
        references_array_list.add(new ReferencesVo("123","022426","2222222222222","producto prueba 123"));
        references_array_list.add(new ReferencesVo("456","022466","3333333333333","producto prueba 3454"));
        references_array_list.add(new ReferencesVo("789","035151","4444444444444","producto prueba 54"));
        references_array_list.add(new ReferencesVo("123","022426","2222222222222","producto prueba 123"));
        references_array_list.add(new ReferencesVo("456","022466","3333333333333","producto prueba 3454"));
        references_array_list.add(new ReferencesVo("789","035151","4444444444444","producto prueba 54"));
        references_array_list.add(new ReferencesVo("123","022426","2222222222222","producto prueba 123"));
        references_array_list.add(new ReferencesVo("456","022466","3333333333333","producto prueba 3454"));
        references_array_list.add(new ReferencesVo("789","035151","4444444444444","producto prueba 54"));
        references_array_list.add(new ReferencesVo("123","022426","2222222222222","producto prueba 123"));
        references_array_list.add(new ReferencesVo("456","022466","3333333333333","producto prueba 3454"));
        references_array_list.add(new ReferencesVo("789","035151","4444444444444","producto prueba 54"));
        references_array_list.add(new ReferencesVo("123","022426","2222222222222","producto prueba 123"));
        references_array_list.add(new ReferencesVo("456","022466","3333333333333","producto prueba 3454"));
        references_array_list.add(new ReferencesVo("789","035151","4444444444444","producto prueba 54"));
        references_array_list.add(new ReferencesVo("123","022426","2222222222222","producto prueba 123"));
        references_array_list.add(new ReferencesVo("456","022466","3333333333333","producto prueba 3454"));
        references_array_list.add(new ReferencesVo("789","035151","4444444444444","producto prueba 54"));
        references_array_list.add(new ReferencesVo("123","022426","2222222222222","producto prueba 123"));
        references_array_list.add(new ReferencesVo("456","022466","3333333333333","producto prueba 3454"));
        references_array_list.add(new ReferencesVo("789","035151","4444444444444","producto prueba 54"));

        references_list.setValue(references_array_list);
    }

    public LiveData<ArrayList<ReferencesVo>> getReferencesList() {
        return references_list;
    }
}
