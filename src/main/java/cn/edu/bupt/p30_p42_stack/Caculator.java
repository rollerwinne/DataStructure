package cn.edu.bupt.p30_p42_stack;

import java.util.Stack;
import java.util.*;

/**
 * 先将中缀表达式转成后缀表达式,再作计算
 */
public class Caculator {
    public static void main(String[] args) {
        String infixExpression = "32+14*((14-9)/2+10^2)";
        List<String> suffixList = infixToSuffix(infixExpression);
        System.out.println(suffixList);
        int res = caculate(suffixList);
        System.out.println(infixExpression + "=" + res);
    }

    private static Map<String, Integer> priority = new HashMap<>();

    static {//定义运算符级别(为静态成员变量初始化)
        priority.put("(", -1);//栈内优先级为最低(谁都可以放在它上面)
        priority.put("+", 0);
        priority.put("-", 0);
        priority.put("*", 1);
        priority.put("/", 1);
        priority.put("^", 2);
    }

    public static List<String> infixToSuffix(String infixExpression) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (int index = 0; index < infixExpression.length(); index++) {
            String str = infixExpression.substring(index, index + 1);
            if (isNum(str)) {//如果是一个数字
                if (index == infixExpression.length() - 1)
                    s2.add(str);
                else {
                    String numStr = "";//记录多位数
                    numStr += str;
                    while (index != infixExpression.length() - 1 &&
                            isNum(infixExpression.substring(index + 1, index + 2))) {
                        index++;
                        numStr += infixExpression.substring(index, index + 1);
                    }
                    s2.add(numStr);
                }
            } else if (isOper(str)) {//如果是一个运算符
                while (!s1.isEmpty() &&
                        priority.get(str) <= priority.get(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.push(str);
            } else if ("(".equals(str)) {//如果是左括号
                s1.push(str);
            } else if (")".equals(str)) {//如果是右括号
                while (true) {
                    if (s1.isEmpty())
                        throw new RuntimeException("括号匹配有误");
                    String oper = s1.pop();
                    if ("(".equals(oper))
                        break;
                    s2.add(oper);
                }
            } else {
                throw new RuntimeException("不识别的符号");
            }
        }
        while (!s1.isEmpty())
            s2.add(s1.pop());
        return s2;
    }

    public static int caculate(List<String> suffixList) {
        Stack<Integer> numStack = new Stack<>();
        for (String ele : suffixList) {
            if (isNum(ele)) {
                numStack.push(Integer.parseInt(ele));
            } else if (isOper(ele)) {
                int num1 = numStack.pop();
                int num2 = numStack.pop();
                int res = cal(num1, num2, ele);
                numStack.push(res);
            } else {
                throw new RuntimeException("不识别的符号");
            }
        }
        return numStack.pop();
    }

    private static boolean isNum(String a) {
        return a.matches("[0-9]+");
    }

    private static boolean isOper(String a) {
        return a.matches("[\\+\\-\\*/\\^]");//正则匹配+-*/^ :[\+\-\*/\^]
    }

    private static int cal(int a, int b, String oper) {
        int res;
        switch (oper) {
            case "+":
                res = b + a;
                break;
            case "-":
                res = b - a;
                break;
            case "*":
                res = b * a;
                break;
            case "/":
                res = b / a;
                break;
            case "^":
                res = (int) Math.pow(b, a);
                break;
            default:
                throw new RuntimeException("符号不支持");
        }
        return res;
    }
}