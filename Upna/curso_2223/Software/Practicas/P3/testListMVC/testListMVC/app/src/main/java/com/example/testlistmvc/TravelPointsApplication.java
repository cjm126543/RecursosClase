package com.example.testlistmvc;

import android.app.Application;

import com.example.testlistmvc.Modelos.InterestPoint;

import java.util.ArrayList;
import java.util.List;

public class
TravelPointsApplication extends Application {


    private List<InterestPoint> pointList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        initializeList();
    }

   public  List<InterestPoint> getPoints()
    {
        return pointList;

    }
    public void initializeList() {

        InterestPoint aInterestPoint;
        Double latitud = 43.34343;
        Double longitud = 2.34343;

        for(int i=0; i<5; i++){
            aInterestPoint = new InterestPoint();
            aInterestPoint.setNombre("mismo nombre");
            aInterestPoint.setLatitud(latitud+i);
            aInterestPoint.setLongitud(longitud+i);
            pointList.add(i,aInterestPoint);

        }
    }




}
