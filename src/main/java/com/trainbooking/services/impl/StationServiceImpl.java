package com.trainbooking.services.impl;

import com.trainbooking.dao.StationDao;
import com.trainbooking.entity.Station;
import com.trainbooking.services.EntityService;
import com.trainbooking.services.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("stationService")
public class StationServiceImpl implements StationService, EntityService<Station> {

    private final transient Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StationDao stationDao;
    @Override
    public Station find(Long id) {
        return stationDao.find(id);
    }

    @Transactional
    public void add(Station entity) {
        stationDao.update(entity);
    }

    @Transactional
    public Station update(Station entity) {
        Station result = stationDao.update(entity);
        return result;
    }

    @Transactional
    public void remove(Long id) {
        Station station = stationDao.find(id);
        stationDao.remove(station);
    }

    @Override
    public List<Station> list() {
        return stationDao.list();
    }
}
