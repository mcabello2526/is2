package Integracion.Venta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Utils.Utils;
import Negocio.Venta.TVenta;
import Negocio.Venta.TVentaPelicula;

public class DAOVentaImp implements DAOVenta {

    @Override
    public int cerrar(TVenta tVenta) {	// Llega TVenta con todo listo menos el id
    	int id = -1;
    	Connection con = null;

    	try {
    		con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
    	    con.setAutoCommit(false);

    	    // -- 1. INSERT en venta
    	    PreparedStatement ps = con.prepareStatement(
    	        "INSERT INTO venta (fecha_venta, precio_total, activo, id_cliente, id_trabajador) VALUES (?, ?, 1, ?, ?)",
    	        Statement.RETURN_GENERATED_KEYS);

    	    ps.setString(1, tVenta.getFechaVenta());
    	    ps.setFloat(2, tVenta.getPrecioTotal());
    	    ps.setInt(3, tVenta.getIdCliente());
    	    ps.setInt(4, tVenta.getIdTrabajador());
    	    ps.executeUpdate();

    	    ResultSet rs = ps.getGeneratedKeys();
    	    if (!rs.next()) {
    	        con.rollback();
    	        return id;
    	    }
    	    id = rs.getInt(1);
    	    rs.close();
    	    ps.close();

    	    // -- 2. Para cada línea del carrito
    	    for (TVentaPelicula linea : tVenta.getLineaCarrito()) {

    	        // 2a. Insertar línea
    	        ps = con.prepareStatement(
    	            "INSERT INTO venta_pelicula (id_venta, id_pelicula, precio_pelicula, num_copias, activo) " +
    	            "VALUES (?, ?, ?, ?, 1)");
    	        ps.setInt(1, id);
    	        ps.setInt(2, linea.getIdPelicula());
    	        ps.setFloat(3, linea.getPrecioPelicula());
    	        ps.setInt(4, linea.getNumCopias());
    	        ps.executeUpdate();
    	        ps.close();

    	        // 2b. Descontar stock
    	        ps = con.prepareStatement(
    	            "UPDATE pelicula SET stock = stock - ? WHERE id = ?");
    	        ps.setInt(1, linea.getNumCopias());
    	        ps.setInt(2, linea.getIdPelicula());
    	        ps.executeUpdate();
    	        ps.close();
    	    }

    	    con.commit();
    	    con.close();

    	} catch (Exception e) {
            try { con.rollback(); } catch (Exception ex) {}
            try { con.close(); } catch (Exception ex) {}
            return id;
        }
        return -1;
    }

    
    @Override
    public int abrir(TVenta tVenta) {
        return 0;	// No se comunica con la bd (toda la gestion es en la GUI)
    }

   
    @Override
    public TVenta mostrar(int id) {
        TVenta tVenta = null;
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT id, fecha_venta, precio_total, activo, id_cliente, id_trabajador " +
                "FROM venta WHERE activo = 1 AND id = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tVenta = new TVenta(
                    rs.getInt("id"),
                    rs.getString("fecha_venta"),  // VARCHAR(20)
                    rs.getFloat("precio_total"),
                    rs.getBoolean("activo"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_trabajador")
                );
            }

            rs.close();
            ps.close();

            if (tVenta != null) {
                List<TVentaPelicula> lineas = obtenerLineasDeVenta(con, id);
                tVenta.setLineaCarrito(lineas);
            }

