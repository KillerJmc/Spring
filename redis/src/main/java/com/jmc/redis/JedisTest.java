package com.jmc.redis;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JedisTest {
    @Test
    public void stringTest() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("name", "jmc");
        //20秒后自动删除
        jedis.setex("activeCode", 20, "18");
        String s = jedis.get("name");
        System.out.println(s);
//        jedis.del("name");
        jedis.close();
        jedis.close();
    }

    public interface JedisRunnable {
        void run(Jedis jedis);
    }

    public void runJedis(JedisRunnable jr) {
        Jedis jedis = new Jedis();
        jr.run(jedis);
        jedis.close();
    }

    @Test
    public void hashTest() {
        runJedis(jedis -> {
            jedis.hset("m", "name", "jmc");
            jedis.hset("m", "age", "18");
            jedis.hset("m", "what", "kkk");
            Map<String, String> m = jedis.hgetAll("m");
            System.out.println(m);

            String age = jedis.hget("m", "age");
            System.out.println(age);

            jedis.hdel("m", "what");

            Map<String, String> m2 = jedis.hgetAll("m");
            System.out.println(m2);

//            jedis.del("m");
        });
    }

    @Test
    public void listTest() {
        runJedis(jedis -> {
            jedis.rpush("l", "1", "2", "3", "4");
            jedis.lpush("l", "6");

            List<String> l = jedis.lrange("l", 0, -1);
            System.out.println(l);

            String lp = jedis.lpop("l");
            String rp = jedis.rpop("l");
            System.out.println("lp = " + lp);
            System.out.println("rp = " + rp);

            List<String> l2 = jedis.lrange("l", 0, -1);
            System.out.println(l2);

//            jedis.del("l");
        });
    }

    @Test
    public void setTest() {
        runJedis(jedis -> {
            jedis.sadd("s", "1", "2", "3", "4", "a", "b", "c");
            Set<String> s = jedis.smembers("s");
            System.out.println(s);

            jedis.srem("s", "1", "3", "b");
            Set<String> s2 = jedis.smembers("s");
            System.out.println(s2);

//            jedis.del("s");
        });
    }
    
    @Test
    public void zsetTest() {
        runJedis(jedis -> {
            jedis.zadd("z", 100, "jmc");
            jedis.zadd("z", 500, "李四");
            jedis.zadd("z", 300, "王五");
            jedis.zadd("z", 10, "张飞");

            Set<String> z = jedis.zrange("z", 0, -1);
            System.out.println(z);

            Set<String> z2 = jedis.zrangeByScore("z", 10, 200);
            System.out.println(z2);

            jedis.zrem("z", "张飞", "王五");
            Set<String> z3 = jedis.zrange("z", 0, -1);
            System.out.println(z3);

            Set<Tuple> z4 = jedis.zrangeWithScores("z", 0, -1);
            System.out.println(z4);

//            jedis.del("z");
        });
    }

    @Test
    public void zz_final_CmdTest() {
        runJedis(jedis -> {
            Set<String> keys = jedis.keys("*");
            System.out.println(keys);

            System.out.println("---------------------------------------------------------");
            keys.forEach(k -> System.out.println(jedis.type(k)));

            keys.forEach(jedis::del);
        });
    }

    @Test
    public void poolTest() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
//        JedisPool pool = new JedisPool();
        JedisPool pool = new JedisPool(config);
        Jedis jedis = pool.getResource();

        jedis.setex("k", 1, "hehe");
        System.out.println(jedis.get("k"));

        jedis.close();
    }


}
