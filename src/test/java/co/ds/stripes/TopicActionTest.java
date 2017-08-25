package co.ds.stripes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import co.ds.bean.Topic;
import co.ds.mybatis.mapper.TopicMapper;

public class TopicActionTest extends AbstractBaseActionTest<TopicAction> {

	private static final String LIST_FORWARD = "/WEB-INF/jsp/topic/list.jsp";
	private static final String FORM_FORWARD = "/WEB-INF/jsp/topic/form.jsp";
	private static final String HOME_REDIRECT = "/home";
	private static final String TOPIC_REDIRECT = "/topic";
	public static final Integer TOPIC_ID = 99999;
	public static final String TOPIC_NAME = "TOPIC NAME";

	@ConstructorParam
	@Mock
	private TopicMapper topicMapper;

	@Captor
	private ArgumentCaptor<Topic> topicArgumentCaptor;

	@Test
	public void should_display_form() throws Exception {
		when(topicMapper.list()).thenReturn(new ArrayList<Topic>() {{
			add(getTopic());
		}});
		trip.execute();
		assertEquals("Action should have a topic list of 1", 1, action.getTopics().size());
		assertEquals("Topic should have name of TOPIC NAME", TOPIC_NAME, action.getTopics().get(0).getName());
		assertEquals("Unexpected resolution", LIST_FORWARD, trip.getDestination());
	}

	@Test
	public void should_cancel_list() throws Exception {
		trip.execute("cancelList");
		assertEquals("Unexpected resolution", HOME_REDIRECT, trip.getRedirectUrl());
	}

	@Test
	public void should_display_edit() throws Exception {
		trip.setParameter("topic.id", TOPIC_ID.toString());
		when(topicMapper.fetch(TOPIC_ID)).thenReturn(getTopic());
		trip.execute("edit");
		assertEquals("Unexpected resolution", FORM_FORWARD, trip.getDestination());
	}

	@Test
	public void should_cancel_form() throws Exception {
		trip.execute("cancelForm");
		assertEquals("Unexpected resolution", TOPIC_REDIRECT, trip.getRedirectUrl());
	}

	@Test
	public void should_save_new() throws Exception {
		trip.setParameter("topic.name", TOPIC_NAME);
		trip.execute("save");
		verify(topicMapper).insert(topicArgumentCaptor.capture());
		final Topic topic = topicArgumentCaptor.getValue();
		assertNull("ID should be null", topic.getId());
		assertEquals("Unexpected name value", topic.getName(),TOPIC_NAME);
		assertEquals("Unexpected resolution", TOPIC_REDIRECT, trip.getRedirectUrl());
	}

	@Test
	public void should_save_update() throws Exception {
		trip.setParameter("topic.id", TOPIC_ID.toString());
		trip.setParameter("topic.name", TOPIC_NAME);
		trip.execute("save");
		verify(topicMapper).update(topicArgumentCaptor.capture());
		final Topic topic = topicArgumentCaptor.getValue();
		assertEquals("Unexpected ID value",topic.getId(), TOPIC_ID);
		assertEquals("Unexpected name value", topic.getName(), TOPIC_NAME);
		assertEquals("Unexpected resolution", TOPIC_REDIRECT, trip.getRedirectUrl());
	}
	/* HELPERS */

	private Topic getTopic() {
		return new Topic() {{
			setId(TOPIC_ID);
			setName(TOPIC_NAME);
		}};
	}
}
