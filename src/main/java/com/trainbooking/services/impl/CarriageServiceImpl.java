package com.trainbooking.services.impl;

import com.trainbooking.dao.CarriageDao;
import com.trainbooking.entity.Carriage;
import com.trainbooking.services.CarriageService;
import com.trainbooking.services.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("carriageService")
public class CarriageServiceImpl implements CarriageService, EntityService<Carriage>{
    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarriageDao carriageDao;
    @Override
    public Carriage find(Long id) {
        return carriageDao.find(id);
    }

    @Transactional
    public void add(Carriage entity) {
        carriageDao.update(entity);
    }

    @Transactional
    public Carriage update(Carriage entity) {
        Carriage result = carriageDao.update(entity);
        return result;
    }

    @Transactional
    public void remove(Long id) {
        Carriage carriage = carriageDao.find(id);
        carriageDao.remove(carriage);
    }

    @Override
    public List<Carriage> list() {
        return carriageDao.list();
    }
}
