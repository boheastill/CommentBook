package com.github.boheastill.pd2.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeGod {
    public static void main(String[] args) {
        People tee = new Tee();
        People eff = new Eff();
        People arr = new Arr();
        People[] people = new People[]{tee, eff, arr};
        for (int i = 0; i < 3; i++) {

            sayone(people);
            System.out.println("------------------");
        }
    }

    private static void sayone(People[] people) {
        for (People p : people) {
            System.out.println(p + "回答：");
            People.AnwserEnum auTee = p.say("你是Tee");
            People.AnwserEnum auEff = p.say("你是Eff");
            People.AnwserEnum auArr = p.say("你是Arr");
            System.out.println("auTee:" + auTee.name);
            System.out.println("auEff:" + auEff.name);
            System.out.println("auArr:" + auArr.name);
        }
    }

}
//语义分析


/**
 * 回答属实
 */
class Tee extends People {

    @Override
    AnwserEnum say(String info) {
        //如果info包含Tee，则返回true
        if (info.contains("Tee")) {
            return AnwserEnum.YES;
        } else {
            return AnwserEnum.NO;
        }
    }
}

/**
 * 回答取反
 */
class Eff extends People {

    @Override
    AnwserEnum say(String info) {
        //如果info包含Eff，则返回false
        if (info.contains("Eff")) {
            return AnwserEnum.NO;
        } else {
            return AnwserEnum.YES;
        }
    }
}

/**
 * 无规律回答
 */
class Arr extends People {

    @Override
    AnwserEnum say(String info) {
        //随机回答
        if (Math.random() > 0.5) {
            return AnwserEnum.YES;
        } else {
            return AnwserEnum.NO;
        }
    }
}

abstract class People {
    //可能的答案集合，
    enum AnwserEnum {


        YES("a"), NO("b");
        String name;

        AnwserEnum(String name) {
            this.name = name;
        }

    }

    enum PeopleEnum {
        Tee("真神"), Eff("假神"), Arr("自由神");
        String name;

        PeopleEnum(String name) {
            this.name = name;
        }
    }

    enum LogicEnum {
        AND("与"), OR("或"), NOT("非"), XOR("异或"), NONE("原来是");
        String name;

        LogicEnum(String name) {
            this.name = name;
        }
    }

    enum booleanEnum {
        TRUE("是"), FALSE("不是");
        String name;

        booleanEnum(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        String startText = "你";
        String boleanValue = booleanEnum.TRUE.name;
        String tarValue = PeopleEnum.Tee.name;
        //PeopleEnum与LogicEnum的排列组合
        String[] peopleEnum = new String[]{PeopleEnum.Tee.name, PeopleEnum.Eff.name, PeopleEnum.Arr.name};
        String[] logicEnum = new String[]{LogicEnum.AND.name, LogicEnum.OR.name, LogicEnum.NOT.name, LogicEnum.XOR.name};
        //排列组合
        LinkedList<Object> linkedList = new LinkedList<>();
        LinkedList<Object> linkedList1 = new LinkedList<>();
        for (int i = 0; i < peopleEnum.length; i++) {
            linkedList.addAll(mix(peopleEnum[i]))
            ;
        }
        //对peopleEnum进行排列组合
        for (int i = 0; i < peopleEnum.length; i++) {
            for (int j = 0; j < peopleEnum.length; j++) {
                if (i!=j){
                    linkedList1.addAll(mix(peopleEnum[i], peopleEnum[j]));
                }
            }
        }


        //生成判断语句
        StringBuilder sb = new StringBuilder();
        sb.append(startText).append(boleanValue).append(tarValue);
        System.out.println(sb);
    }


    static List<String> mix(String a) {
        String[] logicEnum = new String[]{LogicEnum.NOT.name, LogicEnum.NONE.name};//前置
        for (int i = 0; i < logicEnum.length; i++) {
            logicEnum[i] = logicEnum[i] + a;
        }
        //数组转list
        List<String> list = Arrays.asList(logicEnum);
        return list;
    }

    static List<String> mix(String a, String b) {
        String[] logicEnum = new String[]{LogicEnum.AND.name, LogicEnum.OR.name, LogicEnum.XOR.name};
        for (int i = 0; i < logicEnum.length; i++) {
            logicEnum[i] = a + logicEnum[i] + b;
        }
        //数组转list
        List<String> list = Arrays.asList(logicEnum);
        return list;
    }

    //提问问题，获得答案
    abstract AnwserEnum say(String info);
}
