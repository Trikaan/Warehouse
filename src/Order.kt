import java.util.*

class Order (private var data : Map<String, Int>, private val workerId: String, warehouse : Warehouse){ // Order class
    private val id = UUID.randomUUID().toString()
    private var totalPrice = 0.0
    private fun getId() : String = id

    init {
        calculateTotalPrice(warehouse)
    }

    private fun calculateTotalPrice(warehouse : Warehouse){
        for ((key, value) in data) {
            totalPrice += (warehouse.products.find{ it.name == key }?.cost ?: 0.0) * value
        }
    }

    fun getInfo(warehouse: Warehouse){
        println("----------------")
        println("Order ${warehouse.orders.indexOfFirst{it.getId() == id} + 1}:")
        for ((key, value) in data){
            println("$key x $value | ${(warehouse.products.first{it.name == key}.cost) * value}$")
        }
        println("Total: $totalPrice")
        println("Worker: ${warehouse.workers.find{it.getId() == workerId}?.name}")
        println("----------------")
    }
}

class OrderFactory{
    fun createOrder(data : Map<String, Int>, workerId: String, warehouse : Warehouse){
        val order = Order(data, workerId, warehouse)
        var allAvailable = true

        for ((key, value) in data){
            val prodIndex = warehouse.products.indexOfFirst {it.name == key}
            if (prodIndex == -1){ // Якщо виявляється що кількість якогось продукту недостатня
                println("${warehouse.workers.find { it.getId() == workerId }?.name}: Can't create an order! $key doesn't exist")
                allAvailable = false
                break
            } else if (value > warehouse.products[prodIndex].quantity) {
                println("${warehouse.workers.find { it.getId() == workerId }?.name}: Can't create an order! The quantity of $key is less than $value")
                allAvailable = false
                break
            }
        }
        if (allAvailable){
            for ((key, value) in data){
                val prodIndex = warehouse.products.indexOfFirst {it.name == key}
                warehouse.products[prodIndex].quantity -= value
            }
            warehouse.orders.add(order)
            println("${warehouse.workers.find { it.getId() == workerId }?.name}: Order was successfully added")
        }
    }
}