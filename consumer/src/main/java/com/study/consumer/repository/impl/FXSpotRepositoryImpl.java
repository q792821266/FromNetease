package com.study.consumer.repository.impl;

import com.study.consumer.entity.FXSpot;
import com.study.consumer.repository.FXSpotRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FXSpotRepositoryImpl implements FXSpotRepository {
    private List<FXSpot> fxSpots = new ArrayList<>();

    @Override
    public void addFxSpot(FXSpot fxSpot) {
        fxSpots.add(fxSpot);
    }

    @Override
    public List<FXSpot> getFxSpots(String currencyFrom, String currencyTo) {
        return fxSpots.stream()
                .filter(spot -> spot.getCurrencyFrom().equals(currencyFrom) && spot.getCurrencyTo().equals(currencyTo))
                .sorted(Comparator.comparing(FXSpot::getObservedAt).reversed()) // 按观察时间降序排序
                .collect(Collectors.toList());
    }

}
