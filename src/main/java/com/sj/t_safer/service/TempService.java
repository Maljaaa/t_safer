package com.sj.t_safer.service;

import com.sj.t_safer.dto.Bubble2Result;
import com.sj.t_safer.dto.Pie2Result;
import com.sj.t_safer.dto.TempDashResult;
import com.sj.t_safer.dto.TempResult;
import com.sj.t_safer.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TempService {

    private final ProcessMetalRepository processMetalRepository;
    private final DataTempRepository dataTempRepository;
    private final InspectInRepository inspectInRepository;
    private final InspectOutRepository inspectOutRepository;

    // temp 표 제공
    public List<TempDashResult> getTempResultsByDateRange(String startDate, String endDate) {
        System.out.println("service getTempResultsByDateRange");
        List<Object[]> queryResults = dataTempRepository.findTempResultsByDateRange(startDate, endDate);

        return queryResults.stream()
                .map(result -> new TempDashResult(
                        (String) result[0],
                        (String) result[1],
                        (String) result[2],
                        (String) result[3]
                ))
                .collect(Collectors.toList());
    }

    // temp 결과 제공
    public TempResult getTempResult(String startDate, String endDate) {
        System.out.println("service getTempResult");
        String title = processMetalRepository.getTitle12();
        Long inCount = inspectInRepository.getCount(startDate, endDate);
        Long outCount = inspectOutRepository.getCount(startDate, endDate);
        TempResult tempResult = new TempResult(title, inCount, outCount);

        return tempResult;
    }

    // [temp] 적합 부적합 pie2 chart 제공
    public List<Pie2Result> getPie2Results(String startDate, String endDate) {
        System.out.println("service getPie2Results");
        List<Object[]> resultsForPie = dataTempRepository.findResultsForPie(startDate, endDate);

        return resultsForPie.stream()
                .map(result -> new Pie2Result(
                        Long.parseLong(result[0].toString()),
                        Long.parseLong(result[1].toString())
                ))
                .collect(Collectors.toList());
    }

    // [temp] bubble chart 제공

    public List<Bubble2Result> getBubble2Results(String startDate, String endDate) {
        System.out.println("service getBubble2Results");
        List<Object[]> resultsForBubble2 = dataTempRepository.findResultsForBubble(startDate, endDate);


        return resultsForBubble2.stream()
                .map(result -> {
                    String title = result[0].toString();
                    BigInteger date = BigInteger
                            .valueOf(Long.valueOf(String.valueOf(result[1])));
                    Float y = Float.parseFloat(result[2].toString());
                    String color = "";

                    if ("적합".equals(title)) {
                        color = "#477995";
                    } else if ("부적합".equals(title)) {
                        color = "#D73937";
                    }

                    return new Bubble2Result(title, date, y, color);
                })
                .collect(Collectors.toList());
    }
}
