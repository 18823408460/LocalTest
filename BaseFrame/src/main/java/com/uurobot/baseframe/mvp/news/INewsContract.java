package com.uurobot.baseframe.mvp.news;

import com.uurobot.baseframe.mvp.base.IBaseContract;

import java.util.List;

/**
 * Created by Administrator on 2018/6/13.
 */

public interface INewsContract {
        interface IView extends IBaseContract.IBaseView{
                // 将获取到的新闻信息更新到界面
                void updateNews(List<String>  data);
        }

        interface IPresenter extends IBaseContract.IPresenter<IView>{
                // 获取新闻信息
                void getNews(String time);
        }
}
