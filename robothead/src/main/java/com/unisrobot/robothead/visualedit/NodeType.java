package com.unisrobot.robothead.visualedit;

/**
 * Created by Administrator on 2018/6/27.
 * 节点的类型
 */

public final class NodeType {
    public static final String BASIC = "Basic";
    public static final String COMBINEACTION = "CombineAction";
    public static final String EARS = "Ears";
    public static final String EYES = "Eyes";
    public static final String LANGUAGE = "Language";
    public static final String LOGIC = "Logic";
    public static final String MIND = "Mind";
    public static final String PERCEPTION = "Perception";


    //基本动作
    public static final class Basic {
        //基础_方向速度时间
        public static final String BasicActionPrefab_GoSpeedSeconed = "BasicActionPrefab_GoSpeedSeconed";

        //设置肩关节,设置肘关节,设置腕关节
        public static final String BasicActionPrefab_BasicJoint = "BasicActionPrefab_BasicJoint";

        //向左转 , 角度;向右转 , 角度
        public static final String BasicActionPrefab_TrunAngle = "BasicActionPrefab_TrunAngle";

        //基础_轮子速度
        public static final String BasicActionPrefab_WheelsSpeed = "BasicActionPrefab_WheelsSpeed";

        //停止移动
        public static final String BasicActionPrefab_Second = "BasicActionPrefab_Second";
    }

    //组合动作
    public static final class CombineAction {
        public static final String CombineActionsPrefab_ = "CombineActionsPrefab_";
    }

    //听觉智能(控制耳朵灯)
    public static final class Ears {
        public static final String EarsPrefab_ = "EarsPrefab_";
        public static final String EarsPrefab_Hear = "EarsPrefab_Hear";
    }

    //视觉智能(控制眼睛灯)
    public static final class Eyes {
        public static final String EyesPrefab_ = "EyesPrefab_";
        public static final String EyesPrefab_LookAngle = "EyesPrefab_LookAngle";
        public static final String EyesPrefab_Feelings = "EyesPrefab_Feelings";
    }

    //语言智能
    public static final class Language {
        public static final String LanguagePrefab_Speak = "LanguagePrefab_Speak";
        public static final String LanguagePrefab_ = "LanguagePrefab_";
    }

    //逻辑智能
    public static final class Logic {
        public static final String LogicPrefab_WaitSecond = "LogicPrefab_WaitSecond";
        public static final String LogicPrefab_RepeatTimes = "LogicPrefab_RepeatTimes";
        public static final String LogicPrefab_ContinueRepeat = "LogicPrefab_ContinueRepeat";
        public static final String LogicPrefab_ContinueUntil = "LogicPrefab_ContinueUntil";
        public static final String LogicPrefab_IfElse = "LogicPrefab_IfElse";
        public static final String LogicPrefab_If = "LogicPrefab_If";
        public static final String LogicPrefab_CallFunction = "LogicPrefab_CallFunction";
        public static final String LogicPrefab_MakeFunction = "LogicPrefab_MakeFunction";
        public static final String LogicPrefab_IfElseFace = "LogicPrefab_IfElseFace";
        public static final String LogicPrefab_ = "LogicPrefab_";
    }

    //思维智能
    public static final class Mind {
        public static final String MindPrefab_Set = "MindPrefab_Set";
        public static final String MindPrefab_UseChange = "MindPrefab_UseChange";
        public static final String MindPrefab_CalculateSecond = "MindPrefab_CalculateSecond";
        public static final String MindPrefab_AndOrRelation = "MindPrefab_AndOrRelation";
        public static final String MindPrefab_Num = "MindPrefab_Num";
        public static final String MindPrefab_NumberCanBeDone = "MindPrefab_NumberCanBeDone";
        public static final String MindPrefab_CalculateNumber = "MindPrefab_CalculateNumber";
        public static final String MindPrefab_Heart = "MindPrefab_Heart";
        public static final String MindPrefab_AcalculateBRemainder = "MindPrefab_AcalculateBRemainder";
        public static final String MindPrefab_Calculate = "MindPrefab_Calculate";
        public static final String MindPrefab_AtoBRandomInteger = "MindPrefab_AtoBRandomInteger";
        public static final String MindPrefab_Not = "MindPrefab_Not";
    }

    //感知智能
    public static final class Perception {
        public static final String PerceptionPrefab_RepeatUntilTouch = "PerceptionPrefab_RepeatUntilTouch";
        public static final String PerceptionPrefab_WaitTouch = "PerceptionPrefab_WaitTouch";
        public static final String PerceptionPrefab_LookAt = "PerceptionPrefab_LookAt";
        public static final String PerceptionPrefab_When = "PerceptionPrefab_When";
        public static final String PerceptionPrefab_FellSomething = "PerceptionPrefab_FellSomething";
        public static final String PerceptionPrefab_SensorCTCT = "PerceptionPrefab_SensorCTCT";
        public static final String PerceptionPrefab_SensorAPIContext = "PerceptionPrefab_SensorAPIContext";
        public static final String PerceptionPrefab_SensorAPICC = "PerceptionPrefab_SensorAPICC";
        public static final String PerceptionPrefab_SensorTextContentAttach = "PerceptionPrefab_SensorTextContentAttach";
        public static final String PerceptionPrefab_SensorTextContent = "PerceptionPrefab_SensorTextContent";
    }
}
