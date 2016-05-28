package com.trainbooking.services.impl;

import com.trainbooking.dao.SalesDao;
import com.trainbooking.dto.SalesDto;
import com.trainbooking.dto.SalesStatisticDto;
import com.trainbooking.entity.Sale;
import com.trainbooking.services.EntityService;
import com.trainbooking.services.SalesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("salesService")
public class SalesServiceImpl implements SalesService, EntityService<Sale>{
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SalesDao salesDao;
    @Override
    public Sale find(Long id) {
        return salesDao.find(id);
    }

    @Transactional
    public void add(Sale entity) {
        salesDao.update(entity);
    }

    @Transactional
    public Sale update(Sale entity) {
        Sale result = salesDao.update(entity);
        return result;
    }

    @Transactional
    public void remove(Long id) {
        Sale sale = salesDao.find(id);
        salesDao.remove(sale);
    }

    @Override
    public List<Sale> list() {
        return salesDao.list();
    }

    @Override
    public void removeByTicketId(Long id) {
        salesDao.removeByTicketId(id);
    }

    @Override
    public List<SalesDto> getSoldTicketsList() {
        return salesDao.getSoldTicketsList();
    }

    @Override
    public List<SalesStatisticDto> getSumTickets() {
        return salesDao.getSumTickets();
    }
}
