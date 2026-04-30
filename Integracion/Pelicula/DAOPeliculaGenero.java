/**
 *
 */
package Integracion.Pelicula;

/**
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author maria
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public interface DAOPeliculaGenero {
	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param IDPelicula
	* @param IDGenero
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int vincularPeliculaAGenero(int idPelicula, int idGenero);

	/**
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param IDPelicula
	* @param IDGenero
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public int desvincularPeliculaAGenero(int idPelicula, int idGenero);
	
	
	
	
	
	public boolean yaVinculado(int idPelicula, int idGenero);
}