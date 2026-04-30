/**
 *
 */
package Integracion.Pelicula;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Integracion.Utils.Utils;

/**
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author maria
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class DAOPeliculaGeneroImp implements DAOPeliculaGenero {
	
	@Override
	public int vincularPeliculaAGenero(int idPelicula, int idGenero) {
		int res = -1;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO pelicula_genero (id_pelicula, id_genero) VALUES (?,?)");

            ps.setInt(1, idPelicula);
            ps.setInt(2, idGenero);

            res = ps.executeUpdate();

            ps.close();
            con.close();
            
        } catch (Exception e) {
            return res;
        }
        return res;
	}

	@Override
	public int desvincularPeliculaAGenero(int idPelicula, int idGenero) {
		 int res = -1;
	        
	        try {
	            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
	            PreparedStatement ps = con.prepareStatement(
	                "DELETE FROM  pelicula_genero WHERE id_pelicula = ? AND id_genero = ?");	// Borrado fisico

	            ps.setInt(1, idPelicula);
	            ps.setInt(2, idGenero);
	            
	            res = ps.executeUpdate();

	            ps.close();
	            con.close();
	            
	        } catch (SQLException e) {
	            return res;
	        }
	        return res;
	}
	
	public boolean yaVinculado(int idPelicula, int idGenero) {
		boolean res = false;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM  pelicula_genero WHERE id_pelicula = ? AND id_genero = ?");

            ps.setInt(1, idPelicula);
            ps.setInt(2, idGenero);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            	res = true;
            }

            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException e) {
            return res;
        }
        return res;
	}
}