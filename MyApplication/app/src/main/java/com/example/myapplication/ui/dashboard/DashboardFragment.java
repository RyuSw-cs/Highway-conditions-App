package com.example.myapplication.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataHighway;
import com.example.myapplication.GetDataHighway;
import com.example.myapplication.LoadDataHighway;
import com.example.myapplication.MyAdapter;
import com.example.myapplication.R;
import com.example.myapplication.Retrofit_Highway;
import com.example.myapplication.Road;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private MyAdapter mAdapter;

    public List<DataHighway> result_data = new ArrayList<>();
    private ArrayList<Road> list = new ArrayList<>();
    private ArrayList<Road> data_s = new ArrayList<>();

    public LoadDataHighway result;
    String key = "8098105639";
    private DashboardViewModel dashboardViewModel;
    private Context mContext;

    Spinner spinner, spinner2;
    ImageView img = null;
    ArrayAdapter<CharSequence> adspin, adspin2;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        insert_data();
        refresh();
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_traffic, container, false);

        spinner = (Spinner) root.findViewById(R.id.spinner2);
        spinner.setPrompt("노선을 선택하세요.");
        spinner2 = (Spinner) root.findViewById(R.id.spinner3);
        spinner.setPrompt("상/하행을 선택하세요.");

        adspin = ArrayAdapter.createFromResource(mContext, R.array.road, android.R.layout.simple_spinner_item);
        adspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adspin);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                data_s.clear();
                for(int k = 0; k<list.size(); k++){
                    if(adspin.getItem(position).equals(list.get(k).getRouteName()) && adspin2.getItem(position).equals("상행")&&list.get(k).getUpdownTypeCode().equals("S")){
                        data_s.add(new Road(list.get(k).getConzoneName(), list.get(k).getRouteName(), list.get(k).getStdHour(), list.get(k).getUpdownTypeCode()));
                        data_s.get(data_s.size()-1).setGrade(list.get(k).getGrade());
                    }
                    if(adspin.getItem(position).equals(list.get(k).getRouteName()) && adspin2.getItem(position).equals("하행")&&list.get(k).getUpdownTypeCode().equals("E")){
                        data_s.add(new Road(list.get(k).getConzoneName(), list.get(k).getRouteName(), list.get(k).getStdHour(), list.get(k).getUpdownTypeCode()));
                        data_s.get(data_s.size()-1).setGrade(list.get(k).getGrade());
                    }
                }
                mAdapter = new MyAdapter(img, data_s);
                recyclerView.setAdapter(mAdapter);
            }
            public void onNothingSelected(AdapterView<?>  parent) {}
        });
        adspin2 = ArrayAdapter.createFromResource(mContext, R.array.updown, android.R.layout.simple_spinner_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adspin2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                data_s.clear();
                for(int k = 0; k<list.size(); k++){
                    if(adspin2.getItem(position).equals("상행")&&list.get(k).getUpdownTypeCode().equals("S")){
                        data_s.add(new Road(list.get(k).getConzoneName(), list.get(k).getRouteName(), list.get(k).getStdHour(), list.get(k).getUpdownTypeCode()));
                        data_s.get(data_s.size()-1).setGrade(list.get(k).getGrade());
                    }
                    if(adspin2.getItem(position).equals("하행")&&list.get(k).getUpdownTypeCode().equals("E")){
                        data_s.add(new Road(list.get(k).getConzoneName(), list.get(k).getRouteName(), list.get(k).getStdHour(), list.get(k).getUpdownTypeCode()));
                        data_s.get(data_s.size()-1).setGrade(list.get(k).getGrade());
                    }
                }
                mAdapter = new MyAdapter(img, data_s);
                recyclerView.setAdapter(mAdapter);
            }
            public void onNothingSelected(AdapterView<?>  parent) {}
        });
        recyclerView = root.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);


        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    //데이터 넣어주기만 하면 끝납니다.
    public void insert_data() {
        //콘조, 루트, 시간, 상하행
        list.add(new Road("대관령IC-진부IC", "영동선", "default", "S"));
        list.add(new Road("진부IC-속사IC", "영동선", "default", "S"));
        list.add(new Road("속사IC-평창IC", "영동선", "default", "S"));
        list.add(new Road("평창IC-면온IC", "영동선", "default", "S"));
        list.add(new Road("면온IC-동둔내하이패스IC", "영동선", "default", "S"));
        list.add(new Road("동둔내하이패스IC-둔내IC", "영동선", "default", "S"));
        list.add(new Road("둔내IC-새말IC", "영동선", "default", "S"));
        list.add(new Road("새말IC-원주IC", "영동선", "default", "S"));
        list.add(new Road("원주IC-원주JC", "영동선", "default", "S"));
        list.add(new Road("원주JC-만종JC", "영동선", "default", "S"));
        list.add(new Road("만종JC-문막IC", "영동선", "default", "S"));
        list.add(new Road("문막IC-여주JC", "영동선", "default", "S"));
        list.add(new Road("여주JC-이천IC", "영동선", "default", "S"));
        list.add(new Road("이천IC-호법JC", "영동선", "default", "S"));
        list.add(new Road("호법JC-덕평IC", "영동선", "default", "S"));
        list.add(new Road("덕평IC-양지IC", "영동선", "default", "S"));
        list.add(new Road("양지IC-용인IC", "영동선", "default", "S"));
        list.add(new Road("용인IC-마성IC", "영동선", "default", "S"));
        list.add(new Road("마성IC-신갈IC", "영동선", "default", "S"));
        list.add(new Road("신갈IC-동수원IC", "영동선", "default", "S"));
        list.add(new Road("동수원IC-북수원IC", "영동선", "default", "S"));
        list.add(new Road("북수원IC-부곡IC", "영동선", "default", "S"));
        list.add(new Road("부곡IC-동군포IC", "영동선", "default", "S"));
        list.add(new Road("동군포IC-군포IC", "영동선", "default", "S"));
        list.add(new Road("군포IC-둔대JC", "영동선", "default", "S"));
        list.add(new Road("둔대JC-안산JC", "영동선", "default", "S"));
        list.add(new Road("안산JC-안산IC", "영동선", "default", "S"));
        list.add(new Road("서안산IC-군자요금소", "영동선", "default", "S"));
        list.add(new Road("군자요금소-군자JC", "영동선", "default", "S"));
        list.add(new Road("군자JC-월곶JC", "영동선", "default", "S"));
        list.add(new Road("월곶JC-서창JC", "영동선", "default", "S"));
        list.add(new Road("서창JC-서창기점", "영동선", "default", "S"));

        list.add(new Road("서창기점-서창JC", "영동선", "default", "E"));
        list.add(new Road("서창JC-월곶JC", "영동선", "default", "E"));
        list.add(new Road("월곶JC-군자JC", "영동선", "default", "E"));
        list.add(new Road("군자JC-군자요금소", "영동선", "default", "E"));
        list.add(new Road("군자요금소-서안산IC", "영동선", "default", "E"));
        list.add(new Road("서안산IC-안산IC", "영동선", "default", "E"));
        list.add(new Road("안산IC-안산JC", "영동선", "default", "E"));
        list.add(new Road("안산JC-둔대JC", "영동선", "default", "E"));
        list.add(new Road("둔대JC-군포IC", "영동선", "default", "E"));
        list.add(new Road("군포IC-동군포IC", "영동선", "default", "E"));
        list.add(new Road("동군포IC-부곡IC", "영동선", "default", "E"));
        list.add(new Road("부곡IC-북수원IC", "영동선", "default", "E"));
        list.add(new Road("북수원IC-동수원IC", "영동선", "default", "E"));
        list.add(new Road("동수원IC-신갈JC", "영동선", "default", "E"));
        list.add(new Road("신갈JC-마성IC", "영동선", "default", "E"));
        list.add(new Road("마성IC-용인IC", "영동선", "default", "E"));
        list.add(new Road("용인IC-양지IC", "영동선", "default", "E"));
        list.add(new Road("양지IC-덕평IC", "영동선", "default", "E"));
        list.add(new Road("덕평IC-호법JC", "영동선", "default", "E"));
        list.add(new Road("호법JC-이천IC", "영동선", "default", "E"));
        list.add(new Road("이천IC-여주JC", "영동선", "default", "E"));
        list.add(new Road("여주JC-여주IC", "영동선", "default", "E"));
        list.add(new Road("여주IC-문막IC", "영동선", "default", "E"));
        list.add(new Road("문막IC-만종JC", "영동선", "default", "E"));
        list.add(new Road("만종JC-원주JC", "영동선", "default", "E"));
        list.add(new Road("원주JC-새말IC", "영동선", "default", "E"));
        list.add(new Road("둔내IC-동둔내하이패스IC", "영동선", "default", "E"));
        list.add(new Road("동둔내하이패스IC-면온IC", "영동선", "default", "E"));
        list.add(new Road("면온IC-평창IC", "영동선", "default", "E"));
        list.add(new Road("평창IC-속사IC", "영동선", "default", "E"));
        list.add(new Road("속사IC-진부IC", "영동선", "default", "E"));
        list.add(new Road("진부IC-대관령IC", "영동선", "default", "E"));
        for(int i = 0; i < list.size();i++){
            if(list.get(i).getRouteName().equals("영동선") && list.get(i).getUpdownTypeCode().equals("S")){
                data_s.add(list.get(i));
            }
        }
    }
    public void change_data() {
        if(result_data.size() != 0) {
            for (int i = 0; i < result_data.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    //막힌 경로가 똑같이 있다면?
                    if (result_data.get(i).getConzoneName().equals(list.get(j).getConzoneName())) {
                        list.get(j).setGrade(result_data.get(i).getGrade());
                    }
                }
            }
        }
    }
    public void refresh(){
        /*레트로핏을 이용하여 데이터 가져오기*/
        //정보를 가지는 리스트를 초기화!
        GetDataHighway service1 = Retrofit_Highway.getInstance().create(GetDataHighway.class);
        Call<LoadDataHighway> call = service1.getInstanceHighway(key, "json");
        //레트로핏 비동기 실행
        call.enqueue(new Callback<LoadDataHighway>() {
            @Override
            public void onResponse(Call<LoadDataHighway> call, Response<LoadDataHighway> response) {
                if(response.isSuccessful()){
                    result = (LoadDataHighway)response.body();
                    if(result.getDhh().size() != 0) {
                        for (int i = 0; i < result.getDhh().size(); i++) {
                            result_data.add(
                                    new DataHighway(result.getDhh().get(i).getStdHour(),
                                            result.getDhh().get(i).getRouteNo(),
                                            result.getDhh().get(i).getRouteName(),
                                            result.getDhh().get(i).getUpdownTypeCode(),
                                            result.getDhh().get(i).getVdsId(),
                                            result.getDhh().get(i).getTrafficAmout(),
                                            result.getDhh().get(i).getShareRatio(),
                                            result.getDhh().get(i).getConzoneId(),
                                            result.getDhh().get(i).getConzoneName(),
                                            result.getDhh().get(i).getStdDate(),
                                            result.getDhh().get(i).getSpeed(),
                                            result.getDhh().get(i).getTimeAvg(),
                                            result.getDhh().get(i).getGrade()));
                        }
                        OnSuccess(result_data);
                    }
                }
                else{
                    Log.v("결과는 : ","실패");
                }

            }
            @Override
            public void onFailure(Call<LoadDataHighway> call, Throwable t) {
                Log.d("결과는 : ","아에 연결도 안돼!");
            }

        });

    }
    public void OnSuccess(List<DataHighway> result_data) {
        change_data();
    }
}