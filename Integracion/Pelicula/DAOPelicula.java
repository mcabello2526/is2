/**
 *
 */
package Integracion.Pelicula;

import java.util.List;

import Negocio.Pelicula.TPelicula;

/**
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author maria
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface DAOPelicula {
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tPelicula
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int crear(TPelicula tPelicula);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param IDGenero
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<TPelicula> mostrarPeliculasPorGenero(int idGenero);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param IDProductora
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<TPelicula> mostrarPeliculasPorProductora(int idProductora);

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
	* @param tPelicula
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int modificar(TPelicula tPelicula);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param ID
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TPelicula mostrar(int id);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<TPelicula> mostrarTodo();

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param nombre
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TPelicula mostrarPorNombre(String nombre);
}