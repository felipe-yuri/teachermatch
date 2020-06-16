// (c) Everis 2019

package br.com.test.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.easymock.EasyMockSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.MergedContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DelegatingSmartContextLoader;
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ClassUtils;

public class EasyMockMockedLoader extends DelegatingSmartContextLoader {
	private static final Logger LOG = LoggerFactory.getLogger(EasyMockMockedLoader.class);

	private AnnotationConfigContextLoaderDecorator loaderByAnnotations = new AnnotationConfigContextLoaderDecorator();
	private XmlConfigContextLoaderDecorator loaderByXml = new XmlConfigContextLoaderDecorator();

	@SuppressWarnings("all")
	@Override
	public ApplicationContext loadContext(MergedContextConfiguration mergedConfig) throws Exception {
		// Overwrite the annotationConfigLoader and xmlLoader with out implementation
		ReflectionTestUtils.setField(this, "annotationConfigLoader", loaderByAnnotations);
		ReflectionTestUtils.setField(this, "xmlLoader", loaderByXml);
		return super.loadContext(mergedConfig);
	}

	@Override
	public void processContextConfiguration(ContextConfigurationAttributes configAttributes) {
		Class<?>[] classes = configAttributes.getClasses();
		String[] locations = configAttributes.getLocations();
		if (classes.length == 0 && locations.length == 0) {
			// Allow empty loaders, in this case everything should be mocked
			configAttributes.setClasses(new Class<?>[] { Object.class });
		}
		super.processContextConfiguration(configAttributes);
	}

	private class XmlConfigContextLoaderDecorator extends GenericXmlContextLoader {
		@Override
		protected void prepareContext(GenericApplicationContext context) {
			QualifierAnnotationAutowireCandidateResolver qualifierAnnotationAutowireCandidateResolver = new QualifierAnnotationAutowireCandidateResolver();
			BeanFactoryDecorator beanFactoryDecorator = new BeanFactoryDecorator();
			beanFactoryDecorator.setAutowireCandidateResolver(qualifierAnnotationAutowireCandidateResolver);
			// Override the beanFactory with our custom implementation
			ReflectionTestUtils.setField(context, "beanFactory", beanFactoryDecorator);
			super.prepareContext(context);
		}
	}

	private class AnnotationConfigContextLoaderDecorator extends AnnotationConfigContextLoader {
		@Override
		protected void prepareContext(GenericApplicationContext context) {
			QualifierAnnotationAutowireCandidateResolver qualifierAnnotationAutowireCandidateResolver = new QualifierAnnotationAutowireCandidateResolver();
			BeanFactoryDecorator beanFactoryDecorator = new BeanFactoryDecorator();
			beanFactoryDecorator.setAutowireCandidateResolver(qualifierAnnotationAutowireCandidateResolver);
			// Override the beanFactory with our custom implementation
			ReflectionTestUtils.setField(context, "beanFactory", beanFactoryDecorator);
			super.prepareContext(context);
		}
	}

	private static class BeanFactoryDecorator extends DefaultListableBeanFactory {
		private static final String VALUE = "value";

		private final Set<Class<? extends Annotation>> qualifierTypes = new LinkedHashSet<Class<? extends Annotation>>(
				2);

		private final Map<String, Object> mockedBeansByName = new HashMap<String, Object>();
		private final Map<Class<?>, Object> mockedBeansByType = new HashMap<Class<?>, Object>();

		private final EasyMockSupport easyMockSupport = new EasyMockSupport();

