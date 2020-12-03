package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class NoticeActivity extends AppCompatActivity {
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notice);
        init();
        getData();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList(
                "[안내] COVID19로부터 우리를 지키는 행동수칙",
                "[안내] 끊김 오류 안내",
                "[안내] 전국 17개소 다차로 하이패스 공사 실시",
                "[안내] 주말 교통전망");
        List<String> listContent = Arrays.asList(
                "12.03 20:45",
                "12.01 20:35",
                "11.29 20:01",
                "11.23 19:36"
        );
        List<String> listMain = Arrays.asList(
                "1. 흐르는 물에 비누로 꼼꼼히 손 씻기\n2. 기침 재채기할 때 옷소매로 입과 코 가리기 \n 씻지 않은 손으로 눈,코,입 만지지 않기",
                "서버상의 문제로 인해서 휴게소 지도에서 끊김 현상이 발생할 수 있으니 이 점 양해해 주시기 바랍니다.",
                "전국 17개소 다차로 하이패스 공사 실시\n공사기간: 2020, 12월 ~ 2021 2월\n공사내용 : 기존 하이패스 차로 폐쇄 후 다차로 하이패스 구축 공사",
                "12월 5일 토요일 : -전국 440만대, 수도권에서 지방으로 40만대, 지방에서 수도권으로 41만대\n12월 6일 일요일 : -전국 400만대, 수도권에서 지방으로 33만대, 지방에서 수도권으로 40만대 "
        );

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setMaintext(listMain.get(i));
            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}