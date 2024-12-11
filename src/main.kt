fun main(){
    val warehouse = Warehouse() //creating warehouse
    val prodFactory = ProductFactory()
    val orderFactory = OrderFactory()

    val worker1 = Worker("Sergiy", "Staff", warehouse)
    val worker2 = Worker("John", "Admin", warehouse)

    prodFactory.createProduct("Name2", 1150.0, 2, warehouse)//creating products for adding
    prodFactory.createProduct("Name24", 1220.0, 2, warehouse)
    prodFactory.createProduct("Name1", 1390.0, 3, warehouse)
    prodFactory.createProduct("Name76", 140.0, 4, warehouse)
    prodFactory.createProduct("Name0", 15.0, 5, warehouse)

    val map1 = mapOf(
        "Name2" to 1,
        "Name24" to 1,
        "Name1" to 1,
        "Name76" to 1,
        "Name0" to 1
    )

    orderFactory.createOrder(map1, worker1.getId(), warehouse) // creating actual order

    worker1.orderProduct("Name2", 2, warehouse)
    worker2.orderProduct("Name2", 2, warehouse)
}