package com.group4.carrental.dao.implementation;

import com.group4.carrental.connection.IDatabaseConnection;
import com.group4.carrental.dao.IUpdatePasswordDAO;
import com.group4.carrental.model.Password;
import com.group4.carrental.service.implementation.LoggerInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("UpdatePasswordDAO")
public class UpdatePasswordDAO implements IUpdatePasswordDAO {

    private IDatabaseConnection databaseConnection;
    private LoggerInstance loggerInstance;

    private static final String USER_TABLE = "User";
    private static final String PASSWORD_FIELD = "password";
    private static final String GET_PASSWORD_QUERY = "select password from "+USER_TABLE+" where user_id = ?";
    private static final String UPDATE_PASSWORD_QUERY = "update "+USER_TABLE + " SET password = ? where user_id = ?";


    @Autowired
    public UpdatePasswordDAO(@Qualifier("DatabaseConnection") IDatabaseConnection databaseConnection,
                             LoggerInstance loggerInstance){
        this.databaseConnection = databaseConnection;
        this.loggerInstance = loggerInstance;
    }

    @Override
    public String getUserOldPassword(int userId) {

        String userPassword = null;
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement getPasswordStatement = null;
        try {
            connection = databaseConnection.getDBConnection();
            getPasswordStatement =connection.prepareStatement(GET_PASSWORD_QUERY);
            getPasswordStatement.setInt(1,userId);
            resultSet = getPasswordStatement.executeQuery();

            if(resultSet.next()){
                userPassword = resultSet.getString(PASSWORD_FIELD);
            }

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"Get user Old password DAO Error :"+e.toString());
            e.printStackTrace();
        } finally {
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(getPasswordStatement != null){
                    getPasswordStatement.close();
                }
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                loggerInstance.log(2,"Get user Old password DAO Error :"+e.toString());
                e.printStackTrace();
            }
        }
        loggerInstance.log(0,"Get user Old password DAO : Success");
        return userPassword;
    }

    @Override
    public void updatePassword(int userId,Password password) {

        Connection connection = null;
        try {
            connection = databaseConnection.getDBConnection();
            PreparedStatement updateStattement = connection.prepareStatement(UPDATE_PASSWORD_QUERY);
            updateStattement.setString(1, password.getNewPassword());
            updateStattement.setInt(2,userId);
            updateStattement.executeUpdate();

        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            loggerInstance.log(2,"User update password DAO Error :"+e.toString());
            e.printStackTrace();


        } finally {
            try {
                databaseConnection.closeDBConnection(connection);
            } catch (SQLException e) {
                loggerInstance.log(2,"User update password DAO Error :"+e.toString());
                e.printStackTrace();
            }
        }

        loggerInstance.log(0,"User update password DAO: Success");

    }
}
