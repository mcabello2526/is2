/**
 *
 */
package Integracion.Nacionalidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Utils.Utils;
import Negocio.Nacionalidad.TNacionalidad;

/**
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author maria
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class DAONacionalidadImp implements DAONacionalidad {
	@Override
	public int crear(TNacionalidad tNacionalidad) {
		int id = -1;
		
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO nacionalidad (nombre, continente, activo) VALUES (?,?,1)",
                Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, tNacionalidad.getNombre());
            ps.setString(2, tNacionalidad.getContinente());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);

            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            return id;
        }
        return id;
	}

	@Override
	public List<TNacionalidad> mostrarTodo() {
		List<TNacionalidad> lista = null;
		
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM nacionalidad WHERE activo = 1");

            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<TNacionalidad>();

            while (rs.next()) {
                lista.add(new TNacionalidad(rs.getInt("id"), rs.getString("nombre"), rs.getString("continente"), true));
            }

            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            return null;
        }
        return lista;
	}

	@Override
	public TNacionalidad mostrar(int id) {
		TNacionalidad tNacionalidad = null;
		
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM nacionalidad WHERE activo = 1 AND id = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	tNacionalidad = new TNacionalidad(rs.getInt("id"), rs.getString("nombre"), rs.getString("continente"), true);
            }

            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            return null;
        }
        return tNacionalidad;
	}

	@Override
	public int modificar(TNacionalidad tNacionalidad) {
		int id = -1;
		
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "UPDATE nacionalidad SET nombre=?, continente=?, activo=1 WHERE id=?");

            ps.setString(1, tNacionalidad.getNombre());
            ps.setString(2, tNacionalidad.getContinente());
            ps.setInt(3, tNacionalidad.getId());

            id = ps.executeUpdate();

            ps.close();
            con.close();
            
        } catch (Exception e) {
            return id;
        }
        return id;
	}

	@Override
	public int eliminar(int id) {
		int res = -1;
		
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "UPDATE nacionalidad SET activo=0 WHERE id=?");

            ps.setInt(1, id);
            res = ps.executeUpdate();

            ps.close();
            con.close();
            
        } catch (SQLException e) {
            return res;
        }
        return res;
	}

}