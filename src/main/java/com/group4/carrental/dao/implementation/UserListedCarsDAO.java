package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IUserListedCarsDAO;
import com.group4.carrental.model.CarList;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository("UserListedCarsDAO")
public class UserListedCarsDAO implements IUserListedCarsDAO {

    private IDatabaseConnection databaseConnection;

    private static final String GET_LISTEDCAR_QUERY = "select Car.*,Car_Type.*,City_Name.* from Car INNER JOIN User ON Car.owner_id=User.user_id" +
            " INNER JOIN City_Name ON Car.car_city = City_Name.city_id"+
            " INNER JOIN Car_Type ON Car.car_type_id = Car_Type.car_type_id where User.user_id = ? AND car_status_id = 2 ";

    private static final String REMOVE_CAR_QUERY = "DELETE from Car where car_id = ?";
    ArrayList<CarList> carArrayList ;







    @Autowired
    public UserListedCarsDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    @Override
    public ArrayList<CarList> getListedCars(int userId) {

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement=null;
        carArrayList = new ArrayList<>();

        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(GET_LISTEDCAR_QUERY);
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();



            while(resultSet.next()){

                CarList car = new CarList();
                car.setCarId(resultSet.getInt("car_id"));
                car.setCarOwner(resultSet.getInt("owner_id"));
                car.setCarRate(resultSet.getDouble("car_rate"));
                car.setCarTypeName(resultSet.getString("car_type_name"));
                car.setCityName(resultSet.getString("city_name"));
                car.setDescription(resultSet.getString("car_description"));
                car.setCarModel(resultSet.getString("car_model"));
                car.setCityId(resultSet.getInt("car_city"));
                car.setCarTypeId(resultSet.getInt("car_type_id"));

                Blob carImage = resultSet.getBlob("car_image");
                String carImageData = null;
                byte[] imageBytes = carImage.getBytes(1, (int) carImage.length());
                carImageData = Base64.encodeBase64String(imageBytes);
                car.setImageURL(carImageData);



                carArrayList.add(car);

            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement!=null)
                {
                    preparedStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return carArrayList;
    }

    @Override
    public void removeCarById(int carId) {

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement=null;

        try {
            connection = databaseConnection.getDBConnection();
            preparedStatement = connection.prepareStatement(REMOVE_CAR_QUERY);
            preparedStatement.setInt(1,carId);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(preparedStatement!=null)
                {
                    preparedStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public CarList getCarDetailsById(int carId) {

        CarList carList= new CarList();
        for(int i=0; i <carArrayList.size(); i++){
            if(carArrayList.get(i).getCarId() == carId){
                carList = carArrayList.get(i);
            }
        }

        return carList;

    }


}
