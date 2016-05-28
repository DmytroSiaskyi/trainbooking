package com.trainbooking.dao.impl;

import com.trainbooking.dao.PlacesDao;
import com.trainbooking.entity.Places;
import org.springframework.stereotype.Repository;

@Repository
public class PlacesDaoImpl extends DaoImpl<Places> implements PlacesDao{
    public PlacesDaoImpl(){super(Places.class);}
}
