package dh.backend.dao.impl;

import dh.backend.dao.IDao;
import dh.backend.db.H2Connection;
import dh.backend.model.Avion;
import org.apache.log4j.Logger;

import javax.accessibility.AccessibleValue;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvionIDao implements IDao<Avion> {

    public static Logger LOGGER=Logger.getLogger(AvionIDao.class);

    public static String SQL_INSERT_AVION="INSERT INTO FLOTA VALUES(DEFAULT,?,?,?,?)";
    public static String SQL_SELECT_ID="SELECT * FROM FLOTA WHERE ID=?";
    public static String SQL_DROP_ID="DELETE FROM FLOTA WHERE ID=?";
    public static String SQL_SELECT_ALL="SELECT * FROM FLOTA";

    @Override
    public Avion registrar(Avion avion) {
        Connection connection=null;
        Avion avionPersistido=null;
        try{

            connection= H2Connection.getConnection();

            PreparedStatement preparedStatement= connection.prepareStatement(SQL_INSERT_AVION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, avion.getNombre());
            preparedStatement.setString(2, avion.getModelo());
            preparedStatement.setString(3, avion.getMatricula());
            preparedStatement.setDate(4, Date.valueOf(avion.getFechaEntrada()));

            preparedStatement.executeUpdate();

            ResultSet resultSet= preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                Integer id=resultSet.getInt(1);

                avionPersistido=new Avion(id,avion.getNombre(), avion.getModelo(), avion.getMatricula(),avion.getFechaEntrada());

            }

            LOGGER.info("Avion Registrado: "+avionPersistido);

        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }

        return avionPersistido;
    }

    @Override
    public Avion buscarPorId(Integer id) {
        Connection connection=null;
        Avion avionPersistido=null;
        try{

            connection= H2Connection.getConnection();

            PreparedStatement preparedStatement= connection.prepareStatement(SQL_SELECT_ID);
            preparedStatement.setInt(1,id);

            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()){
                Integer idDevuelto=resultSet.getInt(1);
                String nombre=resultSet.getString(2);
                String modelo=resultSet.getString(3);
                String matricula=resultSet.getString(4);
                LocalDate fechaEntrada= resultSet.getDate(5).toLocalDate();

                avionPersistido=new Avion(idDevuelto,nombre,modelo,matricula,fechaEntrada);

            }

            LOGGER.info("Avion Registrado: "+avionPersistido);

        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }

        return avionPersistido;
    }

    @Override
    public void eliminarPorId(Integer id) {
        Connection connection=null;

        try{
            connection=H2Connection.getConnection();

            PreparedStatement preparedStatement= connection.prepareStatement(SQL_DROP_ID);
            preparedStatement.setInt(1,id);

            preparedStatement.execute();

            ResultSet resultSet= preparedStatement.getResultSet();

            LOGGER.info("Registro eliminado");

        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Override
    public List<Avion> buscarTodos() {

        List<Avion> aviones=new ArrayList<>();
        Connection connection=null;
        Avion avionRegistrado=null;

        try{

            connection=H2Connection.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(SQL_SELECT_ALL);


            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                Integer idDevuelto=resultSet.getInt(1);
                String nombre=resultSet.getString(2);
                String modelo=resultSet.getString(3);
                String matricula=resultSet.getString(4);
                LocalDate fechaEntrada= resultSet.getDate(5).toLocalDate();

                avionRegistrado=new Avion(idDevuelto,nombre,modelo,matricula,fechaEntrada);
                aviones.add(avionRegistrado);
            }

            LOGGER.info("Aviones Registrados:"+aviones);

        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }

        return aviones;
    }
}
