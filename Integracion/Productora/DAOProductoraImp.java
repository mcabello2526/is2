package Integracion.Productora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Utils.Utils;
import Negocio.Productora.TProductora;


public class DAOProductoraImp implements DAOProductora {

	@Override
	public int crear(TProductora tProductora) {
		int id = -1;
		
		try {
			Connection con = DriverManager.getConnection( Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO productora (nombre, anio_creacion, id_nacionalidad, activo)" + " VALUES(?,?,?,1)",
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, tProductora.getNombre());
			ps.setInt(2, tProductora.getAnioCreacion());
			ps.setInt(3, tProductora.getIdNacionalidad());

			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
			rs.close();
			ps.close();
			con.close();
			
		}
		catch (Exception e) {
			return id;
		}
		return id;
	}

	@Override
	public List<TProductora> mostrarTodo() {
		List<TProductora> lista = null;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM productora WHERE activo = 1");

            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<TProductora>();

            while (rs.next()) {
                lista.add(new TProductora(rs.getInt("id"), rs.getString("nombre"), rs.getInt("anio_creacion"),
                        true, rs.getInt("id_nacionalidad")));
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
	public TProductora mostrar(int id) {
		TProductora tProductora = null;
	        
	        try {
	            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
	            PreparedStatement ps = con.prepareStatement(
	                "SELECT * FROM productora WHERE activo = 1 AND id = ?");

	            ps.setInt(1, id);
	            
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	            	tProductora = new TProductora(rs.getInt("id"), rs.getString("nombre"), rs.getInt("anio_creacion"),
	                        true, rs.getInt("id_nacionalidad"));
	            }

	            rs.close();
	            ps.close();
	            con.close();
	            
	        } catch (Exception e) {
	            return null;
	        }
	        return tProductora;
	}

	@Override
	public int modificar(TProductora tProductora) {
		int id = -1;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "UPDATE productora SET nombre=?, anio_creacion=?, id_nacionalidad=?, activo=1 WHERE id=?");

            ps.setString(1, tProductora.getNombre());
            ps.setInt(2, tProductora.getAnioCreacion());
            ps.setInt(3, tProductora.getIdNacionalidad());
            ps.setInt(4, tProductora.getId());

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
	                "UPDATE productora SET activo=0 WHERE id=?");

	            ps.setInt(1, id);
	            res = ps.executeUpdate();

	            ps.close();
	            con.close();
	            
	        } catch (Exception e) {
	            return res;
	        }
	        return res;
	}

	@Override
	public List<TProductora> mostrarPorNacionalidad(int idNacionalidad) {
		List<TProductora> lista = null;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM productora WHERE activo = 1 AND id_nacionalidad = ?");

            ps.setInt(1, idNacionalidad);

            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<TProductora>();

            while (rs.next()) {
            	lista.add(new TProductora(rs.getInt("id"), rs.getString("nombre"), rs.getInt("anio_creacion"),
                        true, rs.getInt("id_nacionalidad")));
            }

            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            return null;
        }
        return lista;
	}
}
