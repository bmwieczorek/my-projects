package com.bawi.spring.boot;

import com.bawi.spring.boot.entity.Product;
import com.bawi.spring.boot.entity.Shop;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

@SpringBootApplication
public class MySpringBootHibernateNPlus1HSQLDB {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySpringBootHibernateNPlus1HSQLDB.class);

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootHibernateNPlus1HSQLDB.class, args);
    }

    @Autowired
    private ShopDao shopDao;

    @Repository
    static class ShopDao {

        @Autowired
        SessionFactory sessionFactory;

        @SuppressWarnings("WeakerAccess")
        @Transactional
        public void save(Shop shop) {
            sessionFactory.getCurrentSession().save(shop);
        }

        @SuppressWarnings("WeakerAccess")
        @Transactional
        public List<Shop> findAll() {
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(Shop.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            @SuppressWarnings("unchecked")
            List<Shop> list = criteria.list();
            return list;
        }
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
           Stream.of(new Shop("PL",
                        new Product("water", BigDecimal.valueOf(1.5)),
                        new Product("bread", BigDecimal.valueOf(2.0))),
                     new Shop("US", new Product("car", BigDecimal.valueOf(100)))
                 )
                 .forEach(shop -> {
                     shopDao.save(shop);
                     LOGGER.info("Saved: {}", shop);
                 });

            List<Shop> shops = shopDao.findAll();
            // if fetchType=lazy retrieve all shops without associated products:
            // select this_.id as id1_1_0_, this_.shipment_address as shipment2_1_0_ from Shop this_

            // if fetchType=eager retrieve all shops with associated products using outer join:
            // select this_.id as id1_1_1_, this_.shipment_address as shipment2_1_1_, products2_.shop_id as shop_id4_0_3_, products2_.id as id1_0_3_, products2_.id as id1_0_0_, products2_.name as name2_0_0_, products2_.price as price3_0_0_, products2_.shop_id as shop_id4_0_0_
            //  from Shop this_ left outer join Product products2_ on this_.id=products2_.shop_id

            shops.forEach(shop -> LOGGER.info("Retrieved: {}", shop));
            // if fetchType=lazy and if shop's toString include products then run n selects for products for n shops:
            // select products0_.shop_id as shop_id4_0_0_, products0_.id as id1_0_0_, products0_.id as id1_0_1_, products0_.name as name2_0_1_, products0_.price as price3_0_1_, products0_.shop_id as shop_id4_0_1_
            //  from Product products0_ where products0_.shop_id=?

            List<Product> products = shops.get(0).getProducts();
            LOGGER.info("Retrieved products from first shop: {}", products); // in case of lazy fetchType select only for products from first shop
            // if fetchType=lazy then retrieve only products for first shop
        };
    }

    @Bean
    LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.bawi.spring.boot.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:.");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);
        return properties;
    }

    @Bean
    @Autowired
    HibernateTransactionManager transactionManager(SessionFactory sf) {
        return new HibernateTransactionManager(sf);
    }

}
