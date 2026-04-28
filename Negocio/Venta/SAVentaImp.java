package Negocio.Venta;

import java.util.ArrayList;
import java.util.List;

import Integracion.Cliente.DAOCliente;
import Integracion.FactoryDAO.FactoryDAO;
import Integracion.Pelicula.DAOPelicula;
import Integracion.Trabajador.DAOTrabajador;
import Integracion.Venta.DAOVenta;
import Negocio.Cliente.TCliente;
import Negocio.Pelicula.TPelicula;
import Negocio.Trabajador.TTrabajador;

/**
 * Implementación de la lógica de negocio del módulo de Ventas.
 *
 * Códigos de retorno negativos usados de forma consistente:
 *  -1  → Parámetros nulos o sintácticamente inválidos
 *  -2  → Carrito vacío / datos insuficientes
 *  -3  → Cliente no encontrado o inactivo
 *  -4  → Trabajador no encontrado o inactivo
 *  -5  → Al menos una película del carrito no existe o está inactiva
 *  -6  → Stock insuficiente para al menos una película del carrito
 *  -7  → Error al persistir en la Base de Datos
 */
public class SAVentaImp implements SAVenta {

    @Override
    public int abrir(TVenta tVenta) {
        return 0;
    }

    @Override
    public int aniadirPeliculaACarrito(TVentaPelicula tVentaPelicula) {
       
        return 0;
    }

    @Override
    public int eliminarPeliculaDeCarrito(int idVenta, int idPelicula) {
        
        return 0;
    }

  

    /**
     * CERRAR VENTA – Caso de uso principal y más crítico.
     *
     * Recibe un TVenta construido por GUIAbrirVenta con:
     *   - idCliente e idTrabajador introducidos por el usuario
     *   - lineaCarrito: List<TVentaPelicula> convertida del Map<Integer,TLineaVenta>
     *     (cada TVentaPelicula tiene idPelicula + numCopias; precio = 0 y idVenta = 0
     *      hasta que este método los enriquezca)
     *
     * Flujo de validación completa (todo antes de tocar la BD):
     *   1. Validación sintáctica de los parámetros
     *   2. Carrito no vacío
     *   3. Cliente existe y está activo
     *   4. Trabajador existe y está activo
     *   5. Para cada línea: película existe, activa y con stock suficiente
     *   6. Si todo OK → enriquece los precios y delega la transacción al DAO
     *
     * @param tVenta TVenta con idCliente, idTrabajador y la lista de líneas del carrito
     * @return ID de la venta creada (> 0) o código negativo de error
     */
    @Override
    public int cerrar(TVenta tVenta) {

        // ── 1. Validación sintáctica ──────────────────────────────────────────
        if (tVenta == null
                || tVenta.getIdCliente() <= 0
                || tVenta.getIdTrabajador() <= 0) {
            return -1;
        }

        // ── 2. Carrito no vacío ───────────────────────────────────────────────
        List<TVentaPelicula> lineas = tVenta.getLineaCarrito();
        if (lineas == null || lineas.isEmpty()) {
            return -2;
        }

        DAOCliente     daoCliente    = FactoryDAO.getInstance().getDAOCliente();
        DAOTrabajador  daoTrabajador = FactoryDAO.getInstance().getDAOTrabajador();
        DAOPelicula    daoPelicula   = FactoryDAO.getInstance().getDAOPelicula();

        // ── 3. Cliente existe y está activo ───────────────────────────────────
        TCliente tCliente = daoCliente.mostrar(tVenta.getIdCliente());
        if (tCliente == null || !tCliente.getActivo()) {
            return -3;
        }

        // ── 4. Trabajador existe ──────────────────────────────────────────────
        TTrabajador tTrabajador = daoTrabajador.mostrar(tVenta.getIdTrabajador());
        if (tTrabajador == null) {
            return -4;
        }

        // ── 5. Validar cada película del carrito y enriquecer precios ─────────
        float precioTotal = 0f;
        for (TVentaPelicula linea : lineas) {
            TPelicula pelicula = daoPelicula.mostrar(linea.getIdPelicula());

            // Película inexistente o inactiva
            if (pelicula == null || !pelicula.getActivo()) {
                return -5;
            }

            // Stock insuficiente
            if (pelicula.getStock() < linea.getNumCopias()) {
                return -6;
            }

            // Enriquecer la línea con el precio real de la película
            linea.setPrecioPelicula(pelicula.getPrecio());
            linea.setActivo(true);

            precioTotal += pelicula.getPrecio() * linea.getNumCopias();
        }

        // ── 5b. Aplicar descuento VIP si procede ─────────────────────────────
        if (tCliente.getEsVip()) {
            // El descuento del cliente VIP se aplica sobre el total
            // (El valor del descuento no está en TCliente base; se aplica un
            //  descuento fijo del 10% si no se dispone del dato concreto)
            precioTotal = precioTotal * 0.90f;
        }

        // Fijar el precio total calculado en el transfer antes de pasarlo al DAO
        tVenta.setPrecioTotal(precioTotal);
        tVenta.setActivo(true);

        // ── 6. Delegar la persistencia transaccional al DAO ───────────────────
        // DAOVenta.cerrar() inserta la venta, las líneas y descuenta el stock
        // en una única transacción JDBC. Retorna el ID generado o -7 si falla.
        DAOVenta daoVenta = FactoryDAO.getInstance().getDAOVenta();
        int idVenta = daoVenta.cerrar(tVenta);
        if (idVenta <= 0) {
            return -7;
        }
        return idVenta;
    }

