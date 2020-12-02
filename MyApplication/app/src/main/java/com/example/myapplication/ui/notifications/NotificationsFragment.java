package com.example.myapplication.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.NoticeActivity;
import com.example.myapplication.R;
import com.example.myapplication.popupcall;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    TextView txtResult;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mypage, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        final Button b1 = root.findViewById(R.id.button9);
        final Button b2 = root.findViewById(R.id.button10);
        final Button b3 = root.findViewById(R.id.button11);
        final Button b4 = root.findViewById(R.id.button12);
        final Button b5 = root.findViewById(R.id.button13);
        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.button9 :
                        Intent intent = new Intent(getActivity(), popupcall.class);
                        intent.putExtra("data", "사고 신고");
                        startActivityForResult(intent, 1);

                        break ;
                    case R.id.button10 :
                        Intent intent2 = new Intent(getActivity(), NoticeActivity.class);
                        startActivityForResult(intent2,1001);

                        break ;
                    case R.id.button11 :

                        break ;
                    case R.id.button12 :

                        break ;
                    case 13 :
                        System.exit(0);
                        break ;

                }
            }
        } ;
        b1.setOnClickListener(onClickListener);
        b2.setOnClickListener(onClickListener);
        b3.setOnClickListener(onClickListener);
        b4.setOnClickListener(onClickListener);
        b5.setOnClickListener(onClickListener);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });
        return root;
    }
}