package com.trainbooking.controller;

import com.trainbooking.components.LoggedInUser;
import com.trainbooking.entity.Sale;
import com.trainbooking.entity.Ticket;
import com.trainbooking.services.SalesService;
import com.trainbooking.services.TicketService;
import com.trainbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class SalesController extends  BaseController{
    @Autowired
    private TicketService ticketService;
    @Autowired
    private SalesService salesService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = {"/sales"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model) {
        return "sales/index";
    }
    @RequestMapping(value = {"/sales"}, method = RequestMethod.POST)
    public String propositionList(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model) {
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String type = request.getParameter("type");
        String date = request.getParameter("date");
        List<Ticket> ticketsList = null;
        String resultText;
        if(!start.isEmpty() && !end.isEmpty() && !type.isEmpty() && !date.isEmpty()){
            resultText = "Результат пошуку";
            ticketsList = ticketService.findTickets(start, end, type, date);
            if(ticketsList.size() == 0){
                resultText = "За даним вами напрямком не знайдено квитків!";
            }
        }else{
            resultText = "Заповніть всі поля для пошуку!";
        }
        model.addAttribute("start", start);
        model.addAttribute("type", type);
        model.addAttribute("end", end);
        model.addAttribute("date", date);
        String flag = request.getParameter("flag");
        if(flag!=null) {
            String result;
            if (ticketService.cancelTickets(start, end, type, date)) {
                result = "Білети розблоковано.";
            } else {
                result = "Помилка операції.";
            }
            model.addAttribute("resultText", result);
            return "sales/index";
        }
        model.addAttribute("resultText", resultText);
        model.addAttribute("ticketList", ticketsList);
        return "sales/index";
    }
    @RequestMapping(value = {"/sales/confirm"}, method = RequestMethod.GET)
      public String confirm(HttpServletRequest request,
                            HttpServletResponse response, @RequestParam(required = false) Long id,
                            Model model) {
        Ticket ticket = ticketService.find(id);
        ticketService.cancelTickets(ticket.getStart().getName(), ticket.getEnd().getName(), ticket.getPlaces().getCarriage().getType(), ticket.getDate().toString());
        model.addAttribute("ticket", ticket);
        return "sales/confirm";
    }
    @RequestMapping(value = {"/sales/sold"}, method = RequestMethod.POST)
     public String sold(HttpServletRequest request,
                        HttpServletResponse response, @RequestParam(required = false) Long id,
                        LoggedInUser user,
                        Model model) {
        Ticket ticket = ticketService.find(id);
        ticket.setEnabled(false);
        ticketService.update(ticket);
        Sale sale = new Sale();
        if(request.getParameter("client").length() < 5){
            model.addAttribute("ticket", ticket);
            model.addAttribute("errorMessage", "Заповніть дані про клієнта!");
            return "sales/confirm";
        }
        sale.setClient(request.getParameter("client"));
        sale.setTicket(ticket);
        sale.setCasier(userService.findByLogin(user.getCurrentUser().getUsername()));
        sale.setSold(new Date());
        salesService.update(sale);
        return "sales/succes";
    }
}
