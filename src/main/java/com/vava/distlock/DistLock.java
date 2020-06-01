package com.vava.distlock;

/**
 * @author Steve
 * Created on 2020-06
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vava.mybatis.MybatisApplication;

public class DistLock {
    final static Runnable task = () -> {
        String resoureName = "testDistLock";
        DistLock distLock = new DistLock();
        String nodeInfo;
        try {
            nodeInfo = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            nodeInfo = "xxx";
            e.printStackTrace();
        }
        //        nodeInfo += ("_" + Thread.currentThread().getId());
        nodeInfo += ("_" + (int) (Math.random() * 10));
        try {
            boolean lock = distLock.lock(resoureName, nodeInfo);
            if (lock) {
                System.out.println(">>>>>> node: " + nodeInfo + " getLock! ");
                Thread.sleep((int) (Math.random() * 100));
                System.out.println(">>>>>> node: " + nodeInfo + " do something done! ");
            } else {
                System.out.println("node: " + nodeInfo + " failed to getLock! ");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                boolean unlock = distLock.unlock(resoureName, nodeInfo);
                System.out.println("node: " + nodeInfo + " unlock!: " + unlock);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(task);
        }

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    private static SqlSessionFactory getSqlSessionFactory() throws IOException {
        // 根据xml配置文件创建一个SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    public boolean lock(String resourceName, String nodeInfo) throws IOException {
        SqlSessionFactory sqlSessionFactory = MybatisApplication.getSqlSessionFactory();
        // 获取sqlSession实例
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过注解配置
            DistLockMapper distLockMapper = sqlSession.getMapper(DistLockMapper.class);
            DistLockRepo lock = distLockMapper.getLockByResourceName(resourceName);
            if (lock == null) {
                // 还没人往里写，尝试获取锁
                int ret = distLockMapper.insertLock(resourceName, nodeInfo, System.currentTimeMillis());
                if (ret == 1) {
                    // 插入成功，认为获取到锁
                    sqlSession.commit();
                    return true;
                } else {
                    // 插入失败，认为获取锁失败
                    return false;
                }
            }
            if (lock.getNodeInfo().equals(nodeInfo)) {
                // 是自己写入的，可重入
                System.out.println(">>>>>>> node: " + nodeInfo + " get the lock again! ");
                return true;
            } else {
                // 是别人写入的，获取锁失败
                return false;
            }
        } finally {
            sqlSession.close();
        }
    }

    public boolean unlock(String resourceName, String nodeInfo) throws IOException {
        SqlSessionFactory sqlSessionFactory = MybatisApplication.getSqlSessionFactory();
        // 获取sqlSession实例
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过注解配置
            DistLockMapper distLockMapper = sqlSession.getMapper(DistLockMapper.class);
            int ret = distLockMapper.deleteLock(resourceName, nodeInfo);
            //            System.out.println("node: " + nodeInfo + " unlock!, ret = " + ret);
            if (ret == 1) {
                sqlSession.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return false;
    }


}
