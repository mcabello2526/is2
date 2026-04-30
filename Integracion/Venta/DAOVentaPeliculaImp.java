package Integracion.Venta;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Integracion.Utils.Utils;
import Negocio.Venta.TVentaPelicula;

/**
 * DAO para consultas directas sobre la tabla linea_venta.
 *
 * NOTA: La inserción masiva de líneas al cerrar una venta se realiza dentro
 * de la transacción de DAOVentaImp.cerrar() para garantizar atomicidad.
 * Este DAO proporciona operaciones puntuales de consulta y verificación.
 */
public class DAOVentaPeliculaImp implements DAOVentaPelicula {

    /**
     * Añade una única línea a una venta ya existente en la BD.
     * Uso típico: añadir retroactivamente una película a una venta abierta.
     *
     * @return 0 si OK, -1 si error
     */
    @Override
    public int anadirPeliculaACarrito(TVentaPelicula tVentaPelicula) {
        try (Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD)) {

            String sql = "INSERT INTO venta_pelicula (id_venta, id_pelicula, precio_pelicula, num_copias, activo) " +
                         "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, tVentaPelicula.getIdVenta());
            ps.setInt(2, tVentaPelicula.getIdPelicula());
            ps.setFloat(3, tVentaPelicula.getPrecioPelicula());
            ps.setInt(4, tVentaPelicula.getNumCopias());
            ps.setBoolean(5, tVentaPelicula.getActivo());

            int filas = ps.executeUpdate();
            ps.close();
            return (filas > 0) ? 0 : -1;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Elimina una línea de venta dado el ID de la venta y el ID de la película.
     *
     * @return 0 si OK, -1 si error
     */
    @Override
    public int eliminarPeliculaDeCarrito(int idVenta, int idPelicula) {
        try (Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD)) {

            String sql = "DELETE FROM linea_venta WHERE id_venta = ? AND id_pelicula = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            ps.setInt(2, idPelicula);

            int filas = ps.executeUpdate();
            ps.close();
            return (filas > 0) ? 0 : -1;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Comprueba si una película ya tiene una línea de venta activa (venta abierta).
     * Útil para impedir duplicados en el carrito ya persistido.
     *
     * @return true si ya existe una línea activa para esa película
     */
    @Override
    public boolean estaEnVentaAbierta(int idPelicula) {
        try (Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD)) {

            String sql = "SELECT COUNT(*) FROM linea_venta lv " +
                         "JOIN venta v ON lv.id_venta = v.id " +
                         "WHERE lv.id_pelicula = ? AND v.activo = TRUE AND lv.activo = TRUE";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPelicula);
            ResultSet rs = ps.executeQuery();

            boolean resultado = false;
            if (rs.next()) {
                resultado = rs.getInt(1) > 0;
            }
            rs.close();
            ps.close();
            return resultado;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}