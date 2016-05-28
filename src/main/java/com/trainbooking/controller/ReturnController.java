package com.trainbooking.controller;

import com.trainbooking.entity.Ticket;
import com.trainbooking.services.SalesService;
import com.trainbooking.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ReturnController extends BaseController{
    @Autowired
    private TicketService ticketService;
    @Autowired
    private SalesService salesService;

    @RequestMapping(value = {"/return"}, method = RequestMethod.GET)
     public String home(HttpServletRequest request,
                        HttpServletResponse response,
                        Model model) {
        return "return/index";
    }
    @RequestMapping(value = {"/return"}, method = RequestMethod.POST)
    public String returnTicket(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model) {
        if(!request.getParameter("number").isEmpty()) {
            Long id = Long.parseLong(request.getParameter("number"));
            Ticket ticket = ticketService.find(id);
            if(ticket!=null) {
                if (ticket.getEnabled()) {
                    model.addAttribute("error", "Даний квиток не куплений!");
                } else {
                    model.addAttribute("ticket", ticket);
                    if(request.getParameter("return")!=null){
                        salesService.removeByTicketId(id);
                        return "/return/result";
                    }
                }
            }else{
                model.addAttribute("error", "Даний квиток не існує!");
            }
            model.addAttribute("id",id);
        }
        return "return/index";
    }
}
