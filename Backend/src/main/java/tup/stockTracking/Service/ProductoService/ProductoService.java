package tup.stockTracking.Service.ProductoService;

import java.util.List;

import tup.stockTracking.Models.Categoria;
import tup.stockTracking.Models.Producto;
import tup.stockTracking.Models.Unidad;

public interface ProductoService {

    public Producto getProductById(Long id);

    /* prubas */
    public Categoria getCategoria(Long id);

    public Unidad getUnidad(Long id);

    /* pruebas */
    
    public List<Producto> getAllProduct();

    public Producto saveProduct(Producto producto);

    public Producto updateProduct(Producto producto, Long id);

    public void deleteProduct(Long id);

}
