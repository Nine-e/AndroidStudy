package cn.edu.hznu.broadcastbestpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by del on 2017/11/2.
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();
    //向activities添加元素
    public static void addActivity (Activity activity){
        activities.add(activity);
    }
    //从activities中删除元素
    public static void removeActivity (Activity activity){
        activities.remove(activity);
    }
    //销毁所有活动
    public static void finishAll(){
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
