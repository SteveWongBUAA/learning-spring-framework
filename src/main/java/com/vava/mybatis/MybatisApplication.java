package com.vava.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vava.mybatis.bean.EmployeeRepo;
import com.vava.mybatis.mapper.EmployeeMapper;
import com.vava.mybatis.mapper.EmployeeMapperAnnotation;


/**
 * @author Steve
 * Created on 2020-03
 *
 * 1.SqlSession和connection都是非线程安全，每次使用到应该获取新的对象
 * 2.mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象,将接口和xml进行绑定
 * 3.两个重要的配置文件
 * ** 指定数据库连接信息、事务管理器信息等，可以通过xml指定，也可以代码指定
 * ** 映射文件，mapper，映射sql和接口
 *
 *
 *
 *
 */
public class MybatisApplication {
    public static void main(String[] args) throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 获取sqlSession实例
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过xml配置
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            EmployeeRepo employeeRepo = employeeMapper.getEmployeeById(1);
            System.out.println(employeeRepo);
            // 通过注解配置
            EmployeeMapperAnnotation employeeMapperAnnotation = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            employeeRepo = employeeMapperAnnotation.getEmployeeByName("tom");
            System.out.println(employeeRepo);
            employeeRepo = employeeMapperAnnotation.getEmployeeByName("tom");
            System.out.println(employeeRepo);
            employeeRepo = employeeMapperAnnotation.getEmployeeByName("tom");
            System.out.println(employeeRepo);

            int ret = employeeMapper.addEmployee(employeeRepo);
            System.out.println("add employee: " + ret + " id = " + employeeRepo.getId());
            sqlSession.commit();

            employeeRepo.setId(2);
            ret = employeeMapper.updateEmployee(employeeRepo);
            System.out.println("update employee: " + ret);
            sqlSession.commit();

            ret = employeeMapper.delEmployeeById(2);
            System.out.println("delete employee: " + ret);
            sqlSession.commit();

        } finally {
            sqlSession.close();
        }
    }

    private static SqlSessionFactory getSqlSessionFactory() throws IOException {
        // 根据xml配置文件创建一个SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
