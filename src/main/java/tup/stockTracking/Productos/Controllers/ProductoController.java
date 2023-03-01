package tup.stockTracking.Productos.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tup.stockTracking.Productos.Models.Categoria;
import tup.stockTracking.Productos.Models.Producto;
import tup.stockTracking.Productos.Models.Unidad;
import tup.stockTracking.Productos.Service.ProductoServiceImp;

@RestController
@RequestMapping({ "/api/" })
@CrossOrigin(origins = "http://localhost:4200/")
public class ProductoController {

    @Autowired
    private ProductoServiceImp ProductService;

    // Controladores para obtener solo 1 elemento

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {

        return ResponseEntity.ok().body(this.ProductService.getProductById(id));
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {

        return ResponseEntity.ok().body(this.ProductService.getCategoriaById(id));
    }

    @GetMapping("/unidad/{id}")
    public ResponseEntity<Unidad> obtenerUnidadPorId(@PathVariable Long id) {

        return ResponseEntity.ok().body(this.ProductService.getUnidadById(id));
    }

    // Controlador para obtener todos los elementos

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        return ResponseEntity.ok().body(this.ProductService.getAllProduct());
    }

    @GetMapping("/productos/categorias")
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        return ResponseEntity.ok().body(this.ProductService.getAllCategoria());
    }

    @GetMapping("/productos/unidades")
    public ResponseEntity<List<Unidad>> obtenerTodasLasUnidad() {
        return ResponseEntity.ok().body(this.ProductService.getAllUnidad());
    }

    // Controladores para los las funciones CRUD que solo puede hacer el
    // ADMINISTRADOR

    // CREATE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/productos")
    public ResponseEntity<Producto> CreateProduct(@RequestBody Producto producto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.ProductService.saveProduct(producto));
    }

    // UPDATE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> UpdateProduct(@PathVariable Long id, @RequestBody Producto detalleProducto) {

        Producto productoActualizado = this.ProductService.updateProduct(detalleProducto, id);
        return ResponseEntity.ok(productoActualizado);
    }

    // DELETE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Producto> DeleteProducto(@PathVariable Long id) {

        // Lo que esta sucediendo es lo siguiente:
        /*
         * Llama a un metodo que va a buscar el 'estado' de ese producto.
         * si retorna un true: significa que el producto estaba habilitado. y pasara a
         * estar desabilitado.
         * si retorna un false: significa que le producto estaba deshabilitado, y pasara a
         * estar desabilitado.
         * 
         * los productos realmente no se eliminan de una base de datos, sino que su estado pasa a estar
         * deshabilitado para el cliente. (solo se puede habilitar desde la db)
         */

        if (!ProductService.EstadoProducto(id)) {

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().build();
    }

}
