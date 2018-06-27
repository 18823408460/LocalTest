package com.unisrobot.robothead.visualedit;

/**
 * Created by Administrator on 2018/6/27.
 */

public final class NodeEvent {
        //基本动作
        public static final class Basic {
                public static final String DirctionSpeedSeconed = "基础_方向速度时间";
                public static final String SET_JIAN_JOINT = "设置肩关节";
                public static final String SET_ZHOU_JOINT = "设置肘关节";
                public static final String SET_WAN_JOINT = "设置腕关节";
                public static final String TRUN_LEFT_ANGLE = "向左转 , 角度";
                public static final String TRUN_RIGHT_ANGLE = "向右转 , 角度";
                public static final String WHEELSSPEED = "基础_轮子速度";
                public static final String STOP_SECOND = "停止移动";

        }

        //组合动作
        public static final class CombineAction {
                public static final String SLIENCE = "安静安静";
                public static final String WELCOME = "欢迎欢迎";
                public static final String SHUA_SHUAI = "耍帅";
                public static final String OPEN_HUAI_BAO = "张开怀抱";
                public static final String XIU_JI_ROU = "秀肌肉";
                public static final String SAY_HELLO = "Say Hello";
                public static final String QIU_BAOBAO = "求抱抱";
                public static final String JING_LI = "敬礼";
                public static final String ZHAO_SHOU = "招手3次";
                public static final String JU_JI_YUAN_QI = "聚集元气";
                public static final String BU_YAO = "不要不要";
                public static final String ZEN_MEN_LA = "怎么啦？";
        }

        //听觉智能(控制耳朵灯)
        public static final class Ears {
                public static final String HEART = "听到";
                public static final String DA_ZHAO_HU = "打招呼";
                public static final String ENGLISH = "英文";
                public static final String QUERY = "询问";
                public static final String WEATHER = "天气";
        }

        //视觉智能(控制眼睛灯)
        public static final class Eyes {
                public static final String SCREEN_ANIM = "屏幕动画";
                public static final String EMOTION = "情绪表情";
                public static final String LOOK_LEFT = "向左看 , 角度";
                public static final String LOOK_RIGHT_ = "向右看 , 角度";
                public static final String LOOK_UP = "抬头 , 角度";
                public static final String LOOK_DOWN = "低头 , 角度";
                public static final String LOOK_BROUNT = "看前方";
                public static final String SMART_TREE = "屏幕动画智能苗圃";
                public static final String SMART_DOCTOR = "屏幕动画智慧医生";
                public static final String GOOD_WORLD = "屏幕动画奇妙世界";
        }

        //语言智能
        public static final class Language {
                public static final String SPEEK = "说";
                public static final String ANIMAL = "动物";
                public static final String TRAFFIC = "交通";
                public static final String WEN_HOU = "问候";
                public static final String QU_WEI = "趣味";
                public static final String MY_VOICE = "我的声音";
        }

        //逻辑智能
        public static final class Logic {
                public static final String WAIT_SECOND = "等待几秒";
                public static final String REPEAT_COUNT = "重复几次";
                public static final String REPEAT_ALL = "一直重复";
                public static final String REPEAT_UNIT = "重复执行直到";
                public static final String IF_ELSE = "如果否则";
                public static final String IF_SO = "如果那么";
                public static final String INVOKE_FUNCTION = "调用";
                public static final String GO_TO_MAP = "前往地图标记点";
                public static final String CREATE_FUNCTION = "函数";
                public static final String OPEN_FACE = "启动人脸识别，结果为";
                public static final String OPEN_SOMETHING_REG = "打开盛开互动人脸识别";
        }

        //思维智能
        public static final class Mind {
                public static final String SET = "Mind_设置";
                public static final String CHANGE = "Mind_用..改变";
                public static final String AND = "且";
                public static final String OR = "或";
                public static final String NUMBER = "数字";
                public static final String NUMBER_CAN = "数字可被..";
                public static final String NUMBER_IS = "Mind_数字是..";
                public static final String NUMBER_OPERATE = "Mind_计算数字";
                public static final String PICTURE = "爱心";
                public static final String NUMBER_YU_SHU = "Mind_余数";
                public static final String NUMBER_CALC = "(计算)两个数";
                public static final String NUMBER_RANDOM = "Mind_随机整数";
                public static final String NON = "非";
                public static final String NUMBER_COMPARE = "(比较)两个数字";

        }

        //感知智能
        public static final class Perception {
                public static final String REPEAT_UNIT_TOUCH = "重复直到触摸";
                public static final String WAIT_TOUCH = "等待触摸";
                public static final String WAIT_PHONE = "等待手机";
                public static final String LOOK_PEOPLE = "看向说话人";
                public static final String OPEN = "打开";
                public static final String CLOSE = "关闭";
                public static final String ADD = "增加单位";
                public static final String SUB = "减少单位";
                public static final String FEEL_BARRIER_BRONT = "(感觉)前方有障碍";
                public static final String WHILE_BARRIER = "当(前方有障碍)";
                public static final String WEN_SHI_SENSOR = "温湿度传感器";
                public static final String API_SEX = "API接口返回数据：性别";
                public static final String API_AGE = "API接口返回数据：年龄";
                public static final String TOUCH_SENSOR = "触碰传感器被触碰";
                public static final String BRIGHT_SENSOR = "光照传感器光照强度";
                public static final String PEOPLE_SENSOR = "人体传感器检测到人体";
                public static final String CSB_SENSOR = "超声波传感器读数";
                public static final String WNE_SU_SENSOR = "温度传感器温度值";
        }
}
