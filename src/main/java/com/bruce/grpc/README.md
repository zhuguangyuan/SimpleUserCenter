# grpc 使用 example
- gradle 引入依赖，包含code gen
- 编写.proto文件，定义相关message和service
- 执行 gradle tasks下的 other -> generateProto,生成代码在build/generated/source目录下
- 将代码拷贝到代码文件夹下即可使用
- 注意generateProto task 会在每次compile 的时候都执行，想配置跳过 但是暂时不知道怎么配置
    老报错。直接注释掉在build.gradle中的配置就行
- 样例流程
  - storeService找出一列movie
  - preferenceService针对一些特性做用户喜好的区分
  - recommendService再做针对性推荐
  - ControllerService做最后的推荐
  - 客户端调用ControllerService实现的方法来获取movie