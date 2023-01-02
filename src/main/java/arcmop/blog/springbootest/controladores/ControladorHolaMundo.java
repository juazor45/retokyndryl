package arcmop.blog.springbootest.controladores;

import java.util.Collections;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.sql.*;

@RestController
@RequestMapping
public class ControladorHolaMundo {

    @RequestMapping(value = "/sumar/{sum01}/{sum02}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Map saludar(@PathVariable("sum01") Integer sum01, @PathVariable("sum02") Integer sum02) {

        //Statement sqlSt;
        //ResultSet result;
        String SQL = "INSERT INTO reto(sumando01, sumando02, resultado)VALUES(?,?,?)";
       
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/retojuazor";
            Connection conn = DriverManager.getConnection(dbURL,"juazor","jhulen");
            PreparedStatement statement = conn.prepareStatement(SQL);
            statement.setInt(1,sum01);
            statement.setInt(2,sum02);
            statement.setInt(3,sum01+sum02);
            statement.executeUpdate();
            
            //sqlSt = dbConnect.createStatement();
            //System.out.println("Coneccion realizada");
            //result = sqlSt.executeQuery(SQL);
          
            conn.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("clase no encontrada,verificar JAR" + ex.getMessage());
        } catch (SQLException ex){
            System.out.println("SQL con errores" + ex.getMessage());
        }


        return Collections.singletonMap("resultado", String.valueOf(sum01 + sum02));
    }



}
