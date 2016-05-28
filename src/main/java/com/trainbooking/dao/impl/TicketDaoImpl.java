package com.trainbooking.dao.impl;

import com.trainbooking.dao.TicketDao;
import com.trainbooking.dto.TicketDto;
import com.trainbooking.dto.TicketStatisticDto;
import com.trainbooking.entity.Ticket;
import com.trainbooking.models.DateUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TicketDaoImpl extends DaoImpl<Ticket> implements TicketDao{
    public TicketDaoImpl(){super(Ticket.class);}

    @Transactional
    public List<Ticket> findList(String start, String end, String type, String date){
        List<Ticket> ticketList = null;
        Date prevDate = null;
        Date nextDate = null;
        try{
            prevDate = DateUtil.addDays(date, 0);
            nextDate = DateUtil.addDays(date, 1);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            if(!type.equals("тип")) {
                ticketList = entityManager.createQuery("SELECT a FROM Ticket a WHERE a.start = (SELECT b FROM Station b WHERE b.name =:start) AND a.end = (SELECT c FROM Station c WHERE c.name =:end) AND a.date <:ndate AND a.date >:pdate AND a.places IN (SELECT d FROM Places d WHERE d.carriage IN (SELECT car FROM Carriage car WHERE car.type =:type)) AND a.enabled = true", Ticket.class)
                        .setParameter("start", start)
                        .setParameter("end", end)
                        .setParameter("type", type)
                        .setParameter("pdate", prevDate)
                        .setParameter("ndate", nextDate)
                        .getResultList();
            }else{
                ticketList = entityManager.createQuery("SELECT a FROM Ticket a WHERE a.start = (SELECT b FROM Station b WHERE b.name =:start) AND a.end = (SELECT c FROM Station c WHERE c.name =:end) AND a.date <:ndate AND a.date >:pdate AND a.enabled = true", Ticket.class)
                        .setParameter("start", start)
                        .setParameter("end", end)
                        .setParameter("pdate", prevDate)
                        .setParameter("ndate", nextDate)
                        .getResultList();
            }
        }catch(NoResultException e){
            return null;
        }
        blockTickets(ticketList);
        return ticketList;
    }
    public void blockTickets(List<Ticket> ticketList) {
        Long id;
        for(int i = 0; i < ticketList.size(); i++){
            id = ticketList.get(i).getId();
            entityManager.createNativeQuery("UPDATE ticket set enable = false where ticket.id =:id_ticket").setParameter("id_ticket",id).executeUpdate();
        }
    }

    @Transactional
    public Boolean cancelTickets(String start, String end, String type, String date) {
        Date prevDate = null;
        Date nextDate = null;
        try{
            prevDate = DateUtil.addDays(date, 0);
            nextDate = DateUtil.addDays(date, 1);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if(!type.equals("тип")) {
                entityManager.createNativeQuery("UPDATE ticket set enable = true WHERE ticket.id NOT IN(SELECT ticket_id FROM sales) AND ticket.start_station_id=(SELECT id FROM Station  WHERE Station.name =:start) AND ticket.end_station_id = (SELECT id FROM Station WHERE Station.name =:end) AND ticket.date <:ndate AND ticket.date >:pdate AND ticket.places_id IN(SELECT id FROM Places WHERE Places.carriage_id IN(SELECT id FROM Carriage WHERE Carriage.type =:type))")
                        .setParameter("start", start)
                        .setParameter("end", end)
                        .setParameter("type", type)
                        .setParameter("pdate", prevDate)
                        .setParameter("ndate", nextDate).executeUpdate();
                //entityManager.createNativeQuery("UPDATE ticket set enable = true WHERE ticket.id NOT IN(SELECT ticket_id FROM sales)").executeUpdate();
            }else{
                entityManager.createNativeQuery("UPDATE ticket set enable = true WHERE ticket.id NOT IN(SELECT ticket_id FROM sales) AND ticket.start_station_id=(SELECT id FROM Station  WHERE Station.name =:start) AND ticket.end_station_id = (SELECT id FROM Station WHERE Station.name =:end) AND ticket.date <:ndate AND ticket.date >:pdate")
                        .setParameter("start", start)
                        .setParameter("end", end)
                        .setParameter("pdate", prevDate)
                        .setParameter("ndate", nextDate).executeUpdate();
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TicketDto> getTodayTickets(Date date) {
        List<TicketDto> result= new ArrayList<>();
        try{
            List<Object[]> resultList = entityManager.createNativeQuery("SELECT ticket.id, ticket.price, places.number AS p, carriage.number AS n, a.name as start, b.name as end FROM (((ticket INNER JOIN places ON ticket.places_id=places.id) INNER JOIN carriage ON places.carriage_id=carriage.id) INNER JOIN station a ON ticket.start_station_id=a.id) INNER JOIN station b ON ticket.end_station_id=b.id WHERE ticket.enable = true")
                    .getResultList();
            for(int i = 0; i < resultList.size(); i++){
                TicketDto current = new TicketDto();
                current.setId(Integer.parseInt(resultList.get(i)[0].toString()));
                current.setPrice(Double.parseDouble(resultList.get(i)[1].toString()));
                current.setPosition(Integer.parseInt(resultList.get(i)[2].toString()));
                current.setCarriage(Integer.parseInt(resultList.get(i)[3].toString()));
                current.setStart(resultList.get(i)[4].toString());
                current.setEnd(resultList.get(i)[5].toString());
                result.add(current);
            }
        }catch(NoResultException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<TicketStatisticDto> getClientsStatistic() {
        List<TicketStatisticDto> result = new ArrayList<>();
        try{
            List<Object[]> resultList = entityManager.createNativeQuery("SELECT sales.client, count(sales.client) FROM sales GROUP BY client").getResultList();
            for(int i = 0; i < resultList.size(); i++){
                TicketStatisticDto current = new TicketStatisticDto();
                current.setClient(resultList.get(i)[0].toString());
                current.setCount(Integer.parseInt(resultList.get(i)[1].toString()));
                result.add(current);
            }
        }catch(NoResultException e){
            e.printStackTrace();
        }
        return result;
    }
}