		@SuppressWarnings("unchecked")
		public BeanFactoryDecorator() {
			this.qualifierTypes.add(Qualifier.class);
			try {
				this.qualifierTypes.add((Class<? extends Annotation>) ClassUtils.forName("javax.inject.Qualifier",
						QualifierAnnotationAutowireCandidateResolver.class.getClassLoader()));
			} catch (ClassNotFoundException ex) {
				// JSR-330 API not available - simply skip.
			}
		}

//      FIXME : uncomment this method if you are using Spring Framework 4.3.3.RELEASE or higher
//		@SuppressWarnings("unchecked")
//		@Override
//		public <T> NamedBeanHolder<T> resolveNamedBean(Class<T> requiredType) throws BeansException {
//			try {
//				return super.resolveNamedBean(requiredType);
//			} catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
//				return new NamedBeanHolder<T>(requiredType.getCanonicalName(), (T) getMockedBeanByType(requiredType));
//			}
//		}

		@Override
		public Object resolveDependency(DependencyDescriptor descriptor, String beanName,
				Set<String> autowiredBeanNames, TypeConverter typeConverter) {
			try {
				return super.resolveDependency(descriptor, beanName, autowiredBeanNames, typeConverter);
			} catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
				Class<?> dependencyType = descriptor.getDependencyType();
				String beanDependencyName = getBeanDependencyNameByQualifierAnnotation(descriptor);
				if (Modifier.isFinal(dependencyType.getModifiers())) {
					if (dependencyType == String.class) {
						if (beanDependencyName != null && !beanDependencyName.isEmpty()) {
							return beanDependencyName;
						} else {
							return descriptor.getField().getName();
						}
					}
					throw new NoSuchBeanDefinitionException(dependencyType,
							"Cannot create an automatic mock for final type: " + dependencyType);
				}
				Object mockedBean;
				if (beanDependencyName != null && !beanDependencyName.isEmpty()) {
					mockedBean = mockedBeansByName.get(beanDependencyName);
					if (mockedBean == null) {
						LOG.info("Did not find a mocked bean for name {}", beanDependencyName);
						mockedBean = createMockExceptEasyMockSupport(dependencyType);
						mockedBeansByName.put(beanDependencyName, mockedBean);
					} else {
						LOG.info("returning mocked bean for name {}", beanDependencyName);
					}
				} else {
					mockedBean = getMockedBeanByType(dependencyType);
				}
				return mockedBean;
			}
		}

		private Object getMockedBeanByType(Class<?> dependencyType) {
			Object mockedBean;
			mockedBean = mockedBeansByType.get(dependencyType);
			if (mockedBean == null) {
				LOG.info("Did not find a mocked bean for type {}", dependencyType);
				mockedBean = createMockExceptEasyMockSupport(dependencyType);
				mockedBeansByType.put(dependencyType, mockedBean);
			} else {
				LOG.info("returning mocked bean for type {}", dependencyType);
			}
			return mockedBean;
		}

		private Object createMockExceptEasyMockSupport(Class<?> dependencyType) {
			if (easyMockSupport.getClass().equals(dependencyType)) {
				return easyMockSupport;
			}
			return easyMockSupport.createMock(dependencyType);
		}

		private String getBeanDependencyNameByQualifierAnnotation(DependencyDescriptor descriptor) {
			String beanDependencyName = null;
			for (Annotation annotation : descriptor.getAnnotations()) {
				if (isQualifierAnnotation(annotation.annotationType())) {
					beanDependencyName = getAnnotationAtributteValue(annotation, VALUE).toString();
				}
			}
			return beanDependencyName;
		}

		private Object getAnnotationAtributteValue(Annotation annotation, String attributeName) {
			if (annotation == null || attributeName == null || attributeName.isEmpty()) {
				return null;
			}
			try {
				Method method = annotation.annotationType().getDeclaredMethod(attributeName);
				if ((!Modifier.isPublic(method.getModifiers())
						|| !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
					method.setAccessible(true);
				}
				return method.invoke(annotation);
			} catch (Exception ex) {
				return null;
			}
		}

		private boolean isQualifierAnnotation(Class<? extends Annotation> annotationType) {
			for (Class<? extends Annotation> qualifierType : this.qualifierTypes) {
				if (annotationType.equals(qualifierType) || annotationType.isAnnotationPresent(qualifierType)) {
					return true;
				}
			}
			return false;
		}
	}
}
