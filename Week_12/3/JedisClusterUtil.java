package com.myproject.moods.Util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Component
public class JedisClusterUtil {
        Set<HostAndPort> nodes =new HashSet<>();
        JedisCluster jedisCluster;

    public JedisClusterUtil() {
        nodes.add(new  HostAndPort("47.98.192.167",7010));
        nodes.add(new  HostAndPort("47.98.192.167",7020) );
        nodes.add(new  HostAndPort("47.98.192.167",7030) );
        jedisCluster= new JedisCluster(nodes);
    }
    public JedisClusterUtil(String[] ip,int [] port) {
        nodes.add(new  HostAndPort(ip[0],port[0]));
        nodes.add(new  HostAndPort(ip[1],port[1]));
        nodes.add(new  HostAndPort(ip[2],port[2]));
        jedisCluster= new JedisCluster(nodes);
    }

    public Set<HostAndPort> getNodes() {
        return nodes;
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }
}
