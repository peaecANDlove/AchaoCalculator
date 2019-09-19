import java.util.HashMap;
import java.util.Stack;

public class Calculate {
    public int count(String s){
        Stack<Integer> stackNumber = new Stack<>();//存放计算式的数字
        Stack<String> stackOperator = new Stack<>();//存放计算式的操作符
        HashMap<String,Integer> hashMap = new HashMap<>();//提前确定运算符的优先级
        hashMap.put("+",1);
        hashMap.put("-",1);
        hashMap.put("*",2);
        hashMap.put("÷",2);

        for(int i=0;i<s.length();){
            StringBuffer digit = new StringBuffer(); //声明 StringBuffer 是为了好处理字符串
            char c = s.charAt(i); //将字符串切割成单个字符
            while (Character.isDigit(c)){ //判断当前的字符是否为十进制数字，是就添加到digit中
                digit.append(c);
                i++;
                c= s.charAt(i);
            }

            if(digit.length()==0){ //当前digit中已经没有数字了
                switch(c){
                    case'=':{
                        String stmp;
                        while(!stackOperator.isEmpty()){
                            stmp = stackOperator.pop();
                            int a = stackNumber.pop();
                            int b = stackNumber.pop();
                            int result = calculate(b,a,stmp);
                            if(result<0){
                                return -1;
                            }
                            stackNumber.push(result);
                        }
                        break;
                    }
                    default:{
                        String stmp;
                        while(!stackOperator.isEmpty()){
                            stmp = stackOperator.pop();
                            if(hashMap.get(stmp)>=hashMap.get(String.valueOf(c))){
                                int a = stackNumber.pop();
                                int b = stackNumber.pop();
                                int result = calculate(b,a,stmp);
                                if(result<0){
                                    return -1;
                                }
                                stackNumber.push(result);
                            }
                            else{
                                stackOperator.push(stmp);
                                break;
                            }
                        }
                        stackOperator.push(String.valueOf(c));
                        break;
                    }
                }
            }
            else{
                stackNumber.push(Integer.valueOf(digit.toString()));
                continue;
            }
            i++;
        }

        return stackNumber.peek();
    }

    private int calculate(int a,int b,String stmp){
        int result = 0; //用于存储结果
        char s = stmp.charAt(0);
        switch(s){
            case'+':{
                result=a+b;
                break;
            }
            case'-':{
                result=a-b;
                break;
            }
            case'*':{
                result=a*b;
                break;
            }
            case'÷':{
                if(b==0) {return -1;}
                else if(a%b!=0){return -2;}
                else {result=a/b;}
                break;
            }

        }
        return result;
    }
}
