/**
 *
 */
package Integracion.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Integracion.Utils.Utils;
import Negocio.Cliente.TCliente;
import Negocio.Cliente.TNormal;
import Negocio.Cliente.TVip;

/**
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author maria
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class DAOClienteImp implements DAOCliente {
	@Override
	public int crear(TCliente tCliente) {	
		int id = -1;
        
        try {
            Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO cliente (nombre, apellidos, correo, activo) VALUES (?,?,?,1)",
                Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, tCliente.getNombre());
            ps.setString(2, tCliente.getApellidos());
            ps.setString(3, tCliente.getCorreo());


            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);
            
            rs.close();
            ps.close();
            
            if (tCliente.getEsVip()) {	// Añadimos a subtabla de trabajador (con id generado por la bd)
            	TVip t = (TVip) tCliente;
            	ps = con.prepareStatement(
                        "INSERT INTO cliente_vip (id, descuento) VALUES (?,?)");
            	
            	ps.setInt(1, id);
                ps.setInt(2, t.getDescuento());
            } else {
            	TNormal t = (TNormal) tCliente;
            	ps = con.prepareStatement(
                        "INSERT INTO cliente_normal (id, num_copias) VALUES (?,?)");
            	
            	ps.setInt(1, id);
                ps.setInt(2, t.getNumCopias());
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
	public TCliente mostrar(int id) {
		TCliente tCliente= null;

	    try {
	        Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
	        PreparedStatement ps = con.prepareStatement(
	              "SELECT c.*, cv.descuento, cn.num_copias "
	            + "FROM cliente c "
	            + "LEFT JOIN cliente_vip cv ON c.id = cv.id "
	            + "LEFT JOIN cliente_normal cn ON c.id = cn.id "
	            + "WHERE c.id = ?");

	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	rs.getInt("descuento");
	            if (!rs.wasNull()) {
	                // Es cliente vip
	            	tCliente = new TVip(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"), 
	            			rs.getString("correo"), true, true, rs.getInt("descuento"));
	            } else {
	                // Es cliente normal
	            	tCliente = new TNormal(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"), 
	            			rs.getString("correo"), true, false, rs.getInt("num_copias"));
	            }
	        }

	        rs.close();
	        ps.close();
	        con.close();
	        
	    } catch (Exception e) {
	        return null;
	    }
	    return tCliente;
	}

	@Override
	public int modificar(TCliente tCliente) {
		 int res = -1;

		    try {
		    	// -- Actualizar tabla base
		        Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
		        PreparedStatement ps = con.prepareStatement(
		            "UPDATE cliente SET nombre=?, apellidos=?, correo=?, activo=1  WHERE id=?");

		        ps.setString(1, tCliente.getNombre());
		        ps.setString(2, tCliente.getApellidos());
		        ps.setString(3, tCliente.getCorreo());
		        ps.setInt(4, tCliente.getId());

		        res = ps.executeUpdate();
		        ps.close();

		        // -- Actualizar tabla específica
		        if (tCliente instanceof TVip) {
		        	TVip tv = (TVip) tCliente;
		            PreparedStatement ps2 = con.prepareStatement(
		                "UPDATE cliente_vip SET descuento=? WHERE id=?");

		            ps2.setInt(1, tv.getDescuento());
		            ps2.setInt(2, tv.getId());

		            ps2.executeUpdate();
		            ps2.close();

		        } else if (tCliente instanceof TNormal) {
		        	TNormal tn = (TNormal) tCliente;
		            PreparedStatement ps2 = con.prepareStatement(
		                "UPDATE cliente_normal SET num_copias=? WHERE id=?");

		            ps2.setInt(1, tn.getNumCopias());
		            ps2.setInt(2, tn.getId());

		            ps2.executeUpdate();
		            ps2.close();
		        }

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
                "UPDATE cliente SET activo=0 WHERE id=?");

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
	public List<TCliente> mostrarTodo() {
	    //List<TCliente> lista = null;
		List<TCliente> lista = new ArrayList<>();

	    try {
	        Connection con = DriverManager.getConnection(Utils.PATH, Utils.USERNAME, Utils.PASSWORD);
	        //PreparedStatement ps = con.prepareStatement(
	             
	        String sql ="SELECT c.*, cv.descuento, cn.num_copias "
	            + "FROM cliente c "
	            + "LEFT JOIN cliente_vip cv ON c.id = cv.id "
	            + "LEFT JOIN cliente_normal cn ON c.id = cn.id "
	            + "WHERE c.activo = 1";

	        PreparedStatement ps = con.prepareCall(sql);
	        ResultSet rs = ps.executeQuery();
	       // lista = new ArrayList<TCliente>();

	        while (rs.next()) {
	            rs.getInt("descuento");
	            if (!rs.wasNull()) {
	                lista.add(new TVip(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
	                        rs.getString("correo"), true, true, rs.getInt("descuento")));
	            } else {
	                lista.add(new TNormal(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellidos"),
	                        rs.getString("correo"), true, false, rs.getInt("num_copias")));
	            }
	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {
	    	e.printStackTrace();
	        return null;
	    }
	    return lista;
	}
}