import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    public List<Producto> productosProximosAVencer(int dias) {
        LocalDate ahora = LocalDate.now();
        return productos.stream()
                .filter(p -> p.getFechaVencimiento().isAfter(ahora) && p.getFechaVencimiento().isBefore(ahora.plusDays(dias)))
                .collect(Collectors.toList());
    }

    public List<Producto> productosVencidos() {
        LocalDate ahora = LocalDate.now();
        return productos.stream()
                .filter(p -> p.getFechaVencimiento().isBefore(ahora))
                .collect(Collectors.toList());
    }

    public void eliminarProducto(String nombre) {
        productos.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
    }

    public void actualizarProducto(String nombre, int cantidad, LocalDate fechaVencimiento) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                p.setCantidad(cantidad);
                p.setFechaVencimiento(fechaVencimiento);
            }
        }
    }
}