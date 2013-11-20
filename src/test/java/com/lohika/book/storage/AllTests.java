package com.lohika.book.storage;

import integration.BookServiceIntegrationTest;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.lohika.book.storage.config.ApplicationConfigTest;
import com.lohika.book.storage.config.ConfigurationKeyTest;
import com.lohika.book.storage.config.ConfiguratorTest;
import com.lohika.book.storage.dao.BaseDaoTest;
import com.lohika.book.storage.dao.BookDaoTest;
import com.lohika.book.storage.dao.DomainManagerFactory;
import com.lohika.book.storage.dao.DomainManagerFactoryTest;
import com.lohika.book.storage.service.BookServiceTest;
import com.lohika.book.storage.service.FileManagerTest;
import com.lohika.book.storage.service.FileServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ ApplicationConfigTest.class, ConfigurationKeyTest.class,
        ConfiguratorTest.class, BaseDaoTest.class, BookDaoTest.class,
        DomainManagerFactoryTest.class, BookServiceTest.class,
        FileManagerTest.class, FileServiceTest.class,BookServiceIntegrationTest.class })
public class AllTests {
    
    @BeforeClass
    public static void setUpClass() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceTest");
        DomainManagerFactory.setEntityManagerFactory(entityManagerFactory);
    }

    @AfterClass
    public static void tearDownClass() {
        new DomainManagerFactory().contextDestroyed(null);
    }
}
