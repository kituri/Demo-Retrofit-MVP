# Demo-Retrofit-MVP
retrofit2.0.1(okhttp3) + mvp + bottom navigation (material design) + gank.io(api) + fragments

更新
2016.04.27
抽离P层某些过重的方法，使其更合理，MVP三端代码量再次降低（各端各做自己的事）

其他实现：recyclerview + fresco （带下拉刷新和自动加载更多）

说明：现学了一些新库和当前流行的 MVP 设计模式做了一个大杂烩，引用了 gank.io 的api 和 bottom navigation 来实现的伪 “tabhost” 项目

特色：

1.fragment和activity销毁的同时，自动终止该UI上的网络交互。

2.M V P三端代码都只需要很少即可实现相关功能。

3.其他一些好用的基类请到项目中查看。

4.作者认为这个挺好的（殴。

欢迎提意见指出改进方法啊！！！意见宝贵感谢感谢>_<

==================大家好，我叫分割线=========================

以下为借鉴的项目(次序不分先后)：

bottom navigation (material design) 
开源库：https://github.com/roughike/BottomBar

晓峰的Meizhi
https://github.com/drakeet/Meizhi

Witype的lite另一种姿势
https://github.com/Witype/LiteHttpDemo
