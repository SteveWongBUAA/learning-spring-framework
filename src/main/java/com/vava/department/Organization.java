package com.vava.department;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.vava.mybatis.bean.DepartmentRepo;
import com.vava.mybatis.bean.EmployeeRepo;
import com.vava.mybatis.mapper.DepartmentMapper;
import com.vava.mybatis.mapper.EmployeeMapper;

/**
 * @author Steve
 * Created on 2020-04
 * <p>
 * 参考：极客时间：https://time.geekbang.org/column/article/207456
 */

// 构建组织架构的代码
public class Organization {
    private static final long ORGANIZATION_ROOT_ID = 1;

    public static void main(String[] args) throws IOException {
        DepartmentRepo rootDepartment = new DepartmentRepo(ORGANIZATION_ROOT_ID, 0, "根部门");
        buildOrganization(rootDepartment);

        //        DepartmentRepo department = new DepartmentRepo(666, 0, "根部门");
        //        getEmployeeByDepartmentId(department);

        System.out.println("end");
    }

    private static SqlSessionFactory getSqlSessionFactory() throws IOException {
        // 根据xml配置文件创建一个SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    private static void createOrganization() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 获取sqlSession实例
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过xml配置
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            // 创建表
            //            departmentMapper.createNewTable();
            // 插入数据
            DepartmentRepo departmentRepo = new DepartmentRepo(ORGANIZATION_ROOT_ID, 0, "根部门");
            for (int i = 2; i <= 9; i++) {
                departmentRepo.setId(i);
                departmentRepo.setName("一级部门_" + i);
                departmentRepo.setParentDepartmentId(1);
                departmentMapper.insert(departmentRepo);

                for (int j = 2; j <= 9; j++) {
                    int id2 = i * 10 + j;
                    departmentRepo.setId(id2);
                    departmentRepo.setName("二级部门_" + id2);
                    departmentRepo.setParentDepartmentId(i);
                    departmentMapper.insert(departmentRepo);

                    for (int k = 2; k <= 9; k++) {

                        int id3 = i * 100 + j * 10 + k;
                        departmentRepo.setId(id3);
                        departmentRepo.setName("三级部门_" + id3);
                        departmentRepo.setParentDepartmentId(id2);
                        departmentMapper.insert(departmentRepo);

                    }
                }
            }
            sqlSession.commit();

        } finally {
            sqlSession.close();
        }

    }

    private static void buildOrganization(DepartmentRepo departmentRepo) throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 获取sqlSession实例
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过xml配置
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

            List<DepartmentRepo> subDepartments = departmentMapper.selectSubDepartments(departmentRepo.getId());
            for (DepartmentRepo subDepartment : subDepartments) {
                departmentRepo.addSubNode(subDepartment);
                buildOrganization(subDepartment);
            }
            List<EmployeeRepo> employees = employeeMapper.getDepartmentEmployees(departmentRepo.getId());
            for (EmployeeRepo employeeRepo : employees) {
                departmentRepo.addSubNode(employeeRepo);
            }

        } finally {
            sqlSession.close();
        }
    }

    private static void getEmployeeByDepartmentId(DepartmentRepo departmentRepo) throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 获取sqlSession实例
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 通过xml配置
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

            List<EmployeeRepo> employees = employeeMapper.getDepartmentEmployees(departmentRepo.getId());
            for (EmployeeRepo employeeRepo : employees) {
                departmentRepo.addSubNode(employeeRepo);
            }


        } finally {
            sqlSession.close();
        }
    }
}
