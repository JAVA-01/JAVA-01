package com.myproject.moods.distribute.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author 孟祥迎
 * @version 1.0
 * @Date 2021-05-03
 */
public class ActivemqConsumer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory =new ActiveMQConnectionFactory("tcp://47.98.192.167:61616");

        try {
            Connection connection =connectionFactory.createConnection();
            connection.start();

            Session session =connection.createSession(false,Session.AUTO_ACKNOWLEDGE); //是否启动事务，自动签收
            Destination destination =session.createQueue("queue");
            MessageConsumer consumer =session.createConsumer(destination);
            while(true){
                TextMessage textMessage =(TextMessage) consumer.receive();
                System.out.println(textMessage.getText());
                if(textMessage==null){break;}

                    System.out.println(textMessage.getText());

            }
            if(connection!=null){
                connection.close();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }


    }


}
