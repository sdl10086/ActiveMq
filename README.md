# 消息队列
## 应用场景：异步处理，应用解耦，流量削锋和消息通讯。
主要目的是减少请求响应时间和解耦。及将不需要即时(同步)返回的操作放入消息队列中。同时只需要保证消息格式不变，消息的生产者和消费者不需要彼此联系，也不会受到对方的影响。  
会导致的问题：  
会导致暂时的不一致。如：消息的生产者会认为消息已经发送成功，而实际上还在队列中。但是最后会保持一致。但是如果消息在后续的业务中出现问题就会导致最后的不一致需要注意。  
## JMS几个概念
 **Destination** ：消息发送的目的地，也就是前面说的Queue和Topic。创建好一个消息之后，只需要把这个消息发送到目的地，消息的发送者就可以继续做自己的事情，而不用等待消息被处理完成。至于这个消息什么时候，会被哪个消费者消费，完全取决于消息的接受者。  
 **Message** ：从字面上就可以看出是被发送的消息。它有下面几种类型：  
      StreamMessage：Java 数据流消息，用标准流操作来顺序的填充和读取。  
      MapMessage：一个Map类型的消息；名称为 string 类型，而值为 Java 的基本类型。  
      TextMessage：普通字符串消息，包含一个String。  
      ObjectMessage：对象消息，包含一个可序列化的Java 对象  
      BytesMessage：二进制数组消息，包含一个byte[]。  
      XMLMessage:  一个XML类型的消息。  
   最常用的是TextMessage和ObjectMessage。    
   **Session**： 与JMS提供者所建立的会话，通过Session我们才可以创建一个Message。  
   **Connection**： 与JMS提供者建立的一个连接。可以从这个连接创建一个会话，即Session。  
   **ConnectionFactory**: 那如何创建一个Connection呢？这就需要下面讲到的ConnectionFactory了。通过这个工厂类就可以得到一个与JMS提供者的连接，即Conection。  
   **Producer**： 消息的生产者，要发送一个消息，必须通过这个生产者来发送。  
   **MessageConsumer**： 与生产者相对应，这是消息的消费者或接收者，通过它来接收一个消息。  
   前面多次提到JMS提供者，因为JMS给我们提供的只是一系列接口，当我们使用一个JMS的时候，还是需要一个第三方的提供者，它的作用就是真正管理 这些Connection，Session，Topic和Queue等。

ConnectionFactory---->Connection--->Session--->Message  
Destination + Session------------------------------------>Producer  
Destination + Session------------------------------------>MessageConsumer  

# Spring整合activeMq  

