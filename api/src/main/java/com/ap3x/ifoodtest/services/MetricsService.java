package com.ap3x.ifoodtest.services;

import com.ap3x.ifoodtest.db.AthenaConnection;
import com.ap3x.ifoodtest.dto.OrderByStateData;
import com.ap3x.ifoodtest.dto.TopRestaurantsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MetricsService {

    private static final Logger LOGGER = Logger.getLogger("MetricsService");

    @Autowired
    private AthenaConnection athena;

    public TopRestaurantsData getTopRestaurants(Integer customerId) throws SQLException {
        TopRestaurantsData restaurantsData = new TopRestaurantsData(customerId);

        String query = "SELECT o.customer_id, r.name, count(*) as orders " +
                "FROM orders o " +
                "JOIN restaurants r " +
                "ON o.restaurant_id = r.id " +
                "WHERE o.customer_id = " + customerId + " " +
                "GROUP BY o.customer_id, r.name " +
                "ORDER BY 3 desc " +
                "LIMIT 10";

        LOGGER.info(query);

        Connection conn = athena.getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(query);
        ResultSet rs = stmt.getResultSet();
        while(rs.next()){
            restaurantsData.setCustomerId(rs.getInt("customer_id"));
            restaurantsData.getTopRestaurants().add(rs.getString("name"));
        }

        conn.close();

        if (restaurantsData.getCustomerId() ==  null)
            throw new RuntimeException("Orders not found for this customer...");
        return restaurantsData;
    }

    public List<OrderByStateData> getOrdersByState(String date) throws SQLException {
        List<OrderByStateData> ordersByRegion = new ArrayList<>();

        String query = "SELECT r.state, count(*) as orders " +
                "FROM orders o " +
                "JOIN restaurants r " +
                "ON o.restaurant_id = r.id " +
                "WHERE date(date) = date('" + date + "') " +
                "GROUP BY r.state";

        Connection conn = athena.getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(query);
        ResultSet rs = stmt.getResultSet();
        while(rs.next()){
            OrderByStateData data = new OrderByStateData();
            data.setState(rs.getString("state"));
            data.setOrders(rs.getInt("orders"));
            data.setDate(date);
            ordersByRegion.add(data);
        }

        conn.close();
        if (ordersByRegion.size() == 0)
            throw new RuntimeException("No orders found...");
        return ordersByRegion;
    }

}
