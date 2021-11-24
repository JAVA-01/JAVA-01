package com.myproject.moods.distribute.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-03
 */
public class producer {

    public static void main(String[] args) throws JMSException {
        // 1、创建一个连接工厂对象，需要指定服务的ip及端口。
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://47.98.192.167:61616");
        // 2、使用工厂对象创建一个Connection对象。
        Connection connection = connectionFactory.createConnection();
        // 3、开启连接，调用Connection对象的start方法。
        connection.start();
        // 4、创建一个Session对象。
        // 第一个参数：是否开启事务。如果true开启事务，第二个参数无意义。一般不开启事务false。
        // 第二个参数：应答模式。自动应答或者手动应答。一般自动应答。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 5、使用Session对象创建一个Destination对象。两种形式queue、topic，现在应该使用topic
        Topic topic = session.createTopic("test-topic");
        // 6、使用Session对象创建一个Producer对象。
        MessageProducer producer = session.createProducer(topic);
        // 7、创建一个Message对象，可以使用TextMessage。
        for (int i = 0; i < 50; i++) {
            TextMessage textMessage = session.createTextMessage("第" + i + "一个ActiveMQ队列目的地的消息");
            // 8、发送消息
            producer.send(textMessage);
        }
        // 9、关闭资源
        producer.close();
        session.close();
        connection.close();

    }



}
