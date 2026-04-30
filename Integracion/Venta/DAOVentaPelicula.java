/**
 *
 */
package Integracion.Venta;

import Negocio.Venta.TVentaPelicula;

/**
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author maria
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface DAOVentaPelicula {
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tVentaPelicula
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int anadirPeliculaACarrito(TVentaPelicula tVentaPelicula);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param IDVenta
	* @param IDPelicula
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int eliminarPeliculaDeCarrito(int idVenta, int idPelicula);

	public boolean estaEnVentaAbierta(int idPelicula);

}