package com.sj.t_safer.controller;

import com.sj.t_safer.dto.*;
import com.sj.t_safer.service.MetalService;
import com.sj.t_safer.service.TempService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashController {

    private static final Logger logger = LoggerFactory.getLogger(DashController.class);

    private final MetalService metalService;
    private final TempService tempService;

    @GetMapping("/monitor/search")
    public String getDash(Model model, @RequestParam String startDate, @RequestParam String endDate) {
        System.out.println("DashController getDash()");

        CCPResult ccpResult = metalService.getCcpResults(startDate, endDate);
        model.addAttribute("ccpResults", ccpResult);

        TempResult tempResult = tempService.getTempResult(startDate, endDate);
        model.addAttribute("tempResults", tempResult);

        List<MetalDashResult> metalData = metalService.getResultsByDateRange(startDate, endDate);
        logger.info("Success to load dashboard data!!");
        model.addAttribute("metalDash", metalData);

        List<TempDashResult> tempData = tempService.getTempResultsByDateRange(startDate, endDate);
        model.addAttribute("tempDash", tempData);

        List<BubbleResult> bubbleResults = metalService.getBubbleResults(startDate, endDate);
        model.addAttribute("bubbleResults", bubbleResults);

        List<Bubble2Result> bubble2Results = tempService.getBubble2Results(startDate, endDate);
        model.addAttribute("bubble2Results", bubble2Results);

        List<PieResult> pieResults = metalService.getPieResults(startDate, endDate);
        model.addAttribute("pieResults", pieResults);

        List<Pie2Result> pie2Results = tempService.getPie2Results(startDate, endDate);
        model.addAttribute("pie2Results", pie2Results);


        model.addAttribute("start", startDate);
        model.addAttribute("end", endDate);

        return "monitor";
    }
}
