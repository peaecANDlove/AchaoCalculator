

import java.util.Random;




public class CreateCalculation {

    public String createCalculation(){  //用于产生计算式的类，并把计算式封装成一个String类型的数据进行处理
        Random random = new Random();
        String[] operator = {"+","-","*","÷"};

        int operatorCount=2+random.nextInt(2); //产生的操作符的个数2~3个
        int num[] = new int[operatorCount+1]; //产生的操作数字的个数应当比操作符多一个
        int[] operatorIndex = indexs(operatorCount, 4);
        String s = new String();

        for(int j=0;j<=operatorCount;j++){
            num[j] = random.nextInt(101); //用于产生0~100范围内的整数
        }



        switch(operatorCount){
            case 2: {

                s = num[0] + operator[operatorIndex[0]] + num[1] +
                        operator[operatorIndex[1]] + num[2] ;
                break;
            }

            case 3:{
                s=num[0]+operator[operatorIndex[0]]+num[1]+
                        operator[operatorIndex[1]]+num[2]+
                        operator[operatorIndex[2]]+num[3];
            }
        }

        s+="=";
        Calculate calculate = new Calculate();
        int answer = calculate.count(s);
        if(answer>=0){
            s+=answer;
        }else{
            return createCalculation(); //不符合条件的就重新创建计算式
        }

        return s;
    }

    private int[] indexs(int n,int m){ //用于产生操作符下标数组的方法
        Random random = new Random();
        int similar=0;    //设置判定符号相同的变量
        int []a=new int [n];
        for(int j=1;j<n;j++){
            a[j] = random.nextInt(m);
        }
        for(int j=1;j<n;j++) {
            if (a[0] == a[j]){
                similar++;
            }
        }
        if(similar == n-1){ //判断计算式中是否有两个以上的相同的操作符，如果存在就重新产生操作符下标。
            return indexs(n,m);
        }
        else{
            return a;
        }
    }
}
