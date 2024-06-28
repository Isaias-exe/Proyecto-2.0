 import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Notificador notificador = new Notificador();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            System.out.println("\nMenú de Inventario:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Listar Productos");
            System.out.println("3. Mostrar Productos Próximos a Vencer");
            System.out.println("4. Mostrar Productos Vencidos");
            System.out.println("5. Eliminar Producto");
            System.out.println("6. Actualizar Producto");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Ingrese el nombre del producto: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la cantidad: ");
                        int cantidad = scanner.nextInt();
                        scanner.nextLine(); // Consumir nueva línea
                        System.out.print("Ingrese la fecha de vencimiento (yyyy-MM-dd): ");
                        String fechaStr = scanner.nextLine();
                        LocalDate fechaVencimiento = LocalDate.parse(fechaStr, formatter);
                        inventario.agregarProducto(new Producto(nombre, cantidad, fechaVencimiento));
                        System.out.println("Producto agregado exitosamente.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Productos en el inventario:");
                    for (Producto p : inventario.listarProductos()) {
                        System.out.println(p.getNombre() + " - Cantidad: " + p.getCantidad() + " - Vence: " + p.getFechaVencimiento());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Ingrese los días para considerar próximos a vencer: ");
                        int dias = scanner.nextInt();
                        scanner.nextLine(); // Consumir nueva línea
                        List<Producto> proximosAVencer = inventario.productosProximosAVencer(dias);
                        for (Producto p : proximosAVencer) {
                            System.out.println(p.getNombre() + " - Cantidad: " + p.getCantidad() + " - Vence: " + p.getFechaVencimiento());
                            notificador.enviarAlerta("El producto " + p.getNombre() + " está próximo a vencer el " + p.getFechaVencimiento());
                        }
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        List<Producto> vencidos = inventario.productosVencidos();
                        for (Producto p : vencidos) {
                            System.out.println(p.getNombre() + " - Cantidad: " + p.getCantidad() + " - Venció: " + p.getFechaVencimiento());
                            notificador.enviarAlerta("El producto " + p.getNombre() + " ha vencido el " + p.getFechaVencimiento());
                        }
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        System.out.print("Ingrese el nombre del producto a eliminar: ");
                        String nombreEliminar = scanner.nextLine();
                        inventario.eliminarProducto(nombreEliminar);
                        System.out.println("Producto eliminado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error: " + e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        System.out.print("Ingrese el nombre del producto a actualizar: ");
                        String nombreActualizar = scanner.nextLine();
                        System.out.print("Ingrese la nueva cantidad: ");
                        int nuevaCantidad = scanner.nextInt();
                        scanner.nextLine(); // Consumir nueva línea
                        System.out.print("Ingrese la nueva fecha de vencimiento (yyyy-MM-dd): ");
                        String nuevaFechaStr = scanner.nextLine();
                        LocalDate nuevaFechaVencimiento = LocalDate.parse(nuevaFechaStr, formatter);
                        inventario.actualizarProducto(nombreActualizar, nuevaCantidad, nuevaFechaVencimiento);
                        System.out.println("Producto actualizado exitosamente.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Saliendo del sistema.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }
}