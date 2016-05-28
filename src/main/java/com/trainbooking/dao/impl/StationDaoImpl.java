package com.trainbooking.dao.impl;

import com.trainbooking.dao.StationDao;
import com.trainbooking.entity.Station;
import org.springframework.stereotype.Repository;

@Repository
public class StationDaoImpl extends DaoImpl<Station> implements StationDao {
    public StationDaoImpl(){super(Station.class);}
}
