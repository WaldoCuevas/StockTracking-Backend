package tup.stockTracking.Service.ProductoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tup.stockTracking.Exceptions.ResourceNotFoundException;
import tup.stockTracking.Models.Categoria;
import tup.stockTracking.Models.Producto;
import tup.stockTracking.Models.Unidad;
import tup.stockTracking.Repository.CategoriaRepository;
import tup.stockTracking.Repository.ProductoRepository;
import tup.stockTracking.Repository.UnidadRepository;

@Service
public class ProductoServiceImp implements ProductoService {

    @Autowired
    private ProductoRepository productRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UnidadRepository unidadRepository;

    // Metodos para obtener solo 1 elemento

    @Override
    public Producto getProductById(Long id) {

        Producto producto = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el id: " + id));
        return producto;
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la categoria con el id: " + id));
        return categoria;
    }

    @Override
    public Unidad getUnidadById(Long id) {
        Unidad unidad = unidadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la unidad de con el id: " + id));
        return unidad;
    }

    // Metodos para obtener todos los elementos

    @Override
    public List<Producto> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Categoria> getAllCategoria() {

        return categoriaRepository.findAll();
    }

    @Override
    public List<Unidad> getAllUnidad() {

        return unidadRepository.findAll();
    }

    // Metodos CRUD

    // CREATE
    @Override
    public Producto saveProduct(Producto producto) {
        producto.setCategoria(getCategoriaById((long) producto.getCategoria().getId())); // (long) 2.0
        producto.setUnidad(getUnidadById((long) producto.getUnidad().getId())); // (long)3.0)
        return productRepository.save(producto);
    }

    // UPDATE
    @Override
    public Producto updateProduct(Producto productoModificado, Long id) {

        Producto producto = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el id: " + id));

        producto.setId(productoModificado.getId());
        producto.setNombre(productoModificado.getNombre());
        producto.setDescripcion(productoModificado.getDescripcion());
        producto.setCantidad(productoModificado.getCantidad());
        producto.setEstado(productoModificado.getEstado());

        producto.setUnidad(productoModificado.getUnidad());
        producto.setCategoria(productoModificado.getCategoria());

        Producto productoActualizado = productRepository.save(producto);
        return productoActualizado;
    }

    // DELETE
    @Override
    public void deleteProduct(Long id) {

        boolean estado = true;

        Producto producto = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el id: " + id));

        producto.setCategoria(null);
        producto.setUnidad(null);
        productRepository.delete(producto);

    }

    @Override
    public Boolean EstadoProducto(Long id) {

        Producto producto = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el producto con el id: " + id));

        if (producto.getEstado()) {

            producto.setEstado(false);

            productRepository.save(producto);

            return producto.getEstado();

        }

        producto.setEstado(true);

        productRepository.save(producto);

        return producto.getEstado();
    }

}
