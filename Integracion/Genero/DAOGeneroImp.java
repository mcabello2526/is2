package Integracion.Genero;

import Integracion.Utils.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Negocio.Genero.TGenero;


public class DAOGeneroImp implements DAOGenero {
	
	@Override
	public int crear(TGenero tGenero) {
		int id = -1;
		
		try {
			Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
			PreparedStatement ps = con.prepareStatement("INSERT INTO genero (nombre, descripcion, activo) VALUES (?, ?, 1)", 
					Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, tGenero.getNombre());
			ps.setString(2, tGenero.getDescripcion());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);

            rs.close();
            ps.close();
            con.close();
			
		} catch ( Exception e) {
			return id;
		}
		
		return id;
	}
	
	@Override
	public List<TGenero> mostrarTodo() {
		List<TGenero> lista = null;
		
		try {
			Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
			PreparedStatement ps = con.prepareStatement(
					"SELECT * FROM genero WHERE activo = 1");
			
			ResultSet rs = ps.executeQuery();
			lista = new ArrayList<TGenero>();
			
			while (rs.next()) {
                lista.add(new TGenero(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), true));
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

	public TGenero mostrar(int id) {
		
		TGenero tGenero= null;
		
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM genero WHERE activo = 1 AND id = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	tGenero = new TGenero(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), true);
            }

            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            return null;
        }
        return tGenero;
	}

	@Override
	public int modificar(TGenero tGenero) {
		int id = -1;
		
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "UPDATE genero SET nombre=?, descripcion=?, activo=1 WHERE id=?");

            ps.setString(1, tGenero.getNombre());
            ps.setString(2, tGenero.getDescripcion());
            ps.setInt(3, tGenero.getId());

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
                "UPDATE genero SET activo=0 WHERE id=?");

            ps.setInt(1, id);
            res = ps.executeUpdate();

            ps.close();
            con.close();
            
        } catch (SQLException e) {
            return res;
        }
        return res;
	}

	@Override
	public List<TGenero> mostrarPorPelicula(int idPelicula) {
		List<TGenero> lista = null;
		
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                      "SELECT * "
                    + "FROM Genero g "
                    + "JOIN pelicula_genero pg ON g.id = pg.id_genero "
                    + "WHERE pg.id_pelicula = ? AND g.activo = 1");

            ps.setInt(1, idPelicula);

            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<TGenero>();

            while (rs.next()) {
                lista.add(new TGenero(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), true));
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