/**
 *
 */
package Integracion.Productora;

import java.util.List;

import Negocio.Productora.TProductora;


public interface DAOProductora {

	public int crear(TProductora tProductora);
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public List<TProductora> mostrarTodo();

	
	public TProductora mostrar(int id);


	public int modificar(TProductora tProductora);


	public int eliminar(int id);


	public List<TProductora> mostrarPorNacionalidad(int idNacionalidad);
}