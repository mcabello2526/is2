/**
 *
 */
package Integracion.Nacionalidad;

import java.util.List;

import Negocio.Nacionalidad.TNacionalidad;

/**
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author maria
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public interface DAONacionalidad {
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tNacionalidad
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int crear(TNacionalidad tNacionalidad);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<TNacionalidad> mostrarTodo();

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param ID
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TNacionalidad mostrar(int id);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tNacionalidad
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int modificar(TNacionalidad tNacionalidad);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param ID
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int eliminar(int id);

}