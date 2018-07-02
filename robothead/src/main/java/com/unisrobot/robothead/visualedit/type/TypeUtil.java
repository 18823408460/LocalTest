package com.unisrobot.robothead.visualedit.type;

import com.unisrobot.robothead.visualedit.nodebean.common.NodeEvent;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;

/**
 * Created by Administrator on 2018/6/28.
 */

public class TypeUtil {

    /**
     * 这些节点是容器节点
     *
     * @param nodeDataBase
     * @return
     */
    public static boolean IsViewGroupNode(VpJsonBean.NodeDataBase nodeDataBase) {
        String prefabName = nodeDataBase.PrefabName;
        if (NodeJsonType.Perception.PerceptionPrefab_RepeatUntilTouch.equals(prefabName) ||
                NodeJsonType.Logic.LogicPrefab_RepeatCount.equals(prefabName) ||
                NodeJsonType.Logic.LogicPrefab_RepeatCycle.equals(prefabName) ||
                NodeJsonType.Logic.LogicPrefab_If.equals(prefabName) ||
                NodeJsonType.Logic.LogicPrefab_IfElseFace.equals(prefabName) ||
                NodeJsonType.Logic.LogicPrefab_IfElse.equals(prefabName) ||
                NodeJsonType.Logic.LogicPrefab_RepeatUntil.equals(prefabName) ||
                NodeJsonType.Logic.LogicPrefab_MakeFunction.equals(prefabName)
                ) {
            return true;
        }
        return false;
    }


    /**
     * 这个
     *
     * @param nodeDataBase
     * @return
     */
    public static NodeRunType getRunType(VpJsonBean.NodeDataBase nodeDataBase) {
        String prefabName = nodeDataBase.PrefabName;
        NodeRunType nodeRunType = NodeRunType.TIME;
        switch (prefabName) {
            case NodeJsonType.Basic.BasicActionPrefab_GoSpeedSeconed:
            case NodeJsonType.Basic.BasicActionPrefab_BasicJoint:
            case NodeJsonType.Basic.BasicActionPrefab_TrunAngle:
            case NodeJsonType.Basic.BasicActionPrefab_WheelsSpeed:
            case NodeJsonType.Basic.BasicActionPrefab_Second:

            case NodeJsonType.CombineAction.CombineActionsPrefab_:

            case NodeJsonType.Eyes.EyesPrefab_:
            case NodeJsonType.Eyes.EyesPrefab_Feelings:
            case NodeJsonType.Eyes.EyesPrefab_LookAngle:

            case NodeJsonType.Language.LanguagePrefab_:
            case NodeJsonType.Language.LanguagePrefab_Speak:
            case NodeJsonType.Perception.PerceptionPrefab_LookAt:
            case NodeJsonType.Mind.MindPrefab_Set:
            case NodeJsonType.Mind.MindPrefab_UseChange:
            case NodeJsonType.Logic.LogicPrefab_:
                nodeRunType = NodeRunType.TIME;
                break;

            case NodeJsonType.Ears.EarsPrefab_:
            case NodeJsonType.Perception.PerceptionPrefab_When:
            case NodeJsonType.Logic.LogicPrefab_WaitSecond:
                nodeRunType = NodeRunType.WAIT_NEXT;
                break;
            case NodeJsonType.Perception.PerceptionPrefab_WaitTouch:
                String event1 = nodeDataBase.Event;
                if (NodeEvent.Perception.WAIT_PHONE.equals(event1) || NodeEvent.Perception.WAIT_TOUCH.equals(event1)) {
                    nodeRunType = NodeRunType.WAIT_NEXT;
                } else if (NodeEvent.Perception.OPEN.equals(event1) || NodeEvent.Perception.CLOSE.equals(event1)) {
                    nodeRunType = NodeRunType.TIME;
                }
                break;
            case NodeJsonType.Perception.PerceptionPrefab_RepeatUntilTouch:
            case NodeJsonType.Logic.LogicPrefab_RepeatUntil:
                nodeRunType = NodeRunType.REPEAT_UNIT;
                break;
            case NodeJsonType.Logic.LogicPrefab_RepeatCount:
                nodeRunType = NodeRunType.REPEAT_COUNT;
                break;
            case NodeJsonType.Logic.LogicPrefab_CallFunction:
                String event = nodeDataBase.Event;
                if (NodeEvent.Logic.GO_TO_MAP.equals(event)) {
                    nodeRunType = NodeRunType.WAIT_NEXT;

                } else if (NodeEvent.Logic.INVOKE_FUNCTION.equals(event)) {
                    nodeRunType = NodeRunType.REPEAT_COUNT;
                }
                break;
            case NodeJsonType.Logic.LogicPrefab_If:
            case NodeJsonType.Logic.LogicPrefab_IfElse:
                nodeRunType = NodeRunType.CONDITION;
                break;
            case NodeJsonType.Logic.LogicPrefab_IfElseFace:
                nodeRunType = NodeRunType.FACE;
                break;
            case NodeJsonType.Logic.LogicPrefab_RepeatCycle:
                nodeRunType = NodeRunType.REPEAT_CYCLE;
                break;
        }
        return nodeRunType;
    }
}
