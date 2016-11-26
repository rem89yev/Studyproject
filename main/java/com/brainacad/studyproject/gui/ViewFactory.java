package com.brainacad.studyproject.gui;

import com.brainacad.studyproject.gui.view.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yevhen-PC on 08.11.2016.
 */
public class ViewFactory {

    private static ViewFactory instance;
    private Map<View, RefreshableView> holder = new HashMap<>();

    private ViewFactory() {
    }

    public static ViewFactory getInstance() {
        if (instance == null) {
            instance = new ViewFactory();
        }
        return instance;
    }

    public RefreshableView getView(View type) {
        RefreshableView refreshableView;
        switch (type) {
            case LOGIN:
                holder.putIfAbsent(type, new LoginView());
                return holder.get(type);
            case USERS:
                holder.putIfAbsent(type, new UsersView());
                return holder.get(type);
            case EDIT_USER:
                holder.putIfAbsent(type, new EditUserView());
                return holder.get(type);
            case ADD_USER:
                holder.putIfAbsent(type, new AddUserView());
                return holder.get(type);
            case ALL_ADS:
                holder.putIfAbsent(type, new AllAdsView());
                return holder.get(type);
            case FULL_AD_DESCRIPTION:
                holder.putIfAbsent(type, new FullAdDescriptionView());
                return holder.get(type);
            case MY_ADS:
                holder.putIfAbsent(type, new MyAdsView());
                return holder.get(type);
            case FULL_MY_AD_DESCRIPTION:
                holder.putIfAbsent(type, new FullMyAdDescriptionView());
                return holder.get(type);
            case ADD_ADVERTISEMENT:
                holder.putIfAbsent(type, new AddAdvertisementView());
                return holder.get(type);
            default:
                throw new RuntimeException("TODO");
        }
    }
}
