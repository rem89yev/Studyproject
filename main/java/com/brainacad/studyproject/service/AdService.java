package com.brainacad.studyproject.service;

import com.brainacad.studyproject.data.domain.Ad;

import java.util.Collection;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public interface AdService {

    Collection<Ad> getAllAds();

    Ad getAdById(int id);

    int addAd(Ad ad);

    boolean update(Ad ad);

    boolean delete(int id);
}
