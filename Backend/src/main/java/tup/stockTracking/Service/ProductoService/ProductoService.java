package tup.stockTracking.Service.ProductoService;

import java.util.List;

import tup.stockTracking.Models.Categoria;
import tup.stockTracking.Models.Producto;
import tup.stockTracking.Models.Unidad;

public interface ProductoService {

    //Obtienes un producto mediante su ID
    public Producto getProductById(Long id);

    /* Metodos -  Categoria y Unidad */
    /*
     * Estos metodos sirven para poder manipular 2 clases (Entidades) pertenencientes a la clase Producto,
     * pero sin la necesidad de acceder directamente a la clase producto.
     * Esto es util cuando quieres obtener solo ciertos elementos de una tupla/columan de una DB.
     * Puedes obtener 1 entidad por id, o todos los campos de la clase correspondiente.
    */

    public Categoria getCategoriaById(Long id);

    public List<Categoria> getAllCategoria();

    public Unidad getUnidadById(Long id);

    public List<Unidad> getAllUnidad();

    /* Métodos CRUD */
    
    /*
     * Los siguientes metodos que son los implementados por ProductoServiceImp 
     * realizaran las 4 operaciones basicas CRUD:
     * CREATE -> 
     * READ -> 
     * UPDATE->
     * DELETE -> Por razones Institucionales, no se eliminara directamente un elemento de la DB, en cambio
     * se realizara una
     */
    public List<Producto> getAllProduct();

    public Producto saveProduct(Producto producto);

    public Producto updateProduct(Producto producto, Long id);

    /* Eliminar y no eliminar productos */

    public void deleteProduct(Long id);

    public Boolean EstadoProducto(Long id);

}
