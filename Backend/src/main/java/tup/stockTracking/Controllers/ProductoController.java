package tup.stockTracking.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tup.stockTracking.Models.Categoria;
import tup.stockTracking.Models.Producto;
import tup.stockTracking.Models.Unidad;
import tup.stockTracking.Service.ProductoService.ProductoServiceImp;

@RestController
@RequestMapping({ "/api/" })
@CrossOrigin(origins = "http://localhost:4200/")
public class ProductoController {

    @Autowired
    private ProductoServiceImp ProductService;

    @GetMapping("/productos/producto/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {

        return ResponseEntity.ok().body(this.ProductService.getProductById(id));
    }

    /* pruebas */

    @GetMapping("/productos/unidad/{id}")
    public ResponseEntity<Unidad> obtenerUnidadPorId(@PathVariable Long id) {

        return ResponseEntity.ok().body(this.ProductService.getUnidad(id));
    }

    @GetMapping("/productos/categoria/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaoPorId(@PathVariable Long id) {

        return ResponseEntity.ok().body(this.ProductService.getCategoria(id));
    }

    /* pruebas */


    @GetMapping("/productos")
    public List<Producto> obtenerTodosLosProductos() {
        return this.ProductService.getAllProduct();
    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> GuardarProductos(@RequestBody Producto producto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.ProductService.saveProduct(producto));
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto detalleProducto) {

        Producto productoActualizado = this.ProductService.updateProduct(detalleProducto, id);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable Long id) {

        this.ProductService.deleteProduct(id);
        return ResponseEntity.ok().build();

    }

}
