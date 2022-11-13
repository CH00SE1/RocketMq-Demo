# [作者](https://github.com/CH00SE1/)  说明

## RocketMq-Demo
2022年11月13号学习rocketmq

## rocketmq

### 消费者的消费模式

#### 广播模式

广播模式是每一个消费者，都会消费消息。 场景：发一个消息到邮箱，手机，站内提示；

#### 负载均衡模式

负载均衡模式是每一个消费只有被某一个消费消费一次。

#### 顺序消费队列


####分布式事务消息
```text
1.RocketMq Half(Prepare) Message -- 半消息（预处理消息）
解释：半消息是一种特殊的消息类型，该状态的消息暂时不能被Consumer消费。
当一条事务消息被成功投递到broker上，但是broker并没有接受到Producer发出的二次确认时，该事务消息就处于“暂时不可被消费”状态
2.Message Status Check -- 消息状态回查
解释：由于网络抖动，Producer重启等原因，可能导致Producer向broker发送的二次确认消息没有成功送达。
如果broker检测到某条事务消息长时间处于半消息状态，则会主动向Producer端发起回查操作，查询该事务消息在Producer端的事务状态(Commit或Rollback)。
Message Status Check主要用来解决分布式事务中的超时问题。
```

#### 操作指令
```text
配置rocketmq环境变量

启动rocektmq 服务
1.mqnamesrv.cmd


启动broker
2.mqbroker.cmd -n localhost:9876 autoCreateTopicEnable=true .\conf\broker.conf

发送和接受消息测试
tools.cmd org.apache.rocketmq.example.quickstart.Producer

接受消息测试
tools.cmd org.apache.rocketmq.example.quickstart.Consumer

下载可视化操作页面
https://github.com/apache/rocketmq-externals

https://github.com/apache/rocketmq-dashboard

修改yml 配置
maven 打包 mvn clean package -Dmaven.test.skip=true
```

#### rocketmq集群
```text
1.单master模式
也就是一个master节点，称不上是集群。一旦宕机，整个服务不可用。
2.多master模式
多个master节点组成集群，单个master节点宕机或重启对应服务没有影响。
优点：所有模式中性能最高
缺点: 多个master节点宕机期间，未被消费的消息在节点恢复之前不可用，消息的实时性就受到影响。
注意：使用同步刷盘可以保证消息不被丢失，同时topic相对应的queue应该分布在集群中各个节点，而不是只在某个节点上，否则，该节点宕机会对订阅该topic的应用造成影响。
多master多slave异步复制模式
在多master模式的基础上，每个master节点都至少有一个相对应slave
master节点可读可写，但是salve只能读不能写，类似于mysql的主从模式。
优点：在master宕机时，消费者可以从slave读取消息，消息的实时性不会受影响，性能几乎和多master一样。
缺点：使用异步复制的方式有可能会造成有消息丢失的问题。
多master多slave同步双写模式
同多master多slave异步复制模式类似，区域在于master和slave之间的数据同步方式。
优点：同步双写的同步模式能保证数据不丢失。
缺点：发送单个消息RT会更长，性能相比异步复制低10%左右。
刷盘策略：同步刷盘和异步刷盘。（指的是节点自身数据是同步还是异步存储）。
同步模式：同步双写和异步复制（指的一组master和slave之间的数据同步）。
注意：要保证数据可靠，需采用同步刷盘和同步双写的方式，但性能会比较低。
```