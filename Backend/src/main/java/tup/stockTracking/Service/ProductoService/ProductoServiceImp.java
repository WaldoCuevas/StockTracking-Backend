package tup.stockTracking.Service.ProductoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tup.stockTracking.Exceptions.ResourceNotFoundException;
import tup.stockTracking.Models.Categoria;
import tup.stockTracking.Models.Producto;
import tup.stockTracking.Models.Unidad;
import tup.stockTracking.Repository.ProductoRepository;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productRepository;

    @Override
    public Producto getProductById(Long id) {

        Producto producto = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el id: " + id));
        return producto;
    }

    /*pruebas */
    @Override
    public Categoria getCategoria(Long id) {
        Producto producto = getProductById(id);
        Categoria categoria = producto.getCategoria();
        return categoria;
    }

    @Override
    public Unidad getUnidad(Long id) {
        Producto producto = getProductById(id);
        Unidad unidad = producto.getUnidad();
        return unidad;
    }
    /*pruebas */

    @Override
    public List<Producto> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Producto saveProduct(Producto producto) {
        producto.setCategoria(getCategoria( (long) producto.getCategoria().getId())); //(long) 2.0
        producto.setUnidad(getUnidad( (long)producto.getUnidad().getId())); //(long)3.0)
        return productRepository.save(producto);
    }

    @Override
    public Producto updateProduct(Producto productoModificado, Long id) {

        Producto producto = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el id: " + id));

        producto.setId(productoModificado.getId());
        producto.setNombre(productoModificado.getNombre());
        producto.setDescripcion(productoModificado.getDescripcion());
        producto.setCantidad(productoModificado.getCantidad());
        producto.setUnidad(productoModificado.getUnidad());
        producto.setCategoria(productoModificado.getCategoria());

        Producto productoActualizado = productRepository.save(producto);
        return productoActualizado;
    }

    @Override
    public void deleteProduct(Long id) {

        Producto producto = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el id: " + id));
        productRepository.delete(producto);
    }



}
