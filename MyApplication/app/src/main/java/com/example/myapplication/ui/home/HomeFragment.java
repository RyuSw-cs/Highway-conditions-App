package com.example.myapplication.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.DataRestArea;
import com.example.myapplication.GetDataRestArea;
import com.example.myapplication.LoadDataRestArea;
import com.example.myapplication.R;
import com.example.myapplication.RestInfo;
import com.example.myapplication.RetrofitOnSuccess;
import com.example.myapplication.Retrofit_RestArea;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements RetrofitOnSuccess{
    TMapView tMapView;
    private HomeViewModel homeViewModel;
    LinearLayout linearLayoutTmap;
    Context mContext;
    public LoadDataRestArea result2;
    public List<DataRestArea> result_data2 = new ArrayList<>();
    String key = "8098105639";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rest, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        linearLayoutTmap = (LinearLayout) root.findViewById(R.id.linearLayoutTmap);
        tMapView = new TMapView(mContext);
        tMapView.setSKTMapApiKey("l7xxd7985eb0bdab41d8afcf01ed989732a2");
        linearLayoutTmap.addView(tMapView);
        Load_RestArea_1();
        //지도 크기(줌)
        tMapView.setZoomLevel(13);
        //맵의 Type
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        //언어 설정
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        //setGps();
        tMapView.setCompassMode(false);
        tMapView.setTrackingMode(false);
        tMapView.setSightVisible(true);
        tMapView.setCompassMode(false);
        tMapView.setIconVisibility(true);
        setGps();
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //현위치로 좌표 설정
                tMapView.setLocationPoint(longitude,latitude);
                tMapView.setCenterPoint(longitude, latitude);
            }
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    };
    //내위치 찾아가기
    public void setGps() {
        final LocationManager lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자(실내에선 NETWORK_PROVIDER 권장)
                1000, // 통지사이의 최소 시간간격 (miliSecond)
                1, // 통지사이의 최소 변경거리 (m)
                mLocationListener);
    }

    public void set_marker(){
        //마커 이미지
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.shimpyo);
        ArrayList<TMapPoint> alTMapPoint = new ArrayList<TMapPoint>();

        for(int i = 0; i<result_data2.size(); i++){
            TMapMarkerItem markerItem1= new TMapMarkerItem();
            //맵포인트 리스트에서 값을 넣어주고~
            if(result_data2.get(i).getyValue() == null && result_data2.get(i).getxValue() == null){
                alTMapPoint.add(new TMapPoint(0.0, 0.0)); // null이면 default값 삽입
            }
            //api 수정 요망!
            else if(result_data2.get(i).getUnitName().equals("탄천휴게소(순천)")||result_data2.get(i).getUnitName().equals("외동휴게소(울산)")||result_data2.get(i).getUnitName().equals("외동휴게소(포항)")){
                alTMapPoint.add(new TMapPoint(0.0, 0.0)); // null이면 default값 삽입
            }
            else {
                String temp = result_data2.get(i).getUnitName();
                double temp1 = Double.parseDouble(result_data2.get(i).getxValue());
                double temp2 = Double.parseDouble(result_data2.get(i).getyValue());
                alTMapPoint.add(new TMapPoint(temp2, temp1)); // 휴게소 위치 정보 등록
            }
            //마커를 설정하고~
            markerItem1.setIcon(bitmap);
            markerItem1.setTMapPoint(alTMapPoint.get(i));
            markerItem1.setName(result_data2.get(i).getUnitName());
            markerItem1.setCanShowCallout(true);
            //풍선뷰
            markerItem1.setCalloutTitle(result_data2.get(i).getUnitName());
            //click이미지 대충 하나해놔..
            markerItem1.setCalloutRightButtonImage(bitmap2);
            tMapView.addMarkerItem("markerItem"+Integer.toString(i),markerItem1);
        }
        tMapView.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
            @Override
            public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {
                //인텐트 주고 휴게소 레이아웃을 가져오자!
                Intent intent = new Intent(mContext, RestInfo.class);
                intent.putExtra("name",tMapMarkerItem.getCalloutTitle());
                startActivity(intent);
            }
        });
    }
    public void Load_RestArea_1(){
        /*레트로핏을 이용하여 데이터 가져오기*/
        //정보를 가지는 리스트를 초기화!
        GetDataRestArea service1 = Retrofit_RestArea.getInstance().create(GetDataRestArea.class);
        Call<LoadDataRestArea>call = service1.getInstanceRestArea(key, "json","99","1");
        //레트로핏 비동기 실행
        call.enqueue(new Callback<LoadDataRestArea>() {
            @Override
            public void onResponse(Call<LoadDataRestArea> call, Response<LoadDataRestArea> response) {
                if(response.isSuccessful()){
                    result2 = (LoadDataRestArea)response.body();
                    if(result2.getList().size() != 0){
                        for (int i = 0; i < result2.getList().size(); i++) {
                            result_data2.add(result2.getList().get(i));
                        }
                    }
                    Log.d("데이터 성공 첫번째",Integer.toString(result_data2.size()));
                    onSuccess(result_data2);
                }
                else{
                    Log.v("결과는 : ","실패");
                }
            }

            @Override
            public void onFailure(Call<LoadDataRestArea> call, Throwable t) {
                Log.d("결과는 : ","아에 연결도 안돼!");
            }
        });
    }
    public void Load_RestArea_2(List<DataRestArea> result_data2){
        /*레트로핏을 이용하여 데이터 가져오기*/
        //정보를 가지는 리스트를 초기화!
        GetDataRestArea service1 = Retrofit_RestArea.getInstance().create(GetDataRestArea.class);
        Call<LoadDataRestArea>call = service1.getInstanceRestArea(key, "json","99","2");
        //레트로핏 비동기 실행
        call.enqueue(new Callback<LoadDataRestArea>() {
            @Override
            public void onResponse(Call<LoadDataRestArea> call, Response<LoadDataRestArea> response) {
                if(response.isSuccessful()){
                    result2 = (LoadDataRestArea)response.body();
                    if(result2.getList().size() != 0){
                        for (int i = 0; i < result2.getList().size(); i++) {
                            result_data2.add(result2.getList().get(i));
                        }
                    }
                    Log.d("데이터 정상 확인2",Integer.toString(result_data2.size()));
                    onSuccess2(result_data2);
                }
                else{
                    Log.v("결과는 : ","실패");
                }
            }

            @Override
            public void onFailure(Call<LoadDataRestArea> call, Throwable t) {
                Log.d("결과는 : ","아에 연결도 안돼!");
            }
        });
    }
    public void Load_RestArea_3(List<DataRestArea> result_data2){
        /*레트로핏을 이용하여 데이터 가져오기*/
        //정보를 가지는 리스트를 초기화!
        GetDataRestArea service1 = Retrofit_RestArea.getInstance().create(GetDataRestArea.class);
        Call<LoadDataRestArea> call = service1.getInstanceRestArea(key, "json","99","3");
        //레트로핏 비동기 실행
        call.enqueue(new Callback<LoadDataRestArea>() {
            @Override
            public void onResponse(Call<LoadDataRestArea> call, Response<LoadDataRestArea> response) {
                if(response.isSuccessful()){
                    result2 = (LoadDataRestArea)response.body();
                    if(result2.getList().size() != 0){
                        for (int i = 0; i < result2.getList().size(); i++) {
                            result_data2.add(result2.getList().get(i));
                        }
                    }
                    Log.d("데이터 정상 확인3",Integer.toString(result_data2.size()));
                    onSuccess3(result_data2);
                }
                else{
                    Log.v("결과는 : ","실패");
                }
            }
            @Override
            public void onFailure(Call<LoadDataRestArea> call, Throwable t) {
                Log.d("결과는 : ","아에 연결도 안돼!");
            }
        });
    }
    public void onSuccess(List<DataRestArea> result_data2) {
        Load_RestArea_2(result_data2);
    }

    public void onSuccess2(List<DataRestArea> result_data2) {
        Load_RestArea_3(result_data2);
    }

    public void onSuccess3(List<DataRestArea> result_data2) {
        set_marker();
    }
}