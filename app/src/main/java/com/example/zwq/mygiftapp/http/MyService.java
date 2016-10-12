package com.example.zwq.mygiftapp.http;


import com.example.zwq.mygiftapp.bean.GiftListContent;
import com.example.zwq.mygiftapp.bean.GiftListInfo;
import com.example.zwq.mygiftapp.bean.HotInfo;
import com.example.zwq.mygiftapp.bean.KaiCeInfo;
import com.example.zwq.mygiftapp.bean.KaiFuInfo;
import com.example.zwq.mygiftapp.bean.OpenContentInfo;
import com.example.zwq.mygiftapp.bean.SearchInfo;
import com.example.zwq.mygiftapp.bean.SpecialOneContentInfo;
import com.example.zwq.mygiftapp.bean.SpecialOneInfo;
import com.example.zwq.mygiftapp.bean.SpecialTwoContentInfo;
import com.example.zwq.mygiftapp.bean.SpecialTwoInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by zhuwenqiang on 2016/9/26.
 * http://www.1688wan.com/majax.action?method=bdxqs&pageNo=0
 * http://www.1688wan.com/majax.action?method=bdxqschild&id=119
 * http://www.1688wan.com/majax.action?method=getWeekll&pageNo=1
 * http://www.1688wan.com/majax.action?method=getWeekll&pageNo=0
 * http://www.1688wan.com/majax.action?method=getWeekllChid&id=20161007
 */
public interface MyService {
    @GET("/majax.action?method=getGiftList")
    Call<GiftListInfo> getGiftList(@Query("pageno")int pageno);
    @GET("/majax.action?method=getGiftInfo")
    Call<GiftListContent> getGiftContent(@Query("id")String id);
    @GET("/majax.action?method=getWebfutureTest")
    Call<KaiCeInfo> getKaiCeList();
    @GET("/majax.action?method=getJtkaifu")
    Call<KaiFuInfo> getKaiFuList();
    @GET("/majax.action?method=getAppInfo")
    Call<OpenContentInfo> getOpenContent(@Query("id")String id);
    @GET("/majax.action?method=hotpushForAndroid")
    Call<HotInfo> getHotInfo();
    @POST("/majax.action?method=searchGift")
    Call<SearchInfo> getSearchInfo(@Query("key") String key);
    @GET("/majax.action?method=bdxqs")
    Call<SpecialOneInfo> getSpecialOneInfo(@Query("pageNo") int pageNo) ;
    @GET("/majax.action?method=bdxqschild")
    Call<SpecialOneContentInfo> getSpecialOneContentInfo(@Query("id") int id) ;
    @GET("/majax.action?method=getWeekll")
    Call<SpecialTwoInfo> getSpecialTwoInfo(@Query("pageNo") int pageNo) ;
    @GET("/majax.action?method=getWeekllChid")
    Call<SpecialTwoContentInfo> getSpecialTwoContentInfo(@Query("id") int id) ;
}
