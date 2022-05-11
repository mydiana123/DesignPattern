package cn.wjb114514.Facade.Code;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *   org.apache.ibatis.session.Configuration
 *   protected Properties variables = new Properties();
 *   protected ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
 *   protected ObjectFactory objectFactory = new DefaultObjectFactory();
 *   protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
 *
 *   // MetaObject就是外观类， objectFactory, objectWrapperFactory, reflectorFactory 就是三个子部件
 *
 *     public MetaObject newMetaObject(Object object) {
 *     return MetaObject.forObject(object, objectFactory, objectWrapperFactory, reflectorFactory);
 *   }
 *
 *     public static MetaObject forObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory, ReflectorFactory reflectorFactory) {
 *     if (object == null) {
 *       return SystemMetaObject.NULL_META_OBJECT;
 *     } else {
 *       return new MetaObject(object, objectFactory, objectWrapperFactory, reflectorFactory);
 *     }
 *   }
 *   // 这就是界面类内部封装的子系统的逻辑，可以看到子系统的实现还是较为有逻辑的，此方法仅提供Client和子系统之间的一层更简洁的接口
 *   // 我们调用页面类的方法，可以直接获取子系统进行组合工作的结果~
 *   private MetaObject(Object object, ObjectFactory objectFactory, ObjectWrapperFactory objectWrapperFactory, ReflectorFactory reflectorFactory) {
 *     this.originalObject = object;
 *     this.objectFactory = objectFactory;
 *     this.objectWrapperFactory = objectWrapperFactory;
 *     this.reflectorFactory = reflectorFactory;
 *
 *     if (object instanceof ObjectWrapper) {
 *       this.objectWrapper = (ObjectWrapper) object;
 *     } else if (objectWrapperFactory.hasWrapperFor(object)) {
 *       this.objectWrapper = objectWrapperFactory.getWrapperFor(this, object);
 *     } else if (object instanceof Map) {
 *       this.objectWrapper = new MapWrapper(this, (Map) object);
 *     } else if (object instanceof Collection) {
 *       this.objectWrapper = new CollectionWrapper(this, (Collection) object);
 *     } else {
 *       this.objectWrapper = new BeanWrapper(this, object);
 *     }
 *   }
 *
 *
 */

public class Source {
////    Configuration
//Reflector
////    DefaultObjectFactory
    @Test
    public void testMetaObject() {

        // 现在获取的metaObject就可以操作javaBean属性
        JavaBean javaBean = new JavaBean();
        MetaObject metaObject = SystemMetaObject.forObject(javaBean);
        metaObject.setValue("field","test");
        System.out.println(javaBean.getField());

        Map<String,String> map = new HashMap<>();
        MetaObject metaObject1 = SystemMetaObject.forObject(map);
        metaObject1.setValue("hello","world");
        System.out.println(map.get("hello"));
    }
}

