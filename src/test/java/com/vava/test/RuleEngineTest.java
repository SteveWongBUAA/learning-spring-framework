package com.vava.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.InstructionSet;
import com.vava.qlexpress.ApplicationVisibleRule;
import com.vava.qlexpress.Employee;
import com.vava.qlexpress.QLExpression;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;

/**
 * @author Steve
 * Created on 2020-07
 * https://gitee.com/cuibo119/QLExpress
 */
public class RuleEngineTest {
    @Test
    public void test1() throws Exception {
        ExpressRunner runner = new ExpressRunner();
        Employee e = new Employee();
        e.setId(1);
        e.setWorkPlace("北京五道口");
        e.setDepartment("效率工程部");
        e.setNatureCode("正式员工");
        e.setType("一线员工");
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("e", e);
        String express = "((e.workPlace == '北京五道口') && !(e.department in ('效率工程部'))) "
                + "|| ((e.natureCode == '正式员工') && !(e.type in ('一线员工','三线员工')))";
        //        express = "((e.workPlace == '北京五道口') && !(e.department in ('效率工程部'))) \n"
        //                + "|| ((e.natureCode == '正式员工') && (e.type in ('一线员工','三线员工')))";
        express = "((e.workPlace == '北京五道口') && !(e.department in ('效率工程部'))) \n"
                + "|| ((e.natureCode == '正式员工') && (e.type in ('一线员工','三线员工')))";

        String[] outVarNames = runner.getOutVarNames(express);
        InstructionSet instructionSet = runner.parseInstructionSet(express);
        System.out.println(instructionSet);

        for (String i: outVarNames) {
            System.out.println(i);
        }
        Object r = runner.execute(express, context, null, false, false);
//        System.out.println("################# employee: " + e.toString() + " res: " + r);

//        e.setDepartment("商业化");
//        r = runner.execute(express, context, null, false, false);
//        System.out.println("################# employee: " + e.toString() + " res: " + r);
    }

    @Test
    public void testQLExpression() {
        List<ApplicationVisibleRule> applicationVisibleRules = new ArrayList<>();
        applicationVisibleRules.add(new ApplicationVisibleRule(1, "abd", 1, "workPlace", "==", "'北京五道口'"));
        applicationVisibleRules.add(new ApplicationVisibleRule(2, "abd", 1, "department", "not in", "('效率工程部')"));
        applicationVisibleRules.add(new ApplicationVisibleRule(1, "abd", 2, "natureCode", "==", "'正式员工'"));
        applicationVisibleRules.add(new ApplicationVisibleRule(1, "abd", 2, "type", "in", "('一线员工','三线员工')"));
        QLExpression qlExpression = new QLExpression(applicationVisibleRules);
        String s = qlExpression.toString();
        Assert.assertEquals("((e.workPlace == '北京五道口') && !(e.department in ('效率工程部'))) \n"
                + "|| ((e.natureCode == '正式员工') && (e.type in ('一线员工','三线员工')))", s);
    }

    @Test
    public void test2() throws Exception {
        String express = "int 平均分 = (语文+数学+英语+综合考试.科目2)/4.0;return 平均分";
        ExpressRunner runner = new ExpressRunner(true, true);
        String[] names = runner.getOutVarNames(express);
        for (String s : names) {
            System.out.println("var : " + s);
        }

    }

    // 8.集合的快速写法
    @Test
    public void testSet() throws Exception {
        ExpressRunner runner = new ExpressRunner(false, false);
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        String express = "abc = NewMap(1:1,2:2); return abc.get(1) + abc.get(2);";
        Object r = runner.execute(express, context, null, false, false);
        System.out.println(r);
        express = "abc = NewList(1,2,3); return abc.get(1)+abc.get(2)";
        r = runner.execute(express, context, null, false, false);
        System.out.println(r);
        express = "abc = [1,2,3]; return abc[1]+abc[2];";
        r = runner.execute(express, context, null, false, false);
        System.out.println(r);
    }

    @Test
    public void testGroovy() throws Exception {
        GroovyClassLoader groovyLoader = new GroovyClassLoader();
        Class<Script> groovyClass = (Class<Script>) groovyLoader.parseClass("a+b*c");
        Script script = groovyClass.newInstance();

        Binding bind = new Binding();

        bind.setVariable("a", 1);
        bind.setVariable("b", 2);
        bind.setVariable("c", 3);

        script.setBinding(bind);

        Object result = script.run();
        System.out.println(result);

    }

    @Test
    public void testMyRule() {
        String exp = "(#{1} && #{3}) || (#{4} && #{5})";
        Map<Integer, String> map  = new HashMap<>();
        map.put(1, "规则1");
        map.put(3, "规则3");
        map.put(4, "规则4");
        map.put(5, "规则5");

        String pattern = "#\\{([0-9]*)\\}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(exp);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
            System.out.println("Found value: " + m.group(3) );
        } else {
            System.out.println("NO MATCH");
        }

    }
}
