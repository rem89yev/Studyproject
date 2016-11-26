package com.brainacad.studyproject.data.core;

import com.brainacad.studyproject.data.domain.Ad;
import com.brainacad.studyproject.data.domain.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import static com.brainacad.studyproject.data.domain.AdType.*;
import static com.brainacad.studyproject.data.domain.Role.ADMIN;
import static com.brainacad.studyproject.data.domain.Role.USER;

/**
 * Created by Yevhen-PC on 02.11.2016.
 */
public class StubDataHolder {

    private static Collection<Ad> ads;
    private static Collection<User> users;
    private static boolean created = false;

    public static void createData() {

        if (!created) {
            User admin = new User();
            admin.setId(1);
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole(ADMIN);

            User user1 = new User();
            user1.setId(2);
            user1.setUsername("user1");
            user1.setPassword("user1");
            user1.setRole(USER);

            User user2 = new User();
            user2.setId(3);
            user2.setUsername("user2");
            user2.setPassword("user2");
            user2.setRole(USER);

            users = new HashSet<>();
            users.add(admin);
            users.add(user1);
            users.add(user2);

            Ad ad1 = new Ad();
            ad1.setAdId(1);
            ad1.setUserId(2);
            ad1.setAdType(SELL);
            ad1.setShortDescription("Sell Notebook");
            ad1.setFullDescription("Sell Notebook HP G4 250, HD LED, RAM 2 Gb, SDD 120 Gb");


            Ad ad2 = new Ad();
            ad2.setAdId(2);
            ad2.setUserId(3);
            ad2.setAdType(BUY);
            ad2.setShortDescription("Buy Notebook");
            ad2.setFullDescription("Buy Notebook Asus Z3, HD LED, RAM 3 Gb, SDD 250 Gb");

            Ad ad3 = new Ad();
            ad3.setAdId(3);
            ad3.setUserId(2);
            ad3.setAdType(CHANGE);
            ad3.setShortDescription("Change Position");
            ad3.setFullDescription("Change Position, Delete Position, Next Position");

            ads = new HashSet<>();
            ads.add(ad1);
            ads.add(ad2);
            ads.add(ad3);

            created = true;
        }
    }

    public static int add(User user) {
        if (users.add(user)) {
            int maxId = 0;
            Iterator<User> userIterator = users.iterator();
            while (userIterator.hasNext()) {
                User element = userIterator.next();
                if (element.getId() > maxId) {
                    maxId = element.getId();
                }
            }
            user.setId(maxId + 1);
            user.setRole(USER);
            return user.getId();
        } else {
            return 0;
        }
    }

    public static Collection<User> getUsers() {
        return users;
    }

    public static int addAd(Ad ad) {
        if (ads.add(ad)) {
            int maxId = 0;
            Iterator<Ad> adIterator = ads.iterator();
            while (adIterator.hasNext()) {
                Ad element = adIterator.next();
                if (element.getAdId() > maxId) {
                    maxId = element.getAdId();
                }
            }
            ad.setAdId(maxId + 1);
            return ad.getAdId();
        } else {
            return 0;
        }
    }

    public static Collection<Ad> getAds() {
        return ads;
    }

    public static boolean deleteUser(int id) {
        users = getUsers();
        ads = getAds();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                int userId = id;
                iterator.remove();
                Iterator<Ad> adIterator = ads.iterator();
                while (adIterator.hasNext()) {
                    Ad ad = adIterator.next();
                    if (ad.getUserId() == userId) {
                        adIterator.remove();
                    }
                }
                return true;
            }
        }
        return false;
    }

}
