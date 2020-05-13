package com.vava.test;


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

}

