package com.trainbooking.dao;


import com.trainbooking.dto.SalesDto;
import com.trainbooking.dto.SalesStatisticDto;
import com.trainbooking.entity.Sale;

import java.util.List;

public interface SalesDao extends Dao<Sale>{
    void removeByTicketId(Long id);
    List<SalesDto> getSoldTicketsList();
    List<SalesStatisticDto> getSumTickets();
}