/*
梳理下mybatis里这些类是干啥的
metaObject：源信息类 ==> 提供了操作javaBean，Map，Collection内部属性的通道。 底层：反射
也就是说，我们可以通过metaObject完成对javaBean，Map，Collection对象的赋值/取值


其中三个组件之一的ObjectFactory ==> 负责创建对象，主要就干了两件事 1.获取对象的运行时类型 2.获取对象的构造器信息，并创建一个相关对象
  public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
	  // 做接口转换，将接口绑定上默认实现类，如Map->HashMap等
    Class<?> classToCreate = resolveInterface(type);
    // 实例化类classToCreate
    return (T) instantiateClass(classToCreate, constructorArgTypes, constructorArgs);
  }

  // 获得当前接口的实际运行类型。方便反射时调用相关方法
  protected Class<?> resolveInterface(Class<?> type) {
    Class<?> classToCreate;
    if (type == List.class || type == Collection.class || type == Iterable.class) {
      classToCreate = ArrayList.class;
    } else if (type == Map.class) {
      classToCreate = HashMap.class;
    } else if (type == SortedSet.class) { // issue #510 Collections Support
      classToCreate = TreeSet.class;
    } else if (type == Set.class) {
      classToCreate = HashSet.class;
    } else {
        // 检测当前Object为javaBean
      classToCreate = type;
    }
    return classToCreate;
  }

   * type: 对象class
   * constructorArgTypes: 参数class列表
   * constructorArgs: 参数值列表
   // 可以看到大概来说spring创建bean对象也是这么干的，即获取构造器对象，然后使用newInstance方法创建对象。
private <T> T instantiateClass(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
    try {
        // 反射调用构造器
        Constructor<T> constructor;
        if (constructorArgTypes == null || constructorArgs == null) {
            // 获取无参构造[私有化的也可以获取]
            constructor = type.getDeclaredConstructor();
            // 反射暴破[强行操作私有化的构造器]
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            // 返回对象实例
            return constructor.newInstance();
        }
        // 获得有参构造器
        constructor = type
                .getDeclaredConstructor(constructorArgTypes.toArray(new Class[constructorArgTypes.size()]));
        if (!constructor.isAccessible()) {
            constructor.setAccessible(true);
        }
        return constructor.newInstance(constructorArgs.toArray(new Object[constructorArgs.size()]));
    } catch (Exception e) {
        StringBuilder argTypes = new StringBuilder();
        if (constructorArgTypes != null && !constructorArgTypes.isEmpty()) {
            // 拼接出一个方法
            for (Class<?> argType : constructorArgTypes) {
                argTypes.append(argType.getSimpleName());
                argTypes.append(",");
            }
            argTypes.deleteCharAt(argTypes.length() - 1); // remove trailing ,
        }
        StringBuilder argValues = new StringBuilder();
        if (constructorArgs != null && !constructorArgs.isEmpty()) {
            for (Object argValue : constructorArgs) {
                argValues.append(String.valueOf(argValue));
                argValues.append(",");
            }
            argValues.deleteCharAt(argValues.length() - 1); // remove trailing ,
        }
        throw new ReflectionException("Error instantiating " + type + " with invalid types (" + argTypes
                + ") or values (" + argValues + "). Cause: " + e, e);
    }
}
 */

