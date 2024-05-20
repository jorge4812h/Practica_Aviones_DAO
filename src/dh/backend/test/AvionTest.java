package dh.backend.test;

import dh.backend.dao.impl.AvionIDao;
import dh.backend.model.Avion;
import dh.backend.service.AvionService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.http.WebSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvionTest {

    public static Logger LOGGER=Logger.getLogger(AvionTest.class);

    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db_avion_charter_1;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    AvionService avionService=new AvionService(new AvionIDao());

    @Test
    @DisplayName("Testo Registro de Avion")
    
    void Test1(){
        Avion avion=new Avion("Pepito","Petito","578295", LocalDate.of(2024,5,17) );

        Avion avionRegistrado=avionService.registrarAvion(avion);

        assertNotNull(avionRegistrado);
    }
    @Test
    @DisplayName("Testeo Busqueda por ID")

    void Test2(){

        Integer id=1;
        Avion avionEncontrado=avionService.buscarPorId(id);

        assertEquals(id,avionEncontrado.getId());
    }

    @Test
    @DisplayName("Busqueda de Todos")

    void Test3(){

        //Creamos 2 aviones extras para conocer el listado
        Avion avion=new Avion("Pepito","Petito","578295", LocalDate.of(2024,5,17) );
        avionService.registrarAvion(avion);
        Avion avion2=new Avion("Lacra","Mundial","987613", LocalDate.of(2024,3,17) );
        avionService.registrarAvion(avion2);

        //Creamos la lista
        List<Avion> aviones = avionService.buscarTodos();

        //Validamos el tamaño de la lista.
        assertEquals(3,aviones.size());

    }


    @Test
    @DisplayName("Eliminar Registro")

    void Test4(){

        //Creamos el avion nuevo
        Avion avion=new Avion("Pepito","Petito","578295", LocalDate.of(2024,5,17) );

        //Lo Registramos
        avionService.registrarAvion(avion);

        //Validamos la cantidad de registros.
        avionService.buscarTodos();

        //Eliminamos le primer registro de prueba.
        avionService.eliminarPorId(1);

        //System.out.println(aviones);


        //Creamos la lista de aviones definitiva.
        List <Avion> aviones  =avionService.buscarTodos();

        assertEquals(1,aviones.size());

    }



}
