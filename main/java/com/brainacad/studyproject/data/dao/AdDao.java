package com.brainacad.studyproject.data.dao;

import com.brainacad.studyproject.data.domain.Ad;


/**
 * Created by Yevhen-PC on 25.11.2016.
 */
public interface AdDao extends CrudDao<Ad> {

    Ad getAddByShort(String shortDescript);
}

