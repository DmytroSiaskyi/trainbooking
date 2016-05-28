package com.trainbooking.services.impl;


import com.trainbooking.dao.TicketDao;
import com.trainbooking.dto.TicketDto;
import com.trainbooking.dto.TicketStatisticDto;
import com.trainbooking.entity.Ticket;
import com.trainbooking.services.EntityService;
import com.trainbooking.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("ticketService")
public class TicketServiceImpl implements TicketService, EntityService<Ticket>{
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TicketDao ticketDao;
    @Override
    public Ticket find(Long id) {
        return ticketDao.find(id);
    }

    @Transactional
    public void add(Ticket entity) {
        ticketDao.update(entity);
    }

    @Transactional
    public Ticket update(Ticket entity) {
        Ticket result = ticketDao.update(entity);
        return result;
    }

    @Transactional
    public void remove(Long id) {
        Ticket ticket = ticketDao.find(id);
        ticketDao.remove(ticket);
    }

    @Override
    public List<Ticket> list() {
        return ticketDao.list();
    }

    @Override
    public List<Ticket> findTickets(String start, String end, String type, String date) {
        List<Ticket> result = ticketDao.findList(start, end, type, date);
        return result;
    }

    @Override
    public Boolean cancelTickets(String start, String end, String type, String date) {
        return ticketDao.cancelTickets(start, end, type, date);
    }

    @Override
    public List<TicketDto> getTodayTickets(Date date) {
        return ticketDao.getTodayTickets(date);
    }

    @Override
    public List<TicketStatisticDto> getClientsStatistic() {
        return ticketDao.getClientsStatistic();
    }
}
