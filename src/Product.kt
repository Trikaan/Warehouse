class Product (var name : String, var cost : Double, var quantity : Int){ // Product class
    fun checkAvailability() = quantity > 0
}

class ProductFactory{
    fun createProduct(name : String, cost : Double, quantity : Int, warehouse : Warehouse){
        warehouse.products.add(Product(name, cost, quantity))
    }
}