package com.vava.net;

import com.vava.net.exception.InvalidExpression;
import com.vava.net.tokens.NetOp;
import com.vava.net.tokens.OperatorEnum;
import com.vava.net.tokens.RelationOp;
import com.vava.net.tokens.SingleEntityOp;
import com.vava.net.tokens.Token;

/**
 * @author Steve
 * Created on 2020-04
 */
public class NetExpression {
    private String expression;
    // 处理到哪个字符
    private int nowPos;

    public NetExpression(String expression) throws InvalidExpression {
        this.expression = expression.trim();
        this.nowPos = 0;
        NetOp parse = parse();
//        System.out.println(parse);
    }


    public NetOp parse() throws InvalidExpression {
        // 输入是一个EntityNet，经过relationOp找到另一个EntityNet，新的EntityNet又可以接一个relationOp。。。
        NetOp netOp = parseEntityNet();
        return parseRelationChain(netOp);
    }

    private NetOp parseRelationChain(NetOp netOp) throws InvalidExpression {
        Token token = nextToken();
        if (null == token) {
            // 没有下文了，返回上一个
            return netOp;
        }
        String strToken = token.getStrToken();
        if (strToken.equals(OperatorEnum.RELATION.getStrOperator())) {
            return parseRelationChain(parseRelationItem(netOp));
        } else {
            throw new InvalidExpression("now token should be ->, but got: " + token);
        }

    }

    private NetOp parseRelationItem(NetOp sourceOp) throws InvalidExpression {
        Token token = nextToken();
        if (null == token) {
            return sourceOp;
        }
        if (!token.isOp()) {
            return new RelationOp(sourceOp, token);
        }
        return null;
    }

    private NetOp parseEntityNet() throws InvalidExpression {
        Token token = nextToken();
        if (null == token) return null;
        String strToken = token.getStrToken();
        if (strToken.equals(OperatorEnum.BRACE_LEFT.getStrOperator())) {
            //todo
            return null;
        } else {
            if (!token.isOp()) {
                return parseSingleEntity(token);
            }
        }
        return null;
    }

    private NetOp parseSingleEntity(Token token) {
        return new SingleEntityOp(token);
    }

    public Token nextToken() throws InvalidExpression {
        // 跳过空格
        while (nowPos < expression.length() && expression.charAt(nowPos) == ' ') {
            nowPos++;
        }
        if (nowPos >= expression.length()) {
            return null;
        }
        String strToken = expression.substring(nowPos, nowPos + 1);
        if (!Token.isValidChar(strToken)) {
            throw new InvalidExpression("invalid token at " + nowPos + " [" + strToken + "]");
        }
        // 高优匹配运算符
        if (strToken.equals("-")) {
            if (nowPos + 1 < expression.length() && expression.substring(nowPos + 1, nowPos + 2).equals(">")) {
                nowPos += 2;
                return new Token(OperatorEnum.RELATION.getStrOperator(), true);
            }
        }
        OperatorEnum operatorEnum = OperatorEnum.findByStrOperator(strToken);
        if (null != operatorEnum) {
            // 匹配到合法operator
            nowPos++;
            return new Token(operatorEnum.getStrOperator(), true);
        }
        // 到达这里说明不匹配运算符，那就认为是一个变量，变量的终止条件是检查到了另一个运算符，或者到达表达式结尾
        int varStartPos = nowPos;
        int varEndPos = expression.length();
        for (; nowPos < expression.length(); nowPos++) {
            if (OperatorEnum.findByStrOperator(expression.substring(nowPos, nowPos + 1)) != null) {
                varEndPos = nowPos;
                break;
            }
        }
        String strVar = expression.substring(varStartPos, varEndPos);
        return new Token(strVar, false);
    }
}
