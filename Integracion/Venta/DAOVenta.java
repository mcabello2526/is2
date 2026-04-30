/**
 *
 */
package Integracion.Venta;

import java.util.List;

import Negocio.Venta.TVenta;

/**
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author maria
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface DAOVenta {
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tVenta
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int abrir(TVenta tVenta);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param ID
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TVenta mostrar(int id);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param TVenta
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int modificar(TVenta TVenta);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param ID
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int eliminar(int id);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<TVenta> mostrarTodo();

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tVenta
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int cerrar(TVenta tVenta);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idCliente
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<TVenta> mostrarPorCliente(int idCliente);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param idTrabajador
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<TVenta> mostrarPorTrabajador(int idTrabajador);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tVenta
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int hacerDevolucion(TVenta tVenta);
}