    // ─── mostrar ─────────────────────────────────────────────────────────────

    @Override
    public TVenta mostrar(int id) {
        if (id <= 0) return null;

        DAOVenta daoVenta = FactoryDAO.getInstance().getDAOVenta();
        TVenta tVenta = daoVenta.mostrar(id);

        if (tVenta == null || !tVenta.getActivo()) return null;
        return tVenta;
    }

    // ─── mostrarTodas ─────────────────────────────────────────────────────────

    @Override
    public List<TVenta> mostrarTodas() {
        DAOVenta daoVenta = FactoryDAO.getInstance().getDAOVenta();
        List<TVenta> todas = daoVenta.mostrarTodas();
        List<TVenta> activas = new ArrayList<>();

        if (todas != null) {
            for (TVenta v : todas) {
                if (v.getActivo()) activas.add(v);
            }
        }
        return activas;
    }

    // ─── mostrarPorCliente ────────────────────────────────────────────────────

    @Override
    public List<TVenta> mostrarPorCiente(int idCliente) {
        if (idCliente <= 0) return new ArrayList<>();

        DAOVenta daoVenta = FactoryDAO.getInstance().getDAOVenta();
        List<TVenta> lista = daoVenta.mostrarPorCliente(idCliente);
        List<TVenta> activas = new ArrayList<>();

        if (lista != null) {
            for (TVenta v : lista) {
                if (v.getActivo()) activas.add(v);
            }
        }
        return activas;
    }

    // ─── mostrarPorTrabajador ─────────────────────────────────────────────────

    @Override
    public List<TVenta> mostrarPorTrabajador(int idTrabajador) {
        if (idTrabajador <= 0) return new ArrayList<>();

        DAOVenta daoVenta = FactoryDAO.getInstance().getDAOVenta();
        List<TVenta> lista = daoVenta.mostrarPorTrabajador(idTrabajador);
        List<TVenta> activas = new ArrayList<>();

        if (lista != null) {
            for (TVenta v : lista) {
                if (v.getActivo()) activas.add(v);
            }
        }
        return activas;
    }

    // ─── modificar ────────────────────────────────────────────────────────────

    @Override
    public int modificar(TVenta tVenta) {
        if (tVenta == null || tVenta.getId() <= 0) return -1;

        DAOVenta daoVenta = FactoryDAO.getInstance().getDAOVenta();
        TVenta existente = daoVenta.mostrar(tVenta.getId());
        if (existente == null || !existente.getActivo()) return -2;

        return daoVenta.modificar(tVenta);
    }

    // ─── eliminar ─────────────────────────────────────────────────────────────

    @Override
    public int eliminar(int id) {
        if (id <= 0) return -1;

        DAOVenta daoVenta = FactoryDAO.getInstance().getDAOVenta();
        TVenta existente = daoVenta.mostrar(id);
        if (existente == null || !existente.getActivo()) return -2;

        return daoVenta.eliminar(id);
    }

    // ─── hacerDevolucion ──────────────────────────────────────────────────────

    /**
     * Hace una devolución de una venta existente.
     * Marca la venta como inactiva y restaura el stock de cada película.
     *
     * @param tVenta TVenta con el ID de la venta a devolver
     * @return 0 si OK, negativo si error
     */
    @Override
    public int hacerDevolucion(TVenta tVenta) {
        if (tVenta == null || tVenta.getId() <= 0) return -1;

        DAOVenta daoVenta = FactoryDAO.getInstance().getDAOVenta();
        TVenta existente = daoVenta.mostrar(tVenta.getId());
        if (existente == null || !existente.getActivo()) return -2;

        return daoVenta.hacerDevolucion(existente);
    }
}