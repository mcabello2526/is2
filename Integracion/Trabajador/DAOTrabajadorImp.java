package Integracion.Trabajador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Utils.Utils;

import Negocio.Trabajador.TCompleto;
import Negocio.Trabajador.TParcial;
import Negocio.Trabajador.TTrabajador;

public class DAOTrabajadorImp implements DAOTrabajador {

	@Override
	public int crear(TTrabajador tTrabajador) {
		int id = -1;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO trabajador (nombre, horas_totales, activo) VALUES (?,?,1)",
                Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, tTrabajador.getNombre());
            ps.setFloat(2, tTrabajador.getHorasTotales());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);
            
            rs.close();
            ps.close();
            
            if (tTrabajador.getEsTiempoCompleto()) {	// Añadimos a subtabla de trabajador (con id generado por la bd)
            	TCompleto t = (TCompleto) tTrabajador;
            	ps = con.prepareStatement(
                        "INSERT INTO trabajador_tiempo_completo (id, anio_incorporacion, bonificacion_anual) VALUES (?,?,?)");
            	
            	ps.setInt(1, id);
                ps.setInt(2, t.getAnio());
                ps.setFloat(3, t.getBonificacion());
            } else {
            	TParcial t = (TParcial) tTrabajador;
            	ps = con.prepareStatement(
                        "INSERT INTO trabajador_tiempo_parcial (id, jornada, horas_extras) VALUES (?,?,?)");
            	
            	ps.setInt(1, id);
                ps.setString(2, t.getJornada());
                ps.setFloat(3, t.getHorasExtra());
            }
            
            ps.executeUpdate();

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
	                "UPDATE trabajador SET activo=0 WHERE id=?");

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
	public TTrabajador mostrar(int id) {
	    TTrabajador tTrabajador = null;

	    try {
	        Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
	        PreparedStatement ps = con.prepareStatement(
	              "SELECT t.*, tc.anio_incorporacion, tc.bonificacion_anual, tp.jornada, tp.horas_extras "
	            + "FROM trabajador t "
	            + "LEFT JOIN trabajador_tiempo_completo tc ON t.id = tc.id "
	            + "LEFT JOIN trabajador_tiempo_parcial tp ON t.id = tp.id "
	            + "WHERE t.id = ?");

	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            if (rs.getString("jornada") != null) {
	                // Es tiempo parcial
	                tTrabajador = new TParcial(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("horas_totales"),
	                		true, false, rs.getString("jornada"), rs.getFloat("horas_extras"));
	            } else {
	                // Es tiempo completo
	                tTrabajador = new TCompleto(rs.getInt("id"), rs.getString("nombre"), rs.getInt("horas_totales"),
	                		true, true, rs.getInt("anio_incorporacion"), rs.getFloat("bonificacion_anual"));
	            }
	        }

	        rs.close();
	        ps.close();
	        con.close();
	        
	    } catch (Exception e) {
	        return null;
	    }
	    return tTrabajador;
	}

	public List<TTrabajador> mostrarTodo() {
	    List<TTrabajador> lista = null;

	    try {
	        Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
	        PreparedStatement ps = con.prepareStatement(
	              "SELECT t.*, tc.anio_incorporacion, tc.bonificacion_anual, tp.jornada, tp.horas_extras "
	            + "FROM trabajador t "
	            + "LEFT JOIN trabajador_tiempo_completo tc ON t.id = tc.id "
	            + "LEFT JOIN trabajador_tiempo_parcial tp ON t.id = tp.id");

	        ResultSet rs = ps.executeQuery();
	        lista = new ArrayList<TTrabajador>();

	        while (rs.next()) {
	            if (rs.getString("jornada") != null) {
	                lista.add(new TParcial(rs.getInt("id"), rs.getString("nombre"), rs.getFloat("horas_totales"),
	                        true, false, rs.getString("jornada"), rs.getFloat("horas_extras")));
	            } else {
	                lista.add(new TCompleto(rs.getInt("id"), rs.getString("nombre"), rs.getInt("horas_totales"),
	                        true, true, rs.getInt("anio_incorporacion"), rs.getFloat("bonificacion_anual")));
	            }
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
	public int modificar(TTrabajador tTrabajador) {	// Es un TParcial o TCompleto
	    int res = -1;
	    
	    try {
	    	// -- Actualizar tabla base
	        Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
	        PreparedStatement ps = con.prepareStatement(
	            "UPDATE trabajador SET nombre=?, horas_totales=?, activo=1 WHERE id=?");

	        ps.setString(1, tTrabajador.getNombre());
	        ps.setFloat(2, tTrabajador.getHorasTotales());
	        ps.setInt(3, tTrabajador.getId());

	        res = ps.executeUpdate();
	        ps.close();

	        // -- Actualizar tabla específica
	        if (tTrabajador instanceof TCompleto) {
	            TCompleto tc = (TCompleto) tTrabajador;
	            PreparedStatement ps2 = con.prepareStatement(
	                "UPDATE trabajador_tiempo_completo SET anio_incorporacion=?, bonificacion_anual=? WHERE id=?");

	            ps2.setInt(1, tc.getAnio());
	            ps2.setFloat(2, tc.getBonificacion());
	            ps2.setInt(3, tc.getId());

	            ps2.executeUpdate();
	            ps2.close();

	        } else if (tTrabajador instanceof TParcial) {
	            TParcial tp = (TParcial) tTrabajador;
	            PreparedStatement ps2 = con.prepareStatement(
	                "UPDATE trabajador_tiempo_parcial SET jornada=?, horas_extras=? WHERE id=?");

	            ps2.setString(1, tp.getJornada());
	            ps2.setFloat(2, tp.getHorasExtra());
	            ps2.setInt(3, tp.getId());

	            ps2.executeUpdate();
	            ps2.close();
	        }

	        con.close();
	        
	    } catch (Exception e) {
	        return res;
	    }
	    return res;
	}


}
