package org.stevengeens.test.arquillian;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.CreateSchema;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.stevengeens.test.arquillian.orm.GeCar;

@RunWith(Arquillian.class)
@CreateSchema("experiment.sql")
public class GeCarTest {
    
	@Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
            .addPackage(GeCar.class.getPackage())
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    // tests go here
    
    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        insertData();
        startTransaction();
    }

    private void clearData() throws Exception {
    	/*
        utx.begin();
        em.joinTransaction();
        System.out.println("Dumping old records...");
        em.createQuery("delete from GeCar").executeUpdate();
        utx.commit();
        */
    }

    private void insertData() throws Exception {
        /*
    	utx.begin();
        em.joinTransaction();
        System.out.println("Inserting records...");
        // ...
        utx.commit();
        // clear the persistence context (first-level cache)
        em.clear();
        */
    	/*
    	try {
	    	Connection jdbcConnection = ds.getConnection();
	    	ScriptRunner sr = new ScriptRunner(jdbcConnection,false,false);
	    	sr.setLogWriter(null);
	    	InputStream initialSql = GeCarTest.class.getClassLoader().getResourceAsStream("experiment.sql");
	    	sr.runScript(new InputStreamReader(initialSql));
			jdbcConnection.commit();
		}
		finally {
			jdbcConnection.close();
		}
		*/
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }
    
    @After
    public void commitTransaction() throws Exception {
        utx.commit();
    }
    
    @Test
    public void shouldFindAllCarsUsingJpqlQuery() throws Exception {

        Assert.fail("Started Execution of shouldFindAllCarsUsingJpqlQuery");
        
        // given
        String fetchingAllCarsInJpql = "select g from GeCar g";
        
        // when
        System.out.println("Selecting (using JPQL)...");
        List<GeCar> cars = em.createQuery(fetchingAllCarsInJpql, GeCar.class).getResultList();

        // then
        System.out.println("Found " + cars.size() + " cars (using JPQL):");
        assertContainsAllCars(cars);
    }
    
    private static void assertContainsAllCars(Collection<GeCar> retrievedCars) {
        Assert.assertEquals(1, retrievedCars.size());
        final Set<String> retrievedCodes = new HashSet<String>();
        for (GeCar car : retrievedCars) {
            retrievedCodes.add(car.getCode());
        }
        Assert.assertTrue(retrievedCodes.containsAll(Arrays.asList("TOY")));
    }
}