package com.vava.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vava.net.EntityNet;
import com.vava.net.NetExpression;
import com.vava.net.exception.InvalidExpression;
import com.vava.net.exception.NotFound;
import com.vava.net.tokens.Token;


public class NetExpressionTest {
    private EntityNet originNet;

    @BeforeEach
    void setUp() throws NotFound {
        originNet = new EntityNet();

        originNet.putRelation("a", "R1", "b");
        originNet.putRelation("a", "R1", "c");
        originNet.putRelation("a", "R1", "d");
        originNet.putRelation("a", "R1", "e");
        originNet.putRelation("a", "R2", "e");

        originNet.putRelation("b", "R2", "f");
        originNet.putRelation("b", "R2", "g");
        originNet.putRelation("b", "R2", "h");
        originNet.putRelation("b", "R2", "i");
        originNet.putRelation("b", "R4", "i");

        originNet.putRelation("c", "R3", "d");
        originNet.putRelation("c", "R3", "e");
        originNet.putRelation("c", "R3", "f");
        originNet.putRelation("c", "R3", "g");

        originNet.putRelation("g", "R2", "c");
        originNet.putRelation("g", "R2", "d");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void printEntityNet() {
        System.out.println(originNet);
    }

    @Test
    public void testValidChar() {
        assertFalse(Token.isValidChar(" "));
        assertTrue(Token.isValidChar("a"));
        assertTrue(Token.isValidChar("#"));
        assertTrue(Token.isValidChar("A"));
        assertTrue(Token.isValidChar("1"));
        assertTrue(Token.isValidChar("R"));
        assertFalse(Token.isValidChar("啊"));
        assertFalse(Token.isValidChar("as"));
        assertTrue(Token.isValidChar("["));
        assertTrue(Token.isValidChar("]"));
        assertTrue(Token.isValidChar("{"));
        assertTrue(Token.isValidChar("}"));
        assertTrue(Token.isValidChar("("));
        assertTrue(Token.isValidChar(")"));
        assertTrue(Token.isValidChar(">"));
        assertFalse(Token.isValidChar("<"));
        assertTrue(Token.isValidChar("-"));
        assertTrue(Token.isValidChar("_"));
        assertTrue(Token.isValidChar("_"));
    }

    @Test
    public void testNextToken() throws InvalidExpression {
        String strNetExpression = "{assd}->R1->R2#";
        NetExpression netExpression = new NetExpression(strNetExpression);
        Token token;
        List<Token> tokens = new ArrayList<>();
        while ((token = netExpression.nextToken()) != null) {
            tokens.add(token);
        }
        System.out.println(strNetExpression);
        System.out.println(tokens);

        strNetExpression = "{assd}->R1->R2";
        netExpression = new NetExpression(strNetExpression);
        tokens = new ArrayList<>();
        while ((token = netExpression.nextToken()) != null) {
            tokens.add(token);
        }
        System.out.println(strNetExpression);
        System.out.println(tokens);
    }

    @Test
    public void testNetExpression() throws InvalidExpression {
        String strNetExpression = "assd->R1->R2";
        NetExpression netExpression = new NetExpression(strNetExpression);

    }

    @Test
    void executeTest() throws NotFound {
        //        String nql = "{a,%}->R1+*R2[~R3,%]#";
        String nql = "a->R1#";
        EntityNet result = new EntityNet();
        //        new Nql(nql, originNet).build().execute(result);
        //        System.out.println(result);
        //        assertEquals(originNet.getEntities("d", "e", "f", "g"), result);
        //
        //        nql = "%->R1#";
        //        result = new EntityNet();
        //        new Nql(nql, originNet).build().execute(result);
        //        System.out.println(result);
        //        assertEquals(originNet.getEntities("b", "c", "d", "e"), result);
    }

    //    @Test
    //    void build() throws InvalidExpression {
    //        String nql =
    //                "employee->~hire#[ok,{target,zhaoshen}]#[hello,world#]+*fire[hrbp,jiatao]->member+date->good# ? "
    //                        + "hello->*test# ? {why,good}[444,234]#";
    //        Nql expression = new Nql(nql, new EntityNet());
    //        NqlOp op = expression.build();
    //        System.out.println("from:" + nql);
    //        System.out.println("to: " + op);
    //    }
    //
    //    @Test
    //    void buildSimpleTest() throws InvalidExpression {
    //        String nql = "A";
    //        Nql expression = new Nql(nql, new EntityNet());
    //        NqlOp op = expression.build();
    //        assertEquals("A", op.toString());
    //        System.out.println("from:" + nql);
    //        System.out.println("to: " + op);
    //
    //        nql = "({A})";
    //        NqlOp op1 = new Nql(nql, new EntityNet()).build();
    //        assertEquals("{A}", op1.toString());
    //
    //        nql = "({A})[a,b]";
    //        assertEquals("({A}[a,b])", new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "({A#})[a,b#]";
    //        assertEquals("({(A#)}[a,(b#)])", new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "({A#})[a,b->c#[e,{f}]->d#]";
    //        assertEquals("({(A#)}[a,(((((b->c)#)[e,{f}])->d)#)])", new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "({A#})[a,b -> c#[e,{f}] -> d#] -> *g#[h, {i, j , k}[l,m]#] ";
    //        assertEquals("(((({(A#)}[a,(((((b->c)#)[e,{f}])->d)#)])->*g)#)[h,(({i,j,k}[l,m])#)])",
    //                new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "{a,b,c}->d ? {e,f,g}[h,i]#";
    //        assertEquals("(({a,b,c}->d)?(({e,f,g}[h,i])#))", new Nql(nql, new EntityNet()).build().toString());
    //        nql = "{a,b->d,c}";
    //        assertEquals("{a,(b->d),c}", new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "a->(b+c)[d,e]->f";
    //        assertEquals("((((a->b)+(a->c))[d,e])->f)", new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "a->(b+c)[d,e]&*f#->g";
    //        assertEquals("(((((a->b)+(a->c))[d,e])&((a->*f)#))->g)", new Nql(nql, new EntityNet()).build().toString
    //        ());
    //
    //        nql = "a->(b+(c->*~m[n,q]->o))[d,e]&*f#->g";
    //        assertEquals("(((((a->b)+((((a->c)->*~m)[n,q])->o))[d,e])&((a->*f)#))->g)",
    //                new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "a->((b+c)[d,e]&*f)#->g";
    //        assertEquals("((((((a->b)+(a->c))[d,e])&(a->*f))#)->g)", new Nql(nql, new EntityNet()).build().toString
    //        ());
    //
    //        nql = "a-b-c";
    //        assertEquals("(a-(b-c))", new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "(a-b-c?d->e)+(f->g?{h,i})";
    //        assertEquals("(((a-(b-c))?(d->e))+((f->g)?{h,i}))", new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "a-`张一山`-c";
    //        assertEquals("(a-(张一山-c))", new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "{`3`,`-1`,`+-*/`,`%#>{}[]()`}";
    //        assertEquals("{3,-1,+-*/,%#>{}[]()}", new Nql(nql, new EntityNet()).build().toString());
    //
    //        nql = "%[R1,%][R2,%]# ? {b,c}->R2# ? %->R3->R4#[R1,{a,b}]";
    //        assertEquals("((((%[R1,%])[R2,%])#)?((({b,c}->R2)#)?((((%->R3)->R4)#)[R1,{a,b}])))",
    //                new Nql(nql, new EntityNet()).build().toString());
    //
    //        String nql1 = "(a->b->)c";
    //        Exception e = Assertions.assertThrows(ParseQLError.class, () -> new Nql(nql1, new EntityNet()).build(),
    //        "");
    //        assertEquals("parse (a->b->)c error: invalid token '->' before )c, expect EntitySet after '->'",
    //                e.getMessage());
    //
    //        String nql2 = "a-b-c?-";
    //        e = assertThrows(ParseQLError.class, () -> new Nql(nql2, new EntityNet()).build());
    //        assertEquals("parse a-b-c?- error: invalid token '?' before -, expect nql after '?'", e.getMessage());
    //
    //        String nql3 = "a-b-c->*";
    //        e = assertThrows(ParseQLError.class, () -> new Nql(nql3, new EntityNet()).build());
    //        assertEquals("parse a-b-c->* error: invalid token '' before EOF, expect 'id'", e.getMessage());
    //
    //        String nql4 = "a-b-c->*+sss";
    //        e = assertThrows(ParseQLError.class, () -> new Nql(nql4, new EntityNet()).build());
    //        assertEquals("parse a-b-c->*+sss error: invalid token '+' before sss, expect 'id'", e.getMessage());
    //
    //        String nql5 = "a-b-c->*sss[23";
    //        e = assertThrows(ParseQLError.class, () -> new Nql(nql5, new EntityNet()).build());
    //        assertEquals("parse a-b-c->*sss[23 error: invalid token '' before EOF, expect ','", e.getMessage());
    //
    //        String nql6 = "a-b-c->*sss[23,]";
    //        e = assertThrows(ParseQLError.class, () -> new Nql(nql6, new EntityNet()).build());
    //        assertEquals(
    //                "parse a-b-c->*sss[23,] error: invalid token ',' before ], expect not empty filter target
    //                EntitySet",
    //                e.getMessage());
    //
    //        String nql7 = "a-b-c->*xx[23,a](v->)";
    //        e = assertThrows(ParseQLError.class, () -> new Nql(nql7, new EntityNet()).build());
    //        assertEquals("parse a-b-c->*xx[23,a](v->) error: invalid token '(' before v->), expect EOF", e
    //        .getMessage());
    //    }
    //
    //    @Test
    //    void executeTest() throws InvalidExpression, NotFound, InvalidParam {
    //        String nql = "{a,%}->R1+*R2[~R3,%]#";
    //        EntityNet result = new EntityNet();
    //        new Nql(nql, originNet).build().execute(result);
    //        System.out.println(result);
    //        assertEquals(originNet.getEntities("d", "e", "f", "g"), result);
    //
    //        nql = "%->R1#";
    //        result = new EntityNet();
    //        new Nql(nql, originNet).build().execute(result);
    //        System.out.println(result);
    //        assertEquals(originNet.getEntities("b", "c", "d", "e"), result);
    //
    //        nql = "%->*R2#";
    //        result = new EntityNet();
    //        new Nql(nql, originNet).build().execute(result);
    //        System.out.println(result);
    //        assertEquals(originNet.getEntities("c", "d", "e", "f", "g", "h", "i"), result);
    //
    //        nql = "(%-{a->R1,b->R2})#";
    //        result = new EntityNet();
    //        new Nql(nql, originNet).build().execute(result);
    //        System.out.println(result);
    //        assertEquals(originNet.getEntities("a"), result);
    //
    //        nql = "?->R1#+R3#";
    //        result = new EntityNet();
    //        new Nql(nql, originNet).build().execute(result, "a");
    //        System.out.println(result);
    //        assertEquals(originNet.getEntities("b", "c", "d", "e"), result);
    //
    //        nql = "%[R1,%][R2,%]#";
    //        result = new EntityNet();
    //        new Nql(nql, originNet).build().execute(result);
    //        System.out.println(result);
    //        assertEquals(originNet.getEntities("a"), result);
    //
    //        nql = "%[R1,%][R2,%]# ? {b,c}->R2#";
    //        result = new EntityNet();
    //        new Nql(nql, originNet).build().execute(result);
    //        System.out.println(result);
    //        assertEquals(originNet.getEntities("a", "f", "g", "h", "i"), result);
    //
    //        nql = "%[R1,%][R2,%]# ? {b,c}->R2# ? %->R3->R4#[R1,{a,b}]";
    //        EntityNet middleResult = new EntityNet();
    //        new Nql(nql, originNet).build().execute(middleResult);
    //        System.out.println(result);
    //        assertEquals(originNet.getEntities("a", "f", "g", "h", "i"), middleResult);
    //
    //        nql = "?->~R2#";
    //        result = new EntityNet();
    //        new Nql(nql, originNet).build().execute(result, new Variable(middleResult));
    //        System.out.println(result);
    //        assertEquals(originNet.getEntities("b"), result);
    //    }
    //
    //    @Test
    //    void clearCacheTest() throws InvalidExpression, NotFound, InvalidParam {
    //        StopWatch sw = new StopWatch();
    //        sw.start();
    //        String nql = "{a,%}->R1+*R2[~R3,%]#";
    //        EntityNet result = new EntityNet();
    //        NqlOp op = new Nql(nql, originNet).build();
    //        sw.split();
    //        System.out.println("build time: " + sw.toSplitString());
    //
    //        op.execute(result);
    //        assertEquals(originNet.getEntities("d", "e", "f", "g"), result);
    //
    //        op.execute(result);
    //        assertEquals(originNet.getEntities("d", "e", "f", "g"), result);
    //        sw.split();
    //        System.out.println("execute time: " + sw.toSplitString());
    //
    //        //CHECKSTYLE:OFF
    //        int times = 100000;
    //        for (int i = 0; i < times; i++) {
    //            op.execute(result);
    //        }
    //        sw.split();
    //        System.out.println(String.format("execute without cache %d times using: %s", times, sw.toSplitString()));
    //    }
    //
    //
    //    @Test
    //    void selectByQueryTest() {
    //        String query =
    //                "((<$username$>->~username[employeeNatureCode,{3,14}] ? <$username$>->~username#)+"
    //                        + "(<$username$>->~username[employeeNatureCode,{1,10,11}] ? "
    //                        + "<$username$>->~username->EmployeeDepartment->~EmployeeDepartment#)+"
    //                        + "(<$username$>->~username[employeeNatureCode,1][ifFrontLine,true] ? %[entity_class,"
    //                        + "core_hr_department][level,03]#)+(<$username$>->~leaderUsername[entity_class,"
    //                        + "core_hr_department]->(~EmployeeDepartment+
    //                        (*~superiorDepartmentId->~EmployeeDepartment))#)"
    //                        + ")->(*superiorDepartmentId+EmployeeDepartment#->*superiorDepartmentId)#";
    //        String pattern = "<\\$(\\w+)\\$>";
    //        Pattern p = Pattern.compile(pattern);
    //        Matcher m = p.matcher(query);
    //        while (m.find()) {
    //            log.info(m.group(0));
    //            log.info(m.group(1));
    //        }
    //    }
    //
    //    @Test
    //    void initArray() {
    //        String[] string = new String[10];
    //        Arrays.fill(string, "username");
    //        Arrays.stream(string).forEach(log::info);
    //    }
    //
    //    @Test
    //    public void subList() {
    //        List<String> list = new ArrayList<>();
    //        list.add("zhangsan");
    //        list.add("lisi");
    //        list.add("wangwu");
    //        log.info("list : {}", new Gson().toJson(list.subList(1, 3)));
    //    }
}