/*
mybatis的反射器

public class Reflector {
  // 对象的实际运行类型
  private final Class<?> type;
  // 对象可以写[set]的属性
  private final String[] readablePropertyNames;
  // 对象可以读[get]的属性
  private final String[] writeablePropertyNames;
  // 对象set方法的映射，应该是把简单方法名和调用器进行了关联。
  // 这种map就可以用作注册表，在IOC BeanFactory里，就使用了很多HashMap管理Bean的信息，比如Bean的Name-Definition的映射，Bean-ClassName映射等...
  private final Map<String, Invoker> setMethods = new HashMap<String, Invoker>();
  private final Map<String, Invoker> getMethods = new HashMap<String, Invoker>();
  // 对象读/写需要的类型。
  private final Map<String, Class<?>> setTypes = new HashMap<String, Class<?>>();
  private final Map<String, Class<?>> getTypes = new HashMap<String, Class<?>>();
  // 对象的默认构造器
  private Constructor<?> defaultConstructor;

  // 大小写不敏感的属性映射 ?? 这个应该是把有大写有小写的字段全映射为 大写/小写字段，应该是供数据库操作使用
  private Map<String, String> caseInsensitivePropertyMap = new HashMap<String, String>();
}


    // 此方法获取了当前对象 所有父类/实现接口，除了Object()的非重写方法外 的全部方法
	private Method[] getClassMethods(Class<?> type) {
		Map<String, Method> uniqueMethods = new HashMap<String, Method>();
		Class<?> currentClass = type;
		// 不返回Object相关方法，如clone() 、equals(Object obj) 、toString() 等
		while (currentClass != null && currentClass != Object.class) {
			// 1. 当前对象定义方法
			addUniqueMethods(uniqueMethods, currentClass.getDeclaredMethods());

			// 2. 当前对象实现的接口方法
			Class<?>[] interfaces = currentClass.getInterfaces();
			for (Class<?> anInterface : interfaces) {
				addUniqueMethods(uniqueMethods, anInterface.getMethods());
			}

			// 3. 递归从当前对象父类继续查找，直到其父对象为Object对象停止
			currentClass = currentClass.getSuperclass();
		}

		Collection<Method> methods = uniqueMethods.values();

		return methods.toArray(new Method[methods.size()]);
	}

	private void addGetMethods(Class<?> type) {
		Map<String, List<Method>> conflictingGetters = new HashMap<String, List<Method>>();
		// 1. 获取Class<?> type所有方法，包括所有私有方法，继承方法，实现接口方法
		Method[] methods = getClassMethods(type);
		for (Method method : methods) {
			if (method.getParameterTypes().length > 0) {
				continue;
			}
			String name = method.getName();
			// 如果我们的方法是 getXX [针对普通属性的获取] 或者isXX [针对boolean类型的获取]
			// 这里还加了一个判断，就是方法名必须是getXX/isXX 单独一个get/is还不行。 所以我们的方法名一定要按照规范来，要是自己随便弄个方法来get/set
			// 人家框架也不认识。
			if ((name.startsWith("get") && name.length() > 3) || (name.startsWith("is") && name.length() > 2)) {

				// 获取当前get方法操作的变量名
				name = PropertyNamer.methodToProperty(name);
				//
				addMethodConflict(conflictingGetters, name, method);
			}
		}

		// 2. 由于Java方法的存在泛型接口方法，需要去重参数类型/返回值类型为Object方法
		// implements Entity<Long> -> void#setId:java.lang.Object
		resolveGetterConflicts(conflictingGetters);
	}

// PropertyNamer 主要是一个用于解析方法名的工具类。
  public static String methodToProperty(String name) {
    if (name.startsWith("is")) {
      // 获取isXX操作的属性，比如isVisitable(); 就是在操作visitable()属性
      name = name.substring(2);
    } else if (name.startsWith("get") || name.startsWith("set")) {
      name = name.substring(3);
    } else {
      throw new ReflectionException("Error parsing property name '" + name + "'.  Didn't start with 'is', 'get' or 'set'.");
    }

    if (name.length() == 1 || (name.length() > 1 && !Character.isUpperCase(name.charAt(1)))) {
      // 因为我们命名规范是 getField()，而实际成员变量是field() 因此需要把第一个字母小写
      name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
    }

    return name;
  }
  // 为什么存在conflictsGetter 也就是冲突的get方法呢?
  // 因为java存在带泛型的方法，JVM把泛型都认为是Object的了，这和我们的预期不相符~
  // 人家是 public void T getXX() {return XX}; 结果我们认为是public void Object getXX()
  // 这就产生了冲突
  // 而且，我们在获取方法时，把这个类的所有方法，包括父类的，实现的接口的方法也给统计了。
  // 因此如果父类/接口里有同名方法，我们优先选择子类的。
  private void resolveGetterConflicts(Map<String, List<Method>> conflictingGetters) {
    // 由于我们之前没有针对泛型方法进行过滤，一股脑的全加进去了，可能产生歧义。
    for (Entry<String, List<Method>> entry : conflictingGetters.entrySet()) {
      Method winner = null;

      // 当前的conflictingGetters 建立了 成员变量-其对应的get方法 的映射。
      // 获取当前get方法操作的属性名
      String propName = entry.getKey();
      // 默认认为get方法不会产生歧义
      boolean isAmbiguous = false;
      // 从当前的同名方法里，选择优胜者（子类方法才是我们需要的那个方法）
      for (Method candidate : entry.getValue()) {
        if (winner == null) {
          winner = candidate;
          continue;
        }
        // 这个过程中，winner优胜者是不断变化的
        // 查看当前优胜者的返回类型
        Class<?> winnerType = winner.getReturnType();
        // 查看当前候选人的返回类型
        Class<?> candidateType = candidate.getReturnType();
        // 如果返回值类型相同，可能存在覆写。也就是这个方法可能是子类重写父类的方法，有可能是歧义。因为同名方法返回值类型相同，就可能造成覆写
        if (candidateType.equals(winnerType)) {
          if (!boolean.class.equals(candidateType)) {
            isAmbiguous = true;
            break;
          } else if (candidate.getName().startsWith("is")) {
            winner = candidate;
          }
        } else if (candidateType.isAssignableFrom(winnerType)) {
          // OK getter type is descendant
          // a.isAssignableFrom(b) b的类型可以赋值给a吗。 子类类型可以赋值给父类，因此如果 子类.isAssignableFrom(父类) 返回false
        } else if (winnerType.isAssignableFrom(candidateType)) {
        // 父类.isAssignableFrom(子类) 返回true。因此此时winner是父类，candidate是 子类，我们要的就是同名方法里的子类方法，这时候就把父类方法过滤掉
          winner = candidate;
        } else {
          isAmbiguous = true;
          break;
        }
      }
      // 把不会产生歧义的方法 正式的加入到get方法集合里
      // 这个方法把 子类的，父类的，实现接口的所有同名方法，选择我们想要的加入到集合里，就不存在同名方法集合了，因为同名方法只有我们想要的那个
      addGetMethod(propName, winner, isAmbiguous);
    }
  }
 */