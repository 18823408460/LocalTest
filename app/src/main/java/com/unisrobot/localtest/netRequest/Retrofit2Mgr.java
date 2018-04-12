package com.unisrobot.localtest.netRequest;

/**
 * Created by Administrator on 2018/4/11.
 */

/**
 *  Retrofit2 的baseUlr 必须以 /（斜线） 结束，不然会抛出一个IllegalArgumentException
 *
 *  2. Converter现在从Retrofit2,中删除，两个包时独立的。
 *
 *  3， url的定义方式不一样。
 *
 *  4 。在Retrofit 1.9中，如果获取的 response 不能背解析成定义好的对象，则会调用failure。
 *  但是在Retrofit 2.0中，不管 response 是否能被解析。onResponse总是会被调用。但是在结果不能被解析的情况下，response.body()会返回null。别忘了处理这种情况
 *  Response/Failure 逻辑和Retrofit 1.9差别很大。如果你决定迁移到Retrofit 2.0，注意小心谨慎的处理这些情况。
 *
 * 5 .除了使用Call模式来定义接口，我们也可以定义自己的type，比如MyCall。。我们把Retrofit 2.0的这个机制称为CallAdapter-- RxJava准备的CallAdapter
 */
//Retrofit2是从 2.0.0beta3 -- 2.4.0至今
public class Retrofit2Mgr {

}
