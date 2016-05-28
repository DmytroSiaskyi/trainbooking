package com.trainbooking.dao.impl;

import com.trainbooking.dao.CarriageDao;
import com.trainbooking.entity.Carriage;
import org.springframework.stereotype.Repository;

@Repository
public class CarriageDaoImpl extends DaoImpl<Carriage> implements CarriageDao{
    public CarriageDaoImpl(){super(Carriage.class);}
}
