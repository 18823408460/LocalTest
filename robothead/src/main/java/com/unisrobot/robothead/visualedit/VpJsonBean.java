package com.unisrobot.robothead.visualedit;

import java.util.List;

/**
 * Created by Administrator on 2018/6/27.
 */

public class VpJsonBean {

        public String TaskType; // 整个数据包的类型，将 TaskJsonType
        public String TaskName;
        public String TaskIcon;
        public List<NodeDataBase> Tasks; // 所有节点
        public InterruptBean Interrupt;
        public List<TasksOhters> TasksOth;// 无可连接父节点的凹槽的节点
        public List<FunctionName> FuncName;// 创建的Function
        public List<TasksOhters> TasksOth2;

        public static class NodeDataBase {
                public int NodeID;   // 节点id
                public String Type; //节点类型
                public String PrefabName;
                public String Event;  //节点内容(比如”向左转,角度”),这个是不会动态变化的
                public HeartIn HeartInfo; //”心”节点内容
                public List<DataArgs> Args; //content为节点内可变化内容(比如”你好”),参数
                public List<InputNumber> InputInits; // 节点中的数字(如mind类节点A与B之间的随机整数的数字A\B)
                public List<DataColors> Colors;
                public List<DataPictures> Pictures;
                public Appendents Appendent;
                public List<NodeDataBase> Actions; // 节点包含内容(子节点，如重复直到节点内包含的内容)
                public SwitchBean Switch; // 如果否则

                @Override
                public String toString() {
                        return "NodeDataBase{" +
                                "NodeID=" + NodeID +
                                ", Type='" + Type + '\'' +
                                ", PrefabName='" + PrefabName + '\'' +
                                ", Event='" + Event + '\'' +
                                ", HeartInfo=" + HeartInfo +
                                ", Args=" + Args +
                                ", InputInits=" + InputInits +
                                ", Colors=" + Colors +
                                ", Pictures=" + Pictures +
                                ", Appendent=" + Appendent +
                                ", Actions=" + Actions +
                                ", Switch=" + Switch +
                                '}';
                }
        }

        @Override
        public String toString() {
                return "VpJsonBean{" +
                        "TaskJsonType='" + TaskType + '\'' +
                        ", TaskName='" + TaskName + '\'' +
                        ", TaskIcon='" + TaskIcon + '\'' +
                        ", Tasks=" + Tasks +
                        ", Interrupt=" + Interrupt +
                        ", TasksOth=" + TasksOth +
                        ", FuncName=" + FuncName +
                        ", TasksOth2=" + TasksOth2 +
                        '}';
        }

        public static class DataPictures {

                public String Picture;

                @Override
                public String toString() {
                        return "DataPictures{" +
                                "Picture='" + Picture + '\'' +
                                '}';
                }
        }

        public static class Appendents {
                public List<NodeDataBase> Append_A;
                public List<NodeDataBase> Append_B;
                public List<NodeDataBase> Append_C;

                @Override
                public String toString() {
                        return "Appendents{" +
                                "Append_A=" + Append_A +
                                ", Append_B=" + Append_B +
                                ", Append_C=" + Append_C +
                                '}';
                }
        }

        public static class DataArgs {
                public String Content;
                public DataWordsStyle WordsStyle;

                @Override
                public String toString() {
                        return "DataArgs{" +
                                "Content='" + Content + '\'' +
                                ", WordsStyle=" + WordsStyle +
                                '}';
                }
        }

        public static class DataWordsStyle {
                public String WordColor;
                public String WordSize;

                @Override
                public String toString() {
                        return "DataWordsStyle{" +
                                "WordColor='" + WordColor + '\'' +
                                ", WordSize='" + WordSize + '\'' +
                                '}';
                }
        }

        public static class HeartIn {
                public String HeartNumber;
                public String HeartPicture;

                @Override
                public String toString() {
                        return "HeartIn{" +
                                "HeartNumber='" + HeartNumber + '\'' +
                                ", HeartPicture='" + HeartPicture + '\'' +
                                '}';
                }
        }

        public static class NodePosition {
                public String X;
                public String Y;

                @Override
                public String toString() {
                        return "NodePosition{" +
                                "X='" + X + '\'' +
                                ", Y='" + Y + '\'' +
                                '}';
                }
        }


        public static class InputNumber {
                public String Number;

                @Override
                public String toString() {
                        return "InputNumber{" +
                                "Number='" + Number + '\'' +
                                '}';
                }
        }


        public static class DataColors {
                public String Color;

                @Override
                public String toString() {
                        return "DataColors{" +
                                "Color='" + Color + '\'' +
                                '}';
                }
        }

        public class SwitchBean {
                public List<NodeDataBase> Do;
                public List<NodeDataBase> Else;

                @Override
                public String toString() {
                        return "SwitchBean{" +
                                "Do=" + Do +
                                ", Else=" + Else +
                                '}';
                }
        }

        public static class InterruptBean {
                public String InterruptType;
                public List<NodeDataBase> Actions;

                @Override
                public String toString() {
                        return "InterruptBean{" +
                                "InterruptType='" + InterruptType + '\'' +
                                ", Actions=" + Actions +
                                '}';
                }
        }

        public static class TasksOhters {
                public NodePosition HeadNodePos;
                public List<NodeDataBase> TasksOther;

                @Override
                public String toString() {
                        return "TasksOhters{" +
                                "HeadNodePos=" + HeadNodePos +
                                ", TasksOther=" + TasksOther +
                                '}';
                }
        }

        public static class FunctionName {
                public String FunName;

                @Override
                public String toString() {
                        return "FunctionName{" +
                                "FunName='" + FunName + '\'' +
                                '}';
                }
        }

}