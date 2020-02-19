现在的微商（电商）系统有很多都是有返利的，有着不超过三层的层级关系，返利的计算与微商（电商）的商品销售应该是松耦合的关系。当生成一个已付款的订单时，触发计算返利的事件，根据返利的计算规则和层级关系，分配返利，这时生成的返利应该只是客户预计可以获得的收益，当订单状态为完成时触发返利成功的事件，这时就可以将返利发放到客户的账户。在订单发生退货退款事件时，相应扣除返利。根据领域驱动设计，在创建新订单时，需要发布几个事件，一个通知商品扣除，一个是通知返利计算。通知返利计算通过消息触发，当返利计算服务接收到消息时触发。
返利是一个实体，返利的计算和查询是分开的，返利的计算完成后，通知用户的账户，改变用户的收益，计算和查询可以是两个不同的库或表，用户查询时要获得比较多的信息，他会关心这个订单是哪个下级买的，是直接下级还是间接下级，订单的金额，返利的比例或金额，而这些查询的项目，有些是计算中并不关心的。这里根据这个原则做了个例子，只是返利的例子，含着命令与查询两个服务。
CQRS，是 Command Query Responsibility Segregation的缩写，也就是通常所说的读写隔离。在上面，我们说，为了性能考虑，将聚合对象的数据状态用物化视图的形式保存，可以用于数据的查询操作，也就是我们把数据的更新与查询的流程隔离开来。我们通过事件来更新聚合对象的数据状态，同时由另一个处理器处理相同的事件，来更新物化视图的数据。
所以，Event Sourcing与CQRS有着天然的联系，所以也经常会有人把他们放在一起讨论。实际上，CQRS是在使用Event Sourcing模式以后，又使用了物化视图的情况下，所产生的额外的好处。
