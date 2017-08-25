package co.ds.stripes;

import java.util.List;

import com.google.inject.Inject;

import co.ds.bean.Subscriber;
import co.ds.bean.Topic;
import co.ds.mybatis.mapper.TopicMapper;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

@UrlBinding("/topic")
public class TopicAction extends BaseAction {

	private static final String LIST_FORWARD = "/WEB-INF/jsp/topic/list.jsp";
	private static final String FORM_FORWARD = "/WEB-INF/jsp/topic/form.jsp";

	private TopicMapper topicMapper;

	private List<Topic> topics;

	@ValidateNestedProperties({
		@Validate(on = "save", field = "name", required = true)
	})
	private Topic topic;

	@Inject
	public TopicAction(final TopicMapper topicMapper) {
		this.topicMapper = topicMapper;
	}

	@DefaultHandler
	public Resolution list() {
		topics = topicMapper.list();
		return new ForwardResolution(LIST_FORWARD);
	}

	public Resolution cancelList() {
		return new RedirectResolution(HomeAction.class);
	}

	public Resolution edit() {
		topic = topicMapper.fetch(topic.getId());
		return form();
	}
	
	public Resolution form() {
		topics = topicMapper.list();
		return new ForwardResolution(FORM_FORWARD);
	}

	public Resolution cancelForm() {
		return new RedirectResolution(TopicAction.class);
	}

	public Resolution save() {
		if (topic.getId() == null) {
			topicMapper.insert(topic);
		} else {
			topicMapper.update(topic);
		}
		return new RedirectResolution(TopicAction.class);
	}
	
	/* READ-ONLY */
	
	public List<Topic> getTopics() {
		return topics;
	}
	
	/* READ/WRITE */
	
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(final Topic topic) {
		this.topic = topic;
	}
}
