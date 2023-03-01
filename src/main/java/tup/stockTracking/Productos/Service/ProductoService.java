package tup.stockTracking.Productos.Service;

import java.util.List;

import tup.stockTracking.Productos.Models.Categoria;
import tup.stockTracking.Productos.Models.Producto;
import tup.stockTracking.Productos.Models.Unidad;

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
     * DELETE -> Por razones Institucionales, no se eliminara directamente un elemento de la DB, 
     * en cambio se realizara un cambio de estado, es decir, pasar producto a estado de baja o de alta.
     */
    public List<Producto> getAllProduct();

    public Producto saveProduct(Producto producto);

    public Producto updateProduct(Producto producto, Long id);

    /* Eliminar y dar de baja un productos */

    public void deleteProduct(Long id);

    public Boolean EstadoProducto(Long id);

}