            con.close();
        } catch (Exception e) {
            return null;
        }
        return tVenta;
    }

    
    @Override
    public List<TVenta> mostrarTodo() {
        List<TVenta> lista = null;
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT id, fecha_venta, precio_total, activo, id_cliente, id_trabajador " +
                "FROM venta WHERE activo = 1");

            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<TVenta>();

            while (rs.next()) {
                lista.add(new TVenta(
                    rs.getInt("id"),
                    rs.getString("fecha_venta"),
                    rs.getFloat("precio_total"),
                    rs.getBoolean("activo"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_trabajador")
                ));
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
    public List<TVenta> mostrarPorCliente(int idCliente) {
        List<TVenta> lista = null;
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT id, fecha_venta, precio_total, activo, id_cliente, id_trabajador " +
                "FROM venta WHERE activo = 1 AND id_cliente = ?");

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<TVenta>();

            while (rs.next()) {
                lista.add(new TVenta(
                    rs.getInt("id"),
                    rs.getString("fecha_venta"),
                    rs.getFloat("precio_total"),
                    rs.getBoolean("activo"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_trabajador")
                ));
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
    public List<TVenta> mostrarPorTrabajador(int idTrabajador) {
        List<TVenta> lista = null;
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "SELECT id, fecha_venta, precio_total, activo, id_cliente, id_trabajador " +
                "FROM venta WHERE activo = 1 AND id_trabajador = ?");

            ps.setInt(1, idTrabajador);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<TVenta>();

            while (rs.next()) {
                lista.add(new TVenta(
                    rs.getInt("id"),
                    rs.getString("fecha_venta"),
                    rs.getFloat("precio_total"),
                    rs.getBoolean("activo"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_trabajador")
                ));
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
    public int modificar(TVenta tVenta) {
        int res = -1;
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "UPDATE venta SET fecha_venta=?, precio_total=?, id_cliente=?, id_trabajador=? WHERE id=?");

            ps.setString(1, tVenta.getFechaVenta());
            ps.setFloat(2, tVenta.getPrecioTotal());
            ps.setInt(3, tVenta.getIdCliente());
            ps.setInt(4, tVenta.getIdTrabajador());
            ps.setInt(5, tVenta.getId());

            res = ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            return res;
        }
        return res;
    }

    
    @Override
    public int eliminar(int id) {
        int res = -1;
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "UPDATE venta SET activo = 0 WHERE id = ?");

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
    public int hacerDevolucion(TVenta tVenta) {	// TODO: revisar cuanto se devuelve
        Connection con = null;
        try {
            con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            con.setAutoCommit(false);

            // 1. Marcar la venta como inactiva
            PreparedStatement psVenta = con.prepareStatement(
                "UPDATE venta SET activo = 0 WHERE id = ?");
            psVenta.setInt(1, tVenta.getId());
            psVenta.executeUpdate();
            psVenta.close();

            // 2. Restaurar el stock de cada película
            List<TVentaPelicula> lineas = obtenerLineasDeVenta(con, tVenta.getId());
            PreparedStatement psStock = con.prepareStatement(
                "UPDATE pelicula SET stock = stock + ? WHERE id = ?");

            for (TVentaPelicula linea : lineas) {
                psStock.setInt(1, linea.getNumCopias());
                psStock.setInt(2, linea.getIdPelicula());
                psStock.executeUpdate();
            }
            psStock.close();

            con.commit();
            return 0;

        } catch (Exception e) {
            if (con != null) {
                try { con.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return -1;
        } finally {
            if (con != null) {
                try { con.close(); } catch (Exception e) { e.printStackTrace(); }
            }
        }
    }

    // Se utiliza en (cerrar, hacerDevolucion), recibe conexion ya abierta
    private List<TVentaPelicula> obtenerLineasDeVenta(Connection con, int idVenta) throws Exception {
        List<TVentaPelicula> lineas = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(
            "SELECT id_venta, id_pelicula, precio_pelicula, num_copias, activo " +
            "FROM venta_pelicula WHERE activo = 1 AND id_venta = ?");  // CORREGIDO: venta_pelicula

        ps.setInt(1, idVenta);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            TVentaPelicula vp = new TVentaPelicula(
                rs.getInt("id_venta"),
                rs.getInt("id_pelicula"),
                rs.getBoolean("activo"),
                rs.getFloat("precio_pelicula"),
                rs.getInt("num_copias")
            );
            lineas.add(vp);
        }

        rs.close();
        ps.close();
        return lineas;
    }
}