package com.example.spe_logistic.ui.perfil;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.spe_logistic.MyApp;
import com.example.spe_logistic.R;
import com.example.spe_logistic.SQLiteConnectionHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilPqrFragment extends Fragment implements SearchView.OnQueryTextListener {


    ArrayList<PerfilPqrVo> perfilPqrVos = new ArrayList<>();
    private PerfilPqrAdapter adapter;
    private NavController navController;
    private RecyclerView pqr_list;
    private FloatingActionButton new_pqr;
    private int user_id;
    private SQLiteConnectionHelper con;

    public PerfilPqrFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_perfil_pqr, container, false);

        con = new SQLiteConnectionHelper(getContext(),"SPEDB",null,1);
        SQLiteDatabase db = con.getReadableDatabase();

        SharedPreferences preferences = getContext().getSharedPreferences("credentials", getContext().MODE_PRIVATE);
        user_id = preferences.getInt("user_id",0);

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        new_pqr = root.findViewById(R.id.new_pqr);
        new_pqr.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            navController.navigate(R.id.perfilPqrItemFragment,bundle);
        });

        pqr_list = root.findViewById(R.id.pqr_list);
        pqr_list.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<PerfilPqrVo> pqr_array_list = new ArrayList<>();
        String queryPqr =   "SELECT     pqrs.id, " +
                            "           categorias_pqrs.nombre, " +
                            "           descripcion, " +
                            "           estado_id " +
                            "FROM       pqrs " +
                            "INNER JOIN categorias_pqrs " +
                            "ON         categorias_pqrs.id = pqrs.categoria_id " +
                            "WHERE      cliente_id = "+user_id +" " +
                            "ORDER BY   pqrs.id DESC";
        Cursor cursor = db.rawQuery(queryPqr,null);
        try {
            while (cursor.moveToNext()) {
                pqr_array_list.add(new PerfilPqrVo(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }
        } finally {
            cursor.close();
        }

        perfilPqrVos = pqr_array_list;
        adapter = new PerfilPqrAdapter(perfilPqrVos);
        pqr_list.setAdapter(adapter);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Mis PQRs");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        ArrayList<PerfilPqrVo> newList = new ArrayList<>();

        for (int i = 0; i < perfilPqrVos.size(); i++){
            PerfilPqrVo perfilPqrVo = perfilPqrVos.get(i);

            if (perfilPqrVo.getId().contains(userInput) || perfilPqrVo.getDescription().contains(userInput)){
                newList.add(perfilPqrVo);
            }
        }

        if (newList.size() == 0){
            adapter.updateList(perfilPqrVos);
        }else {
            adapter.updateList(newList);
        }

        return true;
    }
}
