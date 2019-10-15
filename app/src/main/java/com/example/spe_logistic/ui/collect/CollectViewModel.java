package com.example.spe_logistic.ui.collect;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class CollectViewModel extends ViewModel {

    private MutableLiveData<ArrayList<CollectVo>> collect_list;

    public CollectViewModel() {

        collect_list = new MutableLiveData<>();

        ArrayList<CollectVo> collect_array_list = new ArrayList<>();


        collect_array_list.add(new CollectVo("03","Calle 15 96 36","01/10/2019","1"));
        collect_array_list.add(new CollectVo("04","Crra 36 52","08/10/2019","1"));
        collect_array_list.add(new CollectVo("05","Calle 96A sur 36","07/11/2019","2"));
        collect_array_list.add(new CollectVo("06","Calle 34A 75 sur 03","06/11/2019","1"));
        collect_array_list.add(new CollectVo("07","Circular 1A 39","05/11/2019","2"));
        collect_array_list.add(new CollectVo("09","Crra 64 93 1","01/11/2019","1"));
        collect_array_list.add(new CollectVo("10","Crra 92 88BB 63","02/11/2019","3"));


        collect_list.setValue(collect_array_list);

    }

    public LiveData<ArrayList<CollectVo>> getCollectList() {
        return collect_list;
    }
}