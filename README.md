## mybatis-plus-serialization
为了实现mybatis-plus不能序列化传输问题，提供了一个兼容转换功能。

比如dubbo中不能IService中接口不能rpc调用。

**注意： 本项目只为兼容旧项目迁移等情况下使用，永远不要在项目开始阶段使用。不应该把SQL对象暴露为RPC接口。**

### 使用说明

dubbo接口继承CRUDService即可，调用时使用CRUDService中方法和参数
