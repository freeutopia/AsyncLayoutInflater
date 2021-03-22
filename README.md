## AsyncLayoutInflater


轻量级Android异步布局加载框架，针对Android布局加载的两大瓶颈IO和Reflect进行优化。该框架将布局加载和解析等耗时操作移至子线程中处理，子线程将xml转化成view后，再回调到主线程完成界面加载。同时支持完成屏幕适配（详细用法参考Demo工程）。

>该框架侵入性极强（需要替换掉原有的setContentView），试用前请务必慎重，建议和MVP框架结合（参考案例：[https://github.com/freeutopia/AndMVP](https://github.com/freeutopia/AndMVP)）。

## 框架说明
|关键角色|角色功能|描述|
|---|---|---|
|`AsyncLayoutInflater`|LayoutInflater代理|开放给客户端调用的接口|
|`InflateThread`|异步任务调度|异步任务调度，维护一个阻塞队列来管理布局加载，处理完成后使用Handler回调给主线程
|`RealLayoutInflater`|真正布局解析器|继承系统LayoutInflater，在递归生成View时加入了屏幕适配策略|


## 依赖配置

```groovy

//需要在使用到本库的模块添加以下依赖
dependencies {
    ...
    implementation 'com.utopia:inflater:1.0.0'
}

```

## 使用示例
1、异步解析xml：
````java
@Override
protected void onCreate(Bundle savedInstanceState) {   
        super.onCreate(savedInstanceState);   
        AsyncLayoutInflater.from(this).inflate(R.layout.activity_main, view->{
            setContentView(view);
            //后续和view相关的操作需要放在这后面处理
        });
}
````

2、（选用）屏幕适配：
````java
public class MyApplication extends Application {   
        @Override   
        public void onCreate() {       
                super.onCreate();       
                new LayoutInflaterConfig.Builder()               
                        .designSize(600,800, Unit.DP)     //设计图尺寸，dp自选          
                        .orientation(OrientationMode.HORIZONTAL)   //设计图方向            
                        .context(this)               
                        .build();   
        }
}


````

## 异步加载说明

````java
1、异步转换出来的 View 并没有被加到 parent view中，如果需要加到 parent view 中，就需要我们自己手动添加；
2、如果 AsyncLayoutInflater 失败，那么会自动回退到UI线程来加载布局；
````
## 屏幕适配说明

````java
1、通过inflater把xml映射成View，在View构建过程中会生成我们需要关注的View像素信息（除matchParent和warpContent以外的内容），我们只需要按比例替换像素即可；
2、相对于[头条适配方案](https://github.com/JessYanCoding/AndroidAutoSize)更加便捷快速；
````

## 开源协议
```text
Copyright 2021 freeutopia.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```