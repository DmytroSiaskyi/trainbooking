package com.trainbooking.services;

import com.trainbooking.dto.SalesDto;
import com.trainbooking.dto.SalesStatisticDto;
import com.trainbooking.entity.Sale;

import java.util.List;

public interface SalesService extends EntityService<Sale>{
    void removeByTicketId(Long id);
    List<SalesDto> getSoldTicketsList();
    List<SalesStatisticDto> getSumTickets();
}
