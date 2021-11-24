package com.myproject.moods.distribute.activemq;



import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-03
 */
public class ActivemqProduce {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory =new ActiveMQConnectionFactory("tcp://47.98.192.167:61616");
        try {
            Connection connection =connectionFactory.createConnection();
            connection.start();//连接默认关闭
            Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination destination =session.createQueue("queue");

//            Topic topic =session.createTopic("topic");
            MessageProducer  producer =session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//投放模式，非持久化
            for (int i=0;i<=5;i++) {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText("我是第"+i+"消息");
                producer.send(textMessage);
            }
            if(connection!=null){
                connection.close();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
