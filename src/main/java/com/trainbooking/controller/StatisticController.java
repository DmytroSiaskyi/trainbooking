package com.trainbooking.controller;

import com.trainbooking.components.LoggedInUser;
import com.trainbooking.dto.SalesDto;
import com.trainbooking.dto.SalesStatisticDto;
import com.trainbooking.dto.TicketDto;
import com.trainbooking.dto.TicketStatisticDto;
import com.trainbooking.services.SalesService;
import com.trainbooking.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class StatisticController extends BaseController{

    @Autowired
    private SalesService salesService;
    @Autowired
    private TicketService ticketService;
    @RequestMapping(value = {"/statistic"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model, LoggedInUser user) {
        List<SalesDto> list1 = salesService.getSoldTicketsList();
        List<SalesStatisticDto> list3 = salesService.getSumTickets();
        List<TicketDto> list2 = ticketService.getTodayTickets(new Date());
        List<TicketStatisticDto> list4 =ticketService.getClientsStatistic();
        model.addAttribute("list1", list1);
        model.addAttribute("list2", list2);
        model.addAttribute("list3", list3);
        model.addAttribute("list4", list4);
        return "statistic/index";
    }
}
