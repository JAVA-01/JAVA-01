#  AOP 两种动态代理实现

## JDK 动态代理

~~~json
# 依接口实现， JDK提供的Proxy类的静态方法newProxyInstance（类加载器，需要实现的接口，处理器类）。
此方法的返回值是生成的动态类
# handler需要实现一个InvocationHandler 接口，并重写里面的invoke方法，动态类调用方法时，自动执行invoke放。此时动态类，方法，和方法参数填入到invoke方法的参数中。而原本的类在创建handler时作为构造方法的参数传入，方便处理类在invoke时做一些操作。
也就是动态代理类需要，类加载器，类实现的接口，和一个处理器类实例。
动态代理类调用某个接口中有的方法时会调用处理器类实例中的invoke方法。   反射 ——通过类加载器，方法名，方法参数获取的类的方法。后两个在调用具体方法时得知。

~~~



~~~json
# 具体实现 
Proxy 代理handler ，handler代理被代理对象，两层代理
#Proxy创造了自己的一个子类，子类实现了被代理类的接口。
~~~

~~~java
public final class XXX extends Proxy implements XXX
~~~

~~~java
/**一个方法的实现实例*/
 
  public final String SayHello(String paramString)
  {
    try
    {
      return (String)this.h.invoke(this, m4, new Object[] { paramString });
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
~~~

~~~json
# h是传进来InvocationHandler//
Proxy.newProxyInstance(类加载器，接口，类处理器)。
# Proxy实现了接口。
# 调用代理类实现的方法时会，会在proxy里调用thisinvoke，并将代理类this，方法名，方法参数传入。进行第二层的代理
~~~

## CGLIB 动态代理

~~~json
# 通过类对象  Enhancer根据类对象和MethodInterceptor 创造出一个被代理对象的子类c，其中C中有两种方法
1.重写的父类方法  调用MethodInterceptor中的Interceptor方法
2.cglib开头的方法，调用父类的方法
# 通过调用代理类的重写方法调用Interceptor方法，interceptor中做一些增强，返回调用代理类的cglib开头的方法，调用父类的方法

~~~

