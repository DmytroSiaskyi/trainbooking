package com.trainbooking.services.impl;

import com.trainbooking.dao.PlacesDao;
import com.trainbooking.entity.Places;
import com.trainbooking.services.EntityService;
import com.trainbooking.services.PlacesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("placesService")
public class PlacesServiceImpl implements PlacesService, EntityService<Places>{

    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PlacesDao placesDao;

    @Override
    public Places find(Long id) {
        return placesDao.find(id);
    }

    @Transactional
    public void add(Places entity) {
        placesDao.update(entity);
    }

    @Transactional
    public Places update(Places entity) {
        Places result = placesDao.update(entity);
        return result;
    }

    @Transactional
    public void remove(Long id) {
        Places entity = placesDao.find(id);
        placesDao.remove(entity);
    }

    @Override
    public List<Places> list() {
        return placesDao.list();
    }
}
