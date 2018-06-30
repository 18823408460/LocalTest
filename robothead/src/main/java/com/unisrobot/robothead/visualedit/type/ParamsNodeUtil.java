package com.unisrobot.robothead.visualedit.type;

import com.unisrobot.robothead.bluetooth.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.NodeEvent;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.nodebean.common.VpProtocol;

/**
 * Created by Administrator on 2018/6/30.
 * <p>
 * 参数节点的值获取
 * <p>
 * 》数值型  = 静态数值 + 动态值(各种sensor的读数)
 * <p>
 * 》操作符比较型（> < =  != ）  = 静态数值 + 动态值（各种sensor的读数）
 * <p>
 * 》逻辑型（且，或 =两参）(非)：
 * <p>
 * 》类型比较型（是整数，是奇数。。。 可被整数）
 */

public class ParamsNodeUtil {

        /**
         * 这些参数节点，返回的是boolean。 剩下的返回是数值型
         */
        private static String booleanResultParamsNode[] = {
                NodeEvent.Perception.FEEL_BARRIER_BRONT,
                NodeEvent.Perception.PEOPLE_SENSOR,
                NodeEvent.Perception.API_AGE,
                NodeEvent.Perception.API_SEX,

                NodeEvent.Mind.NUMBER_COMPARE,
                NodeEvent.Mind.AND,
                NodeEvent.Mind.NON,
                NodeEvent.Mind.OR,
                NodeEvent.Mind.NUMBER_IS,
                NodeEvent.Mind.NUMBER_CAN,
        };

        public boolean isBooleanResultType(VpJsonBean.NodeDataBase nodeDataBase) {
                String prefabName = nodeDataBase.PrefabName;
                for (String data : booleanResultParamsNode) {
                        if (prefabName.equals(data)) {
                                return true;
                        }
                }
                return false;
        }
}
