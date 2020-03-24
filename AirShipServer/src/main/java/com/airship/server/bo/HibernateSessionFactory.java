package com.airship.server.bo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSessionFactory {
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<Session>();// 创建一个ThreadLocal<Session>对象用来存放当前Session对象
	private static Configuration configuration = new Configuration();
	private static SessionFactory sessionFactory;
	private static String configFile = CONFIG_FILE_LOCATION;

	static {
		try {
			configuration.configure();// 读取并解析hibernate.cfg.xml文件

			ServiceRegistry serviceRgistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();

			sessionFactory = configuration.buildSessionFactory(serviceRgistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private HibernateSessionFactory() {
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void rebuildSessionFactory() {
		synchronized (sessionFactory) {
			try {
				configuration.configure(configFile);
				ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).buildServiceRegistry();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//打開session
	public static Session getSession() {
		//獲取當前session的對象
		Session session = sessionThreadLocal.get();
		try {
			if (session == null || !session.isOpen()) {
				if (sessionFactory == null) {// 如果sessionFactory为null，创建一个
					rebuildSessionFactory();
				}
				//如果session没有打开，就用sessionFactory打开
				session = (sessionFactory != null) ? sessionFactory.openSession() : null;
				//　//将session对象放到ThreadLocal对象里，以便使用
				sessionThreadLocal.set(session);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return session;
	}

	public static void closeSession() {
		Session session = sessionThreadLocal.get();
		sessionThreadLocal.set(null);
		try {
			if (session != null && session.isOpen()) {
				session.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setConfigFile(String configFile) {
		HibernateSessionFactory.configFile = configFile;
		sessionFactory = null;
	}

	public static Configuration getConfiguration() {
		return configuration;
	}
}