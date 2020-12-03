package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MainHolder>implements View.OnClickListener {

    private ArrayList<Road>data = new ArrayList<>();
    private ImageView img;
    MainHolder mainHolder;

    // 생성자
    public MyAdapter(ImageView img, ArrayList<Road>data){
        this.img = img;
        this.data = data;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),"머로 추가할까", Toast.LENGTH_SHORT).show();
    }

    //여기에 아이템의 변수를 만들어주세요
    public static class MainHolder extends RecyclerView.ViewHolder{
        public TextView main_text, main_text2;
        public ImageView img;
        public MainHolder(View view){
            super(view);
            //아이템의 레이아웃에대한 id를 찾아야함.
            this.main_text = view.findViewById(R.id.multi1);
            this.main_text2 = view.findViewById(R.id.multi2);
            this.img = view.findViewById(R.id.imageView2);
        }
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View holderView = inflater.inflate(R.layout.item2, parent, false);
        img = (ImageView)holderView.findViewById(R.id.imageView2);
        mainHolder = new MainHolder(holderView);
        return mainHolder;
    }

    //여기에 내용을 만드세요
    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {
        String temp = data.get(i).getConzoneName();
        String temp2 = data.get(i).getGrade();

        if(temp2.equals("1")){
            temp2 = "소통원할";
        }
        else if(temp2.equals("2")){
            temp2 = "서행";
        }
        else{
            temp2 = "정체";
        }

        if(data.get(i).getGrade().equals("3")){
            img.setImageResource(R.drawable.stop);
        }
        else if(data.get(i).getGrade().equals("2")){
            img.setImageResource(R.drawable.normal);
        }
        else{
            img.setImageResource(R.drawable.fast);
        }

        mainHolder.main_text.setText(temp);
        mainHolder.main_text2.setText(temp2);
        mainHolder.img = img;
    }
    //몇개 반환?
    @Override
    public int getItemCount() {
        return data.size();
    }
}
