package com.unisrobot.robothead.visualedit.nodebean.common;

/**
 * Created by Administrator on 2018/6/27.
 * <p>
 * 节点中选择型参数
 */

public class NodeParams {
        //基本动作
        public static final class Basic {
                public static final String GO_AHEAD = "前进";
                public static final String GO_BACK = "后退";
                public static final String GO_LEFT = "向左";
                public static final String GO_RIGHT = "向右";
                public static final String GO_ALL = "全部";
                public static final String JOINT_LEFT = "左肩";
                public static final String JOINT_RIGHT = "右肩";
                public static final String GO_AHEADS = "向前";
                public static final String GO_BACKS = "向后";
                public static final String GO_UP = "向上";
                public static final String GO_DOWN = "向下";
        }

        //组合动作
        public static final class CombineAction {
        }

        //听觉智能(控制耳朵灯)
        public static final class Ears {
        }

        //视觉智能(控制眼睛灯)
        public static final class Eyes {
                public static final String EMOTION_DAIJI = "待机";
                public static final String EMOTION_SHANGXIN = "伤心";
                public static final String EMOTION_AOMAN = "傲慢";
                public static final String EMOTION_KUNLE = "困了";
                public static final String EMOTION_KAIJI = "开机";
                public static final String EMOTION_JINGXI = "惊喜";
                public static final String EMOTION_SINIAN = "思念";
                public static final String EMOTION_TANLAN = "贪婪";
                public static final String EMOTION_DAKU = "大哭";
                public static final String EMOTION_SEHNGQI = "生气";
                public static final String EMOTION_GUANJI = "关机";
                public static final String EMOTION_HAPPY = "开心";
                public static final String EMOTION_TOU_YUN = "头晕";
                public static final String EMOTION_XINSUI = "心碎";

                public static final String EMOTION_GUAFENG = "刮风";
                public static final String EMOTION_XIAYU = "下雨";
                public static final String EMOTION_FENGYU = "刮风下雨";

                public static final String EMOTION_NORMAL = "正常";
                public static final String EMOTION_SPLASH = "启动页";
                public static final String EMOTION_TIP_MEASURE = "提示测量";
                public static final String EMOTION_DI_WEN = "低体温";
                public static final String EMOTION_DI_SHAO = "低烧";
                public static final String EMOTION_GAO_SHAO = "高烧";

                public static final String EMOTION_FEIJI = "飞机";
        }

        //语言智能
        public static final class Language {
                public static final String HAI_TUN = "海豚";
                public static final String MA = "马";
                public static final String MAO_MI = "猫咪";
                public static final String SHI_ZI = "狮子";
                public static final String XIAO_GOU = "小狗";
                public static final String RANODM = "随机";

                public static final String LABA = "喇叭";
                public static final String FEIJI = "飞机";
                public static final String JINGBAO = "警报";
                public static final String HUO_CHE = "火车";
                public static final String SAI_CHE = "赛车";

                public static final String HELLO = "你好";
                public static final String HELLO2 = "你好2";
                public static final String GOODBYE = "再见";
                public static final String HAPPY = "开心";

                public static final String BILI = "哔哩";
                public static final String JIGUANG = "激光";
                public static final String ZHIZHI = "吱吱";
                public static final String DOUDOU = "嘟嘟";
                public static final String LEILEI = "略略";
                public static final String ZIZI = "滋滋";
        }

        //逻辑智能
        public static final class Logic {
                public static final String REGISTER = "已注册";
                public static final String UNREGISTER = "未注册";
        }

        //思维智能
        public static final class Mind {
                public static final String SI_SHE_WU_RU = "四舍五入";
                public static final String DOWN_ZHENG = "向下取整";
                public static final String UP_ZHENG = "向上取整";

                public static final String SIN = "sin";
                public static final String COS = "cos";
                public static final String TAN = "tan";
                public static final String SUQRY = "开平方";
                public static final String ABS = "绝对值";
                public static final String ARCSIN = "arcsin";
                public static final String ARCCOS = "arccos";
                public static final String ARCTAN = "arctan";

                public static final String IS_OUSHU = "是偶数";
                public static final String IS_JISHU = "是奇数";
                public static final String IS_ZHISHU = "是质数";
                public static final String IS_ZHNEGSHU = "为整数";
                public static final String IS_ZHNEG = "为正";
                public static final String IS_FU = "为负";

                public static final String Can_Be_Zhengchu = "可被整除";

                public static final String Shape_Color_Pink = "Pink";
                public static final String Shape_Color_Yellow = "Yellow";
                public static final String Shape_Color_Green = "Green";
                public static final String Shape_Color_Orange = "Orange";
        }

        //感知智能
        public static final class Perception {
                public static final String SENSOR_HEAD = "头部感应器";
                public static final String SENSOR_LEFT_EAR = "左耳感应器";
                public static final String SENSOR_RIGHT_EAR = "右耳感应器";
                public static final String SENSOR_LEFT_CHEST = "左胸感应器";
                public static final String SENSOR_FUBU = "腹部感应器";
                public static final String SENSOR_TUNBU = "臀部感应器";
                public static final String LEFT_DIP = "左倾斜";
                public static final String RIGHT_DIP = "右倾斜";
                public static final String AHEAD_DIP = "前倾斜";
                public static final String BACK_DIP = "后倾斜";

                public static final String AHEAD_BARRIER = "前方有障碍";
                public static final String BACK_BARRIER = "后方有障碍";
                public static final String VOICE = "听到说话声";
                public static final String VOICE_SHOU = "听到拍手声";
                public static final String PEOPEL = "感应到人体";

                public static final String LEFT = "左侧";
                public static final String RIGHT = "右侧";
                public static final String LEFT_AHEAD = "左前方";
                public static final String RIGHT_AHEAD = "右前方";
                public static final String CHEST_UP = "胸口上方";
                public static final String CHEST_DOWN = "胸口下方";
                public static final String BACK_LEFT = "后背左侧";
                public static final String BACK_RIGHT = "后背右侧";

                public static final String WNEDU = "温度";
                public static final String SHIDU = "湿度";

                public static final String NAN = "男";
                public static final String NV = "女";

                public static final String LAMP_DATANG = "大堂灯";
                public static final String LAMP_GUODA = "过道灯";
                public static final String LAMP_WOSHI = "卧室灯";
                public static final String LAMP_CHUANGHU = "窗户灯";
                public static final String LAMP_CHUANGLIAN = "窗帘";
                public static final String LAMP_JIASHI = "加湿器";
                public static final String LAMP_QUSHI = "去湿器";

                public static final String KONGTIAO = "空调";
                public static final String TV = "电视";
                public static final String MENLING = "门铃";
                public static final String MIEHUO = "灭火系统";
                public static final String PAIFENGSHAN = "排风扇";
                public static final String MEN = "门";
        }
}
