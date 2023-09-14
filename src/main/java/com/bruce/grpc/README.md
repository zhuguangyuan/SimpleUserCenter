# grpc 使用 example
- gradle 引入依赖，包含code gen
- 编写.proto文件，定义相关message和service
- 执行 gradle tasks下的 other -> generateProto,生成代码在build/generated/source目录下
- 将代码拷贝到代码文件夹下即可使用