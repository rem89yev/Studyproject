package com.brainacad.studyproject.service.impl;

import com.brainacad.studyproject.data.dao.AdDao;
import com.brainacad.studyproject.data.dao.DaoFactory;
import com.brainacad.studyproject.data.domain.Ad;
import com.brainacad.studyproject.service.AdService;

import java.util.Collection;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class AdServiceImpl implements AdService {

    private AdDao adDao = DaoFactory.getDaoFactory().getAdDao();

    @Override
    public Collection<Ad> getAllAds() {
        return adDao.getAll();
    }

    @Override
    public Ad getAdById(int id) {
        return adDao.get(id);
    }

    @Override
    public int addAd(Ad ad) {
        return adDao.add(ad);
    }

    @Override
    public boolean update(Ad ad) {
        return adDao.update(ad);
    }

    @Override
    public boolean delete(int id) {
        return adDao.delete(id);
    }
}
