package co.ds.guice;

import co.ds.servlet.HsqlDbSetupServlet;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.DynamicMappingFilter;
import net.sourceforge.stripes.controller.StripesFilter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BasicServletModuleTest {

	@Mock
	@Bind(to = SqlSessionManager.class)
	private SqlSessionManager sqlSessionManager;

	@Mock
	private SqlSession sqlSession;

	@Mock
	private Connection connection;

	@Before
	public void before() {
		initMocks(this);
	}

	@Test
	public void should_build_servlet_module() throws NoSuchFieldException, IllegalAccessException {

		when(sqlSessionManager.openSession()).thenReturn(sqlSession);
		when(sqlSession.getConnection()).thenReturn(connection);

		final Injector injector =
				Guice.createInjector(new BasicServletModule(), BoundFieldModule.of(this));
		injector.injectMembers(this);

		// THIS TEST IS PURELY TO MAKE SURE THE CONFIGURATION DOESN'T BLOW UP
	}

}
