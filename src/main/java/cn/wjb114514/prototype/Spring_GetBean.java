package cn.wjb114514.prototype;

public class Spring_GetBean {
}

/**
 * <bean id="userDao" class="com.jb.Pro.Dao.Impl.UserDaoImpl"  scope="prototype"/>
 * @Override // Class:AbstractApplicationContext
 * public Object getBean(String name) throws BeansException {
 *    assertBeanFactoryActive();
 *    return getBeanFactory().getBean(name);
 * }
 * // Class:AbstractBeanFactory
 * public Object getBean(String name) throws BeansException {
 *     return this.doGetBean(name, (Class)null, (Object[])null, false);
 * }
 * protected <T> T doGetBean(String name, @Nullable Class<T> requiredType, @Nullable Object[] args, boolean typeCheckOnly) throws BeansException {
 *     String beanName = this.transformedBeanName(name); // 获取bean对象的name属性
 *     Object sharedInstance = this.getSingleton(beanName); // 从单例对象的缓存里获取，如果我们采取单例模式创建Bean对象，就会直接从缓存里获取该对象
 *     Object bean;
 *     if (sharedInstance != null && args == null) {
 *         if (this.logger.isTraceEnabled()) {
 *             if (this.isSingletonCurrentlyInCreation(beanName)) {
 *                 this.logger.trace("Returning eagerly cached instance of singleton bean '" + beanName + "' that is not fully initialized yet - a consequence of a circular reference");
 *             } else {
 *                 this.logger.trace("Returning cached instance of singleton bean '" + beanName + "'");
 *             }
 *         }
 *
 *
 *         bean = this.getObjectForBeanInstance(sharedInstance, name, beanName, (RootBeanDefinition)null);
 *     } else {
 *         if (this.isPrototypeCurrentlyInCreation(beanName)) {
 *             throw new BeanCurrentlyInCreationException(beanName);
 *         }
 *         // 获取用于实例化对象的工厂:工厂设计模式,优先获取父类的工厂
 *         BeanFactory parentBeanFactory = this.getParentBeanFactory();
 *         if (parentBeanFactory != null && !this.containsBeanDefinition(beanName)) {
 *             String nameToLookup = this.originalBeanName(name);
 *             if (parentBeanFactory instanceof AbstractBeanFactory) {
 *                 return ((AbstractBeanFactory)parentBeanFactory).doGetBean(nameToLookup, requiredType, args, typeCheckOnly);
 *             }
 *
 *             if (args != null) {
 *                 return parentBeanFactory.getBean(nameToLookup, args);
 *             }
 *
 *             if (requiredType != null) {
 *                 return parentBeanFactory.getBean(nameToLookup, requiredType);
 *             }
 *
 *             return parentBeanFactory.getBean(nameToLookup);
 *         }
 *
 *         if (!typeCheckOnly) {
 *             this.markBeanAsCreated(beanName);
 *         }
 *         // 重点来了！ 通过注册表里的private final Map<String, RootBeanDefinition> mergedBeanDefinitions = new ConcurrentHashMap<>(256);这个属性，把Bean对象的名字name和BeanDefinition进行了绑定
 *         try { // 我们可以直接获取这个bean对应的BeanDefinition，之前配置的xml里的scope="prototype"属性，就在这个BeanDefinition里！
 *             RootBeanDefinition mbd = this.getMergedLocalBeanDefinition(beanName);
 *             this.checkMergedBeanDefinition(mbd, beanName, args);
 *             String[] dependsOn = mbd.getDependsOn();
 *             String[] var11;
 *             if (dependsOn != null) {
 *                 var11 = dependsOn;
 *                 int var12 = dependsOn.length;
 *
 *                 for(int var13 = 0; var13 < var12; ++var13) {
 *                     String dep = var11[var13];
 *                     if (this.isDependent(beanName, dep)) {
 *                         throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
 *                     }
 *
 *                     this.registerDependentBean(dep, beanName);
 *
 *                     try {
 *                         this.getBean(dep);
 *                     } catch (NoSuchBeanDefinitionException var24) {
 *                         throw new BeanCreationException(mbd.getResourceDescription(), beanName, "'" + beanName + "' depends on missing bean '" + dep + "'", var24);
 *                     }
 *                 }
 *             }
 *
 *             if (mbd.isSingleton()) {
 *                 sharedInstance = this.getSingleton(beanName, () -> {
 *                     try {
 *                         return this.createBean(beanName, mbd, args);
 *                     } catch (BeansException var5) {
 *                         this.destroySingleton(beanName);
 *                         throw var5;
 *                     }
 *                 });
 *                 bean = this.getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
 *             } else if (mbd.isPrototype()) {
 *                 var11 = null;
 *                 // 如果从BeanDefinition里得知采取原型模式创建Bean对象，则使用原型实例来创建
 *                 Object prototypeInstance;
 *                 try {
 *                     this.beforePrototypeCreation(beanName);
 *                     prototypeInstance = this.createBean(beanName, mbd, args);
 *                 } finally {
 *                     this.afterPrototypeCreation(beanName);
 *                 }
 *                   // 使用原型实例创建对象
 *                 bean = this.getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
 *             } else {
 *                 String scopeName = mbd.getScope();
 *                 Scope scope = (Scope)this.scopes.get(scopeName);
 *                 if (scope == null) {
 *                     throw new IllegalStateException("No Scope registered for scope name '" + scopeName + "'");
 *                 }
 *
 *                 try {
 *                     Object scopedInstance = scope.get(beanName, () -> {
 *                         this.beforePrototypeCreation(beanName);
 *
 *                         Object var4;
 *                         try {
 *                             var4 = this.createBean(beanName, mbd, args);
 *                         } finally {
 *                             this.afterPrototypeCreation(beanName);
 *                         }
 *
 *                         return var4;
 *                     });
 *                     bean = this.getObjectForBeanInstance(scopedInstance, name, beanName, mbd);
 *                 } catch (IllegalStateException var23) {
 *                     throw new BeanCreationException(beanName, "Scope '" + scopeName + "' is not active for the current thread; consider defining a scoped proxy for this bean if you intend to refer to it from a singleton", var23);
 *                 }
 *             }
 *         } catch (BeansException var26) {
 *             this.cleanupAfterBeanCreationFailure(beanName);
 *             throw var26;
 *         }
 *     }
 *
 *     if (requiredType != null && !requiredType.isInstance(bean)) {
 *         try {
 *             T convertedBean = this.getTypeConverter().convertIfNecessary(bean, requiredType);
 *             if (convertedBean == null) {
 *                 throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
 *             } else {
 *                 return convertedBean;
 *             }
 *         } catch (TypeMismatchException var25) {
 *             if (this.logger.isTraceEnabled()) {
 *                 this.logger.trace("Failed to convert bean '" + name + "' to required type '" + ClassUtils.getQualifiedName(requiredType) + "'", var25);
 *             }
 *
 *             throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
 *         }
 *     } else {
 *         return bean;
 *     }
 * }
 */
