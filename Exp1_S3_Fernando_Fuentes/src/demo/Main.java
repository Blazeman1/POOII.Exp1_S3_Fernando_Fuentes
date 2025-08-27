
package demo;

import command.Command;
import command.DiscountInvoker;
import command.cart.AddProductCommand;
import command.cart.BuyCartCommand;
import command.cart.DeleteProductCommand;
import command.discounts.ApplyCategoryDiscountCommand;
import command.discounts.ApplyGeneralDiscountCommand;
import controller.DiscountController;
import controller.ProductController;
import controller.ShoppingCartController;
import decorator.Component;
import model.DiscountManager;
import model.ShoppingCart;
import model.User;
import view.DiscountView;
import view.ProductView;
import view.ShoppingCartView;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n  --- Sistema de Pedidos Online con MVC y Patrones de Diseño ---\n");
        
        // Iniciación MVC
        // Crear modelos (Productos del modelo MVC)
        model.Product camisaModel = new model.Product("P001", "Camisa Algodón", 25000, "Camisas");
        model.Product pantalonModel = new model.Product("P002", "Pantalón Jeans", 80000, "Pantalones");
        model.Product vestidoModel = new model.Product("P003", "Vestido Negro", 120000, "Vestidos");
        model.Product zapatosModel = new model.Product("P004", "Zapatos Deportivos", 60000, "Calzado");
        model.Product chaquetaModel = new model.Product("P005", "Chaqueta de Cuero", 200000, "Chaquetas");
        
        List<model.Product> products = Arrays.asList(camisaModel, pantalonModel, vestidoModel, zapatosModel, chaquetaModel);
        
        // Crear Usuario y Carrito               
        User user = new User("U001", "Juan Pérez", "juan@email.com");
        ShoppingCart cart = ShoppingCart.getInstance();
        
        // Crear vistas
        ProductView productView = new ProductView();
        ShoppingCartView cartView = new ShoppingCartView();
        DiscountView discountView = new DiscountView();
        
        // Crear controladores
        ProductController productController = new ProductController(products, productView);
        ShoppingCartController cartController = new ShoppingCartController(cart, cartView);
        DiscountController discountController = new DiscountController(discountView);
        
        // Mostrar productos
        System.out.println("  --- Productos Disponibles ---");
        productController.displayProducts();
        System.out.println();
        
        // Aplicar descuentos usando el controlador
        System.out.println("  --- Aplicando Descuentos ---");
        discountController.applyPercentageDiscount(camisaModel, 10);
        discountController.applyCategoryDiscount(pantalonModel, "Pantalones", 15);
        discountController.applyCategoryDiscount(vestidoModel, "Accesorios", 20); // No debería aplicar
        System.out.println();
        
        // Agregar productos al carrito
        System.out.println("\n  --- Agregando Productos al Carrito ---");
        cartController.addToCart(camisaModel);
        cartController.addToCart(pantalonModel);
        cartController.addToCart(vestidoModel);
        cartController.addToCart(chaquetaModel);
        System.out.println();
        
        // Mostrar carrito
        System.out.println("  --- Carrito Actual ---");
        cartController.displayCart();
        System.out.println();
        
        // Crear y procesar una orden
        System.out.println("  --- Procesando Orden ---");
        command.cart.BuyCartCommand buyCommand = new command.cart.BuyCartCommand();
        buyCommand.ejecutar();
        System.out.println();
        
        // Demostración Patrón Decorator
        System.out.println("  --- Patrón Decorator ---");
        // Crear productor usando el decorator
        Component camisaDecorator = new decorator.Product("Camisa Decorator", 25000, "Camisas");
        Component pantalonDecorator = new decorator.Product("Pantalón Decorator", 80000, "Pantalones");
        Component vestidoDecorator = new decorator.Product("Vestido Decorator", 120000, "Vestidos");
        
        // Aplicar descuentos usando decorators
        System.out.println("\n 1. Descuentos con Decorator:");
        System.out.println("Producto original: " + camisaDecorator.getDescription());
        
        Component camisaConDescuento = DiscountManager.getInstance().applyGeneralDiscount(camisaDecorator);
        System.out.println("Con descuento general: " + camisaConDescuento.getDescription());
        
        Component pantalonConDescuento = DiscountManager.getInstance().applyCategoryDiscountToComponent(pantalonDecorator, "Pantalones", 20.0);
        System.out.println("Con descuento categoría: " + pantalonConDescuento.getDescription());
        
        Component vestidoSinDescuento = DiscountManager.getInstance().applyCategoryDiscountToComponent(vestidoDecorator, "Ropa deportiva", 15.0); // Categoría no coincide
        System.out.println("Sin descuento (categoría no coincide): " + vestidoSinDescuento.getDescription());
        
        // Demostración Patrón Command
        System.out.println("\n\n  --- Demostración Patrón Command ---");
        
        // Crear productos para comandos
        Component producto1 = new decorator.Product("Blusa Seda", 35000, "Blusas");
        Component producto2 = new decorator.Product("Falda Elegante", 60000, "Fadas");
        Component producto3 = new decorator.Product("Cinturón Cuero", 25000, "Accesorios");
        
        System.out.println("\n 1. Comandos de Descuento:");
        
        // Comandos de descuento
        Command descuentoGeneralCmd = new ApplyGeneralDiscountCommand(producto1);
        Command descuentoCategoriaCmd = new ApplyCategoryDiscountCommand(producto2, "Faldas", 25.0);
        Command descuentoSinEfectoCmd = new ApplyCategoryDiscountCommand(producto3, "Vestidos", 30.0);
        
        System.out.println("Antes de descuentos:");
        System.out.println(" - " + producto1.getDescription());
        System.out.println(" - " + producto2.getDescription());
        System.out.println(" - " + producto3.getDescription());
        
        System.out.println("\nAplicando descuentos:");
        descuentoGeneralCmd.ejecutar();
        descuentoCategoriaCmd.ejecutar();
        descuentoSinEfectoCmd.ejecutar();
        
        System.out.println("\nDeshaciendo descuentos:");
        descuentoGeneralCmd.deshacer();
        descuentoCategoriaCmd.deshacer();
        
        System.out.println("\n 2. Comandos de Carrito:");
        
        // Comandos de carrito
        Command agregarProductoCmd = new AddProductCommand(producto1);
        Command eliminarProductoCmd = new DeleteProductCommand(producto1);
        Command comprarCarritoCmd = new BuyCartCommand();
        
        System.out.println("Agregando producto al carrito:");
        agregarProductoCmd.ejecutar();
        
        System.out.println("\nEliminando producto del carrito:");
        eliminarProductoCmd.ejecutar();
        
        System.out.println("\nDeshaciendo eliminación:");
        eliminarProductoCmd.deshacer();
        
        System.out.println("\nRealizar compra:");
        comprarCarritoCmd.ejecutar();
        
        System.out.println("\nDeshaciendo compra:");
        comprarCarritoCmd.deshacer();
        
        // Desmostración Patrón Singleton
        System.out.println("\n\n  --- Demostración Patrón Singleton ---");
        
        DiscountManager manager1 = DiscountManager.getInstance();
        DiscountManager manager2 = DiscountManager.getInstance();
        
        ShoppingCart cart1 = ShoppingCart.getInstance();
        ShoppingCart cart2 = ShoppingCart.getInstance();
        
        System.out.println("DiscountManager - ¿Misma instancia? " + (manager1 == manager2));
        System.out.println("ShoppingCart - ¿Misma instancia? " + (cart1 == cart2));
        System.out.println("HashCode Manager1: " + manager1.hashCode());
        System.out.println("HashCode Manager2: " + manager2.hashCode());
        System.out.println("HashCode Cart1: " + cart1.hashCode());
        System.out.println("HashCode Cart2: " + cart2.hashCode());
        System.out.println();
        
        // Demostración Invocador de Comandos
        System.out.println("\n\n  --- Demostración Invocador de Comandos ---");
        
        DiscountInvoker discountInvoker = new DiscountInvoker();
        
        // Crear nuevos productos para el invoker
        Component producto4 = new decorator.Product("Abrigo Invierno", 150000, "Abrigos");
        Component producto5 = new decorator.Product("Bufanda Lana", 35000, "Accesorios");
        
        // Agregar comandos al invoker
        discountInvoker.addCommand(new ApplyGeneralDiscountCommand(producto4));
        discountInvoker.addCommand(new ApplyCategoryDiscountCommand(producto5, "Accesorios", 40.0));
        
        System.out.println("Ejecutando todos los comandos del invoker:");
        discountInvoker.ejecutarComandos();
        
        System.out.println("\nDeshaciendo todos los comandos:");
        discountInvoker.deshacerComandos();
                
        // Resumen final
        System.out.println("\n\n  --- Resumen del Sitema ---\n");
        
        System.out.println(" Arquitectura MVC implementada: ");
        System.out.println("    - Modelos: Product, Order, User, ShoppingCart, DiscountManager");
        System.out.println("    - Vistas: ProductView, ShoppingView, DiscountView");
        System.out.println("    - Controladores: ProductController, ShoppingController, DsicountController");
                
        System.out.println("\n Patrones de diseño utilizados:");
        System.out.println("    - Singleton: DiscountManager, ShoppingCart con una única instancia");
        System.out.println("    - Decorator: Sistema flexible de descuentos (TenPercentDiscount, CategoryDiscount)");
        System.out.println("    - Command: Acciones del carrito y descuentos (ApplyGeneralDiscountCommand, ApplyCategoryDiscountCommand)");
        
        System.out.println("\n Funcionalidades Implementadas");
        System.out.println("    - Gestión de productos y categorías");
        System.out.println("    - Sistema de descuentos flexible");
        System.out.println("    - Carrito de compras persistentes");
        System.out.println("    - Comandos ejecutables y deshacibles");
        System.out.println("    - Invocador de comandos múltiples");
    }
}
