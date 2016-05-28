package com.trainbooking;

import com.trainbooking.entity.*;
import com.trainbooking.models.UserRole;
import com.trainbooking.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplicationBootstrap implements InitializingBean {
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Value("#{properties['db.hbm2ddl.auto']}")
    private String db;
    @Value("#{properties['cloud.login']}")
    private String login;
    @Value("#{properties['cloud.pass']}")
    private String pass;

    @Autowired
    private UserService userService;
    @Autowired
    private StationService stationService;
    @Autowired
    private CarriageService carriageService;
    @Autowired
    private PlacesService placesService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private SalesService salesService;
    @Transactional
    public void afterPropertiesSet() throws Exception {
        if (db.contains("create")){
            initUsers();
//            initStation();
//            initCarriage();
//            initPlaces();
//            initTickets();
//            initSales();
        }
    }
    private void initSales(){
        Sale sale = new Sale();
        sale.setSold(new Date());
        sale.setCasier(userService.findByLogin("admin"));
        sale.setTicket(ticketService.find(new Long(13)));
        sale.setClient("Сяський Дмитро");
        salesService.update(sale);
    }
    private void initTickets(){
        Ticket ticket = new Ticket();
        ticket.setDate(new Date());
        ticket.setEnabled(false);
        ticket.setPrice(124.2);
        ticket.setStart(stationService.find(new Long(2)));
        ticket.setEnd(stationService.find(new Long(3)));
        ticket.setPlaces(placesService.find(new Long(9)));
        ticketService.update(ticket);
        ticket = new Ticket();
        ticket.setDate(new Date());
        ticket.setEnabled(true);
        ticket.setPrice(67.2);
        ticket.setStart(stationService.find(new Long(4)));
        ticket.setEnd(stationService.find(new Long(2)));
        ticket.setPlaces(placesService.find(new Long(12)));
        ticketService.update(ticket);
        ticket = new Ticket();
        ticket.setDate(new Date());
        ticket.setEnabled(true);
        ticket.setPrice(67.2);
        ticket.setStart(stationService.find(new Long(2)));
        ticket.setEnd(stationService.find(new Long(3)));
        ticket.setPlaces(placesService.find(new Long(10)));
        ticketService.update(ticket);
        ticket = new Ticket();
        ticket.setDate(new Date());
        ticket.setEnabled(true);
        ticket.setPrice(67.2);
        ticket.setStart(stationService.find(new Long(2)));
        ticket.setEnd(stationService.find(new Long(3)));
        ticket.setPlaces(placesService.find(new Long(11)));
        ticketService.update(ticket);
    }
    private void initPlaces(){
        Places places = new Places();
        places.setNumber(11);
        places.setTier("нижнє");
        places.setCarriage(carriageService.find(new Long(5)));
        placesService.update(places);
        places = new Places();
        places.setNumber(13);
        places.setTier("нижнє");
        places.setCarriage(carriageService.find(new Long(5)));
        placesService.update(places);
        places = new Places();
        places.setNumber(15);
        places.setTier("нижнє");
        places.setCarriage(carriageService.find(new Long(5)));
        placesService.update(places);
        places = new Places();
        places.setNumber(12);
        places.setTier("верхнє");
        places.setCarriage(carriageService.find(new Long(8)));
        placesService.update(places);
    }
    private void initCarriage(){
        Carriage carriage = new Carriage();
        carriage.setNumber(11);
        carriage.setTrainNumber(141);
        carriage.setType("плацкарт");
        carriageService.update(carriage);
        carriage = new Carriage();
        carriage.setTrainNumber(141);
        carriage.setNumber(12);
        carriage.setType("плацкарт");
        carriageService.update(carriage);
        carriage = new Carriage();
        carriage.setTrainNumber(141);
        carriage.setNumber(14);
        carriage.setType("плацкарт");
        carriageService.update(carriage);
        carriage = new Carriage();
        carriage.setTrainNumber(231);
        carriage.setNumber(10);
        carriage.setType("купе");
        carriageService.update(carriage);
    }
    private void initStation(){
        Station station = new Station();
        station.setName("Київ");
        stationService.add(station);
        station = new Station();
        station.setName("Одеса");
        stationService.add(station);
        station= new Station();
        station.setName("Львів");
        stationService.add(station);
    }
    private void initUsers() {
        List<User> users = userService.list();
        if (users.size()>0){
            return;
        }
        UserRole roleAdmin = new UserRole();
        roleAdmin.setName(Constants.admin);

        UserRole roleUser = new UserRole();
        roleUser.setName(Constants.user);

        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword(new ShaPasswordEncoder().encodePassword("admin", null));
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setRole(roleAdmin.getName());
        userService.add(admin);

        admin = new User();
        admin.setLogin("cashier1");
        admin.setPassword(new ShaPasswordEncoder().encodePassword("admin", null));
        admin.setFirstName("Ivan");
        admin.setLastName("Petrov");
        admin.setRole(roleAdmin.getName());
        userService.add(admin);
    }
}
