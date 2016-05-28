package com.trainbooking.services;

import com.trainbooking.dto.TicketDto;
import com.trainbooking.dto.TicketStatisticDto;
import com.trainbooking.entity.Ticket;

import java.util.Date;
import java.util.List;

public interface TicketService extends EntityService<Ticket>{
    List<Ticket> findTickets(String start, String end, String type, String date);
    Boolean cancelTickets(String start, String end, String type, String date);
    List<TicketDto> getTodayTickets(Date date);
    List<TicketStatisticDto> getClientsStatistic();
}
