import java.util.*

class Worker(var name : String, var role : String, warehouse : Warehouse){
    private val id = UUID.randomUUID().toString()

    fun getId() = id

    init {
        warehouse.workers.add(this)
    }

    fun addProd(prod : Product, warehouse : Warehouse){
        warehouse.products.add(prod)
        println("$name: Product ${prod.name} has been successfully added. Quantity: ${prod.quantity}")
    }

    fun deleteProd(delName : String, warehouse : Warehouse) = if(warehouse.products.removeIf{it.name == delName}){
        println("$name: $delName was removed successfully!")
    } else {
        println("$name: $delName wasn't removed, error occurred!")
    }

    fun updateName(name : String, newName : String, warehouse : Warehouse){
        val prodIndex = warehouse.products.indexOfFirst {it.name == name}
        if (prodIndex != -1){
            warehouse.products[prodIndex].name = newName
            println("${this.name}: Name of ${warehouse.products[prodIndex].name} updated to $newName")
        } else {
            println("${this.name}: A product with the name $name wasn't found")
        }
    }

    fun updateCost(name : String, newCost : Double, warehouse : Warehouse){
        val prodIndex = warehouse.products.indexOfFirst {it.name == name}
        if (prodIndex != -1){
            warehouse.products[prodIndex].cost = newCost
            println("${this.name}: Cost of ${warehouse.products[prodIndex].name} updated from (${warehouse.products[prodIndex].cost}) to $newCost")
        } else {
            println("${this.name}: A product with the name $name wasn't found")
        }
    }

    fun orderProduct(name : String, quantity : Int, warehouse : Warehouse){
        if (role == "Admin"){
            val prodIndex = warehouse.products.indexOfFirst {it.name == name}
            if (prodIndex != -1){
                warehouse.products[prodIndex].quantity += quantity
                println("${this.name}: You've ordered $quantity of ${warehouse.products[prodIndex].name}")
            } else {
                println("${this.name}: A product with the name $name wasn't found")
            }
        } else println("Access denied. ${this.name} do not have permission to order products")
    }
}