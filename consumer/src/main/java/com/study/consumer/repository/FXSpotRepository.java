package com.study.consumer.repository;

import com.study.consumer.entity.FXSpot;

import java.util.List;

public interface FXSpotRepository {
    void addFxSpot(FXSpot fxSpot);
    List<FXSpot> getFxSpots(String currency1, String currency2);
}
