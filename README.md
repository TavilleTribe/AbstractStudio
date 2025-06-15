<div align="center">
    <h1>抽象工坊</h1>
    <h5>不搞抽象吗?那你大概率戒过毒</h5>
</div>

## 💡这是什么

- 时不时更新抽象玩法内容，主打的就是一个乱搞乱玩，兴趣使然
- 目前项目是使用Java的gnu.io.SerialPort通过USART串口功能向STM32中发送数据包点灯
- 后面看心情更新，就这样吧，拜拜

## 📖所需操作

- 需要将项目pack/jdk中两个dll文件放到你使用的jdk的jdk/bin目录下
- 需要将项目pack中的RXTXComm.jar文件作为gradle依赖
- 使用shadow插件打包至生成的jar中
- 在Idea里运行，tmd不知道为什么，打包成jar运行不了

## ⚡快速食用

1、如果你已经克隆了此项目并完成上述步骤，请输入以下指令构建

```shell
gradlew build
```
