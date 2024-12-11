class Warehouse {
    var products: MutableList<Product> = mutableListOf()
    var orders: MutableList<Order> = mutableListOf()
    var workers: MutableList<Worker> = mutableListOf()

    fun getAvailableProds(){
        products.filter{it.checkAvailability()}
            .forEach{println("${it.name}, ${it.cost}$, ${it.quantity}")}
    }

    fun getProdsSortedByName(){
        products.sortedBy{it.name}
            .forEach {println("${it.name}, ${it.cost}$, ${it.quantity}")}
    }

    fun getProdsSortedByPrice(){
        products.sortedBy{it.cost}
            .forEach {println("${it.name}, ${it.cost}$, ${it.quantity}")}
    }

    fun getOrders(warehouse : Warehouse){
        orders.forEach{it.getInfo(warehouse)}
    }

    fun getWorkers(){
        workers.forEach{println("${it.name} works here")}
    }
}