package com.trainbooking.dao.impl;

import com.trainbooking.dao.SalesDao;
import com.trainbooking.dto.SalesDto;
import com.trainbooking.dto.SalesStatisticDto;
import com.trainbooking.entity.Sale;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SalesDaoImpl extends DaoImpl<Sale> implements SalesDao{
    public SalesDaoImpl(){super(Sale.class);}

    @Transactional
    public void removeByTicketId(Long id) {
        entityManager.createNativeQuery("DELETE FROM sales WHERE sales.ticket_id=:ticketID")
                .setParameter("ticketID", id)
                .executeUpdate();
        updateTicket(id);
    }

    @Override
    public List<SalesDto> getSoldTicketsList() {
        List<SalesDto> result = new ArrayList<>();
        try{
            List<Object[]> resultList = entityManager.createNativeQuery("SELECT cashier.last_name, sales.client, sales.ticket_id, ticket.price, sales.sold from (sales INNER JOIN ticket ON sales.ticket_id=ticket.id) INNER JOIN cashier ON sales.casier_id=cashier.id").getResultList();
            for(int i = 0; i < resultList.size(); i++){
                SalesDto current = new SalesDto();
                current.setCashier((String)(resultList.get(i))[0]);
                current.setClient((String) (resultList.get(i))[1]);
                current.setPrice((resultList.get(i))[3].toString());
                current.setDate((resultList.get(i))[4].toString());
                result.add(current);
            }
        }catch (NoResultException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<SalesStatisticDto> getSumTickets() {
        List<SalesStatisticDto> result = new ArrayList<>();
        try{
            List<Object[]> resultList = entityManager.createNativeQuery("SELECT cashier.last_name, sum(ticket.price), count(cashier.last_name) from (sales INNER JOIN cashier ON sales.casier_id=cashier.id) INNER JOIN ticket ON sales.ticket_id=ticket.id GROUP BY cashier.last_name").getResultList();
            for(int i = 0; i < resultList.size(); i++) {
                SalesStatisticDto current = new SalesStatisticDto();
                current.setCashier((String)resultList.get(i)[0]);
                current.setSum((Double) resultList.get(i)[1]);
                current.setNumber(Integer.parseInt(resultList.get(i)[2].toString()));
                result.add(current);
            }
        }catch(NoResultException e){
            e.printStackTrace();
        }
        return result;
    }


    private void updateTicket(Long id){
        entityManager.createNativeQuery("UPDATE ticket SET enable=true WHERE id=:ticketID").setParameter("ticketID",id).executeUpdate();
    }
}
