package Integracion.Pelicula;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Utils.Utils;
import Negocio.Pelicula.TPelicula;

public class DAOPeliculaImp implements DAOPelicula {

    @Override
    public int crear(TPelicula tPelicula) {
        int id = -1;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO pelicula (nombre, precio, stock, id_productora, activo) VALUES (?,?,?,?,1)",
                Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, tPelicula.getNombre());
            ps.setFloat(2, tPelicula.getPrecio());
            ps.setInt(3, tPelicula.getStock());
            ps.setInt(4, tPelicula.getIdProductora());

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
    public List<TPelicula> mostrarPeliculasPorGenero(int idGenero) {
        List<TPelicula> lista = null;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                      "SELECT * "
                    + "FROM Pelicula p "
                    + "JOIN pelicula_genero pg ON p.id = pg.id_pelicula "
                    + "WHERE pg.id_genero = ? AND p.activo = 1");

            ps.setInt(1, idGenero);

            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<TPelicula>();

            while (rs.next()) {
                lista.add(new TPelicula(rs.getInt("id"), rs.getString("nombre"), rs.getInt("stock"),
                        rs.getFloat("precio"), rs.getInt("id_productora"), true));
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
    public List<TPelicula> mostrarPeliculasPorProductora(int idProductora) {
        List<TPelicula> lista = null;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM pelicula WHERE activo = 1 AND id_productora = ?");

            ps.setInt(1, idProductora);

            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<TPelicula>();

            while (rs.next()) {
                lista.add(new TPelicula(rs.getInt("id"), rs.getString("nombre"), rs.getInt("stock"),
                        rs.getFloat("precio"), rs.getInt("id_productora"), true));
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
    public int eliminar(int id) {
        int res = -1;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "UPDATE pelicula SET activo=0 WHERE id=?");

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
    public int modificar(TPelicula tPelicula) {
        int id = -1;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "UPDATE pelicula SET nombre=?, precio=?, stock=?, id_productora=?, activo=1 WHERE id=?");

            ps.setString(1, tPelicula.getNombre());
            ps.setFloat(2, tPelicula.getPrecio());
            ps.setInt(3, tPelicula.getStock());
            ps.setInt(4, tPelicula.getIdProductora());
            ps.setInt(5, tPelicula.getId());

            id = ps.executeUpdate();

            ps.close();
            con.close();
            
        } catch (Exception e) {
            return id;
        }
        return id;
    }

    @Override
    public TPelicula mostrar(int id) {
        TPelicula tPelicula = null;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM pelicula WHERE activo = 1 AND id = ?");

            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tPelicula = new TPelicula(rs.getInt("id"), rs.getString("nombre"), rs.getInt("stock"),
                        rs.getFloat("precio"), rs.getInt("id_productora"), true);
            }

            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            return null;
        }
        return tPelicula;
    }

    @Override
    public List<TPelicula> mostrarTodo() {
        List<TPelicula> lista = null;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM pelicula WHERE activo = 1");

            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<TPelicula>();

            while (rs.next()) {
                lista.add(new TPelicula(rs.getInt("id"), rs.getString("nombre"), rs.getInt("stock"),
                        rs.getFloat("precio"), rs.getInt("id_productora"), true));
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
    public TPelicula mostrarPorNombre(String nombre) {
        TPelicula tPelicula = null;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM pelicula WHERE activo = 1 AND nombre = ?");

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tPelicula = new TPelicula(rs.getInt("id"), rs.getString("nombre"), rs.getInt("stock"),
                        rs.getFloat("precio"), rs.getInt("id_productora"), true);
            }

            rs.close();
            ps.close();
            con.close();
            
        } catch (Exception e) {
            return null;
        }
        return tPelicula;
    }
}