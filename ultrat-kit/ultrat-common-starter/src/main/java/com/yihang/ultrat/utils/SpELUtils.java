package com.yihang.ultrat.utils;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Optional;

public class SpELUtils {
    private static final ExpressionParser parser = new SpelExpressionParser();

    private static final DefaultParameterNameDiscoverer parameterNameDiscover = new DefaultParameterNameDiscoverer();

    public static String parseSpEL(Method method, Object[] args, String spEl) {
        String[] params = Optional.ofNullable(parameterNameDiscover.getParameterNames(method)).orElse(new String[]{});
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }
        Expression expression = parser.parseExpression(spEl);
        return expression.getValue(context, String.class);
    }
}
