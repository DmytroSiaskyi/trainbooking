package com.trainbooking.dao;

import com.trainbooking.dto.TicketDto;
import com.trainbooking.dto.TicketStatisticDto;
import com.trainbooking.entity.Ticket;

import java.util.Date;
import java.util.List;

public interface TicketDao extends Dao<Ticket>{
    List<Ticket> findList(String start, String end, String type, String date);
    void blockTickets(List<Ticket>ticketList);
    Boolean cancelTickets(String start, String end, String type, String date);
    List<TicketDto> getTodayTickets(Date date);
    List<TicketStatisticDto> getClientsStatistic();
}
