package com.brainacad.studyproject.data.dao.impl;

import com.brainacad.studyproject.data.core.StubDataHolder;
import com.brainacad.studyproject.data.dao.AdDao;
import com.brainacad.studyproject.data.domain.Ad;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class StubAdDao implements AdDao {

    @Override
    public Ad getAddByShort(String shortDescript) {
        Collection<Ad> ads = StubDataHolder.getAds();

        for (Ad ad : ads) {
            if (ad.getShortDescription().equals(shortDescript)) {
                return ad;
            }
        }
        return null;
    }

    @Override
    public Ad get(int id) {
        Collection<Ad> ads = StubDataHolder.getAds();
        for (Ad ad : ads) {
            if (ad.getAdId() == id) {
                return ad;
            }
        }
        return null;
    }

    @Override
    public int add(Ad entity) {
        return StubDataHolder.addAd(entity);
    }

    @Override
    public boolean delete(int id) {
        Collection<Ad> ads = StubDataHolder.getAds();
        Iterator<Ad> iterator = ads.iterator();
        while (iterator.hasNext()) {
            Ad ad = iterator.next();
            if (ad.getAdId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(Ad entity) {
        Collection<Ad> ads = StubDataHolder.getAds();
        for (Ad ad : ads) {
            if (ad.getAdId() == entity.getAdId()) {
                ad.setShortDescription(entity.getShortDescription());
                ad.setFullDescription(entity.getFullDescription());
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<Ad> getAll() {
        return StubDataHolder.getAds();
    }

}
