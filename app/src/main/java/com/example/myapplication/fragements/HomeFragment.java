package com.example.myapplication.fragements;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.api.RetrofitClient;
import com.example.myapplication.api.WebApi;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.db.DBManager;
import com.example.myapplication.models.User;
import com.example.myapplication.models.UserDetail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    ArrayAdapter<String> adapter;
    ArrayList<String> usersData = new ArrayList<>();
    ArrayList<String> searchResult = new ArrayList<>();
    private void search(String s){
        searchResult.clear();
        for(int i=0; i< usersData.size() ; i++ ){
            if(usersData.get(i).contains(s)){
                searchResult.add(usersData.get(i));
            }
        }
        adapter.notifyDataSetChanged();
//        adapter = new ArrayAdapter<>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                searchResult);
//
//        binding.listViewUsers.setAdapter(adapter);
    }
    private void populateListView(){
//        DBManager mgr = new DBManager(getActivity());
//         usersData.addAll(mgr.getAllUsers());
//        searchResult.addAll(mgr.getAllUsers());
        WebApi api = RetrofitClient.getInstance().getMyApi();
        api.getAllUsers().enqueue(new Callback<ArrayList<UserDetail>>() {
            @Override
            public void onResponse(Call<ArrayList<UserDetail>> call,
                                   Response<ArrayList<UserDetail>> response) {
                if(response.isSuccessful()){
                    ArrayList<UserDetail> userDetails = response.body();
                    ArrayAdapter<UserDetail> adp = new ArrayAdapter<>(
                            getActivity(),
                            android.R.layout.simple_list_item_1,
                            userDetails);

                    binding.listViewUsers.setAdapter(adp);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<UserDetail>> call, Throwable t) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        populateListView();
        binding.editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               //edit text
                search(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.searchViewUsers.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //searchview
                search(s);
                return false;
            }
        });
        binding.listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,
                                    View view, int p, long l) {
                String data = searchResult.get(p);
                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }











}