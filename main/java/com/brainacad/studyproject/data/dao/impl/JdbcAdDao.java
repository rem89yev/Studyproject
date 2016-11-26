package com.brainacad.studyproject.data.dao.impl;

import com.brainacad.studyproject.data.core.ConnectionManager;
import com.brainacad.studyproject.data.dao.AdDao;
import com.brainacad.studyproject.data.domain.Ad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import static com.brainacad.studyproject.data.domain.AdType.*;

/**
 * Created by Yevhen-PC on 26.11.2016.
 */
public class JdbcAdDao implements AdDao {

    public static final String AD_ID = "ad_id";
    public static final String SHORT_DESCRIPTION = "short_description";
    public static final String FULL_DESCRIPTION = "full_description";
    public static final String AD_TYPE = "ad_type";

    public static final String USER_ID_AD_GOT = "user_id_ad_got";
    public static final String SELECT_FROM_ADS_BY_ID = "SELECT * FROM ads WHERE " + AD_ID + " = ?";
    public static final String INSERT_INTO_ADS = "INSERT INTO ads (" + SHORT_DESCRIPTION + ", " + FULL_DESCRIPTION + "," +
            USER_ID_AD_GOT + ") VALUES (?,?,?)";
    public static final String DELETE_FROM_ADS_WHERE = "DELETE FROM ads WHERE " + AD_ID + " = ?";
    public static final String UPDATE_ADS = "UPDATE ads SET " + SHORT_DESCRIPTION + "=?, " + FULL_DESCRIPTION + "=? WHERE " +
            AD_ID + "=?";
    public static final String SELECT_FROM_USERS = "SELECT * FROM ads";


    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public Ad getAddByShort(String shortDescript) {
        return null;
    }

    @Override
    public Ad get(int id) {
        Connection connection = connectionManager.getConnection();
        Ad ad = null;

        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_ADS_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet != null) {
                ad = new Ad();
                while (resultSet.next()) {
                    ad.setAdId(resultSet.getInt(AD_ID));
                    ad.setShortDescription(resultSet.getString(SHORT_DESCRIPTION));
                    ad.setFullDescription(resultSet.getString(FULL_DESCRIPTION));
                    switch (resultSet.getInt(AD_TYPE)) {
                        case 1:
                            ad.setAdType(SELL);
                            break;
                        case 2:
                            ad.setAdType(BUY);
                            break;
                        case 3:
                            ad.setAdType(CHANGE);
                            break;
                        default:
                            ad.setAdType(SELL);
                            break;
                    }
                    ad.setUserId(resultSet.getInt(USER_ID_AD_GOT));
                }
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ad;
    }

    @Override
    public int add(Ad entity) {
        Connection connection = connectionManager.getConnection();
        int id = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_ADS);
            statement.setString(1, entity.getShortDescription());
            statement.setString(2, entity.getFullDescription());
            statement.setInt(3, entity.getUserId());
            id = statement.executeUpdate();
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            //TODO Log it
        }
        return id;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = connectionManager.getConnection();
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_FROM_ADS_WHERE);
            statement.setInt(1, id);
            int i = statement.executeUpdate();
            if (id == 0) {
                //TODO Log it
            } else {
                result = true;
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            //TODO Log it
        }
        return result;
    }

    @Override
    public boolean update(Ad entity) {
        Connection connection = connectionManager.getConnection();
        boolean result = false;
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_ADS);
            statement.setString(1, entity.getShortDescription());
            statement.setString(2, entity.getFullDescription());
            statement.setInt(3, entity.getAdId());
            int i = statement.executeUpdate();
            if (i == 0) {
                //TODO Exception and log
            } else {
                result = true;
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<Ad> getAll() {
        Connection connection = connectionManager.getConnection();
        Collection<Ad> ads = new HashSet<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USERS);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Ad ad = new Ad();
                    ad.setAdId(resultSet.getInt(AD_ID));
                    ad.setShortDescription(resultSet.getString(SHORT_DESCRIPTION));
                    ad.setFullDescription(resultSet.getString(FULL_DESCRIPTION));
                    switch (resultSet.getInt(AD_TYPE)) {
                        case 1:
                            ad.setAdType(SELL);
                            break;
                        case 2:
                            ad.setAdType(BUY);
                            break;
                        case 3:
                            ad.setAdType(CHANGE);
                            break;
                        default:
                            ad.setAdType(SELL);
                            break;
                    }
                    ad.setUserId(resultSet.getInt(USER_ID_AD_GOT));
                    ads.add(ad);
                }
            }
            connectionManager.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ads;
    }
}
