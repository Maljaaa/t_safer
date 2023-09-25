package com.sj.t_safer.service;

import com.sj.t_safer.dto.*;
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
public class MetalService {

    private final DataMetalRepository dataMetalRepository;
    private final InspectTestRepository inspectTestRepository;
    private final InspectNgRepository inspectNgRepository;
    private final ProcessMetalRepository processMetalRepository;




    // metal 표 제공
    public List<MetalDashResult> getResultsByDateRange(String startDate, String endDate) {
        System.out.println("service getResultsBydateRange");
        List<Object[]> queryResults = dataMetalRepository.findResultsByDateRange(startDate, endDate);

        return queryResults.stream()
                .map(result -> new MetalDashResult(
                        (String) result[0],
                        (String) result[1],
                        (String) result[2],
                        (String) result[3],
                        (String) result[4],
                        (String) result[5]
                ))
                .collect(Collectors.toList());
    }


    // ccp 결과 제공
    public CCPResult getCcpResults(String startDate, String endDate) {
        System.out.println("service getCcpResults");
        String title = processMetalRepository.getTitle11();
        Long testCount = inspectTestRepository.getCount(startDate, endDate);
        Long ngCount = inspectNgRepository.getCount(startDate, endDate);
        CCPResult ccpResult = new CCPResult(title, testCount, ngCount);

        return ccpResult;
    }


    // [metal] 적합 부적합 pie chart 제공
    public List<PieResult> getPieResults(String startDate, String endDate) {
        System.out.println("service getPieResults");
        List<Object[]> resultsForPie = dataMetalRepository.findResultsForPie(startDate, endDate);

        return resultsForPie.stream()
                .map(result -> new PieResult(
                        Long.parseLong(result[0].toString()),
                        Long.parseLong(result[1].toString())
                ))
                .collect(Collectors.toList());
    }

    // [metal] bubble chart 제공

    public List<BubbleResult> getBubbleResults(String startDate, String endDate) {
        System.out.println("service getBubbleResults");
        List<Object[]> resultsForBubble = dataMetalRepository.findResultsForBubble(startDate, endDate);


        return resultsForBubble.stream()
                .map(result -> {
                    String title = result[0].toString();
                    BigInteger date = BigInteger
                            .valueOf(Long.valueOf(String.valueOf(result[1])));
                    Float y = Float.valueOf(result[2].toString());
                    String color = "";

                    if ("적합".equals(title)) {
                        color = "#477995";
                    } else if ("부적합".equals(title)) {
                        color = "#D73937";
                    }

                    return new BubbleResult(title, date, y, color);
                })
                .collect(Collectors.toList());
    }

}
